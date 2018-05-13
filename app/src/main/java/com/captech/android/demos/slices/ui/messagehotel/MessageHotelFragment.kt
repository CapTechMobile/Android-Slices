package com.captech.android.demos.slices.ui.messagehotel

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.captech.android.demos.slices.R

class MessageHotelFragment : Fragment() {

    companion object {
        fun newInstance() = MessageHotelFragment()
    }

    private lateinit var viewModel: MessageHotelViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_message_hotel, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MessageHotelViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
