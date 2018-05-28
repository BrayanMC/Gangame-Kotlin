package bmc.com.gangamesdk

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.junit.Assert
import org.junit.Test

class RequestTest {

    @Test
    fun dealsRequest_success() {
        val apiService = GangameApiService()
        val response = apiService.apiClient.getDeals().execute()
        val deals = response.body()

        val jsonArray: JsonArray = JsonParser().parse(MockResponses.DEALS_RESPONSE).asJsonArray

        Assert.assertTrue(response.isSuccessful)
        deals?.let {
            Assert.assertEquals(deals.size, jsonArray.size())
            deals.zip(jsonArray).forEach { (deal, jsonDeal) ->
                with(jsonDeal.asJsonObject) {

                    Assert.assertEquals(deal.title, this["title"].asString)
                    Assert.assertEquals(deal.metacriticScore, this["metacriticScore"].asInt)
                    Assert.assertEquals(deal.salePrice, this["salePrice"].asFloat)
                    Assert.assertEquals(deal.normalPrice, this["normalPrice"].asFloat)
                    Assert.assertEquals(deal.steamRating, this["steamRatingPercent"].asInt)
                    Assert.assertEquals(deal.thumb, this["thumb"].asString)
                }
            }
        }
    }

    @Test
    fun topRatedRequest_success() {
        val apiService = GangameApiService()
        val response = apiService.apiClient.getTopRatedGames().execute()
        val topGames = response.body()

        val listJsonObject: List<JsonObject> = JsonParser().parse(MockResponses.TOP_100_GAMES)
                .asJsonObject
                .entrySet()
                .map { (_, json) ->
                    json.asJsonObject
                }

        Assert.assertTrue(response.isSuccessful)
        topGames?.let {
            Assert.assertEquals(topGames.size, listJsonObject.size)
            topGames.zip(listJsonObject).forEach { (topGame, jsonTopGame) ->
                with(jsonTopGame.asJsonObject) {

                    Assert.assertEquals(topGame.title, this["name"].asString)
                    Assert.assertEquals(topGame.owners, this["owners"].asInt)
                    Assert.assertEquals(topGame.price, this["price"].asFloat)
                    Assert.assertEquals(topGame.publisher, this["publisher"].asString)
                    Assert.assertEquals(topGame.steamRating, this["score_rank"].asInt)
                    Assert.assertEquals(topGame.thumb, "http://cdn.akamai.steamstatic.com/steam/apps/${this["appid"].asInt}/capsule_sm_120.jpg?t=1488471030")
                }
            }
        }
    }
}