package Model;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class HolidayModel {
    
    private HolidayDAO holidayDAO;

   
    public HolidayModel(HolidayDAO holidayDAO) {
        this.holidayDAO = holidayDAO;
    }

   
    public boolean addHoliday(Employee employee, String startDate, String endDate, HolidayType holidayType) {
        try {
            return holidayDAO.addHoliday(employee, startDate, endDate, holidayType);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lister tous les congés
    public List<Holiday> listAllHolidays() {
        try {
            return holidayDAO.getAllHolidays();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

   
    public boolean deleteHoliday(int holidayId) {
        try {
            return holidayDAO.deleteHoliday(holidayId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateHoliday(int holidayId, String startDate, String endDate, HolidayType holidayType) {
        try {
            return holidayDAO.updateHoliday(holidayId, startDate, endDate, holidayType);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
