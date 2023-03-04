package ru.stas.model

sealed class Product {
    abstract val category: String
    abstract val image_url: String
    abstract val name: String
}

data class FlashSaleProduct(
    override val category: String,
    override val image_url: String,
    override val name: String,
    val discount: Int?,
    val price: Double?
) : Product()

data class LatestXProduct(
    override val category: String,
    override val image_url: String,
    override val name: String,
    val price: Double
) : Product()
