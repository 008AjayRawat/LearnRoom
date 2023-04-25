package com.learn.learnroomdatabase

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    @Embedded val address: Address,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0 //Key as Int, so that room can autogenerate ids.
)


data class Address(
    val street: String,
    val state: String,
    val city: String,
    @ColumnInfo("post_code") val postCode: String
)