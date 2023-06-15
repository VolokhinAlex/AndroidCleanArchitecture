package com.volokhinaleksey.androidcleanarchitecture

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.ui.theme.AndroidCleanArchitectureTheme
import com.volokhinaleksey.androidcleanarchitecture.viewmodels.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.getLaunchCount()
        setContent {
            AndroidCleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainViewModel.data.observeAsState().value?.let {
                        Log.e("launchCount", it.launchCount.toString())
                        LaunchedEffect(key1 = true) {
                            mainViewModel.isShowingEvaluationWindow(it.launchCount)
                        }
                        if (savedInstanceState == null) {
                            LaunchedEffect(key1 = true) {
                                val count = it.launchCount + 1
                                mainViewModel.saveLaunchCount(count = DataLaunchCount(count))
                            }
                        }
                    }

                    mainViewModel.isShowingEvaluationWindow.observeAsState().value?.let {
                        if (it) {
                            Greeting(text = "You need to display the application evaluation window")
                        } else {
                            Greeting(text = "You don't need to show the evaluation window")
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun Greeting(text: String) {
    Text(
        text = text,
        modifier = Modifier
    )
}