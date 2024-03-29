package com.gruenerfelix.bakingapp.bakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;

import com.gruenerfelix.bakingapp.bakingapp.data.RecipeContract;
import com.gruenerfelix.bakingapp.bakingapp.data.RecipeProvider;
import com.gruenerfelix.bakingapp.bakingapp.model.Recipe;
import com.gruenerfelix.bakingapp.bakingapp.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Random;

public class RecipeWidgetIntentService extends IntentService {

    public static final String ACTION_GET_INGREDIENTS = "com.gruenerfelix.bakinapp.action.get.ingredients";

    public static void startActionGetIngredients(Context context) {

        Intent intent = new Intent(context, RecipeWidgetIntentService.class);
        intent.setAction(ACTION_GET_INGREDIENTS);
        context.startService(intent);
    }


    public RecipeWidgetIntentService() {
        super("RecipeWidgetIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_INGREDIENTS.equals(action)) {
                handleGetIngredients();
            }
        }
    }

    private void handleGetIngredients() {

        Cursor recipeCursor = getContentResolver().query(
                RecipeProvider.Recipes.CONTENT_URI,
                null,
                null,
                null,
                RecipeContract.RecipeEntry.COLUMN_ID
        );


        ArrayList<Recipe> recipeList = JsonUtils.getRecipeListFromCursor(recipeCursor, getApplicationContext());

        Recipe randRecipe = recipeList.get(new Random().nextInt(recipeList.size()));

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidgetProvider.class));

        RecipeWidgetProvider.updateRecipeWidgets(this, appWidgetManager, randRecipe, appWidgetIds);
    }
}

