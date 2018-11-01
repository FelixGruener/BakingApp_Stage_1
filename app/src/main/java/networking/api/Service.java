package networking.api;

import com.gruenerfelix.bakingapp.bakingapp.model.Recipe;

import java.util.List;

import networking.Routes;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET(Routes.END_POINT)
    Call<List<Recipe>> fetchData();

}
