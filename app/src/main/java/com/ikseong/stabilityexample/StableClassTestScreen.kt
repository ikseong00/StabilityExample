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
fun StableClassTestScreen(modifier: Modifier = Modifier, isPortrait: Boolean = true) {
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
            val rememberStableClass by remember { mutableStateOf("remember") }
            val nonRememberStableClass = "nonRemember"
            val stableTestClass by remember { mutableStateOf(DefaultStableClass("Text")) }

            Card {
                Text("StableClass", fontWeight = FontWeight.Bold)
                TestClassContent(stableTestClass)
                TestClassContent(DefaultStableClass(rememberStableClass))
                TestClassContent(DefaultStableClass(nonRememberStableClass))
                TestClassContent(DefaultStableClass("New"))
                TestClassContent(DefaultStableClass("$count Text"))
            }

            HorizontalDivider()

            val rememberList by remember { mutableStateOf(listOf("List")) }
            val nonRememberList = listOf("List")
            val listStableTestClass by remember {
                mutableStateOf(
                    ListStableClass(
                        "Text",
                        listOf("List")
                    )
                )
            }

            Card {
                Text("ListStableClass", fontWeight = FontWeight.Bold)
                TestClassContent(listStableTestClass)
                TestClassContent(ListStableClass("remember", rememberList))
                TestClassContent(ListStableClass("nonRemember", nonRememberList))
                TestClassContent(ListStableClass("New", listOf("List")))
                TestClassContent(ListStableClass("$count Text", listOf("List")))
            }

            HorizontalDivider()

            val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
            val nonRememberArray = arrayOf("Array")
            val arrayStableTestClass by remember {
                mutableStateOf(
                    ArrayStableClass(
                        "Text",
                        arrayOf("Array")
                    )
                )
            }

            Card {
                Text("ArrayStableClass", fontWeight = FontWeight.Bold)
                TestClassContent(arrayStableTestClass)
                TestClassContent(ArrayStableClass("remember", rememberArray))
                TestClassContent(ArrayStableClass("nonRemember", nonRememberArray))
                TestClassContent(ArrayStableClass("New", arrayOf("Array")))
                TestClassContent(ArrayStableClass("$count Text", arrayOf("Array")))
            }

            HorizontalDivider()

            val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
            val nonRememberUnstableClass = UnstableClass("Unstable")
            val unstableStableTestClass by remember {
                mutableStateOf(
                    UnstableMemberStableClass(
                        "Text",
                        UnstableClass("Unstable")
                    )
                )
            }

            Card {
                Text("UnstableMemberStableClass", fontWeight = FontWeight.Bold)
                TestClassContent(unstableStableTestClass)
                TestClassContent(UnstableMemberStableClass("New", UnstableClass("Unstable")))
                TestClassContent(UnstableMemberStableClass("remember", rememberUnstableClass))
                TestClassContent(UnstableMemberStableClass("nonRemember", nonRememberUnstableClass))
                TestClassContent(
                    UnstableMemberStableClass(
                        "$count Text",
                        UnstableClass("Unstable")
                    )
                )
            }

            HorizontalDivider()

            val rememberStableMemberClass by remember { mutableStateOf(StableClass("Stable")) }
            val nonRememberStableMemberClass = StableClass("Stable")
            val stableMemberStableTestClass by remember {
                mutableStateOf(
                    StableMemberStableClass(
                        "Text",
                        StableClass("Stable")
                    )
                )
            }

            Card {
                Text("StableMemberStableClass", fontWeight = FontWeight.Bold)
                TestClassContent(stableMemberStableTestClass)
                TestClassContent(StableMemberStableClass("New", StableClass("Stable")))
                TestClassContent(StableMemberStableClass("remember", rememberStableMemberClass))
                TestClassContent(
                    StableMemberStableClass(
                        "nonRemember",
                        nonRememberStableMemberClass
                    )
                )
                TestClassContent(StableMemberStableClass("$count Text", StableClass("Stable")))
            }

            HorizontalDivider()

            val rememberImmutableClass by remember { mutableStateOf(ImmutableClass("Immutable")) }
            val nonRememberImmutableClass = ImmutableClass("Immutable")
            val immutableMemberStableTestClass by remember {
                mutableStateOf(
                    ImmutableMemberStableClass("Text", ImmutableClass("Immutable"))
                )
            }

            Card {
                Text("ImmutableMemberStableClass", fontWeight = FontWeight.Bold)
                TestClassContent(immutableMemberStableTestClass)
                TestClassContent(ImmutableMemberStableClass("New", ImmutableClass("Immutable")))
                TestClassContent(ImmutableMemberStableClass("remember", rememberImmutableClass))
                TestClassContent(
                    ImmutableMemberStableClass(
                        "nonRemember",
                        nonRememberImmutableClass
                    )
                )
                TestClassContent(
                    ImmutableMemberStableClass(
                        "$count Text",
                        ImmutableClass("Immutable")
                    )
                )
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
                val rememberStableClass by remember { mutableStateOf("remember") }
                val nonRememberStableClass = "nonRemember"
                val stableTestClass by remember { mutableStateOf(DefaultStableClass("Text")) }

                Card {
                    Text("StableClass", fontWeight = FontWeight.Bold)
                    TestClassContent(stableTestClass)
                    TestClassContent(DefaultStableClass(rememberStableClass))
                    TestClassContent(DefaultStableClass(nonRememberStableClass))
                    TestClassContent(DefaultStableClass("New"))
                    TestClassContent(DefaultStableClass("$count Text"))
                }

                HorizontalDivider()

                val rememberList by remember { mutableStateOf(listOf("List")) }
                val nonRememberList = listOf("List")
                val listStableTestClass by remember { mutableStateOf(ListStableClass("Text", listOf("List"))) }

                Card {
                    Text("ListStableClass", fontWeight = FontWeight.Bold)
                    TestClassContent(listStableTestClass)
                    TestClassContent(ListStableClass("remember", rememberList))
                    TestClassContent(ListStableClass("nonRemember", nonRememberList))
                    TestClassContent(ListStableClass("New", listOf("List")))
                    TestClassContent(ListStableClass("$count Text", listOf("List")))
                }

                HorizontalDivider()

                val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
                val nonRememberArray = arrayOf("Array")
                val arrayStableTestClass by remember { mutableStateOf(ArrayStableClass("Text", arrayOf("Array"))) }

                Card {
                    Text("ArrayStableClass", fontWeight = FontWeight.Bold)
                    TestClassContent(arrayStableTestClass)
                    TestClassContent(ArrayStableClass("remember", rememberArray))
                    TestClassContent(ArrayStableClass("nonRemember", nonRememberArray))
                    TestClassContent(ArrayStableClass("New", arrayOf("Array")))
                    TestClassContent(ArrayStableClass("$count Text", arrayOf("Array")))
                }

                HorizontalDivider()

                val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
                val nonRememberUnstableClass = UnstableClass("Unstable")
                val unstableStableTestClass by remember { mutableStateOf(UnstableMemberStableClass("Text", UnstableClass("Unstable"))) }

                Card {
                    Text("UnstableMemberStableClass", fontWeight = FontWeight.Bold)
                    TestClassContent(unstableStableTestClass)
                    TestClassContent(UnstableMemberStableClass("New", UnstableClass("Unstable")))
                    TestClassContent(UnstableMemberStableClass("remember", rememberUnstableClass))
                    TestClassContent(UnstableMemberStableClass("nonRemember", nonRememberUnstableClass))
                    TestClassContent(UnstableMemberStableClass("$count Text", UnstableClass("Unstable")))
                }

                HorizontalDivider()

                val rememberStableMemberClass by remember { mutableStateOf(StableClass("Stable")) }
                val nonRememberStableMemberClass = StableClass("Stable")
                val stableMemberStableTestClass by remember { mutableStateOf(StableMemberStableClass("Text", StableClass("Stable"))) }

                Card {
                    Text("StableMemberStableClass", fontWeight = FontWeight.Bold)
                    TestClassContent(stableMemberStableTestClass)
                    TestClassContent(StableMemberStableClass("New", StableClass("Stable")))
                    TestClassContent(StableMemberStableClass("remember", rememberStableMemberClass))
                    TestClassContent(StableMemberStableClass("nonRemember", nonRememberStableMemberClass))
                    TestClassContent(StableMemberStableClass("$count Text", StableClass("Stable")))
                }

                HorizontalDivider()

                val rememberImmutableClass by remember { mutableStateOf(ImmutableClass("Immutable")) }
                val nonRememberImmutableClass = ImmutableClass("Immutable")
                val immutableMemberStableTestClass by remember { mutableStateOf(ImmutableMemberStableClass("Text", ImmutableClass("Immutable"))) }

                Card {
                    Text("ImmutableMemberStableClass", fontWeight = FontWeight.Bold)
                    TestClassContent(immutableMemberStableTestClass)
                    TestClassContent(ImmutableMemberStableClass("New", ImmutableClass("Immutable")))
                    TestClassContent(ImmutableMemberStableClass("remember", rememberImmutableClass))
                    TestClassContent(ImmutableMemberStableClass("nonRemember", nonRememberImmutableClass))
                    TestClassContent(ImmutableMemberStableClass("$count Text", ImmutableClass("Immutable")))
                }
            }
        }
    }
}

@Composable
fun TestClassContent(content: DefaultStableClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: ListStableClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: ArrayStableClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: UnstableMemberStableClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: StableMemberStableClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Composable
fun TestClassContent(content: ImmutableMemberStableClass) {
    Row(modifier = Modifier.padding(10.dp)) { Text(text = content.text) }
}

@Stable
class DefaultStableClass(val text: String)

@Stable
class ListStableClass(val text: String, val list: List<String>)

@Stable
class ArrayStableClass(val text: String, val array: Array<String>)

@Stable
class UnstableMemberStableClass(val text: String, val unstable: UnstableClass)

@Stable
class StableMemberStableClass(val text: String, val stable: StableClass)

@Stable
class ImmutableMemberStableClass(val text: String, val immutable: ImmutableClass)
