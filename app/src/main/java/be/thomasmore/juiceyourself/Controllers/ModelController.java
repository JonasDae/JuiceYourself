package be.thomasmore.juiceyourself.Controllers;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.thomasmore.juiceyourself.Models.Categorie;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.CocktailCounter;
import be.thomasmore.juiceyourself.Models.Glas;
import be.thomasmore.juiceyourself.Models.Ingredient;

public class ModelController implements Serializable {
    // members
    List<Glas> glazen;
    List<Categorie> categorieen;
    List<Ingredient> ingredienten;
    List<Cocktail> cocktails;

    List<Cocktail> searchResult;
    Cocktail detailCocktail;

    // methods
    public ModelController(List<Glas> glazen, List<Categorie> categorieen, List<Ingredient> ingredienten, List<Cocktail> cocktails) {
        this.glazen = glazen;
        this.categorieen = categorieen;
        this.ingredienten = ingredienten;
        this.cocktails = cocktails;
    }
    public List<Cocktail> searchCocktails(String regexNaam, String glas, String categorie, String ingredient) {
        int searchFlag = 0;
        if(!glas.equals("Alle"))
            searchFlag |= 1;
        if(!categorie.equals("Alle"))
            searchFlag |= 2;
        if(!ingredient.equals("Alle"))
            searchFlag |= 4;

        List<Cocktail> outlist = new ArrayList<Cocktail>();
        for(Cocktail cocktail: cocktails) {
            int matches = 0;
            if(!cocktail.getNaam().toLowerCase().contains(regexNaam.toLowerCase()))
                continue;
            if(cocktail.getGlas().getNaam().toLowerCase().equals(glas.toLowerCase()))
                matches |= 1;
            if(cocktail.getCategorie().getNaam().toLowerCase().equals(categorie.toLowerCase()))
                matches |= 2;
            if(cocktail.checkIngredient(ingredient))
                matches |= 4;

            if(matches == searchFlag)
                outlist.add(cocktail);
        }
        this.searchResult = outlist;
        return outlist;
    }

    public List<Cocktail> getSearchResult() {
        return searchResult;
    }

    public Cocktail getDetailCocktail() {
        return detailCocktail;
    }
    public void setDetailCocktail(Cocktail detailCocktail) {
        this.detailCocktail = detailCocktail;
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
        String[] out = new String[glazen.size()+1];
        out[0] = "Alle";
        for (int i = 1; i < glazen.size(); i++) {
            out[i] = glazen.get(i).getNaam();
        }
        return out;
    }

    public String[] getCategorieenValues() {
        String[] out = new String[categorieen.size()+1];
        out[0] = "Alle";
        for (int i = 1; i < categorieen.size(); i++) {
            out[i] = categorieen.get(i).getNaam();
        }
        return out;
    }
    public String[] getIngredientValues() {
        String[] out = new String[ingredienten.size()+1];
        out[0] = "Alle";
        for (int i = 1; i < ingredienten.size(); i++) {
            out[i] = ingredienten.get(i).getNaam();
        }
        return out;
    }
}
