@file:JvmName("ValidationExt")
package es.iessaladillo.pedrojoya.pr149.extensions

import android.util.Patterns

fun String.isValidSpanishPhoneNumber() =
        ((length <= 0 || length >= 9) && (startsWith("6")
                || startsWith("7") || startsWith("8") || startsWith("9"))
                && Patterns.PHONE.matcher(this).matches())

fun String.isValidEmail()= Patterns.EMAIL_ADDRESS.matcher(this).matches()
