package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

public class PetDetailActivity extends AppCompatActivity {

    private ImageView imagePet;
    private EditText inputType, inputName, inputBirthday, inputGender, inputInfo;
    private Button buttonReturn;
    private ImageButton buttonEdit;

    private Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        imagePet = findViewById(R.id.imagePet);
        inputType = findViewById(R.id.inputType);
        inputName = findViewById(R.id.inputName);
        inputBirthday = findViewById(R.id.inputBirthday);
        inputGender = findViewById(R.id.inputGender);
        inputInfo = findViewById(R.id.inputInfo);
        buttonReturn = findViewById(R.id.buttonReturn);
        buttonEdit = findViewById(R.id.buttonEditToggle);

        // Make fields read-only
        inputType.setEnabled(false);
        inputName.setEnabled(false);
        inputBirthday.setEnabled(false);
        inputGender.setEnabled(false);
        inputInfo.setEnabled(false);

        pet = (Pet) getIntent().getSerializableExtra("pet");

        if (pet != null) {
            inputType.setText(pet.getSpecies());
            inputName.setText(pet.getName());
            inputBirthday.setText(pet.getBirthday());
            inputGender.setText(pet.getGender());
            inputInfo.setText(pet.getAdditionalInfo());

            Glide.with(this)
                    .load(pet.getImageUrl())
                    .placeholder(R.drawable.circle_background)
                    .circleCrop()
                    .into(imagePet);

        }

        buttonReturn.setOnClickListener(v -> finish());

        buttonEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(PetDetailActivity.this, EditPetActivity.class);
            editIntent.putExtra("pet", pet);
            startActivity(editIntent);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        FirebaseFirestore.getInstance()
                .collection("pets")
                .document(pet.getId())
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    Pet updatedPet = documentSnapshot.toObject(Pet.class);
                    if (updatedPet != null) {
                        pet = updatedPet;
                        inputType.setText(pet.getSpecies());
                        inputName.setText(pet.getName());
                        inputBirthday.setText(pet.getBirthday());
                        inputGender.setText(pet.getGender());
                        inputInfo.setText(pet.getAdditionalInfo());

                        Glide.with(this)
                                .load(pet.getImageUrl())
                                .placeholder(R.drawable.circle_background)
                                .circleCrop()
                                .into(imagePet);
                    }
                });
    }

}
