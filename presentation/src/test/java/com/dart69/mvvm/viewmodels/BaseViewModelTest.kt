package com.dart69.mvvm.viewmodels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class TestBaseViewModel : BaseViewModel<Int>(0) {
    fun increment() = states.update(Int::inc)
}

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest {
    private lateinit var viewModel: TestBaseViewModel
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun beforeEach() {
        viewModel = TestBaseViewModel()
    }

    @Test
    fun `the state is equal to two after increment called twice`() = runBlocking {
        val count = 3
        val result = async(testDispatcher) {
            viewModel.statesAsFlow().take(count).last()
        }
        repeat(count) { viewModel.increment() }
        assertEquals(2, result.await())
    }
}