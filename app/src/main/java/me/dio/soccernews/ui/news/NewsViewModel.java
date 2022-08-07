package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> newsData;

    public NewsViewModel() {
        newsData = new MutableLiveData<>();

        /// TODO: Remove news mock
        List<News> newsList = new ArrayList<>();
        newsList.add(new News(
                "Abelhas Rainhas iniciam treinos",
                "Time picoense de futebol feminino iniciam treino e técnico mostra-se confiante."
        ));
        newsList.add(new News(
                "Abelhas Rainhas ganham primeiro jogo",
                "Time picoense vence partida contra Teresina por 2 a 1."
        ));
        newsList.add(new News(
                "Abelhas Rainhas passam para a semifinal",
                "O time passa para a semifinal do campeonato piauiense de futebol depois de vários jogos acirrados."
        ));

        newsData.setValue(newsList);
    }

    public LiveData<List<News>> getNews() {
        return newsData;
    }
}