package com.example.administrator.tantanselector;

/**
 * Created by Administrator on 2018/2/8.
 */

public class Data {
    String avatar ;
    String name ;
    String gender ;
    String age ; 
    String constellation ; 
    String occupation ;

    public Data(String avatar, String name, String gender, String age, String constellation, String occupation) {
        this.avatar = avatar;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.constellation = constellation;
        this.occupation = occupation;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getage() {
        return age;
    }

    public void setage(String age) {
        this.age = age;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
