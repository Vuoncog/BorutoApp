package com.example.borutoapp.data.local

import androidx.room.TypeConverter

class ListStringConverter {
    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String{
        val stringConverter: StringBuilder = StringBuilder()
        list.forEach {
            stringConverter.append(it).append(separator)
        }
        stringConverter.setLength(stringConverter.length - separator.length)
        return stringConverter.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> = string.split(separator)
}