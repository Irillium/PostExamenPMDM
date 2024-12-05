package edu.iesam.loginexam1eval.feature.user.domain

import org.koin.core.annotation.Single

@Single
class GetUserRemindUseCase(private val userRepository: UserRepository) {
    fun invoke():User?{
        return userRepository.findUserRemind()
    }
}