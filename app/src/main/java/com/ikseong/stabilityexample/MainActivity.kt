package com.ikseong.stabilityexample

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.ikseong.stabilityexample.ui.theme.StabilityExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StabilityExampleTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val tabTitles = listOf(
        "Class (Class/Data)", "EqualOverride", "Immutable (Class/Data)", "Stable (Class/Data)",
        "MultiModule", "StateClass", "UiState", "UiTest"
    )
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    Scaffold(
        topBar = {
            ScrollableTabRow(
                modifier = Modifier.statusBarsPadding(),
                selectedTabIndex = selectedTabIndex
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTabIndex) {
                0 -> {
                    if (isPortrait) {
                        Row {
                            ClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                            DataClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                        }
                    } else {
                        Column {
                            ClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                            DataClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                        }
                    }
                }
                1 -> EqualOverrideClassTestScreen()
                2 -> {
                    if (isPortrait) {
                        Row {
                            ImmutableClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                            ImmutableDataClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                        }
                    } else {
                        Column {
                            ImmutableClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                            ImmutableDataClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                        }
                    }
                }
                3 -> {
                    if (isPortrait) {
                        Row {
                            StableClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                            StableDataClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                        }
                    } else {
                        Column {
                            StableClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                            StableDataClassTestScreen(modifier = Modifier.weight(1f), isPortrait = isPortrait)
                        }
                    }
                }
                4 -> MultiModuleScreen()
                5 -> StateClassTestScreen()
                6 -> UiStateScreen()
                7 -> UiTestScreen()
            }
        }
    }
}

