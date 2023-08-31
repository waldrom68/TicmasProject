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
        // El metodo devuelve valores tipo indexOf: true -> 1, false -> 0, null -> -1
        viewModel.comparisonResult.value?.result.also {
            assertEquals(-1, it)
        }
    }

    @Test
    fun mainViewModel_CheckActionCompareWithDifferentData() = runTest {
        val caseList: List<List<String>> = MockTestData().diferentData()

        caseList.forEach {
            launch {
                viewModel.actionCompare(it[0], it[1])
            }

            advanceUntilIdle()
            // El metodo devuelve valores tipo indexOf: true -> 1, false -> 0, null -> -1
            viewModel.comparisonResult.value?.result.also {
                assertEquals(0, it)
            }
        }
    }

    @Test
    fun mainViewModel_CheckActionCompareWithEqualData() = runTest {
        val caseList: List<List<String>> = MockTestData().equalsData()

        caseList.forEach {
            launch {
                viewModel.actionCompare(it[0], it[1])
            }

            advanceUntilIdle()
            // El metodo devuelve valores tipo indexOf: true -> 1, false -> 0, null -> -1
            viewModel.comparisonResult.value?.result.also {
                assertEquals( 1, it )
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
            // El metodo devuelve valores tipo indexOf: true -> 1, false -> 0, null -> -1
            viewModel.comparisonResult.value?.result.also {
                assertEquals(-1, it)
            }
        }
    }

//    @Test
//    fun mainViewModel_CheckActionReset() = runTest {
//        // Outside of this test, the new values assigned in EditText
//        launch {
//            viewModel.actionReset()
//        }
//
//        advanceUntilIdle()
//        // El metodo devuelve valores tipo indexOf: true -> 1, false -> 0, null -> -1
//        viewModel.comparisonResult.value?.result.also {
//            assertEquals(-1, it)
//        }
//    }

}