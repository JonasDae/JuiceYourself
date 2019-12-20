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

import be.thomasmore.juiceyourself.Controllers.ModelController;
import be.thomasmore.juiceyourself.adapters.SpinnerAdapter;

public class Counter extends AppCompatActivity {
// members
    ModelController controller;
    Spinner spinnerCocktail;
// methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        controller = (ModelController) getIntent().getSerializableExtra("ModelController");

        spinnerCocktail = (Spinner) findViewById(R.id.spinnerCocktail);
        SpinnerAdapter adapterCocktail = new SpinnerAdapter(getApplicationContext(), controller.getCocktailValues());
        spinnerCocktail.setAdapter(adapterCocktail);
    }

    public void JUICE_UP_BRO(View v) {
Log.e("INFO", "YEAH BRO WE JUICED NOW");
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
