package bmc.com.gangamecommons

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import bmc.com.gangamecommons.R
import kotlinx.android.synthetic.main.fragment_list.view.*

abstract class BaseListFragment : BaseFragment() {

    lateinit var listAdapter: RecyclerView.Adapter<*>

    override fun getLayoutResId(): Int {
        return R.layout.fragment_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = getAdapter()

        view.recyclerView?.let {
            with(view.recyclerView) {
                this.adapter = listAdapter
                this.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    abstract fun getAdapter(): RecyclerView.Adapter<*>
}