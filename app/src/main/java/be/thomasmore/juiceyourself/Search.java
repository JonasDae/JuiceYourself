package be.thomasmore.juiceyourself;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import be.thomasmore.juiceyourself.Controllers.HttpReader;
import be.thomasmore.juiceyourself.Controllers.JsonHelper;
import be.thomasmore.juiceyourself.adapters.SpinnerAdapter;

public class Search extends AppCompatActivity {

    Spinner spinnerGlas;
    Spinner spinnerCategorie;
    Spinner spinnerIngredient;
    TextView textNaam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

// UI elementen alias
        spinnerGlas = (Spinner) findViewById(R.id.spinnerGlas);
        spinnerCategorie = (Spinner) findViewById(R.id.spinnerCategorie);
        spinnerIngredient = (Spinner) findViewById(R.id.spinnerIngrediënt);
        textNaam = (TextView) findViewById(R.id.Cocktail) ;
        fillCategorieSpinner();
        fillGlasSpinner();
        fillIngredientSpinner();

// options menu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // spinners en lijsten opvullen met adapters
    public void fillGlasSpinner()
    {
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper helper = new JsonHelper();
                String[] glazen = helper.getGlazenNamen(result);
                SpinnerAdapter adapterGlas = new SpinnerAdapter(getApplicationContext(),glazen);
                spinnerGlas.setAdapter(adapterGlas);
            }
        });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/list.php?g=list");
    }
    public void fillCategorieSpinner()
    {
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper helper = new JsonHelper();
                String[] categorieen = helper.getCategorieNamen(result);
                SpinnerAdapter adapterCategorie = new SpinnerAdapter(getApplicationContext(),categorieen);
                spinnerCategorie.setAdapter(adapterCategorie);
            }
        });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list");

    }
    public void fillIngredientSpinner()
    {
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper helper = new JsonHelper();
                String[] ingredienten = helper.getIngredientNamen(result);
                SpinnerAdapter adapterIngredient = new SpinnerAdapter(getApplicationContext(),ingredienten);
                spinnerIngredient.setAdapter(adapterIngredient);
            }
        });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list");

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
                return true;
            case R.id.menu_counter:
                counter_onClick();
                return true;
            default:
                return false;
        }
    }

    public void searchResult_onClick(View v) {

        String regexNaam = (String) textNaam.getText().toString();
        String glasstr = (String) spinnerGlas.getSelectedItem();
        String categoriestr = (String) spinnerCategorie.getSelectedItem();
        String ingredientstr = (String) spinnerIngredient.getSelectedItem();

        Intent intent = new Intent(this, SearchResult.class);
        intent.putExtra("regexNaam", regexNaam);
        intent.putExtra("glasstr", glasstr);
        intent.putExtra("categoriestr", categoriestr);
        intent.putExtra("ingredientstr", ingredientstr);

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
