package DAO;

import Model.Employee;
import java.util.List;

public interface EmployeeDAOI {
    void add(Employee employee); // Ajouter un employé
    void delete(int id); // Supprimer un employé
    List<Employee> listAll(); // Lister tous les employés
    Employee findById(int id); // Trouver un employé par ID
    void update(Employee employee, int id); // Mettre à jour un employé
}