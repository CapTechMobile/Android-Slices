package com.captech.android.demos.slices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class SendMoneyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_money)

        var makePaymentBtn = findViewById<View>(R.id.makePaymentBtn) as Button
        makePaymentBtn.setOnClickListener {
            Toast.makeText(this@SendMoneyActivity, "Payment Sent", Toast.LENGTH_SHORT).show()
        }
    }
}
