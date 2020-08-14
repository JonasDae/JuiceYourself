package be.thomasmore.juiceyourself;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import be.thomasmore.juiceyourself.Controllers.DatabaseController;
import be.thomasmore.juiceyourself.Controllers.HttpReader;
import be.thomasmore.juiceyourself.Controllers.JsonHelper;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.CocktailCounter;
import be.thomasmore.juiceyourself.adapters.ToplistAdapter;

public class Highscore extends AppCompatActivity {
// members
    DatabaseController dbc;
    List<CocktailCounter> top;
    List<Cocktail> cocktails;
    ListView topListView;
// methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbc = DatabaseController.getInstance(this);
// rechtstreeks top 10 ophalen van DB en in lijst zetten, zie adapter
        this.top = dbc.getTop();
        for(CocktailCounter c: top) {
            Cocktail cocktail = dbc.getCocktailById(c.getCocktailId());
            if(!(cocktail.getNaam() == null))
            {
                c.setCocktailNaam(cocktail.getNaam());
            }
            else
            {
                HttpReader reader = new HttpReader();
                reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                    @Override
                    public void resultReady(String result) {
                        JsonHelper helper = new JsonHelper();
                        Cocktail cocktail = helper.getCocktail(result);
                        for(CocktailCounter c: top) {
                            if(c.getCocktailId() == cocktail.getId())
                                c.setCocktailNaam(cocktail.getNaam());
                        }

                        fillList();
                    }
                });
                reader.execute("https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i="+c.getCocktailId());
            }
        }
    }
    public void fillList()
    {
        topListView = (ListView) findViewById(R.id.toplist);
        ToplistAdapter adapterTop = new ToplistAdapter(getApplicationContext(), top);
        topListView.setAdapter(adapterTop);
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
