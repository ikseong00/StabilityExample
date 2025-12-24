package com.ikseong.stabilityexample

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ClassTestScreen(modifier: Modifier = Modifier, isPortrait: Boolean = true) {
    var count by remember { mutableIntStateOf(0) }

    if (isPortrait) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = { count++ }, // count 를 증가 시켜 recomposition 발생
            ) {
                Text("count: $count")
            }
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            val rememberString by remember { mutableStateOf("remember") }
            val nonRememberString = "nonRemember"
            val testClass by remember { mutableStateOf(TestClass("Text")) }

            Card {
                Text("TestClass", fontWeight = FontWeight.Bold)
                TestClassContent(testClass)
                TestClassContent(TestClass(rememberString))
                TestClassContent(TestClass(nonRememberString))
                TestClassContent(TestClass("New"))
                TestClassContent(TestClass("$count Text"))
            }

            HorizontalDivider()

            val rememberList by remember { mutableStateOf(listOf("List")) }
            val nonRememberList = listOf("List")
            val listTestClass by remember { mutableStateOf(ListTestClass("Text", listOf("List"))) }

            Card {
                Text("ListTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(listTestClass)
                TestClassContent(ListTestClass("remember", rememberList))
                TestClassContent(ListTestClass("nonRemember", nonRememberList))
                TestClassContent(ListTestClass("New", listOf("List")))
                TestClassContent(ListTestClass("$count Text", listOf("List")))
            }

            HorizontalDivider()

            val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
            val nonRememberArray = arrayOf("Array")
            val arrayTestClass by remember { mutableStateOf(ArrayTestClass("Text", arrayOf("Array"))) }

            Card {
                Text("ArrayTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(arrayTestClass)
                TestClassContent(ArrayTestClass("remember", rememberArray))
                TestClassContent(ArrayTestClass("nonRemember", nonRememberArray))
                TestClassContent(ArrayTestClass("New", arrayOf("Array")))
                TestClassContent(ArrayTestClass("$count Text", arrayOf("Array")))
            }

            HorizontalDivider()

            val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
            val nonRememberUnstableClass = UnstableClass("Unstable")
            val unstableTestClass by remember { mutableStateOf(UnstableTestClass("Text", UnstableClass("Unstable"))) }

            Card {
                Text("UnstableTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(unstableTestClass)
                TestClassContent(UnstableTestClass("New", UnstableClass("Unstable")))
                TestClassContent(UnstableTestClass("remember", rememberUnstableClass))
                TestClassContent(UnstableTestClass("nonRemember", nonRememberUnstableClass))
                TestClassContent(UnstableTestClass("$count Text", UnstableClass("Unstable")))
            }

            HorizontalDivider()

            val rememberStableClass by remember { mutableStateOf(StableClass("Stable")) }
            val nonRememberStableClass = StableClass("Stable")
            val stableMemberTestClass by remember { mutableStateOf(StableMemberTestClass("Text", StableClass("Stable"))) }

            Card {
                Text("StableMemberTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(stableMemberTestClass)
                TestClassContent(StableMemberTestClass("New", StableClass("Stable")))
                TestClassContent(StableMemberTestClass("remember", rememberStableClass))
                TestClassContent(StableMemberTestClass("nonRemember", nonRememberStableClass))
                TestClassContent(StableMemberTestClass("$count Text", StableClass("Stable")))
            }

            HorizontalDivider()

            val rememberImmutableClass by remember { mutableStateOf(ImmutableClass("Immutable")) }
            val nonRememberImmutableClass = ImmutableClass("Immutable")
            val immutableMemberTestClass by remember { mutableStateOf(ImmutableMemberTestClass("Text", ImmutableClass("Immutable"))) }

            Card {
                Text("ImmutableMemberTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(immutableMemberTestClass)
                TestClassContent(ImmutableMemberTestClass("New", ImmutableClass("Immutable")))
                TestClassContent(ImmutableMemberTestClass("remember", rememberImmutableClass))
                TestClassContent(ImmutableMemberTestClass("nonRemember", nonRememberImmutableClass))
                TestClassContent(ImmutableMemberTestClass("$count Text", ImmutableClass("Immutable")))
            }
        }
    }
    } else {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = { count++ }, // count 를 증가 시켜 recomposition 발생
            ) {
                Text("count: $count")
            }
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
            ) {

            val rememberString by remember { mutableStateOf("remember") }
            val nonRememberString = "nonRemember"
            val testClass by remember { mutableStateOf(TestClass("Text")) }

            Card {
                Text("TestClass", fontWeight = FontWeight.Bold)
                TestClassContent(testClass)
                TestClassContent(TestClass(rememberString))
                TestClassContent(TestClass(nonRememberString))
                TestClassContent(TestClass("New"))
                TestClassContent(TestClass("$count Text"))
            }

            VerticalDivider()

            val rememberList by remember { mutableStateOf(listOf("List")) }
            val nonRememberList = listOf("List")
            val listTestClass by remember { mutableStateOf(ListTestClass("Text", listOf("List"))) }

            Card {
                Text("ListTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(listTestClass)
                TestClassContent(ListTestClass("remember", rememberList))
                TestClassContent(ListTestClass("nonRemember", nonRememberList))
                TestClassContent(ListTestClass("New", listOf("List")))
                TestClassContent(ListTestClass("$count Text", listOf("List")))
            }

            VerticalDivider()

            val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
            val nonRememberArray = arrayOf("Array")
            val arrayTestClass by remember { mutableStateOf(ArrayTestClass("Text", arrayOf("Array"))) }

            Card {
                Text("ArrayTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(arrayTestClass)
                TestClassContent(ArrayTestClass("remember", rememberArray))
                TestClassContent(ArrayTestClass("nonRemember", nonRememberArray))
                TestClassContent(ArrayTestClass("New", arrayOf("Array")))
                TestClassContent(ArrayTestClass("$count Text", arrayOf("Array")))
            }

            VerticalDivider()

            val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
            val nonRememberUnstableClass = UnstableClass("Unstable")
            val unstableTestClass by remember { mutableStateOf(UnstableTestClass("Text", UnstableClass("Unstable"))) }

            Card {
                Text("UnstableTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(unstableTestClass)
                TestClassContent(UnstableTestClass("New", UnstableClass("Unstable")))
                TestClassContent(UnstableTestClass("remember", rememberUnstableClass))
                TestClassContent(UnstableTestClass("nonRemember", nonRememberUnstableClass))
                TestClassContent(UnstableTestClass("$count Text", UnstableClass("Unstable")))
            }

            VerticalDivider()

            val rememberStableClass by remember { mutableStateOf(StableClass("Stable")) }
            val nonRememberStableClass = StableClass("Stable")
            val stableMemberTestClass by remember { mutableStateOf(StableMemberTestClass("Text", StableClass("Stable"))) }

            Card {
                Text("StableMemberTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(stableMemberTestClass)
                TestClassContent(StableMemberTestClass("New", StableClass("Stable")))
                TestClassContent(StableMemberTestClass("remember", rememberStableClass))
                TestClassContent(StableMemberTestClass("nonRemember", nonRememberStableClass))
                TestClassContent(StableMemberTestClass("$count Text", StableClass("Stable")))
            }

            VerticalDivider()

            val rememberImmutableClass by remember { mutableStateOf(ImmutableClass("Immutable")) }
            val nonRememberImmutableClass = ImmutableClass("Immutable")
            val immutableMemberTestClass by remember { mutableStateOf(ImmutableMemberTestClass("Text", ImmutableClass("Immutable"))) }

            Card {
                Text("ImmutableMemberTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(immutableMemberTestClass)
                TestClassContent(ImmutableMemberTestClass("New", ImmutableClass("Immutable")))
                TestClassContent(ImmutableMemberTestClass("remember", rememberImmutableClass))
                TestClassContent(ImmutableMemberTestClass("nonRemember", nonRememberImmutableClass))
                TestClassContent(ImmutableMemberTestClass("$count Text", ImmutableClass("Immutable")))
            }
        }
    }
    }
}

@Composable
fun TestClassContent(content: TestClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: ListTestClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: ArrayTestClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: UnstableTestClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: StableMemberTestClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: ImmutableMemberTestClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

class TestClass(var text: String)
class ListTestClass(var text: String, val list: List<String>)
class ArrayTestClass(var text: String, val array: Array<String>)
class UnstableTestClass(var text: String, val unstable: UnstableClass)
class StableMemberTestClass(var text: String, val stable: StableClass)
class ImmutableMemberTestClass(var text: String, val immutable: ImmutableClass)
