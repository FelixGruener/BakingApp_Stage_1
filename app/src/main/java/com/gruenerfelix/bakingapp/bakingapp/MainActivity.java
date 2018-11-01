package com.gruenerfelix.bakingapp.bakingapp;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.gruenerfelix.bakingapp.bakingapp.adapter.RecipeAdapter;
import com.gruenerfelix.bakingapp.bakingapp.model.Recipe;
import com.gruenerfelix.bakingapp.bakingapp.model.Ingredient;
import com.gruenerfelix.bakingapp.bakingapp.model.Step;
import com.gruenerfelix.bakingapp.bakingapp.sync.RecipeSyncUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import networking.api.Service;
import networking.generators.DataServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private List<Recipe> baking = new ArrayList<>();
    private Integer recipeId;


    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recycler_view.setHasFixedSize(true);

        if (isTablet(this)) {

            recycler_view.setLayoutManager(new GridLayoutManager(this, 3));

        } else {

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recycler_view.setLayoutManager(layoutManager);
        }

        RecipeSyncUtils.initialize(this);
        loadData();
    }

    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    private void loadData() {

        Service service = DataServiceGenerator.createService(Service.class);
        Call<List<Recipe>> call = service.fetchData();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        List<Recipe> bakingProcesses = response.body();
                        recycler_view.setItemAnimator(new DefaultItemAnimator());
                        recycler_view.setAdapter(new RecipeAdapter(getApplicationContext(), bakingProcesses));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }

    private static <T> List<T> getListFromJson(String jsonString, Type type) {
        if (!isValid(jsonString)) {
            return null;
        }
        return new Gson().fromJson(jsonString, type);
    }

    private static boolean isValid(String json) {
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonSyntaxException jse) {
            return false;
        }
    }
}
