package be.thomasmore.juiceyourself.Models;

public class Glas {
// members
    private int Id;
    private String Naam;
// methods
    public Glas() {}
    public Glas(int id, String naam) {
        this.Id = id;
        this.Naam = naam;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getNaam() {
        return Naam;
    }
    public void setNaam(String naam) {
        Naam = naam;
    }
}
