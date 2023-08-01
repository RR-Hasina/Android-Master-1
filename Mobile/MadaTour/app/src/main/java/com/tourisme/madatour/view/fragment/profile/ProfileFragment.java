package com.tourisme.madatour.view.fragment.profile;

import android.app.Application;
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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.tourisme.madatour.R;
import com.tourisme.madatour.model.Client;
import com.tourisme.madatour.network.RestApiServiceClient;
import com.tourisme.madatour.network.RetrofitInstance;
import com.tourisme.madatour.repository.ClientRespository;
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
     Button btnConnexion, btnInscription;
     EditText emailConnexion, mdpConnexion;
     EditText nomInscription, prenomInscription, emailInscription, mdpInscription, telephoneInscription;
     SharedPreferences sharedPreferences;
     SharedPreferences.Editor editor;

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
        View view=  inflater.inflate(R.layout.fragment_profile, container, false);
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
                call.enqueue(new Callback<ClientResponse>() {
                    @Override
                    public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                        ClientResponse clientWrapper = response.body();
                        ArrayList<Client> listeClient= (ArrayList<Client>) clientWrapper.getClient();
                        if (listeClient.size()==1){
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
                        List<Client> listeClient= (List<Client>) clientWrapper.getClient();
                    }
                    @Override
                    public void onFailure(Call<ClientResponse> call, Throwable t) {
                        Log.d("ListSize"," - > Error    "+ t.getMessage());
                    }
                });
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("destination", "kakaBoudin"); // Pass any data you want to the new activity
                startActivity(intent);
                Toast.makeText(getContext(),"Inscription réussi", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}