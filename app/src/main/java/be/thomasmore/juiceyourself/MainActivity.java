package be.thomasmore.juiceyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.Glas;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LeesCocktails();
    }
    private void LeesGlazen() {
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                List<Glas> glazen = jsonHelper.getGlazen(result);
                // TODO: debug info
                /*
                for(int i=0;i<glazen.size();i++) {
                    Log.e("INFO",glazen.get(i).getNaam());

                }
                */
            }
        });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/list.php?g=list");
    }
    private void LeesCocktails() {
        HttpReader reader = new HttpReader();
        reader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
        @Override
        public void resultReady(String result) {
            Log.e("INFO",result);
            JsonHelper jsonHelper = new JsonHelper();
            List<Cocktail> cocktails = jsonHelper.getCocktails(result);
            /*
            // TODO: debug info
                for(int i=0;i<cocktails.size();i++) {
                    Log.e("INFO",cocktails.get(i).toString());

                }
            */
        }
    });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a");

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
            default:
                return false;
        }

    }

    public void search_onClick(View v) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void new_onClick(View v) {
        Intent intent = new Intent(this, New.class);
        startActivity(intent);
    }

    public void top_onClick(View v) {
        Intent intent = new Intent(this, Highscore.class);
        startActivity(intent);
    }

    public void counter_onClick(View v) {
        Intent intent = new Intent(this, Counter.class);
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
