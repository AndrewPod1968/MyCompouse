package ru.andkaz.mycomposefm.data.repository

import android.content.Context
import ru.andkaz.mycomposefm.damain.models.SaveUserNameParam
import ru.andkaz.mycomposefm.damain.models.UserName
import ru.andkaz.mycomposefm.damain.repository.UserRepsitory
import ru.andkaz.mycomposefm.data.storage.UserStorage
import ru.andkaz.mycomposefm.data.storage.models.User

/*
private const val SHARED_PREFS_NAME ="shared_prefst_name"
private const val KEY_FIRST_NAME ="firstName"
private const val KEY_LAST_NAME ="lastName"
private const val DEFAULT_FIRST_NAME ="Default first name"
private const val DEFAULT_LAST_NAME ="Default last name"
class UserRepsitoryImpl( contex:Context):UserRepsitory {
    private  val sharedPreferences = contex.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveName(saveparam:SaveUserNameParam):Boolean{
        sharedPreferences.edit().putString(KEY_FIRST_NAME,saveparam.name).apply()
        return true

    }
    override fun getName(): UserName {
        val firstName=sharedPreferences.getString(KEY_FIRST_NAME,DEFAULT_FIRST_NAME)?:DEFAULT_FIRST_NAME
        val lastName=sharedPreferences.getString(KEY_LAST_NAME, DEFAULT_LAST_NAME)?: DEFAULT_LAST_NAME

        return UserName(firstName = firstName, lastName = lastName)
    }
}

class SharedPrefUserStorage(contex:Context) : UserStorage {
    private  val sharedPreferences = contex.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    override fun save(user: User): Boolean {
        sharedPreferences.edit().putString(KEY_FIRST_NAME,user.firstName).apply()
        sharedPreferences.edit().putString(KEY_LAST_NAME,user.lastName).apply()
        return true
    }

    override fun get(): User {

        val lastName=sharedPreferences.getString(KEY_LAST_NAME, DEFAULT_LAST_NAME)?: DEFAULT_LAST_NAME
        return User(firstName,lastName)
    }
}*/
class UserRepositoryImpl(private val userStorage: UserStorage): UserRepsitory {
    override fun saveName(saveParam:SaveUserNameParam):Boolean{
        val user = mapToStorage(saveParam=saveParam)
        return userStorage.save(user)
    }
    override fun getName():UserName{
        val user=userStorage.get()
        return mapToDomain(user)
    }

    private fun mapToDomain(user: User):UserName{
        return  UserName(firstName = user.firstName, lastName = user.lastName)
    }
    private fun mapToStorage(saveParam:SaveUserNameParam):User{
        return  User(firstName = saveParam.name, lastName = "")
    }
}