package com.captech.android.demos.slices.util

import android.content.Context
import android.location.Address
import java.util.*

class HotelUtils {
    companion object {
        fun getDistanceInMiles(context: Context, address: Address): Double {
            return 0.2 + Random(address.hashCode().toLong()).nextDouble();
        }
    }

}
