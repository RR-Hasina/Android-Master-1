package com.tourisme.madatour.view.fragment.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tourisme.madatour.R;
import com.tourisme.madatour.model.Client;
import com.tourisme.madatour.network.RestApiServiceClient;
import com.tourisme.madatour.network.RetrofitInstance;
import com.tourisme.madatour.view.activity.MainActivity;
import com.tourisme.madatour.response.ClientResponse;

import java.util.ArrayList;
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

     Button btnConnexion, btnInscription, btnDeconnexion;
     EditText emailConnexion, mdpConnexion;
     EditText nomInscription, prenomInscription, emailInscription, mdpInscription, telephoneInscription;


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

        if(sharedPreferences.getString("username",null)!=null){
            view=inflater.inflate(R.layout.fragment_reservation,container,false);
            this.btnDeconnexion=view.findViewById(R.id.btnDeconnexion);
            this.btnDeconnexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPreferences=getActivity().getSharedPreferences("Application", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.remove("username");
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
                            editor.commit();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("destination", "kakaBoudin"); // Pass any data you want to the new activity
                            startActivity(intent);
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
                intent.putExtra("destination", "kakaBoudin"); // Pass any data you want to the new activity
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
}