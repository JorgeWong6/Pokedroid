package com.mvp.demo.pokedroid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mvp.demo.pokedroid.R;
import com.mvp.demo.pokedroid.presenter.Presenter;
import com.mvp.demo.pokedroid.presenter.PresenterImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    Presenter presenter;
    @Inject
    GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        // Initialize
        recyclerView.setAdapter(presenter.getAdapter());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    presenter.updateData(
                            layoutManager.getChildCount(),
                            layoutManager.getItemCount(),
                            layoutManager.findFirstVisibleItemPosition()
                    );
                }
            }
        });

        if (PresenterImpl.offset == 0) {
            PresenterImpl.readyToLoad = true;
            presenter.fetchData(PresenterImpl.offset);
        }
    }
}
