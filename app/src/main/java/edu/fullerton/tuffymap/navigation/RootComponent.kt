package edu.fullerton.tuffymap.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import edu.fullerton.tuffymap.navigation.components.LoginComponent
import edu.fullerton.tuffymap.navigation.components.MainComponent
import edu.fullerton.tuffymap.navigation.components.RegisterComponent
import kotlinx.serialization.Serializable

class RootComponent(componentContext: ComponentContext)
    : ComponentContext by componentContext {
        private val navigation = StackNavigation<Config>()
        val childStack = childStack(
            source = navigation,
            serializer = Config.serializer(),
            //can provide a ListOfNotNull(Config, Config, ...)
            // maybe have the root component have a variable loggedIn
            // initialConfiguration = { ListOfNotNull(if (loggedIn) Config.MainPage else Config.LoginPage) }
            // it might not even need to be in a list since it only one screen (either home or login page)
            initialConfiguration = Config.LoginPage,
            handleBackButton = true,
            childFactory = ::createChild
        )
        @OptIn(ExperimentalDecomposeApi::class)
        private fun createChild(
            config: Config,
            context: ComponentContext
        ): Child {
            return when(config) {
                Config.LoginPage -> Child.LoginPage(
                    LoginComponent(
                        componentContext = context,
                        onNavigateToLogin = {
                            navigation.pushNew(Config.RegisterPage)
                        },
                        //note: can use navigation.replaceALl(Config.HomePage)
                        //hopefully this way the back button wont go back to login page ?
                        onNavigateToMain = { successfulLogin ->
                            if(successfulLogin) {
                                navigation.replaceAll(Config.MainPage)
                            }
                        }
                    )
                )
                Config.RegisterPage -> Child.RegisterPage(RegisterComponent(context))
                Config.MainPage -> Child.MainPage(
                    MainComponent(
                        componentContext = context,
                        onNavigateToLogin = {
                            navigation.replaceAll(Config.LoginPage)
                        }
                    )
                )
            }
        }

        sealed class Child {
            data class LoginPage(val component: LoginComponent): Child()
            data class RegisterPage(val component: RegisterComponent): Child()
            data class MainPage(val component: MainComponent): Child()
        }

        @Serializable
        sealed class Config {
            @Serializable
            data object LoginPage: Config()
            @Serializable
            data object RegisterPage: Config()
            @Serializable
            data object MainPage: Config()
        }
}