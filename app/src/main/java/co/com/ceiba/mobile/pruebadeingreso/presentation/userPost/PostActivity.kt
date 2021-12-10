package co.com.ceiba.mobile.pruebadeingreso.presentation.userPost

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI
import co.com.ceiba.mobile.pruebadeingreso.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : BaseActivity() {

    private val postViewModel : PostViewModel by viewModels()
    private lateinit var binding : ActivityPostBinding

    private lateinit var userSelected: UsersUI
    private val adapter: PostsAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        setUserInfo()
        subscribeUi()
        initRecyclerView()
    }

    private fun setUserInfo() {
        with(binding){
            name.text = userSelected.name
            phone.text = userSelected.phone
            email.text = userSelected.email
        }
    }

    private fun getIntentData() {
        intent.extras?.let {
            userSelected = it.getSerializable(USER_KEY) as UsersUI
            postViewModel.retrievePostData(userSelected.id)
        }
    }

    private fun initRecyclerView() {
        adapter.setData(emptyList())
        with(binding){
            recyclerViewPostsResults.layoutManager = LinearLayoutManager(this@PostActivity, RecyclerView.VERTICAL, false)
            recyclerViewPostsResults.adapter = adapter
        }

    }

    private fun subscribeUi() {
        postViewModel.post.observe(this, {
            when(it){
                is Result.Success<ArrayList<PostUI>> -> {
                    progressBar(false)
                    it.data?.let { post -> adapter.setData(post.toList()) }
                }
                is Result.Loading -> {
                    progressBar(true)
                }
                is Result.Failure -> {
                    showSnackbar(binding.contentCard)
                }
            }
        })
    }

}