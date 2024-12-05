package edu.iesam.loginexam1eval.feature.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.iesam.loginexam1eval.feature.user.domain.CreateUserUseCase
import edu.iesam.loginexam1eval.feature.user.domain.GetUserUserCase
import edu.iesam.loginexam1eval.feature.user.domain.User
import org.koin.android.annotation.KoinViewModel
import org.koin.core.logger.MESSAGE
import java.lang.Error

@KoinViewModel
class UserViewModel(
    private val getUserUserCase: GetUserUserCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _uiState=MutableLiveData<UiState>()
    val uiState:LiveData<UiState> = _uiState

    fun addUser(user:User){
        val userData= getUserUserCase.invoke(user.id)
        if (userData != null){
            _uiState.postValue(UiState(message = "Este usuario ya esta registrado"))
        }else{
            createUserUseCase.invoke(user)
            val userSaved= getUserUserCase.invoke(user.id)
            if (userSaved != null) {
                _uiState.postValue(UiState(message = "Usuario guardado correctamente"))
            }
        }
    }

    data class UiState(
        val user: User? = null,
        val message: String? = null
    )
}