package be.thomasmore.juiceyourself;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.thomasmore.juiceyourself.Controllers.ModelController;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.adapters.SpinnerAdapter;

public class Search extends AppCompatActivity {

    ModelController controller;
    Spinner spinnerGlas;
    Spinner spinnerCategorie;
    Spinner spinnerIngredient;
    TextView textNaam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        controller = (ModelController) getIntent().getSerializableExtra("ModelController");

        spinnerGlas = (Spinner) findViewById(R.id.spinnerGlas);
        spinnerCategorie = (Spinner) findViewById(R.id.spinnerCategorie);
        spinnerIngredient = (Spinner) findViewById(R.id.spinnerIngrediënt);
        textNaam = (TextView) findViewById(R.id.Cocktail) ;

        SpinnerAdapter adapterGlas = new SpinnerAdapter(getApplicationContext(),controller.getGlazenValues());
        spinnerGlas.setAdapter(adapterGlas);
        SpinnerAdapter adapterCategorie = new SpinnerAdapter(getApplicationContext(),controller.getCategorieenValues());
        spinnerCategorie.setAdapter(adapterCategorie);
        SpinnerAdapter adapterIngredient = new SpinnerAdapter(getApplicationContext(),controller.getIngredientValues());
        spinnerIngredient.setAdapter(adapterIngredient);

// options menu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }





// options menu hieronder
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void searchResult_onClick(View v) {

        String regexNaam = (String) textNaam.getText().toString();
        String glas = (String) spinnerGlas.getSelectedItem();
        String categorie = (String) spinnerCategorie.getSelectedItem();
        String ingredient = (String) spinnerIngredient.getSelectedItem();
// zoekresultaten worden opgeslagen in controller zelf
// intent vind arraylists niet leuk, daarmee
        controller.searchCocktails(regexNaam, glas, categorie, ingredient);

        Intent intent = new Intent(this, SearchResult.class);
        intent.putExtra("ModelController", controller);
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
