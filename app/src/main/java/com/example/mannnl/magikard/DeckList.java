package com.example.mannnl.magikard;

/**
 * Created by mannnl on 1/5/2018.
 */

public class DeckList {

    private String mName;
    private String mFormat;

    private boolean delete;

    DeckList(String Name, String Format, boolean delete){
        this.mName = Name;
        this.mFormat = Format;
        this.delete = delete;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmFormat() {
        return mFormat;
    }

    public void setmFormat(String mFormat) {
        this.mFormat = mFormat;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete() {
        this.delete = !delete;
    }

}
