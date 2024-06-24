package com.example.aigame.ui.presentation

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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.aigame.R
import com.example.aigame.domain.entities.Option
import com.example.aigame.ui.theme.buddyChampionFamily
import com.example.aigame.ui.theme.getNativePaint
import com.example.aigame.view_models.QuestionViewModel
import com.example.aigame.view_models.ViewStates
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

@Composable
fun QuestionScreen(isNewGame: Boolean) {
    val viewModel: QuestionViewModel = hiltViewModel()
    val context = LocalContext.current

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
        InnerView(viewModel)
    }
}

@Composable
fun InnerView(viewModel: QuestionViewModel) {
    var imageState by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    MobileAds.initialize(context)

    val chapter by viewModel.chapterData.collectAsState()
    val option by viewModel.optionData.collectAsState()

    val imageUrl by remember { mutableStateOf(chapter.interfaceResources?.image ?: "") }
    val adView = remember {
        AdView(context).apply {
            setAdSize(AdSize.FLUID)
            adUnitId = "ca-app-pub-3940256099942544/6300978111"
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
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context).data(data = imageUrl)
                        .apply {
                            listener(object : ImageRequest.Listener {
                                override fun onCancel(request: ImageRequest) {
                                    imageState = 400
                                }

                                override fun onError(
                                    request: ImageRequest,
                                    result: ErrorResult
                                ) {
                                    imageState = 400
                                }

                                override fun onStart(request: ImageRequest) {
                                    imageState = 0
                                }

                                override fun onSuccess(
                                    request: ImageRequest,
                                    result: SuccessResult
                                ) {
                                    imageState = 200
                                }
                            })
                            crossfade(true)
                        }.build()
                )
                when (imageState) {
                    0 -> {
                        Image(
                            painterResource(R.drawable.image_background),
                            contentDescription = "desc",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                    200 -> {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                    else -> {
                        Image(
                            painterResource(R.drawable.detective_background),
                            contentDescription = "desc",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                }

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
            LoadCard(option)
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
fun LoadCard(option: Option) {
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
        val viewState by viewModel.viewStateFlow.collectAsState()

        when (viewState) {
            ViewStates.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LottieAnimationExample()
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
                        }, // Margin of 30dp
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()) // ScrollView agregado aquí
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
                            onClick = { /*viewModel.popBackStack()*/ }
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
        }
    }
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
