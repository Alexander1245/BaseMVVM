package com.dart69.basemvvm.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dart69.basemvvm.databinding.ActivityMainBinding
import com.dart69.basemvvm.databinding.ItemPetBinding
import com.dart69.basemvvm.main.models.Pet
import com.dart69.mvvm.recyclerview.base.BaseItemCallback
import com.dart69.mvvm.recyclerview.binding.BindingListAdapter
import com.dart69.mvvm.recyclerview.decoration.linear.MarginItemDecoration
import com.dart69.mvvm.screens.Screen
import com.dart69.mvvm.viewmodels.repeatOnStarted
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), Screen<ActivityMainBinding, MainViewModel> {
    override val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel: MainViewModel by viewModels()

    private val petAdapter by lazy {
        BindingListAdapter.Builder<Pet, ItemPetBinding>(ItemPetBinding::inflate)
            .addItemCallback(BaseItemCallback { old, new -> old.id == new.id })
            .bindViewHolder { pet, binding -> binding.textViewName.text = pet.name }
            .addClickListeners { binding ->
                binding.root.onClick { pet, root ->
                    Snackbar.make(root, pet.toString(), Snackbar.LENGTH_SHORT).show()
                }
            }.build()
    }

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