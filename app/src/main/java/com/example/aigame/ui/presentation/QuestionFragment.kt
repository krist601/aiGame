package com.example.aigame.ui.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.aigame.R
import com.example.aigame.data.entities.responses.QuestionResponse
import com.example.aigame.view_models.QuestionViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
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

        val randomColor by remember { mutableStateOf(generateRandomColor()) }

        Box(
            Modifier
                .fillMaxSize()
                .background(color = randomColor.first)
        ) {
            Image(
                painterResource(R.drawable.background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize())
            InnerView(randomColor)
        }

    }
    @Composable
    fun InnerView(randomColor: Pair<Color,Color>){
        val context = LocalContext.current
        MobileAds.initialize(context)

        val adView = remember {
            AdView(context).apply {
                adSize = AdSize.FLUID
                adUnitId = "ca-app-pub-3940256099942544/6300978111"//"ca-app-pub-8059056970711952/1746641337"
                loadAd(AdRequest.Builder().build())
            }
        }

        val imageUrl = "https://static.wikia.nocookie.net/clash-of-clans/images/c/c5/Dragón_info.png/revision/latest/scale-to-width-down/120?cb=20210819010118&path-prefix=es"
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
                    modifier = Modifier
                        .background(Color.Transparent)
                        .height(350.dp),
                ) {
                    val painter = rememberImagePainter(data = imageUrl)

                    Box(modifier = Modifier
                        .background(Color.Transparent)
                        .padding(end = 32.dp, start = 32.dp, top = 48.dp, bottom = 32.dp)) {
                        Image(
                            painterResource(R.drawable.image_background),
                            modifier = Modifier
                                .fillMaxSize()
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                    Text(
                        text = "Dragon",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    Text(
                        text = "Dragonastico",
                        color = Color.LightGray,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 56.dp, start = 24.dp)
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

    @Composable
    fun LoadCard(question: QuestionResponse, randomColor: Pair<Color,Color>){
        var visible by remember { mutableStateOf(0) }
        var rotated by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()

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
                .padding(top = 16.dp, end = 32.dp, start = 32.dp, bottom = 16.dp)
                .fillMaxSize()
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }
                /*.clickable {
                    visible = 0
                }*/
        )
        {
            if (visible == 0) {
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
                        shape = MaterialTheme.shapes.small,
                        colors = ButtonDefaults.buttonColors(randomColor.second),
                        onClick = {
                            visible = 1
                            rotated = !rotated
                        }) {
                        Text(text = "Continue")
                    }
                }
            }else if (visible == 1) {
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
                                .padding(top = 8.dp, bottom = 0.dp, end = 32.dp, start = 32.dp)
                                .clip(RoundedCornerShape(2)),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(randomColor.second),
                            onClick = {
                                visible = 2
                                //viewModel.getQuestion(option)
                                //randomColor = generateRandomColor()
                                scope.launch {
                                    delay(3000)
                                    visible = 0
                                    rotated = !rotated
                                }
                            }
                        ) {
                            Text(text = option)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }else if (visible == 2) {
                Card(
                    modifier = Modifier
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LottieAnimationExample()
                    }
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
    @Composable
    fun LottieAnimationExample() {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.animation2)
        )
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            restartOnPlay = false

        )

        LottieAnimation(
            modifier = Modifier.height(100.dp).width(100.dp),
            composition = composition,
            progress = progress,
        )
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
