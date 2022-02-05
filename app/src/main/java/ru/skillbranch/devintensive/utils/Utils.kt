package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {

        var firstName: String? = null
        var lastName: String? = null

        if (fullName.isNullOrBlank()) {

        } else {
            val parts: List<String>? = fullName?.split(" ")
            firstName = parts?.getOrNull(0)
            lastName = parts?.getOrNull(1)
            if (firstName.isNullOrBlank()) firstName = null
            if (lastName.isNullOrBlank()) lastName = null
        }
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var nick = payload.replace(Regex("[а-я]", RegexOption.IGNORE_CASE)) {
            when (it.value) {
                "а","А" -> "a"
                "б","Б" -> "b"
                "в","В" -> "v"
                "г","Г" -> "g"
                "д","Д" -> "d"
                "е","Е" -> "e"
                "ё","Ё" -> "e"
                "ж","Ж" -> "zh"
                "з","З" -> "z"
                "и","И" -> "i"
                "й","Й" -> "i"
                "к","К" -> "k"
                "л","Л" -> "l"
                "м","М" -> "m"
                "н","Н" -> "n"
                "о","О" -> "o"
                "п","П" -> "p"
                "р","Р" -> "r"
                "с","С" -> "s"
                "т","Т" -> "t"
                "у","У" -> "u"
                "ф","Ф" -> "f"
                "х","Х" -> "h"
                "ц","Ц" -> "c"
                "ч","Ч" -> "ch"
                "ш","Ш" -> "sh"
                "щ","Щ" -> "sh'"
                "ъ","Ъ" -> ""
                "ы","Ы" -> "i"
                "ь","Ь" -> ""
                "э","Э" -> "e"
                "ю","Ю" -> "yu"
                "я","Я" -> "ya"
                else -> {
                    it.value
                }
            }
        }
        nick=nick.split(" ").map { it.capitalize() }.joinToString(" ")
        nick = nick.replace("\\s".toRegex(), divider)

        return nick
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val initials: String? = when {
            firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null
            firstName.isNullOrBlank() -> lastName?.first()?.uppercase().toString()
            lastName.isNullOrBlank() -> firstName.first().uppercase().toString()
            else -> {
                firstName.first().uppercase() + lastName.first().uppercase()
            }
        }
        return initials
    }
}


