package edu.iesam.loginexam1eval.feature.user.domain

import org.koin.core.annotation.Single

@Single
class ModifyUserUseCase (private val userRepository: UserRepository) {
    fun invoke(user: User){
        return userRepository.modify(user)
    }
}