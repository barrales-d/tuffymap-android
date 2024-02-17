package edu.fullerton.tuffymap.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import edu.fullerton.tuffymap.navigation.components.MainComponent
import edu.fullerton.tuffymap.ui.theme.TuffyMapTheme

@Composable
fun MainScreen(component: MainComponent) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Home", "Classes", "Settings")

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = (tabIndex == index),
                    onClick = { tabIndex = index },
                )
            }
        }
        when(tabIndex) {
            0 -> {
                HomeScreen()
            }
            1 -> {
                Text(text = "Classes")
            }
            2 -> {
                SettingScreen {
                    component.onNavigateToLogin()
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    TuffyMapTheme {
        MainScreen(
            MainComponent(
                componentContext = DefaultComponentContext(LifecycleRegistry()),
                onNavigateToLogin = { }
            )
        )
    }
}