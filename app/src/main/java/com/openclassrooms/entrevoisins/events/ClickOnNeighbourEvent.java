package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class ClickOnNeighbourEvent {

    public Neighbour neighbour;

    public ClickOnNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
