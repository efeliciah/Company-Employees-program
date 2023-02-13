package assignment3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//In AbsCompany there are "hidden" methods that are used in the public methods in Company

public abstract class AbsCompany{
    final Map<String, IEmployee> Employees;
    final Map<String, Integer> NumOfDeg;

    public AbsCompany() {
        this.Employees = new LinkedHashMap<>();
        this.NumOfDeg = new HashMap<>();
    }

    //Following methods checks for exceptions
    protected void usedID(String id) throws CompanyException { //Throws exception if the id we are trying to register is already used.
        if (this.Employees.containsKey(id)) {
            throw new CompanyException("Cannot register. ID " + id + " is already registered.");
        }
    }

    protected void IdNotReg(String id) throws CompanyException { //Throws exception if the id we are trying to use isn't registered.
        if (!this.Employees.containsKey(id)) {
            throw new CompanyException("Employee " + id + " was not registered yet.");
        }
    }

    protected void noEmployees() throws CompanyException {
        if (Employees.isEmpty()) {
            throw new CompanyException("No employees registered yet.");
        }
    }

    //Keeping track of employees with degrees
    protected void updateNumOfDeg(String degree, int change) {
        if (NumOfDeg.containsKey(degree)) {
            int i = NumOfDeg.get(degree);
            NumOfDeg.put(degree, change + i);
            if (NumOfDeg.get(degree) <= 0) {
                NumOfDeg.remove(degree);
            }
        } else {
            NumOfDeg.put(degree, 1);
        }
    }

    //Promoting employees
    protected void promotion(String id, String employeeType) throws CompanyException {
        IdNotReg(id);
        IEmployee employee = Employees.get(id);
        Employees.remove(id);
        if (employeeType.equals("Intern")) {
            Employees.put(id, new Intern(id, employee.getName(), employee.getRawGrossSalary(), 0));
        } else if (employeeType.equals("Manager")) {
            Employees.put(id, new Manager(id, employee.getName(), employee.getRawGrossSalary(), "MSc"));
        } else if (employeeType.equals("Director")) {
            Employees.put(id, new Director(id, employee.getName(), employee.getRawGrossSalary(), "MSc", "Business"));
        }
    }

    //Adding employees to the hashmap
    protected String addEmployee(String id, IEmployee employee) throws CompanyException {
        usedID(id);
        Employees.put(id, employee);
        return "Employee " + employee.getId() + " was registered successfully.";
    }

}
