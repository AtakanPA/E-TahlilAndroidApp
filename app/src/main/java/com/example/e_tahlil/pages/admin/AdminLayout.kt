package com.example.e_tahlil.pages.admin

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.e_tahlil.AuthViewModel
import com.example.e_tahlil.MyAppNavigation


@SuppressLint("RestrictedApi")
@Composable
fun AdminLayout(modifier: Modifier, authViewModel: AuthViewModel){
   val navController=rememberNavController()
    Scaffold (topBar = {




    }, content = {
        paddingValues ->
        MyAppNavigation(modifier=Modifier.padding(paddingValues),authViewModel=authViewModel,navController)


    }, bottomBar = {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val topLevelRoutes = listOf(
                NavigationItem.Home,NavigationItem.Profile,NavigationItem.Settings

            )
            topLevelRoutes.forEach { topLevelRoute ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = topLevelRoute.icon, contentDescription = topLevelRoute.label) },
                    label = { Text(topLevelRoute.label) },
                    selected = currentDestination?.hierarchy?.any { it.route == topLevelRoute.route } == true,
                    onClick = {
                        navController.navigate(topLevelRoute.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    })
}


sealed class NavigationItem(val route: String, val label: String, val icon:ImageVector) {
    object Home : NavigationItem("adminhome", "Home",  Icons.Default.Home )
    object Profile : NavigationItem("adminprofile", "Profile", Icons.Default.Person )
    object Settings : NavigationItem("adminsettings", "Settings",  Icons.Default.Settings )
}