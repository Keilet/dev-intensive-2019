package ru.skillbranch.devintensive.extensions


import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs



const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val time = this.time
    var timeDiff = time - date.time
    if (timeDiff>0) timeDiff+=30
    var numeric = timeDiff / SECOND
    numeric = abs(-numeric)
    val numericM= numeric/60
    val numericH= numericM/60
    val numericD= numeric/24/60/60
    var negative=""
    var positive=""
    if (timeDiff<0)negative=" назад"
    else positive="через "
    val res: String = when (numeric) {
        in 0..1 -> "только что"
        in 1..45 -> "${positive}несколько секунд${negative}"
        in 45..75 -> "${positive}минуту${negative}"
        in 75..2700 -> "${positive}${TimeUnits.MINUTE.plural(numericM.toInt())}${negative}"
        in 2700..4500 -> "${positive}час${negative}"
        in 4500..79200 -> "${positive}${TimeUnits.HOUR.plural(numericH.toInt())}${negative}"
        in 79200..93600 -> "${positive}день${negative}"
        in 93600..31104000 -> "${positive}${TimeUnits.DAY.plural(numericD.toInt())}${negative}"
        else -> {
            "более года назад"
        }
    }
    return res
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int):String{
        val lastDigit = value%10
        val days= value - lastDigit
        val time:String = when(this){
            SECOND -> when{
                value-lastDigit == 10 ->"секунд"
                lastDigit == 0 ->"секунд"
                lastDigit == 1 ->"секунду"
                lastDigit <= 4 ->"секунды"
                else -> "секунд"
            }
            MINUTE -> when{
                value-lastDigit == 10 ->"минут"
                lastDigit == 0 ->"минут"
                lastDigit == 1 ->"минуту"
                lastDigit <= 4 ->"минуты"
                else -> "минут"
            }
            HOUR -> when{
                value-lastDigit == 10 ->"часов"
                lastDigit == 0 ->"часов"
                lastDigit == 1 ->"час"
                lastDigit <= 4 ->"часа"
                else -> "часов"
            }
            DAY -> when{
                days == 10|| days == 110||days == 210|| days == 310 ->"дней"
                lastDigit == 0 ->"дней"
                lastDigit == 1 ->"день"
                lastDigit <= 4 ->"дня"
                else -> "дней"
            }
        }
        return "$value $time"
    }
}
