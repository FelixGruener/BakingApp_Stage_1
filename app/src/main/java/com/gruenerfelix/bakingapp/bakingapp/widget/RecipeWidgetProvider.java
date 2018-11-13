package com.gruenerfelix.bakingapp.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.gruenerfelix.bakingapp.bakingapp.MainActivity;
import com.gruenerfelix.bakingapp.bakingapp.R;
<<<<<<< HEAD
import com.gruenerfelix.bakingapp.bakingapp.RecipeDetailActivity;
import com.gruenerfelix.bakingapp.bakingapp.model.Recipe;
import com.gruenerfelix.bakingapp.bakingapp.model.WidgetType;
import com.gruenerfelix.bakingapp.bakingapp.utils.PreferenceUtil;
=======
import com.gruenerfelix.bakingapp.bakingapp.model.Recipe;

import static com.gruenerfelix.bakingapp.bakingapp.widget.ListRemoteViewsFactory.WIDGET_RECIPE_ID;
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822

public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
<<<<<<< HEAD
                                int appWidgetId, WidgetType widgetType) {

        RemoteViews views;

        if (widgetType == WidgetType.INGREDIENTS) {
            views = getIngredientListRemoteView(context);
        } else if (widgetType == WidgetType.RECIPES) {
            views = getRecipeListRemoteView(context);
        } else {
            views = getEmptyRemoteView(context);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, WidgetType widgetType) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, widgetType);
=======
                                Recipe randRecipe, int appWidgetId) {

        RemoteViews rv = getListRemoteView(context, randRecipe);

        String widgetRecipeTitle;
        if (randRecipe != null) {
            String recipeNameText = randRecipe.getName();
            widgetRecipeTitle = recipeNameText + " " + context.getString(R.string.ingredients_title_text);
        } else {
            widgetRecipeTitle = context.getString(R.string.ingredients_title_text);
        }
        rv.setTextViewText(R.id.widget_recipe_title, widgetRecipeTitle);

        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }


    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager,
                                           Recipe randRecipe, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, randRecipe, appWidgetId);
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
<<<<<<< HEAD
        RecipeWidgetService.startActionUpdateWidgets(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static RemoteViews getIngredientListRemoteView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list_view);
        String recipeName = PreferenceUtil.getSelectedRecipeName(context);
        views.setTextViewText(R.id.title_text_view, context.getString(R.string.ingredient_of, recipeName));

        Intent intent = new Intent(context, IngredientListWidgetService.class);
        views.setRemoteAdapter(R.id.widget_list_view, intent);

        Intent appIntent = new Intent(context, RecipeDetailActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_list_view, appPendingIntent);

        views.setEmptyView(R.id.widget_list_view, R.id.empty_view);
        return views;
    }

    private static RemoteViews getRecipeListRemoteView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list_view);
        String title = context.getString(R.string.recipes);
        views.setTextViewText(R.id.title_text_view, title);

        Intent intent = new Intent(context, RecipeListWidgetService.class);
        views.setRemoteAdapter(R.id.widget_list_view, intent);

        Intent appIntent = new Intent(context, RecipeDetailActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_list_view, appPendingIntent);

        views.setEmptyView(R.id.widget_list_view, R.id.empty_view);
        return views;
    }

    private static RemoteViews getEmptyRemoteView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.empty_widget_view);

        Intent appIntent = new Intent(context, MainActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.empty_widget_image_view, appPendingIntent);
        views.setOnClickPendingIntent(R.id.empty_widget_image_view, appPendingIntent);

        return views;
    }
=======
        RecipeWidgetIntentService.startActionGetIngredients(context);
    }

    private static RemoteViews getListRemoteView(Context context, Recipe randRecipe) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredients_list);

        Intent intent = new Intent(context, ListWidgetService.class);

        intent.putExtra(WIDGET_RECIPE_ID, randRecipe.getId());
        // When intents are compared, the extras are ignored, so we need to embed the extras
        // into the data so that the extras will not be ignored.
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        views.setRemoteAdapter(R.id.widget_list, intent);

        Intent appIntent = new Intent(context, MainActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_list, appPendingIntent);

        views.setEmptyView(R.id.widget_list, R.id.widget_empty);
        return views;
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
}
