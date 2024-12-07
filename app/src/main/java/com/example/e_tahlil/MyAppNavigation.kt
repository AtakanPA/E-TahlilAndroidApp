package com.example.e_tahlil

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.e_tahlil.pages.HomePage
import com.example.e_tahlil.pages.LoginPage
import com.example.e_tahlil.pages.SignupPage

@Composable
fun MyAppNavigation(modifier:Modifier=Modifier,authViewModel: AuthViewModel){
    val navController:NavHostController=rememberNavController()
    NavHost(navController=navController, startDestination = "login"){

        composable("login") {

            LoginPage(modifier,navController,authViewModel)



        }
        composable("signup") {

            SignupPage(modifier,navController,authViewModel)



        }
        composable("home") {

            HomePage(modifier,navController,authViewModel)



        }
    }

}