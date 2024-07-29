package com.kdg.uncertain.ui.presentation

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kdg.uncertain.R
import com.kdg.uncertain.domain.entities.Option
import com.kdg.uncertain.ui.theme.buddyChampionFamily
import com.kdg.uncertain.ui.theme.getNativePaint
import com.kdg.uncertain.view_models.QuestionViewModel
import com.kdg.uncertain.view_models.ViewStates
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

@Composable
fun QuestionScreen(navController: NavController, isNewGame: Boolean) {
    val viewModel: QuestionViewModel = hiltViewModel()
    val context = LocalContext.current
    val viewState by viewModel.viewStateFlow.collectAsState()

    MobileAds.initialize(context)

    LaunchedEffect(isNewGame) {
        if (isNewGame) {
            viewModel.getChapter("CH3S1")
        } else {
            viewModel.getSavedGame()
        }
    }
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.detective_background_blur_bw),
            contentDescription = "desc",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        if (viewState != ViewStates.ConnectionError) {
                InnerView(viewModel, navController, viewState)
        }else{
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center,
            ) {
                LottieAnimationError()

            }
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    modifier = Modifier
                        .padding(bottom = 40.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    onClick = { navController.popBackStack() }
                ) {
                    Text("Back", fontSize = 24.sp, fontFamily = buddyChampionFamily)
                }
            }
        }
    }
}
@Composable
fun InnerView(viewModel: QuestionViewModel, navController: NavController, viewState: ViewStates) {
    var imageState by remember { mutableStateOf(0) }
    val context = LocalContext.current
    MobileAds.initialize(context)

    val chapter by viewModel.chapterData.collectAsState()
    val option by viewModel.optionData.collectAsState()

    val imageUrl by remember { mutableStateOf(chapter.interfaceResources?.image ?: "") }
    val adView = remember {
        AdView(context).apply {
            setAdSize(AdSize.FLUID)
            adUnitId = this.context.getString(R.string.ad_unit_id)
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
                    .height(250.dp),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .listener(
                            onSuccess = { request, metadata ->
                                // Handle success, if needed
                            },
                            onError = { request, throwable ->
                                // Handle the error
                                Log.e("ImageLoadError", "Error loading image", throwable.throwable)
                            }
                        )
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.image_background)
                )

                Canvas(
                    modifier = Modifier.padding(16.dp),
                    onDraw = {
                        val titleStrokePaint = getNativePaint(context, 84f, true)
                        val titleFillPaint = getNativePaint(context, 84f, false)
                        val subtitleStrokePaint = getNativePaint(context, 64f, true)
                        val subtitleFillPaint = getNativePaint(context, 64f, false)
                        drawIntoCanvas {
                            it.nativeCanvas.drawText(
                                chapter.interfaceResources?.title.orEmpty(),
                                15f, 40.dp.toPx(), titleStrokePaint
                            )
                            it.nativeCanvas.drawText(
                                chapter.interfaceResources?.title.orEmpty(),
                                15f, 40.dp.toPx(), titleFillPaint
                            )
                            it.nativeCanvas.drawText(
                                chapter.interfaceResources?.subtitle.orEmpty(),
                                15f, 60.dp.toPx(), subtitleStrokePaint
                            )
                            it.nativeCanvas.drawText(
                                chapter.interfaceResources?.subtitle.orEmpty(),
                                15f, 60.dp.toPx(), subtitleFillPaint
                            )
                        }
                    }
                )
            }
            LoadCard(navController, viewState, option)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .height(50.dp)
        ) {
            AndroidView(factory = { adView })
        }
    }
}


@Composable
fun LoadCard(navController: NavController, viewState: ViewStates, option: Option) {
    val viewModel: QuestionViewModel = hiltViewModel()
    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500),
        label = ""
    )
    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500), label = ""
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500), label = ""
    )

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            },
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        when (viewState) {
            ViewStates.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LottieAnimationLoading()
                }
            }
            ViewStates.Questions -> {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = if (rotated) animateBack else animateFront
                        }, // Margin of 30dp
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = option.text ?: "",
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = buddyChampionFamily
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            onClick = {
                                rotated = !rotated
                                viewModel.getAnswerState()
                            }
                        ) {
                            Text("Continue", fontSize = 16.sp, fontFamily = buddyChampionFamily)
                        }
                    }
                }
            }
            ViewStates.Answers -> {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = if (rotated) animateBack else animateFront
                            rotationY = 180f
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        option.question?.let{
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text =  it,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = buddyChampionFamily
                            )
                        }
                        option.options?.forEach { leOption ->
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                onClick = {
                                    rotated = !rotated
                                    viewModel.setNewQuestion(leOption)
                                }
                            ) {
                                Text(
                                    leOption.option ?: "",
                                    fontSize = 16.sp,
                                    fontFamily = buddyChampionFamily
                                )
                            }
                        }
                    }
                }

            }
            ViewStates.DeadEnd -> {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = if (rotated) animateBack else animateFront
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = option.text ?: "",
                            fontSize = 24.sp,
                            fontFamily = buddyChampionFamily
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            onClick = { navController.popBackStack() }
                        ) {
                            Text("Back", fontSize = 24.sp, fontFamily = buddyChampionFamily)
                        }
                    }
                }
            }
            ViewStates.NextChapter -> {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = if (rotated) animateBack else animateFront
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = option.text ?: "",
                            fontSize = 24.sp,
                            fontFamily = buddyChampionFamily
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            onClick = {
                                option.next_chapter_id?.let { viewModel.getChapter(it) }
                            }
                        ) {
                            Text("Next chapter", fontSize = 24.sp, fontFamily = buddyChampionFamily)
                        }
                    }
                }
            }

            ViewStates.ConnectionError -> {}
        }
    }
}

@Composable
fun LottieAnimationLoading() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.fingerprint_animation)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        restartOnPlay = false
    )

    LottieAnimation(
        modifier = Modifier
            .height(300.dp)
            .width(300.dp),
        composition = composition,
        progress = progress
    )
}

@Composable
fun LottieAnimationError() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.connection_error)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        restartOnPlay = false
    )

    LottieAnimation(
        modifier = Modifier
            .height(300.dp)
            .width(300.dp),
        composition = composition,
        progress = progress
    )
}

/*
private fun getNativePaint(context: Context, textSize: Float, isStroke: Boolean): Paint {
    val paint = Paint()
    paint.textSize = textSize
    paint.color = if (isStroke) Color.BLACK else Color.WHITE
    paint.style = if (isStroke) Paint.Style.STROKE else Paint.Style.FILL
    paint.strokeWidth = if (isStroke) 4f else 0f
    return paint
}
*/
