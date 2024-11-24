package edu.iesam.loginexam1eval.feature.user.domain

interface UserRepository {
    fun save(user:User)
    fun findById(id:String):User?
    fun findUserRemind():User?
    fun modify(user:User)
    fun deleteById(id:String)
}