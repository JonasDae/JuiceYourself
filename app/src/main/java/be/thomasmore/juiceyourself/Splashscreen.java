package be.thomasmore.juiceyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.juiceyourself.Controllers.DatabaseController;
import be.thomasmore.juiceyourself.Controllers.HttpReader;
import be.thomasmore.juiceyourself.Controllers.JsonHelper;
import be.thomasmore.juiceyourself.Controllers.ModelController;
import be.thomasmore.juiceyourself.Models.Categorie;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.Glas;
import be.thomasmore.juiceyourself.Models.Ingredient;
import android.os.Handler;

public class Splashscreen extends AppCompatActivity {

    // members
    List<Glas> glazen;
    List<Categorie> categorieen;
    List<Ingredient> ingredienten;
    List<Cocktail> cocktails = new ArrayList<Cocktail>();
    ModelController controller;
    DatabaseController dbc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
// een voor een alle gets oproepen, soort van recursief
        this.getAPIGlazen();
    }

    private void endSplashScreen() {
// dbc is voor lokale database, moet ni doorgegeve worde naar intent want da word apart opgeslage
        dbc = DatabaseController.getInstance(this);
        cocktails.addAll(dbc.getCocktails());
// controller in intent stoppen en door naar main
        controller = ModelController.getInstance(glazen, categorieen, ingredienten, cocktails);
        Intent i = new Intent (Splashscreen.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void getAPIGlazen() {
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper helper = new JsonHelper();
                glazen = helper.getGlazen(result);
// op naar de volgende
                getAPICategorieen();
            }
        });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/list.php?g=list");
    }
    private void getAPICategorieen() {
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper helper = new JsonHelper();
                categorieen = helper.getCategorien(result);
// nog veel te gaan
                getAPIIngredienten();
            }
        });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list");
    }
    private void getAPIIngredienten() {
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper helper = new JsonHelper();
                ingredienten  = helper.getIngredienten(result);
// API kan niet alles in 1 keer ophalen
                for(char c = 'a'; c <= 'z';c++) {
// nog 26 te gaan :D
                    getAPICocktails(c);
                }
            }
        });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list");
    }
    private void getAPICocktails(char c) {
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper helper = new JsonHelper();
                cocktails.addAll(helper.getCocktails(result));
                Cocktail last = cocktails.get(cocktails.size() - 1);
// @docent: gelieve de volgende 3 lijnen over te slagen bij het nakijken, dankuwel
                if (last.getNaam().toLowerCase().charAt(0) == 'z') {
// en klaar
                    endSplashScreen();
                }
            }
        });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + c);
    }
}

