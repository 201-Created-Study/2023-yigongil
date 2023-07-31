package com.created.team201.presentation.createStudy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.created.domain.model.CreateStudy
import com.created.domain.model.Period
import com.created.domain.repository.CreateStudyRepository
import com.created.team201.presentation.createStudy.model.CreateStudyUiModel
import com.created.team201.presentation.createStudy.model.PeriodUiModel
import com.created.team201.presentation.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreateStudyViewModelTest {
    private lateinit var repository: CreateStudyRepository
    private lateinit var viewModel: CreateStudyViewModel

    // LiveData 테스트를 위한 룰 선언
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    @ExperimentalCoroutinesApi
    fun setupCoroutine() {
        // Dispatcher 상태를 Unconfined 로 변경
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Before
    fun setupViewModel() {
        repository = mockk()
        viewModel = CreateStudyViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `스터디 정보를 갖고 스터디를 개설할 수 있다`() {
        // given
        coEvery { repository.createStudy(CreateStudyFixture.study) } returns Result.success(Unit)

        // when
        viewModel.createStudy(CreateStudyFixture.study.toUiModel())

        // then
        viewModel.isSuccessCreateStudy.getOrAwaitValue()
        assertEquals(true, viewModel.isSuccessCreateStudy.value)
    }

    @Test
    fun `스터디의 필수 정보인 이름, 인원, 시작일, 주기, 회차, 소개가 모두 작성되면 작성 완료 버튼이 활성화 된다`() {
        // given
        viewModel.setName("자바 스터디")
        viewModel.setPeopleCount(3)
        viewModel.setStartDate("2023.07.30")
        viewModel.setPeriod(30)
        viewModel.setCycle(2, 0)
        viewModel.setIntroduction("안녕하세요")

        // when

        // then
        viewModel.isEnableCreateStudy.getOrAwaitValue()
        assertEquals(true, viewModel.isEnableCreateStudy.value)
    }

    private fun CreateStudy.toUiModel(): CreateStudyUiModel =
        CreateStudyUiModel(name, peopleCount, startDate, period, cycle.toUiModel(), introduction)

    private fun Period.toUiModel(): PeriodUiModel =
        PeriodUiModel(date, unit)
}
