package ua.opu.shoppinglist.viewmodel

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ShoppingViewModelTest {
    @Test
    fun addAndToggleItem_updatesList() {
        val viewModel = ShoppingViewModel()

        viewModel.updateNewItemName("Молоко")
        viewModel.addItem()

        assertEquals(1, viewModel.items.value.size)
        assertEquals("Молоко", viewModel.items.value.single().name)
        assertFalse(viewModel.items.value.single().isBought)

        viewModel.toggleBought(viewModel.items.value.single().id)

        assertTrue(viewModel.items.value.single().isBought)
    }
}
