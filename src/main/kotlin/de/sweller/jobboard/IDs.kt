package de.sweller.jobboard

val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9') + '_' + '-'

fun generateId(): String = generateSequence { chars.random() }.take(9).joinToString("")
