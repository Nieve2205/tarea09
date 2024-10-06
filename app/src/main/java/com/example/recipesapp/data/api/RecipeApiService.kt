package com.example.recipesapp.data.api

import com.example.recipesapp.data.model.RecipeResponse
import retrofit2.http.GET

interface RecipeApiService {
    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse
}
