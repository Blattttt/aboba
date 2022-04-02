package sample;

public class BD {
    private String Name;
    private String LastName;
    private String Age;
    private String UserRole;
    private String EmailAddress;
    private String Office;

    public BD(String Name, String LastName, String Age, String UserRole, String EmailAddress, String Office) {
        this.Name = Name;
        this.LastName = LastName;
        this.Age = Age;
        this.UserRole = UserRole;
        this.EmailAddress = EmailAddress;
        this.Office = Office;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String UserRole) {
        this.UserRole = UserRole;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }

    public String getOffice() {
        return Office;
    }

    public void setOffice(String Office) {
        this.Office = Office;
    }
}

