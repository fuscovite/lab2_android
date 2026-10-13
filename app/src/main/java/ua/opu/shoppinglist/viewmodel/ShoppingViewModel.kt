package ua.opu.shoppinglist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ua.opu.shoppinglist.model.ShoppingItem

class ShoppingViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val items: StateFlow<List<ShoppingItem>> = _items.asStateFlow()

    private val _newItemName = MutableStateFlow("")
    val newItemName: StateFlow<String> = _newItemName.asStateFlow()

    val remainingCount: StateFlow<Int> = items
        .map { list -> list.count { !it.isBought } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0,
        )

    fun updateNewItemName(value: String) {
        _newItemName.value = value
    }

    fun addItem() {
        val name = _newItemName.value.trim()
        if (name.isEmpty()) return

        _items.value += ShoppingItem(
            id = UUID.randomUUID().toString(),
            name = name,
        )
        _newItemName.value = ""
    }

    fun toggleBought(id: String) {
        _items.value = _items.value.map { item ->
            if (item.id == id) item.copy(isBought = !item.isBought) else item
        }
    }
}
