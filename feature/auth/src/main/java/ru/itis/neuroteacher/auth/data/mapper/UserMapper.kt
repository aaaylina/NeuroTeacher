package ru.itis.neuroteacher.auth.data.mapper

import com.google.firebase.auth.FirebaseUser
import ru.itis.neuroteacher.auth.data.model.UserDataModel
import ru.itis.neuroteacher.auth.domain.model.User
import javax.inject.Inject

internal class UserMapper @Inject constructor() {

    fun toEntity(firebaseUser: FirebaseUser): UserDataModel {
        return UserDataModel(
            id = firebaseUser.uid,
            email = firebaseUser.email ?: "",
        )
    }

    fun toDomain(entity: UserDataModel): User {
        return User(
            id = entity.id,
            email = entity.email
        )
    }
}