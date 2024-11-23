package edu.iesam.loginexam1eval.feature.user.data

import edu.iesam.loginexam1eval.feature.user.data.local.LoginXmlLocalDataSource
import edu.iesam.loginexam1eval.feature.user.domain.User
import edu.iesam.loginexam1eval.feature.user.domain.UserRepository
import org.koin.core.annotation.Single

@Single
class UserDataRepository(private val local: LoginXmlLocalDataSource):UserRepository {
    override fun save(user: User) {
        local.save(user)
    }

    override fun findById(id: String): User? {
       return local.findById(id)
    }

}