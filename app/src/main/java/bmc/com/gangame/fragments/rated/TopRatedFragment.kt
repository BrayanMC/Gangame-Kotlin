package bmc.com.gangame.fragments.rated

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.View
import bmc.com.gangame.BR
import bmc.com.gangame.R
import bmc.com.gangame.data.GangameDataSource
import bmc.com.gangame.model.TopGame
import bmc.com.gangamecommons.BaseListFragment
import bmc.com.gangamecommons.DataBindingRecyclerAdapter

class TopRatedFragment : BaseListFragment() {

    override fun getAdapter(): RecyclerView.Adapter<*> {
        return DataBindingRecyclerAdapter<TopGame>(BR.topGame, R.layout.item_top_game)
    }

    override fun onResume() {
        super.onResume()
        showTopRated()
    }

    fun showTopRated() {
        GangameDataSource
                .getTopRated()
                .subscribe({ list ->
                    replaceItems(list)
                }, { error ->
                    showError(error)
                })
    }

    private fun replaceItems(list: List<TopGame>) {
        with(listAdapter as DataBindingRecyclerAdapter<TopGame>) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }
    }

    private fun showError(error: Throwable) {
        error.printStackTrace()
        if (view != null)
            Snackbar.make(view as View,
                    R.string.error_games_request,
                    Snackbar.LENGTH_LONG)
                    .setAction(R.string.message_retry_request, { showTopRated() })
                    .show()
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (listAdapter as DataBindingRecyclerAdapter<TopGame>)
                .items
                .addAll(getDummyTopGames())
        listAdapter.notifyDataSetChanged()
    }

    private fun getDummyTopGames(): ArrayList<TopGame> {
        return arrayListOf(TopGame(title = "Counter Strike",
                owners = 13407338,
                publisher = "Valve",
                ratingSteam = 80,
                price = 9.99F,
                position = 1,
                thumb = "http://cdn.akamai.steamstatic.com/steam/apps/10/capsule_184x69.jpg"))
    }*/
}
