package com.learn.learnroomdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ContactViewModel(
    private val dao: ContactDao
) : ViewModel() {

    private val _state = MutableStateFlow(ContactState())
    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.FIRST_NAME -> {
                    dao.getContactsOrderByFirstName()
                }
                SortType.LAST_NAME -> {
                    dao.getContactsOrderByLastName()
                }
                SortType.PHONE_NUMBER -> {
                    dao.getContactsOrderByPhoneNumber()
                }
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())


    fun onEvent(event: ContactEvent) {
        when (event) {
            is ContactEvent.DeleteContacts -> {
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            }
            ContactEvent.HideDialog -> {
                _state.update {
                    it.copy(isAddingContact = false)
                }
            }
            ContactEvent.SaveContact -> {
                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val phoneNumber = state.value.phoneNumber
                val address = state.value.address

                if (firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
                    return
                }

                if (address.street.isBlank() || address.city.isBlank() || address.postCode.isBlank() || address.state.isBlank()) {
                    return
                }

                val contact = Contact(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber,
                    address = with(address) { Address(street = this.street, city = this.city, postCode = this.postCode, state = this.state) }
                )

                viewModelScope.launch {
                    dao.upsertContact(contact)
                }
                _state.update {
                    it.copy(
                        isAddingContact = false,
                        firstName = "",
                        lastName = "",
                        phoneNumber = ""
                    )
                }
            }
            is ContactEvent.SetFirstName -> {
                _state.update {
                    it.copy(
                        firstName = event.firstName
                    )
                }
            }
            is ContactEvent.SetLastName -> {
                _state.update {
                    it.copy(
                        lastName = event.lastName
                    )
                }
            }
            is ContactEvent.SetPhoneNumber -> {
                _state.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
            }
            is ContactEvent.SetStreet -> {
                _state.update {
                    it.copy(
                        address = it.address.copy(street = event.street)
                    )
                }
            }
            is ContactEvent.SetCity -> {
                _state.update {
                    it.copy(
                        address = it.address.copy(city = event.city)
                    )
                }
            }
            is ContactEvent.SetPostCode -> {
                _state.update {
                    it.copy(
                        address = it.address.copy(postCode = event.postCode)
                    )
                }
            }
            is ContactEvent.SetState -> {
                _state.update {
                    it.copy(
                        address = it.address.copy(state = event.state)
                    )
                }
            }
            ContactEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = true
                    )
                }
            }
            is ContactEvent.SortContacts -> {
                _sortType.value = event.sortType
            }
        }
    }


}