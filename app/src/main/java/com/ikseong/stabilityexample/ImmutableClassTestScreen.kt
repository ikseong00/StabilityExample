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
fun ImmutableClassTestScreen(modifier: Modifier = Modifier, isPortrait: Boolean = true) {
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
            val testClass by remember { mutableStateOf(DefaultImmutableTestClass("Text")) }


            Card {
                Text("DefaultImmutableTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(testClass)
                TestClassContent(DefaultImmutableTestClass(rememberString))
                TestClassContent(DefaultImmutableTestClass(nonRememberString))
                TestClassContent(DefaultImmutableTestClass("New"))
                TestClassContent(DefaultImmutableTestClass("$count Text"))
            }

            HorizontalDivider()

            val rememberList by remember { mutableStateOf(listOf("List")) }
            val nonRememberList = listOf("List")
            val listImmutableTestClass by remember {
                mutableStateOf(
                    ListImmutableTestClass(
                        "Text",
                        listOf("List")
                    )
                )
            }
            Card {
                Text("ListImmutableTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(listImmutableTestClass)
                TestClassContent(
                    ListImmutableTestClass(
                        "remember",
                        rememberList
                    )
                )
                TestClassContent(ListImmutableTestClass("nonRemember", nonRememberList))
                TestClassContent(ListImmutableTestClass("New", listOf("List")))
                TestClassContent(ListImmutableTestClass("$count Text", listOf("List")))
            }

            HorizontalDivider()

            val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
            val nonRememberArray = arrayOf("Array")
            val arrayImmutableTestClass by remember {
                mutableStateOf(ArrayImmutableTestClass("Text", arrayOf("Array")))
            }
            Card {
                Text("ArrayImmutableTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(arrayImmutableTestClass)
                TestClassContent(
                    ArrayImmutableTestClass(
                        "remember",
                        rememberArray
                    )
                )
                TestClassContent(ArrayImmutableTestClass("nonRemember", nonRememberArray))
                TestClassContent(ArrayImmutableTestClass("New", arrayOf("Array")))
                TestClassContent(ArrayImmutableTestClass("$count Text", arrayOf("Array")))
            }


            HorizontalDivider()

            val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
            val nonRememberUnstableClass = UnstableClass("Unstable")
            val unStableTestClass by remember {
                mutableStateOf(
                    UnstableImmutableTestClass("Text", UnstableClass("Unstable"))
                )
            }

            Card {
                Text("UnstableImmutableTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(unStableTestClass)
                TestClassContent(
                    UnstableImmutableTestClass("New", UnstableClass("Unstable"))
                )
                TestClassContent(
                    UnstableImmutableTestClass("remember", rememberUnstableClass)
                )
                TestClassContent(
                    UnstableImmutableTestClass("nonRemember", nonRememberUnstableClass)
                )
                TestClassContent(
                    UnstableImmutableTestClass("$count Text", UnstableClass("Unstable"))
                )
            }


            HorizontalDivider()

            val rememberStableClass by remember { mutableStateOf(StableClass("Stable")) }
            val nonRememberStableClass = StableClass("Stable")
            val stableTestClass by remember {
                mutableStateOf(
                    StableImmutableTestClass("Text", StableClass("Stable"))
                )
            }

            Card {
                Text("StableImmutableTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(stableTestClass)
                TestClassContent(
                    StableImmutableTestClass("New", StableClass("Stable"))
                )
                TestClassContent(
                    StableImmutableTestClass("remember", rememberStableClass)
                )
                TestClassContent(
                    StableImmutableTestClass("nonRemember", nonRememberStableClass)
                )
                TestClassContent(
                    StableImmutableTestClass("$count Text", StableClass("Stable"))
                )
            }


            HorizontalDivider()

            val rememberImmutableClass by remember { mutableStateOf(ImmutableClass("Immutable")) }
            val nonRememberImmutableClass = ImmutableClass("Immutable")
            val immutableTestClass by remember {
                mutableStateOf(
                    ImmutableImmutableTestClass("Text", rememberImmutableClass)
                )
            }

            Card {
                Text("ImmutableImmutableTestClass", fontWeight = FontWeight.Bold)
                TestClassContent(immutableTestClass)
                TestClassContent(
                    ImmutableImmutableTestClass("New", ImmutableClass("Immutable"))
                )
                TestClassContent(
                    ImmutableImmutableTestClass("remember", rememberImmutableClass)
                )
                TestClassContent(
                    ImmutableImmutableTestClass("nonRemember", nonRememberImmutableClass)
                )
                TestClassContent(
                    ImmutableImmutableTestClass("$count Text", ImmutableClass("Immutable"))
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
                val testClass by remember { mutableStateOf(DefaultImmutableTestClass("Text")) }

                Card {
                    Text("DefaultImmutableTestClass", fontWeight = FontWeight.Bold)
                    TestClassContent(testClass)
                    TestClassContent(DefaultImmutableTestClass(rememberString))
                    TestClassContent(DefaultImmutableTestClass(nonRememberString))
                    TestClassContent(DefaultImmutableTestClass("New"))
                    TestClassContent(DefaultImmutableTestClass("$count Text"))
                }

                HorizontalDivider()

                val rememberList by remember { mutableStateOf(listOf("List")) }
                val nonRememberList = listOf("List")
                val listImmutableTestClass by remember {
                    mutableStateOf(ListImmutableTestClass("Text", listOf("List")))
                }
                Card {
                    Text("ListImmutableTestClass", fontWeight = FontWeight.Bold)
                    TestClassContent(listImmutableTestClass)
                    TestClassContent(ListImmutableTestClass("remember", rememberList))
                    TestClassContent(ListImmutableTestClass("nonRemember", nonRememberList))
                    TestClassContent(ListImmutableTestClass("New", listOf("List")))
                    TestClassContent(ListImmutableTestClass("$count Text", listOf("List")))
                }

                HorizontalDivider()

                val rememberArray by remember { mutableStateOf(arrayOf("Array")) }
                val nonRememberArray = arrayOf("Array")
                val arrayImmutableTestClass by remember {
                    mutableStateOf(ArrayImmutableTestClass("Text", arrayOf("Array")))
                }
                Card {
                    Text("ArrayImmutableTestClass", fontWeight = FontWeight.Bold)
                    TestClassContent(arrayImmutableTestClass)
                    TestClassContent(ArrayImmutableTestClass("remember", rememberArray))
                    TestClassContent(ArrayImmutableTestClass("nonRemember", nonRememberArray))
                    TestClassContent(ArrayImmutableTestClass("New", arrayOf("Array")))
                    TestClassContent(ArrayImmutableTestClass("$count Text", arrayOf("Array")))
                }

                HorizontalDivider()

                val rememberUnstableClass by remember { mutableStateOf(UnstableClass("Unstable")) }
                val nonRememberUnstableClass = UnstableClass("Unstable")
                val unStableTestClass by remember {
                    mutableStateOf(UnstableImmutableTestClass("Text", UnstableClass("Unstable")))
                }

                Card {
                    Text("UnstableImmutableTestClass", fontWeight = FontWeight.Bold)
                    TestClassContent(unStableTestClass)
                    TestClassContent(UnstableImmutableTestClass("New", UnstableClass("Unstable")))
                    TestClassContent(UnstableImmutableTestClass("remember", rememberUnstableClass))
                    TestClassContent(UnstableImmutableTestClass("nonRemember", nonRememberUnstableClass))
                    TestClassContent(UnstableImmutableTestClass("$count Text", UnstableClass("Unstable")))
                }

                HorizontalDivider()

                val rememberStableClass by remember { mutableStateOf(StableClass("Stable")) }
                val nonRememberStableClass = StableClass("Stable")
                val stableTestClass by remember {
                    mutableStateOf(StableImmutableTestClass("Text", StableClass("Stable")))
                }

                Card {
                    Text("StableImmutableTestClass", fontWeight = FontWeight.Bold)
                    TestClassContent(stableTestClass)
                    TestClassContent(StableImmutableTestClass("New", StableClass("Stable")))
                    TestClassContent(StableImmutableTestClass("remember", rememberStableClass))
                    TestClassContent(StableImmutableTestClass("nonRemember", nonRememberStableClass))
                    TestClassContent(StableImmutableTestClass("$count Text", StableClass("Stable")))
                }

                HorizontalDivider()

                val rememberImmutableClass by remember { mutableStateOf(ImmutableClass("Immutable")) }
                val nonRememberImmutableClass = ImmutableClass("Immutable")
                val immutableTestClass by remember {
                    mutableStateOf(ImmutableImmutableTestClass("Text", rememberImmutableClass))
                }

                Card {
                    Text("ImmutableImmutableTestClass", fontWeight = FontWeight.Bold)
                    TestClassContent(immutableTestClass)
                    TestClassContent(ImmutableImmutableTestClass("New", ImmutableClass("Immutable")))
                    TestClassContent(ImmutableImmutableTestClass("remember", rememberImmutableClass))
                    TestClassContent(ImmutableImmutableTestClass("nonRemember", nonRememberImmutableClass))
                    TestClassContent(ImmutableImmutableTestClass("$count Text", ImmutableClass("Immutable")))
                }
            }
        }
    }
}

@Composable
fun TestClassContent(content: DefaultImmutableTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: ListImmutableTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: ArrayImmutableTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: StableImmutableTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: UnstableImmutableTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Composable
fun TestClassContent(content: ImmutableImmutableTestClass) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = content.text)
    }
}

@Immutable
class DefaultImmutableTestClass(var text: String)

@Immutable
class ListImmutableTestClass(
    var text: String,
    val list: List<String>,
)

@Immutable
class ArrayImmutableTestClass(
    var text: String,
    val array: Array<String>,
)

@Immutable
class UnstableImmutableTestClass(
    var text: String,
    val unstableClass: UnstableClass,
)

@Immutable
class StableImmutableTestClass(
    var text: String,
    val stableClass: StableClass,
)

@Immutable
class ImmutableImmutableTestClass(
    var text: String,
    val immutableClass: ImmutableClass,
)

data class UnstableClass(
    var text: String,
)

@Stable
data class StableClass(
    val text: String,
)


@Immutable
data class ImmutableClass(
    val text: String,
)
