package com.captech.android.demos.slices.provider

import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.GridRowBuilder
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.ListBuilder.*
import androidx.slice.builders.SliceAction
import com.captech.android.demos.slices.*
import com.captech.android.demos.slices.data.model.Hotel
import com.captech.android.demos.slices.data.repository.HotelRepository
import com.captech.android.demos.slices.receiver.HotelsBroadcastReceiver
import com.captech.android.demos.slices.util.HotelUtils
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class HotelSliceProvider : SliceProvider() {

    /**
     * Instantiate any required objects. Return true if the provider was successfully created,
     * false otherwise.
     */
    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    /**
     * Converts URL to content URI (i.e. content://com.captech.android.demos.slices.provider...)
     */
    override fun onMapIntentToUri(intent: Intent?): Uri {
        // Note: implementing this is only required if you plan on catching URL requests.
        // This is an example solution.
        var uriBuilder: Uri.Builder = Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
        if (intent == null) return uriBuilder.build()
        val data = intent.data
        if (data != null && data.path != null) {
            val path = data.path.replace("/", "")
            uriBuilder = uriBuilder.path(path)
        }
        val context = context
        if (context != null) {
            uriBuilder = uriBuilder.authority(context.getPackageName())
        }
        return uriBuilder.build()
    }

    /**
     * Construct the Slice and bind data if available.
     */
    override fun onBindSlice(sliceUri: Uri): Slice? {
        val context = getContext() ?: return null
        return when {
            sliceUri.path.equals(ContentUris.ReservationPaths.MAKE, true) ->
                createSliceMakeReservation(context, sliceUri)
            sliceUri.path.equals(ContentUris.ReservationPaths.READY, true) ->
                createSliceReservationReady(context, sliceUri)
            sliceUri.path.equals(ContentUris.ReservationPaths.RATE, true) ->
                createSliceRateReservation(context, sliceUri)
            else -> null
        }
    }

    private fun createSliceMakeReservation(context: Context, sliceUri: Uri): Slice? {
        val maxHotels = 3;
        val hotelRepo = HotelRepository();
        val hotels: List<Hotel> = getSortedHotels(context, hotelRepo).take(maxHotels)
        val seeMoreAction = SliceAction(createSeeMoreIntent(),
                IconCompat.createWithResource(context, R.drawable.ic_more),
                ICON_IMAGE, "Sort by Price")
        val sortByPriceAction = SliceAction(createSortByPriceIntent(),
                IconCompat.createWithResource(context, R.drawable.ic_sort_price),
                ICON_IMAGE, "Sort by Price")
        val sortByDistanceAction = SliceAction(createSortByDistanceIntent(),
                IconCompat.createWithResource(context, R.drawable.ic_map_marker_distance),
                ICON_IMAGE, "Sort by Distance")
        val listBuilder = ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                .setHeader {
                    it.apply {
                        setTitle("Make a Reservation")
                        setPrimaryAction(seeMoreAction)
                    }
                }
                .addAction(seeMoreAction)
        val gridRowBuilder = GridRowBuilder(listBuilder)

        hotels.forEach { hotel ->
            gridRowBuilder.addCell {
                it.apply {
                    addImage(IconCompat.createWithResource(context, hotel.imageResId), LARGE_IMAGE)
                    addTitleText(hotel.name)
                    addText(getPriceAndDistance(context, hotel))
                    setContentIntent(createViewHotelIntent(hotel))
                }
            }
        }
        listBuilder.addGridRow(gridRowBuilder)
        listBuilder.addRow() {
            it.apply {
                setTitle("Sort Hotels")
                setSubtitle(getSubtitleMakeReservation())
                addEndItem(sortByPriceAction)
                addEndItem(sortByDistanceAction)
            }
        }
        return listBuilder.build()
    }

    private fun createSliceReservationReady(context: Context, sliceUri: Uri): Slice? {
        val hotelRepo = HotelRepository();
        val hotel = hotelRepo.hotels.first();
        val checkInAction = SliceAction(createCheckInIntent(),
                IconCompat.createWithResource(context, R.drawable.ic_marker_check),
                ICON_IMAGE, "Check In")
        val messageHotelAction = SliceAction(createMessageIntent(),
                IconCompat.createWithResource(context, R.drawable.ic_forum),
                ICON_IMAGE, "Message the Front Desk")
        val listBuilder = ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                .setHeader {
                    it.apply {
                        setTitle("Your Reservation is Ready")
                        setPrimaryAction(checkInAction)
                    }
                }
                .addAction(checkInAction)
                .addAction(messageHotelAction)
                .addGridRow {
                    it.apply {
                        addCell {
                            it.apply {
                                addImage(IconCompat.createWithResource(context, hotel.imageResId), LARGE_IMAGE)
                                addTitleText(hotel.name)
                                addText(getPriceAndDistance(context, hotel))
                                setContentIntent(createViewHotelIntent(hotel))
                            }
                        }
                    }
                }
        return listBuilder.build()
    }

    private fun createSliceRateReservation(context: Context, sliceUri: Uri): Slice? {
        val listBuilder = ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                .setHeader {
                    it.apply {
                        setTitle("Rate Your Stay")
                    }
                }
        val gridRowBuilder = GridRowBuilder(listBuilder)

        val rating = (context.applicationContext as SlicesApplication).appState.rating
        val remainingRating = HotelUtils.getRemainingRating(rating);
        for (i in 1..rating) {
            gridRowBuilder.addCell {
                it.apply {
                    addImage(IconCompat.createWithResource(context, R.drawable.ic_star_filled), SMALL_IMAGE)
                    setContentIntent(createSetRatingIntent(i))
                }
            }
        }
        if (rating < HotelUtils.MAX_RATING) {
            for (i in rating + 1..(rating + remainingRating)) {
                gridRowBuilder.addCell {
                    it.apply {
                        addImage(IconCompat.createWithResource(context, R.drawable.ic_star), SMALL_IMAGE)
                        setContentIntent(createSetRatingIntent(i))
                    }
                }
            }
        }
        listBuilder.addGridRow(gridRowBuilder)
        return listBuilder.build()
    }

    private fun getSubtitleMakeReservation(): CharSequence {
        @HotelRepository.SortingOptions val sortBy = (context.applicationContext as SlicesApplication).appState.sortingType
        return if (!HotelRepository.NONE.equals(sortBy, true)) "By " + sortBy else "";
    }

    private fun getSortedHotels(context: Context, hotelRepo: HotelRepository): List<Hotel> {
        @HotelRepository.SortingOptions val sortBy = (context.applicationContext as SlicesApplication).appState.sortingType
        return when {
            HotelRepository.PRICE.equals(sortBy, true) ->
                hotelRepo.hotels.sortedWith(compareBy({ it.ratePerNight }))
            HotelRepository.DISTANCE.equals(sortBy, true) ->
                hotelRepo.hotels.sortedWith(compareBy({ HotelUtils.getDistanceInMiles(context, it.address) }))
            else ->
                hotelRepo.hotels
        }
    }

    private fun getPriceAndDistance(context: Context, hotel: Hotel): String {
        val currencyInstance = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val formatter = DecimalFormat("#0.00")
        val price = currencyInstance.format(hotel.ratePerNight)
        val distance = formatter.format(HotelUtils.getDistanceInMiles(context, hotel.address))
        return price + " - " + distance + "mi"
    }

    private fun createSortByPriceIntent(): PendingIntent {
        val intent = Intent(context, HotelsBroadcastReceiver::class.java)
        val requestCode = HotelsBroadcastReceiver.REQUEST_CODE_SORT_BY_PRICE
        intent.putExtra(HotelsBroadcastReceiver.EXTRA_REQUEST_CODE, requestCode)
        return PendingIntent.getBroadcast(context, requestCode, intent, 0)
    }

    private fun createSortByDistanceIntent(): PendingIntent {
        val intent = Intent(context, HotelsBroadcastReceiver::class.java)
        val requestCode = HotelsBroadcastReceiver.REQUEST_CODE_SORT_BY_DISTANCE
        intent.putExtra(HotelsBroadcastReceiver.EXTRA_REQUEST_CODE, requestCode)
        return PendingIntent.getBroadcast(context, requestCode, intent, 0)
    }

    private fun createSeeMoreIntent(): PendingIntent {
        val intent = Intent(context, SeeMoreHotelsActivity::class.java)
        val requestCode = SeeMoreHotelsActivity.REQUEST_CODE_SEE_MORE_HOTELS
        intent.setAction(System.currentTimeMillis().toString())
        return PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createViewHotelIntent(hotel: Hotel): PendingIntent {
        val intent = Intent(context, ViewHotelActivity::class.java)
        val requestCode = ViewHotelActivity.REQUEST_CODE_VIEW_HOTEL
        intent.putExtra(ViewHotelActivity.EXTRA_HOTEL, hotel)
        intent.setAction(System.currentTimeMillis().toString())
        return PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createCheckInIntent(): PendingIntent {
        val intent = Intent(context, HotelsBroadcastReceiver::class.java)
        val requestCode = HotelsBroadcastReceiver.REQUEST_CODE_CHECK_IN
        intent.putExtra(HotelsBroadcastReceiver.EXTRA_REQUEST_CODE, requestCode)
        return PendingIntent.getBroadcast(context, requestCode, intent, 0)
    }

    private fun createMessageIntent(): PendingIntent {
        val intent = Intent(context, MessageHotelActivity::class.java)
        val requestCode = MessageHotelActivity.REQUEST_CODE_MESSAGE_HOTEL
        intent.setAction(System.currentTimeMillis().toString())
        return PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createSetRatingIntent(rating: Int): PendingIntent {
        val intent = Intent(context, HotelsBroadcastReceiver::class.java)
        val requestCode = HotelsBroadcastReceiver.REQUEST_CODE_SET_RATING
        intent.putExtra(HotelsBroadcastReceiver.EXTRA_REQUEST_CODE, requestCode)
        intent.putExtra(HotelsBroadcastReceiver.EXTRA_RATING, rating)
        intent.setAction(System.currentTimeMillis().toString())
        return PendingIntent.getBroadcast(context, requestCode, intent, 0)
    }

    /**
     * Slice has been pinned to external process. Subscribe to data source if necessary.
     */
    override fun onSlicePinned(sliceUri: Uri?) {
        // When data is received, call context.contentResolver.notifyChange(sliceUri, null) to
        // trigger HotelSliceProvider#onBindSlice(Uri) again.
    }

    /**
     * Unsubscribe from data source if necessary.
     */
    override fun onSliceUnpinned(sliceUri: Uri?) {
        // Remove any observers if necessary to avoid memory leaks.
    }
}
