package co.com.ceiba.mobile.pruebadeingreso.presentation.userlist

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI
import co.com.ceiba.mobile.pruebadeingreso.presentation.BaseActivity
import co.com.ceiba.mobile.pruebadeingreso.presentation.userPost.PostActivity
import co.com.ceiba.mobile.pruebadeingreso.utils.SearchViewQueryTextCallback
import co.com.ceiba.mobile.pruebadeingreso.utils.OnItemSelected
import co.com.ceiba.mobile.pruebadeingreso.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result as Result

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: UsersViewModel by viewModels()


    private lateinit var binding: ActivityMainBinding

    private var usersResultContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    binding.editTextSearch.text.clear()
                }
            }
        }

    private val onItemSelectedListener: OnItemSelected<UsersUI> =
        object : OnItemSelected<UsersUI> {
            override fun onItemSelected(item: UsersUI) {

                val additionalCardsIntent =
                    Intent(this@MainActivity, PostActivity::class.java).apply {
                        putExtra(USER_KEY, item)
                    }
                usersResultContent.launch(additionalCardsIntent)

            }
        }

    private val adapter: UsersAdapter = UsersAdapter(onItemSelectedListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeUi()
        initRecyclerView()
        initSearchComponentHelper()
    }


    private fun initRecyclerView() {
        adapter.setData(emptyList())
        with(binding) {
            recyclerViewSearchResults.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewSearchResults.adapter = adapter
        }
    }

    private fun subscribeUi() {

        viewModel.user.observe(this, {
            when (it) {
                is Result.Success<ArrayList<UsersUI>> -> {
                    progressBar(false)
                    it.data?.let { users -> adapter.setData(users.toList()) }
                }
                is Result.Loading -> {
                    progressBar(true)
                }
                is Result.Failure -> {
                    showSnackbar(binding.content)
                }
            }
        })
    }

    private fun initSearchComponentHelper() {

        val listener = object : SearchViewQueryTextCallback {
            override fun onTextChanged(query: CharSequence) {
                adapter.getFilter().filter(query)
            }
        }
        binding.editTextSearch.addTextChangedListener(listener)
    }

    override fun onResume() {
        viewModel.retrieveUsersDefault()
        super.onResume()
    }

}