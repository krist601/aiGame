package com.example.aigame.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aigame.R
import com.example.aigame.ui.theme.buddyChampionFamily
import com.example.aigame.view_models.HomeMenuViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@Inject lateinit var viewModel: HomeMenuViewModel

@Composable
fun HomeMenuScreen(
    onNewGameClicked: () -> Unit,
    onContinueGameClicked: () -> Unit
) {
    val viewModel: HomeMenuViewModel = hiltViewModel()
    Box {
        Image(
            painter = painterResource(id = R.drawable.detective_menu_background_two),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        TitleCanvas()
        MenuOptions(viewModel, onNewGameClicked, onContinueGameClicked)
    }
}

@Composable
fun TitleCanvas() {
    // Your Canvas code for the title
}

@Composable
fun MenuOptions(
    viewModel: HomeMenuViewModel,
    onNewGameClicked: () -> Unit,
    onContinueGameClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .height(360.dp)
            //.align(Alignment.BottomCenter)
        ,
        verticalArrangement = Arrangement.Bottom
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(30.dp)) {
                Button(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    onClick = onNewGameClicked
                ) {
                    Text("New Game", fontSize = 16.sp, fontFamily = buddyChampionFamily)
                }
                if (viewModel.hasSavedGame()) {
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        onClick = onContinueGameClicked
                    ) {
                        Text("Continue", fontSize = 16.sp, fontFamily = buddyChampionFamily)
                    }
                }
                // Additional buttons can be added here
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeMenu() {
    HomeMenuScreen(
        onNewGameClicked = {},
        onContinueGameClicked = {}
    )
}