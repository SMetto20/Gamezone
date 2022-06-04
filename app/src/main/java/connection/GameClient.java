package connection;

import com.moringaschool.thegamezone.Constants;

import java.io.IOException;

import interfaces.GameApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameClient {
    private static Retrofit retrofit = null;

    public static GameApi getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.GAME_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit.create(GameApi.class);
    }
}
