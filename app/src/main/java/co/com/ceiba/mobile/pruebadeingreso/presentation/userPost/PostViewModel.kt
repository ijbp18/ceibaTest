package co.com.ceiba.mobile.pruebadeingreso.presentation.userPost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI
import co.com.ceiba.mobile.pruebadeingreso.domain.usecase.PostUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postUserCase: PostUserCase) : ViewModel() {


    private val _post = MutableLiveData<Result<ArrayList<PostUI>>>()
    val post: LiveData<Result<ArrayList<PostUI>>> get() = _post

    fun retrievePostData(userId: Int) {
        viewModelScope.launch {
            postUserCase.invoke(userId)
                .collect{
                    _post.value = it
                }
        }
    }
}