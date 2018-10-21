package com.gruenerfelix.bakingapp.bakingapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.gruenerfelix.bakingapp.bakingapp.adapter.RecipeAdapter;
import com.gruenerfelix.bakingapp.bakingapp.model.BakingProcess;

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

    private List<BakingProcess> baking = new ArrayList<>();

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

        loadData();
    }

    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    private void loadData() {
        Service service = DataServiceGenerator.createService(Service.class);
        Call<JsonArray> call = service.fetchBakingData();
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String listString = response.body().toString();

                        Type listType = new TypeToken<List<BakingProcess>>() {
                        }.getType();
                        baking = getListFromJson(listString, listType);

                        recycler_view.setItemAnimator(new DefaultItemAnimator());
                        recycler_view.setAdapter(new RecipeAdapter(getApplicationContext(), baking));
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

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
