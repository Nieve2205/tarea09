package com.example.recipesapp.ui.view

import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipesapp.data.model.Recipe
import com.example.recipesapp.ui.viewmodel.RecipeViewModel

// Composable
@Composable
fun RecipeScreen(viewModel: RecipeViewModel = viewModel()) {
    val recipes by viewModel.recipes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchRecipes()
    }

    LazyColumn {
        items(recipes) { recipe ->
            RecipeItem(recipe)
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = recipe.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = "Cuisine: ${recipe.cuisine}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Difficulty: ${recipe.difficulty}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Prep Time: ${recipe.prepTimeMinutes} minutes", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Cook Time: ${recipe.cookTimeMinutes} minutes", style = MaterialTheme.typography.bodyMedium)
        }
    }
}