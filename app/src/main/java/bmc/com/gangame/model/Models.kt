package bmc.com.gangame.model

object priceFormatter {
    val FORMAT_PRICE = "$%.2f"

    fun priceFormatted(price: Float) = String.format(FORMAT_PRICE, price)
}

data class Deal(var title: String,
                var salePrice: Float,
                var normalPrice: Float,
                var metacriticScore: Int,
                var steamRating: Int,
                var thumb: String) {

    val salePriceFormatted: String
        get() = priceFormatter.priceFormatted(salePrice)

    val normalPriceFormatted: String
        get() = priceFormatter.priceFormatted(normalPrice)
}

data class TopGame(var title: String,
                   var owners: String,
                   var ratingSteam: Int,
                   var publisher: String,
                   var price: Float,
                   var position: Int,
                   var thumb: String) {

    val priceFormatted: String
        get() = priceFormatter.priceFormatted(price)
}