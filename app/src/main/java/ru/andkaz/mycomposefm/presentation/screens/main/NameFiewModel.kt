package ru.andkaz.mycomposefm.presentation.screens.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NameFiewModel:ViewModel() {
//    private val _state = mutableStateOf(UIName())
//    val state: State<UIName> = _state

    private val _titleInputFlow = MutableStateFlow(UIName())
    val titleInputFlow: StateFlow<UIName> get() = _titleInputFlow


    data class UIName(
        val name: String = "No name",
        val edit_name:String=""

    )

    sealed class NameEvent {
        class GetName(val value: String) : NameEvent()
        class SaveName(val value: String) : NameEvent()
        class EnteredName(val value: String): NameEvent()
    }

     fun onEvent(event: NameEvent) {
        //state.value.
        when (event) {
            is NameEvent.GetName ->{
//                _state.value =state.value.copy(
                _titleInputFlow.value =titleInputFlow.value.copy(
                name=event.value

                )
            }
            is NameEvent.SaveName ->{
//                _state.value =state.value.copy(
                _titleInputFlow.value =titleInputFlow.value.copy(
                    //name=state.value.edit_name
                    name=event.value

                )
            }
            is NameEvent.EnteredName ->{
//                _state.value=state.value.copy(
                _titleInputFlow.value=titleInputFlow.value.copy(
                    edit_name = event.value
                )
            }

        }


    }
}
