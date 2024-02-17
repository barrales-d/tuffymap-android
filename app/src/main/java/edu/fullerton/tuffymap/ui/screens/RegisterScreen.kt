package edu.fullerton.tuffymap.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import edu.fullerton.tuffymap.R
import edu.fullerton.tuffymap.navigation.components.RegisterComponent
import edu.fullerton.tuffymap.navigation.events.RegisterEvent

@Composable
fun RegisterScreen(component: RegisterComponent)
{
    val uName by component.uName.subscribeAsState()
    val password by component.password.subscribeAsState()
    val cPassword by component.cPassword.subscribeAsState()
    val fName by component.fName.subscribeAsState()
    val lName by component.lName.subscribeAsState()
    val email by component.email.subscribeAsState()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
    {

        Image(
            painter = painterResource(R.drawable.tuffy_map_logo),
            contentDescription = "Tuffy map logo",
            modifier = Modifier.aspectRatio(1.95f)
        )
        Text("Tuffy Map")

        OutlinedTextField(
            value = uName,
            onValueChange = { component.onevent(RegisterEvent.UpdateUserName(it)) },
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            placeholder = { Text("Enter UserName:")},
            label = { Text("UserName") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = {component.onevent(RegisterEvent.UpdatePassword(it))},
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            placeholder = { Text("Enter Password:")},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        OutlinedTextField(
            value = cPassword,
            onValueChange = {component.onevent(RegisterEvent.UpdateConfirmPassword(it))},
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            placeholder = { Text("Confirm Password:")},
            label = { Text("Confirm Password") }
        )

        OutlinedTextField(
            value = fName,
            onValueChange = {component.onevent(RegisterEvent.UpdateFirstName(it))},
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            placeholder = { Text("Enter First Name:")},
            label = { Text("First Name") }
        )

        OutlinedTextField(
            value = lName,
            onValueChange = {component.onevent(RegisterEvent.UpdateLastName(it))},
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            placeholder = { Text("Enter Last Name")},
            label = { Text("Last Name") }
        )
        OutlinedTextField(
            value = email,
            onValueChange = {component.onevent(RegisterEvent.UpdateEmail(it))},
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            placeholder = { Text("Enter Email:")},
            label = { Text("Email") }
        )

        Row {
            Button(
                onClick = { component.onevent(RegisterEvent.ClickRegisterButton) },
                modifier = Modifier.fillMaxWidth().weight(1.0f).padding(5.dp)
            ) { Text("Submit") }
        }
    }

}