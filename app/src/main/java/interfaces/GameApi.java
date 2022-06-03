package interfaces;

import com.moringaschool.thegamezone.GamesListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GameApi {
    @GET("/api/games")
    Call<GamesListResponse> getGames();
}
