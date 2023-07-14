package com.example.aigame.ui.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.example.aigame.R
import com.example.aigame.data.entities.responses.QuestionResponse
import com.example.aigame.view_models.QuestionViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class QuestionFragment : Fragment() {
    private val viewModel: QuestionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    View()
                }
            }
        }
    }

    @Composable
    fun View(){

        var randomColor by remember { mutableStateOf(generateRandomColor()) }


        Box(
            Modifier.fillMaxSize().background(color = randomColor.first)
        ) {
            Image(
                painterResource(R.drawable.background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize())
            innerView(randomColor)
        }

    }
    @Composable
    fun innerView(randomColor: Pair<Color,Color>){
        val context = LocalContext.current
        MobileAds.initialize(context)

        val adView = remember {
            AdView(context).apply {
                adSize = AdSize.FLUID
                adUnitId = "ca-app-pub-3940256099942544/6300978111"//"ca-app-pub-8059056970711952/1746641337"
                loadAd(AdRequest.Builder().build())
            }
        }

        val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/The_Legend_of_Zelda-_Tri_Force_Heroes_Logo.png/1600px-The_Legend_of_Zelda-_Tri_Force_Heroes_Logo.png"
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                val question by viewModel.question.collectAsState()
                    Box(
                        modifier = Modifier.background(Color.Transparent).height(350.dp),
                    ) {
                        val painter = rememberImagePainter(data = imageUrl)

                        Box(modifier = Modifier.background(Color.Transparent).padding(end = 32.dp, start = 32.dp, top = 48.dp, bottom = 32.dp)) {
                            Image(
                                painterResource(R.drawable.image_background),
                                modifier = Modifier.fillMaxSize()
                                    .background(Color.Transparent)
                                    .clip(RoundedCornerShape(8)),
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painter,
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxWidth()
                                    .height(250.dp)
                                    .align(Alignment.BottomCenter)
                            )
                        }
                        Text(
                            text = "Your Text",
                            color = Color.White,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                }

                //LoadCard()
                LoadCard(question, randomColor)

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .height(50.dp)
            ) {
                AndroidView(factory = { adView }
                )
            }
        }
    }
    /*@Composable
    fun FlipCard() {

        var rotated by remember { mutableStateOf(false) }

        val rotation by animateFloatAsState(
            targetValue = if (rotated) 180f else 0f,
            animationSpec = tween(500)
        )

        val animateFront by animateFloatAsState(
            targetValue = if (!rotated) 1f else 0f,
            animationSpec = tween(500)
        )

        val animateBack by animateFloatAsState(
            targetValue = if (rotated) 1f else 0f,
            animationSpec = tween(500)
        )

        val animateColor by animateColorAsState(
            targetValue = if (rotated) Color.Red else Color.Blue,
            animationSpec = tween(500)
        )

        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                Modifier
                    .fillMaxSize(.5f)
                    .graphicsLayer {
                        rotationY = rotation
                        cameraDistance = 8 * density
                    }
                    .clickable {
                        rotated = !rotated
                    },
                backgroundColor = animateColor
            )
            {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(text = if (rotated) "Back" else "Front",
                         modifier = Modifier
                        .graphicsLayer {
                            alpha = if (rotated) animateBack else animateFront
                            rotationY = rotation
                        })
                }

            }
        }
    }*/

    @Composable
    fun LoadCard(question: QuestionResponse, randomColor: Pair<Color,Color>){
        var visible by remember { mutableStateOf(false) }
        var rotated by remember { mutableStateOf(false) }

        val rotation by animateFloatAsState(
            targetValue = if (rotated) 180f else 0f,
            animationSpec = tween(500)
        )
        val animateFront by animateFloatAsState(
            targetValue = if (!rotated) 1f else 0f,
            animationSpec = tween(500)
        )

        val animateBack by animateFloatAsState(
            targetValue = if (rotated) 1f else 0f,
            animationSpec = tween(500)
        )
        Card(
            Modifier
                .padding(top = 16.dp)
                .fillMaxSize()
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }
                .clickable {
                    rotated = !rotated
                    visible = !visible
                }
        )
        {
            if (!visible) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = if (rotated) animateBack else animateFront
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp, end = 32.dp, start = 32.dp),
                        text = question.question,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Button(
                        modifier = Modifier
                            .padding(top = 0.dp, bottom = 16.dp, end = 32.dp, start = 32.dp)
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        colors = ButtonDefaults.buttonColors(randomColor.second),
                        onClick = {
                            visible = true
                            flipCard()
                        }) {
                        Text(text = "Continue")
                    }
                }
            }
            if (visible) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = if (rotated) animateBack else animateFront
                            rotationY = 180f
                        }

                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 8.dp, end = 32.dp, start = 32.dp),
                        text = "cual de las deciciones te decides por decidir?",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    question.options?.forEach { option ->
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 0.dp, end = 32.dp, start = 32.dp),
                            colors = ButtonDefaults.buttonColors(randomColor.second),
                            onClick = {
                                viewModel.getQuestion(option)
                                //randomColor = generateRandomColor()
                                visible = false
                            }) {
                            Text(text = option)

                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
    private fun generateRandomColor(): Pair<Color, Color> {
        val random = Random.Default
        val firstRandom = random.nextInt(2)
        val red = if (firstRandom == 0) 0 else random.nextInt(256)
        val green = if (firstRandom == 1) 0 else random.nextInt(256)
        val blue = if (firstRandom == 2) 0 else random.nextInt(256)
        val redLight = if (firstRandom == 0) 0 else if (red > 236) 256 else red + 20
        val greenLight = if (firstRandom == 1) 0 else if (green > 236) 256 else green + 20
        val blueLight = if (firstRandom == 2) 0 else if (blue > 236) 256 else blue + 20
        return Pair(Color(red, green, blue), Color(redLight, greenLight, blueLight))
    }

    fun flipCard(){

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.gameData.collect {
                viewModel.startLevel()
            }
        }
        viewModel.getGameStorage()
    }
    @Preview
    @Composable
    fun Preview(){
        View()
    }
}
