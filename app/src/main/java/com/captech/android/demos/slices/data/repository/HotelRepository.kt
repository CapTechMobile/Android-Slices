package com.captech.android.demos.slices.data.repository

import android.location.Address
import androidx.annotation.StringDef
import com.captech.android.demos.slices.R
import com.captech.android.demos.slices.data.model.Hotel
import java.util.*

class HotelRepository {

    companion object SortBy {
        const val NONE = "NONE"
        const val PRICE = "PRICE"
        const val DISTANCE = "DISTANCE"
    }

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(
            NONE,
            PRICE,
            DISTANCE
    )
    annotation class SortingOptions {
    }

    var hotels: List<Hotel> = emptyList()

    constructor() {
        var hotel1 = Hotel(
                "Hilariott Richmond",
                134.00,
                R.drawable.ic_hotel_01,
                Address(Locale.getDefault())
                        .apply {
                            setAddressLine(1, "1 2nd St")
                            latitude = 38.971624
                            longitude = -76.479221
                        }
        )
        var hotel2 = Hotel(
                "Days Weeks Inn RVA",
                189.00,
                R.drawable.ic_hotel_02,
                Address(Locale.getDefault())
                        .apply {
                            setAddressLine(1, "618 N 1st St")
                            latitude = 37.548868
                            longitude = -77.437728
                        }
        )
        var hotel3 = Hotel(
                "Ritzni Short Pump",
                140.00,
                R.drawable.ic_hotel_03,
                Address(Locale.getDefault())
                        .apply {
                            setAddressLine(1, "613 N 2nd St")
                            latitude = 37.548056
                            longitude = -77.436695
                        }
        )
        var hotel4 = Hotel(
                "La Rancharia RVA",
                99.00,
                R.drawable.ic_hotel_04,
                Address(Locale.getDefault())
                        .apply {
                            setAddressLine(1, "514 N 3rd St")
                            latitude = 37.546705
                            longitude = -77.436780
                        }
        )
        var hotel5 = Hotel(
                "Motel Hotel Richmond",
                102.00,
                R.drawable.ic_hotel_05,
                Address(Locale.getDefault())
                        .apply {
                            setAddressLine(1, "708 E Broad St")
                            latitude = 37.541859
                            longitude = -77.435245
                        }
        )
        var hotel6 = Hotel(
                "Fancy Place Inn Richmond",
                201.00,
                R.drawable.ic_hotel_06,
                Address(Locale.getDefault())
                        .apply {
                            setAddressLine(1, "1200 E Cary St")
                            latitude = 37.535886
                            longitude = -77.434301
                        }
        )
        var hotel7 = Hotel(
                "The Grand Richmond",
                249.00,
                R.drawable.ic_hotel_07,
                Address(Locale.getDefault())
                        .apply {
                            setAddressLine(1, "100 S 12th St")
                            latitude = 37.535777
                            longitude = -77.435494
                        }
        )
        hotels = listOf(hotel1, hotel2, hotel3, hotel4, hotel5, hotel6, hotel7)
    }
}