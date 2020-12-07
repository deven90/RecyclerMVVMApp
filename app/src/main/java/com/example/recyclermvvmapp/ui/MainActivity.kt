package com.example.recyclermvvmapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.recyclermvvmapp.R
import com.example.recyclermvvmapp.viewmodel.MainActivityViewModel

open class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getTitle().observe(this, {
            supportActionBar?.title = it
        })
    }

    fun getName(): String {
        return MainActivity::class.java.simpleName
    }
}