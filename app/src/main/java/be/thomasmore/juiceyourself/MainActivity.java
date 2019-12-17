package be.thomasmore.juiceyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

    public void search_onClick(View v) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void add_onClick(View v) {
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
}
