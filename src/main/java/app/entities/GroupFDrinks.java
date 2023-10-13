package app.entities;

public class GroupFDrinks {

    private int d_id;
    private String d_name;
    private double sugar;

    public GroupFDrinks(int d_id, String d_name, double sugar) {
        this.d_id = d_id;
        this.d_name = d_name;
        this.sugar = sugar;
    }

    public GroupFDrinks(double sugar) {
        //this.d_id = 0; // Set a default value for d_id, or change it as needed.
        //this.d_name = ""; // Set a default value for name, or change it as needed.
        this.sugar = sugar;
    }

    public int getD_id() {
        return d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public double getSugar() {
        return sugar;
    }

    @Override
    public String toString() {
        return "GroupFDrinks{" +
                "d_id=" + d_id +
                ", name='" + d_name + '\'' +
                ", sugar=" + sugar +
                '}';
    }

}
