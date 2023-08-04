package com.tourisme.madatour.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.tourisme.madatour.R;
import com.tourisme.madatour.model.Destination;
import com.tourisme.madatour.view.adapter.PhotoAdapter;

import java.util.List;

public class DetailsDestinationActivity extends AppCompatActivity {

    ImageView photoDestination;
    WebView contentDestination;

    Button buttonPhotos;
    Button buttonVideo;
    private ViewPager2 viewPager;
    Destination destinationSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_destination);
        init();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationBack(toolbar, DetailsDestinationActivity.this);
        destinationSelected = (Destination) getIntent().getSerializableExtra("destination");
        if (destinationSelected != null) {
            String destinationName = destinationSelected.getNom();
            toolbar.setTitle(destinationName);
            Picasso.get().load(destinationSelected.getPhotos().get(0)).into(photoDestination);
            String destinationDescription = destinationSelected.getDescription();
            Log.d("gga", " - > postion    " + destinationDescription);
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                contentDestination.getSettings().setJavaScriptEnabled(true);
                contentDestination.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        // Inject CSS on PageFinished
                        injectCSS();
                        super.onPageFinished(view, url);
                    }
                });
            }
            contentDestination.loadData(destinationDescription, "text/html", "utf-8");
            buttonPhotos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPhotoCarouselDialog(destinationSelected.getPhotos());
                    showPhotoCarouselDialog(destinationSelected.getPhotos());
                }
            });

            buttonVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showYouTubeDialog(destinationSelected.getVideo().get(0));
                }
            });

        }
    }

    private void init() {
        photoDestination = findViewById(R.id.photoDestination);
        contentDestination = findViewById(R.id.contentDestination);
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

    private void injectCSS() {
        contentDestination.loadUrl(
                "javascript:document.body.style.setProperty(\"color\", \"white\");"
        );
        contentDestination.loadUrl(
                "javascript:document.body.style.setProperty(\"background-color\", \"#303030\");"
        );
    }

}