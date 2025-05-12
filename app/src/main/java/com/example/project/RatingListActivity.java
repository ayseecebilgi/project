package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.CaregiverRatingAdapter;
import com.example.project.CaregivingTicket;
import com.example.project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RatingListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CaregiverRatingAdapter adapter;
    private List<CaregivingTicket> ticketsToRate = new ArrayList<>();
    private FirebaseFirestore db;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_list);

        recyclerView = findViewById(R.id.recyclerViewTickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        loadTickets();
    }

    private void loadTickets() {
        db.collection("caregivingTickets")
                .whereEqualTo("ownerId", currentUserId)
                .whereEqualTo("isRated", false)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        CaregivingTicket ticket = doc.toObject(CaregivingTicket.class);

                        if (hasCaregivingEnded(ticket)) {
                            ticketsToRate.add(ticket);
                        }
                    }
                    adapter = new CaregiverRatingAdapter(RatingListActivity.this, ticketsToRate);
                    recyclerView.setAdapter(adapter);
                });
    }

    private boolean hasCaregivingEnded(CaregivingTicket ticket) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date endDate = sdf.parse(ticket.getEndingDate() + " " + ticket.getEndingTimeHour() + ":" + ticket.getEndingTimeMinute());
            return new Date().after(endDate);
        } catch (ParseException e) {
            return false;
        }
    }
}
