package me.dio.soccernews.ui.news;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.soccernews.data.remote.SoccerNewsAPI;
import me.dio.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR
    }

    private final MutableLiveData<List<News>> newsData = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();

    private final SoccerNewsAPI api;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dojak220.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SoccerNewsAPI.class);

        state.setValue(State.DOING);
        api.getNews().enqueue(handleCallback());
        state.setValue(State.DONE);
    }

    @NonNull
    private Callback<List<News>> handleCallback() {
        return new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                } else {
                    /// TODO: Create error handler
                    /// showErrorMessage();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                /// TODO: Create error handler
            }
        };
    }


    public LiveData<List<News>> getNews() {
        return newsData;
    }

    public LiveData<State> getState() {
        return state;
    }
}