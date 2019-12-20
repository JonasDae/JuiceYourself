package be.thomasmore.juiceyourself;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.juiceyourself.Controllers.DatabaseController;
import be.thomasmore.juiceyourself.Controllers.ModelController;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.CocktailCounter;

public class SearchResult extends AppCompatActivity {
    // members
    ModelController controller;
    DatabaseController dbc;
// methods
    ListView listCocktails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = (ModelController) getIntent().getSerializableExtra("ModelController");

        for (Cocktail c : controller.getSearchResult()) {
            Log.e("INFO", c.getNaam());
        }

        listCocktails=(ListView)findViewById(R.id.list_cocktails);

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,controller.getSearchResult());
        listCocktails.setAdapter(arrayAdapter);

        listCocktails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Templistview = controller.getSearchResult().get(position).toString();
                Intent intent=new Intent (SearchResult.this, Details.class);

                intent.putExtra("Listviewclickvalue", Templistview);
                startActivity(intent);
            }
        });


    }

}
