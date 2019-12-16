package be.thomasmore.juiceyourself.Models;

public class CocktailIngredient {
//  members
    private long Id;
    private String Naam;
    private String Hoeveelheid;
//  methods
    public CocktailIngredient() {}
    public CocktailIngredient(long id, String naam, String hoeveelheid)
    {
        this.Id = id;
        this.Naam = naam;
        this.Hoeveelheid = hoeveelheid;
    }

    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }
    public String getNaam() {
        return Naam;
    }
    public void setNaam(String naam) {
        Naam = naam;
    }
    public String getHoeveelheid() {
        return Hoeveelheid;
    }
    public void setHoeveelheid(String hoeveelheid) {
        Hoeveelheid = hoeveelheid;
    }
}
