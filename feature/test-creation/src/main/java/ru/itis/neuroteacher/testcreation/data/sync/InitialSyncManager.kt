package ru.itis.neuroteacher.testcreation.data.sync

import ru.itis.neuroteacher.testcreation.domain.repository.FirebaseQuizRepository
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InitialSyncManager @Inject constructor(
    private val firebaseRepo: FirebaseQuizRepository,
    private val localRepo: TestRepository
) {
    suspend fun syncAllData() {
        try {
            localRepo.loadRemoteTests()
            localRepo.loadRemoteResults()
        } catch (_: Exception) {
        }
    }
}