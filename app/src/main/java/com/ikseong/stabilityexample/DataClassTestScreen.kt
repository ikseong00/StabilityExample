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
fun DataClassTestScreen(modifier: Modifier = Modifier, isPortrait: Boolean = true) {
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
                val testDataClass by remember { mutableStateOf(TestDataClass("Text")) }

                Card {
                    Text("TestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(testDataClass)
                    TestDataClassContent(TestDataClass(rememberString))
                    TestDataClassContent(TestDataClass(nonRememberString))
                    TestDataClassContent(TestDataClass("New"))
                    TestDataClassContent(TestDataClass("$count Text"))
                }

                HorizontalDivider()

                val rememberList by remember { mutableStateOf(listOf("List")) }
                val nonRememberList = listOf("List")
                val listTestDataClass by remember { mutableStateOf(ListTestDataClass("Text", listOf("List"))) }

                Card {
                    Text("ListTestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(listTestDataClass)
                    TestDataClassContent(ListTestDataClass("remember", rememberList))
                    TestDataClassContent(ListTestDataClass("nonRemember", nonRememberList))
                    TestDataClassContent(ListTestDataClass("New", listOf("List")))
                    TestDataClassContent(ListTestDataClass("$count Text", listOf("List")))
                }

                HorizontalDivider()

                val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
                val nonRememberArray = arrayOf("Array")
                val arrayTestDataClass by remember { mutableStateOf(ArrayTestDataClass("Text", arrayOf("Array"))) }

                Card {
                    Text("ArrayTestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(arrayTestDataClass)
                    TestDataClassContent(ArrayTestDataClass("remember", rememberArray))
                    TestDataClassContent(ArrayTestDataClass("nonRemember", nonRememberArray))
                    TestDataClassContent(ArrayTestDataClass("New", arrayOf("Array")))
                    TestDataClassContent(ArrayTestDataClass("$count Text", arrayOf("Array")))
                }

                HorizontalDivider()

                val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
                val nonRememberUnstableClass = UnstableClass("Unstable")
                val unstableTestDataClass by remember { mutableStateOf(UnstableTestDataClass("Text", UnstableClass("Unstable"))) }

                Card {
                    Text("UnstableTestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(unstableTestDataClass)
                    TestDataClassContent(UnstableTestDataClass("New", UnstableClass("Unstable")))
                    TestDataClassContent(UnstableTestDataClass("remember", rememberUnstableClass))
                    TestDataClassContent(UnstableTestDataClass("nonRemember", nonRememberUnstableClass))
                    TestDataClassContent(UnstableTestDataClass("$count Text", UnstableClass("Unstable")))
                }

                HorizontalDivider()

                val rememberStableClass by remember { mutableStateOf(StableClass("Stable")) }
                val nonRememberStableClass = StableClass("Stable")
                val stableMemberTestDataClass by remember { mutableStateOf(StableMemberTestDataClass("Text", StableClass("Stable"))) }

                Card {
                    Text("StableMemberTestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(stableMemberTestDataClass)
                    TestDataClassContent(StableMemberTestDataClass("New", StableClass("Stable")))
                    TestDataClassContent(StableMemberTestDataClass("remember", rememberStableClass))
                    TestDataClassContent(StableMemberTestDataClass("nonRemember", nonRememberStableClass))
                    TestDataClassContent(StableMemberTestDataClass("$count Text", StableClass("Stable")))
                }

                HorizontalDivider()

                val rememberImmutableClass by remember { mutableStateOf(ImmutableClass("Immutable")) }
                val nonRememberImmutableClass = ImmutableClass("Immutable")
                val immutableMemberTestDataClass by remember { mutableStateOf(ImmutableMemberTestDataClass("Text", ImmutableClass("Immutable"))) }

                Card {
                    Text("ImmutableMemberTestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(immutableMemberTestDataClass)
                    TestDataClassContent(ImmutableMemberTestDataClass("New", ImmutableClass("Immutable")))
                    TestDataClassContent(ImmutableMemberTestDataClass("remember", rememberImmutableClass))
                    TestDataClassContent(ImmutableMemberTestDataClass("nonRemember", nonRememberImmutableClass))
                    TestDataClassContent(ImmutableMemberTestDataClass("$count Text", ImmutableClass("Immutable")))
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
                val testDataClass by remember { mutableStateOf(TestDataClass("Text")) }

                Card {
                    Text("TestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(testDataClass)
                    TestDataClassContent(TestDataClass(rememberString))
                    TestDataClassContent(TestDataClass(nonRememberString))
                    TestDataClassContent(TestDataClass("New"))
                    TestDataClassContent(TestDataClass("$count Text"))
                }

                HorizontalDivider()

                val rememberList by remember { mutableStateOf(listOf("List")) }
                val nonRememberList = listOf("List")
                val listTestDataClass by remember { mutableStateOf(ListTestDataClass("Text", listOf("List"))) }

                Card {
                    Text("ListTestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(listTestDataClass)
                    TestDataClassContent(ListTestDataClass("remember", rememberList))
                    TestDataClassContent(ListTestDataClass("nonRemember", nonRememberList))
                    TestDataClassContent(ListTestDataClass("New", listOf("List")))
                    TestDataClassContent(ListTestDataClass("$count Text", listOf("List")))
                }

                HorizontalDivider()

                val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
                val nonRememberArray = arrayOf("Array")
                val arrayTestDataClass by remember { mutableStateOf(ArrayTestDataClass("Text", arrayOf("Array"))) }

                Card {
                    Text("ArrayTestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(arrayTestDataClass)
                    TestDataClassContent(ArrayTestDataClass("remember", rememberArray))
                    TestDataClassContent(ArrayTestDataClass("nonRemember", nonRememberArray))
                    TestDataClassContent(ArrayTestDataClass("New", arrayOf("Array")))
                    TestDataClassContent(ArrayTestDataClass("$count Text", arrayOf("Array")))
                }

                HorizontalDivider()

                val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
                val nonRememberUnstableClass = UnstableClass("Unstable")
                val unstableTestDataClass by remember { mutableStateOf(UnstableTestDataClass("Text", UnstableClass("Unstable"))) }

                Card {
                    Text("UnstableTestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(unstableTestDataClass)
                    TestDataClassContent(UnstableTestDataClass("New", UnstableClass("Unstable")))
                    TestDataClassContent(UnstableTestDataClass("remember", rememberUnstableClass))
                    TestDataClassContent(UnstableTestDataClass("nonRemember", nonRememberUnstableClass))
                    TestDataClassContent(UnstableTestDataClass("$count Text", UnstableClass("Unstable")))
                }

                HorizontalDivider()

                val rememberStableClass by remember { mutableStateOf(StableClass("Stable")) }
                val nonRememberStableClass = StableClass("Stable")
                val stableMemberTestDataClass by remember { mutableStateOf(StableMemberTestDataClass("Text", StableClass("Stable"))) }

                Card {
                    Text("StableMemberTestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(stableMemberTestDataClass)
                    TestDataClassContent(StableMemberTestDataClass("New", StableClass("Stable")))
                    TestDataClassContent(StableMemberTestDataClass("remember", rememberStableClass))
                    TestDataClassContent(StableMemberTestDataClass("nonRemember", nonRememberStableClass))
                    TestDataClassContent(StableMemberTestDataClass("$count Text", StableClass("Stable")))
                }

                HorizontalDivider()

                val rememberImmutableClass by remember { mutableStateOf(ImmutableClass("Immutable")) }
                val nonRememberImmutableClass = ImmutableClass("Immutable")
                val immutableMemberTestDataClass by remember { mutableStateOf(ImmutableMemberTestDataClass("Text", ImmutableClass("Immutable"))) }

                Card {
                    Text("ImmutableMemberTestDataClass", fontWeight = FontWeight.Bold)
                    TestDataClassContent(immutableMemberTestDataClass)
                    TestDataClassContent(ImmutableMemberTestDataClass("New", ImmutableClass("Immutable")))
                    TestDataClassContent(ImmutableMemberTestDataClass("remember", rememberImmutableClass))
                    TestDataClassContent(ImmutableMemberTestDataClass("nonRemember", nonRememberImmutableClass))
                    TestDataClassContent(ImmutableMemberTestDataClass("$count Text", ImmutableClass("Immutable")))
                }
            }
        }
    }
}

@Composable
fun TestDataClassContent(content: TestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestDataClassContent(content: ListTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestDataClassContent(content: ArrayTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestDataClassContent(content: UnstableTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestDataClassContent(content: StableMemberTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestDataClassContent(content: ImmutableMemberTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

data class TestDataClass(var text: String)
data class ListTestDataClass(var text: String, val list: List<String>)
data class ArrayTestDataClass(var text: String, val array: Array<String>)
data class UnstableTestDataClass(var text: String, val unstable: UnstableClass)
data class StableMemberTestDataClass(var text: String, val stable: StableClass)
data class ImmutableMemberTestDataClass(var text: String, val immutable: ImmutableClass)
