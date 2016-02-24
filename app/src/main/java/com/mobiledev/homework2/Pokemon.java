package com.mobiledev.homework2;

import android.os.Parcel;
import android.os.Parcelable;

public class Pokemon implements Parcelable {
    private String mName, mId, mSpeciesId, mHeight, mWeight, mHP, mAttack, mDefense, mSpecialAttack, mSpecialDefense, mSpeed, mBaseExperience;

    public Pokemon(String csvStr) {
        String[] split = csvStr.trim().split(",");

        mId = split[0];
        mName = split[1];
        mSpeciesId = split[2];
        mHeight = split[3];
        mWeight = split[4];
    }

    public String getImageUrl() {
        return "http://img.pokemondb.net/artwork/" + getName() + ".jpg";
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    public String getSpeciesId() {
        return mSpeciesId;
    }

    public String getHeight() {
        return mHeight;
    }

    public String getWeight() {
        return mWeight;
    }

    public String getmName() {
        return mName;
    }

    public String getmId() {
        return mId;
    }

    public String getmSpeciesId() {
        return mSpeciesId;
    }

    public String getmHeight() {
        return mHeight;
    }

    public String getmWeight() {
        return mWeight;
    }

    public String getmHP() {
        return mHP;
    }

    public String getmAttack() {
        return mAttack;
    }

    public String getmDefense() {
        return mDefense;
    }

    public String getmSpecialAttack() {
        return mSpecialAttack;
    }

    public String getmSpecialDefense() {
        return mSpecialDefense;
    }

    public String getmSpeed() {
        return mSpeed;
    }

    public String getmBaseExperience() {
        return mBaseExperience;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public void setmSpeciesId(String mSpeciesId) {
        this.mSpeciesId = mSpeciesId;
    }

    public void setmHeight(String mHeight) {
        this.mHeight = mHeight;
    }

    public void setmWeight(String mWeight) {
        this.mWeight = mWeight;
    }

    public void setmHP(String mHP) {
        this.mHP = mHP;
    }

    public void setmAttack(String mAttack) {
        this.mAttack = mAttack;
    }

    public void setmDefense(String mDefense) {
        this.mDefense = mDefense;
    }

    public void setmSpecialAttack(String mSpecialAttack) {
        this.mSpecialAttack = mSpecialAttack;
    }

    public void setmSpecialDefense(String mSpecialDefense) {
        this.mSpecialDefense = mSpecialDefense;
    }

    public void setmSpeed(String mSpeed) {
        this.mSpeed = mSpeed;
    }

    public void setmBaseExperience(String mBaseExperience) {
        this.mBaseExperience = mBaseExperience;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel source) {
            return new Pokemon(source);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mId);
        dest.writeString(mSpeciesId);
        dest.writeString(mHeight);
        dest.writeString(mWeight);
        dest.writeString(mHP);
        dest.writeString(mAttack);
        dest.writeString(mDefense);
        dest.writeString(mBaseExperience);
        dest.writeString(mSpecialAttack);
        dest.writeString(mSpecialDefense);
        dest.writeString(mSpeed);
    }


    public Pokemon(Parcel source) {
        mName = source.readString();
        mId = source.readString();
        mSpeciesId = source.readString();
        mHeight = source.readString();
        mWeight = source.readString();
        mHP = source.readString();
        mAttack = source.readString();
        mDefense = source.readString();
        mBaseExperience = source.readString();
        mSpecialAttack = source.readString();
        mSpecialDefense = source.readString();
        mSpeed = source.readString();
    }

}
