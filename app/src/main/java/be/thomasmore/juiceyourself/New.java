package be.thomasmore.juiceyourself;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import be.thomasmore.juiceyourself.Controllers.DatabaseController;
import be.thomasmore.juiceyourself.Controllers.HttpReader;
import be.thomasmore.juiceyourself.Controllers.JsonHelper;
import be.thomasmore.juiceyourself.Models.Categorie;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.CocktailIngredient;
import be.thomasmore.juiceyourself.Models.Glas;
import be.thomasmore.juiceyourself.adapters.IngredientListAdapter;
import be.thomasmore.juiceyourself.adapters.SpinnerAdapter;

public class New extends AppCompatActivity {

    // members
    DatabaseController dbc;
    Spinner spinnerGlas;
    Spinner spinnerCategorie;
    Spinner spinnerIngredient;
    EditText textNaam;
    EditText ingredientAmnt;
    EditText Instructies;
    ListView list;
    Cocktail out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        out = new Cocktail();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// lokale DB nodig
        dbc = DatabaseController.getInstance(this);
// alias voor UI elementen
        spinnerGlas = (Spinner) findViewById(R.id.spinnerGlas);
        spinnerCategorie = (Spinner) findViewById(R.id.spinnerCategorie);
        spinnerIngredient = (Spinner) findViewById(R.id.spinnerIngrediënt);
        textNaam = (EditText) findViewById(R.id.Cocktail) ;
        Instructies= (EditText) findViewById(R.id.text_instructies) ;
        ingredientAmnt = (EditText) findViewById(R.id.ingredientAmnt) ;
        list = (ListView) findViewById(R.id.list);
// spinners en lijsten opvullen, zie adapters
        fillGlasSpinner();
        fillCategorieSpinner();
        fillIngredientSpinner();
        IngredientListAdapter adapterList = new IngredientListAdapter(getApplicationContext(),out.getIngredienten());
        list.setAdapter(adapterList);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void new_onClick(View v) {
        Categorie cat = new Categorie();
        cat.setNaam(spinnerCategorie.getSelectedItem().toString());
        Glas glas = new Glas();
        glas.setNaam(spinnerGlas.getSelectedItem().toString());
// placeholder: word correct weggeschreven
        out.setId(1234567890);

        out.setNaam(textNaam.getText().toString());
        out.setCategorie(cat);
        out.setGlas(glas);

        out.setAlcoholisch(true);
        out.setInstructies(Instructies.getText().toString());
        out.setThumbnail("");
        out.debugPrint();
        out.setId(dbc.insertCocktail(out));
// toevoegen aan lokale DB
//        controller.addCocktail(out);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void ing_onClick(View v) {
// ingredient lijst bijvullen, zie adapter
        CocktailIngredient i = new CocktailIngredient();
        i.setHoeveelheid(ingredientAmnt.getText().toString());
        i.setNaam(spinnerIngredient.getSelectedItem().toString());
        out.addIngredient(i);
        IngredientListAdapter adapterList = new IngredientListAdapter(getApplicationContext(),out.getIngredienten());
        list.setAdapter(adapterList);
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
            case R.id.menu_hulp:
                hulp_onClick();
                return true;
            default:
                return false;
        }
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

    public void hulp_onClick() {
        Intent intent = new Intent(this, Hulp.class);
        startActivity(intent);
    }
}
