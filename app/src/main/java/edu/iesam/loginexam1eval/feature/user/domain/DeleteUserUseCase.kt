package edu.iesam.loginexam1eval.feature.user.domain

import org.koin.core.annotation.Single

@Single
class DeleteUserUseCase (private val userRepository: UserRepository) {
    fun invoke(id:String){
        userRepository.deleteById(id)
    }
}