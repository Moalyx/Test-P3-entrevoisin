package com.openclassrooms.entrevoisins;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.AddNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {



    @BindView(R.id.name_image_detail)
    TextView nameImageDetail;
    @BindView(R.id.imageViewDetail)
    ImageView imageViewDetail;
    @BindView(R.id.namedetail)
    TextView nameDetail;
    @BindView(R.id.adressDetail)
    TextView adressDetail;
    @BindView(R.id.numberDetail)
    TextView numberDetail;
    @BindView(R.id.fbDetail)
    TextView fbdetail;
    @BindView(R.id.hobbyDetail)
    TextView hobbyDetail;
    @BindView(R.id.faButton)
    FloatingActionButton faButton;
    @BindView(R.id.faReturnButton)
    FloatingActionButton faReturnButton;

    private NeighbourApiService mApiService;
    private List<Neighbour> favoriteNeighbours;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getIncomingIntent();

        faReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this , ListNeighbourActivity.class );
                startActivity(intent);
            }
        });

        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
    }



    private void getIncomingIntent() {
        if (getIntent().hasExtra("neighbour")) {
            Neighbour neighbour = getIntent().getParcelableExtra("neighbour");
            setImage(neighbour.getAvatarUrl());
            nameDetail.setText(neighbour.getName());
            adressDetail.setText(neighbour.getAddress());
            numberDetail.setText(neighbour.getPhoneNumber());
            hobbyDetail.setText(neighbour.getAboutMe());
            fbdetail.setText("www.facebook.fr/" + neighbour.getName());
            nameImageDetail.setText(neighbour.getName());
        }
    }

    private void setImage(String image) {
        Glide.with(this)
                .load(image)
                .into(imageViewDetail);
    }


}