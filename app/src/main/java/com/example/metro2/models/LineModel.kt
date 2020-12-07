package com.example.metro2

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LineModel(
    @SerializedName("line")
    val line: String,
    @SerializedName("stations")
    val stations: List<String>,
    @SerializedName("color")
    val color: String,
    @SerializedName("is_expanded")
    var isExpanded: Boolean = false

) : Serializable {
    override
    fun toString(): String {
        return GsonBuilder().serializeNulls().create().toJson(this)
    }
}