package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    var str = this
    var removeSpace = value
    if (removeSpace < str.lastIndex) {
        if (str[removeSpace-1]==' ') removeSpace--
        str = str.replaceRange(removeSpace..lastIndex, "...")
    }
    return str
}

fun String.stripHtml(): String {
    val str = this
    val regex = "</?.*?>".toRegex()
    val replaced = str.replace(regex, "").replace("\\s+".toRegex()," ")
    return replaced
}