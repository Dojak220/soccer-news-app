package me.dio.soccernews.ui.news;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.soccernews.data.SoccerNewsRepository;
import me.dio.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR
    }

    private final MutableLiveData<List<News>> newsData = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();


    public NewsViewModel() {
        state.setValue(State.DOING);
        SoccerNewsRepository.getInstance().getRemoteApi().getNews().enqueue(handleCallback());
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
                    state.setValue(State.ERROR);
                    /// TODO: Create error handler
                    /// showErrorMessage();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                state.setValue(State.ERROR);
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