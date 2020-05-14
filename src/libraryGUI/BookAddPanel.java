package libraryGUI;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;
import LibraryControl.BookTypeManager;
import LibraryControl.BookManager;
import LibraryBean.BookTypeBean;

public class BookAddPanel extends JPanel implements ActionListener{
    private Box containerBox;
    private Box titleBox;
    private Box nameAndAuthorBox;
    private Box priceAndTypeBox;
    private Box descriptionBox;
    private Box buttonBox;
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel authorLabel;
    private JLabel priceLabel;
    private JLabel typeLabel;
    private JLabel descriptionLabel;
    private JTextField nameTextField;
    private JTextField authorTextField;
    private JTextField priceTextField;
    private JTextArea descriptionTextArea;
    private JComboBox typeComboBox;
    private JButton addButton;
    private JButton resetButton;
    private JButton returnButton;

    public BookAddPanel(){
        this.add(createBookAddBox());
        List bookTypeList = new BookTypeManager().loadAllBookType();
        String[] bookTypeNames = new String[bookTypeList.size()];
        typeComboBox.addItem("--please select--");
        for(int i = 0; i < bookTypeList.size(); i++){
            BookTypeBean b = (BookTypeBean)bookTypeList.get(i);
            bookTypeNames[i] = b.getBookTypeName();
            typeComboBox.addItem(bookTypeNames[i]);
        }

        addButton.addActionListener(this);
        resetButton.addActionListener(this);
        returnButton.addActionListener(this);

    }

    private Box createBookAddBox(){
        containerBox = Box.createVerticalBox();

        titleBox = Box.createHorizontalBox();
        titleLabel = new JLabel("Add New Book");
        titleLabel.setFont(new Font("Dialog",Font.BOLD,20));
        titleBox.add(titleLabel);
        containerBox.add(titleBox);
        containerBox.add(Box.createVerticalStrut(30));

        nameAndAuthorBox = Box.createHorizontalBox();
        nameLabel = new JLabel("book name: ");
        nameTextField = new JTextField(10);
        authorLabel = new JLabel("book author: ");
        authorTextField = new JTextField(10);
        nameAndAuthorBox.add(nameLabel);
        nameAndAuthorBox.add(Box.createHorizontalStrut(40));
        nameAndAuthorBox.add(nameTextField);
        nameAndAuthorBox.add(Box.createHorizontalStrut(40));
        nameAndAuthorBox.add(authorLabel);
        nameAndAuthorBox.add(Box.createHorizontalStrut(40));
        nameAndAuthorBox.add(authorTextField);
        containerBox.add(nameAndAuthorBox);
        containerBox.add(Box.createVerticalStrut(30));

        priceAndTypeBox = Box.createHorizontalBox();
        priceLabel = new JLabel("book price: ");
        priceTextField = new JTextField(10);
        typeLabel = new JLabel("book type: ");
        typeComboBox = new JComboBox();
        priceAndTypeBox.add(priceLabel);
        priceAndTypeBox.add(Box.createHorizontalStrut(40));
        priceAndTypeBox.add(priceTextField);
        priceAndTypeBox.add(Box.createHorizontalStrut(40));
        priceAndTypeBox.add(typeLabel);
        priceAndTypeBox.add(Box.createHorizontalStrut(40));
        priceAndTypeBox.add(typeComboBox);
        containerBox.add(priceAndTypeBox);
        containerBox.add(Box.createVerticalStrut(30));

        descriptionBox = Box.createHorizontalBox();
        descriptionLabel = new JLabel("book description: ");
        descriptionTextArea = new JTextArea(10,40);
        descriptionBox.add(descriptionLabel);
        descriptionBox.add(Box.createHorizontalStrut(40));
        descriptionBox.add(descriptionTextArea);
        containerBox.add(descriptionBox);
        containerBox.add(Box.createVerticalStrut(50));

        buttonBox = Box.createHorizontalBox();
        addButton = new JButton("add");
        resetButton = new JButton("reset");
        returnButton = new JButton("return");
        buttonBox.add(addButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(resetButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(returnButton);
        containerBox.add(buttonBox);

        return containerBox;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == addButton){
            String name = nameTextField.getText();
            String author = authorTextField.getText();
            float price = Float.parseFloat(priceTextField.getText());
            String type = (String)typeComboBox.getSelectedItem();
            String description = descriptionTextArea.getText();

            List bookTypeList = new BookTypeManager().loadSelectedBookType(type);
            BookTypeBean b = (BookTypeBean) bookTypeList.get(0);
            int bookTypeId = b.getBookTypeId();
            boolean isOK = new BookManager().addBook(name,author,price,description,bookTypeId);
            if(isOK){
                JOptionPane.showMessageDialog(null, "Successfully added!");
                nameTextField.setText("");
                authorTextField.setText("");
                priceTextField.setText("");
                descriptionTextArea.setText("");
                typeComboBox.setSelectedIndex(0);

            }
            else{
                JOptionPane.showMessageDialog(null, "Something went wrong...");
            }

        }
        if(e.getSource() == resetButton){
            nameTextField.setText("");
            authorTextField.setText("");
            priceTextField.setText("");
            descriptionTextArea.setText("");
            typeComboBox.setSelectedIndex(0);
        }
        if(e.getSource() == returnButton){
            this.removeAll();
            JLabel bookimage = new JLabel();
            Icon icon = new ImageIcon("/Users/john/IdeaProjects/myLibrary/src/images/book2.jpg");
            bookimage.setIcon(icon);
            bookimage.setSize(icon.getIconWidth(),icon.getIconHeight());
            this.repaint();
            this.add(bookimage);
            this.revalidate();
        }



    }
}
