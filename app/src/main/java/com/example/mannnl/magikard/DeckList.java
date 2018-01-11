package com.example.mannnl.magikard;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mannnl on 1/5/2018.
 */

public class DeckList {

    private String mName;
    private String mFormat;

    private PokemonCard mPokemonCards = new PokemonCard();
    private TrainerCard mTrainerCards = new TrainerCard();
    private EnergyCard mEnergyCards = new EnergyCard();

    private boolean delete;

    DeckList(String Name, String Format, PokemonCard PokemonCards, TrainerCard TrainerCards, EnergyCard EnergyCards, boolean delete){
        this.mName = Name;
        this.mFormat = Format;
        this.mPokemonCards = PokemonCards;
        this.mTrainerCards = TrainerCards;
        this.mEnergyCards = EnergyCards;
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

    public PokemonCard getmPokemonCards() {
        return mPokemonCards;
    }

    public void setmPokemonCards(PokemonCard mPokemonCards) {
        this.mPokemonCards = mPokemonCards;
    }

    public TrainerCard getmTrainerCards() {
        return mTrainerCards;
    }

    public void setmTrainerCards(TrainerCard mTrainerCards) {
        this.mTrainerCards = mTrainerCards;
    }

    public EnergyCard getmEnergyCards() {
        return mEnergyCards;
    }

    public void setmEnergyCards(EnergyCard mEnergyCards) {
        this.mEnergyCards = mEnergyCards;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete() {
        this.delete = !delete;
    }

}
