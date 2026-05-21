package com.frogobox.compose.view

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

class FrogoComposeViewModelTest {

    // Concrete implementation of FrogoComposeViewModel for testing
    private class TestViewModel : FrogoComposeViewModel() {
        var onStartCalled = false
        var onClearDisposableCalled = false

        override fun onStart() {
            super.onStart()
            onStartCalled = true
        }

        override fun onClearDisposable() {
            super.onClearDisposable()
            onClearDisposableCalled = true
        }

        // Expose protected onCleared() for unit testing
        fun triggerOnCleared() {
            onCleared()
        }
    }

    // =============================================================================================
    // UNIT TESTS
    // =============================================================================================

    @Test
    fun testViewModelLifecycleCallbacks() {
        val viewModel = TestViewModel()
        
        // Assert initial state
        assertTrue(!viewModel.onStartCalled)
        assertTrue(!viewModel.onClearDisposableCalled)

        // Invoke start
        viewModel.onStart()
        assertTrue(viewModel.onStartCalled)

        // Trigger cleared (which should trigger onClearDisposable)
        viewModel.triggerOnCleared()
        assertTrue(viewModel.onClearDisposableCalled)
    }

    // =============================================================================================
    // PERFORMANCE TESTS
    // =============================================================================================

    @Test
    fun testViewModelCreationPerformance() {
        val count = 10000
        val timeTaken = measureTimeMillis {
            for (i in 0 until count) {
                val vm = TestViewModel()
                vm.onStart()
            }
        }
        println("Performance: Created and started $count ViewModels in $timeTaken ms")
        assertTrue("Creation of $count ViewModels took too long: $timeTaken ms", timeTaken < 2000)
    }

    // =============================================================================================
    // STRESS TESTS
    // =============================================================================================

    @Test
    fun testConcurrentViewModelLifecycleStress() {
        val viewModel = TestViewModel()
        val numThreads = 10
        val opsPerThread = 500
        val executor = Executors.newFixedThreadPool(numThreads)
        val startOpsCount = AtomicInteger(0)
        val clearOpsCount = AtomicInteger(0)

        for (i in 0 until numThreads) {
            executor.submit {
                for (j in 0 until opsPerThread) {
                    viewModel.onStart()
                    startOpsCount.incrementAndGet()
                    viewModel.triggerOnCleared()
                    clearOpsCount.incrementAndGet()
                }
            }
        }

        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)

        assertEquals(numThreads * opsPerThread, startOpsCount.get())
        assertEquals(numThreads * opsPerThread, clearOpsCount.get())
        assertTrue(viewModel.onStartCalled)
        assertTrue(viewModel.onClearDisposableCalled)
    }
}
