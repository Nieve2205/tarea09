package com.example.recipesapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipesapp.ui.viewmodel.RecipeViewModel

@Composable
fun RecipeDetailScreen(viewModel: RecipeViewModel, recipeId: Int, navController: NavController) {
    val recipe = viewModel.getRecipeById(recipeId)

    recipe?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = it.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Cuisine: ${it.cuisine}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Difficulty: ${it.difficulty}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Prep Time: ${it.prepTimeMinutes} minutes", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Cook Time: ${it.cookTimeMinutes} minutes", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Ingredients:", style = MaterialTheme.typography.titleMedium)
            it.ingredients.forEach { ingredient ->
                Text("• $ingredient", style = MaterialTheme.typography.bodyMedium)
            }
            Text(text = "Instructions:", style = MaterialTheme.typography.titleMedium)
            it.instructions.forEachIndexed { index, instruction ->
                Text("${index + 1}. $instruction", style = MaterialTheme.typography.bodyMedium)
            }
        }
    } ?: Text("Recipe not found")
}
