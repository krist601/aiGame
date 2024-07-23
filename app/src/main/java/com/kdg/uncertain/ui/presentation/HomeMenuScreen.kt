package com.kdg.uncertain.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kdg.uncertain.R
import com.kdg.uncertain.ui.theme.buddyChampionFamily
import com.kdg.uncertain.view_models.HomeMenuViewModel

@Composable
fun HomeMenuScreen(
    onNewGameClicked: () -> Unit,
    onContinueGameClicked: () -> Unit
) {
    val viewModel: HomeMenuViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            text = "Uncertain",
            fontSize = 72.sp,
            fontFamily = buddyChampionFamily,
            textAlign = TextAlign.Justify
        )
        BlackSpacer(4.dp, 4.dp)
        BlackSpacer(4.dp, 4.dp)
        Image(
            painter = painterResource(id = R.drawable.detective_background_menu),
            contentDescription = "Background Image",
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            text = "In this game, you’ll step into the shoes of an astute detective, navigating through a series of thrilling and intricate cases. Each investigation will challenge your wit, observation skills, and deductive reasoning as you delve into the hidden secrets and unravel the truth.",
            fontFamily = buddyChampionFamily,
            textAlign = TextAlign.Justify
        )
        BlackSpacer(2.dp, 4.dp)
        MenuOptions(viewModel, onNewGameClicked, onContinueGameClicked)
    }
}

@Composable
fun BlackSpacer(height: Dp, paddingTop: Dp){
    Box(
        modifier = Modifier
            .padding(top = paddingTop, start = 16.dp, end = 16.dp)
            .height(height)
            .fillMaxWidth()
            .background(Color.Black)
    )
}

@Composable
fun MenuOptions(
    viewModel: HomeMenuViewModel,
    onNewGameClicked: () -> Unit,
    onContinueGameClicked: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            onClick = onNewGameClicked,
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text("New Game", fontSize = 16.sp, fontFamily = buddyChampionFamily)
        }
        if (viewModel.hasSavedGame()) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onClick = onContinueGameClicked,
                shape = RoundedCornerShape(50),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text("Continue", fontSize = 16.sp, fontFamily = buddyChampionFamily)
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