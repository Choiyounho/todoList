package com.soten.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.soten.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 3000

    private lateinit var binding: ActivityMainBinding

    private val viewModel: TodoViewModel by viewModels()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        if (FirebaseAuth.getInstance().currentUser == null) {
            login()
        }

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(
                emptyList(),
                onClickDeleteIcon = {
                    viewModel.deleteTodo(it)
                }, onClickItem = {
                    viewModel.okTodo(it)
                })
        }

        binding.editTextDescription.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addTodo()
                return@setOnEditorActionListener true
            }
            false
        }

        binding.buttonAdd.setOnClickListener {
            addTodo()
        }

        viewModel.todoLiveData.observe(this@MainActivity, Observer {
            (binding.recyclerview.adapter as TodoAdapter).setData(it)
        })
    }

    private fun addTodo() {
        if (binding.editTextDescription.text.isEmpty()) {
            Toast.makeText(applicationContext, "내용을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val todo = Todo(binding.editTextDescription.text.toString())
        viewModel.addTodo(todo)
        binding.editTextDescription.setText("")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                viewModel.fetchData()
            } else {
                finish()
            }
        }
    }

    private fun login() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    private fun logout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                login()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
