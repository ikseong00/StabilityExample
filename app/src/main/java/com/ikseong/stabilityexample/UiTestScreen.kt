package com.ikseong.stabilityexample

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList


@Composable
fun UiTestScreen(modifier: Modifier = Modifier) {

    var list by remember {
        mutableStateOf(
            persistentListOf<ListItem>(
                UnstableSelectItem(
                    "사과",
                    false
                ),
                UnstableSelectItem(
                    "포도",
                    false
                ),
                UnstableSelectItem(
                    "딸기",
                    false
                ),
                UnstableSelectItem(
                    "바나나",
                    false
                ),
                UnstableSelectItem(
                    "수박",
                    false
                ),
                UnstableSelectItem(
                    "참외",
                    false
                ),
            )

//            persistentListOf(
//                SelectItem(
//                    "사과",
//                    false
//                ),
//                SelectItem(
//                    "포도",
//                    false
//                ),
//                SelectItem(
//                    "딸기",
//                    false
//                ),
//                SelectItem(
//                    "바나나",
//                    false
//                ),
//                SelectItem(
//                    "수박",
//                    false
//                ),
//                SelectItem(
//                    "참외",
//                    false
//                ),
//            )
//            persistentListOf(
//                StableSelectItem(
//                    "사과",
//                    false
//                ),
//                StableSelectItem(
//                    "포도",
//                    false
//                ),
//                StableSelectItem(
//                    "딸기",
//                    false
//                ),
//                StableSelectItem(
//                    "바나나",
//                    false
//                ),
//                StableSelectItem(
//                    "수박",
//                    false
//                ),
//                StableSelectItem(
//                    "참외",
//                    false
//                ),
//            )
        )
    }
    var count by remember { mutableIntStateOf(0) }


    Column(modifier = modifier.fillMaxSize()) {

        Button(
            onClick = { count++ }, // count 를 증가 시켜 recomposition 발생
        ) {
            Text("count: $count")
        }
        var item by remember {
            mutableStateOf(
                StableSelectItem(
                    "t사과참외",
                    false
                ),
            )
        }
        0.dp

        Item(item) { item.isSelected = item.isSelected.not() }

        HorizontalDivider()
        LazyColumList(list) { item ->
            list = list.toList().map {
                UnstableSelectItem(
                    it.name,
                    if (it.name == item.name) {
                        it.isSelected.not()
                    } else {
                        it.isSelected
                    }
                )

            }.toPersistentList()

//        list = list.map {
//            if (it.name == item.name) {
//                it.copy(isSelected = it.isSelected.not())
//            } else {
//                it
//            }
//        }.toPersistentList()

//            list.forEach {
//                if (it.name == item.name) {
//                    it.isSelected = it.isSelected.not()
//                }
//            }
        }


        val model = remember { CounterModel(0) }

        Column {
            Button(onClick = { model.inc() }) {
                Text("inc")
            }

            // model 인스턴스는 계속 동일(===)인데,
            // model.count 가 snapshot state라서 여기만 다시 그림
            CounterText(model)

            // model을 파라미터로 받지만 내부에서 model.count 안 읽으면
            // model.count 변화와 무관하게 여기까지 다시 그릴 필요가 없음
            StaticPart(model)
        }


    }

}

//@Stable
data class CounterModel(
    val initial: Int,
    var name: String = ",",
) {
    var count by mutableIntStateOf(initial)
        private set

    fun inc() {
        count++
    }
}

@Composable
fun CounterText(model: CounterModel) {
    Text("count = ${model.count}") // <-- count 읽음 → count 바뀌면 여기 invalidation
}

@Composable
fun StaticPart(model: CounterModel) {
    Text("I don't read count") // <-- count를 안 읽으면, count 변화가 여기 invalidation 안 함
}

@Composable
fun Item(
    item: ListItem,
    onClick: (ListItem) -> Unit,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(item) }
            .padding(10.dp)
            .background(if (item.isSelected) Color(0x99FF0000) else Color(0xFFFFFFFF)),
        text = item.name,
        fontSize = 30.sp,
    )
}

@Composable
fun LazyColumList(
    list: ImmutableList<ListItem>,
    onClick: (ListItem) -> Unit = {},
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(
            items = list,
            key = { it.name },
        ) {
            Item(it, onClick)
        }
    }
}

@Stable
interface ListItem {
    val name: String
    val isSelected: Boolean
}

class UnstableSelectItem(
    override val name: String,
    override var isSelected: Boolean = false,
) : ListItem

@Immutable
data class SelectItem(
    override val name: String,
    override val isSelected: Boolean = false,
) : ListItem

//@Stable
//@Immutable
class StableSelectItem(
    override val name: String,
    isSelected: Boolean = false,
) : ListItem {
    override var isSelected by mutableStateOf(isSelected)
}
