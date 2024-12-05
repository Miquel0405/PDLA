package planasleiman;

import planasleiman.Missions.Mission;
import planasleiman.Users.Beneficiaire;
import planasleiman.Users.Benevole;
import planasleiman.Verifying.Valideur;

public class Main {
    // private static void createAndShowGUI() {
    //     // Crear y configurar la ventana principal
    //     JFrame frame = new JFrame("User Registration");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setLayout(new BorderLayout());

    //     // Etiqueta de título
    //     JLabel titleLabel = new JLabel("Welcome to the Registration System");
    //     titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    //     titleLabel.setPreferredSize(new Dimension(300, 50));
    //     frame.add(titleLabel, BorderLayout.NORTH);

    //     // Panel para el botón
    //     JPanel buttonPanel = new JPanel();
    //     buttonPanel.setLayout(new FlowLayout());

    //     // Botón para registro de usuarios
    //     JButton registerButton = new JButton("Register New User");
    //     buttonPanel.add(registerButton);
    //     frame.add(buttonPanel, BorderLayout.CENTER);

    //     // Agregar un ActionListener al botón
    //     registerButton.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             openRegistrationForm();
    //         }
    //     });

    //     // Configuración final de la ventana
    //     frame.setSize(400, 200);
    //     frame.setLocationRelativeTo(null); // Centrar la ventana
    //     frame.setVisible(true);
    // }

    // /**
    //  * Abre el formulario de registro.
    //  */
    // private static void openRegistrationForm() {
    //     // Crear un nuevo marco para el formulario de registro
    //     JFrame formFrame = new JFrame("User Registration Form");
    //     formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //     formFrame.setLayout(new GridLayout(8, 2, 10, 10));
    //     formFrame.setSize(400, 350);

    //     // Componentes del formulario
    //     JLabel userTypeLabel = new JLabel("User Type:");
    //     String[] userTypes = {"Volunteer", "Person in Need"};
    //     JComboBox<String> userTypeDropdown = new JComboBox<>(userTypes);

    //     JLabel lastNameLabel = new JLabel("Last Name:");
    //     JTextField lastNameField = new JTextField();

    //     JLabel firstNameLabel = new JLabel("First Name:");
    //     JTextField firstNameField = new JTextField();

    //     JLabel emailLabel = new JLabel("Email:");
    //     JTextField emailField = new JTextField();

    //     JLabel phoneLabel = new JLabel("Phone:");
    //     JTextField phoneField = new JTextField();

    //     JLabel addressLabel = new JLabel("Address:");
    //     JTextField addressField = new JTextField();

    //     JButton submitButton = new JButton("Submit");
    //     JLabel statusLabel = new JLabel("");

    //     // Agregar componentes al formulario
    //     formFrame.add(userTypeLabel);
    //     formFrame.add(userTypeDropdown);
    //     formFrame.add(lastNameLabel);
    //     formFrame.add(lastNameField);
    //     formFrame.add(firstNameLabel);
    //     formFrame.add(firstNameField);
    //     formFrame.add(emailLabel);
    //     formFrame.add(emailField);
    //     formFrame.add(phoneLabel);
    //     formFrame.add(phoneField);
    //     formFrame.add(addressLabel);
    //     formFrame.add(addressField);
    //     formFrame.add(new JLabel()); // Espacio vacío
    //     formFrame.add(submitButton);
    //     formFrame.add(new JLabel()); // Espacio vacío
    //     formFrame.add(statusLabel);

    //     // Funcionalidad del botón "Submit"
    //     submitButton.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             String userType = (String) userTypeDropdown.getSelectedItem();
    //             String lastName = lastNameField.getText().trim();
    //             String firstName = firstNameField.getText().trim();
    //             String email = emailField.getText().trim();
    //             String phone = phoneField.getText().trim();
    //             String address = addressField.getText().trim();

    //             if (lastName.isEmpty() || firstName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
    //                 statusLabel.setText("Please fill in all fields.");
    //                 statusLabel.setForeground(Color.RED);
    //             } else {
    //                 // Simular guardado de datos
    //                 if (userType=="Person in need"){
    //                     Beneficiaire beneficiaire = new Beneficiaire(lastName, firstName, phone, email, address);
    //                     beneficiaire.saveinDatabase();
    //                 }else if (userType=="Volunteer"){
    //                     Benevole benevole = new Benevole(lastName, firstName, phone, email, address);
    //                     benevole.saveinDatabase();
    //                 }
    //                 System.out.println("User Type: " + userType);
    //                 System.out.println("Last Name: " + lastName);
    //                 System.out.println("First Name: " + firstName);
    //                 System.out.println("Email: " + email);
    //                 System.out.println("Phone: " + phone);
    //                 System.out.println("Address: " + address);

    //                 statusLabel.setText("Registration successful!");
    //                 statusLabel.setForeground(Color.GREEN);

    //                 // Limpiar campos
    //                 lastNameField.setText("");
    //                 firstNameField.setText("");
    //                 emailField.setText("");
    //                 phoneField.setText("");
    //                 addressField.setText("");
    //             }
    //         }
    //     });

    //     // Mostrar el formulario
    //     formFrame.setLocationRelativeTo(null); // Centrar el formulario
    //     formFrame.setVisible(true);
    // }

//     public static void main(String[] args) {
//         // Crear y mostrar la GUI en el hilo de despacho de eventos
//         javax.swing.SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 createAndShowGUI();
//             }
//         });
//     }
// }

    
    public static void main(String[] args) {

        //On cree un nouveau beneficiaire et on l'enregistre dans la base de donnees
        Beneficiaire beneficiaire = new Beneficiaire("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        beneficiaire.saveinDatabase();

        // On cree un nouvel utilisateur valideur et on l'enregistre dans la base de donnees
        Valideur val = new Valideur("valideur", "123456789","valideur@mail.com");
        val.saveValideur();

        //On cree une nouvelle mission ayant comme beneficiaire et utilisateur valideur ceux qu'on vient de creer (la mission est directement enregistree dans la base de donnees)
        Mission mission = beneficiaire.CreateMission("MissionTest", "DescriptionTest", val);

        //On cree un nouveau benevole et on l'enregistre dans la base de donnees
        Benevole benevole = new Benevole("NomTest", "PrenomTest", "123456789", "test@mail.com", "Adresse test");
        benevole.saveinDatabase();

        
        // L'utilisateur valideur aprouve la mission (le statut de la mission change)
        val.validateMission(mission, true, null);

        // Le benevole qu'on vient de creer s'incrit dans la mission disponible
        benevole.CouvrirMission(mission);
        
    }
}
