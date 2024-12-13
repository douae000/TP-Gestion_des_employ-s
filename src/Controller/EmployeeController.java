package Controller;

import DAO.EmployeeDAOI;
import DAO.EmployeeDAOImpl;
import Model.Employee;
import Model.Poste;
import Model.Role;
import View.EmployeeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;


public class EmployeeController {
    private final EmployeeView view;
    private final EmployeeDAOI dao;

    public EmployeeController(EmployeeView view) {
        this.view = view;
        this.dao = new EmployeeDAOImpl();

        // Écouteur pour le bouton Ajouter
        view.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        // Écouteur pour le bouton Lister
        view.listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listEmployees();
            }
        });

        // Écouteur pour le bouton Supprimer
        view.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        // Écouteur pour le bouton Modifier
        view.modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyEmployee();
            }
        });
    }

    // Méthode pour ajouter un employé
    private void addEmployee() {
        try {
            String nom = view.nameField.getText();
            String prenom = view.surnameField.getText();
            String email = view.emailField.getText();
            String phone = view.phoneField.getText();
            double salaire = Double.parseDouble(view.salaryField.getText());
            Role role = Role.valueOf(view.roleCombo.getSelectedItem().toString().toUpperCase());
            Poste poste = Poste.valueOf(view.posteCombo.getSelectedItem().toString().toUpperCase());

            Employee employee = new Employee(nom, prenom, email, phone, salaire, role, poste);
            dao.add(employee);
            JOptionPane.showMessageDialog(view, "Employé ajouté avec succès.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Erreur: " + ex.getMessage());
        }
    }

    
    private void listEmployees() {
        List<Employee> employees = dao.listAll();
        String[] columnNames = {"ID", "Nom", "Prénom", "Email", "Téléphone", "Salaire", "Rôle", "Poste"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Employee emp : employees) {
            Object[] row = {emp.getId(), emp.getNom(), emp.getPrenom(), emp.getEmail(), emp.getPhone(), emp.getSalaire(), emp.getRole(), emp.getPoste()};
            model.addRow(row);
        }

        view.employeeTable.setModel(model);
    }

    
    private void deleteEmployee() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(view, "Entrez l'ID de l'employé à supprimer :"));
            dao.delete(id);
            JOptionPane.showMessageDialog(view, "Employé supprimé avec succès.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Erreur: " + ex.getMessage());
        }
    }

   
    private void modifyEmployee() {
        try {
            
            int selectedRow = view.employeeTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner un employé dans le tableau.");
                return;
            }
    
            int id = (int) view.employeeTable.getValueAt(selectedRow, 0);
    
            String nom = view.nameField.getText();
            String prenom = view.surnameField.getText();
            String email = view.emailField.getText();
            String phone = view.phoneField.getText();
            double salaire = Double.parseDouble(view.salaryField.getText());
            Role role = Role.valueOf(view.roleCombo.getSelectedItem().toString().toUpperCase());
            Poste poste = Poste.valueOf(view.posteCombo.getSelectedItem().toString().toUpperCase());
            Employee updatedEmployee = new Employee(nom, prenom, email, phone, salaire, role, poste);
    
            dao.update(updatedEmployee, id);
    
            JOptionPane.showMessageDialog(view, "Employé mis à jour avec succès.");
            listEmployees();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Erreur: " + ex.getMessage());
        }
    }  
}