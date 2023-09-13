package com.example.aigame.ui.presentation

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aigame.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var fragmentContainer: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        //val splashScreen = SplashScreen.installSplashScreen()

        setContent {
            afterScreen(savedInstanceState)
            /*SplashScreen()
            val handler = remember { Handler() }
            handler.postDelayed(
                {
                    setContent {
                        afterScreen(savedInstanceState)
                    }
                },
                2000 // Adjust this delay as needed (in milliseconds)
            )*/
        }
    }
    @Composable
    fun afterScreen(savedInstanceState: Bundle?){
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragmentContainer)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeMenuFragment())
                .commit()
        }
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    /*@Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "homeMenu") {
            composable("homeMenu") { HomeMenuFragment() }
            composable("question") { QuestionFragment() }
            // Add more composable destinations here as needed
        }
    }*/
}