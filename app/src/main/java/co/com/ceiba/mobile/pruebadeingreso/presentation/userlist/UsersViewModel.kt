package co.com.ceiba.mobile.pruebadeingreso.presentation.userlist

import androidx.lifecycle.*
import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI
import co.com.ceiba.mobile.pruebadeingreso.domain.usecase.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    private val _users = MutableLiveData<Result<ArrayList<UsersUI>>>()
    val user: LiveData<Result<ArrayList<UsersUI>>> get() = _users

    fun retrieveUsersDefault() {

        viewModelScope.launch {
            usersUseCase.invoke()
                .collect{
                _users.value = it
            }
        }
    }

}