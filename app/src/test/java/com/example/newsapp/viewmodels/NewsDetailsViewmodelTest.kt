package com.example.newsapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsapp.repository.NewsdetailsRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NewsDetailsViewmodelTest {

    private var viewmodel: NewsDetailsViewmodel? = null

    @MockK
    private lateinit var mainRepository: NewsdetailsRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewmodel = NewsDetailsViewmodel(mainRepository)
        print("")
    }

    @Test
    fun test_article_id_from_article_url() {
        val url = "https://www.theverge.com/2020/7/21/21332300/nikon-z5-full-frame-mirrorless-camera-price-release-date-specs/index.html"
        val expectedId = "www.theverge.com-2020-7-21-21332300-nikon-z5-full-frame-mirrorless-camera-price-release-date-specs-index.html"
        assertEquals(expectedId, viewmodel?.getUrlId(url))
    }
}