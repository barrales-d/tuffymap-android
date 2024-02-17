package edu.fullerton.tuffymap.navigation.components

import com.arkivanov.decompose.ComponentContext

class MainComponent(
    componentContext: ComponentContext,
    val onNavigateToLogin: () -> Unit
) : ComponentContext by componentContext {
}
