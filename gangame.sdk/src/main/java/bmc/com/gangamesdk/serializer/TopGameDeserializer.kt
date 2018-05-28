package bmc.com.gangamesdk.serializer

import bmc.com.gangamesdk.model.TopGame
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TopGameDeserializer : JsonDeserializer<TopGame> {

    companion object {
        const val BASE_IMG_URL = "http://cdn.akamai.steamstatic.com/steam/apps/%s/capsule_sm_120.jpg?t=1488471030"
        const val KEY_GAME_ID = "appid"
        const val KEY_SCORE_RANK = "score_rank"
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): TopGame {
        val topGame = Gson().fromJson(json, TopGame::class.java)

        //topGame.price = topGame.price / 100F

        val jsonGame = json.asJsonObject

        val score = jsonGame[KEY_SCORE_RANK].asString
        topGame.steamRating = if(score.isEmpty()) 0 else Integer.parseInt(score)
        topGame.price = topGame.price / 100
        topGame.thumb = String.format(BASE_IMG_URL, jsonGame[KEY_GAME_ID].asInt)

        return topGame
    }
}