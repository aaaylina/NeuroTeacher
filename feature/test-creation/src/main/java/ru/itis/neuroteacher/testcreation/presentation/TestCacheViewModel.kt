package ru.itis.neuroteacher.testcreation.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.itis.neuroteacher.testcreation.data.TestCache
import javax.inject.Inject

@HiltViewModel
class TestCacheViewModel @Inject constructor() : ViewModel() {
    val cache = TestCache()
}