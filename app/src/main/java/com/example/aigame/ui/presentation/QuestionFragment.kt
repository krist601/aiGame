package com.example.aigame.ui.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.aigame.R
import com.example.aigame.domain.entities.Option
import com.example.aigame.ui.theme.accent
import com.example.aigame.ui.theme.buddyChampionFamily
import com.example.aigame.view_models.QuestionViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class QuestionFragment() : Fragment() {
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

        //val randomColor by remember { mutableStateOf(generateRandomColor()) }

        Box(
            Modifier
                .fillMaxSize()
                //.background(color = randomColor.first)
        ) {
            Image(
                painterResource(R.drawable.detective_background_blur_bw),
                contentDescription = "desc",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize())
            InnerView(Pair(accent, accent))
        }

    }
    @Composable
    fun InnerView(randomColor: Pair<Color,Color>){
        val context = LocalContext.current
        MobileAds.initialize(context)

        val chapter by viewModel.chapterData.collectAsState()
        val option by viewModel.optionData.collectAsState()

        val imageUrl = chapter.interfaceResources?.image ?: ""
        val adView = remember {
            AdView(context).apply {
                adSize = AdSize.FLUID
                adUnitId = "ca-app-pub-3940256099942544/6300978111"//"ca-app-pub-8059056970711952/1746641337"
                loadAd(AdRequest.Builder().build())
            }
        }
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
                            painterResource(R.drawable.card),
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
                        text = chapter.interfaceResources?.title.orEmpty(),
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp),
                        fontFamily = buddyChampionFamily,
                    )
                    Text(
                        text = chapter.interfaceResources?.subtitle.orEmpty(),
                        color = Color.LightGray,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = buddyChampionFamily,
                        modifier = Modifier
                            .padding(top = 56.dp, start = 24.dp)
                    )
                }
                LoadCard(option, randomColor)
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
    fun LoadCard(option: Option, randomColor: Pair<Color,Color>){
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
            if (option.options.isNullOrEmpty() && option.nextCanonicalEventId == null) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = if (rotated) animateBack else animateFront
                        }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(bottom = 16.dp, end = 32.dp, start = 32.dp),
                            text = option.text ?: "",
                            fontFamily = buddyChampionFamily,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            modifier = Modifier
                                .padding(top = 0.dp, bottom = 16.dp, end = 32.dp, start = 32.dp)
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(randomColor.second),
                            onClick = {
                                backButtonPress()
                            }) {
                            Text(
                                fontFamily = buddyChampionFamily,text = "Back")
                        }
                    }
                }
            }else if (visible == 0) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = if (rotated) animateBack else animateFront
                        }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(bottom = 16.dp, end = 32.dp, start = 32.dp),
                            text = option.text ?: "",
                            fontFamily = buddyChampionFamily,
                            //style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            modifier = Modifier
                                .padding(top = 0.dp, bottom = 16.dp, end = 32.dp, start = 32.dp)
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(randomColor.second),
                            onClick = {
                                visible = 1
                                rotated = !rotated
                            }) {
                            Text(
                                fontFamily = buddyChampionFamily,text = "Continue")
                        }
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
                        text = option.question ?: "",
                        fontFamily = buddyChampionFamily,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    option.options?.forEach { leOption ->
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 0.dp, end = 32.dp, start = 32.dp)
                                .clip(RoundedCornerShape(2)),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(randomColor.second),
                            onClick = {
                                visible = 2
                                scope.launch {
                                    delay(3000)
                                    visible = 0
                                    rotated = !rotated
                                    viewModel.setNewQuestion(leOption)
                                }
                            }
                        ) {
                            Text(
                                fontFamily = buddyChampionFamily,text = leOption.option ?: "")
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
    fun backButtonPress(){
        requireFragmentManager().popBackStack()
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
            spec = LottieCompositionSpec.RawRes(R.raw.fingerprint_animation)
        )
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            restartOnPlay = false

        )

        LottieAnimation(
            modifier = Modifier.height(300.dp).width(300.dp),
            composition = composition,
            progress = progress,
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getChapter("CH3S1")
    }
    @Preview
    @Composable
    fun Preview(){
        View()
    }
}
