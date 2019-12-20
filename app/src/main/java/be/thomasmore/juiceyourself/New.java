package be.thomasmore.juiceyourself;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import be.thomasmore.juiceyourself.Controllers.DatabaseController;
import be.thomasmore.juiceyourself.Controllers.ModelController;
import be.thomasmore.juiceyourself.Models.Categorie;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.CocktailIngredient;
import be.thomasmore.juiceyourself.Models.Glas;
import be.thomasmore.juiceyourself.adapters.IngredientListAdapter;
import be.thomasmore.juiceyourself.adapters.SpinnerAdapter;

public class New extends AppCompatActivity {

    // members
    DatabaseController dbc;
    ModelController controller;
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

        dbc = new DatabaseController(this);
        controller = (ModelController) getIntent().getSerializableExtra("ModelController");
        spinnerGlas = (Spinner) findViewById(R.id.spinnerGlas);
        spinnerCategorie = (Spinner) findViewById(R.id.spinnerCategorie);
        spinnerIngredient = (Spinner) findViewById(R.id.spinnerIngrediënt);
        textNaam = (EditText) findViewById(R.id.Cocktail) ;
        Instructies= (EditText) findViewById(R.id.text_instructies) ;
        ingredientAmnt = (EditText) findViewById(R.id.ingredientAmnt) ;
        list = (ListView) findViewById(R.id.list);

        SpinnerAdapter adapterGlas = new SpinnerAdapter(getApplicationContext(),controller.getGlazenValues());
        spinnerGlas.setAdapter(adapterGlas);
        SpinnerAdapter adapterCategorie = new SpinnerAdapter(getApplicationContext(),controller.getCategorieenValues());
        spinnerCategorie.setAdapter(adapterCategorie);
        SpinnerAdapter adapterIngredient = new SpinnerAdapter(getApplicationContext(),controller.getIngredientValues());
        spinnerIngredient.setAdapter(adapterIngredient);
        IngredientListAdapter adapterList = new IngredientListAdapter(getApplicationContext(),out.getIngredienten());
        list.setAdapter(adapterList);
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
        out.setId(1234567890);

        out.setNaam(textNaam.getText().toString());
        out.setCategorie(cat);
        out.setGlas(glas);

        out.setAlcoholisch(true);
        out.setInstructies(Instructies.getText().toString());
        out.setThumbnail("");
        out.debugPrint();
        out.setId(dbc.insertCocktail(out));
        controller.addCocktail(out);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ModelController", controller);
        startActivity(intent);
    }
    public void ing_onClick(View v) {
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
        intent.putExtra("ModelController", controller);
        startActivity(intent);
    }

    public void search_onClick() {
        Intent intent = new Intent(this, Search.class);
        intent.putExtra("ModelController", controller);
        startActivity(intent);
    }

    public void add_onClick() {
        Intent intent = new Intent(this, New.class);
        intent.putExtra("ModelController", controller);
        startActivity(intent);
    }

    public void top_onClick() {
        Intent intent = new Intent(this, Highscore.class);
        intent.putExtra("ModelController", controller);
        startActivity(intent);
    }

    public void counter_onClick() {
        Intent intent = new Intent(this, Counter.class);
        intent.putExtra("ModelController", controller);
        startActivity(intent);
    }

    public void hulp_onClick() {
        Intent intent = new Intent(this, Hulp.class);
        intent.putExtra("ModelController", controller);
        startActivity(intent);
    }
}
