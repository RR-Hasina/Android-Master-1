package com.tourisme.madatour.view.fragment.home;

import static android.content.Intent.getIntent;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.tourisme.madatour.R;
import com.tourisme.madatour.databinding.FragmentHomeBinding;
import com.tourisme.madatour.model.Circuit;
import com.tourisme.madatour.model.Guide;
import com.tourisme.madatour.model.Reservation;
import com.tourisme.madatour.model.Trajet;
import com.tourisme.madatour.network.RestApiServiceCircuit;
import com.tourisme.madatour.network.RetrofitInstance;
import com.tourisme.madatour.response.CircuitResponse;
import com.tourisme.madatour.view.activity.MainActivity;
import com.tourisme.madatour.view.fragment.attraction.AttractionFragment;
import com.tourisme.madatour.view.fragment.dashboard.DashboardFragment;
import com.tourisme.madatour.view.fragment.profile.ProfileFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    protected FragmentActivity mActivity;
    private FragmentHomeBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView circuitTitre;
    TextView circuitInfo;

    ImageView circuitImage1;
    ImageView circuitImage2;
    ImageView circuitImage3;
    TextView circuitItineraire;
    Circuit circuitSelected;
    TableLayout tableLayoutTrajets;
    TextView circuitDebut;
    TextView circuitFin;
    TextView circuitDisponible;
    TextView circuitPrix;
    TextView circuitStatut;

    Button btnReservation;
    int compteur;

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        circuitTitre = view.findViewById(R.id.circuitTitre);
        circuitInfo = view.findViewById(R.id.circuitInfo);
        circuitImage1 = view.findViewById(R.id.circuitImage1);
        circuitImage2 = view.findViewById(R.id.circuitImage2);
        circuitImage3 = view.findViewById(R.id.circuitImage3);
        circuitItineraire = view.findViewById(R.id.circuitItineraire);
        tableLayoutTrajets = view.findViewById(R.id.talbeauLayoutTrajet);
        RestApiServiceCircuit apiServiceCircuit = RetrofitInstance.getApiServiceCircuit();
        Call<CircuitResponse> call = apiServiceCircuit.getCircuitList();
        call.enqueue(new Callback<CircuitResponse>() {
            @Override
            public void onResponse(Call<CircuitResponse> call, Response<CircuitResponse> response) {
                CircuitResponse circuitResponse = response.body();
                List<Circuit> circuitList = circuitResponse.getCircuit();
                circuitTitre.setText(circuitList.get(0).getNom());
                circuitInfo.setText(circuitList.get(0).getDescription().getInfo());
                circuitSelected = (Circuit) mActivity.getIntent().getSerializableExtra("circuit");
                Picasso.get().load(circuitList.get(0).getPhotos().get(0)).into(circuitImage1);
                Picasso.get().load(circuitList.get(0).getPhotos().get(1)).into(circuitImage2);
                Picasso.get().load(circuitList.get(0).getPhotos().get(2)).into(circuitImage3);
                circuitItineraire.setText(circuitList.get(0).getItineraire().getTitre());
                List<Trajet> trajet = circuitList.get(0).getItineraire().getTrajet();
                for (int i = 0; i < trajet.size(); i++) {
                    TableRow tableRow = new TableRow(mActivity);
                    TextView circuitDepart = new TextView(mActivity);
                    circuitDepart.setText(trajet.get(i).getLieu_depart() != null ? trajet.get(i).getLieu_depart() : "-");
                    circuitDepart.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                    circuitDepart.setPadding(8, 8, 8, 8);

                    TextView circuitArrivee = new TextView(mActivity);
                    circuitArrivee.setText(trajet.get(i).getLieu_arrivee() != null ? trajet.get(i).getLieu_arrivee() : "-");
                    circuitArrivee.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                    circuitArrivee.setPadding(8, 8, 8, 8);

                    TextView circuitDuree = new TextView(mActivity);
                    circuitDuree.setText(trajet.get(i).getDuree() != null ? trajet.get(i).getDuree() : "-");
                    circuitDuree.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                    circuitDuree.setPadding(8, 8, 8, 8);

                    TextView circuitDistance = new TextView(mActivity);
                    circuitDistance.setText(trajet.get(i).getDistance() != null ? trajet.get(i).getDistance() : "-");
                    circuitDistance.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                    circuitDistance.setPadding(8, 8, 8, 8);

                    TextView circuitTransport = new TextView(mActivity);
                    circuitTransport.setText(trajet.get(i).getTransport() != null ? trajet.get(i).getTransport() : "-");
                    circuitTransport.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                    circuitTransport.setPadding(8, 8, 8, 8);

                    // Ajoutez les TextView à la TableRow
                    tableRow.addView(circuitDepart);
                    tableRow.addView(circuitArrivee);
                    tableRow.addView(circuitDuree);
                    tableRow.addView(circuitDistance);
                    tableRow.addView(circuitTransport);

                    // Ajoutez la TableRow au TableLayout
                    tableLayoutTrajets.addView(tableRow);
                    if (i < trajet.size() - 1) {
                        View separator = new View(mActivity);
                        separator.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2)); // Hauteur de la ligne de séparation
                        separator.setBackgroundColor(Color.BLACK); // Couleur de la ligne de séparation
                        tableLayoutTrajets.addView(separator);
                    }
                }
                circuitDebut = view.findViewById(R.id.circuitDebut);
                circuitDebut.setText(circuitList.get(0).getDisponibilite().getDate_debut());
                circuitFin = view.findViewById(R.id.circuitFin);
                circuitFin.setText(circuitList.get(0).getDisponibilite().getDate_fin());
                circuitDisponible = view.findViewById(R.id.circuitDisponible);
                circuitDisponible.setText(circuitList.get(0).getDisponibilite().getDisponible());
                circuitPrix = view.findViewById(R.id.circuitPrix);
                circuitPrix.setText(circuitList.get(0).getDisponibilite().getPrix());
                circuitStatut = view.findViewById(R.id.circuitStatut);
                circuitStatut.setText(circuitList.get(0).getDisponibilite().getValueStatut());
                btnReservation = view.findViewById(R.id.btnReservation);
                int ok = 0;
                sharedPreferences = mActivity.getSharedPreferences("Application", Context.MODE_PRIVATE);
                if (sharedPreferences.getString("idClient", null) != null) {
                    for (int i = 0; i < circuitList.get(0).getListeReservation().size(); i++) {
                        if (circuitList.get(0).getListeReservation().get(i).equals(sharedPreferences.getString("idClient", null))) {
                            ok += 1;
                        }
                    }
                }
                if (Integer.parseInt(circuitList.get(0).getDisponibilite().getDisponible()) < 1) {
                    btnReservation.setEnabled(false);
                    btnReservation.setText("Place indisponible");
                } else if (ok > 0) {
                    btnReservation.setText("Voir billet");
                    btnReservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Fragment ProfileFragment = new ProfileFragment();
                            replaceFragment(ProfileFragment);

                        }
                    });
                } else {
                    btnReservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sharedPreferences = mActivity.getSharedPreferences("Application", Context.MODE_PRIVATE);
                            if (sharedPreferences.getString("idClient", null) != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                                builder.setMessage("Êtes-vous sûr de valider la réservation?")
                                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                    NotificationChannel notificationChannel = new NotificationChannel("MyChn", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
                                                    NotificationManager manager = mActivity.getSystemService(NotificationManager.class);
                                                    manager.createNotificationChannel(notificationChannel);
                                                }
                                                NotificationCompat.Builder builder = new NotificationCompat.Builder(mActivity, "MyChn")
                                                        .setSmallIcon(android.R.drawable.btn_radio)
                                                        .setContentTitle("MadaTour")
                                                        .setContentText("Vous aviez acheté un billet de réservation au pres de MadaTour");
                                                notification = builder.build();
                                                notificationManagerCompat = NotificationManagerCompat.from(mActivity);
                                                if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                                                    // TODO: Consider calling
                                                    //    ActivityCompat#requestPermissions
                                                    // here to request the missing permissions, and then overriding
                                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                    //                                          int[] grantResults)
                                                    // to handle the case where the user grants the permission. See the documentation
                                                    // for ActivityCompat#requestPermissions for more details.
                                                    return;
                                                }
                                                notificationManagerCompat.notify(1, notification);

                                                compteur=0;
                                                // User clicked "Yes," proceed with the reservation
                                                String idClient=sharedPreferences.getString("idClient",null);
                                                RestApiServiceCircuit apiServiceCircuit = RetrofitInstance.getApiServiceCircuit();
                                                Call<CircuitResponse> call = apiServiceCircuit.addReservation(new Reservation("Faune et Flore",idClient));

                                                call.enqueue(new Callback<CircuitResponse>() {
                                                    @Override
                                                    public void onResponse(Call<CircuitResponse> call, Response<CircuitResponse> response) {
                                                        CircuitResponse circuitResponse = response.body();
                                                        List<Circuit> listeCircuit=circuitResponse.getCircuit();
                                                    }
                                                    @Override
                                                    public void onFailure(Call<CircuitResponse> call, Throwable t) {
                                                        // Handle the error if the reservation fails
                                                    }

                                                });
                                                replaceFragment(new DashboardFragment());
                                            }
                                        })
                                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                // User clicked "No," cancel the reservation action
                                            }
                                        });

                                // Create and show the AlertDialog
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }else{
                                Fragment ProfileFragment=new ProfileFragment();
                                replaceFragment(ProfileFragment);
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<CircuitResponse> call, Throwable t) {
                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            mActivity = (FragmentActivity) context;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.mActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}