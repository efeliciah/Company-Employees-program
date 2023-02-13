package assignment3;

public class Director extends Manager {
    private Department department;

    public Director(String id, String name, double OriginalGrossSalary, String degree, String department) throws CompanyException {
        super(id, name, OriginalGrossSalary, degree);
        setDepartment(department);
        this.grossSalary = truncate(calculateGrossSalary());
        this.netSalary = calculateNetSalary();
    }

    @Override
    public String toString() {
        return super.toString() + " Dept: " + this.department.toString();
    }

    @Override
    protected double calculateGrossSalary() {
        double gross = super.calculateGrossSalary();
        double bonus = 5000;
        return gross + bonus;
    }

    @Override
    protected double calculateNetSalary() {
        double extraTax = 0.2;
        double moreExtraTax = 0.4;
        double taxThreshold = 30000;
        if (this.grossSalary > 50000) {
            double tax = taxThreshold * extraTax + (this.grossSalary - taxThreshold) * moreExtraTax;
            return this.grossSalary - tax;
        } else if (this.grossSalary > 30000) {
            return this.grossSalary - (this.grossSalary * extraTax);
        } else {
            return super.calculateNetSalary();
        }
    }

    @Override
    public void setDepartment(String department) throws CompanyException {
        if ((department.equals(Department.Business.deptName) || department.equals(Department.Technical.deptName) || department.equals(Department.HumanResources.deptName))) {
            department = department.replaceAll("\\s+", "");
            this.department = Department.valueOf(department);
            this.grossSalary = calculateGrossSalary();
            this.netSalary = calculateNetSalary();
        } else {
            throw new CompanyException("Department must be one of the options: Business, Human Resources or Technical.");
        }

    }

    public enum Department {
        HumanResources("Human Resources"), Technical("Technical"), Business("Business");
        private final String deptName;

        Department(String deptName) {
            this.deptName = deptName;
        }

        @Override
        public String toString() {
            return this.deptName;
        }
    }
}
