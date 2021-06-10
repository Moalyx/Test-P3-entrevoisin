package com.openclassrooms.entrevoisins;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.name_image_detail)
    TextView nameImageDetail;
    @BindView(R.id.imageViewDetail)
    ImageView imageViewDetail;
    @BindView(R.id.nameDetailTextView)
    TextView nameDetail;
    @BindView(R.id.addressDetailTextView)
    TextView adressDetail;
    @BindView(R.id.numberDetailTextView)
    TextView numberDetail;
    @BindView(R.id.fbDetailTextView)
    TextView fbDetail;
    @BindView(R.id.hobbyDetail)
    TextView hobbyDetail;
    @BindView(R.id.faButton)
    FloatingActionButton faButton;
    @BindView(R.id.returnButton)
    ImageButton returnButton;

    private NeighbourApiService mApiService;
    private Neighbour neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getIncomingIntent();
        mApiService = DI.getNeighbourApiService();

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNeighbour();
            }
        });
    }


    private void getIncomingIntent() {
        if (getIntent().hasExtra("neighbour")) {
            neighbour = getIntent().getParcelableExtra("neighbour");
            nameDetail.setText(neighbour.getName());
            adressDetail.setText(neighbour.getAddress());
            numberDetail.setText(neighbour.getPhoneNumber());
            hobbyDetail.setText(neighbour.getAboutMe());
            fbDetail.setText("www.facebook.fr/" + neighbour.getName());
            nameImageDetail.setText(neighbour.getName());
            Glide.with(this)
                    .load(neighbour.getAvatarUrl())
                    .into(imageViewDetail);
            if (neighbour.isFavorite()) {
                faButton.setImageResource(R.drawable.ic_baseline_star_24);
            } else {
                faButton.setImageResource(R.drawable.ic_baseline_star_24_grey);
            }
        }
    }

    private void handleNeighbour() {
        if (!neighbour.isFavorite()) {
            neighbour.setFavorite(true);
            faButton.setImageResource(R.drawable.ic_baseline_star_24);
            Toast.makeText(DetailActivity.this, "Added on favorite", Toast.LENGTH_SHORT).show();
            mApiService.addFavoriteNeighbour(neighbour);


        } else {
            neighbour.setFavorite(false);
            faButton.setImageResource(R.drawable.ic_baseline_star_24_grey);
            Toast.makeText(DetailActivity.this, "Delete from favorite", Toast.LENGTH_SHORT).show();
            mApiService.deleteFavoriteNeighbour(neighbour);
        }
    }
}




