package com.enping.cccandroidtest.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Personal advice: Using @Embedded for retrieval person object.
@Entity
data class Estimate(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String = "",
    @ColumnInfo(name = "address")
    @SerializedName("address")
    val address: String = "",
    @ColumnInfo(name = "company")
    @SerializedName("company")
    val company: String = "",
    @ColumnInfo(name = "contact")
    @SerializedName("contact")
    val contact: String = "",
    @ColumnInfo(name = "created_by")
    @SerializedName("created_by")
    val createdBy: String = "",
    @ColumnInfo(name = "created_date")
    @SerializedName("created_date")
    val createdDate: String = "",
    @ColumnInfo(name = "number")
    @SerializedName("number")
    val number: Int = 0,
    @ColumnInfo(name = "requested_by")
    @SerializedName("requested_by")
    val requestedBy: String = "",
    @ColumnInfo(name = "revision_number")
    @SerializedName("revision_number")
    val revisionNumber: Int = 0
)