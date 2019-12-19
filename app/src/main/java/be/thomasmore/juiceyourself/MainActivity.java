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
    ModelController controller;
// methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = (ModelController) getIntent().getSerializableExtra("ModelController");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //Menu views
    public void home_onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void search_onClick(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void new_onClick(View view) {
        Intent intent = new Intent(this, New.class);
        startActivity(intent);
    }

    public void top_onClick(View view) {
        Intent intent = new Intent(this, Highscore.class);
        startActivity(intent);
    }

    public void counter_onClick(View view) {
        Intent intent = new Intent(this, Counter.class);
        startActivity(intent);
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
        Intent intent = new Intent(this, SearchResult.class);
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
