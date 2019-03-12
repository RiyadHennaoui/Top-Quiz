package com.example.topquiz;

class User {

    private String mFirstName;

    public String getFirstname() {
        return mFirstName;
    }

    public void setFirstname(String mFirstName) {
        this.mFirstName = mFirstName;
    }


    @Override
    public String toString() {
        return "User{" +
                "mFirstname='" +
                mFirstName + '\'' +
                '}' ;
    }
}
