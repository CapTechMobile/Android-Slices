package com.captech.android.demos.slices.provider

import android.net.Uri

class ContentUris {

    companion object {
        val URI_MAKE = Uri.parse(ReservationUriStrings.RESERVATION_MAKE)
        val URI_READY = Uri.parse(ReservationUriStrings.RESERVATION_READY)
        val URI_RATE = Uri.parse(ReservationUriStrings.RESERVATION_RATE)
    }

    class ReservationUriStrings {
        companion object {
            const val RESERVATION_MAKE = "content://com.captech.android.demos.slices.provider" + ReservationPaths.MAKE
            const val RESERVATION_READY = "content://com.captech.android.demos.slices.provider" + ReservationPaths.READY
            const val RESERVATION_RATE = "content://com.captech.android.demos.slices.provider" + ReservationPaths.RATE
        }
    }

    class ReservationPaths {
        companion object {
            const val MAKE = "/make-reservation"
            const val READY = "/reservation"
            const val RATE = "/rate-reservation"
        }
    }
}