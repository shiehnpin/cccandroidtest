package com.enping.cccandroidtest.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Person(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String = "",
    @SerializedName("email")
    @ColumnInfo(name = "email")
    val email: String = "",
    @SerializedName("first_name")
    @ColumnInfo(name = "first_name")
    val firstName: String = "",
    @SerializedName("last_name")
    @ColumnInfo(name = "last_name")
    val lastName: String = "",
    @SerializedName("phone_number")
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String = ""
)