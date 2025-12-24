package com.ikseong.stabilityexample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EqualOverrideClassTestScreen(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {

        Button(
            onClick = { count++ }, // count 를 증가 시켜 recomposition 발생
        ) {
            Text("count: $count")
        }

        val unstableEqualTestClass by remember { mutableStateOf(UnstableEqualTestClass("UnstableEqualTestClass Text")) }
        val stableEqualTestClass by remember { mutableStateOf(StableEqualTestClass("StableEqualTestClass Text")) }

        HorizontalDivider()
        TestEqualClassContent(unstableEqualTestClass)
        val value = UnstableEqualTestClass("UnstableEqualTestClass Text")
        println(value.equals(unstableEqualTestClass))
        TestEqualClassContent(value)
        TestEqualClassContent(UnstableEqualTestClass("$count TestClass Text"))
        TestEqualClassContent(stableEqualTestClass)
        // Object.equals() 비교
        val value2 = StableEqualTestClass("StableEqualTestClass Text")
        println(value2.equals(stableEqualTestClass))
        TestEqualClassContent(value2)
        TestEqualClassContent(StableEqualTestClass("$count TestClass Text"))

        val unstableNonEqualTestClass by remember { mutableStateOf(UnstableNonEqualTestClass("UnstableNonEqualTestClass Text")) }
        val stableNonEqualTestClass by remember { mutableStateOf(StableNonEqualTestClass("StableNonEqualTestClass Text")) }


        HorizontalDivider()
        TestEqualClassContent(unstableNonEqualTestClass)
        TestEqualClassContent(UnstableNonEqualTestClass("New UnstableNonEqualTestClass Text"))
        TestEqualClassContent(UnstableNonEqualTestClass("$count TestClass Text"))
        TestEqualClassContent(stableNonEqualTestClass)
        // unstable -> 참조 주소 비교
        TestEqualClassContent(StableNonEqualTestClass("New StableNonEqualTestClass Text"))
        TestEqualClassContent(StableNonEqualTestClass("$count TestClass Text"))
    }
}

@Composable
fun TestEqualClassContent(content: UnstableEqualTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestEqualClassContent(content: StableEqualTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestEqualClassContent(content: UnstableNonEqualTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestEqualClassContent(content: StableNonEqualTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

class UnstableEqualTestClass(
    var text: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UnstableEqualTestClass

        return text == other.text
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }
}


class StableEqualTestClass(
    val text: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StableEqualTestClass

        return text == other.text
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }
}

class UnstableNonEqualTestClass(
    var text: String,
)

class StableNonEqualTestClass(
    val text: String,
)
