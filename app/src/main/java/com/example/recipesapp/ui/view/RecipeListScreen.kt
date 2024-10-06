package com.example.recipesapp.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipesapp.data.model.Recipe
import com.example.recipesapp.ui.viewmodel.RecipeViewModel

@Composable
fun RecipeListScreen(viewModel: RecipeViewModel, navController: NavController) {
    val recipes by viewModel.recipes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchRecipes()
    }

    LazyColumn {
        items(recipes) { recipe ->
            RecipeListItem(recipe, navController)
        }
    }
}

@Composable
fun RecipeListItem(recipe: Recipe, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("recipeDetail/${recipe.id}") },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )
    }
}