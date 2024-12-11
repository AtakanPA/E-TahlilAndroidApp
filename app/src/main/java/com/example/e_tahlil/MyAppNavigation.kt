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
import com.example.e_tahlil.pages.admin.AdminHomePage
import com.example.e_tahlil.pages.admin.AdminLayout
import com.example.e_tahlil.pages.admin.HastaAraPage
import com.example.e_tahlil.pages.admin.HastaDetayPage
import com.example.e_tahlil.pages.admin.TahlilEklePage

@Composable
fun MyAppNavigation(modifier:Modifier=Modifier,authViewModel: AuthViewModel,navController: NavHostController,adminViewModel: AdminViewModel){

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
        composable("adminhome") {

          AdminHomePage( modifier,navController,authViewModel)


        }
        composable("hastaara") {

            HastaAraPage( modifier = modifier, navController = navController, authViewModel = authViewModel, adminViewModel = adminViewModel)


        }
        composable("hastadetay")
        {
            HastaDetayPage(navController, authViewModel, adminViewModel, modifier = modifier)

        }
        composable("tahlilekle") {
            TahlilEklePage(modifier,navController,authViewModel,adminViewModel)

        }
    }





}