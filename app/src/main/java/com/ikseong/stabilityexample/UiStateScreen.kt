package com.ikseong.stabilityexample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UiStateScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        var count by remember { mutableStateOf(0) }
        Button(
            onClick = { count++ }, // count 를 증가 시켜 recomposition 발생
        ) {
            Text("count: $count")
        }

        var uiState by remember { mutableStateOf<UiState<String>>(SuccessUiState("UiStateContent")) }

        UiStateContent(uiState = uiState)
        UiStateContent(uiState = SuccessUiState("New UiStateContent"))
        UiStateContent(uiState = SuccessUiState("$count UiStateContent"))

        HorizontalDivider()

        var dataUiState by remember { mutableStateOf<UiState<String>>(SuccessUiState("DataUiStateContent")) }

        DataUiStateContent(uiState = dataUiState)
        // strong skipping 모드가 아닐 때는 리컴포지션 됨
        // unstable 하기 때문
        DataUiStateContent(uiState = DataSuccessUiState("New DataUiStateContent"))
        DataUiStateContent(uiState = DataSuccessUiState("$count DataUiStateContent"))

        HorizontalDivider()

        var stableUiState by remember { mutableStateOf<StableUiState<String>>(StableSuccessUiState("StableSuccessUiState")) }

        StableUiStateContent(uiState = stableUiState)
        StableUiStateContent(uiState = StableSuccessUiState("New StableSuccessUiState"))
        StableUiStateContent(uiState = StableSuccessUiState("$count StableSuccessUiState"))

        HorizontalDivider()

        var stableDataUiState by remember {
            mutableStateOf<StableUiState<String>>(
                StableDataSuccessUiState("StableDataUiStateContent")
            )
        }

        StableDataUiStateContent(uiState = stableDataUiState)
        StableDataUiStateContent(uiState = StableDataSuccessUiState("New StableDataUiStateContent"))
        StableDataUiStateContent(uiState = StableDataSuccessUiState("$count StableDataUiStateContent"))

        HorizontalDivider()

        var detailsUiState by remember { mutableStateOf<DetailsUiState>(Idle2) }

        Button(onClick = {
            detailsUiState = when (detailsUiState) {
                Idle2 -> Loading2()
                is Loading2 -> Error2("Error $count")
                is Error2 -> Idle2
                else -> Idle2
            }
        }) {
            Text("Change DetailsUiState")
        }

        DetailsUiStateContent(uiState = detailsUiState)
        DetailsUiStateContent(uiState = Idle2)
        DetailsUiStateContent(uiState = Loading2())
        DetailsUiStateContent(uiState = Error2("Static Error"))
        DetailsUiStateContent(uiState = Error2("Error $count"))

        HorizontalDivider()

        var unstableDetailsUiState by remember {
            mutableStateOf<UnstableDetailsUiState>(
                Idle
            )
        }

        Button(onClick = {
            unstableDetailsUiState = when (unstableDetailsUiState) {
                Idle -> Loading
                Loading -> Error("Error $count")
                is Error -> Idle
                else -> Idle
            }
        }) {
            Text("Change UnstableDetailsUiState")
        }

        UnstableDetailsUiStateContent(uiState = unstableDetailsUiState)
        UnstableDetailsUiStateContent(uiState = Idle)
        UnstableDetailsUiStateContent(uiState = Loading)
        UnstableDetailsUiStateContent(uiState = Error("Static Error"))
        UnstableDetailsUiStateContent(uiState = Error("Error $count"))
    }

}

@Composable
fun UiStateContent(
    modifier: Modifier = Modifier,
    uiState: UiState<String>,
) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(
            text = uiState.value.toString()
        )
    }
}

@Composable
fun DataUiStateContent(
    modifier: Modifier = Modifier,
    uiState: UiState<String>,
) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(
            text = uiState.value.toString()
        )
    }
}

@Composable
fun StableUiStateContent(
    modifier: Modifier = Modifier,
    uiState: StableUiState<String>,
) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(
            text = uiState.value.toString()
        )
    }
}

@Composable
fun StableDataUiStateContent(
    modifier: Modifier = Modifier,
    uiState: StableUiState<String>,
) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(
            text = uiState.value.toString()
        )
    }
}

interface UiState<T> {
    val value: T?
    val exception: Throwable?

    val hasSuccess: Boolean
        get() = exception == null
}

class SuccessUiState<T>(override val value: T) : UiState<T> {
    override val exception: Throwable? = null
}

data class DataSuccessUiState<T>(override val value: T) : UiState<T> {
    override val exception: Throwable? = null
}

@Stable
interface StableUiState<T> {
    val value: T?
    val exception: Throwable?

    val hasSuccess: Boolean
        get() = exception == null
}

class StableSuccessUiState<T>(override val value: T) : StableUiState<T> {
    override val exception: Throwable? = null
}

data class StableDataSuccessUiState<T>(override val value: T) : StableUiState<T> {
    override val exception: Throwable? = null
}

// Stable sealed interface 테스트
@Stable
sealed interface DetailsUiState
data object Idle2 : DetailsUiState
class Loading2(var loading: Boolean = false) : DetailsUiState
data class Error2(val message: String?) : DetailsUiState

@Composable
fun DetailsUiStateContent(
    modifier: Modifier = Modifier,
    uiState: DetailsUiState,
) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(
            text = when (uiState) {
                Idle2 -> "Idle"
                is Loading2 -> "Loading"
                is Error2 -> "Error: ${uiState.message}"
                else -> "12"
            }
        )
    }
}

// Unstable sealed interface 테스트 (Stable 어노테이션 없음)
interface UnstableDetailsUiState
object Idle : UnstableDetailsUiState
data object Loading : UnstableDetailsUiState
data class Error(var message: String?) : UnstableDetailsUiState

@Composable
fun UnstableDetailsUiStateContent(
    modifier: Modifier = Modifier,
    uiState: UnstableDetailsUiState,
) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(
            text = when (uiState) {
                Idle -> "Idle"
                Loading -> "Loading"
                is Error -> "Error: ${uiState.message}"
                else -> "1"
            }
        )
    }
}
