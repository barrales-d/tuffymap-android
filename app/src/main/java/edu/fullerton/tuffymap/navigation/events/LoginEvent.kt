package edu.fullerton.tuffymap.navigation.events

sealed interface LoginEvent {
    data object ClickLoginButton: LoginEvent
    data object ClickRegisterButton: LoginEvent
    data class UpdateEmail(val text: String): LoginEvent
    data class UpdatePassword(val text: String): LoginEvent
}
