package com.gruenerfelix.bakingapp.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.gruenerfelix.bakingapp.bakingapp.fragment.RecipeStepFragment;

public class RecipeStepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.recipe_step);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("Steps", getIntent().getParcelableExtra("Steps") );

            RecipeStepFragment fragment = new RecipeStepFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }
}
