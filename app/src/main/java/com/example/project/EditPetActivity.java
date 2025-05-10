package com.example.project;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditPetActivity extends AppCompatActivity {

    private ImageView imagePet;
    private EditText inputType, inputName, inputBirthday, inputGender, inputInfo;
    private Button buttonSave, buttonCancel, buttonDelete;

    private FirebaseFirestore db;
    private String ownerId;
    private Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet);

        // Link views
        imagePet = findViewById(R.id.imagePet);
        inputType = findViewById(R.id.inputType);
        inputName = findViewById(R.id.inputName);
        inputBirthday = findViewById(R.id.inputBirthday);
        inputGender = findViewById(R.id.inputGender);
        inputInfo = findViewById(R.id.inputInfo);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonDelete = findViewById(R.id.buttonDelete);

        // Get Firebase + intent
        db = FirebaseFirestore.getInstance();
        ownerId = "owner456";//FirebaseAuth.getInstance().getCurrentUser().getUid();
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

        buttonCancel.setOnClickListener(v -> finish());

        buttonDelete.setOnClickListener(v -> {
            db.collection("pets").document(pet.getId()).delete()
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(this, "Pet deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    });
        });

        buttonSave.setOnClickListener(v -> {
            pet.setSpecies(inputType.getText().toString());
            pet.setName(inputName.getText().toString());
            pet.setBirthday(inputBirthday.getText().toString());
            pet.setGender(inputGender.getText().toString());
            pet.setAdditionalInfo(inputInfo.getText().toString());

            db.collection("pets").document(pet.getId()).set(pet)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();
                        finish();
                    });
        });
    }
}
