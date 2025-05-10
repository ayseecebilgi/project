package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {


    private LinearLayout llPets;
    private Pet selectedPet;
    private String selectedPetId;
    private void displayPets(List<Pet> pets) {
        for (int i = 0; i < pets.size(); i++) {
            View petView = getLayoutInflater().inflate(R.layout.item_pet, llPets, false);
            ImageView imgPet = petView.findViewById(R.id.imgPet);


            Pet pet = pets.get(i);

            Glide.with(this)
                    .load(pet.getImageUrl())
                    .placeholder(R.drawable.circle_shape)
                    .circleCrop()
                    .into(imgPet);

            petView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent();
                    // i.setClass(getApplicationContext(), className.class);
                    // startActivity(i);
                    //ECE DOLDURACAK PET PROFİL SAYFASI AÇILMA ÖZELLİĞİ VE DAHA SONRASI
                }
            });

            llPets.addView(petView);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        llPets = findViewById(R.id.llPets);

        findViewById(R.id.adoption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), Adoption.class);
                startActivity(i);
            }
        });

        findViewById(R.id.caregiving).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), Caregiving.class);
                startActivity(i);
            }
        });

        findViewById(R.id.petsIPetSit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), PetISit.class);
                startActivity(i);
            }
        });

        findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                //USER PROFILE GELDIGINDE GECERIZ
               //i.setClass(getApplicationContext(), UserProfileActivity.class);
               //startActivity(i);
            }
        });


        findViewById(R.id.AddingPetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // i.setClass(getApplicationContext(), className.class);
                // startActivity(i);
               //ECE DOLDURACAK ADDING BUTTON
            }
        });

        findViewById(R.id.RankCaregivers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // i.setClass(getApplicationContext(), className.class);
                // startActivity(i);
                //ECE DOLDURACAK   RankCaregivers BUTTON
            }
        });


        findViewById(R.id.notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), Notify.class);
                startActivity(i);
            }
        });
        displayPets(User.getPets());

    }


}
