package com.gruenerfelix.bakingapp.bakingapp.utils;

<<<<<<< HEAD

import java.io.IOException;
=======
import java.io.IOException;

>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

<<<<<<< HEAD


=======
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
public class NetworkUtils {

    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net";

    public static String getResponseFromHttpUrl() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        RecipeApi api = retrofit.create(RecipeApi.class);

        Call<String> call = api.getAllRecipes();
        return  call.execute().body();
    }
<<<<<<< HEAD
}

=======
}
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
