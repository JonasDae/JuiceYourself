package be.thomasmore.juiceyourself;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
            // TODO: debug info
                for(int i=0;i<cocktails.size();i++) {
                    Log.e("INFO",cocktails.get(i).toString());

                }
        }
    });
        reader.execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a");

    }
}
