package edu.iesam.loginexam1eval.feature.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.iesam.loginexam1eval.feature.user.domain.CreateUserUseCase
import edu.iesam.loginexam1eval.feature.user.domain.DeleteUserUseCase
import edu.iesam.loginexam1eval.feature.user.domain.GetUserRemindUseCase
import edu.iesam.loginexam1eval.feature.user.domain.GetUserUserCase
import edu.iesam.loginexam1eval.feature.user.domain.ModifyUserUseCase
import edu.iesam.loginexam1eval.feature.user.domain.User
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UserViewModel(
    private val getUserUserCase: GetUserUserCase,
    private val createUserUseCase: CreateUserUseCase,
    private val getUserRemindUseCase: GetUserRemindUseCase,
    private val modifyUserUseCase: ModifyUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun addUser(user: User) {
        val userData = getUserUserCase.invoke(user.id)
        if (userData != null) {
            _uiState.postValue(UiState(message = "Este usuario ya esta registrado"))
        } else {
            createUserUseCase.invoke(user)
            val userSaved = getUserUserCase.invoke(user.id)
            if (userSaved != null) {
                _uiState.postValue(UiState(message = "Usuario guardado correctamente"))
            }
        }
    }

    fun loginUser(id: String, password: String, reminder: Boolean) {
        val userData = getUserUserCase.invoke(id)
        if (userData != null) {
            if (userData.pasword == password) {
                modifyUserUseCase.invoke(User(userData.id,userData.pasword,reminder))
                _uiState.postValue(UiState(user = userData))
            } else {
                _uiState.postValue(UiState(message = "Contraseña no valida"))
            }
        } else {
            _uiState.postValue(UiState(message = "Usuario no encontrado"))
        }
    }

    fun loadUserRemind(){
        val userRemind=getUserRemindUseCase.invoke()
        _uiState.postValue(UiState(userRemind=userRemind))
    }

    fun deleteUser(id:String,password: String){
        val userData= getUserUserCase.invoke(id)
        if(userData!=null){
            if(userData.pasword==password){
                deleteUserUseCase.invoke(id)
                _uiState.postValue(UiState(message = "Usuario eliminado correctamente"))
            }else{
                _uiState.postValue(UiState(message = "Contraseña no valida"))
            }
        }else{
            _uiState.postValue(UiState(message = "El usuario no existe"))
        }
    }

    data class UiState(
        val user: User? = null,
        val message: String? = null,
        val userRemind:User?=null
    )
}