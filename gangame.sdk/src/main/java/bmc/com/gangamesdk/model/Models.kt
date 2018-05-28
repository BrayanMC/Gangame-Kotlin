package bmc.com.gangamesdk.model

import com.google.gson.annotations.SerializedName

data class Deal(var title: String,
                var salePrice: Float,
                var normalPrice: Float,
                var metacriticScore: Int,
                @SerializedName("steamRatingPercent") var steamRating: Int,
                var thumb: String)

data class TopGame(@SerializedName("name") var title: String,
                   var publisher: String,
                   @SerializedName("score_rank") var steamRating: Int,
                   var owners: String,
                   var price: Float,
                   var thumb: String)
