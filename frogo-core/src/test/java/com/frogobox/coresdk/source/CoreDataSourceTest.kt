package com.frogobox.coresdk.source

import io.reactivex.rxjava3.disposables.Disposable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class CoreDataSourceTest {

    // Concrete subclass to test the abstract CoreDataSource
    class TestDataSource : CoreDataSource() {
        fun getCompositeDisposableSize(): Int {
            // We can't access compositeDisposable directly because it's private in CoreDataSource.
            // But we can check if it is active or verify by adding disposables and clearing them.
            return 0
        }
    }

    @Test
    fun testAddAndClearDisposable() {
        val dataSource = TestDataSource()
        val clearedCount = AtomicInteger(0)
        
        val disposable = object : Disposable {
            private var disposed = false
            override fun dispose() {
                disposed = true
                clearedCount.incrementAndGet()
            }
            override fun isDisposed(): Boolean = disposed
        }

        dataSource.addSubscribe(disposable)
        dataSource.onClearDisposables()

        assertEquals(1, clearedCount.get())
        assertTrue(disposable.isDisposed)
    }

    @Test
    fun testStressConcurrentSubscriptions() {
        val dataSource = TestDataSource()
        val numThreads = 20
        val subscriptionsPerThread = 50
        val executor = Executors.newFixedThreadPool(numThreads)
        
        val disposedCount = AtomicInteger(0)

        // Stress/Load testing: Spawn multiple threads adding disposables concurrently
        for (i in 0 until numThreads) {
            executor.submit {
                for (j in 0 until subscriptionsPerThread) {
                    val disposable = object : Disposable {
                        private var disposed = false
                        override fun dispose() {
                            disposed = true
                            disposedCount.incrementAndGet()
                        }
                        override fun isDisposed(): Boolean = disposed
                    }
                    dataSource.addSubscribe(disposable)
                }
            }
        }

        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)

        // Clear everything and verify
        dataSource.onClearDisposables()
        assertEquals(numThreads * subscriptionsPerThread, disposedCount.get())
    }

    private fun assertTrue(value: Boolean) {
        org.junit.Assert.assertTrue(value)
    }
}
