package com.captech.android.demos.slices.ui.viewhotel

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.captech.android.demos.slices.data.model.Hotel

class ViewHotelViewModel() : ViewModel(), Parcelable {

    @Bindable
    lateinit var hotel: Hotel

    constructor(parcel: Parcel) : this() {
        hotel = parcel.readParcelable(Hotel::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(hotel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ViewHotelViewModel> {
        override fun createFromParcel(parcel: Parcel): ViewHotelViewModel {
            return ViewHotelViewModel(parcel)
        }

        override fun newArray(size: Int): Array<ViewHotelViewModel?> {
            return arrayOfNulls(size)
        }
    }
}
