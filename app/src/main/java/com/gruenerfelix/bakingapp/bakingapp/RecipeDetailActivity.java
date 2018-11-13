package com.gruenerfelix.bakingapp.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
=======
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
<<<<<<< HEAD
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gruenerfelix.bakingapp.bakingapp.fragment.RecipeDetailFragment;
import com.gruenerfelix.bakingapp.bakingapp.fragment.RecipeStepFragment;
import com.gruenerfelix.bakingapp.bakingapp.model.Recipe;
import com.gruenerfelix.bakingapp.bakingapp.model.Ingredient;
import com.gruenerfelix.bakingapp.bakingapp.model.Step;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static com.gruenerfelix.bakingapp.bakingapp.MainActivity.EXTRA_RECIPE;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.StepClickListener,
        RecipeStepFragment.StepActionListener {

    public static final String EXTRA_LIST_INDEX = "extra_list_index";
    public static final String EXTRA_STEP_LIST = "extra_step_list";
    public static final String EXTRA_RECIPE_NAME = "extra_recipe_name";

    @Nullable
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;

    @BindView(R.id.recipe_detail_content_view)
    FrameLayout container;

    private boolean isTwoPane;
    private Recipe recipe;
    private int selectedStepIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(EXTRA_RECIPE)) {
                recipe = intent.getParcelableExtra(EXTRA_RECIPE);
                selectedStepIndex = savedInstanceState != null && savedInstanceState.containsKey(EXTRA_LIST_INDEX) ?
                        savedInstanceState.getInt(EXTRA_LIST_INDEX) : 0;
            }
        } else if (savedInstanceState != null) {
            recipe = savedInstanceState.getParcelable(EXTRA_RECIPE);
            selectedStepIndex = savedInstanceState.getInt(EXTRA_LIST_INDEX);
        }

        if (recipe != null) {
            if (savedInstanceState == null) {
                replaceRecipeDetailFragment(recipe);
            }

            if (findViewById(R.id.recipe_step_content_view) != null) {
                isTwoPane = true;
                if (savedInstanceState == null) {
                    replaceRecipeStepFragment(selectedStepIndex);
                }
            } else {
                isTwoPane = false;
            }

            setTitle(recipe.getName());
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else {
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_LIST_INDEX, selectedStepIndex);
        outState.putParcelable(EXTRA_RECIPE, recipe);
    }

    @Override
    public void onStepClicked(int position) {
        selectedStepIndex = position;
        if (isTwoPane) {
            replaceRecipeStepFragment(position);
        } else {
            Intent intent = new Intent(this, RecipeStepActivity.class);
            intent.putExtra(EXTRA_LIST_INDEX, position);
            intent.putParcelableArrayListExtra(EXTRA_STEP_LIST, new ArrayList<Parcelable>(recipe.getSteps()));
            intent.putExtra(EXTRA_RECIPE_NAME, recipe.getName());
            startActivity(intent);
        }
    }

    @Override
    public void onNext() {
        if (selectedStepIndex < recipe.getSteps().size() - 1) {
            selectedStepIndex++;
            replaceRecipeStepFragment(selectedStepIndex);
        }
    }

    @Override
    public void onPrev() {
        if (selectedStepIndex > 0) {
            selectedStepIndex--;
            replaceRecipeStepFragment(selectedStepIndex);
        }
    }

    private void replaceRecipeDetailFragment(Recipe recipe) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setRecipe(recipe);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_detail_content_view, recipeDetailFragment)
                .commit();
    }

    private void replaceRecipeStepFragment(int position) {
        RecipeStepFragment recipeStepFragment = new RecipeStepFragment();
        recipeStepFragment.setListIndex(position);
        recipeStepFragment.setStep(recipe.getSteps().get(position));
        recipeStepFragment.isPrevEnabled(position > 0);
        recipeStepFragment.isNextEnabled(position < recipe.getSteps().size() - 1);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().
                replace(R.id.recipe_step_content_view, recipeStepFragment)
                .commit();
    }
=======
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

>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
}
