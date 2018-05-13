package com.captech.android.demos.slices.util

import android.content.Context
import android.location.Address
import java.util.*

class HotelUtils {
    companion object {
        const val MAX_RATING: Int = 5;
        fun getDistanceInMiles(context: Context, address: Address): Double {
            return 0.2 + Random(address.hashCode().toLong()).nextDouble();
        }

        fun getRemainingRating(rating: Int): Int {
            return MAX_RATING - rating;
        }
    }


}
