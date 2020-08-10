package be.thomasmore.juiceyourself;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import be.thomasmore.juiceyourself.Controllers.ModelController;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.adapters.IngredientListAdapter;

public class Details extends AppCompatActivity {
// members
    Cocktail detailCocktail;
    TextView textNaam;
    TextView textCategorie;
    TextView textGlas;
    TextView textAlcoholisch;
    TextView textInstructies;
    ListView list;
// methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        detailCocktail = (Cocktail) getIntent().getSerializableExtra("detailCocktail");

// aliasen voor alle UI componenten da we nodig hebben
        textNaam =(TextView)findViewById(R.id.cocktail_naam);
        textCategorie =(TextView)findViewById(R.id.cocktail_categorie);
        textGlas =(TextView)findViewById(R.id.cocktail_glas);
        textAlcoholisch =(TextView)findViewById(R.id.cocktail_alcoholisch);
        textInstructies =(TextView)findViewById(R.id.cocktail_instructies);
        list = (ListView) findViewById(R.id.list);
// lijsten opvullen, zie adapter
        textNaam.setText(detailCocktail.getNaam());
        textCategorie.setText(detailCocktail.getCategorie().getNaam());
        textGlas.setText(detailCocktail.getGlas().getNaam());
        textAlcoholisch.setText(String.valueOf(detailCocktail.isAlcoholisch()));
        textInstructies.setText(detailCocktail.getInstructies());
        IngredientListAdapter adapterList = new IngredientListAdapter(getApplicationContext(),detailCocktail.getIngredienten());
        list.setAdapter(adapterList);
    }

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
