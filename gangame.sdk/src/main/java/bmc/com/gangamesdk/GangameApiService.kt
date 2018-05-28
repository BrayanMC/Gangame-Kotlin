package bmc.com.gangamesdk

import bmc.com.gangamesdk.model.TopGame
import bmc.com.gangamesdk.serializer.ListTopGameDeserializer
import bmc.com.gangamesdk.serializer.TopGameDeserializer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GangameApiService(val apiConfig: GangameApiConfig = GangameClientConfig()) {

    val apiClient: RetrofitGangameApi

    init {
        val typeToken = object : TypeToken<ArrayList<TopGame>>() {}.type

        val gson = GsonBuilder()
                .registerTypeAdapter(typeToken, ListTopGameDeserializer())
                .registerTypeAdapter(TopGame::class.java, TopGameDeserializer())
                .create()

        val apiClientConfig =
                Retrofit.Builder()
                        .baseUrl(Routes.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        apiConfig.setupConfig(apiClientConfig)
        apiClient = apiClientConfig.build().create(RetrofitGangameApi::class.java)
    }
}
