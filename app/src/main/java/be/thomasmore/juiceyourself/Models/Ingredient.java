package be.thomasmore.juiceyourself.Models;

import java.io.Serializable;

public class Ingredient implements Serializable {
// members
    private long Id;
    private String Naam;
    private String Beschrijving;
    private boolean Alcoholisch;
// methods
    public Ingredient() {}
    public Ingredient(long id, String naam, String beschrijving, boolean alcoholisch) {
        this.Id = id;
        this.Naam = naam;
        this.Beschrijving = beschrijving;
        this.Alcoholisch = alcoholisch;
    }

    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }
    public String getNaam() {
        return Naam;
    }
    public void setNaam(String naam) {
        Naam = naam;
    }
    public String getBeschrijving() {
        return Beschrijving;
    }
    public void setBeschrijving(String beschrijving) {
        Beschrijving = beschrijving;
    }
    public Boolean getAlcoholisch() {
        return Alcoholisch;
    }
    public void setAlcoholisch(Boolean alcoholisch) {
        Alcoholisch = alcoholisch;
    }
}
