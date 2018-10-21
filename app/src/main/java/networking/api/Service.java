package networking.api;

import com.google.gson.JsonArray;

import networking.Routes;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET(Routes.END_POINT)
    Call<JsonArray> fetchBakingData();

}
