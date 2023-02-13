package assignment3;

public class Employee extends AbsEmployee {
    public Employee(String id, String name, double grossSalary) {       //Default employee
        super(id, name, grossSalary);
        this.grossSalary = truncate(calculateGrossSalary());
        this.netSalary = calculateNetSalary();
    }
}
