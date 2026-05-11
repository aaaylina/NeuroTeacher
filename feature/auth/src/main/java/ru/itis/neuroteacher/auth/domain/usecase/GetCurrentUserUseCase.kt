package ru.itis.neuroteacher.auth.domain.usecase

import ru.itis.neuroteacher.auth.domain.model.User

interface GetCurrentUserUseCase {
    operator fun invoke(): User?

}