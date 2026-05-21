package com.frogobox.recycler.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FrogoRvConstantTest {

    // =============================================================================================
    // UNIT TESTS
    // =============================================================================================

    @Test
    fun testHolderOptionConstants() {
        assertEquals(0, FrogoRvConstant.OPTION_HOLDER_FIRST)
        assertEquals(1, FrogoRvConstant.OPTION_HOLDER_SECOND)
    }

    @Test
    fun testLayoutConstants() {
        assertEquals("LAYOUT_LINEAR_VERTICAL", FrogoRvConstant.LAYOUT_LINEAR_VERTICAL)
        assertEquals("LAYOUT_LINEAR_VERTICAL_REVERSE", FrogoRvConstant.LAYOUT_LINEAR_VERTICAL_REVERSE)
        assertEquals("LAYOUT_LINEAR_HORIZONTAL", FrogoRvConstant.LAYOUT_LINEAR_HORIZONTAL)
        assertEquals("LAYOUT_LINEAR_HORIZONTAL_REVERSE", FrogoRvConstant.LAYOUT_LINEAR_HORIZONTAL_REVERSE)
        assertEquals("LAYOUT_STAGGERED_GRID", FrogoRvConstant.LAYOUT_STAGGERED_GRID)
        assertEquals("LAYOUT_GRID", FrogoRvConstant.LAYOUT_GRID)
        assertEquals("LAYOUT_FLEXBOX", FrogoRvConstant.LAYOUT_FLEXBOX)
    }

    @Test
    fun testAdapterConstants() {
        assertEquals("FROGO_ADAPTER_R_CLASS", FrogoRvConstant.FROGO_ADAPTER_R_CLASS)
        assertEquals("FROGO_ADAPTER_VIEW_BINDING", FrogoRvConstant.FROGO_ADAPTER_VIEW_BINDING)
        assertEquals("FROGO_ADAPTER_MULTI", FrogoRvConstant.FROGO_ADAPTER_MULTI)
        assertEquals("FrogoRecyclerView", FrogoRvConstant.FROGO_RV_TAG)
        assertEquals("FrogoRecyclerCompose", FrogoRvConstant.FROGO_RV_COMPOSE_TAG)
    }

    @Test
    fun testDummyTexts() {
        assertTrue(FrogoRvConstant.DUMMY_LOREM_IPSUM.startsWith("Lorem Ipsum is simply dummy text"))
        assertTrue(FrogoRvConstant.LINK_PHOTO_GITHUB.contains("githubusercontent"))
    }

    // =============================================================================================
    // USABILITY TESTS
    // =============================================================================================

    @Test
    fun testDeprecatedMessagesUsability() {
        // Assert that deprecated messages are helpful and follow a standardized structure
        val messages = listOf(
            FrogoRvConstant.Deprecated.isViewLinearVertical,
            FrogoRvConstant.Deprecated.isViewLinearHorizontal,
            FrogoRvConstant.Deprecated.isViewStaggeredGrid,
            FrogoRvConstant.Deprecated.isViewGrid,
            FrogoRvConstant.Deprecated.injectAdapter
        )

        for (msg in messages) {
            assertTrue("Message '$msg' should start with 'We are going to replace'", msg.startsWith("We are going to replace"))
        }

        assertTrue(FrogoRvConstant.Deprecated.isViewLinearVertical.contains("createLayoutLinearVertical"))
        assertTrue(FrogoRvConstant.Deprecated.isViewLinearHorizontal.contains("createLayoutLinearHorizontal"))
        assertTrue(FrogoRvConstant.Deprecated.isViewStaggeredGrid.contains("createLayoutStaggeredGrid"))
        assertTrue(FrogoRvConstant.Deprecated.isViewGrid.contains("createLayoutGrid"))
        assertTrue(FrogoRvConstant.Deprecated.injectAdapter.contains("injector method"))
    }
}
