package me.dio.soccernews.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.soccernews.data.SoccerNewsRepository;
import me.dio.soccernews.domain.News;

public class FavoritesViewModel extends ViewModel {


    public FavoritesViewModel() {}

    public LiveData<List<News>> loadFavoriteNews() {
        return SoccerNewsRepository.getInstance().getLocalDatabase().newsDao().loadAllFavorites();
    }
    public void saveNews(News news) {
        AsyncTask.execute(() ->  SoccerNewsRepository.getInstance().getLocalDatabase().newsDao().favorite(news));
    }


}