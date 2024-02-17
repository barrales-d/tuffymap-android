package edu.fullerton.tuffymap.navigation.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import edu.fullerton.tuffymap.navigation.events.LoginEvent

class LoginComponent (
    componentContext: ComponentContext,
    private val onNavigateToLogin: () -> Unit,
    private val onNavigateToMain: (loginSucceeded: Boolean) -> Unit
) : ComponentContext by componentContext {
    private var mEmail = MutableValue("")
    private var mPassword = MutableValue("")
    private var mIsValid = MutableValue(false)

    val email: Value<String> = mEmail
    val password: Value<String> = mPassword
    val isValid: Value<Boolean> = mIsValid

    fun onEvent(event: LoginEvent) {
        when(event) {
            LoginEvent.ClickLoginButton -> {
                val loginSucceeded = true
                mIsValid.value = loginSucceeded
                // pass variable to callback to keep firebase authentication local to this file?
                onNavigateToMain(loginSucceeded)
            }
            LoginEvent.ClickRegisterButton -> {
                /*pass callback to Constructor to navigate to next page */
                onNavigateToLogin()
            }
            is LoginEvent.UpdateEmail -> {
                mEmail.value = event.text
            }
            is LoginEvent.UpdatePassword -> {
                mPassword.value = event.text
            }
        }
    }

}