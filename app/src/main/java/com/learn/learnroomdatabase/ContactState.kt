package com.learn.learnroomdatabase

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val address: AddressState = AddressState("", "", "", ""),
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME

)


data class AddressState(
    val street: String = "",
    val state: String = "",
    val city: String = "",
    val postCode: String = ""
)
