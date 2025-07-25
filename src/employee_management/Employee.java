package employee_management;

public class Employee {
    private String id, fullName, email, department, designation, gender, education, skill;
    private double salary;
    private String imagePath; 

    
    public Employee(String id, String fullName, String email, String department, String designation, double salary,
                    String gender, String education, String skill, String imagePath) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.gender = gender;
        this.education = education;
        this.skill = skill;
        this.imagePath = imagePath;
    }

    // Getter methods
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }
    public String getGender() { return gender; }
    public String getEducation() { return education; }
    public String getSkill() { return skill; }
    public String getImagePath() { return imagePath; } 

    // Setter methods
    public void setId(String id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
    public void setDesignation(String designation) { this.designation = designation; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setGender(String gender) { this.gender = gender; }
    public void setEducation(String education) { this.education = education; }
    public void setSkill(String skill) { this.skill = skill; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; } 

    @Override
    public String toString() {
        return id + " - " + fullName;
    }
}
