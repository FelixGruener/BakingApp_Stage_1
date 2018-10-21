package com.gruenerfelix.bakingapp.bakingapp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.gruenerfelix.bakingapp.bakingapp.database.AppDatabase;
import com.gruenerfelix.bakingapp.bakingapp.database.Ingredients;
import com.gruenerfelix.bakingapp.bakingapp.database.RecipeDao;
import com.gruenerfelix.bakingapp.bakingapp.database.Recipes;
import com.gruenerfelix.bakingapp.bakingapp.database.Steps;

public class RecipeContentProvider extends ContentProvider {




      /* The authority of this content provider. */
    public static final String AUTHORITY = "com.gruenerfelix.bakingapp.bakingapp.provider";

    /* The URI for the Recipe table. */
    public static final Uri URI_RECIPE = Uri.parse(
            "content://" + AUTHORITY + "/" + Recipes.TABLE_NAME);

    /* The URI for the Ingredient table. */
    public static final Uri URI_INGREDIENTS = Uri.parse(
            "content://" + AUTHORITY + "/" + Ingredients.TABLE_NAME);

    /* The URI for the Steps table. */
    public static final Uri URI_STEPS = Uri.parse(
            "content://" + AUTHORITY + "/" + Steps.TABLE_NAME);

    /* The match code for some items in the Menu table. */
    private static final int CODE_RECIPES_DIR = 1;

    /* The match code for an item in the Menu table. */
    private static final int CODE_RECIPES_ITEM = 2;

    /* The match code for some items in the Menu table. */
    private static final int CODE_INGREDIENTS_DIR = 3;

    /* The match code for an item in the Menu table. */
    private static final int CODE_INGREDIENTS_ITEM = 4;

    /* The match code for some items in the Menu table. */
    private static final int CODE_STEPS_DIR = 5;

    /* The match code for an item in the Menu table. */
    private static final int CODE_STEPS_ITEM = 6;

    /* The URI matcher. */
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, Recipes.TABLE_NAME, CODE_RECIPES_DIR);
        MATCHER.addURI(AUTHORITY, Recipes.TABLE_NAME + "/*", CODE_RECIPES_ITEM);
        MATCHER.addURI(AUTHORITY, Ingredients.TABLE_NAME, CODE_INGREDIENTS_DIR);
        MATCHER.addURI(AUTHORITY, Ingredients.TABLE_NAME + "/*", CODE_INGREDIENTS_ITEM);
        MATCHER.addURI(AUTHORITY, Steps.TABLE_NAME, CODE_STEPS_DIR);
        MATCHER.addURI(AUTHORITY, Steps.TABLE_NAME + "/*", CODE_STEPS_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);
        if (code == CODE_RECIPES_DIR || code == CODE_RECIPES_ITEM) {
            final Context context = getContext();
            if (context == null) {
                return null;
            }
            RecipeDao recipe = AppDatabase.getInstance(context).recipeDao();
            final Cursor cursor;
            if (code == CODE_RECIPES_DIR) {
                cursor = recipe.selectAllRecipes();
            } else {
                cursor = recipe.selectByIdRecipes(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        } else if (code == CODE_INGREDIENTS_DIR || code == CODE_INGREDIENTS_ITEM){

            final Context context = getContext();
            if (context == null) {
                return null;
            }
            RecipeDao recipe = AppDatabase.getInstance(context).recipeDao();
            final Cursor cursor;
            if (code == CODE_INGREDIENTS_DIR) {
                cursor = recipe.selectAllIngredients();
            } else {
                cursor = recipe.selectByIdIngredients(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;

        } else if (code == CODE_STEPS_DIR || code == CODE_STEPS_ITEM){

            final Context context = getContext();
            if (context == null) {
                return null;
            }
            RecipeDao recipe = AppDatabase.getInstance(context).recipeDao();
            final Cursor cursor;
            if (code == CODE_STEPS_DIR) {
                cursor = recipe.selectAllSteps();
            } else {
                cursor = recipe.selectByIdSteps(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
