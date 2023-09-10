package com.example.testing_project_1_adroid_with_nodejs


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testing_project_1_adroid_with_nodejs.data.PreferenceProvider
import com.example.testing_project_1_adroid_with_nodejs.databinding.ActivityLobbyBinding
import com.example.testing_project_1_adroid_with_nodejs.other.Resource
import com.example.testing_project_1_adroid_with_nodejs.repository.Repository
import com.example.testing_project_1_adroid_with_nodejs.viewmodel.MainViewModel

class LobbyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLobbyBinding
    private lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLobbyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preference = PreferenceProvider(context = this)
        val repository = Repository(preference)
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val username = intent.getStringExtra("username")




        Log.d("USERNAME", viewModel.getUsername().toString())

        viewModel.handleUsername(username.toString())

        binding.apply {
            viewModel.nullUsername.observe(this@LobbyActivity){event->
                if(!event.hasBeenHandled){
                    event.getContentIfNotHandled()?.let {resource ->
                        when(resource){
                            is Resource.Success -> {
                                val prefsUsername = viewModel.getUsername()
                                textView.text = prefsUsername.toString()
                            }

                            is Resource.Error -> {


                                textView.text = username

                            }

                            else -> {}
                        }
                    }
                }
            }

        }

        binding.logoutBtn.setOnClickListener {
            viewModel.setFlag(false)

            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@LobbyActivity, MainActivity::class.java))
            finish()
        }




    }


}