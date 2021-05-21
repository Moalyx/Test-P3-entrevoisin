package com.openclassrooms.entrevoisins.events;

import android.content.Intent;
import android.view.View;

import com.openclassrooms.entrevoisins.DetailActivity;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class ClickOnNeighbourEvent {

    public Neighbour neighbour;

    public ClickOnNeighbourEvent(Neighbour neighbour, View view){
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra("neighbour", neighbour);
        view.getContext().startActivity(intent);

    }


}
