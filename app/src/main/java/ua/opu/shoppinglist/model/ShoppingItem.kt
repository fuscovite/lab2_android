package ua.opu.shoppinglist.model

data class ShoppingItem(
    val id: String,
    val name: String,
    val isBought: Boolean = false,
)
