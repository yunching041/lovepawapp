package com.example.lovepaw;

public class UserProfile {
    private String name;
    private String id;
    private String phone;
    private String gender;

    public UserProfile() {
        // Default constructor required for calls to DataSnapshot.getValue(UserProfile.class)
    }

    public UserProfile(String name, String id, String phone, String gender) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.gender = gender;
    }

    // Getter and setter methods for the fields
    // These methods are optional, but it's good practice to include them
}
