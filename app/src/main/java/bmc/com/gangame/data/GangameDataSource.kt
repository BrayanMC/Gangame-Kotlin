package bmc.com.gangame.data

import bmc.com.gangame.model.Deal
import bmc.com.gangame.model.TopGame
import bmc.com.gangamesdk.GangameApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object GangameDataSource {

    val apiService = GangameApiService()

    fun getDeals(): Observable<ArrayList<Deal>> {
        return apiService.apiClient
                .getDealsObservable()
                .map { sdkDeals ->
                    val deals = sdkDeals.map { deal ->
                        DealMapper.fromSdk(deal)
                    }
                    val arrayList = arrayListOf<Deal>()
                    arrayList.addAll(deals)
                    arrayList
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun getTopRated(): Observable<ArrayList<TopGame>> {
        return apiService.apiClient
                .getTop100GamesObservable()
                .map { sdkGames ->
                    val games = sdkGames.mapIndexed { index, game ->
                        TopGameMapper.fromSdk(game, index + 1)
                    }
                    val arrayList = arrayListOf<TopGame>()
                    arrayList.addAll(games)
                    arrayList
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun getMostOwned(): Observable<ArrayList<TopGame>> {
        return apiService.apiClient
                .getMostOwnedObservable()
                .map { sdkGames ->
                    val games = sdkGames.mapIndexed { index, game ->
                        TopGameMapper.fromSdk(game, index + 1)
                    }
                    val arrayList = arrayListOf<TopGame>()
                    arrayList.addAll(games)
                    arrayList
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}