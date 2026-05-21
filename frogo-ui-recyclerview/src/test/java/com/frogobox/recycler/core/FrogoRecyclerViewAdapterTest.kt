package com.frogobox.recycler.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.system.measureTimeMillis

@RunWith(RobolectricTestRunner::class)
class FrogoRecyclerViewAdapterTest {

    // =============================================================================================
    // UNIT TESTS
    // =============================================================================================

    @Test
    fun testDefaultFlags() {
        val adapter = FrogoViewAdapter<String>()
        assertFalse(adapter.hasEmptyView)
        assertFalse(adapter.hasMultiHolder)
        assertFalse(adapter.hasNestedView)
    }

    @Test
    fun testSetupFlags() {
        val adapter = FrogoViewAdapter<String>()
        
        adapter.setupNestedView()
        assertTrue(adapter.hasNestedView)
        
        adapter.setupMultiHolder()
        assertTrue(adapter.hasMultiHolder)
        
        adapter.setupEmptyView(123)
        assertTrue(adapter.hasEmptyView)
    }

    @Test
    fun testGetItemCountSimpleList() {
        val adapter = FrogoViewAdapter<String>()
        assertEquals(0, adapter.itemCount)

        adapter.setItem(listOf("A", "B", "C"))
        assertEquals(3, adapter.itemCount)
    }

    @Test
    fun testGetItemCountWithEmptyView() {
        val adapter = FrogoViewAdapter<String>()
        adapter.setupEmptyView(null)

        // When list is empty, and hasEmptyView is true, itemCount should be 1 (empty view layout is displayed)
        assertEquals(1, adapter.itemCount)

        // When list has items, itemCount is the size of the list
        adapter.setItem(listOf("A", "B"))
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun testGetItemCountNestedView() {
        val adapter = FrogoViewAdapter<String>()
        adapter.setupNestedView()
        
        val nestedData = listOf(
            mutableListOf("A", "B"),
            mutableListOf("C", "D", "E")
        )
        adapter.setupDataNested(nestedData)
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun testSetAndGetItems() {
        val adapter = FrogoViewAdapter<String>()
        val data = listOf("Apple", "Banana", "Cherry")
        adapter.setItem(data)
        
        val retrieved = adapter.getItem()
        assertEquals(3, retrieved.size)
        assertEquals("Apple", retrieved[0])
        assertEquals("Banana", retrieved[1])
        assertEquals("Cherry", retrieved[2])
    }

    // =============================================================================================
    // STRESS TESTS
    // =============================================================================================

    @Test
    fun testSubmitLargeDataSetStress() {
        val adapter = FrogoViewAdapter<String>()
        val largeList = List(10000) { "Item $it" }
        
        adapter.setItem(largeList)
        assertEquals(10000, adapter.itemCount)
        assertEquals("Item 0", adapter.getItem()[0])
        assertEquals("Item 9999", adapter.getItem()[9999])
    }

    // =============================================================================================
    // PERFORMANCE TESTS
    // =============================================================================================

    @Test
    fun testSetItemPerformance() {
        val adapter = FrogoViewAdapter<String>()
        val iterations = 50
        val itemsCount = 1000
        val dummyList = List(itemsCount) { "Performance $it" }
        
        val timeTaken = measureTimeMillis {
            for (i in 0 until iterations) {
                adapter.setItem(dummyList)
                adapter.setItem(emptyList())
            }
        }
        
        println("Performance: Set and cleared $itemsCount items $iterations times in $timeTaken ms")
        // Ensuring it takes a reasonable time (e.g. less than 2 seconds)
        assertTrue("Setting items $iterations times took too long: $timeTaken ms", timeTaken < 2000)
    }
}
