package edu.fullerton.tuffymap.navigation.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import edu.fullerton.tuffymap.navigation.events.RegisterEvent

class RegisterComponent(
    componentContext: ComponentContext,
    private val navigateToLogin: () -> Unit
    //private val onNavigateToMain: (registerSuccess: Boolean) -> Unit
)
    : ComponentContext by componentContext
{
    private var vUserName = MutableValue("")
    private var vPassword = MutableValue("")
    private var vConfirmPassword = MutableValue("")
    private var vFirstName = MutableValue("")
    private var vLastName = MutableValue("")
    private var vEmail = MutableValue("")

    val uName: Value<String> = vUserName
    val password: Value<String> = vPassword
    val cPassword: Value<String> = vConfirmPassword
    val fName: Value<String> = vFirstName
    val lName: Value<String> = vLastName
    val email: Value<String> = vEmail


    fun onevent(event: RegisterEvent)
    {
        when(event)
        {
            RegisterEvent.ClickRegisterButton ->
            {
//                /pass callback to  to navigate to next page/
                navigateToLogin()
            }
            is RegisterEvent.UpdateUserName -> {
                vUserName.value = event.text
            }
            is RegisterEvent.UpdatePassword -> {
                vPassword.value = event.text
            }
            is RegisterEvent.UpdateConfirmPassword -> {
                vConfirmPassword.value = event.text
            }
            is RegisterEvent.UpdateFirstName -> {
                vFirstName.value = event.text
            }
            is RegisterEvent.UpdateLastName -> {
                vLastName.value = event.text
            }
            is RegisterEvent.UpdateEmail -> {
                vEmail.value = event.text
            }
        }
    }
}
