package com.rome.tech.ticmasproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rome.tech.ticmasproject.compare.mock.MockTestData
import com.rome.tech.ticmasproject.compare.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Local unit test, which will execute on the development machine (host).
 *
 */

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelUnitTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        viewModel.comparisonResult.value?.result.also {
            assertEquals(
                "Debe ingresar ambos valores para comparar", it
            )
        }
    }

    @Test
    fun mainViewModel_CheckActionCompareWithDataNegative() = runTest {
        val caseList: List<List<String>> = MockTestData().diferentData()

        caseList.forEach {
            launch {
                viewModel.actionCompare(it[0], it[1])
            }

            advanceUntilIdle()
            viewModel.comparisonResult.value?.result.also {
                assertEquals(
                    "Textos ingresados diferentes", it
                )
            }
        }
    }

    @Test
    fun mainViewModel_CheckActionCompareWithDataPositive() = runTest {
        val caseList: List<List<String>> = MockTestData().equalsData()

        caseList.forEach {
            launch {
                viewModel.actionCompare(it[0], it[1])
            }

            advanceUntilIdle()
            viewModel.comparisonResult.value?.result.also {
                assertEquals(
                    "Textos ingresados iguales", it
                )
            }
        }
    }

    @Test
    fun mainViewModel_CheckActionCompareWithoutData() = runTest {
        val caseList: List<List<String>> = MockTestData().withoutData()

        caseList.forEach {
            launch {
                viewModel.actionCompare(it[0], it[1])
            }

            advanceUntilIdle()
            viewModel.comparisonResult.value?.result.also {
                assertEquals(
                    "Debe ingresar ambos valores para comparar", it
                )
            }
        }
    }

    @Test
    fun mainViewModel_CheckActionReset() = runTest {
        // Outside of this test, the new values assigned to the EditText
        launch {
            viewModel.actionReset()
        }

        advanceUntilIdle()
        viewModel.comparisonResult.value?.result.also {
            assertEquals(
                "Debe ingresar ambos valores para comparar", it
            )
        }
    }

}