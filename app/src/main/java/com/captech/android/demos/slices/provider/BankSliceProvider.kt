package com.captech.android.demos.slices.provider

import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat

import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.ListBuilder.SMALL_IMAGE
import androidx.slice.builders.SliceAction
import com.captech.android.demos.slices.R
import com.captech.android.demos.slices.SendMoneyActivity

class BankSliceProvider : SliceProvider() {
    /**
     * Instantiate any required objects. Return true if the provider was successfully created,
     * false otherwise.
     */
    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    /**
     * Converts URL to content URI (i.e. content://com.captech.bankingslices...)
     */
    override fun onMapIntentToUri(intent: Intent?): Uri {
        // Note: implementing this is only required if you plan on catching URL requests.
        // This is an example solution.
        var uriBuilder: Uri.Builder = Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
        if (intent == null) return uriBuilder.build()
        val data = intent.data
        if (data != null && data.path != null) {
            val path = data.path.replace("/", "")
            uriBuilder = uriBuilder.path(path)
        }

        if (context != null) {
            uriBuilder = uriBuilder.authority(context.packageName)
        }
        return uriBuilder.build()
    }

    /**
     * Construct the Slice and bind data if available.
     */
    override fun onBindSlice(sliceUri: Uri): Slice? {
        return when {
            sliceUri.path == "/atm" -> createAtmSlice(sliceUri)
            sliceUri.path == "/paymentdue" -> createPaymentDueSlice(sliceUri)
            else -> null
        }
    }

    private fun createPaymentDueSlice(sliceUri: Uri): Slice {
        return ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                .setAccentColor(Color.parseColor("#3cba54")) // Specify color for tinting icons
                .addRow {
                    it.apply {
                        setTitle("Payment Due: 5/20/18")
                        setSubtitle("Amount: $234.56")
                        addEndItem(sendMoneyAction())
                    }
                }
                .build()
    }

    private fun createAtmSlice(sliceUri: Uri): Slice {
        // Create the parent builder.
        return ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                .setAccentColor(Color.parseColor("#000000"))
                .setHeader {
                    it.apply {
                        setTitle("Nearby ATMs")
                        setSubtitle("Within 5 miles")
                        setPrimaryAction(mapsAction())
                    }
                }
                .addAction(mapsAction())
                .addGridRow {
                    it.apply {
                        addCell {
                            it.apply {
                                addImage(IconCompat.createWithResource(context, R.drawable.bank).setTint(Color.parseColor("#db3236")), SMALL_IMAGE)
                                addTitleText("Bank 1")
                                addText("0.7 mi")
                                setContentIntent(mapsIntent())
                            }
                        }
                        addCell {
                            it.apply {
                                addImage(IconCompat.createWithResource(context, R.drawable.bank).setTint(Color.parseColor("#f4c20d")), SMALL_IMAGE)
                                addTitleText("Bank 2")
                                addText("2.5 mi")
                                setContentIntent(mapsIntent())
                            }
                        }
                        addCell {
                            it.apply {
                                addImage(IconCompat.createWithResource(context, R.drawable.bank).setTint(Color.parseColor("#3cba54")), SMALL_IMAGE)
                                addTitleText("Bank 3")
                                addText("2.9 mi")
                                setContentIntent(mapsIntent())
                            }
                        }
                        addCell {
                            it.apply {
                                addImage(IconCompat.createWithResource(context, R.drawable.bank).setTint(Color.parseColor("#4885ed")), SMALL_IMAGE)
                                addTitleText("Bank 4")
                                addText("4.2 mi")
                                setContentIntent(mapsIntent())
                            }
                        }
                    }
                }
                .build()
    }

    private fun mapsIntent(): PendingIntent {
        val gmmIntentUri = Uri.parse("google.navigation:q=7100+Forest+Ave, Richmond+Virginia")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.`package` = "com.google.android.apps.maps"
        return PendingIntent.getActivity(context, 0, mapIntent, 0)
    }

    private fun mapsAction(): SliceAction {
        return SliceAction(mapsIntent(),
                IconCompat.createWithResource(context, R.drawable.ic_map_marker),
                "Open Google Maps."
        )
    }

    private fun sendMoneyAction(): SliceAction {
        val sendMoneyIntent = Intent(context, SendMoneyActivity::class.java)
        return SliceAction(PendingIntent.getActivity(context, 0, sendMoneyIntent, 0),
                IconCompat.createWithResource(context, R.drawable.ic_send_money), "Send Money Action")
    }
}
