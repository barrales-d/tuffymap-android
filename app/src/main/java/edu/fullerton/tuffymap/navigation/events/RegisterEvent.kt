package edu.fullerton.tuffymap.navigation.events

sealed interface RegisterEvent
{
    data object ClickRegisterButton: RegisterEvent
    data class UpdateUserName(val text: String) : RegisterEvent
    data class UpdatePassword(val text: String) : RegisterEvent
    data class UpdateConfirmPassword(val text: String) : RegisterEvent
    data class UpdateFirstName(val text: String) : RegisterEvent
    data class UpdateLastName(val text: String) : RegisterEvent
    data class UpdateEmail(val text: String) : RegisterEvent
}