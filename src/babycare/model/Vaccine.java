package babycare.model;

public class Vaccine extends HealthService {
    private String ageRange;
    private String description;

    public Vaccine(String name, String ageRange, String description) {
        this.name = name;
        this.ageRange = ageRange;
        this.description = description;
    }

    @Override
    public String getDetails() {
        return "— " + name + "\n    توضیح: " + description + "\n";
    }

    public String getAgeRange() { return ageRange; }
}
