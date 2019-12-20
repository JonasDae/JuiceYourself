package be.thomasmore.juiceyourself.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.juiceyourself.Counter;
import be.thomasmore.juiceyourself.Models.Categorie;
import be.thomasmore.juiceyourself.Models.Cocktail;
import be.thomasmore.juiceyourself.Models.CocktailCounter;
import be.thomasmore.juiceyourself.Models.CocktailIngredient;
import be.thomasmore.juiceyourself.Models.Glas;
import be.thomasmore.juiceyourself.Models.Ingredient;

public class DatabaseController extends SQLiteOpenHelper {
    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "juiceyourself";

    private SQLiteDatabase dbr;
    private SQLiteDatabase dbw;

    public DatabaseController(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        dbr = this.getReadableDatabase();
        dbw = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_COCKTAIL_CREATE = "CREATE TABLE cocktail (" +
                "id INTEGER PRIMARY KEY,"+
                "naam TEXT,"+
                "categorie TEXT,"+
                "glas TEXT,"+
                "instructies TEXT,"+
                "thumb TEXT,"+
                "alcoholisch INTEGER,"+
                "ingr1 TEXT,"+
                "ingr2 TEXT,"+
                "ingr3 TEXT,"+
                "ingr4 TEXT,"+
                "ingr5 TEXT,"+
                "ingr6 TEXT,"+
                "ingr7 TEXT,"+
                "ingr8 TEXT,"+
                "ingr9 TEXT,"+
                "ingr10 TEXT,"+
                "ingr11 TEXT,"+
                "ingr12 TEXT,"+
                "ingr13 TEXT,"+
                "ingr14 TEXT,"+
                "ingr15 TEXT,"+
                "measure1 TEXT,"+
                "measure2 TEXT,"+
                "measure3 TEXT,"+
                "measure4 TEXT,"+
                "measure5 TEXT,"+
                "measure6 TEXT,"+
                "measure7 TEXT,"+
                "measure8 TEXT,"+
                "measure9 TEXT,"+
                "measure10 TEXT,"+
                "measure11 TEXT,"+
                "measure12 TEXT,"+
                "measure13 TEXT,"+
                "measure14 TEXT,"+
                "measure15 TEXT);";
        String QUERY_COUNTER_CREATE = "CREATE TABLE counter (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cocktailId INTEGER,"+
                "counter INTEGER"+
                ");";
        db.execSQL(QUERY_COCKTAIL_CREATE);
        db.execSQL(QUERY_COUNTER_CREATE);
        insertTestDataCocktail();
        insertTestDataCounter(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cocktail");
        db.execSQL("DROP TABLE IF EXISTS counter");
        onCreate(db);
    }

    public void insertTestDataCocktail() {

    }
    public void insertTestDataCounter(SQLiteDatabase db) {
        CocktailCounter c;
        ContentValues values;
        c = new CocktailCounter(1,17222,3);
        values = new ContentValues();
        values.put("cocktailId", c.getCocktailId());
        values.put("counter", c.getCounter());
        db.insert("counter",null, values);

        c = new CocktailCounter(4,13501,6);
        values = new ContentValues();
        values.put("cocktailId", c.getCocktailId());
        values.put("counter", c.getCounter());
        db.insert("counter",null, values);

        c = new CocktailCounter(7,17225,9);
        values = new ContentValues();
        values.put("cocktailId", c.getCocktailId());
        values.put("counter", c.getCounter());
        db.insert("counter",null, values);

        c = new CocktailCounter(10,17837,12);
        values = new ContentValues();
        values.put("cocktailId", c.getCocktailId());
        values.put("counter", c.getCounter());
        db.insert("counter",null, values);
    }
    public long insertCocktail(Cocktail c) {
        SQLiteDatabase db = dbw;
        ContentValues values = new ContentValues();

        values.put("naam", c.getNaam());
        values.put("categorie", c.getCategorie().getNaam());
        values.put("glas", c.getGlas().getNaam());
        values.put("instructies", c.getInstructies());
        values.put("thumb", c.getThumbnail());
        values.put("alcoholisch", c.isAlcoholisch());
        for(int i=1;i<16;i++) {
            values.put("ingr"+i, "null");
            values.put("measure"+i, "null");
        }
        int i=0;
        for(i=0;i<c.getIngredienten().size();i++){
            values.put("ingr"+(i+1), c.getIngredienten().get(i).getNaam());
            values.put("measure"+(i+1), c.getIngredienten().get(i).getHoeveelheid());
        }

        long id =  db.insert("cocktail",null, values);
        return id;
    }
    public long insertCounter(CocktailCounter c) {
        SQLiteDatabase db = dbw;
        ContentValues values = new ContentValues();

        values.put("cocktailId", c.getCocktailId());
        values.put("counter", c.getCounter());

        long id =  db.insert("counter",null, values);
        return id;
    }
    public boolean updateCounter(CocktailCounter c) {
        SQLiteDatabase db = dbw;
        ContentValues values = new ContentValues();

        values.put("cocktailId", c.getCocktailId());
        values.put("counter", c.getCounter());

        int rows = db.update("counter",
                                    values,
                        "id = ?",
                                    new String[] { String.valueOf(c.getId())});
        return rows > 0;
    }
    public CocktailCounter getCounterByCocktail(long cocktailId) {

        Cursor cursor = dbr.query(
                "counter",      // tabelnaam
                new String[] { "id", "cocktailId", "counter"}, // kolommen
                "cocktailId = ?",  // selectie
                new String[] { String.valueOf(cocktailId) }, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);          // ??

        CocktailCounter counter = new CocktailCounter(0,(int)cocktailId,0);

        if (cursor.moveToFirst()) {
            counter = new CocktailCounter(cursor.getLong(0), cursor.getInt(1), cursor.getInt(2));
        }
        cursor.close();
        return counter;
    }
    public List<Cocktail> getCocktails() {
        List<Cocktail> outlist = new ArrayList<Cocktail>();
        String QUERY_COUNTER_SELECT_ALL = "SELECT * FROM cocktail";
        SQLiteDatabase db = dbr;
        Cursor cursor = db.rawQuery(QUERY_COUNTER_SELECT_ALL, null);
        if(cursor.moveToFirst()) {
            do{
                Categorie cat = new Categorie();
                Glas glas = new Glas();
                Cocktail c = new Cocktail();
                c.setId(cursor.getLong(0));
                c.setNaam(cursor.getString(1));
                cat.setNaam(cursor.getString(2));
                c.setCategorie(cat);
                glas.setNaam(cursor.getString(3));
                c.setGlas(glas);
                c.setInstructies(cursor.getString(4));
                c.setThumbnail(cursor.getString(5));
                c.setAlcoholisch(cursor.getInt(5)>0);
                for(int i=0;i<15;i++) {
                    CocktailIngredient ing = new CocktailIngredient();
                    ing.setNaam(cursor.getString(6+i));
                    ing.setHoeveelheid(cursor.getString(21+i));
                    c.addIngredient(ing);
                }
                outlist.add(c);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return outlist;
    }
    public List<CocktailCounter> getCounters() {
        List<CocktailCounter> outlist = new ArrayList<CocktailCounter>();
        String QUERY_COUNTER_SELECT_ALL = "SELECT * FROM counter";
        SQLiteDatabase db = dbr;
        Cursor cursor = db.rawQuery(QUERY_COUNTER_SELECT_ALL, null);
        if(cursor.moveToFirst()) {
            do{
                CocktailCounter c = new CocktailCounter(cursor.getLong(0),cursor.getInt(1),cursor.getInt(2));
                outlist.add(c);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return outlist;
    }
    public List<CocktailCounter> getTop() {
        List<CocktailCounter> outlist = new ArrayList<CocktailCounter>();
        String QUERY_COUNTER_SELECT_ALL = "SELECT * FROM counter ORDER BY counter DESC LIMIT 10";
        SQLiteDatabase db = dbr;
        Cursor cursor = db.rawQuery(QUERY_COUNTER_SELECT_ALL, null);
        if(cursor.moveToFirst()) {
            do{
                CocktailCounter c = new CocktailCounter(cursor.getLong(0),cursor.getInt(1),cursor.getInt(2));
                outlist.add(c);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return outlist;
    }
}
