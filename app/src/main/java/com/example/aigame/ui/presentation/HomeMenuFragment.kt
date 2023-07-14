package com.example.aigame.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
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
        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = {

                val fragment = QuestionFragment()
                requireFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
            }) {
                Text("New Game")
            }
            Button(onClick = onContinueGameClicked) {
                Text("Continue Game")
            }
            Button(onClick = onLoginClicked) {
                Text("Log In")
            }
            Button(onClick = onSignUpClicked) {
                Text("Sign Up")
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