package bmc.com.gangamesdk

import bmc.com.gangamesdk.model.Deal
import bmc.com.gangamesdk.model.TopGame
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitGangameApi {

    @GET(Routes.GET_DEALS)
    fun getDeals(): Call<ArrayList<Deal>>

    @GET(Routes.GET_TOP_100_GAMES)
    fun getTopRatedGames(): Call<ArrayList<TopGame>>

    @GET(Routes.GET_MOST_OWNED_GAMES)
    fun getMostOwned(): Call<ArrayList<TopGame>>

    @GET(Routes.GET_DEALS)
    fun getDealsObservable(): Observable<ArrayList<Deal>>

    @GET(Routes.GET_TOP_100_GAMES)
    fun getTop100GamesObservable(): Observable<ArrayList<TopGame>>

    @GET(Routes.GET_MOST_OWNED_GAMES)
    fun getMostOwnedObservable(): Observable<ArrayList<TopGame>>
}
