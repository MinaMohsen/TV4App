package com.example.tv4app.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("SimpleDateFormat")
fun String.toFormatedTime(): String {
    val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US)
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date: Date = inputFormat.parse(this)
    return outputFormat.format(date)
}