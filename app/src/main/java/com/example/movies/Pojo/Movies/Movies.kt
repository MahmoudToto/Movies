package com.example.movies.Pojo.Movies

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Movies(

    val page: Int,

    val results: List<Result> = emptyList(),
    val total_pages: Int,
    val total_results: Int
)