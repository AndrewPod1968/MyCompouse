package ru.andkaz.mycomposefm.damain.usecase

import ru.andkaz.mycomposefm.damain.models.UserName

class GetUserNameUseCase {
   fun execute(): UserName {
        //return userRepsitory.getName()
       return UserName(firstName = "Andrew", lastName = "Kaz")
//
   }
}