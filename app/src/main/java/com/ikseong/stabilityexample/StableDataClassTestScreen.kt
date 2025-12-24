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
fun StableDataClassTestScreen(modifier: Modifier = Modifier, isPortrait: Boolean = true) {
    var count by remember { mutableIntStateOf(0) }

    if (isPortrait) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { count++ },
            ) {
                Text("count: $count")
            }
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            val rememberStableDataClass by remember { mutableStateOf("remember") }
            val nonRememberStableDataClass = "nonRemember"
            val stableTestClass by remember { mutableStateOf(StableDataClass("Text")) }

            Card {
                Text("StableDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(stableTestClass)
                TestClassContent(StableDataClass(rememberStableDataClass))
                TestClassContent(StableDataClass(nonRememberStableDataClass))
                TestClassContent(StableDataClass("New"))
                TestClassContent(StableDataClass("$count Text"))
            }

            HorizontalDivider()

            val rememberList by remember { mutableStateOf(listOf("List")) }
            val nonRememberList = listOf("List")
            val listStableDataTestClass by remember { mutableStateOf(ListStableDataClass("Text", listOf("List"))) }

            Card {
                Text("ListStableDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(listStableDataTestClass)
                TestClassContent(ListStableDataClass("remember", rememberList))
                TestClassContent(ListStableDataClass("nonRemember", nonRememberList))
                TestClassContent(ListStableDataClass("New", listOf("List")))
                TestClassContent(ListStableDataClass("$count Text", listOf("List")))
            }

            HorizontalDivider()

            val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
            val nonRememberArray = arrayOf("Array")
            val arrayStableDataTestClass by remember { mutableStateOf(ArrayStableDataClass("Text", arrayOf("Array"))) }

            Card {
                Text("ArrayStableDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(arrayStableDataTestClass)
                TestClassContent(ArrayStableDataClass("remember", rememberArray))
                TestClassContent(ArrayStableDataClass("nonRemember", nonRememberArray))
                TestClassContent(ArrayStableDataClass("New", arrayOf("Array")))
                TestClassContent(ArrayStableDataClass("$count Text", arrayOf("Array")))
            }

            HorizontalDivider()

            val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
            val nonRememberUnstableClass = UnstableClass("Unstable")
            val unstableMemberStableDataTestClass by remember { mutableStateOf(UnstableMemberStableDataClass("Text", UnstableClass("Unstable"))) }

            Card {
                Text("UnstableMemberStableDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(unstableMemberStableDataTestClass)
                TestClassContent(UnstableMemberStableDataClass("New", UnstableClass("Unstable")))
                TestClassContent(UnstableMemberStableDataClass("remember", rememberUnstableClass))
                TestClassContent(UnstableMemberStableDataClass("nonRemember", nonRememberUnstableClass))
                TestClassContent(UnstableMemberStableDataClass("$count Text", UnstableClass("Unstable")))
            }

            HorizontalDivider()

            val rememberStableMemberClass by remember { mutableStateOf(StableClass("Stable")) }
            val nonRememberStableMemberClass = StableClass("Stable")
            val stableMemberStableDataTestClass by remember { mutableStateOf(StableMemberStableDataClass("Text", StableClass("Stable"))) }

            Card {
                Text("StableMemberStableDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(stableMemberStableDataTestClass)
                TestClassContent(StableMemberStableDataClass("New", StableClass("Stable")))
                TestClassContent(StableMemberStableDataClass("remember", rememberStableMemberClass))
                TestClassContent(StableMemberStableDataClass("nonRemember", nonRememberStableMemberClass))
                TestClassContent(StableMemberStableDataClass("$count Text", StableClass("Stable")))
            }

            HorizontalDivider()

            val rememberImmutableClass by remember { mutableStateOf(ImmutableClass("Immutable")) }
            val nonRememberImmutableClass = ImmutableClass("Immutable")
            val immutableMemberStableDataTestClass by remember { mutableStateOf(ImmutableMemberStableDataClass("Text", ImmutableClass("Immutable"))) }

            Card {
                Text("ImmutableMemberStableDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(immutableMemberStableDataTestClass)
                TestClassContent(ImmutableMemberStableDataClass("New", ImmutableClass("Immutable")))
                TestClassContent(ImmutableMemberStableDataClass("remember", rememberImmutableClass))
                TestClassContent(ImmutableMemberStableDataClass("nonRemember", nonRememberImmutableClass))
                TestClassContent(ImmutableMemberStableDataClass("$count Text", ImmutableClass("Immutable")))
            }
        }
    }
    } else {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { count++ },
            ) {
                Text("count: $count")
            }
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val rememberStableDataClass by remember { mutableStateOf("remember") }
                val nonRememberStableDataClass = "nonRemember"
                val stableTestClass by remember { mutableStateOf(StableDataClass("Text")) }

                Card {
                    Text("StableDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(stableTestClass)
                    TestClassContent(StableDataClass(rememberStableDataClass))
                    TestClassContent(StableDataClass(nonRememberStableDataClass))
                    TestClassContent(StableDataClass("New"))
                    TestClassContent(StableDataClass("$count Text"))
                }

                HorizontalDivider()

                val rememberList by remember { mutableStateOf(listOf("List")) }
                val nonRememberList = listOf("List")
                val listStableDataTestClass by remember { mutableStateOf(ListStableDataClass("Text", listOf("List"))) }

                Card {
                    Text("ListStableDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(listStableDataTestClass)
                    TestClassContent(ListStableDataClass("remember", rememberList))
                    TestClassContent(ListStableDataClass("nonRemember", nonRememberList))
                    TestClassContent(ListStableDataClass("New", listOf("List")))
                    TestClassContent(ListStableDataClass("$count Text", listOf("List")))
                }

                HorizontalDivider()

                val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
                val nonRememberArray = arrayOf("Array")
                val arrayStableDataTestClass by remember { mutableStateOf(ArrayStableDataClass("Text", arrayOf("Array"))) }

                Card {
                    Text("ArrayStableDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(arrayStableDataTestClass)
                    TestClassContent(ArrayStableDataClass("remember", rememberArray))
                    TestClassContent(ArrayStableDataClass("nonRemember", nonRememberArray))
                    TestClassContent(ArrayStableDataClass("New", arrayOf("Array")))
                    TestClassContent(ArrayStableDataClass("$count Text", arrayOf("Array")))
                }

                HorizontalDivider()

                val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
                val nonRememberUnstableClass = UnstableClass("Unstable")
                val unstableMemberStableDataTestClass by remember { mutableStateOf(UnstableMemberStableDataClass("Text", UnstableClass("Unstable"))) }

                Card {
                    Text("UnstableMemberStableDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(unstableMemberStableDataTestClass)
                    TestClassContent(UnstableMemberStableDataClass("New", UnstableClass("Unstable")))
                    TestClassContent(UnstableMemberStableDataClass("remember", rememberUnstableClass))
                    TestClassContent(UnstableMemberStableDataClass("nonRemember", nonRememberUnstableClass))
                    TestClassContent(UnstableMemberStableDataClass("$count Text", UnstableClass("Unstable")))
                }

                HorizontalDivider()

                val rememberStableMemberClass by remember { mutableStateOf(StableClass("Stable")) }
                val nonRememberStableMemberClass = StableClass("Stable")
                val stableMemberStableDataTestClass by remember { mutableStateOf(StableMemberStableDataClass("Text", StableClass("Stable"))) }

                Card {
                    Text("StableMemberStableDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(stableMemberStableDataTestClass)
                    TestClassContent(StableMemberStableDataClass("New", StableClass("Stable")))
                    TestClassContent(StableMemberStableDataClass("remember", rememberStableMemberClass))
                    TestClassContent(StableMemberStableDataClass("nonRemember", nonRememberStableMemberClass))
                    TestClassContent(StableMemberStableDataClass("$count Text", StableClass("Stable")))
                }

                HorizontalDivider()

                val rememberImmutableClass by remember { mutableStateOf(ImmutableClass("Immutable")) }
                val nonRememberImmutableClass = ImmutableClass("Immutable")
                val immutableMemberStableDataTestClass by remember { mutableStateOf(ImmutableMemberStableDataClass("Text", ImmutableClass("Immutable"))) }

                Card {
                    Text("ImmutableMemberStableDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(immutableMemberStableDataTestClass)
                    TestClassContent(ImmutableMemberStableDataClass("New", ImmutableClass("Immutable")))
                    TestClassContent(ImmutableMemberStableDataClass("remember", rememberImmutableClass))
                    TestClassContent(ImmutableMemberStableDataClass("nonRemember", nonRememberImmutableClass))
                    TestClassContent(ImmutableMemberStableDataClass("$count Text", ImmutableClass("Immutable")))
                }
            }
        }
    }
}

@Composable
fun TestClassContent(content: StableDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: ListStableDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: ArrayStableDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: UnstableMemberStableDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: StableMemberStableDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: ImmutableMemberStableDataClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Stable
data class StableDataClass(var text: String) {
    var state by mutableStateOf(text)
}
@Stable
data class ListStableDataClass(val text: String, val list: List<String>)
@Stable
data class ArrayStableDataClass(val text: String, val array: Array<String>)
@Stable
data class UnstableMemberStableDataClass(val text: String, val unstable: UnstableClass)
@Stable
data class StableMemberStableDataClass(val text: String, val stable: StableClass)
@Stable
data class ImmutableMemberStableDataClass(val text: String, val immutable: ImmutableClass)
