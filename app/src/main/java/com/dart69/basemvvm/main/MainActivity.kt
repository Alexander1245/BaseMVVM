package com.dart69.basemvvm.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dart69.basemvvm.databinding.ActivityMainBinding
import com.dart69.mvvm.screens.Screen
import com.dart69.mvvm.viewmodels.repeatOnStarted

class MainActivity : AppCompatActivity(), Screen<ActivityMainBinding, MainViewModel> {
    override val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        repeatOnStarted(this) {
            viewModel.collectStates { state ->
                binding.buttonReset.isEnabled = state.isResetEnabled
                binding.textViewScore.text = state.message.asString(this)
            }
        }

        repeatOnStarted(this) {
            viewModel.collectEvents { event ->
                event.applyOn(binding.root)
            }
        }

        binding.textViewScore.setOnClickListener { viewModel.incrementPoints() }
        binding.buttonReset.setOnClickListener { viewModel.reset() }
    }
}