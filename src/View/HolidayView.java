package View;

import javax.swing.*;
import java.awt.*;

public class HolidayView extends JFrame {

    
    private JTextField holidayStartDate;
    private JTextField holidayEndDate;
    private JComboBox<String> holidayType;
    private JComboBox<String> employeeComboBox;
    private JTable holidayTable;
    private JButton addButton, deleteButton, listButton;

    public HolidayView() {
        
        setTitle("Gestion des Congés");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JTabbedPane tabbedPane = new JTabbedPane();

        
        JPanel holidayPanel = new JPanel();
        holidayPanel.setLayout(new BorderLayout());

        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2));
        
        formPanel.add(new JLabel("Date de début"));
        holidayStartDate = new JTextField();
        formPanel.add(holidayStartDate);

        formPanel.add(new JLabel("Date de fin"));
        holidayEndDate = new JTextField();
        formPanel.add(holidayEndDate);

        formPanel.add(new JLabel("Type de congé"));
        holidayType = new JComboBox<>(new String[]{"Congé payé", "Congé maladie", "Congé sans solde"});
        formPanel.add(holidayType);

        formPanel.add(new JLabel("Employé"));
        employeeComboBox = new JComboBox<>(new String[]{"Employé 1", "Employé 2", "Employé 3"});
        formPanel.add(employeeComboBox);

        holidayPanel.add(formPanel, BorderLayout.NORTH);

        
        holidayTable = new JTable(); 
        holidayPanel.add(new JScrollPane(holidayTable), BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Ajouter");
        deleteButton = new JButton("Supprimer");
        listButton = new JButton("Lister");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);

        holidayPanel.add(buttonPanel, BorderLayout.SOUTH);

       
        tabbedPane.addTab("Congés", holidayPanel);

      
        JPanel employeePanel = new JPanel();
        tabbedPane.addTab("Employés", employeePanel);

       
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HolidayView().setVisible(true));
    }
}

   
