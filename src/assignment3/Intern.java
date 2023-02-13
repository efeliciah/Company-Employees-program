package assignment3;

//Extends AbsEmployee and not Employee, since Employee has other calculations in constructor than the ones necessary for the intern.
//calculateGrossSalary needs the intern to already have the gpa registered, but super() needs to come first...
public class Intern extends AbsEmployee {
    private int GPA;

    public Intern(String id, String name, double grossSalary, int gpa) {
        super(id, name, grossSalary);
        setGPA(gpa);
        this.netSalary = this.grossSalary;      //Apparently interns do not pay their taxes.
    }

    @Override
    public String toString() {
        return super.toString() + " GPA: " + this.GPA;
    }

    @Override
    public void setGPA(int gpa) throws CompanyException {
        if (gpa > 10 || gpa < 0) {
            throw new CompanyException(gpa + " outside range. Must be between 0-10.");
        } else {
            this.GPA = gpa;
            this.grossSalary = calculateGrossSalary();
            this.netSalary = this.grossSalary;
        }
    }

    @Override
    protected double calculateGrossSalary() {
        double[] bonus;
        if (GPA <= 5) {
            bonus = new double[]{0, 0};
        } else if (GPA < 8) {
            bonus = new double[]{1, 0};
        } else {
            bonus = new double[]{1, 1000};
        }
        return this.rawGrossSalary * bonus[0] + bonus[1];
    }

}
