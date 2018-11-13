package com.gruenerfelix.bakingapp.bakingapp.sync;

import android.app.IntentService;
import android.content.Intent;

<<<<<<< HEAD


=======
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
public class RecipeIntentService extends IntentService {

    public RecipeIntentService() {
        super("RecipeIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        RecipeSyncTask.syncRecipe(this);
    }
}