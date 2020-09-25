package com.enping.cccandroidtest.model

import java.text.SimpleDateFormat
import java.util.*

data class Record(
    val id: String = "",
    val address: String = "",
    val company: String = "",
    val revisionNumber: Int = 0,
    val number: Int = 0,
    val contact: Person,
    val createdBy: Person,
    val requestedBy: Person,
    val createdDatetime: String = ""
){
    private val inFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private val outFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val createdDate: String
    get() {
        return outFormatter.format(inFormatter.parse(this.createdDatetime)!!)
    }
}