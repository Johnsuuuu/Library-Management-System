package libraryGUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import LibraryBean.*;
import LibraryControl.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class BookManagePanel extends JPanel implements ActionListener, ListSelectionListener {

    private Box containerBox;
    private Box titleBox;
    private Box checkBox1;
    private Box checkBox2;
    private Box tableBox;
    private Box contentBox1;
    private Box contentBox2;
    private Box descriptionBox;
    private Box buttonBox;

    private JComboBox bookTypeComboBox1;
    private JComboBox bookTypeComboBox2;

    private JLabel titleLabel;
    private JLabel nameLabel1;
    private JLabel authorLabel1;
    private JLabel bookTypeLabel1;
    private JLabel idLabel;
    private JLabel priceLabel;
    private JLabel nameLabel2;
    private JLabel authorLabel2;
    private JLabel bookTypeLabel2;
    private JLabel descriptionLabel;

    private JTextField nameTextField1;
    private JTextField nameTextField2;
    private JTextField authorTextField1;
    private JTextField authorTextField2;
    private JTextField idTextField;
    private JTextField priceTextField;

    private JTextArea descriptionTextArea;

    private JButton checkButton;
    private JButton resetButton1;
    private JButton resetButton2;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton returnButton;

    private JScrollPane tableScrollPanel;
    private JTable table;
    private DefaultTableModel tableModel;


    public BookManagePanel(){
        this.add(createBookManageBox());
        List bookTypeList = new BookTypeManager().loadAllBookType();
        String[] bookTypeNames = new String[bookTypeList.size()];
        bookTypeComboBox1.addItem("--please select--");
        bookTypeComboBox2.addItem("--please select--");
        for(int i = 0; i < bookTypeList.size(); i++){
            BookTypeBean b = (BookTypeBean)bookTypeList.get(i);
            bookTypeNames[i] = b.getBookTypeName();
            bookTypeComboBox1.addItem(bookTypeNames[i]);
            bookTypeComboBox2.addItem(bookTypeNames[i]);
        }

        checkButton.addActionListener(this);
        resetButton1.addActionListener(this);
        resetButton2.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        returnButton.addActionListener(this);
        table.getSelectionModel().addListSelectionListener(this);
    }

    private Box createBookManageBox() {
        containerBox = Box.createVerticalBox();

        titleBox = Box.createHorizontalBox();
        titleLabel = new JLabel("Edit Book Info");
        titleLabel.setFont(new Font("Dialog",Font.BOLD,20));
        titleBox.add(titleLabel);
        containerBox.add(titleBox);
        containerBox.add(Box.createVerticalStrut(15));

        checkBox1 = Box.createHorizontalBox();
        nameLabel1 = new JLabel("book name: ");
        nameTextField1 = new JTextField(20);
        authorLabel1 = new JLabel("author: ");
        authorTextField1 = new JTextField(20);
        checkBox1.add(nameLabel1);
        checkBox1.add(Box.createHorizontalStrut(10));
        checkBox1.add(nameTextField1);
        checkBox1.add(Box.createHorizontalStrut(10));
        checkBox1.add(authorLabel1);
        checkBox1.add(Box.createHorizontalStrut(10));
        checkBox1.add(authorTextField1);
        containerBox.add(checkBox1);
        containerBox.add(Box.createVerticalStrut(15));

        checkBox2 = Box.createHorizontalBox();
        bookTypeLabel1 = new JLabel("book type: ");
        bookTypeComboBox1 = new JComboBox();
        checkButton = new JButton("inquire");
        resetButton1 = new JButton("reset");
        checkBox2.add(bookTypeLabel1);
        checkBox2.add(Box.createHorizontalStrut(10));
        checkBox2.add(bookTypeComboBox1);
        checkBox2.add(Box.createHorizontalStrut(10));
        checkBox2.add(checkButton);
        checkBox2.add(Box.createHorizontalStrut(10));
        checkBox2.add(resetButton1);
        containerBox.add(checkBox2);
        containerBox.add(Box.createVerticalStrut(15));

        tableBox = Box.createHorizontalBox();
        tableScrollPanel = new JScrollPane();
        tableScrollPanel.setViewportView(this.createTable());
        tableScrollPanel.setPreferredSize(new Dimension(700,250));
        tableBox.add(tableScrollPanel);
        containerBox.add(tableBox);
        containerBox.add(Box.createVerticalStrut(15));

        contentBox1 = Box.createHorizontalBox();
        idLabel = new JLabel("id: ");
        idTextField = new JTextField(3);
        nameLabel2 = new JLabel("book name: ");
        nameTextField2 = new JTextField(10);
        authorLabel2 = new JLabel("author: ");
        authorTextField2 = new JTextField(10);
        contentBox1.add(idLabel);
        contentBox1.add(Box.createHorizontalStrut(10));
        contentBox1.add(idTextField);
        contentBox1.add(Box.createHorizontalStrut(10));
        contentBox1.add(nameLabel2);
        contentBox1.add(Box.createHorizontalStrut(10));
        contentBox1.add(nameTextField2);
        contentBox1.add(Box.createHorizontalStrut(10));
        contentBox1.add(authorLabel2);
        contentBox1.add(Box.createHorizontalStrut(10));
        contentBox1.add(authorTextField2);
        containerBox.add(contentBox1);
        containerBox.add(Box.createVerticalStrut(15));

        contentBox2 = Box.createHorizontalBox();
        priceLabel = new JLabel("price");
        priceTextField = new JTextField(10);
        bookTypeLabel2 = new JLabel("book type: ");
        bookTypeComboBox2 = new JComboBox();
        contentBox2.add(priceLabel);
        contentBox2.add(Box.createHorizontalStrut(10));
        contentBox2.add(priceTextField);
        contentBox2.add(Box.createHorizontalStrut(10));
        contentBox2.add(bookTypeLabel2);
        contentBox2.add(Box.createHorizontalStrut(10));
        contentBox2.add(bookTypeComboBox2);
        containerBox.add(contentBox2);
        containerBox.add(Box.createVerticalStrut(15));

        descriptionBox = Box.createHorizontalBox();
        descriptionLabel = new JLabel("book description: ");
        descriptionTextArea = new JTextArea(3,10);
        descriptionBox.add(descriptionLabel);
        descriptionBox.add(descriptionTextArea);
        containerBox.add(descriptionBox);
        containerBox.add(Box.createVerticalStrut(15));

        buttonBox = Box.createHorizontalBox();
        updateButton = new JButton("update");
        deleteButton = new JButton("delete");
        resetButton2 = new JButton("reset");
        returnButton = new JButton("return");
        buttonBox.add(updateButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(deleteButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(resetButton2);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(returnButton);
        containerBox.add(buttonBox);

        return containerBox;


    }



    private JTable createTable(){
        String[][] rowdata = new BookManager().ListToArray(new BookManager().loadAllBook());
        String[] headers = {"id", "book name", "author", "price", "book type", "description"};
        for(int i = 0; i < rowdata.length; i++){
            rowdata[i][4] = new BookTypeManager().loadSelectedBookTypeName(Integer.parseInt(rowdata[i][4]))[0];
        }
        table = new JTable();
        table.setRowHeight(30);
        tableModel = new DefaultTableModel(rowdata,headers);
        table.setModel(tableModel);

        return table;
    }

    private void refreshTable(){
        String[][] rowdata = new BookManager().ListToArray(new BookManager().loadAllBook());
        String[] headers = {"id", "book name", "author", "price", "book type", "description"};
        for(int i = 0; i < rowdata.length; i++){
            rowdata[i][4] = new BookTypeManager().loadSelectedBookTypeName(Integer.parseInt(rowdata[i][4]))[0];
        }
        tableModel.setDataVector(rowdata, headers);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == checkButton){
            String name = nameTextField1.getText();
            String author = authorTextField1.getText();
            String booktype = (String)bookTypeComboBox1.getSelectedItem();
            String[][] rowdata = new BookManager().ListToArray(new BookManager().loadSelectedBook(name,author,booktype));
            String[] headers = {"id", "book name", "author", "price", "book type", "description"};
            for(int i = 0; i < rowdata.length; i++){
                rowdata[i][4] = new BookTypeManager().loadSelectedBookTypeName(Integer.parseInt(rowdata[i][4]))[0];
            }
            tableModel.setDataVector(rowdata, headers);

        }
        if(e.getSource() == resetButton1){
            String[][] rowdata = new BookManager().ListToArray(new BookManager().loadAllBook());
            String[] headers = {"id", "book name", "author", "price", "book type", "description"};
            for(int i = 0; i < rowdata.length; i++){
                rowdata[i][4] = new BookTypeManager().loadSelectedBookTypeName(Integer.parseInt(rowdata[i][4]))[0];
            }
            tableModel.setDataVector(rowdata, headers);
            nameTextField1.setText("");
            authorTextField1.setText("");
            bookTypeComboBox1.setSelectedIndex(0);
        }
        if(e.getSource() == updateButton){
            String id = idTextField.getText();
            String name = nameTextField2.getText();
            Float price = Float.parseFloat(priceTextField.getText());
            String author = authorTextField2.getText();
            String booktype = (String)bookTypeComboBox2.getSelectedItem();
            String description = descriptionTextArea.getText();
            boolean isOK = new BookManager().updateBook(id,name,author,price,booktype,description);
            if(isOK){
                refreshTable();
                idTextField.setText("");
                nameTextField2.setText("");
                authorTextField2.setText("");
                priceTextField.setText("");
                descriptionTextArea.setText("");
                bookTypeComboBox2.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Successfully updated!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Something went wrong...");
            }

        }
        if(e.getSource() == deleteButton){
            String id = idTextField.getText();
            int isOK = JOptionPane.showConfirmDialog(null, "For real?");
            if(isOK == JOptionPane.OK_OPTION) {
                boolean is = new BookManager().deleteBook(id);
                if(is){
                    refreshTable();
                    idTextField.setText("");
                    nameTextField2.setText("");
                    authorTextField2.setText("");
                    priceTextField.setText("");
                    descriptionTextArea.setText("");
                    bookTypeComboBox2.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null, "Successfully deleted!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Something went wrong...");
                }

            }
            else{
                refreshTable();
                idTextField.setText("");
                nameTextField2.setText("");
                authorTextField2.setText("");
                priceTextField.setText("");
                descriptionTextArea.setText("");
                bookTypeComboBox2.setSelectedIndex(0);
            }


        }

        if(e.getSource() == resetButton2){
            refreshTable();
            idTextField.setText("");
            nameTextField2.setText("");
            authorTextField2.setText("");
            priceTextField.setText("");
            descriptionTextArea.setText("");
            bookTypeComboBox2.setSelectedIndex(0);
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int getSelectedRowIndex = table.getSelectedRow();
        if (getSelectedRowIndex == -1){

        }
        else{
            idTextField.setText((String)table.getValueAt(getSelectedRowIndex, 0));
            nameTextField2.setText((String)table.getValueAt(getSelectedRowIndex, 1));
            authorTextField2.setText((String)table.getValueAt(getSelectedRowIndex, 2));
            priceTextField.setText((String)table.getValueAt(getSelectedRowIndex, 3));
            bookTypeComboBox2.setSelectedItem(table.getValueAt(getSelectedRowIndex, 4));
            descriptionTextArea.setText((String) table.getValueAt(getSelectedRowIndex, 5));

        }
    }

}
