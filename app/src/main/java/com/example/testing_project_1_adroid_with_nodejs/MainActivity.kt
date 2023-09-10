package com.example.testing_project_1_adroid_with_nodejs


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.testing_project_1_adroid_with_nodejs.data.PreferenceProvider
import com.example.testing_project_1_adroid_with_nodejs.databinding.ActivityMainBinding
import com.example.testing_project_1_adroid_with_nodejs.other.Resource
import com.example.testing_project_1_adroid_with_nodejs.repository.Repository
import com.example.testing_project_1_adroid_with_nodejs.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preference = PreferenceProvider(context = this)
        val repository = Repository(preference)
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val intent = Intent(this, LobbyActivity::class.java)

        viewModel.checking()

        binding.apply {

            buttonLogin.setOnClickListener {

                viewModel.login(
                    editEmail.text.toString(),
                    editPassword.text.toString()
                )

            }

            buttonRegister.setOnClickListener {
                viewModel.register(
                    editEmail.text.toString(),
                    editUsername.text.toString(),
                    editPassword.text.toString()
                )
            }


        }




        viewModel.isLoggIn.observe(this){event->
            if(!event.hasBeenHandled){
                event.getContentIfNotHandled()?.let { resource->
                    when(resource){
                        is Resource.Success -> {

                            startActivity(intent)
                            finish()
                        }
                        else -> {}
                    }
                }
            }
        }




        viewModel.loginResponse.observe(this) { resource ->
                    when(resource){
                        is Resource.Success -> {
                            resource.data?.let {
                                viewModel.setFlag(true)


                                intent.putExtra("username",it.body()?.username)
                                viewModel.saveUsername(it.body()?.username.toString())
                                startActivity(intent)
                                finish()

                            }
                        }
                        
                        is Resource.Error -> {
                            Toast.makeText(this, "Invalid email or password or both!!", Toast.LENGTH_SHORT).show()
                        }

                        else -> {}
                    }
                }

        viewModel.registerResponse.observe(this){resource->
            when(resource){
                is  Resource.Success -> {
                    Toast.makeText(this, "Registered Successfully!!", Toast.LENGTH_SHORT).show()
                }
                
                is Resource.Error -> {
                    Toast.makeText(this, "Register Failure!!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        
    }




}