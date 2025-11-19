import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A comprehensive Unit Converter application in Java Swing.
 * Supports Length, Weight, and Temperature conversions.
 */
public class UnitConverter extends JFrame {

    // UI Components
    private JComboBox<String> categoryBox;
    private JComboBox<String> fromUnitBox;
    private JComboBox<String> toUnitBox;
    private JTextField inputField;
    private JTextField resultField;
    private JButton convertButton;
    private JLabel errorLabel;

    // Data for units
    private static final String[] CATEGORIES = {"Length", "Weight", "Temperature"};
    
    private static final String[] LENGTH_UNITS = {
        "Meters", "Kilometers", "Centimeters", "Millimeters", 
        "Miles", "Yards", "Feet", "Inches"
    };
    
    private static final String[] WEIGHT_UNITS = {
        "Kilograms", "Grams", "Milligrams", 
        "Pounds", "Ounces", "Tons"
    };
    
    private static final String[] TEMP_UNITS = {
        "Celsius", "Fahrenheit", "Kelvin"
    };

    // Conversion factors relative to a base unit (Meters for length, Kilograms for weight)
    private Map<String, Double> lengthFactors;
    private Map<String, Double> weightFactors;

    public UnitConverter() {
        initializeData();
        setupUI();
    }

    private void initializeData() {
        // Base unit: Meter
        lengthFactors = new HashMap<>();
        lengthFactors.put("Meters", 1.0);
        lengthFactors.put("Kilometers", 1000.0);
        lengthFactors.put("Centimeters", 0.01);
        lengthFactors.put("Millimeters", 0.001);
        lengthFactors.put("Miles", 1609.34);
        lengthFactors.put("Yards", 0.9144);
        lengthFactors.put("Feet", 0.3048);
        lengthFactors.put("Inches", 0.0254);

        // Base unit: Kilogram
        weightFactors = new HashMap<>();
        weightFactors.put("Kilograms", 1.0);
        weightFactors.put("Grams", 0.001);
        weightFactors.put("Milligrams", 0.000001);
        weightFactors.put("Pounds", 0.453592);
        weightFactors.put("Ounces", 0.0283495);
        weightFactors.put("Tons", 1000.0);
    }

    private void setupUI() {
        setTitle("Universal Unit Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10));

        // Main Panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Header ---
        JLabel titleLabel = new JLabel("Unit Converter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // --- Category Selection ---
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Category:"), gbc);

        categoryBox = new JComboBox<>(CATEGORIES);
        categoryBox.addActionListener(e -> updateUnitDropdowns());
        gbc.gridx = 1; gbc.gridy = 0;
        mainPanel.add(categoryBox, gbc);

        // --- Input Value ---
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Input Value:"), gbc);

        inputField = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(inputField, gbc);

        // --- From Unit ---
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("From:"), gbc);

        fromUnitBox = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(fromUnitBox, gbc);

        // --- To Unit ---
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("To:"), gbc);

        toUnitBox = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 3;
        mainPanel.add(toUnitBox, gbc);

        // --- Convert Button ---
        convertButton = new JButton("Convert");
        convertButton.setBackground(new Color(66, 135, 245));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.addActionListener(this::performConversion);
        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(convertButton, gbc);

        // --- Result Display ---
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(new JLabel("Result:"), gbc);

        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setFont(new Font("Arial", Font.BOLD, 16));
        resultField.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(resultField, gbc);

        // Error label
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 7;
        mainPanel.add(errorLabel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Initialize unit dropdowns
        updateUnitDropdowns();
    }

    /**
     * Updates the From and To dropdowns based on the selected category.
     */
    private void updateUnitDropdowns() {
        String category = (String) categoryBox.getSelectedItem();
        fromUnitBox.removeAllItems();
        toUnitBox.removeAllItems();

        String[] units;
        switch (category) {
            case "Length":
                units = LENGTH_UNITS;
                break;
            case "Weight":
                units = WEIGHT_UNITS;
                break;
            case "Temperature":
                units = TEMP_UNITS;
                break;
            default:
                units = new String[]{};
        }

        for (String unit : units) {
            fromUnitBox.addItem(unit);
            toUnitBox.addItem(unit);
        }
    }

    /**
     * Main conversion logic handler.
     */
    private void performConversion(ActionEvent e) {
        errorLabel.setText("");
        resultField.setText("");

        try {
            String inputStr = inputField.getText();
            if (inputStr.isEmpty()) {
                errorLabel.setText("Please enter a value.");
                return;
            }

            double input = Double.parseDouble(inputStr);
            String category = (String) categoryBox.getSelectedItem();
            String fromUnit = (String) fromUnitBox.getSelectedItem();
            String toUnit = (String) toUnitBox.getSelectedItem();
            
            double result = 0;

            switch (category) {
                case "Length":
                    result = convertLength(input, fromUnit, toUnit);
                    break;
                case "Weight":
                    result = convertWeight(input, fromUnit, toUnit);
                    break;
                case "Temperature":
                    result = convertTemperature(input, fromUnit, toUnit);
                    break;
            }

            // Format result to avoid long decimal places if possible
            if (result == (long) result) {
                resultField.setText(String.format("%d", (long) result));
            } else {
                resultField.setText(String.format("%.6f", result));
            }

        } catch (NumberFormatException ex) {
            errorLabel.setText("Invalid number format.");
        } catch (Exception ex) {
            errorLabel.setText("An error occurred during conversion.");
            ex.printStackTrace();
        }
    }

    // --- Logic for Length Conversion ---
    private double convertLength(double value, String from, String to) {
        // Convert to base (Meters) then to target
        double inMeters = value * lengthFactors.get(from);
        return inMeters / lengthFactors.get(to);
    }

    // --- Logic for Weight Conversion ---
    private double convertWeight(double value, String from, String to) {
        // Convert to base (Kilograms) then to target
        double inKilos = value * weightFactors.get(from);
        return inKilos / weightFactors.get(to);
    }

    // --- Logic for Temperature Conversion ---
    private double convertTemperature(double value, String from, String to) {
        if (from.equals(to)) return value;

        double celsius = 0;

        // Convert to Celsius first
        switch (from) {
            case "Celsius":
                celsius = value;
                break;
            case "Fahrenheit":
                celsius = (value - 32) * 5.0 / 9.0;
                break;
            case "Kelvin":
                celsius = value - 273.15;
                break;
        }

        // Convert from Celsius to target
        switch (to) {
            case "Celsius":
                return celsius;
            case "Fahrenheit":
                return (celsius * 9.0 / 5.0) + 32;
            case "Kelvin":
                return celsius + 273.15;
            default:
                return celsius;
        }
    }

    public static void main(String[] args) {
        // Run UI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel for better integration
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new UnitConverter().setVisible(true);
        });
    }
}