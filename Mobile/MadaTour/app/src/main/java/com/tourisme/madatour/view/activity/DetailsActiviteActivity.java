package com.tourisme.madatour.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tourisme.madatour.R;
import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.view.adapter.PhotoAdapter;

import java.util.List;

public class DetailsActiviteActivity extends AppCompatActivity {

    ImageView photoActivite;
    WebView contentActivite;

    Button buttonPhotos;
    private ViewPager2 viewPager;
    Guide activiteSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activite);
        init();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationBack(toolbar, DetailsActiviteActivity.this);
        activiteSelected = (Guide) getIntent().getSerializableExtra("activite");
        if (activiteSelected != null) {
            String activiteName = activiteSelected.getNom();
            toolbar.setTitle(activiteName);
            Picasso.get().load(activiteSelected.getPhotos().get(0)).into(photoActivite);
            String activiteDescription = activiteSelected.getDescription();
            Log.d("gga", " - > postion    " + activiteDescription);
            contentActivite.loadData(activiteDescription, "text/html", "utf-8");
            buttonPhotos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPhotoCarouselDialog(activiteSelected.getPhotos());
                }
            });

        }
    }

    private void init() {
        photoActivite = findViewById(R.id.photoActivite);
        contentActivite = findViewById(R.id.contentActivite);
        buttonPhotos = findViewById(R.id.buttonPhotos);
    }

    public void navigationBack(Toolbar toolbar, AppCompatActivity current) {
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void showPhotoCarouselDialog(List<String> photoList) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_photos, null);
        viewPager = dialogView.findViewById(R.id.viewPager);
        PhotoAdapter photoAdapter = new PhotoAdapter(photoList);
        viewPager.setAdapter(photoAdapter);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}