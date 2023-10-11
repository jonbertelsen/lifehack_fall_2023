package app.persistence;

public class GroupFDrinks {

    private int d_id;
    private String name;
    private double sugar;

    public GroupFDrinks(int d_id, String name, double sugar) {
        this.d_id = d_id;
        this.name = name;
        this.sugar = sugar;
    }

    public GroupFDrinks(double sugar) {
        //this.d_id = 0; // Set a default value for d_id, or change it as needed.
        //this.name = ""; // Set a default value for name, or change it as needed.
        this.sugar = sugar;
    }

    public int getD_id() {
        return d_id;
    }

    public String getName() {
        return name;
    }

    public double getSugar() {
        return sugar;
    }

    @Override
    public String toString() {
        return "GroupFDrinks{" +
                "d_id=" + d_id +
                ", name='" + name + '\'' +
                ", sugar=" + sugar +
                '}';
    }

}
