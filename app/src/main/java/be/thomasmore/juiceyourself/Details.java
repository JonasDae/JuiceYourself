package be.thomasmore.juiceyourself;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import be.thomasmore.juiceyourself.Controllers.ModelController;

public class Details extends AppCompatActivity {
// members
    ModelController controller;
    TextView textNaam;
    TextView textCategorie;
    TextView textGlas;
    TextView textAlcoholisch;
    TextView textInstructies;
// methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = (ModelController) getIntent().getSerializableExtra("ModelController");

        textNaam =(TextView)findViewById(R.id.cocktail_naam);
        textCategorie =(TextView)findViewById(R.id.cocktail_categorie);
        textGlas =(TextView)findViewById(R.id.cocktail_glas);
        textAlcoholisch =(TextView)findViewById(R.id.cocktail_alcoholisch);
        textInstructies =(TextView)findViewById(R.id.cocktail_instructies);

        textNaam.setText(controller.getDetailCocktail().getNaam());
        textCategorie.setText(controller.getDetailCocktail().getCategorie().getNaam());
        textGlas.setText(controller.getDetailCocktail().getGlas().getNaam());
        textAlcoholisch.setText(String.valueOf(controller.getDetailCocktail().isAlcoholisch()));
        textInstructies.setText(controller.getDetailCocktail().getInstructies());
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
