package com.example.mannnl.magikard;

/**
 * Created by mannnl on 11/2/2017.
 */

public class PokemonCard {

    private String mName;
    private String mHitPoints;
    private String mType;

    private boolean delete;

    public PokemonCard(String Name, String HitPoints, String Type, boolean delete){
        this.mName = Name;
        this.mHitPoints = HitPoints;
        this.mType = Type;
        this.delete = false;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmHitPoints() {
        return mHitPoints;
    }

    public void setmHitPoints(String mHitPoints) {
        this.mHitPoints = mHitPoints;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public boolean isDelete() { return delete; }

    public void setDelete() { this.delete = !delete; }
}
