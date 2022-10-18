package com.example.newsapplication.ui.db

import androidx.room.TypeConverter
import com.example.newsapplication.ui.model.Source

class Convertors {

    @TypeConverter
    fun fromSource(source: Source): String {
        return "${source.name},${source.id}"
    }

    @TypeConverter
    fun toSource(string: String): Source {
        val info = string.split(",");
        return Source(info[0], info[1])
    }
}