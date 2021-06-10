package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    private static final List<Neighbour> favorites = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        favorites.remove(neighbour);
    }

    @Override
    public List<Neighbour> getFavorites() {
        return favorites;
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        favorites.remove(neighbour);
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        if (!favorites.contains(neighbour))
        {
            favorites.add(neighbour);
        }
    }
}
