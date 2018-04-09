package com.mvp.demo.pokedroid.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.demo.pokedroid.R;
import com.mvp.demo.pokedroid.presenter.Presenter;
import com.mvp.demo.pokedroid.viewmodel.PokemonViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class PokemonFragment extends Fragment {
    // FOR DATA
    public static final String OFFSET = "offset";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    PokemonAdapter adapter;
    @Inject
    Presenter presenter;
    private GridLayoutManager layoutManager;

    public PokemonFragment() {
        // Required empty public constructor
    }

    public static PokemonFragment newInstance() {
        return new PokemonFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pokemon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        assert getArguments() != null;
        int offset = getArguments().getInt(OFFSET);

        final PokemonViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(PokemonViewModel.class);

        recyclerView.setAdapter(adapter);
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        presenter.setViewModel(viewModel);

        assert presenter != null;
        presenter.fetchData(offset);

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
    }
}
