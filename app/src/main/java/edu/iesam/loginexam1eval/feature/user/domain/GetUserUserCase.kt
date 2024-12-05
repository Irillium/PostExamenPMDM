package edu.iesam.loginexam1eval.feature.user.domain

import org.koin.core.annotation.Single

@Single
class GetUserUserCase (private val userRepository: UserRepository) {
    fun invoke(id:String):User?{
        return userRepository.findById(id)
    }
}