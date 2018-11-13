package com.gruenerfelix.bakingapp.bakingapp.sync;

import android.content.ContentResolver;
import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.gruenerfelix.bakingapp.bakingapp.data.RecipeProvider;
import com.gruenerfelix.bakingapp.bakingapp.model.Recipe;
import com.gruenerfelix.bakingapp.bakingapp.model.RecipeContentValues;
import com.gruenerfelix.bakingapp.bakingapp.utils.JsonUtils;
import com.gruenerfelix.bakingapp.bakingapp.utils.NetworkUtils;

<<<<<<< HEAD

import java.lang.reflect.Type;
import java.util.List;


=======
import java.lang.reflect.Type;
import java.util.List;

>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
public class RecipeSyncTask {

    synchronized public static void syncRecipe(Context context) {

        try {

            String jsonRecipeResponse = NetworkUtils
                    .getResponseFromHttpUrl();

            Type listType = new TypeToken<List<Recipe>>() {
            }.getType();

            List<Recipe> recipes = JsonUtils.getRecipeListFromJson(jsonRecipeResponse, listType);

            RecipeContentValues values = JsonUtils
                    .getRecipeContentValues(recipes);

            if (values.getRecipes() != null && values.getRecipes().length != 0) {
                ContentResolver recipeContentResolver = context.getContentResolver();

                recipeContentResolver.delete(
                        RecipeProvider.Recipes.CONTENT_URI,
                        null,
                        null);
<<<<<<< HEAD

=======
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
                recipeContentResolver.delete(
                        RecipeProvider.RecipeIngredients.CONTENT_URI,
                        null,
                        null);
                recipeContentResolver.delete(
                        RecipeProvider.RecipeSteps.CONTENT_URI,
                        null,
                        null);
<<<<<<< HEAD

=======
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
                recipeContentResolver.bulkInsert(
                        RecipeProvider.Recipes.CONTENT_URI,
                        values.getRecipes());
                recipeContentResolver.bulkInsert(
                        RecipeProvider.RecipeIngredients.CONTENT_URI,
                        values.getIngredients());
                recipeContentResolver.bulkInsert(
                        RecipeProvider.RecipeSteps.CONTENT_URI,
                        values.getSteps());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
