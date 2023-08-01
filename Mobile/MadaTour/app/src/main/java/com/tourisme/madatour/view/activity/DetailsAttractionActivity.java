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

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.tourisme.madatour.R;
import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.view.adapter.PhotoAdapter;

import java.util.List;

public class DetailsAttractionActivity extends AppCompatActivity {

    ImageView photoAttraction;
    WebView contentAttraction;

    Button buttonPhotos;
    Button buttonVideo;
    private ViewPager2 viewPager;
    Guide attractionSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_attraction);
        init();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationBack(toolbar, DetailsAttractionActivity.this);
        attractionSelected = (Guide) getIntent().getSerializableExtra("attraction");
        if (attractionSelected != null) {
            String attractionName = attractionSelected.getNom();
            toolbar.setTitle(attractionName);
            Picasso.get().load(attractionSelected.getPhotos().get(0)).into(photoAttraction);
            String activiteDescription = attractionSelected.getDescription();
            Log.d("gga", " - > postion    " + activiteDescription);
            contentAttraction.loadData(activiteDescription, "text/html", "utf-8");
            buttonPhotos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPhotoCarouselDialog(attractionSelected.getPhotos());
                }
            });
            buttonVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showYouTubeDialog(attractionSelected.getVideo().get(0));
                }
            });

        }
    }

    private void init() {
        photoAttraction = findViewById(R.id.photoAttraction);
        contentAttraction = findViewById(R.id.contentAttraction);
        buttonPhotos = findViewById(R.id.buttonPhotos);
        buttonVideo =  findViewById(R.id.buttonVideos);
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

    private void showYouTubeDialog(String videoId) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_video_player, null);
        builder.setView(dialogView);

        YouTubePlayerView youTubePlayerView = dialogView.findViewById(R.id.youtubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}