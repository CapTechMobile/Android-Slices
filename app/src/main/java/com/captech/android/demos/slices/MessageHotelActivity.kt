package com.captech.android.demos.slices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.captech.android.demos.slices.ui.messagehotel.MessageHotelFragment

class MessageHotelActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_MESSAGE_HOTEL = 5001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_hotel)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MessageHotelFragment.newInstance())
                    .commitNow()
        }
    }

}
