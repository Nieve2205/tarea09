package com.example.recipesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.api.RecipeApiService
import com.example.recipesapp.data.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeViewModel : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(RecipeApiService::class.java)

    fun fetchRecipes() {
        viewModelScope.launch {
            try {
                val response = apiService.getRecipes()
                _recipes.value = response.recipes
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun getRecipeById(id: Int): Recipe? {
        return recipes.value.find { it.id == id }
    }
}
