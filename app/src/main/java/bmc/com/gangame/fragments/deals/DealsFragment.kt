package bmc.com.gangame.fragments.deals

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.View
import bmc.com.gangame.BR
import bmc.com.gangame.R
import bmc.com.gangame.data.GangameDataSource
import bmc.com.gangame.model.Deal
import bmc.com.gangamecommons.BaseListFragment
import bmc.com.gangamecommons.DataBindingRecyclerAdapter

class DealsFragment : BaseListFragment() {

    override fun getAdapter(): RecyclerView.Adapter<*> {
        return DataBindingRecyclerAdapter<Deal>(BR.deal, R.layout.item_deal)
    }

    override fun onResume() {
        super.onResume()
        showDeals()
    }

    fun showDeals() {
        GangameDataSource
                .getDeals()
                .subscribe({ list ->
                    replaceItems(list)
                }, { error ->
                    showError(error)
                })
    }

    private fun replaceItems(list: List<Deal>) {
        with(listAdapter as DataBindingRecyclerAdapter<Deal>) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }
    }

    private fun showError(error: Throwable) {
        error.printStackTrace()
        if (view != null)
            Snackbar.make(view as View,
                    R.string.error_deals_request,
                    Snackbar.LENGTH_LONG)
                    .setAction(R.string.message_retry_request, { showDeals() })
                    .show()
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (listAdapter as DataBindingRecyclerAdapter<Deal>)
                .items
                .addAll(getDummieDeals())
        listAdapter.notifyDataSetChanged()
    }

    fun getDummieDeals(): ArrayList<Deal> {
        return arrayListOf(Deal("Counter Strike",
                0.99F,
                9.99F,
                80,
                80,
                "http://cdn.akamai.steamstatic.com/steam/apps/10/capsule_184x69.jpg"))
    }*/
}
