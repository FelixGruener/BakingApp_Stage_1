package com.gruenerfelix.bakingapp.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gruenerfelix.bakingapp.bakingapp.fragment.RecipeStepFragment;
import com.gruenerfelix.bakingapp.bakingapp.model.Recipe;
import com.gruenerfelix.bakingapp.bakingapp.model.Ingredient;
import com.gruenerfelix.bakingapp.bakingapp.model.Step;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class RecipeDetailActivity extends AppCompatActivity {

    private boolean mTwoPane;
    Recipe recipe;
    String recipeName;
    List<Ingredient> recipeIngredient;
    List<Step> recipeStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("Recipe")) {

            recipe = getIntent().getParcelableExtra("Recipe");
            recipeIngredient = recipe.getIngredients();
            recipeStep = recipe.getSteps();
            recipeName = recipe.getName();

            setTitle(recipeName);

        }else{
            Toast.makeText(this, "Data not available", Toast.LENGTH_SHORT).show();
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration decoration = new DividerItemDecoration(this, VERTICAL);

        MultiSnapRecyclerView recycler_view = (MultiSnapRecyclerView) findViewById(R.id.ingredient_recyclerview);
        MultiSnapRecyclerView recyclerView = (MultiSnapRecyclerView) findViewById(R.id.steps_recyclerview);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new StepAdapter(getApplicationContext(), recipeStep));
        recyclerView.addItemDecoration(decoration);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(new IngredientAdapter(getApplicationContext(), recipeIngredient));
        recycler_view.addItemDecoration(decoration);

        if (findViewById(R.id.item_detail_container) != null) {

            mTwoPane = true;
        }
    }

    public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {

        private Context mContext;
        private List<Ingredient> ingredientList;


        public IngredientAdapter(Context mContext, List<Ingredient> ingredientList){
            this.mContext = mContext;
            this.ingredientList = ingredientList;
        }

        @Override
        public IngredientAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.recipe_detail_ingredient_item, viewGroup, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final IngredientAdapter.MyViewHolder viewHolder, int i){

            viewHolder.ingredient.setText(ingredientList.get(i).getIngredient());


            String quantity = Double.toString(ingredientList.get(i).getQuantity());

            String recipeIngredient = quantity + " " + ingredientList.get(i).getMeasure() + " " + ingredientList.get(i).getIngredient();
            viewHolder.ingredient.setText(recipeIngredient);

        }

        @Override
        public int getItemCount(){
            return ingredientList.size();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView ingredient, recipe;



            public MyViewHolder(View view){

                super(view);
                ingredient = (TextView) view.findViewById(R.id.recipeIngred);



                view.setOnClickListener(v -> {

                });
            }
        }
    }

    public class StepAdapter extends RecyclerView.Adapter<StepAdapter.MyViewHolder> {

        private Context context;
        private List<Step> recipeStep;


        public StepAdapter(Context mContext, List<Step> recipeStep){
            this.context = mContext;
            this.recipeStep = recipeStep;
        }

        @Override
        public StepAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.recipe_detail_step_item, viewGroup, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder viewHolder, int i){

            viewHolder.shortDesc.setText(recipeStep.get(i).getShortDescription());

        }

        @Override
        public int getItemCount(){
            return recipeStep.size();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView shortDesc, desc, videoUrl;
            public final View mView;



            public MyViewHolder(final View view){

                super(view);
                mView = view;
                shortDesc = (TextView) view.findViewById(R.id.short_description);
                desc = (TextView) view.findViewById(R.id.description);
                videoUrl = (TextView) view.findViewById(R.id.video_url);

                view.setOnClickListener(v -> {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mTwoPane) {
                            Step clickedDataItem = recipeStep.get(pos);
                            Bundle arguments = new Bundle();
                            arguments.putParcelable("Steps", clickedDataItem);
                            RecipeStepFragment fragment = new RecipeStepFragment();
                            fragment.setArguments(arguments);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.item_detail_container, fragment)
                                    .commit();
                        } else {
                            Step clickedDataItem = recipeStep.get(pos);
                            Intent intent = new Intent(context, RecipeStepActivity.class);
                            intent.putExtra("Steps", clickedDataItem);
                            context.startActivity(intent);
                        }
                    }

                });
            }
        }
    }

}
