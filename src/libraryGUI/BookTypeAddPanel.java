package libraryGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import LibraryControl.BookTypeManager;



public class BookTypeAddPanel extends JPanel implements ActionListener {

    private Box containerBox;
    private Box titleBox;
    private Box bookTypeNameBox;
    private Box bookTypeDescriptionBox;
    private Box buttonBox;
    private JLabel titleLabel;
    private JLabel bookTypeNameLabel;
    private JLabel bookTypeDescriptionLabel;
    private JTextArea bookTypeDescriptionTextArea;
    private JTextField bookTypeNameTextField;
    private JButton addButton;
    private JButton resetButton;
    private JButton returnButton;


    public BookTypeAddPanel(){

        this.add(createBookTypeBox());
        addButton.addActionListener(this);
        resetButton.addActionListener(this);
        returnButton.addActionListener(this);


    }

    private Box createBookTypeBox(){

        containerBox = Box.createVerticalBox();

        titleBox = Box.createHorizontalBox();
        titleLabel = new JLabel("Add New Book Type");
        titleLabel.setFont(new Font("Dialog",Font.BOLD,20));
        titleBox.add(titleLabel);
        containerBox.add(titleBox);
        containerBox.add(Box.createVerticalStrut(35));

        bookTypeNameBox = Box.createHorizontalBox();
        bookTypeNameLabel = new JLabel("book type name: ");
        bookTypeNameTextField = new JTextField(35);
        bookTypeNameBox.add(bookTypeNameLabel);
        bookTypeNameBox.add(Box.createHorizontalStrut(65));
        bookTypeNameBox.add(bookTypeNameTextField);
        containerBox.add(bookTypeNameBox);
        containerBox.add(Box.createVerticalStrut(60));

        bookTypeDescriptionBox = Box.createHorizontalBox();
        bookTypeDescriptionLabel = new JLabel("book type description: ");
        bookTypeDescriptionTextArea = new JTextArea(10,30);
        bookTypeDescriptionTextArea.setLineWrap(true);
        bookTypeDescriptionBox.add(bookTypeDescriptionLabel);
        bookTypeDescriptionBox.add(Box.createHorizontalStrut(30));
        bookTypeDescriptionBox.add(bookTypeDescriptionTextArea);
        containerBox.add(bookTypeDescriptionBox);
        containerBox.add(Box.createVerticalStrut(100));

        buttonBox = Box.createHorizontalBox();
        addButton = new JButton("Add");
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
            String bookTypeName = bookTypeNameTextField.getText();
            String bookTypeDescription = bookTypeDescriptionTextArea.getText();
            boolean isOK = new BookTypeManager().addBookType(bookTypeName,bookTypeDescription);
            if(isOK){
                JOptionPane.showMessageDialog(null, "Successfully added!");
                bookTypeNameTextField.setText("");
                bookTypeDescriptionTextArea.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "Something went wrong...");
            }

        }
        if(e.getSource() == resetButton){
            bookTypeNameTextField.setText("");
            bookTypeDescriptionTextArea.setText("");
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
