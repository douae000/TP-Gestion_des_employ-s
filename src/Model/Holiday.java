package Model;

import java.util.Date;

public class Holiday {
    private int id;
    private Employee employee;
    private Date startDate;
    private Date endDate;
    private String status; 

    public Holiday(Employee employee, Date startDate, Date endDate, String status) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
