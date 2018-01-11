package com.example.mannnl.magikard;

/**
 * Created by mannnl on 11/2/2017.
 */

 class PokemonCard {

    private String mName;
    private String mHitPoints;
    private String mType;

    private boolean delete;

    PokemonCard(String Name, String HitPoints, String Type, boolean delete){
        this.mName = Name;
        this.mHitPoints = HitPoints;
        this.mType = Type;
        this.delete = false;
    }
    PokemonCard() {

    }

    String getmName() {
        return mName;
    }

    void setmName(String mName) {
        this.mName = mName;
    }

    String getmHitPoints() {
        return mHitPoints;
    }

    void setmHitPoints(String mHitPoints) {
        this.mHitPoints = mHitPoints;
    }

    String getmType() {
        return mType;
    }

    void setmType(String mType) {
        this.mType = mType;
    }

    boolean isDelete() { return delete; }

    void setDelete() { this.delete = !delete; }
}
