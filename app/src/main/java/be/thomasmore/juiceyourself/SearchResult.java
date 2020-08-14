package be.thomasmore.juiceyourself;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import be.thomasmore.juiceyourself.Controllers.DatabaseController;
import be.thomasmore.juiceyourself.Controllers.HttpReader;
import be.thomasmore.juiceyourself.Controllers.JsonHelper;
import be.thomasmore.juiceyourself.Models.Cocktail;

public class SearchResult extends AppCompatActivity {
    // members
    DatabaseController dbc;
    List <Cocktail> searchresults;
// methods
    ListView listCocktails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbc = DatabaseController.getInstance(this);

        String regexNaam = (String) getIntent().getSerializableExtra("regexNaam");
        final String glasstr = (String) getIntent().getSerializableExtra("glasstr");
        final String categoriestr = (String) getIntent().getSerializableExtra("categoriestr");
        final String ingredientstr = (String) getIntent().getSerializableExtra("ingredientstr");


        searchresults = dbc.searchCocktail(regexNaam, glasstr, categoriestr, ingredientstr);
        Log.i("BROKE1", searchresults.toString());
//https://www.thecocktaildb.com/api/json/v1/1/search.php?s=regexNaam
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper helper = new JsonHelper();
                List<Cocktail> list2 = helper.searchCocktails(result, glasstr, categoriestr, ingredientstr);
                Log.i("BROKE2", list2.toString());
                searchresults.addAll(list2);
                Log.i("BROKE3", searchresults.toString());
                fillList();
            }
        });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+regexNaam);

    }
    public void fillList() {
        listCocktails = (ListView) findViewById(R.id.list_cocktails);
        // standaard adapter hier, zet array gewoon om in lijstelementen
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, searchresults);
        listCocktails.setAdapter(arrayAdapter);

// om details te kunnen kijken
        listCocktails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cocktail detailCocktail = searchresults.get(position);

                Intent intent=new Intent (SearchResult.this, Details.class);
                intent.putExtra("detailCocktail", detailCocktail);
                startActivity(intent);
            }
        });
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
