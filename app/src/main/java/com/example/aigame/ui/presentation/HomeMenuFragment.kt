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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aigame.R

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
                painter = painterResource(id = R.drawable.detective_menu_background),
                contentDescription = "desc",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier
                .padding(16.dp)
                .height(360.dp)
                .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    onClick = {
    
                    val fragment = QuestionFragment()
                    requireFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit()
                }) {
                    Text("New Game")
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