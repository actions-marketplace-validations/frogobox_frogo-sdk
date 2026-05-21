package com.frogobox.coresdk.source

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ResourceTest {

    @Test
    fun testResourceSuccess() {
        val data = "Sample Data"
        val resource = Resource.Success(data)
        assertTrue(resource is Resource.Success)
        assertEquals(data, resource.result)
    }

    @Test
    fun testResourceError() {
        val resource = Resource.Error<String>(404, "Not Found")
        assertTrue(resource is Resource.Error)
        assertEquals(404, resource.code)
        assertEquals("Not Found", resource.message)
    }

    @Test
    fun testResourceLoading() {
        val resource = Resource.Loading<String>()
        assertTrue(resource is Resource.Loading)
    }

    @Test
    fun testResourceStateSuccess() {
        val state = ResourceState.Success()
        assertTrue(state is ResourceState.Success)
    }

    @Test
    fun testResourceStateError() {
        val state = ResourceState.Error(500, "Internal Server Error")
        assertTrue(state is ResourceState.Error)
        assertEquals(500, state.code)
        assertEquals("Internal Server Error", state.message)
    }

    @Test
    fun testResourceStateLoading() {
        val state = ResourceState.Loading()
        assertTrue(state is ResourceState.Loading)
    }

    @Test
    fun testDownloadResourceSuccess() {
        val localPath = "/downloads/file.zip"
        val download = DownloadResource.Success(localPath)
        assertTrue(download is DownloadResource.Success)
        assertEquals(localPath, download.localPath)
    }

    @Test
    fun testDownloadResourceLoading() {
        val progress = 75
        val download = DownloadResource.Loading(progress)
        assertTrue(download is DownloadResource.Loading)
        assertEquals(progress, download.progress)
    }

    @Test
    fun testDownloadResourceError() {
        val errorMessage = "Disk Full"
        val download = DownloadResource.Error(errorMessage)
        assertTrue(download is DownloadResource.Error)
        assertEquals(errorMessage, download.message)
    }
}
