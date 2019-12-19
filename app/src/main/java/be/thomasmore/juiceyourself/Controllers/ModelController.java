package be.thomasmore.juiceyourself.Controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.thomasmore.juiceyourself.Models.Categorie;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.Glas;
import be.thomasmore.juiceyourself.Models.Ingredient;

public class ModelController implements Serializable {
    // members
    List<Glas> glazen;
    List<Categorie> categorieen;
    List<Ingredient> ingredienten;
    List<Cocktail> cocktails;

    // methods
    public ModelController(List<Glas> glazen, List<Categorie> categorieen, List<Ingredient> ingredienten, List<Cocktail> cocktails) {
        this.glazen = glazen;
        this.categorieen = categorieen;
        this.ingredienten = ingredienten;
        this.cocktails = cocktails;
    }

    public List<Glas> getGlazen() {
        return glazen;
    }

    public List<Categorie> getCategorieen() {
        return categorieen;
    }

    public List<Ingredient> getIngredienten() {
        return ingredienten;
    }

    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    public String[] getGlazenValues() {
        String[] out = new String[glazen.size()];
        for (int i = 0; i < glazen.size(); i++) {
            out[i] = glazen.get(i).getNaam();
        }
        return out;
    }

    public String[] getCategorieenValues() {
        String[] out = new String[categorieen.size()];
        for (int i = 0; i < categorieen.size(); i++) {
            out[i] = categorieen.get(i).getNaam();
        }
        return out;
    }
}
