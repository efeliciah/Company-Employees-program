package assignment3;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public abstract class AbsEmployee implements IEmployee {
    private final String Id;
    private String name;
    double rawGrossSalary;
    double grossSalary;
    double netSalary;

    private final CompanyException nullName = new CompanyException("Name cannot be blank.");
    final CompanyException nonPositiveSalary = new CompanyException("Salary must be greater than zero.");

    public AbsEmployee(String id, String name, double rawGrossSalary) throws CompanyException {
        if (rawGrossSalary <= 0) {
            throw nonPositiveSalary;
        } else {
            this.rawGrossSalary = truncate(rawGrossSalary);
        }
        if (id.equals("")) {
            throw new CompanyException("ID cannot be blank.");
        } else {
            this.Id = id;
        }
        if (name.replaceAll("\\s+", "").equals("")) {
            throw nullName;
        } else {
            this.name = name;
        }
    }

    public void setName(String name) throws CompanyException {
        if (name.equals("")) {
            throw nullName;
        } else {
            this.name = name;
        }
    }

    public void setGrossSalary(double salary) throws CompanyException {
        if (salary <= 0) {
            throw nonPositiveSalary;
        } else {
            this.rawGrossSalary = truncate(salary);
            this.grossSalary = calculateGrossSalary();
            this.netSalary = calculateNetSalary();
        }
    }

    public void setGPA(int gpa) throws CompanyException {
        throw new CompanyException("Employee is not an intern.");
    }

    public void setDepartment(String dept) throws CompanyException {
        throw new CompanyException("Employee is not a director.");
    }

    public void setDegree(String degree) throws CompanyException {
        throw new CompanyException("Employee is neither a manager nor director.");
    }

    public String getDegree() throws CompanyException {
        throw new CompanyException("Employee does not have a degree.");
    }

    public double getNetSalary() {
        return this.netSalary;
    }

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getGrossSalary() {
        return grossSalary;
    }

    @Override
    public double getRawGrossSalary() {
        return rawGrossSalary;
    }

    protected double calculateNetSalary() {
        double tax = 0.1;
        return truncate(this.grossSalary - (this.grossSalary * tax));
    }

    protected double calculateGrossSalary() {
        return this.rawGrossSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbsEmployee)) return false;
        AbsEmployee that = (AbsEmployee) o;
        return Id.equals(that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        return name + "'s gross salary is " + df.format(grossSalary) + " SEK per month.";
    }

    protected static double truncate(double num) {
        num = (int) (num * 100);
        return num / 100;
    }
}
