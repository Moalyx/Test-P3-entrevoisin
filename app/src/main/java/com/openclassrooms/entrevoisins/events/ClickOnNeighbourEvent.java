package com.openclassrooms.entrevoisins.events;

import android.content.Intent;
import android.view.View;

import com.openclassrooms.entrevoisins.DetailActivity;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class ClickOnNeighbourEvent {

    public Neighbour neighbour;


    public ClickOnNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;

    }


}
