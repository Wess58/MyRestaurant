package com.wess58.myrestaurant.models;

import java.util.ArrayList;

public class Restaurant {

    private String mName;
    private String mPhone;
    private String mWebsite;
    private String mImageUrl;
    private double mRating;
    private double mLatitude;
    private double mLongitude;
    private ArrayList<String> mAddress = new ArrayList<>();
    private ArrayList<String> mCategories = new ArrayList<>();


    public Restaurant( String name, String phone, String website, String imageUrl,
                       double rating, double latitude, double longitude,
                       ArrayList<String> address, ArrayList<String> categories){

        this.mName = name;
        this.mPhone = phone;
        this.mWebsite = website;
        this.mImageUrl = imageUrl;
        this.mRating = rating;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mAddress = address;
        this.mCategories = categories;

    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public double getRating() {
        return mRating;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public ArrayList<String> getAddress() {
        return mAddress;
    }

    public ArrayList<String> getCategories() {
        return mCategories;
    }

}
