package DAO;

import Model.Holiday;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HolidayDAOImpl implements GenericDAOI<Holiday> {

    @Override
    public void add(Holiday holiday) {
        String sql = "INSERT INTO Holiday (employee_id, start_date, end_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, holiday.getEmployee().getId());
            stmt.setDate(2, new java.sql.Date(holiday.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(holiday.getEndDate().getTime()));
            stmt.setString(4, holiday.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Holiday WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Holiday> listAll() {
        List<Holiday> holidays = new ArrayList<>();
        String sql = "SELECT * FROM Holiday";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                Employee employee = new EmployeeDAOImpl().findById(employeeId); 
                Holiday holiday = new Holiday(
                        employee,
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status")
                );
                holiday.setId(rs.getInt("id"));
                holidays.add(holiday);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holidays;
    }

    @Override
    public Holiday findById(int id) {
        String sql = "SELECT * FROM Holiday WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                Employee employee = new EmployeeDAOImpl().findById(employeeId); 
                return new Holiday(
                        employee,
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Holiday holiday, int id) {
        String sql = "UPDATE Holiday SET employee_id = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, holiday.getEmployee().getId());
            stmt.setDate(2, new java.sql.Date(holiday.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(holiday.getEndDate().getTime()));
            stmt.setString(4, holiday.getStatus());
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
         
        }
        
    }
}
