package be.thomasmore.juiceyourself.Models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Cocktail {
// members
    private long id;
    private String naam;
    private Categorie Categorie;
    private Glas Glas;
    private String Instructies;
    private String Thumbnail;
    private ArrayList<CocktailIngredient> Ingredienten;
    private boolean Alcoholisch;

// methods
    public Cocktail() {}

    @NonNull
    @Override
    public String toString() {
        String out = "";
        out += "ID: " + id +
                "\nnaam: " + naam +
                "\nCategorie: " + Categorie.getNaam() +
                "\nGlas: " + Glas.getNaam() +
                "\nInstructies: " + Instructies +
                "\nthumb:" + Thumbnail +
                "\nAlcohol: " + Alcoholisch;
        for(int i = 0; i < Ingredienten.size(); i++){
            out += "\n\t Ingredient: " + Ingredienten.get(i).getNaam() +
                    " : " + Ingredienten.get(i).getHoeveelheid();
        }
        return out;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public be.thomasmore.juiceyourself.Models.Categorie getCategorie() {
        return Categorie;
    }
    public void setCategorie(be.thomasmore.juiceyourself.Models.Categorie categorie) {
        Categorie = categorie;
    }
    public be.thomasmore.juiceyourself.Models.Glas getGlas() {
        return Glas;
    }
    public void setGlas(be.thomasmore.juiceyourself.Models.Glas glas) {
        Glas = glas;
    }
    public String getInstructies() {
        return Instructies;
    }
    public void setInstructies(String instructies) {
        Instructies = instructies;
    }
    public String getThumbnail() {
        return Thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
    public ArrayList<CocktailIngredient> getIngredienten() {
        return Ingredienten;
    }
    public void setIngredienten(ArrayList<CocktailIngredient> ingredienten) {
        Ingredienten = ingredienten;
    }
    public boolean isAlcoholisch() {
        return Alcoholisch;
    }
    public void setAlcoholisch(boolean alcoholisch) {
        Alcoholisch = alcoholisch;
    }
}
