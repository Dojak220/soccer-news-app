package me.dio.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import me.dio.soccernews.R;
import me.dio.soccernews.databinding.FragmentNewsBinding;
import me.dio.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel newsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));

        observeNews();
        observeStates();

        binding.srlMatches.setOnRefreshListener(newsViewModel::findNews);

        return binding.getRoot();
    }

    private void observeNews() {
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news ->
                binding.rvNews.setAdapter(new NewsAdapter(news, newsViewModel::saveNews))
        );
    }

    private void observeStates() {
        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    binding.srlMatches.setRefreshing(true);
                    break;
                case DONE:
                    binding.srlMatches.setRefreshing(false);
                    break;
                case ERROR:
                    binding.srlMatches.setRefreshing(false);
                    showErrorMessage();
            }
        });
    }

    private void showErrorMessage() {
        Snackbar.make(binding.srlMatches, R.string.error_api, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}