package bmc.com.gangame.data

import bmc.com.gangame.model.TopGame

object TopGameMapper {

    fun fromSdk(topGame: bmc.com.gangamesdk.model.TopGame, position: Int) : TopGame {
        return with(topGame) {
            TopGame(title, owners, steamRating, publisher, price, position, thumb)
        }
    }
}