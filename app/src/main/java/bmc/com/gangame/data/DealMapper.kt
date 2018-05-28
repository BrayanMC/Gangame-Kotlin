package bmc.com.gangame.data

import bmc.com.gangame.model.Deal

object DealMapper {

    fun fromSdk(deal: bmc.com.gangamesdk.model.Deal): Deal {
        return with(deal) {
            Deal(title, salePrice, normalPrice, metacriticScore, steamRating, thumb)
        }
    }
}