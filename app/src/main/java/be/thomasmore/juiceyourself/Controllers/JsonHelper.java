package be.thomasmore.juiceyourself.Controllers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.juiceyourself.Models.Categorie;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.CocktailIngredient;
import be.thomasmore.juiceyourself.Models.Glas;
import be.thomasmore.juiceyourself.Models.Ingredient;

// om binnengekomen JSON om te zetten naar java objecten
public class JsonHelper {

// https://www.thecocktaildb.com/api/json/v1/1/list.php?g=list
    public List<Glas> getGlazen(String jsontxt) {
        List<Glas> lijst = new ArrayList<Glas>();
        try {
            JSONObject jsondrinks = new JSONObject(jsontxt);
            JSONArray jsonarr = jsondrinks.getJSONArray("drinks");
            for(int i=0;i<jsonarr.length();i++) {
                JSONObject jsonobj = jsonarr.getJSONObject(i);
                Glas glas = new Glas();
                glas.setId(i);
                glas.setNaam(jsonobj.getString("strGlass"));
                lijst.add(glas);
            }
        }
        catch(JSONException e) {
            Log.e("JSON Parser","Error parsing data: " + e.toString());
        }
        return lijst;
    }
// https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list
    public List<Categorie> getCategorien(String jsontxt) {
        List<Categorie> lijst = new ArrayList<Categorie>();
        try {
            JSONObject jsondrinks = new JSONObject(jsontxt);
            JSONArray jsonarr = jsondrinks.getJSONArray("drinks");
            for(int i=0;i<jsonarr.length();i++) {
                JSONObject jsonobj = jsonarr.getJSONObject(i);
                Categorie categorie = new Categorie();
                categorie.setId(i);
                categorie.setNaam(jsonobj.getString("strCategory"));
                lijst.add(categorie);
            }
        }
        catch(JSONException e) {
            Log.e("JSON Parser","Error parsing data: " + e.toString());
        }
        return lijst;
    }
// https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list
    public List<Ingredient> getIngredienten(String jsontxt) {
        List<Ingredient> lijst = new ArrayList<Ingredient>();
        try {
            JSONObject jsondrinks = new JSONObject(jsontxt);
            JSONArray jsonarr = jsondrinks.getJSONArray("drinks");
            for(int i=0;i<jsonarr.length();i++) {
                JSONObject jsonobj = jsonarr.getJSONObject(i);
                Ingredient ingredient = new Ingredient();
                ingredient.setId(-1);
                ingredient.setNaam(jsonobj.getString("strIngredient1"));
                ingredient.setAlcoholisch(false);
                ingredient.setBeschrijving("PLEASE WAIT");
                lijst.add(ingredient);
            }
        }
        catch(JSONException e) {
            Log.e("JSON Parser","Error parsing data: " + e.toString());
        }
        return lijst;
    }
    public List<Cocktail> getCocktails(String jsontxt) {
        List<Cocktail> lijst = new ArrayList<Cocktail>();
        try {
            JSONObject jsondrinks = new JSONObject(jsontxt);
            JSONArray jsonarr = jsondrinks.getJSONArray("drinks");
            for(int i=0;i<jsonarr.length();i++) {
                JSONObject jsonobj = jsonarr.getJSONObject(i);
                Cocktail cocktail = new Cocktail();
// id
                cocktail.setId(Integer.parseInt(jsonobj.getString("idDrink")));
// naam
                cocktail.setNaam(jsonobj.getString("strDrink"));
// categorie
                Categorie categorie = new Categorie();
                categorie.setId(-1);
                categorie.setNaam(jsonobj.getString("strCategory"));
                cocktail.setCategorie(categorie);
// thumb
                cocktail.setThumbnail(jsonobj.getString("strDrinkThumb"));
// glas
                Glas glas = new Glas();
                glas.setId(-1);
                glas.setNaam(jsonobj.getString("strGlass"));
                cocktail.setGlas(glas);
                cocktail.setInstructies(jsonobj.getString("strInstructions"));
// ingredienten
                ArrayList<CocktailIngredient> ingredienten = new ArrayList<CocktailIngredient>();
                for(int j=1; j <= 15; j++)  {
                    CocktailIngredient ingredient = new CocktailIngredient();
                    ingredient.setId(-1);
                    ingredient.setNaam(jsonobj.getString("strIngredient"+j));
                    ingredient.setHoeveelheid(jsonobj.getString("strMeasure"+j));
                    if(!ingredient.getNaam().equals("null"))
                        ingredienten.add(ingredient);
                }
                cocktail.setIngredienten(ingredienten);
// alcoholisch
                String alcoholisch = jsonobj.getString("strAlcoholic");
                if(alcoholisch.matches("Alcoholic"))
                    cocktail.setAlcoholisch(true);
                else
                    cocktail.setAlcoholisch(false);
                lijst.add(cocktail );
            }
        }
        catch(JSONException e) {
            Log.e("JSON Parser","Error parsing data: " + e.toString());
        }
        return lijst;
    }
}

