package com.captech.android.demos.slices.state

import com.captech.android.demos.slices.data.repository.HotelRepository

class AppState() {
    @HotelRepository.SortingOptions
    var sortingType = HotelRepository.SortBy.NONE
    var rating = 0;
}
