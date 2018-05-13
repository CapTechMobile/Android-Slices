package com.captech.android.demos.slices.ui.viewhotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.captech.android.demos.slices.R
import com.captech.android.demos.slices.data.model.Hotel

class ViewHotelFragment : Fragment() {

    companion object {
        const val EXTRA_HOTEL: String = "EXTRA_HOTEL"
        fun newInstance(hotel: Hotel?): ViewHotelFragment {
            val viewHotelFragment = ViewHotelFragment()
            val args = Bundle()
            if (hotel != null) {
                args.putParcelable(EXTRA_HOTEL, hotel)
            }
            viewHotelFragment.arguments = args
            return viewHotelFragment
        }
    }

    private lateinit var viewModel: ViewHotelViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this).get(ViewHotelViewModel::class.java)
        if (arguments != null && arguments!!.containsKey(EXTRA_HOTEL)) {
            viewModel.hotel = arguments!!.getParcelable<Hotel>(EXTRA_HOTEL)
        }
        val view = inflater.inflate(R.layout.fragment_view_hotel, container, false)
        view.findViewById<TextView>(R.id.hotel_name).setText(viewModel.hotel.name)
        view.findViewById<ImageView>(R.id.image_hotel_image).setImageDrawable(ContextCompat.getDrawable(activity!!.applicationContext, viewModel.hotel.imageResId))
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
