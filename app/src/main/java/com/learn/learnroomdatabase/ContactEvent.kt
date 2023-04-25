package com.learn.learnroomdatabase

sealed interface ContactEvent {
    object SaveContact : ContactEvent
    data class SetFirstName(val firstName: String) : ContactEvent
    data class SetLastName(val lastName: String) : ContactEvent
    data class SetPhoneNumber(val phoneNumber: String) : ContactEvent
    data class SetStreet(val street: String) : ContactEvent
    data class SetCity(val city: String) : ContactEvent
    data class SetPostCode(val postCode: String) : ContactEvent
    data class SetState(val state: String) : ContactEvent
    object ShowDialog : ContactEvent
    object HideDialog : ContactEvent
    data class SortContacts(val sortType: SortType) : ContactEvent
    data class DeleteContacts(val contact: Contact) : ContactEvent


}