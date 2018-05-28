package bmc.com.gangamecommons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bmc.com.gangamecommons.inflate

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = container?.inflate(getLayoutResId())
        return view
    }

    abstract fun getLayoutResId(): Int
}