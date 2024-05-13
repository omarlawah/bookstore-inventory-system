import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Inventory extends JFrame{
    private JTextField txtAuthor, txtName, txtQuantity, txtPrice;
    private JButton btnAddBook, btnUpdate, btnShowBooks;
    private JTextArea allBooksDisplayed;
    private JComboBox<String> bookToUpdate;
    private ArrayList<Book> allBooks;
    private String  selectedBook;
    public Inventory(){
        setTitle("Bookstore Inventory System");
        setSize(750 ,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        allBooks = new ArrayList<>();
        setLayout(new FlowLayout());
        bookToUpdate = new JComboBox<>();

        // book name field with label
        JLabel nameLabel = new JLabel("Book Name:");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(nameLabel);
        txtName = new JTextField(10);
        txtName.setFont( new Font("SansSerif", Font.ITALIC, 14));
        add(txtName);

        // book author field with label
        JLabel authorLabel = new JLabel("Author Name:");
        authorLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(authorLabel);
        txtAuthor = new JTextField(10);
        txtAuthor.setFont( new Font("SansSerif", Font.ITALIC, 14));
        add(txtAuthor);

        // book price field with label
        JLabel priceLabel = new JLabel("Book Price:");
        priceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(priceLabel);
        txtPrice = new JTextField(10);
        txtPrice.setFont(new Font("SansSerif", Font.ITALIC, 14));
        add(txtPrice);

        // book quantity field with label
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(quantityLabel);
        txtQuantity = new JTextField(5);
        txtQuantity.setFont(new Font("SansSerif", Font.ITALIC, 14));
        add(txtQuantity);


        // adding the buttons
        btnAddBook = new JButton("Add Book");
        btnAddBook.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnAddBook.setBackground(Color.blue);
        btnAddBook.setForeground(Color.white);
        btnAddBook.setFocusPainted(false);

        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnUpdate.setBackground(Color.green);
        btnUpdate.setForeground(Color.white);
        btnUpdate.setFocusPainted(false);

        btnShowBooks = new JButton("View inventory");
        btnShowBooks.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnShowBooks.setBackground(Color.red);
        btnShowBooks.setForeground(Color.white);
        btnShowBooks.setFocusPainted(false);
        add(btnAddBook);
        add(btnUpdate);
        add(btnShowBooks);


        //adding results area
        allBooksDisplayed = new JTextArea(20 ,35);
        add(allBooksDisplayed);


        //adding the functionality of the buttons
        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerBook();
            }
        });

        btnShowBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInventory();
            }
        });


        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });
        setVisible(true);
    }
    public void registerBook(){

        // making sure the user doesnt accidentally add the same book twice to the inventory
        for(Book elem:allBooks){
            if(txtName.getText().toLowerCase().trim().equals(elem.getName().trim().toLowerCase())) {
                resetInputs();
                JOptionPane.showMessageDialog(this, "this book already exists!");
                return;
            }
        }
        String name = txtName.getText();
        String author = txtAuthor.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        float price = Float.parseFloat(txtPrice.getText());
        Book book = new Book(author, name ,price, quantity);
        allBooks.add(book);
        bookToUpdate.addItem(book.getName().toString());

        resetInputs();

        // output message to confirm that books are added
        JOptionPane.showMessageDialog(this, "Book added!");
    }

    public void updateBook(){
        //to make sure the code runs only when the user selects "ok" and to not run in
//        case he clicks "no" or "cancel"
        int option = JOptionPane.showConfirmDialog(this, bookToUpdate);
        if(option == JOptionPane.OK_OPTION){
            selectedBook = bookToUpdate.getSelectedItem().toString();
            for(Book book:allBooks){
                if(selectedBook == book.getName()) {
                    int newQuantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Change quantity"));
                    book.setQuantity(newQuantity);
                }
            }
        }
    }

    //function to reset inputs
    public void resetInputs(){
        txtName.setText("");
        txtAuthor.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
    }

    public void showInventory(){
        StringBuilder results = new StringBuilder();
        for(Book book:allBooks){
            results.append("    Name: ").append(book.getName()).append(" ")
                    .append("   Author: ").append(book.getAuthor()).append(" ")
                    .append("   price: ").append(book.getPrice()).append(" ")
                    .append("   Quantity: ").append(book.getQuantity())
                    .append("\n");
            allBooksDisplayed.setText(results.toString());
        }
    }


}
