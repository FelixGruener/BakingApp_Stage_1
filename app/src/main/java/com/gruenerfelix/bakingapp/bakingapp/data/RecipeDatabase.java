package com.gruenerfelix.bakingapp.bakingapp.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

<<<<<<< HEAD

=======
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
@Database(version = RecipeDatabase.VERSION)
public class RecipeDatabase {

    public static final int VERSION = 1;

    @Table(RecipeContract.IngredientEntry.class)
    public static final String RECIPE_INGREDIENTS = "ingredients";

    @Table(RecipeContract.StepEntry.class)
    public static final String RECIPE_STEPS = "steps";

    @Table(RecipeContract.RecipeEntry.class)
    public static final String RECIPES = "recipes";
}