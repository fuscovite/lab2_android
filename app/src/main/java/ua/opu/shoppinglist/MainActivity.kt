package ua.opu.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import ua.opu.shoppinglist.ui.ShoppingScreen
import ua.opu.shoppinglist.ui.theme.ShoppingListTheme
import ua.opu.shoppinglist.viewmodel.ShoppingViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ShoppingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val items by viewModel.items.collectAsStateWithLifecycle()
            val newItemName by viewModel.newItemName.collectAsStateWithLifecycle()
            val remainingCount by viewModel.remainingCount.collectAsStateWithLifecycle()

            ShoppingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingScreen(
                        items = items,
                        newItemName = newItemName,
                        remainingCount = remainingCount,
                        onNameChange = viewModel::updateNewItemName,
                        onAddItem = viewModel::addItem,
                        onToggleItem = viewModel::toggleBought,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
