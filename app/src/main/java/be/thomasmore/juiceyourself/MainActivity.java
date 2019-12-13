package be.thomasmore.juiceyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
