package com.ikseong.stabilityexample.multimodule

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

class MMTestClass(
    var text: String,
)

@Stable
class MMStableTestClass(
    var text: String,
)

@Immutable
class MMImmutableTestClass(
    var text: String,
)

data class MMTestDataClass(var text: String)

@Stable
data class MMStableTestDataClass(var text: String)

@Immutable
data class MMImmutableTestDataClass(var text: String)

class MMUnstableEqualTestClass(
    var text: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MMUnstableEqualTestClass

        return text == other.text
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }
}

class MMStableEqualTestClass(
    val text: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MMStableEqualTestClass

        return text == other.text
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }
}

class MMUnstableNonEqualTestClass(
    var text: String,
)

class MMStableNonEqualTestClass(
    val text: String,
)
