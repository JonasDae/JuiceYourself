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
import android.widget.Toast;

import be.thomasmore.juiceyourself.Controllers.DatabaseController;
import be.thomasmore.juiceyourself.Controllers.HttpReader;
import be.thomasmore.juiceyourself.Controllers.JsonHelper;
import be.thomasmore.juiceyourself.Controllers.ModelController;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.CocktailCounter;
import be.thomasmore.juiceyourself.adapters.SpinnerAdapter;

public class Counter extends AppCompatActivity {
// members
//    ModelController controller;
    DatabaseController dbc;
    CocktailCounter counter;
    Spinner spinnerCocktail;
    String[] cocktailList;
    // methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// lokale database nodig voor weg te schrijven
        dbc = DatabaseController.getInstance(this);
// dees zijn die spinners, zie adapter
        spinnerCocktail = (Spinner) findViewById(R.id.spinnerCocktail);
        fillCocktailSpinner();
    }
    public void fillCocktailSpinner()  {
        cocktailList = new String[0];
        for(char i='a';i<='z';i++)
        {
            HttpReader reader = new HttpReader();
            reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                                                        @Override
                                                        public void resultReady(String result) {
                            JsonHelper helper = new JsonHelper();
                            String[] cocktails = helper.getCocktailValues(result);
// 2 letters zonder cocktails
                            if(cocktails.length > 0)
                            {
                                String[] tmp = new String[cocktails.length + cocktailList.length];
                                int index = 0;
                                for(String elem: cocktails)
                                {
                                    tmp[index] = elem;
                                    index++;
                                }
                                for(String elem: cocktailList)
                                {
                                    tmp[index] = elem;
                                    index++;
                                }
                                cocktailList = new String[tmp.length];
                                cocktailList = tmp.clone();

                                if(cocktails[0].toLowerCase().charAt(0) == 'z')
                                {
                                    Log.d("DBG",cocktails[0]);

                                    SpinnerAdapter adapterCocktail = new SpinnerAdapter(getApplicationContext(), cocktailList);
                                    spinnerCocktail.setAdapter(adapterCocktail);
                                }
                            }
                }
            });
            reader.execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?f="+i);
        }
    }
    // counter incrementeren in lokale db
    public void JUICE_UP_BRO(View v) {
        Cocktail cocktail = dbc.getCocktailByName((String)spinnerCocktail.getSelectedItem());
        if(cocktail.getId() >= 0)
            updateCounter(cocktail);
        else
        {

            HttpReader reader = new HttpReader();
            reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                                                @Override
                                                public void resultReady(String result) {
                    JsonHelper helper = new JsonHelper();
                    Cocktail c = helper.getCocktail(result);
                    updateCounter(c);
                }
            });
            reader.execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+(String)spinnerCocktail.getSelectedItem());
        }
   }
    public void updateCounter(Cocktail cocktail)
    {
        counter = dbc.getCounterByCocktail(cocktail.getId());
        counter.setCounter(counter.getCounter()+1);
        if(!dbc.updateCounter(counter))
            counter.setId(dbc.insertCounter(counter));

        counter = dbc.getCounterByCocktail(cocktail.getId());
        Toast toast = Toast.makeText(getApplicationContext(),"Schol!",Toast.LENGTH_LONG);
        toast.show();
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
