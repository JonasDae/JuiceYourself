package be.thomasmore.juiceyourself.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Cocktail implements Serializable {
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
    public Cocktail() {
        Ingredienten = new ArrayList<>();
    }

    public void debugPrint() {
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
        Log.e("DEBUGPRINT", out);
    }
    @NonNull
    @Override
    public String toString() {
        return naam;
    }
    public boolean checkIngredient(String ingredient) {
        for(CocktailIngredient i: Ingredienten) {
            if(i.getNaam().toLowerCase().equals(ingredient.toLowerCase()))
                return true;
        }
        return false;
    }

    public void addIngredient(CocktailIngredient i) {
        Ingredienten.add(i);
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
