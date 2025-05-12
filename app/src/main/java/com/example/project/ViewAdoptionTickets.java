package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewAdoptionTickets extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.adoption_scroll_menu);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.btnCat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AdoptionTickets.class);
                intent.putExtra("type", "cat");
                startActivity(intent);
            }
        });

        findViewById(R.id.btnDog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AdoptionTickets.class);
                intent.putExtra("type", "dog");
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBird).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AdoptionTickets.class);
                intent.putExtra("type", "bird");
                startActivity(intent);
            }
        });

        findViewById(R.id.btnRabbit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AdoptionTickets.class);
                intent.putExtra("type", "rabbit");
                startActivity(intent);
            }
        });

        findViewById(R.id.btnHamster).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AdoptionTickets.class);
                intent.putExtra("type", "hamster");
                startActivity(intent);
            }
        });

        findViewById(R.id.btnFish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AdoptionTickets.class);
                intent.putExtra("type", "fish");
                startActivity(intent);
            }
        });

        findViewById(R.id.btnTurtle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AdoptionTickets.class);
                intent.putExtra("type", "turtle");
                startActivity(intent);
            }
        });

        findViewById(R.id.btnOther).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AdoptionTickets.class);
                intent.putExtra("type", "other");
                startActivity(intent);
            }
        });
    }
}
