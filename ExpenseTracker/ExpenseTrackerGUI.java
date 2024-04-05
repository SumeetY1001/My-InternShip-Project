import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class User {
    private String username;
    private String password;
    private List<Expense> expenses;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.expenses = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}

class Expense {
    private Date date;
    private double amount;
    private String description;
    private String category;

    public Expense(Date date, double amount, String description, String category) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}

public class ExpenseTrackerGUI extends JFrame {
    private Map<String, User> users;
    private User currentUser;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private JPanel cards;
    private JPanel loginPanel;
    private JTextArea outputArea;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public ExpenseTrackerGUI() {
        super("Expense Tracker");
        this.users = new HashMap<>();
        // Create sample users
        users.put("user1", new User("user1", "password1"));
        users.put("user2", new User("user2", "password2"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create card layout for switching between screens
        cards = new JPanel(new CardLayout());

        createLoginPanel();
        createOptionsPanel();

        add(cards);
    }

    private void createLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Expense Tracker");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginPanel.add(titleLabel, gbc);

        gbc.gridy++;
        JLabel usernameLabel = new JLabel("Username:");
        loginPanel.add(usernameLabel, gbc);

        gbc.gridy++;
        usernameField = new JTextField(20);
        loginPanel.add(usernameField, gbc);

        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        loginPanel.add(passwordLabel, gbc);

        gbc.gridy++;
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, gbc);

        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login(usernameField.getText(), new String(passwordField.getPassword()));
            }
        });
        loginPanel.add(loginButton, gbc);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        loginPanel.add(outputArea, gbc);

        cards.add(loginPanel, "LOGIN");
    }

    private void createOptionsPanel() {
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAddExpenseFrame();
            }
        });
        optionsPanel.add(addExpenseButton);

        JButton listExpensesButton = new JButton("List Expenses");
        listExpensesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayListExpensesFrame();
            }
        });
        optionsPanel.add(listExpensesButton);

        JButton categoryWiseSummationButton = new JButton("Category-wise Summation");
        categoryWiseSummationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayCategoryWiseSummationFrame();
            }
        });
        optionsPanel.add(categoryWiseSummationButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        optionsPanel.add(logoutButton);

        cards.add(optionsPanel, "OPTIONS");
    }

    public void login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "OPTIONS");
            outputArea.setText("Login successful. Welcome, " + currentUser.getUsername() + "!");
        } else {
            currentUser = null;
            outputArea.setText("Invalid username or password.");
        }
    }

    private void logout() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "LOGIN");
        currentUser = null;
        usernameField.setText("");
        passwordField.setText("");
        outputArea.setText("");
    }

    private void displayAddExpenseFrame() {
        JFrame addExpenseFrame = new JFrame("Add Expense");
        addExpenseFrame.setSize(400, 200);
        addExpenseFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel dateLabel = new JLabel("Date (yyyy-MM-dd):");
        JTextField dateField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Date date = new SimpleDateFormat(DATE_FORMAT).parse(dateField.getText());
                    double amount = Double.parseDouble(amountField.getText());
                    String description = descriptionField.getText();
                    String category = categoryField.getText();
                    inputExpense(date, amount, description, category);
                    addExpenseFrame.dispose();
                } catch (ParseException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addExpenseFrame, "Invalid date or amount format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(addButton);

        addExpenseFrame.add(panel);
        addExpenseFrame.setVisible(true);
    }

    private void displayListExpensesFrame() {
        if (currentUser == null) {
            outputArea.append("Please log in first.\n");
            return;
        }

        List<Expense> expenses = currentUser.getExpenses();
        if (expenses.isEmpty()) {
            outputArea.append("No expenses found.\n");
            return;
        }

        JFrame listExpensesFrame = new JFrame("List Expenses");
        listExpensesFrame.setLayout(new GridLayout(expenses.size() + 1, 1));

        JLabel headerLabel = new JLabel("Expenses for " + currentUser.getUsername() + ":");
        listExpensesFrame.add(headerLabel);

        for (Expense expense : expenses) {
            JLabel expenseLabel = new JLabel("Date: " + formatDate(expense.getDate()) +
                    ", Amount: " + expense.getAmount() +
                    ", Description: " + expense.getDescription() +
                    ", Category: " + expense.getCategory());
            listExpensesFrame.add(expenseLabel);
        }

        listExpensesFrame.setSize(600, 400); // Increased size
        listExpensesFrame.setLocationRelativeTo(this);
        listExpensesFrame.setVisible(true);
    }

    private void displayCategoryWiseSummationFrame() {
        if (currentUser == null) {
            outputArea.append("Please log in first.\n");
            return;
        }

        List<Expense> expenses = currentUser.getExpenses();
        if (expenses.isEmpty()) {
            outputArea.append("No expenses found.\n");
            return;
        }

        Map<String, Double> categorySum = new HashMap<>();
        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();
            categorySum.put(category, categorySum.getOrDefault(category, 0.0) + amount);
        }

        JFrame categorySummationFrame = new JFrame("Category-wise Summation");
        categorySummationFrame.setLayout(new GridLayout(categorySum.size() + 1, 1));

        JLabel headerLabel = new JLabel("Category-wise Summation for " + currentUser.getUsername() + ":");
        categorySummationFrame.add(headerLabel);

        for (Map.Entry<String, Double> entry : categorySum.entrySet()) {
            JLabel categoryLabel = new JLabel("Category: " + entry.getKey() + ", Total Expense: " + entry.getValue());
            categorySummationFrame.add(categoryLabel);
        }

        categorySummationFrame.setSize(600, 400); // Increased size
        categorySummationFrame.setLocationRelativeTo(this);
        categorySummationFrame.setVisible(true);
    }

    public void inputExpense(Date date, double amount, String description, String category) {
        if (currentUser == null) {
            outputArea.append("Please log in first.\n");
            return;
        }

        Expense expense = new Expense(date, amount, description, category);
        currentUser.getExpenses().add(expense);
        outputArea.append("Expense added successfully.\n");
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ExpenseTrackerGUI().setVisible(true);
            }
        });
    }
}
