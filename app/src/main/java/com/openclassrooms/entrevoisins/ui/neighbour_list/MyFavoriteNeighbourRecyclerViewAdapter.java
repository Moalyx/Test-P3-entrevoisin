package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteNeighbourRecyclerViewAdapter.ViewHolder> {

    List<Neighbour> favoriteNeighbours;


    public MyFavoriteNeighbourRecyclerViewAdapter(Context context, List<Neighbour> favoriteNeighbours) {
        context = context;
        favoriteNeighbours = favoriteNeighbours;
    }



    @Override
    public MyFavoriteNeighbourRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_row_favorite_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = favoriteNeighbours.get(position);
        holder.favoriteNeighbourName.setText(neighbour.getName());
        Glide.with(holder.favoriteNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.favoriteNeighbourAvatar);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.favorite_avatar)
        public ImageView favoriteNeighbourAvatar;
        @BindView(R.id.favorite_name)
        public TextView favoriteNeighbourName;
        @BindView(R.id.favorite_delete_button)
        public ImageButton favoriteDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
