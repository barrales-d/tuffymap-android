package edu.fullerton.tuffymap.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import edu.fullerton.tuffymap.R
import edu.fullerton.tuffymap.navigation.components.LoginComponent
import edu.fullerton.tuffymap.navigation.events.LoginEvent
import edu.fullerton.tuffymap.ui.theme.TuffyMapTheme


@Composable
fun LoginScreen(component: LoginComponent) {
    val email by component.email.subscribeAsState()
    val password by component.password.subscribeAsState()

    val textFieldModifier = Modifier.fillMaxWidth().padding(16.dp)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.tuffy_map_logo),
            contentDescription = "Tuffy map logo",
            modifier = Modifier.aspectRatio(1.0f)
        )
        Text("Tuffy Map")

        OutlinedTextField(
            value = email,
            onValueChange = { component.onEvent(LoginEvent.UpdateEmail(it)) },
            modifier = textFieldModifier,
            placeholder = { Text("Enter Email:") },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Email, contentDescription = null)
            }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { component.onEvent(LoginEvent.UpdatePassword(it)) },
            modifier = textFieldModifier,
            placeholder = { Text("Enter Password:") },
            label = { Text("Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            isError = component.isValid.value,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, contentDescription = null)
            }
        )

        Row {
            val buttonModifier = Modifier.padding(8.dp).fillMaxWidth().weight(1.0f)
            OutlinedButton(
                onClick = { component.onEvent(LoginEvent.ClickRegisterButton) },
                modifier = buttonModifier
            ) {
                Text("Register")
            }

            Button(
                onClick = { component.onEvent(LoginEvent.ClickLoginButton) },
                modifier = buttonModifier
            ) {
                Text("Login")
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    TuffyMapTheme {
//        LoginScreen()
    }
}