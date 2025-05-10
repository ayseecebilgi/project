package com.example.project;

import java.util.ArrayList;

public class User {
    String userId, name, surname, email, country, city;
    ArrayList<Pet> pets;
    ArrayList<Pet> petsIPetSit;
    public User(String userId, String name, String surname, String email){
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        pets = new ArrayList<>();
        petsIPetSit = new ArrayList<>();
    }
    public void addPetToPets(Pet pet){
        pets.add(pet);
    }
    public void addPetToPetsIPetSit(Pet pet){
        petsIPetSit.add(pet);
    }
    public String getUserId(){return userId;}
    public ArrayList<Pet> getPets(){return pets;}
    public ArrayList<Pet> getPetsIPetSit(){return petsIPetSit;}
    public void setUserId(String userId){this.userId = userId;}
    public String getName(){return this.name;}
    public String getSurname(){return this.surname;}
    public String getEmail(){return this.email;}
    public void setName(String name){this.name = name;}
    public void setSurname(String surname){this.surname = surname;}
    public void setEmail(String email){this.email = email;}
}
