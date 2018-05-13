package com.captech.android.demos.slices.ui.seemorehotels

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.captech.android.demos.slices.R

class SeeMoreHotelsFragment : Fragment() {

    companion object {
        fun newInstance() = SeeMoreHotelsFragment()
    }

    private lateinit var viewModel: SeeMoreHotelsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_see_more_hotels, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SeeMoreHotelsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
