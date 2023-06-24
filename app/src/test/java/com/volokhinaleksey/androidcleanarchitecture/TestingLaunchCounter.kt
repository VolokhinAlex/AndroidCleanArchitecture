package com.volokhinaleksey.androidcleanarchitecture

import com.google.common.truth.Truth.assertThat
import com.volokhinaleksey.androidcleanarchitecture.interactors.LaunchCounterInteractor
import com.volokhinaleksey.androidcleanarchitecture.interactors.LaunchCounterInteractorImpl
import com.volokhinaleksey.androidcleanarchitecture.models.DataLaunchCount
import com.volokhinaleksey.androidcleanarchitecture.repositories.LaunchCounterRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify

class TestingLaunchCounter {

    private lateinit var launchCounterInteractor: LaunchCounterInteractor

    @Mock
    private lateinit var launchCounterRepository: LaunchCounterRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        launchCounterInteractor = LaunchCounterInteractorImpl(repository = launchCounterRepository)
    }

    @Test
    fun `checking the correct saving of startup data`() {
        val dataForSave = mock<DataLaunchCount>()
        doNothing().`when`(launchCounterRepository).saveLaunchCount(launchCount = dataForSave)
        launchCounterInteractor.saveLaunchCount(dataLaunchCount = dataForSave)
        verify(launchCounterRepository, atLeastOnce()).saveLaunchCount(dataForSave)
    }

    @Test
    fun `checking the receipt of data on the number of app launches`() {
        `when`(launchCounterRepository.getLaunchCount()).thenReturn(DataLaunchCount(1))
        val result = launchCounterInteractor.getLaunchCount()
        assertThat(result.launchCount).isEqualTo(1)
    }

    @Test
    fun `checking the display of the evaluation window for the 2nd launch return true`() {
        val launchCount = 2L
        val result = launchCounterInteractor.isShowEvaluationWindow(launchCount)
        assertThat(result).isTrue()
    }

    @Test
    fun `checking the evaluation window display for every 4 launches return true`() {
        val launchCount = 6L
        val result = launchCounterInteractor.isShowEvaluationWindow(launchCount)
        assertThat(result).isTrue()
    }

    @Test
    fun `checking the display of the evaluation window for the 3rd launch return false`() {
        val launchCount = 3L
        val result = launchCounterInteractor.isShowEvaluationWindow(launchCount)
        assertThat(result).isFalse()
    }

}