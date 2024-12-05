package edu.iesam.loginexam1eval.feature.user.domain

import org.koin.core.annotation.Single

@Single
class CreateUserUseCase(private val userRepository: UserRepository) {
    fun invoke(user:User){
        userRepository.save(user)
    }
}