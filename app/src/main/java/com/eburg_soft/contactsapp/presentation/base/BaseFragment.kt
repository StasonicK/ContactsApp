package com.eburg_soft.contactsapp.presentation.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.eburg_soft.contactsapp.R

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    //todo set inject

    override fun onCreate(savedInstanceState: Bundle?) {
        //todo set component
        super.onCreate(savedInstanceState)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.screen_right_in, R.anim.screen_left_out)
    }
}