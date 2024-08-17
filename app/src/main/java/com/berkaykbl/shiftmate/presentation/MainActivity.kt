package com.berkaykbl.shiftmate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.berkaykbl.shiftmate.presentation.home.HomeScreen
import com.berkaykbl.shiftmate.presentation.menu.MenuScreen
import com.berkaykbl.shiftmate.presentation.menu.subscreen.MultiplierScreen
import com.berkaykbl.shiftmate.presentation.menu.subscreen.SalaryScreen
import com.berkaykbl.shiftmate.presentation.ui.theme.ShiftMateTheme
import com.berkaykbl.shiftmate.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShiftMateTheme {
                Surface {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screens.MultiplierMenu.route) {
                        composable(Screens.Home.route) {
                            HomeScreen(navController)
                        }
                        composable(Screens.Menu.route) {
                            MenuScreen(navController)
                        }
                        composable(Screens.SalaryMenu.route) {
                            SalaryScreen(navController)
                        }
                        composable(Screens.MultiplierMenu.route) {
                            MultiplierScreen(navController)
                        }
                    }
                }
            }
        }
    }
}