package ru.andkaz.mycomposefm.damain.usecase

import ru.andkaz.mycomposefm.damain.models.SaveUserNameParam
import ru.andkaz.mycomposefm.damain.repository.UserRepsitory
//import ru.andkaz.mycomposefm.data.repository.UserRepsitoryImpl

class SaveUserNameUseCase(private val userRepsitory: UserRepsitory) {
    fun execute(param: SaveUserNameParam):Boolean {

        val result = userRepsitory.saveName(saveparam =param)
        return result
    }
}