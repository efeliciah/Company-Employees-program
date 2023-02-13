package assignment3;

public class Manager extends AbsEmployee {
    private Degree degree;

    public Manager(String id, String name, double OriginalGrossSalary, String degree) throws CompanyException {
        super(id, name, OriginalGrossSalary);
        setDegree(degree);
    }

    @Override
    public String getDegree() {
        return this.degree.name;
    }

    @Override
    public String toString() {
        return this.degree.toString() + " " + super.toString();
    }

    @Override
    protected double calculateGrossSalary() {
        return this.rawGrossSalary + this.rawGrossSalary * this.degree.bonusMod;
    }

    @Override
    public void setDegree(String degree) {
        if ((degree.equals(Degree.BSc.name) || degree.equals(Degree.MSc.name) || degree.equals(Degree.PhD.name))) {
            this.degree = Degree.valueOf(degree);
            this.grossSalary = truncate(calculateGrossSalary());
            this.netSalary = calculateNetSalary();
        } else {
            throw new CompanyException("Degree must be one of the options: BSc, MSc or PhD.");
        }
    }

    public enum Degree {
        BSc(0.1, "BSc"), MSc(0.2, "MSc"), PhD(0.35, "PhD");
        final double bonusMod;
        final String name;

        Degree(double bonusMod, String name) {
            this.bonusMod = bonusMod;
            this.name = name;
        }
    }
}
