package com.ikseong.stabilityexample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StateClassTestScreen(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {

        Button(
            onClick = { count++ }, // count 를 증가 시켜 recomposition 발생
        ) {
            Text("count: $count")
        }

        val testClassWithState by remember { mutableStateOf(TestClassWithState("TestClassWithState Text")) }
        val unstableTestClassWithState by remember { mutableStateOf(UnstableTestClassWithState("UnstableTestClassWithState Text")) }
        val stableTestClassWithState by remember { mutableStateOf(StableTestClassWithState("StableTestClassWithState Text")) }

        Button(
            onClick = {
                count++
                testClassWithState.state = " TestClassWithState Text"
                unstableTestClassWithState.state = " TestClassWithState Text"
                stableTestClassWithState.state = " TestClassWithState Text"
            }, // count 를 증가 시켜 recomposition 발생
        ) {
            Text("count: $count")
        }

        TestClassWithStateContent(testClassWithState)
        TestClassWithStateContent(TestClassWithState("New TestClassWithState Text"))
        TestClassWithStateContent(TestClassWithState("$count TestClassWithState Text"))

        HorizontalDivider()

        UnstableTestClassWithStateContent(unstableTestClassWithState)
        UnstableTestClassWithStateContent(UnstableTestClassWithState("New UnstableTestClassWithState Text"))
        UnstableTestClassWithStateContent(UnstableTestClassWithState("$count TestClassWithState Text"))

        HorizontalDivider()

        StableTestClassWithStateContent(stableTestClassWithState)
        StableTestClassWithStateContent(StableTestClassWithState("New StableTestClassWithState Text"))
        StableTestClassWithStateContent(StableTestClassWithState("$count TestClassWithState Text"))

    }
}

data class TestClassWithState(
    val text: String,
) {
    var state by mutableStateOf(text)
}

data class UnstableTestClassWithState(
    var text: String,
) {
    var state by mutableStateOf(text)
}

@Stable
data class StableTestClassWithState(
    var text: String,
) {
    var state by mutableStateOf(text)
}

@Composable
fun TestClassWithStateContent(content: TestClassWithState) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.state)
    }
}

@Composable
fun UnstableTestClassWithStateContent(content: UnstableTestClassWithState) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.state)
    }
}

@Composable
fun StableTestClassWithStateContent(content: StableTestClassWithState) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.state)
    }
}