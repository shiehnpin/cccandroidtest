package com.enping.cccandroidtest.model

data class Record(
    val id: String = "",
    val address: String = "",
    val company: String = "",
    val revisionNumber: Int = 0,
    val number: Int = 0,
    val contact: Person,
    val createdBy: Person,
    val requestedBy: Person,
    val createdDate: String = ""
)