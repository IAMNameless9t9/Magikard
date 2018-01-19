package com.example.mannnl.magikard;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mannnl on 1/5/2018.
 */

public class DeckList {

    private String mName;
    private String mFormat;

    private ArrayList<PokemonCard> mPokemonCards;
    private ArrayList<TrainerCard> mTrainerCards;
    private ArrayList<EnergyCard> mEnergyCards;

    private boolean delete;

    DeckList(String Name, String Format, ArrayList<PokemonCard> PokemonCards, ArrayList<TrainerCard> TrainerCards, ArrayList<EnergyCard> EnergyCards, boolean delete){
        this.mName = Name;
        this.mFormat = Format;
        this.mPokemonCards = PokemonCards;
        this.mTrainerCards = TrainerCards;
        this.mEnergyCards = EnergyCards;
        this.delete = delete;
    }

    DeckList(){

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

    public ArrayList<PokemonCard> getmPokemonCards() {
        return mPokemonCards;
    }

    public void setmPokemonCards(ArrayList<PokemonCard> mPokemonCards) {
        this.mPokemonCards = mPokemonCards;
    }

    public ArrayList<TrainerCard> getmTrainerCards() {
        return mTrainerCards;
    }

    public void setmTrainerCards(ArrayList<TrainerCard> mTrainerCards) {
        this.mTrainerCards = mTrainerCards;
    }

    public ArrayList<EnergyCard> getmEnergyCards() {
        return mEnergyCards;
    }

    public void setmEnergyCards(ArrayList<EnergyCard> mEnergyCards) {
        this.mEnergyCards = mEnergyCards;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete() {
        this.delete = !delete;
    }

}
