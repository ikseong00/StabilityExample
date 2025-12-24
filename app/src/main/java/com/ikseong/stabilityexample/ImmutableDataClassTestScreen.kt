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
fun ImmutableDataClassTestScreen(modifier: Modifier = Modifier, isPortrait: Boolean = true) {
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
            val testClass by remember { mutableStateOf(DefaultImmutableTestDataClass("Text")) }

            Card {
                Text("DefaultImmutableTestDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(testClass)
                TestClassContent(DefaultImmutableTestDataClass(rememberString))
                TestClassContent(DefaultImmutableTestDataClass(nonRememberString))
                TestClassContent(DefaultImmutableTestDataClass("New"))
                TestClassContent(DefaultImmutableTestDataClass("$count Text"))
            }

            HorizontalDivider()

            val rememberList by remember { mutableStateOf(listOf("List")) }
            val nonRememberList = listOf("List")
            val listImmutableTestClass by remember {
                mutableStateOf(
                    ListImmutableTestDataClass(
                        "Text",
                        listOf("List")
                    )
                )
            }
            Card {
                Text("ListImmutableTestDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(listImmutableTestClass)
                TestClassContent(
                    ListImmutableTestDataClass(
                        "remember",
                        rememberList
                    )
                )
                TestClassContent(
                    ListImmutableTestDataClass(
                        "nonRemember",
                        nonRememberList
                    )
                )
                TestClassContent(
                    ListImmutableTestDataClass(
                        "New",
                        listOf("List")
                    )
                )
                TestClassContent(
                    ListImmutableTestDataClass(
                        "$count Text",
                        listOf("List")
                    )
                )
            }

            HorizontalDivider()

            val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
            val nonRememberArray = arrayOf("Array")
            val arrayImmutableTestClass by remember {
                mutableStateOf(
                    ArrayImmutableTestDataClass(
                        "Text",
                        arrayOf("Array")
                    )
                )
            }
            Card {
                Text("ArrayImmutableTestDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(arrayImmutableTestClass)
                TestClassContent(
                    ArrayImmutableTestDataClass(
                        "remember",
                        rememberArray
                    )
                )
                TestClassContent(ArrayImmutableTestDataClass("nonRemember", nonRememberArray))
                TestClassContent(ArrayImmutableTestDataClass("New", arrayOf("Array")))
                TestClassContent(ArrayImmutableTestDataClass("$count Text", arrayOf("Array")))
            }


            HorizontalDivider()

            val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
            val nonRememberUnstableClass = UnstableClass("Unstable")
            val unStableTestClass by remember {
                mutableStateOf(
                    UnstableImmutableTestDataClass(
                        "Text",
                        UnstableClass("Unstable")
                    )
                )
            }

            Card {
                Text("UnstableImmutableTestDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(unStableTestClass)
                TestClassContent(
                    UnstableImmutableTestDataClass(
                        "New",
                        UnstableClass("Unstable")
                    )
                )
                TestClassContent(
                    UnstableImmutableTestDataClass(
                        "remember",
                        rememberUnstableClass
                    )
                )
                TestClassContent(
                    UnstableImmutableTestDataClass(
                        "nonRemember",
                        nonRememberUnstableClass
                    )
                )
                TestClassContent(
                    UnstableImmutableTestDataClass(
                        "$count Text",
                        UnstableClass("Unstable")
                    )
                )
            }


            HorizontalDivider()

            val rememberStableClass by remember { mutableStateOf(StableClass("Stable")) }
            val nonRememberStableClass = StableClass("Stable")
            val stableTestClass by remember {
                mutableStateOf(
                    StableImmutableTestDataClass(
                        "Text",
                        StableClass("Stable")
                    )
                )
            }

            Card {
                Text("StableImmutableTestDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(stableTestClass)
                TestClassContent(
                    StableImmutableTestDataClass(
                        "New",
                        StableClass("Stable")
                    )
                )
                TestClassContent(
                    StableImmutableTestDataClass(
                        "remember",
                        rememberStableClass
                    )
                )
                TestClassContent(
                    StableImmutableTestDataClass(
                        "nonRemember",
                        nonRememberStableClass
                    )
                )
                TestClassContent(
                    StableImmutableTestDataClass(
                        "$count Text",
                        StableClass("Stable")
                    )
                )
            }


            HorizontalDivider()

            val rememberImmutableClass by remember { mutableStateOf(ImmutableDataClass("Immutable")) }
            val nonRememberImmutableClass = ImmutableDataClass("Immutable")
            val immutableTestClass by remember {
                mutableStateOf(
                    ImmutableImmutableTestDataClass(
                        "Text",
                        rememberImmutableClass
                    )
                )
            }

            Card {
                Text("ImmutableImmutableTestDataClass", fontWeight = FontWeight.Bold)
                TestClassContent(immutableTestClass)
                TestClassContent(
                    ImmutableImmutableTestDataClass(
                        "New",
                        ImmutableDataClass("Immutable")
                    )
                )
                TestClassContent(
                    ImmutableImmutableTestDataClass(
                        "remember",
                        rememberImmutableClass
                    )
                )
                TestClassContent(
                    ImmutableImmutableTestDataClass(
                        "nonRemember",
                        nonRememberImmutableClass
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
                val rememberString by remember { mutableStateOf("remember") }
                val nonRememberString = "nonRemember"
                val testClass by remember { mutableStateOf(DefaultImmutableTestDataClass("Text")) }

                Card {
                    Text("DefaultImmutableTestDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(testClass)
                    TestClassContent(DefaultImmutableTestDataClass(rememberString))
                    TestClassContent(DefaultImmutableTestDataClass(nonRememberString))
                    TestClassContent(DefaultImmutableTestDataClass("New"))
                    TestClassContent(DefaultImmutableTestDataClass("$count Text"))
                }

                HorizontalDivider()

                val rememberList by remember { mutableStateOf(listOf("List")) }
                val nonRememberList = listOf("List")
                val listImmutableTestClass by remember {
                    mutableStateOf(ListImmutableTestDataClass("Text", listOf("List")))
                }
                Card {
                    Text("ListImmutableTestDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(listImmutableTestClass)
                    TestClassContent(ListImmutableTestDataClass("remember", rememberList))
                    TestClassContent(ListImmutableTestDataClass("nonRemember", nonRememberList))
                    TestClassContent(ListImmutableTestDataClass("New", listOf("List")))
                    TestClassContent(ListImmutableTestDataClass("$count Text", listOf("List")))
                }

                HorizontalDivider()

                val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
                val nonRememberArray = arrayOf("Array")
                val arrayImmutableTestClass by remember {
                    mutableStateOf(ArrayImmutableTestDataClass("Text", arrayOf("Array")))
                }
                Card {
                    Text("ArrayImmutableTestDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(arrayImmutableTestClass)
                    TestClassContent(ArrayImmutableTestDataClass("remember", rememberArray))
                    TestClassContent(ArrayImmutableTestDataClass("nonRemember", nonRememberArray))
                    TestClassContent(ArrayImmutableTestDataClass("New", arrayOf("Array")))
                    TestClassContent(ArrayImmutableTestDataClass("$count Text", arrayOf("Array")))
                }

                HorizontalDivider()

                val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
                val nonRememberUnstableClass = UnstableClass("Unstable")
                val unStableTestClass by remember {
                    mutableStateOf(UnstableImmutableTestDataClass("Text", UnstableClass("Unstable")))
                }

                Card {
                    Text("UnstableImmutableTestDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(unStableTestClass)
                    TestClassContent(UnstableImmutableTestDataClass("New", UnstableClass("Unstable")))
                    TestClassContent(UnstableImmutableTestDataClass("remember", rememberUnstableClass))
                    TestClassContent(UnstableImmutableTestDataClass("nonRemember", nonRememberUnstableClass))
                    TestClassContent(UnstableImmutableTestDataClass("$count Text", UnstableClass("Unstable")))
                }

                HorizontalDivider()

                val rememberStableClass by remember { mutableStateOf(StableClass("Stable")) }
                val nonRememberStableClass = StableClass("Stable")
                val stableTestClass by remember {
                    mutableStateOf(StableImmutableTestDataClass("Text", StableClass("Stable")))
                }

                Card {
                    Text("StableImmutableTestDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(stableTestClass)
                    TestClassContent(StableImmutableTestDataClass("New", StableClass("Stable")))
                    TestClassContent(StableImmutableTestDataClass("remember", rememberStableClass))
                    TestClassContent(StableImmutableTestDataClass("nonRemember", nonRememberStableClass))
                    TestClassContent(StableImmutableTestDataClass("$count Text", StableClass("Stable")))
                }

                HorizontalDivider()

                val rememberImmutableClass by remember { mutableStateOf(ImmutableDataClass("Immutable")) }
                val nonRememberImmutableClass = ImmutableDataClass("Immutable")
                val immutableTestClass by remember {
                    mutableStateOf(ImmutableImmutableTestDataClass("Text", rememberImmutableClass))
                }

                Card {
                    Text("ImmutableImmutableTestDataClass", fontWeight = FontWeight.Bold)
                    TestClassContent(immutableTestClass)
                    TestClassContent(ImmutableImmutableTestDataClass("New", ImmutableDataClass("Immutable")))
                    TestClassContent(ImmutableImmutableTestDataClass("remember", rememberImmutableClass))
                    TestClassContent(ImmutableImmutableTestDataClass("nonRemember", nonRememberImmutableClass))
                }
            }
        }
    }
}

@Composable
fun TestClassContent(content: DefaultImmutableTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: ListImmutableTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: ArrayImmutableTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: StableImmutableTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: UnstableImmutableTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: ImmutableImmutableTestDataClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Immutable
data class DefaultImmutableTestDataClass(var text: String)

@Immutable
data class ListImmutableTestDataClass(
    var text: String,
    val list: List<String>,
)

@Immutable
data class ArrayImmutableTestDataClass(
    var text: String,
    val array: Array<String>,
)

@Immutable
data class UnstableImmutableTestDataClass(
    var text: String,
    val unstableClass: UnstableClass,
)

@Immutable
data class StableImmutableTestDataClass(
    var text: String,
    val stableClass: StableClass,
)

@Immutable
data class ImmutableImmutableTestDataClass(
    var text: String,
    val immutableClass: ImmutableDataClass,
)

@Immutable
data class ImmutableDataClass(
    val text: String,
)
