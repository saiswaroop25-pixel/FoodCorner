import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FoodCornerApplet extends JApplet implements ActionListener {
    JLabel welcomeLabel, nameLabel, numberLabel, addressLabel, quantityLabel, totalPriceLabel;
    JTextField nameField, numberField, addressField, totalPriceField;
    JComboBox<String> quantityChoice;
    JRadioButton cashRadioButton, upiRadioButton;
    ButtonGroup paymentMethodGroup;
    JButton checkoutButton;

    String[] startersVeg = {"Paneer Tikka", "Vegetable Spring Rolls"};
    double[] startersVegPrices = {100.0, 80.0};
    String[] startersNonVeg = {"Chicken Wings", "Fish Fry"};
    double[] startersNonVegPrices = {120.0, 150.0};

    String[] mainCourseVeg = {"Veg Biryani", "Paneer Butter Masala"};
    double[] mainCourseVegPrices = {150.0, 180.0};
    String[] mainCourseNonVeg = {"Chicken Biryani", "Butter Chicken"};
    double[] mainCourseNonVegPrices = {200.0, 220.0};

    String[] desserts = {"Gulab Jamun", "Ice Cream Sundae", "Chocolate Cake", "Fruit Salad", "Rasgulla"};
    double[] dessertsPrices = {50.0, 80.0, 120.0, 60.0, 40.0};

    String[] drinks = {"Coke", "Orange Juice", "Mango Shake", "Lemonade", "Iced Tea"};
    double[] drinksPrices = {30.0, 40.0, 50.0, 35.0, 45.0};

    double totalBill = 0.0;

    public void init() {
        setLayout(new BorderLayout());

        welcomeLabel = new JLabel("Welcome To Food Corner", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        nameLabel = new JLabel("Name:");
        numberLabel = new JLabel("Phone Number:");
        addressLabel = new JLabel("Address:");
        quantityLabel = new JLabel("Quantity:");
        totalPriceLabel = new JLabel("Total Price:");

        nameField = new JTextField(20);
        numberField = new JTextField(20);
        addressField = new JTextField(40);
        totalPriceField = new JTextField(10);
        totalPriceField.setEditable(false);

        quantityChoice = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            quantityChoice.addItem(Integer.toString(i));
        }

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(numberLabel);
        formPanel.add(numberField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(quantityLabel);
        formPanel.add(quantityChoice);
        formPanel.add(totalPriceLabel);
        formPanel.add(totalPriceField);

        JPanel paymentPanel = new JPanel(new FlowLayout());
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Payment Method"));
        paymentMethodGroup = new ButtonGroup();
        cashRadioButton = new JRadioButton("Cash");
        upiRadioButton = new JRadioButton("UPI");
        paymentMethodGroup.add(cashRadioButton);
        paymentMethodGroup.add(upiRadioButton);
        paymentPanel.add(cashRadioButton);
        paymentPanel.add(upiRadioButton);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(this);
        buttonPanel.add(checkoutButton);

        add(welcomePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(paymentPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.SOUTH);

        JPanel foodPanel = new JPanel(new GridLayout(20, 1, 10, 10));

        addFoodItems(foodPanel, "Starter", "Veg", startersVeg, startersVegPrices);
        addFoodItems(foodPanel, "Starter", "Non-Veg", startersNonVeg, startersNonVegPrices);
        addFoodItems(foodPanel, "Main Course", "Veg", mainCourseVeg, mainCourseVegPrices);
        addFoodItems(foodPanel, "Main Course", "Non-Veg", mainCourseNonVeg, mainCourseNonVegPrices);
        addFoodItems(foodPanel, "Dessert", "", desserts, dessertsPrices);
        addFoodItems(foodPanel, "Drink", "", drinks, drinksPrices);

        JScrollPane foodScrollPane = new JScrollPane(foodPanel);
        foodScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        foodScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(foodScrollPane, BorderLayout.WEST);
    }

    private void addFoodItems(JPanel panel, String category, String type, String[] items, double[] prices) {
        for (int i = 0; i < items.length; i++) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel(items[i]);
            JLabel priceLabel = new JLabel("Price: $" + prices[i]);
            JButton addToCartButton = new JButton("Add to Cart");

            addToCartButton.addActionListener(e -> {
                int quantity = Integer.parseInt(quantityChoice.getSelectedItem().toString());
                totalBill += prices[i] * quantity;
                totalPriceField.setText(String.format("%.2f", totalBill));
            });

            itemPanel.add(nameLabel, BorderLayout.WEST);
            itemPanel.add(priceLabel, BorderLayout.CENTER);
            itemPanel.add(addToCartButton, BorderLayout.EAST);
            panel.add(itemPanel);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == checkoutButton) {
            printBill();
        }
    }

    private void printBill() {
        String name = nameField.getText().trim();
        String number = numberField.getText().trim();
        String address = addressField.getText().trim();

        if (name.isEmpty() || number.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double gst = totalBill * 0.18;
        double serviceTax = totalBill * 0.05;
        double swachhBharatTax = totalBill * 0.01;
        double totalAmount = totalBill + gst + serviceTax + swachhBharatTax;

        StringBuilder bill = new StringBuilder();
        bill.append("Name: ").append(name).append("\n");
        bill.append("Phone Number: ").append(number).append("\n");
        bill.append("Address: ").append(address).append("\n\n");

        bill.append("Food Items:\n");
        // Add food items from cart
        // Add food items from cart
        for (int i = 0; i < startersVeg.length; i++) {
            bill.append(startersVeg[i]).append("\n");
        }
        for (int i = 0; i < startersNonVeg.length; i++) {
            bill.append(startersNonVeg[i]).append("\n");
        }
        for (int i = 0; i < mainCourseVeg.length; i++) {
            bill.append(mainCourseVeg[i]).append("\n");
        }
        for (int i = 0; i < mainCourseNonVeg.length; i++) {
            bill.append(mainCourseNonVeg[i]).append("\n");
        }
        for (int i = 0; i < desserts.length; i++) {
            bill.append(desserts[i]).append("\n");
        }
        for (int i = 0; i < drinks.length; i++) {
            bill.append(drinks[i]).append("\n");
        }

        bill.append("\nTotal Bill: $").append(totalBill).append("\n");
        bill.append("GST (18%): $").append(gst).append("\n");
        bill.append("Service Tax (5%): $").append(serviceTax).append("\n");
        bill.append("Swachh Bharat Tax (1%): $").append(swachhBharatTax).append("\n");
        bill.append("Total Amount: $").append(totalAmount).append("\n");

        JOptionPane.showMessageDialog(this, bill.toString(), "Bill Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
