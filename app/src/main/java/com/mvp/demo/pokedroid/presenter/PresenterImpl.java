package com.mvp.demo.pokedroid.presenter;

/**
 * Created by jatempa on 10/23/17.
 */

public class PresenterImpl implements Presenter {
    public static int offset = 0;
    public static boolean readyToLoad = false;

    @Override
    public void fetchData(int offset) {

    }

    @Override
    public void updateData(int visibleItemCount, int totalItemCount, int pastVisibleItems) {
        if (readyToLoad) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {

                fetchData(offset);
            }
        }
    }
}