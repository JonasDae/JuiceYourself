package be.thomasmore.juiceyourself;

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
                categorie.setNaam(jsonobj.getString("strGlass"));
                lijst.add(categorie);
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
/*
    public SearchResult<Vak> getVakken(String jsonTekst) {
        SearchResult<Vak> lijst = new ArrayList<Vak>();

        try {
            JSONArray jsonArrayVakken = new JSONArray(jsonTekst);
            for (int i = 0; i < jsonArrayVakken.length(); i++) {
                JSONObject jsonObjectVak = jsonArrayVakken.getJSONObject(i);

                Vak vak = new Vak();
                vak.setId(jsonObjectVak.getLong("id"));
                vak.setNaam(jsonObjectVak.getString("naam"));
                lijst.add(vak);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return lijst;
    }

    public Student getStudent(String jsonTekst) {
        Student student = new Student();



        return student;
    }

    public School getSchool(String jsonTekst) {
        School school = null;

        try {
            JSONObject jsonObjectSchool = new JSONObject(jsonTekst);
            school = new School();
            school.setNaam(jsonObjectSchool.getString("naam"));
            school.setStraat(jsonObjectSchool.getString("straat"));
            school.setGemeente(jsonObjectSchool.getString("gemeente"));

            JSONArray jsonArrayTelefoonnummers = jsonObjectSchool.getJSONArray("telefoonnummers");
            for (int i = 0; i < jsonArrayTelefoonnummers.length(); i++) {
                JSONObject jsonObjectTelefoonnummer = jsonArrayTelefoonnummers.getJSONObject(i);

                Telefoonnummer telefoonnummer = new Telefoonnummer();
                telefoonnummer.setPlaats(jsonObjectTelefoonnummer.getString("plaats"));
                telefoonnummer.setNummer(jsonObjectTelefoonnummer.getString("nummer"));
                school.addTelefoonnummer(telefoonnummer);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return school;
    }

    public JSONObject getJsonStudent (Student student) {
        JSONObject jsonObjectStudent = new JSONObject();
        try {
            jsonObjectStudent.put("voornaam", student.getVoornaam());
            jsonObjectStudent.put("naam", student.getNaam());
            jsonObjectStudent.put("klas", student.getKlas());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jsonObjectStudent;
    }

*/
}

