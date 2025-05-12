package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainEceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_ece);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button addPetButton = findViewById(R.id.button);
        if(addPetButton == null){
            Toast.makeText(MainEceActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
        }
        else{
            addPetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainEceActivity.this, AddPetActivity.class);
                    startActivity(intent);
                }
            });
        }
        Button testPetButton = findViewById(R.id.buttonTestPetDetail);
        testPetButton.setOnClickListener(v -> {
            Pet testPet = new Pet(
                    "test123",
                    "owner456",
                    "Whiskers",
                    "Cat",
                    "2022-04-01",
                    "Female",
                    "Loves to sleep in the sun.",
                    "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg"
            );

            Intent intent = new Intent(MainEceActivity.this, PetDetailActivity.class);
            intent.putExtra("pet", testPet);
            startActivity(intent);
        });


    }
}