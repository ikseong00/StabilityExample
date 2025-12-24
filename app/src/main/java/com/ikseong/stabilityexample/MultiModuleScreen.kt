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
import com.ikseong.stabilityexample.multimodule.MMImmutableTestClass
import com.ikseong.stabilityexample.multimodule.MMImmutableTestDataClass
import com.ikseong.stabilityexample.multimodule.MMStableEqualTestClass
import com.ikseong.stabilityexample.multimodule.MMStableNonEqualTestClass
import com.ikseong.stabilityexample.multimodule.MMStableTestClass
import com.ikseong.stabilityexample.multimodule.MMStableTestDataClass
import com.ikseong.stabilityexample.multimodule.MMTestClass
import com.ikseong.stabilityexample.multimodule.MMTestDataClass
import com.ikseong.stabilityexample.multimodule.MMUnstableEqualTestClass
import com.ikseong.stabilityexample.multimodule.MMUnstableNonEqualTestClass

@Composable
fun MultiModuleScreen(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {

        Button(
            onClick = { count++ }, // count 를 증가 시켜 recomposition 발생
        ) {
            Text("count: $count")
        }

        val immutableTestClass by remember { mutableStateOf(MMImmutableTestClass("MMImmutableTestClass Text")) }
        val stableTestClass by remember { mutableStateOf(MMStableTestClass("MMStableTestClass Text")) }
        val testClass by remember { mutableStateOf(MMTestClass("MMTestClass Text")) }

        TestClassContent(testClass)
        TestClassContent(MMTestClass("New MMTestClass Text"))
        TestClassContent(MMTestClass("$count TestClass Text"))
        TestClassContent(stableTestClass)
        // Object.equals() 비교
        TestClassContent(MMStableTestClass("New MMTestClass Text"))
        TestClassContent(MMStableTestClass("$count TestClass Text"))
        TestClassContent(immutableTestClass)
        TestClassContent(MMImmutableTestClass("New MMTestClass Text"))
        TestClassContent(MMImmutableTestClass("$count TestClass Text"))

        val testDataClass by remember { mutableStateOf(MMTestDataClass("MMTestDataClass Text")) }
        val stableTestDataClass by remember { mutableStateOf(MMStableTestDataClass("MMStableTestDataClass Text")) }
        val immutableTestDataClass by remember { mutableStateOf(MMImmutableTestDataClass("MMImmutableTestDataClass Text")) }

        HorizontalDivider()

        TestDataClassContent(testDataClass)
        TestDataClassContent(MMTestDataClass("New MMTestClass Text"))
        TestDataClassContent(MMTestDataClass("$count TestClass Text"))
        TestDataClassContent(stableTestDataClass)
        // Object.equals() 비교
        TestDataClassContent(MMStableTestDataClass("New MMTestClass Text"))
        TestDataClassContent(MMStableTestDataClass("$count TestClass Text"))
        TestDataClassContent(immutableTestDataClass)
        TestDataClassContent(MMImmutableTestDataClass("New MMTestClass Text"))
        TestDataClassContent(MMImmutableTestDataClass("$count TestClass Text"))

        val unstableEqualTestClass by remember { mutableStateOf(MMUnstableEqualTestClass("MMUnstableEqualTestClass Text")) }
        val stableEqualTestClass by remember { mutableStateOf(MMStableEqualTestClass("MMStableEqualTestClass Text")) }

        HorizontalDivider()
        TestEqualClassContent(unstableEqualTestClass)
        val value = MMUnstableEqualTestClass("MMUnstableEqualTestClass Text")
        println(value.equals(unstableEqualTestClass))
        TestEqualClassContent(value)
        TestEqualClassContent(MMUnstableEqualTestClass("$count TestClass Text"))
        TestEqualClassContent(stableEqualTestClass)
        // Object.equals() 비교
        val value2 = MMStableEqualTestClass("MMStableEqualTestClass Text")
        println(value2.equals(stableEqualTestClass))
        TestEqualClassContent(value2)
        TestEqualClassContent(MMStableEqualTestClass("$count TestClass Text"))

        val unstableNonEqualTestClass by remember { mutableStateOf(MMUnstableNonEqualTestClass("MMUnstableNonEqualTestClass Text")) }
        val stableNonEqualTestClass by remember { mutableStateOf(MMStableNonEqualTestClass("MMStableNonEqualTestClass Text")) }


        HorizontalDivider()
        TestEqualClassContent(unstableNonEqualTestClass)
        TestEqualClassContent(MMUnstableNonEqualTestClass("New MMUnstableNonEqualTestClass Text"))
        TestEqualClassContent(MMUnstableNonEqualTestClass("$count TestClass Text"))
        TestEqualClassContent(stableNonEqualTestClass)
        // unstable -> 참조 주소 비교
        TestEqualClassContent(MMStableNonEqualTestClass("New MMStableNonEqualTestClass Text"))
        TestEqualClassContent(MMStableNonEqualTestClass("$count TestClass Text"))
    }
}

@Composable
fun TestClassContent(content: MMTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: MMImmutableTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: MMStableTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestDataClassContent(content: MMTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestDataClassContent(content: MMImmutableTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestDataClassContent(content: MMStableTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestEqualClassContent(content: MMUnstableEqualTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestEqualClassContent(content: MMStableEqualTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestEqualClassContent(content: MMUnstableNonEqualTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestEqualClassContent(content: MMStableNonEqualTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}
