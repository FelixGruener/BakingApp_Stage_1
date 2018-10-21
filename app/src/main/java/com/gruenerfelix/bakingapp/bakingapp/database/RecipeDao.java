package com.gruenerfelix.bakingapp.bakingapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;


@Dao
public interface RecipeDao {

    //For Recipe
    @Query("SELECT COUNT (*) FROM  recipes")
    int countRecipes();

    @Insert
    long insert(Recipes recipe);

    @Insert
    long[] insertAll(Recipes[] recipe);

    @Query("SELECT * FROM  recipes")
    Cursor selectAllRecipes();

    @Query("SELECT * FROM recipes  WHERE id  = :id")
    Cursor selectByIdRecipes(long id);

    @Query("DELETE FROM recipes")
    void deleteRecipes();

    @Update
    int updateRecipes(Recipes recipe);

    // For Ingredients
    @Query("SELECT COUNT (*) FROM  ingredients")
    int countIngredients();

    @Insert
    long insert(Ingredients ingredients);

    @Insert
    long[] insertAll(Ingredients[] ingredients);

    @Query("SELECT * FROM  ingredients")
    Cursor selectAllIngredients();

    @Query("SELECT * FROM ingredients  WHERE id  = :id")
    Cursor selectByIdIngredients(long id);

    @Query("DELETE FROM ingredients")
    void deleteIngredients();

    @Update
    int updateIngredients(Ingredients ingredients);

    //For Steps
    @Query("SELECT COUNT (*) FROM  steps")
    int countSteps();

    @Insert
    long insert(Steps steps);

    @Insert
    long[] insertAll(Steps[] steps);

    @Query("SELECT * FROM  steps")
    Cursor selectAllSteps();

    @Query("SELECT * FROM steps  WHERE id  = :id")
    Cursor selectByIdSteps(long id);

    @Query("DELETE FROM steps")
    void deleteSteps();

    @Update
    int updateSteps(Steps steps);

}
