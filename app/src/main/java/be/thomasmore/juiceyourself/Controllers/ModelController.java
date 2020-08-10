package be.thomasmore.juiceyourself.Controllers;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import be.thomasmore.juiceyourself.Models.Categorie;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.CocktailCounter;
import be.thomasmore.juiceyourself.Models.Glas;
import be.thomasmore.juiceyourself.Models.Ingredient;

/*
Alle data word hierin opgeslage en naar alle activities doorgegeven
zou feitelijk statisch kunnen zijn om het gemakkelijk te maken maar meh
Ziet ook zeker naar mijn schone zoekfunctie
Rest is gewoon CRUD
*/

public class ModelController {
    // members
    private List<Glas> glazen;
    private List<Categorie> categorieen;
    private List<Ingredient> ingredienten;
    private List<Cocktail> cocktails;

    private List<Cocktail> searchResult;
    private Cocktail detailCocktail;

    // singleton patroon
    private static ModelController instance;


    // methods
    public static synchronized ModelController getInstance(List<Glas> glazen, List<Categorie> categorieen, List<Ingredient> ingredienten, List<Cocktail> cocktails) {
        if(instance == null) {
            instance = new ModelController(glazen, categorieen, ingredienten, cocktails);
        }
        return instance;
    }
    private ModelController(List<Glas> glazen, List<Categorie> categorieen, List<Ingredient> ingredienten, List<Cocktail> cocktails) {
        this.glazen = glazen;
        this.categorieen = categorieen;
        this.ingredienten = ingredienten;
        this.cocktails = cocktails;
// sorteren alphabetisch
        Collections.sort(this.glazen, new Comparator<Glas>() {
            @Override
            public int compare(Glas o1, Glas o2) {
                return o1.getNaam().compareToIgnoreCase(o2.getNaam());
            }
        });
        Collections.sort(this.categorieen, new Comparator<Categorie>() {
            @Override
            public int compare(Categorie o1, Categorie o2) {
                return o1.getNaam().compareToIgnoreCase(o2.getNaam());
            }
        });
        Collections.sort(this.ingredienten, new Comparator<Ingredient>() {
            @Override
            public int compare(Ingredient o1, Ingredient o2) {
                return o1.getNaam().compareToIgnoreCase(o2.getNaam());
            }
        });
        Collections.sort(this.cocktails, new Comparator<Cocktail>() {
            @Override
            public int compare(Cocktail o1, Cocktail o2) {
                return o1.getNaam().compareToIgnoreCase(o2.getNaam());
            }
        });
    }
    public Cocktail getCocktailByName(String naam) {
        for(Cocktail c: cocktails) {
            if(c.getNaam().equals(naam))
                return c;
        }
        return null;
    }
    public List<Cocktail> searchCocktails(String regexNaam, String glas, String categorie, String ingredient) {
/*
mijn schone zoekfunctie :)
bitwise zwarte magie enzo
*/
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
    public void addCocktail(Cocktail c) {
        cocktails.add(c);
    }

    public Cocktail getCocktailById(int id) {
        for(Cocktail c: cocktails) {
            if(c.getId() == id)
                return c;
        }
        return null;
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

// eerste waarde van spinners op "Alle" zette, vooral voor zoeken en das gemakkelijk om da te late staan voor details en nieuw
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
    public String[] getCocktailValues() {
//        return dbc.getCocktailValues();
        String[] out = {"test"};
        return out;
    }
}
