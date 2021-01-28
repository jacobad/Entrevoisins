package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.DETAIL_PROFILE;

public class DetailProfileActivity extends AppCompatActivity {



    private Neighbour neighbour;

    private NeighbourApiService mFavApiService;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);
        mFavApiService = DI.getNeighbourApiService();



        //Intent intent = getIntent();
        neighbour = getIntent().getParcelableExtra(DETAIL_PROFILE);

        if (neighbour != null) {

            neighbourViewDetails();
            fabOnClickListener();
        }


    }



    public void neighbourViewDetails() {

        setContentView(R.layout.activity_detail_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView DetailAvatar = findViewById(R.id.DetailId);
        Glide.with(DetailAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .into(DetailAvatar);

        CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.toolbar_layout);
        // Set title of Detail page
        collapsingToolbar.setTitle(neighbour.getName());

        TextView DetailName = findViewById(R.id.mDetailName);
        DetailName.setText(neighbour.getName());

        fab = findViewById(R.id.fab);
        updateStarColor();
    }

    private void updateStarColor() {
        if (neighbour.isFavorite()) { // les fav
            fab.setImageResource(R.drawable.ic_baseline_star_91);
            fab.hide();
            fab.show();
        } else {  // les non fav
            fab.setImageResource(R.drawable.ic_baseline_star_90);
            fab.hide();
            fab.show();
        }
    }


    // Methode qui lie mon FAB a cette page et a ma methode ajout de neighbour fav
    public void fabOnClickListener() {


        //le fab reagit au click
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            // et a sa nouvelle vue
            public void onClick(View view) {
                //
                neighbour.setFavorite(!neighbour.isFavorite());
                mFavApiService.addFavNeighbours(neighbour);
                updateStarColor();

            }


        });
    }
}