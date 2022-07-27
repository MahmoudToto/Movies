package com.example.movies.LocalDB.Interfaces

import androidx.room.*
import com.example.movies.Pojo.Favourite
import com.example.movies.Pojo.Movies.Movies
import com.example.movies.Pojo.Movies.Result
import com.example.movies.Pojo.MoviesId.MoviesID

@Dao
interface InfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<Result>)

    @Query("INSERT INTO favourite SELECT * FROM movies WHERE id = :id")
    fun insertMoviesFav(id: Int)

    @Query("SELECT * FROM movies")
    fun getMovies(): List<Result>

    @Query("SELECT * FROM favourite")
    fun getFavMovies(): MutableList<Favourite>

    @Query("DELETE FROM favourite WHERE id=:id")
    fun deleteMovieFavourite(id: Int)
















    @Query("SELECT * FROM favourite WHERE id LIKE :id")

    fun  search(id  : Int  ):Boolean










}