package edu.miu.walmart.models

import java.io.Serializable

data class Product(
    var title: String,
    var price: Double,
    var color: String,
    var image: Int,
    var itemid: String,
    var desc: String
): Serializable