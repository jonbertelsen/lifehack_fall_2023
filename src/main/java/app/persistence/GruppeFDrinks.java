package app.persistence;

public class GruppeFDrinks {

    private int d_id;
    private String name;
    private int sugar;

    public GruppeFDrinks(int d_id, String name, int sugar) {
        this.d_id = d_id;
        this.name = name;
        this.sugar = sugar;
    }

    public GruppeFDrinks(int sugar) {
        this.sugar= sugar;
    }

    public int getD_id() {
        return d_id;
    }

    public String getName() {
        return name;
    }

    public int getSugar() {
        return sugar;
    }

    @Override
    public String toString() {
        return "GruppeFDrinks{" +
                "d_id=" + d_id +
                ", name='" + name + '\'' +
                ", sugar=" + sugar +
                '}';
    }

}
