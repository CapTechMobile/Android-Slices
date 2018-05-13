package com.captech.android.demos.slices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.captech.android.demos.slices.ui.seemorehotels.SeeMoreHotelsFragment

class SeeMoreHotelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_more_hotels)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SeeMoreHotelsFragment.newInstance())
                    .commitNow()
        }
    }

}
