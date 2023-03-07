package ru.andkaz.mycomposefm.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NameFiewModel:ViewModel() {
    private val _state = mutableStateOf(UIName())
    val state: State<UIName> = _state


    data class UIName(
        val name: String = "No name",
        val edit_name:String=""

    )

    sealed class NameEvent {
        class GetName(val value: String) : NameEvent()
        class SaveName(val value: String) : NameEvent()
        class EnteredName(val value: String):NameEvent()
    }

     fun onEvent(event: NameEvent) {
        //state.value.
        when (event) {
            is NameEvent.GetName->{
                _state.value =state.value.copy(
                name=event.value

                )
            }
            is NameEvent.SaveName->{
                _state.value =state.value.copy(
                    //name=state.value.edit_name
                    name=event.value

                )
            }
            is NameEvent.EnteredName->{
                _state.value=state.value.copy(
                    edit_name = event.value
                )
            }

        }


    }
}
