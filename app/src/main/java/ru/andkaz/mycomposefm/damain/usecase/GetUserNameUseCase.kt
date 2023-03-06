package ru.andkaz.mycomposefm.damain.usecase

import ru.andkaz.mycomposefm.damain.models.UserName
import ru.andkaz.mycomposefm.damain.repository.UserRepsitory
import ru.andkaz.mycomposefm.data.repository.UserRepsitoryImpl

class GetUserNameUseCase(private val userRepsitory: UserRepsitory) {
   fun execute(): UserName {
        return userRepsitory.getName()
   }
}