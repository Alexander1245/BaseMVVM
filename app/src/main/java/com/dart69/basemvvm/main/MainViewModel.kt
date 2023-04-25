package com.dart69.basemvvm.main

import androidx.lifecycle.viewModelScope
import com.dart69.basemvvm.R
import com.dart69.mvvm.events.Button
import com.dart69.mvvm.events.ShowSnackBarWithAction
import com.dart69.mvvm.events.ViewEvent
import com.dart69.mvvm.strings.StringResource
import com.dart69.mvvm.strings.asStringResource
import com.dart69.mvvm.viewmodels.CommunicatorViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class MainViewModel : CommunicatorViewModel<MainViewModel.MainState, ViewEvent>(MainState()) {

    init {
        states.onEach { (points) ->
            if (points > 0 && points % 5 == 0) {
                events.emit(
                    ShowSnackBarWithAction(
                        message = R.string.awesome.asStringResource(),
                        actionButton = Button(R.string.reset.asStringResource(), this::reset),
                    )
                )
            }
        }.launchIn(viewModelScope)
    }

    fun incrementPoints() {
        states.update { current ->
            val points = current.points + 1
            current.copy(
                points = points,
                isResetEnabled = points > 0,
                message = R.string.points_n.asStringResource(points),
            )
        }
    }

    fun reset() {
        states.value = MainState()
    }

    data class MainState(
        val points: Int = 0,
        val isResetEnabled: Boolean = false,
        val message: StringResource = R.string.click_to_get_point.asStringResource(),
    )
}