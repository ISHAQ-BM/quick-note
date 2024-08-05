package com.example.quicknote.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.quicknote.databinding.ActivityMainBinding
import com.example.quicknote.presentation.ui.components.QuickNoteApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater).apply {
            composeView.setContent {
                QuickNoteApp()
            }
        }
        setContentView(binding?.root)

    }
}

