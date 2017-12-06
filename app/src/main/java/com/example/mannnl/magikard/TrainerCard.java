package com.example.mannnl.magikard;

/**
 * Created by mannnl on 12/6/2017.
 */

public class TrainerCard {

    private String mName;
    private String mType;
    private String mDescription;

    private boolean delete;

    TrainerCard(String Name, String Type, String Description, boolean delete){
        this.mName = Name;
        this.mType = Type;
        this.mDescription = Description;
        this.delete = false;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String name) {
        mName = name;

    }
    public String getmType() {
        return mType;
    }

    public void setmType(String type) {
        mType = type;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String description) {
        mDescription = description;
    }

    boolean isDelete() { return delete; }

    void setDelete() { this.delete = !delete; }

}
