package be.thomasmore.juiceyourself.Models;

public class CocktailCounter {
    // members
    private long id;
    private int cocktailId;
    private int counter;

    // methods
    public CocktailCounter() {
    }
    public CocktailCounter(long id, int cocktailId, int counter) {
        this.id =id;
        this.cocktailId =cocktailId;
        this.counter = counter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(int cocktailId) {
        this.cocktailId = cocktailId;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
