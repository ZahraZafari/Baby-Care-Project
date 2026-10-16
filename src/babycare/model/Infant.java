package babycare.model;

public class Infant {
    private String name;
    private int ageInMonths;
    private double weight;

    public Infant(String name, int ageInMonths, double weight) {
        this.name = name;
        this.ageInMonths = ageInMonths;
        this.weight = weight;
    }

    public String getName() { return name; }
    public int getAgeInMonths() { return ageInMonths; }
    public double getWeight() { return weight; }
}
