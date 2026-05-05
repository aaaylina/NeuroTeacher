package ru.itis.neuroteacher.feature.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val testRepository: TestRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val uiState: StateFlow<HistoryUiState> = combine(
        _searchQuery,
        _searchQuery.flatMapLatest { query ->
            testRepository.getTestResultsFlow(query)
        }
    ) { query, results ->
        HistoryUiState(
            historyItems = results.map { result ->
                HistoryItem(
                    testId = result.testId,
                    resultId = result.id,
                    testTitle = result.testTitle,
                    date = formatDate(result.dateCompleted),
                    totalQuestions = result.totalQuestions,
                    correctAnswers = result.correctAnswers,
                    scorePercentage = result.scorePercentage.toInt()
                )
            },
            searchQuery = query
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HistoryUiState()
    )
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
    private fun formatDate(date: Date): String {
        return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
    }
}

data class HistoryUiState(
    val historyItems: List<HistoryItem> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
)

data class HistoryItem(
    val testId: Long,
    val resultId: Long,
    val testTitle: String,
    val date: String,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val scorePercentage: Int
)