package ru.itis.neuroteacher.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.domain.model.RecentTestItem
import ru.itis.neuroteacher.domain.usecase.GetAllRecentTestsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllRecentTestsUseCase: GetAllRecentTestsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadAllTests()
    }

    private fun loadAllTests() {
        viewModelScope.launch {
            val tests = getAllRecentTestsUseCase()
            _uiState.update { it.copy(recentTests = tests) }
        }
    }
}

data class HomeUiState(
    val recentTests: List<RecentTestItem> = emptyList()
)
