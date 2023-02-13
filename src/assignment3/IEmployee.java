package assignment3;

//All methods that Company needs access to.
public interface IEmployee {
    String getId();

    String getName();

    double getRawGrossSalary();

    double getGrossSalary();

    double getNetSalary();

    void setName(String newName);

    void setGrossSalary(double originalGrossSalary);

    //Company can't access these unless they are in the interface
    String getDegree();

    void setDegree(String degree);

    void setGPA(int gpa);

    void setDepartment(String dept);
}
