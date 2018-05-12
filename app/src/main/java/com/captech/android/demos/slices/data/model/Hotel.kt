package com.captech.android.demos.slices.data.model

import android.location.Address
import androidx.annotation.DrawableRes

data class Hotel(val name: String, val ratePerNight: Double, @DrawableRes val imageResId: Int, val address: Address)