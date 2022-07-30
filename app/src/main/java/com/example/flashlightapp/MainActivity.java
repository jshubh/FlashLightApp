package com.example.flashlightapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.flashlightapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.button.getText().toString().equals("turn on")) {
                    binding.button.setText("turn off");
                    binding.flashImage.setImageResource(R.drawable.ima3);
                    changeLightState(true);
                }
                else
                {
                    binding.button.setText("turn on");
                    binding.flashImage.setImageResource(R.drawable.images1);
                    changeLightState(false);
                }
            }
        });
    }

    private void changeLightState(boolean state) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            CameraManager cameraManager=(CameraManager) getSystemService(CAMERA_SERVICE);
            String cam=null;
            try {
                cam = cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(cam,state);
            }
            catch (CameraAccessException e)
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText("Turn on");
    }
}