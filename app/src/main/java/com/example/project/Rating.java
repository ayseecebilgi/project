package com.example.project;

public class Rating {
    private String fromUserId;
    private String ticketId;
    private String petId;
    private int rating;

    public Rating() {}

    public Rating(String fromUserId, String ticketId, String petId, int rating) {
        this.fromUserId = fromUserId;
        this.ticketId = ticketId;
        this.petId = petId;
        this.rating = rating;
    }

    public String getFromUserId() {
        return fromUserId;
    }
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getTicketId() {
        return ticketId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getPetId() {
        return petId;
    }
    public void setPetId(String petId) {
        this.petId = petId;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
}

