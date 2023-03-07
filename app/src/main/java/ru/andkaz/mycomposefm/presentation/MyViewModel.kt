package ru.andkaz.mycomposefm.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val _state = mutableStateOf(UIState())
    val state: State<UIState> = _state

    sealed class UIEvent {
        object IncrementCounter : UIEvent()
        class ChooseButton(val number: Int) : UIEvent()
    }

    /**
     * mutates the state as per the user interaction.
     */
    fun onEvent(event: UIEvent) {
        when (event) {
            UIEvent.IncrementCounter ->
                _state.value = state.value.copy(
                    counter = state.value.counter + 1
                )

            is UIEvent.ChooseButton ->
                _state.value = state.value.copy(
                    //buttonNumber = state.value.buttonNumber
                    buttonNumber =event.number//state.value.buttonNumber    //_state.value.buttonNumber
                )
        }
    }
}

/**
 * Handles all the UI information
 */
data class UIState(
    val counter: Int = 0,
    val buttonNumber: Int = 1,
    val name:String =""

)