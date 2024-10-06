package com.example.recipesapp.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipesapp.ui.view.RecipeDetailScreen
import com.example.recipesapp.ui.view.RecipeListScreen
import com.example.recipesapp.ui.viewmodel.RecipeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeApp()
        }
    }
}

@Composable
fun RecipeApp() {
    val navController = rememberNavController()
    val viewModel: RecipeViewModel = viewModel()

    NavHost(navController, startDestination = "recipeList") {
        composable("recipeList") {
            RecipeListScreen(viewModel, navController)
        }
        composable(
            "recipeDetail/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: return@composable
            RecipeDetailScreen(viewModel, recipeId, navController)
        }
    }
}