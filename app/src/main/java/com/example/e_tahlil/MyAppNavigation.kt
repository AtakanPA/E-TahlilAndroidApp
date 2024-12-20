package com.example.e_tahlil

import KilavuzEklePage
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
import com.example.e_tahlil.pages.admin.DegerAraPage
import com.example.e_tahlil.pages.admin.HastaAraPage
import com.example.e_tahlil.pages.admin.HastaDetayPage



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

          AdminHomePage( modifier,navController,authViewModel,adminViewModel)


        }
        composable("hastaara") {

            HastaAraPage( modifier = modifier, navController = navController, authViewModel = authViewModel, adminViewModel = adminViewModel)


        }
        composable("hastadetay/{dogumtarihi}")
        {backStackEntry->
            val dogumTarihi=backStackEntry.arguments?.getString("dogumtarihi")

            HastaDetayPage(navController, authViewModel, adminViewModel, modifier = modifier,dogumTarihi)

        }

        composable("kilavuzekle"){

                KilavuzEklePage(modifier,navController,authViewModel,adminViewModel)


        }
        composable("degerara/{dogumTarihi}"){
                backStackEntry->
            val dogumTarihi=backStackEntry.arguments?.getString("dogumtarihi")
            DegerAraPage(modifier,navController,authViewModel,adminViewModel,dogumTarihi)


        }
    }





}