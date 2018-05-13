package com.captech.android.demos.slices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.captech.android.demos.slices.data.model.Hotel
import com.captech.android.demos.slices.ui.viewhotel.ViewHotelFragment

class ViewHotelActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOTEL: String = "EXTRA_HOTEL"
        const val REQUEST_CODE_VIEW_HOTEL: Int = 3001
    }

    private lateinit var hotel: Hotel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_hotel)
        if (intent != null && intent.hasExtra(EXTRA_HOTEL)) {
            hotel = intent.getParcelableExtra(EXTRA_HOTEL)
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ViewHotelFragment.newInstance(hotel))
                .commitNow()
    }

}
