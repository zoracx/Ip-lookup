package JavaCMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaCMainExe {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JavaCMainExe().createAndShowGUI());
    }

    public void createAndShowGUI() {
        // Create frame for the GUI
        JFrame frame = new JFrame("IP Lookup Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Create the input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel ipLabel = new JLabel("Enter IP Address:");
        JTextField ipField = new JTextField();

        JLabel keyLabel = new JLabel("Enter Access Key:");
        JTextField keyField = new JTextField();

        inputPanel.add(ipLabel);
        inputPanel.add(ipField);
        inputPanel.add(keyLabel);
        inputPanel.add(keyField);

        // Create a combo box for selecting actions
        JLabel menuLabel = new JLabel("Select Action:");
        String[] menuOptions = {
                "Resolve Hostname",
                "Fetch ASN Info",
                "Get ISP Details",
                "Identify Service Type",
                "Get Country by IP",
                "Get State by IP",
                "Get City by IP",
                "Get Latitude by IP",
                "Get Longitude by IP"
        };
        JComboBox<String> menuComboBox = new JComboBox<>(menuOptions);

        // Panel for button and menu
        JPanel menuPanel = new JPanel();
        menuPanel.add(menuLabel);
        menuPanel.add(menuComboBox);

        // Output area for results
        JTextArea resultArea = new JTextArea(8, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Create and add buttons
        JButton fetchButton = new JButton("Fetch Info");

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input data from fields
                String ip = ipField.getText();
                String accessKey = keyField.getText();
                String selectedAction = (String) menuComboBox.getSelectedItem();

                // Create an object of JavaCMain_Class
                JavaCMain_Class ob = new JavaCMain_Class(ip, accessKey);

                String result = "";
                
                // Determine which action to take
                switch (selectedAction) {
                    case "Resolve Hostname":
                        result = ob.resolveHostname(ip);
                        break;
                    case "Fetch ASN Info":
                        result = String.valueOf(ob.fetchASNInfo(ip));
                        break;
                    case "Get ISP Details":
                        result = ob.getISPDetails(ip);
                        break;
                    case "Identify Service Type":
                        result = ob.identifyServiceType(ip);
                        break;
                    case "Get Country by IP":
                        result = ob.getCountryByIP(ip);
                        break;
                    case "Get State by IP":
                        result = ob.getStateByIP(ip);
                        break;
                    case "Get City by IP":
                        result = ob.getCityByIP(ip);
                        break;
                    case "Get Latitude by IP":
                        result = String.valueOf(ob.getLatitudeByIP(ip));
                        break;
                    case "Get Longitude by IP":
                        result = String.valueOf(ob.getLongitudeByIP(ip));
                        break;
                    default:
                        result = "Unknown action selected.";
                }

                // Display the result in the result area
                resultArea.setText("Result for IP: " + ip + "\n" + result);
            }
        });

        // Add components to the frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(menuPanel, BorderLayout.CENTER);
        frame.add(fetchButton, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.EAST);

        // Show the frame
        frame.setVisible(true);
    }
}
