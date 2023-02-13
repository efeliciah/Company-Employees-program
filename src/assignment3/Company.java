package assignment3;

import java.util.*;

public class Company extends AbsCompany {
    public Company() {
        super();
    }

    //New Employee
    public String createEmployee(String employeeid, String employeeName, double grossSalary) {
        IEmployee employee = new Employee(employeeid, employeeName, grossSalary);
        return addEmployee(employeeid, employee);
    }

    //New Intern
    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) {
        IEmployee employee = new Intern(employeeID, employeeName, grossSalary, gpa);
        return addEmployee(employeeID, employee);
    }

    //New Manager
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) {
        IEmployee employee = new Manager(employeeID, employeeName, grossSalary, degree);
        String out = addEmployee(employeeID, employee);
        updateNumOfDeg(degree, 1);
        return out;
    }

    //New Director
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String department) {
        IEmployee employee = new Director(employeeID, employeeName, grossSalary, degree, department);
        String out = addEmployee(employeeID, employee);
        updateNumOfDeg(degree, 1);
        return out;
    }

    public String removeEmployee(String id) throws CompanyException {
        IdNotReg(id);
        try { //If we are removing an employee with a degree, we need to update our list of degrees.
            updateNumOfDeg(Employees.get(id).getDegree(), -1);
        } catch (CompanyException exception) {
            //Ignore, can't remove a degree if the removed employee doesn't have a degree.
            //Kind of ugly to use try as control flow, but I don't know how to do it otherwise.
        }
        this.Employees.remove(id);
        return "Employee " + id + " was successfully removed.";
    }

    public String printAllEmployees(){
        noEmployees();
        String out = "All registered employees:" + System.lineSeparator();
        for (String employeeId : Employees.keySet()) {
            out = out + printEmployee(employeeId) + System.lineSeparator();
        }
        return out;
    }

    public double getNetSalary(String id){
        IdNotReg(id);
        double netSalary = Employees.get(id).getNetSalary();
        System.out.println(Employees.get(id));
        System.out.println(Employees.get(id).getNetSalary());
        netSalary = (int) (netSalary * 10);
        netSalary = netSalary / 10;
        return netSalary;
    }

    public String printEmployee(String id){
        IdNotReg(id);
        return Employees.get(id).toString();
    }

    public double getTotalNetSalary(){
        noEmployees();
        return AbsEmployee.truncate(Employees.values().stream().map(IEmployee::getNetSalary).reduce((double) 0, Double::sum));
    }

    public String printSortedEmployees(){
        noEmployees();
        String out = "Employees sorted by gross salary (ascending order):" + System.lineSeparator();
        TreeMap<Double, IEmployee> ascendingOrder = new TreeMap<>();
        for (String employeeId : Employees.keySet()) {
            ascendingOrder.put(Employees.get(employeeId).getGrossSalary(), Employees.get(employeeId));
        }
        for (Double key : ascendingOrder.keySet()) {
            out = out + printEmployee(ascendingOrder.get(key).getId()) + System.lineSeparator();
        }
        return out;
    }

    public String updateEmployeeName(String id, String newName){
        IdNotReg(id);
        Employees.get(id).setName(newName);
        return updateMessage(id);
    }

    public String updateInternGPA(String id, int gpa){
        IdNotReg(id);
        Employees.get(id).setGPA(gpa);
        return updateMessage(id);
    }

    public String updateManagerDegree(String id, String degree){
        IdNotReg(id);
        updateNumOfDeg(Employees.get(id).getDegree(), -1);
        Employees.get(id).setDegree(degree);
        updateNumOfDeg(Employees.get(id).getDegree(), 1);
        return updateMessage(id);
    }


    public String updateDirectorDept(String id, String dept) {
        IdNotReg(id);
        Employees.get(id).setDepartment(dept);
        return updateMessage(id);
    }

    public String updateGrossSalary(String id, double salary) {
        IdNotReg(id);
        Employees.get(id).setGrossSalary(salary);
        return updateMessage(id);
    }

    protected String updateMessage(String id) {
        return "Employee " + id + " was updated successfully";
    }

    public Map<String, Integer> mapEachDegree() {
        noEmployees();
        return NumOfDeg;
    }

    public String promoteToIntern(String id, int gpa) {
        promotion(id, "Intern");
        Employees.get(id).setGPA(gpa);
        return id + " promoted successfully to Intern.";
    }

    public String promoteToManager(String id, String degree) {
        promotion(id, "Manager");
        Employees.get(id).setDegree(degree);
        return id + " promoted successfully to Manager.";
    }

    public String promoteToDirector(String id, String degree, String department) {
        promotion(id, "Director");
        Employees.get(id).setDegree(degree);
        Employees.get(id).setDepartment(department);
        return id + " promoted successfully to Director.";
    }
}
