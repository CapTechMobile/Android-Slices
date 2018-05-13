package com.captech.android.demos.slices.data.model

import android.location.Address
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import java.util.*


data class Hotel(val name: String, val ratePerNight: Double, @DrawableRes val imageResId: Int, val address: Address) : Parcelable {

    public constructor() : this("", 0.0, -1, Address(Locale.getDefault())) {}

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readParcelable(Address::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(ratePerNight)
        parcel.writeInt(imageResId)
        parcel.writeParcelable(address, flags)
    }

    override fun describeContents(): Int {
        return 0;
    }

    companion object CREATOR : Parcelable.Creator<Hotel> {
        override fun createFromParcel(parcel: Parcel): Hotel {
            return Hotel(parcel)
        }

        override fun newArray(size: Int): Array<Hotel?> {
            return arrayOfNulls(size)
        }
    }
}