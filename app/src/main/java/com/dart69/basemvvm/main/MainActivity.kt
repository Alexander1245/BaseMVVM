package com.dart69.basemvvm.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dart69.basemvvm.databinding.ActivityMainBinding
import com.dart69.mvvm.recyclerview.decoration.linear.MarginItemDecoration
import com.dart69.mvvm.screens.Screen
import com.dart69.mvvm.viewmodels.repeatOnStarted

class MainActivity : AppCompatActivity(), Screen<ActivityMainBinding, MainViewModel> {
    override val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel: MainViewModel by viewModels()

    private val petAdapter by lazy { buildPetAdapter(viewModel::deletePet) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.apply {
            adapter = petAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(MarginItemDecoration(24))
        }

        repeatOnStarted(this) {
            viewModel.collectStates { state ->
                binding.buttonReset.isEnabled = state.isResetEnabled
                binding.textViewScore.text = state.message.asString(this)
                petAdapter.submitList(state.pets)
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