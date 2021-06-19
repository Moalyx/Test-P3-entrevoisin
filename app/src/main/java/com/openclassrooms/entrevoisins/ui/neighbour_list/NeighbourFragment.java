package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.ui.neighbour_detail.DetailActivity;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.ClickOnNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbour;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    public static final String fragment_key = "position";
    private int position;

    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(int position) {
        Bundle arguments = new Bundle();
        arguments.putInt(fragment_key, position);
        NeighbourFragment fragment = new NeighbourFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        position = getArguments().getInt(fragment_key);
        initList();
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        if (position == 0) {
            List<Neighbour> neighbours = mApiService.getNeighbours();
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(neighbours));
        } else {
            List<Neighbour> favoriteNeighbours = mApiService.getFavorites();
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(favoriteNeighbours));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        if (position == 0) {
            mApiService.deleteNeighbour(event.neighbour);
        }
        initList();
    }

    /**
     * fired if the user clicks on a neighbour item
     *
     * @param event
     */
    @Subscribe
    public void onClickOnNeighbour(ClickOnNeighbourEvent event) {
        Intent intent = new Intent(mRecyclerView.getContext(), DetailActivity.class);
        intent.putExtra("neighbour", event.neighbour);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * fired if the user clicks on a neighbour item
     *
     * @param event
     */
    @Subscribe
    public void onDeleteFavoriteNeighbour(DeleteFavoriteNeighbour event) {
        if (position == 1) {
            mApiService.deleteFavoriteNeighbour(event.neighbour);
        }
        initList();
    }
}
