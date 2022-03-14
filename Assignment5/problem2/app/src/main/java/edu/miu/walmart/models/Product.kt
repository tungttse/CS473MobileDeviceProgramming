package edu.miu.walmart.models

data class Product(
    var title: String,
    var price: Double,
    var color: String,
    var image: Int,
    var itemid: String,
    var desc: String
)