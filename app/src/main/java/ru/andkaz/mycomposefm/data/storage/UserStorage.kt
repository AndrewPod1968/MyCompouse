package ru.andkaz.mycomposefm.data.storage

import ru.andkaz.mycomposefm.data.storage.models.User

interface UserStorage {
    fun save(user: User):Boolean
    fun get(): User
}