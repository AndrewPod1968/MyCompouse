package ru.andkaz.mycomposefm.damain.repository

import ru.andkaz.mycomposefm.damain.models.SaveUserNameParam
import ru.andkaz.mycomposefm.damain.models.UserName

interface UserRepsitory {

    fun saveName(saveparam: SaveUserNameParam):Boolean
    fun getName(): UserName
}