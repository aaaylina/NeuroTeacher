package ru.itis.neuroteacher.domain.model

data class RecentTestItem(
    val title: String,
    val date: String,
    val scorePercentage: Int
)