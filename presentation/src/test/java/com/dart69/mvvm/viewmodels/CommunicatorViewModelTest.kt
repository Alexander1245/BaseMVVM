package com.dart69.mvvm.viewmodels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class TestCommunicatorViewModel(
    private val testDispatcher: TestDispatcher
) : CommunicatorViewModel<Int, String>(0) {
    fun increment() = states.update(Int::inc)

    fun toast() = events.launch(TOAST_MESSAGE, testDispatcher)

    companion object {
        const val TOAST_MESSAGE = "Hello!"
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class CommunicatorViewModelTest {
    private lateinit var viewModel: TestCommunicatorViewModel
    private val testDispatcher: TestDispatcher get() = UnconfinedTestDispatcher()

    @Before
    fun beforeEach() {
        viewModel = TestCommunicatorViewModel(testDispatcher)
    }

    @Test
    fun `the state is equal to one after increment called`() = runBlocking {
        val count = 2
        val expected = 1
        val actual = async(testDispatcher) {
            viewModel.statesAsFlow().take(count).last()
        }
        viewModel.increment()
        assertEquals(expected, actual.await())
    }

    @Test
    fun `events have been collected`() = runBlocking {
        val count = 2
        val expected = List(count) { TestCommunicatorViewModel.TOAST_MESSAGE }
        val actual = async(testDispatcher) {
            viewModel.eventsAsFlow().take(count).toList()
        }
        repeat(count) { viewModel.toast() }
        assertEquals(expected, actual.await())
    }
}