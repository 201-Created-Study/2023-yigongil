package com.created.team201.presentation.studyThread

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.created.domain.model.Feeds
import com.created.domain.model.MustDo
import com.created.domain.repository.ThreadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThreadViewModel @Inject constructor(
    private val threadRepository: ThreadRepository
) : ViewModel() {

    private val studyId: MutableStateFlow<Long> = MutableStateFlow(0)

    private val _uiState: MutableStateFlow<ThreadUiState> = MutableStateFlow(ThreadUiState.Loading)
    val uiState: StateFlow<ThreadUiState> get() = _uiState.asStateFlow()

    init {
        updateFeeds()
        updateMustDoCertification()
    }

    fun updateStudyId(id: Long) {
        studyId.value = id
    }

    fun updateMustDoCertification() {
        viewModelScope.launch {
            threadRepository.getMustDoCertification(studyId.value).collectLatest { mustDo ->
                _uiState.value = ThreadUiState.Success().copy(mustDo = mustDo)
            }
        }
    }

    fun dispatchFeed(message: String) {
        viewModelScope.launch {
            runCatching { threadRepository.updateFeeds(studyId.value, message, null) }
                .onFailure {
                    // error 메시지
                }
        }
    }

    fun updateFeeds() {
        viewModelScope.launch {
            threadRepository.getFeeds(studyId.value).collectLatest {
                _uiState.value = ThreadUiState.Success().copy(feeds = it)
            }
        }
    }
}

sealed interface ThreadUiState {
    data class Success(
        val feeds: List<Feeds> = emptyList(),
        val mustDo: List<MustDo> = emptyList()
    ) : ThreadUiState

    object Loading : ThreadUiState
}