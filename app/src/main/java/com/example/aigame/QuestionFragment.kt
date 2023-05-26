package com.example.aigame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.aigame.view_models.QuestionViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

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

        // Initialize AdMob
        val context = LocalContext.current
        MobileAds.initialize(context)

        // Load the banner ad
        val adView = remember {
            AdView(context).apply {
                adSize = AdSize.BANNER
                adUnitId = "ca-app-pub-8059056970711952/1746641337"
                loadAd(AdRequest.Builder().build())
            }
        }
        Column {
            Column(
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                val question by viewModel.question.collectAsState()
                val options by viewModel.options.collectAsState()

                Text(text = question, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                options.forEach { option ->
                    Button(onClick = { /* Handle option selection */ }) {
                        Text(text = option)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .height(50.dp)) {
                // Your existing UI elements

                // Add the AdMob banner ad
                AndroidView(factory = { adView })
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Collect the flows
        lifecycleScope.launchWhenStarted {
            viewModel.question.collect {
                // Update UI with new question
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.options.collect {
                // Update UI with new options
            }
        }
        viewModel.fetchQuestion()
    }
    @Preview
    @Composable
    fun Preview(){
        View()
    }
}
