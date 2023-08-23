package com.example.aigame.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aigame.R
import com.example.aigame.ui.theme.accent
import com.example.aigame.ui.theme.buddyChampionFamily
import com.example.aigame.ui.theme.grayCard

class HomeMenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    MainMenu(
                        onNewGameClicked = {},
                        onContinueGameClicked = {},
                        onLoginClicked = {},
                        onSignUpClicked = {}
                    )
                }
            }
        }
    }

    @Composable
    fun MainMenu(onNewGameClicked: () -> Unit, onContinueGameClicked: () -> Unit, onLoginClicked: () -> Unit, onSignUpClicked: () -> Unit) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.detective_menu_background_two),
                contentDescription = "desc",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(top = 30.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    fontSize = 60.sp,
                    fontFamily = buddyChampionFamily,
                    text = "Uncertain",
                    textAlign = TextAlign.Center, // Center the text horizontally
                    color = Color.White // Change the text color as needed
                )
            }
            Column(modifier = Modifier
                .padding(16.dp)
                .height(360.dp)
                .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp), // Margin of 30dp
                    //colors = CardDefaults.cardColors(
                    //    containerColor = grayCard
                    //),
                ) {
                    // Content of your card goes here
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp),
                    ) {
                        // Add your card's content inside this Box

                        Button(
                            modifier = Modifier.fillMaxWidth().padding(end = 20.dp, start = 20.dp),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(accent),
                            onClick = {

                                val fragment = QuestionFragment()
                                requireFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentContainer, fragment)
                                    .addToBackStack(null)
                                    .commit()
                            }) {
                            Text(
                                fontSize = 24.sp,
                                fontFamily = buddyChampionFamily,
                                text = "New Game"
                            )
                        }

                        Button(
                            modifier = Modifier.fillMaxWidth().padding(end = 20.dp, start = 20.dp, top = 8.dp),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(accent),
                            onClick = {

                                val fragment = QuestionFragment()
                                requireFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentContainer, fragment)
                                    .addToBackStack(null)
                                    .commit()
                            }) {
                            Text(
                                fontSize = 24.sp,
                                fontFamily = buddyChampionFamily,
                                text = "Continue"
                            )
                        }

                        Button(
                            modifier = Modifier.fillMaxWidth().padding(end = 20.dp, start = 20.dp, top = 8.dp),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(accent),
                            onClick = {

                                val fragment = QuestionFragment()
                                requireFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentContainer, fragment)
                                    .addToBackStack(null)
                                    .commit()
                            }) {
                            Text(
                                fontSize = 24.sp,
                                fontFamily = buddyChampionFamily,
                                text = "Options"
                            )
                        }

                        Button(
                            modifier = Modifier.fillMaxWidth().padding(end = 20.dp, start = 20.dp, top = 8.dp),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(accent),
                            onClick = {

                                val fragment = QuestionFragment()
                                requireFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentContainer, fragment)
                                    .addToBackStack(null)
                                    .commit()
                            }) {
                            Text(
                                fontSize = 24.sp,
                                fontFamily = buddyChampionFamily,
                                text = "Credit"
                            )
                        }
                    }
                }
                /*Button(onClick = onContinueGameClicked) {
                    Text("Continue Game")
                }
                Button(onClick = onLoginClicked) {
                    Text("Log In")
                }
                Button(onClick = onSignUpClicked) {
                    Text("Sign Up")
                }*/
            }

        }
    }

    @Preview
    @Composable
    fun PreviewMainMenu() {
        MainMenu(
            onNewGameClicked = {},
            onContinueGameClicked = {},
            onLoginClicked = {},
            onSignUpClicked = {}
        )
    }
}