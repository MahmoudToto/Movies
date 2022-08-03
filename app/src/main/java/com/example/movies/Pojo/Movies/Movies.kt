package com.example.movies.Pojo.Movies


data class Movies(

    val page: Int,

    val results: List<Result> = emptyList(),
    val total_pages: Int,
        val total_results: Int
)