package com.captech.android.demos.slices.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.captech.android.demos.slices.SlicesApplication
import com.captech.android.demos.slices.data.repository.HotelRepository
import com.captech.android.demos.slices.provider.ContentUris

class HotelsBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val REQUEST_CODE_SORT_BY_PRICE = 2001
        const val REQUEST_CODE_SORT_BY_DISTANCE = 2002
        const val REQUEST_CODE_SEE_MORE = 2003
        const val EXTRA_REQUEST_CODE = "EXTRA_REQUEST_CODE"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && intent.hasExtra(EXTRA_REQUEST_CODE)) {
            val requestCode = intent.getIntExtra(EXTRA_REQUEST_CODE, REQUEST_CODE_SORT_BY_PRICE)
            when {
                requestCode == REQUEST_CODE_SORT_BY_PRICE -> {
                    setSortingType(context, HotelRepository.SortBy.PRICE)
                    refreshReservationList(context)
                }
                requestCode == REQUEST_CODE_SORT_BY_DISTANCE -> {
                    setSortingType(context, HotelRepository.SortBy.DISTANCE)
                    refreshReservationList(context)
                }
                requestCode == REQUEST_CODE_SEE_MORE -> {
                    Toast.makeText(context, "SEE MORE",
                            Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun refreshReservationList(context: Context?) {
        if (context != null) {
            context.contentResolver.notifyChange(ContentUris.URI_MAKE, null)
        }
    }

    private fun setSortingType(context: Context?, @HotelRepository.SortingOptions sortingOption: String) {
        val application = getApplication(context)
        if (application != null) {
            application.appState.sortingType = sortingOption
            Toast.makeText(context, getApplication(context)?.appState?.sortingType,
                    Toast.LENGTH_LONG).show()
        }
    }

    private fun getApplication(context: Context?): SlicesApplication? {
        return if (context != null)
            context.applicationContext as SlicesApplication
        else null
    }
}