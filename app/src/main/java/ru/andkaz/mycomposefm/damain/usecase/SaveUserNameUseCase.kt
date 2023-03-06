package ru.andkaz.mycomposefm.damain.usecase

import ru.andkaz.mycomposefm.damain.models.SaveUserNameParam

class SaveUserNameUseCase {
    fun execute(param: SaveUserNameParam):Boolean {
        if (param.name.isEmpty()) {
            return false
        } else {
            true
        }
        return true
    }
}