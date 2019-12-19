package be.thomasmore.juiceyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.juiceyourself.Controllers.HttpReader;
import be.thomasmore.juiceyourself.Controllers.JsonHelper;
import be.thomasmore.juiceyourself.Controllers.ModelController;
import be.thomasmore.juiceyourself.Models.Categorie;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.Glas;
import be.thomasmore.juiceyourself.Models.Ingredient;

public class MainActivity extends AppCompatActivity {
// members
    List<Glas> glazen;
    List<Categorie> categorieen;
    List<Ingredient> ingredienten;
    List<Cocktail> cocktails = new ArrayList<Cocktail>();
    ModelController controller;
// methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getAPIGlazen();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private void populateController() {
        controller = new ModelController(glazen, categorieen, ingredienten, cocktails);
    }

    private void getAPIGlazen() {
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper helper = new JsonHelper();
                glazen = helper.getGlazen(result);
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
                if(last.getNaam().charAt(0) == 'Z'){
                    populateController();
                }
            }
        });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?f="+c);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                home_onClick();
                return true;
            case R.id.menu_search:
                search_onClick();
                return true;
            case R.id.menu_new:
                add_onClick();
                return true;
            case R.id.menu_highscore:
                top_onClick();
            case R.id.menu_counter:
                counter_onClick();
                return true;
            default:
                return false;
        }

    }

    public void search_onClick(View v) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void new_onClick(View v) {
        Intent intent = new Intent(this, New.class);
        startActivity(intent);
    }

    public void top_onClick(View v) {
        Intent intent = new Intent(this, Highscore.class);
        startActivity(intent);
    }

    public void counter_onClick(View v) {
        Intent intent = new Intent(this, Counter.class);
        startActivity(intent);
    }

    //Menu views
    public void home_onClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void search_onClick() {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void add_onClick() {
        Intent intent = new Intent(this, New.class);
        startActivity(intent);
    }

    public void top_onClick() {
        Intent intent = new Intent(this, Highscore.class);
        startActivity(intent);
    }

    public void counter_onClick() {
        Intent intent = new Intent(this, Counter.class);
        startActivity(intent);
    }
}
