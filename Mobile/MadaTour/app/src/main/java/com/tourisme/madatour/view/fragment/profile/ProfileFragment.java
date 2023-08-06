package com.tourisme.madatour.view.fragment.profile;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tourisme.madatour.R;
import com.tourisme.madatour.model.Circuit;
import com.tourisme.madatour.model.Client;
import com.tourisme.madatour.model.Reservation;
import com.tourisme.madatour.network.RestApiServiceCircuit;
import com.tourisme.madatour.network.RestApiServiceClient;
import com.tourisme.madatour.network.RetrofitInstance;
import com.tourisme.madatour.response.CircuitResponse;
import com.tourisme.madatour.view.activity.MainActivity;
import com.tourisme.madatour.response.ClientResponse;
import com.tourisme.madatour.view.fragment.destination.DestinationFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private ProfileViewModel profileViewModel;
    SharedPreferences sharedPreferences;

     Button btnConnexion, btnInscription, btnDeconnexion, btnSuppression;
     EditText emailConnexion, mdpConnexion;
     EditText nomInscription, prenomInscription, emailInscription, mdpInscription, telephoneInscription;
     TextView circuitTableau, joursTableau, debutTableau, circuitPaiement, messageBienvenue;

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    public ProfileFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {

        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=null;
        sharedPreferences=getActivity().getSharedPreferences("Application", Context.MODE_PRIVATE);
        setHasOptionsMenu(true);
        if(sharedPreferences.getString("username",null)!=null){
            view=inflater.inflate(R.layout.fragment_reservation,container,false);
            this.btnDeconnexion=view.findViewById(R.id.btnDeconnexion);
            this.btnSuppression=view.findViewById(R.id.btnSupprimer);
            this.circuitTableau=view.findViewById(R.id.circuitTableau);
            this.debutTableau=view.findViewById(R.id.débutTableau);
            this.joursTableau=view.findViewById(R.id.joursTableau);
            this.circuitPaiement=view.findViewById(R.id.circuitPaiement);
            this.messageBienvenue=view.findViewById(R.id.messageBienvenue);
            this.messageBienvenue.setText("Bonjour, "+sharedPreferences.getString("username",null)+", voici votre tableau de réservation");
            RestApiServiceCircuit restApiServiceCircuit=RetrofitInstance.getApiServiceCircuit();
            Call<CircuitResponse> call=restApiServiceCircuit.checkReservation(new Reservation("Faune et Flore",sharedPreferences.getString("idClient",null)));
            call.enqueue(new Callback<CircuitResponse>() {
                @Override
                public void onResponse(Call<CircuitResponse> call, Response<CircuitResponse> response) {
                    CircuitResponse circuitResponse = response.body();
                    List<Circuit> listeCircuit=circuitResponse.getCircuit();
                    if(listeCircuit.size()>0){
                        circuitTableau.setText(listeCircuit.get(0).getNom());
                        debutTableau.setText(listeCircuit.get(0).getDisponibilite().getDate_debut());
                        joursTableau.setText(listeCircuit.get(0).getItineraire().getNbrJours());
                        circuitPaiement.setText("Payé");

                    }else{
                        circuitTableau.setText(" - ");
                        debutTableau.setText(" - ");
                        joursTableau.setText(" - ");
                        circuitPaiement.setText(" - ");
                        btnSuppression.setEnabled(false);
                    }
                }
                @Override
                public void onFailure(Call<CircuitResponse> call, Throwable t) {

                }
            });
            this.btnSuppression.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Êtes-vous sûr de supprimer votre réservation?")
                            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        NotificationChannel notificationChannel = new NotificationChannel("MyChn", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
                                        NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
                                        manager.createNotificationChannel(notificationChannel);
                                    }
                                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "MyChn")
                                            .setSmallIcon(android.R.drawable.btn_radio)
                                            .setContentTitle("MadaTour")
                                            .setContentText("Vous aviez supprimer votre billet de réservation!");
                                    notification = builder.build();
                                    notificationManagerCompat = NotificationManagerCompat.from(getActivity());
                                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
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
                                    Toast.makeText(getContext(),"Votre billet a été supprimé avec succes", Toast.LENGTH_SHORT).show();
                                    sharedPreferences=getActivity().getSharedPreferences("Application", Context.MODE_PRIVATE);
                                    String idClient = sharedPreferences.getString("idClient",null);
                                    RestApiServiceCircuit restApiServiceCircuit = RetrofitInstance.getApiServiceCircuit();
                                    Call<CircuitResponse> call = restApiServiceCircuit.deleteReservation(new Reservation("Faune et Flore",idClient));

                                    call.enqueue(new Callback<CircuitResponse>() {
                                        @Override
                                        public void onResponse(Call<CircuitResponse> call, Response<CircuitResponse> response) {
                                            CircuitResponse circuitResponse = response.body();
                                            List<Circuit> listeCircuit=circuitResponse.getCircuit();
                                        }
                                        @Override
                                        public void onFailure(Call<CircuitResponse> call, Throwable t) {

                                        }
                                    });
                                    replaceFragment(new DestinationFragment());
                                }
                            }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
            this.btnDeconnexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPreferences=getActivity().getSharedPreferences("Application", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.remove("username");
                    editor.remove("idClient");
                    editor.commit();
                    replaceFragment(new ProfileFragment());
                }
            });

        }else{
        view= inflater.inflate(R.layout.fragment_profile, container, false);
        this.emailConnexion=view.findViewById(R.id.emailConnexion);
        this.mdpConnexion=view.findViewById(R.id.mdpConnexion);
        this.btnConnexion=view.findViewById(R.id.btnConnexion);


        this.btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailConnexion.getText().toString();
                String mdp=mdpConnexion.getText().toString();
                Client client = new Client(email, mdp);
                /*********** API *******************/
                RestApiServiceClient apiServiceClient = RetrofitInstance.getApiServiceClient();
                Call<ClientResponse> call = apiServiceClient.clientLogin(client);
                sharedPreferences=getActivity().getSharedPreferences("Application", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                call.enqueue(new Callback<ClientResponse>() {
                    @Override
                    public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {

                        ClientResponse clientWrapper = response.body();
                        Client listeClient= (Client) clientWrapper.getClient();
                        if (listeClient!=null){
                            editor.putString("username",listeClient.getNom());
                            editor.putString("idClient",listeClient.getId());
                            editor.commit();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("destination", "");
                            startActivity(intent);
                            Toast.makeText(getContext(),"Bienvenue "+sharedPreferences.getString("username",null), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(),"Erreur de connexion", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ClientResponse> call, Throwable t) {
                        Log.d("ListSize"," - > Error    "+ t.getMessage());
                    }
                });
            }
        });

        this.nomInscription=view.findViewById(R.id.nomInscription);
        this.prenomInscription=view.findViewById(R.id.prenomInscription);
        this.emailInscription=view.findViewById(R.id.emailInscription);
        this.mdpInscription=view.findViewById(R.id.mdpInscription);
        this.telephoneInscription=view.findViewById(R.id.telephoneInscription);
        this.btnInscription=view.findViewById(R.id.btnInscription);
        this.btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom=nomInscription.getText().toString();
                String prenom=prenomInscription.getText().toString();
                String email=emailInscription.getText().toString();
                String mdp=mdpInscription.getText().toString();
                String telephone=telephoneInscription.getText().toString();
                Client inscription=new Client("inscription",nom, prenom, email, mdp, telephone);
                RestApiServiceClient apiServiceClient = RetrofitInstance.getApiServiceClient();
                Call<ClientResponse> call=apiServiceClient.clientInscription(inscription);
                call.enqueue(new Callback<ClientResponse>() {
                    @Override
                    public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                        ClientResponse clientWrapper = response.body();
                        Client listeClient= (Client) clientWrapper.getClient();
                        sharedPreferences=getActivity().getSharedPreferences("Application", Context.MODE_PRIVATE);
                        Toast.makeText(getContext(),sharedPreferences.getString("username",null), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<ClientResponse> call, Throwable t) {
                        Log.d("ListSize"," - > Error    "+ t.getMessage());
                    }
                });
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("destination", "");
                startActivity(intent);
            }
        });
        }
        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.search);
        if(item!=null)
            item.setVisible(false);
    }
}