package com.example.mannnl.magikard;

/**
 * Created by mannnl on 12/6/2017.
 */

 class EnergyCard {

    private String mName;
    private String mDesc;

    private Boolean delete;

    EnergyCard(String Name, String Description, boolean delete) {
        this.mName = Name;
        this.mDesc = Description;
        this.delete = delete;
    }
    EnergyCard() {

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public Boolean isDelete() {
        return delete;
    }

    public void setDelete() {
        this.delete = !delete;
    }

}
