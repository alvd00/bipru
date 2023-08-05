package com.example.bipru.utils.extentions

fun Char.isContainsCyrillic(): Boolean = "АВСЕНКМОРТУ".contains(this)

fun String.convertToCyrillic(): String {
    val cyrillicChars = mapOf(
        'A' to 'А',
        'B' to 'В',
        'C' to 'С',
        'E' to 'Е',
        'H' to 'Н',
        'K' to 'К',
        'M' to 'М',
        'O' to 'О',
        'P' to 'Р',
        'T' to 'Т',
        'X' to 'Х',
        'Y' to 'У'
    )

    val convertedInput = StringBuilder()
    for (char in this) {
        if (char in cyrillicChars.keys) {
            val cyrillicChar = cyrillicChars[char]
            convertedInput.append(cyrillicChar)
        } else {
            convertedInput.append(char)
        }
    }
    return convertedInput.toString()
}