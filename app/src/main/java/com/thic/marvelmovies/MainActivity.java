package com.thic.marvelmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.thic.marvelmovies.UI.MoviesHome;
import com.thic.marvelmovies.Viewmodel.ViewmodelData;
import com.thic.marvelmovies.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean connected = false;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        checkInternetConnection();
        waitFor(connected);

        binding.connectionRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.connetcionFailed.setVisibility(View.GONE);
                binding.animationView.setVisibility(View.VISIBLE);
                checkInternetConnection();
                waitFor(connected);
            }
        });

    }

    private void checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else connected = false;

    }
    private void waitFor (Boolean permission) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (permission){
                        intent= new Intent(getApplicationContext(), MoviesHome.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else {
                        binding.animationView.setVisibility(View.GONE);
                        binding.connetcionFailed.setVisibility(View.VISIBLE);
                    }
                }
            },3000);
    }




}