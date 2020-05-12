package libraryGUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import LibraryControl.BookTypeManager;

public class BookTypeManagePanel extends JPanel implements ActionListener, ListSelectionListener {

    private Box containerBox;
    private Box titleBox;
    private Box bookTypeCheckBox;
    private Box tableBox;
    private Box bookTypeIdAndNameBox;
    private Box bookTypeDescriptionBox;
    private Box buttonBox;
    private JButton checkButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton returnButton;
    private JLabel titleLabel;
    private JLabel bookTypeNameLabel1;
    private JLabel bookTypeNameLabel2;
    private JLabel bookTypeIdLabel;
    private JLabel bookTypeDescriptionLabel;
    private JTextField bookTypeNameTextField1;
    private JTextField bookTypeNameTextField2;
    private JTextField bookTypeIdTextField;
    private JTextArea bookTypeDescriptionTextArea;
    private JScrollPane tableScrollPanel;
    private JTable table;
    private DefaultTableModel tableModel;

    public BookTypeManagePanel(){
        this.add(createBookTypeManageVBox());

        checkButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        returnButton.addActionListener(this);
        table.getSelectionModel().addListSelectionListener(this);

    }

    private Box createBookTypeManageVBox(){
        containerBox = Box.createVerticalBox();

        titleBox = Box.createHorizontalBox();
        titleLabel = new JLabel("Edit Book Type Info");
        titleLabel.setFont(new Font("Dialog", Font.BOLD,20));
        titleBox.add(titleLabel);
        containerBox.add(titleBox);
        containerBox.add(Box.createVerticalStrut(20));

        bookTypeCheckBox = Box.createHorizontalBox();
        bookTypeNameLabel1 = new JLabel("book type name: ");
        bookTypeNameTextField1 = new JTextField(10);
        checkButton = new JButton("inquire");
        bookTypeCheckBox.add(bookTypeNameLabel1);
        bookTypeCheckBox.add(Box.createHorizontalStrut(30));
        bookTypeCheckBox.add(bookTypeNameTextField1);
        bookTypeCheckBox.add(Box.createHorizontalStrut(30));
        bookTypeCheckBox.add(checkButton);
        containerBox.add(bookTypeCheckBox);
        containerBox.add(Box.createVerticalStrut(20));

        tableBox = Box.createVerticalBox();
        tableScrollPanel = new JScrollPane();
        tableScrollPanel.setViewportView(createTable());
        tableScrollPanel.setPreferredSize(new Dimension(575,250));
        tableBox.add(tableScrollPanel);
        containerBox.add(tableBox);
        containerBox.add(Box.createVerticalStrut(20));

        bookTypeIdAndNameBox = Box.createHorizontalBox();
        bookTypeIdLabel = new JLabel("id: ");
        bookTypeIdTextField = new JTextField(10);
        bookTypeIdTextField.setEnabled(false);
        bookTypeNameLabel2 = new JLabel("book type name: ");
        bookTypeNameTextField2 = new JTextField(10);
        bookTypeIdAndNameBox.add(bookTypeIdLabel);
        bookTypeIdAndNameBox.add(Box.createHorizontalStrut(20));
        bookTypeIdAndNameBox.add(bookTypeIdTextField);
        bookTypeIdAndNameBox.add(Box.createHorizontalStrut(20));
        bookTypeIdAndNameBox.add(bookTypeNameLabel2);
        bookTypeIdAndNameBox.add(Box.createHorizontalStrut(20));
        bookTypeIdAndNameBox.add(bookTypeNameTextField2);
        containerBox.add(bookTypeIdAndNameBox);
        containerBox.add(Box.createVerticalStrut(20));

        bookTypeDescriptionBox = Box.createHorizontalBox();
        bookTypeDescriptionLabel = new JLabel("book type description: ");
        bookTypeDescriptionTextArea = new JTextArea(5,40);
        bookTypeDescriptionTextArea.setLineWrap(true);
        bookTypeDescriptionBox.add(bookTypeDescriptionLabel);
        bookTypeDescriptionBox.add(Box.createHorizontalStrut(20));
        bookTypeDescriptionBox.add(bookTypeDescriptionTextArea);
        containerBox.add(bookTypeDescriptionBox);
        containerBox.add(Box.createVerticalStrut(20));

        buttonBox = Box.createHorizontalBox();
        updateButton = new JButton("update");
        deleteButton = new JButton("delete");
        returnButton = new JButton("return");
        buttonBox.add(updateButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(deleteButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(returnButton);
        containerBox.add(buttonBox);
        containerBox.add(Box.createVerticalStrut(20));

        return containerBox;

    }

    private JTable createTable(){
        String[][] rowdata = new BookTypeManager().ListToArray(new BookTypeManager().loadAllBookType());
        String[] header = {"id","name","description"};
        table = new JTable();
        table.setRowHeight(30);
        tableModel = new DefaultTableModel(rowdata, header);
        table.setModel(tableModel);

        return table;
    }

    private void refreshTable(){
        String[][] rowdata = new BookTypeManager().ListToArray(new BookTypeManager().loadAllBookType());
        String[] header = {"id","name","description"};
        tableModel.setDataVector(rowdata, header);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == checkButton){
            String[][] rowdata;
            String[] header = {"id","name","description"};
            if(bookTypeNameTextField1.getText().equals("")){
                rowdata = new BookTypeManager().ListToArray(new BookTypeManager().loadAllBookType());
            }
            else{
                String bookTypeName = bookTypeNameTextField1.getText();
                rowdata = new BookTypeManager().ListToArray(new BookTypeManager().loadSelectedBookType(bookTypeName));
            }
            tableModel.setDataVector(rowdata, header);
        }
        if(e.getSource() == updateButton){
            String id = bookTypeIdTextField.getText();
            String name = bookTypeNameTextField2.getText();
            String description = bookTypeDescriptionTextArea.getText();
            boolean isOK = new BookTypeManager().updateBookType(id,name,description);
            if(isOK){
                refreshTable();
                bookTypeIdTextField.setText("");
                bookTypeNameTextField2.setText("");
                bookTypeDescriptionTextArea.setText("");
                JOptionPane.showMessageDialog(null, "Successfully updated!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Something went wrong...");
            }

        }
        if(e.getSource() == deleteButton){
            String id = bookTypeIdTextField.getText();
            int isOK = JOptionPane.showConfirmDialog(null, "For real?");
            if(isOK == JOptionPane.OK_OPTION) {
                boolean is = new BookTypeManager().deleteBookType(id);
                if(is){
                    refreshTable();
                    bookTypeIdTextField.setText("");
                    bookTypeNameTextField2.setText("");
                    bookTypeDescriptionTextArea.setText("");
                    JOptionPane.showMessageDialog(null, "Successfully deleted!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Something went wrong...");
                }

            }
            else{
                refreshTable();
                bookTypeIdTextField.setText("");
                bookTypeNameTextField2.setText("");
                bookTypeDescriptionTextArea.setText("");
            }

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
        if(getSelectedRowIndex == -1){
            refreshTable();
        }
        else{
            bookTypeIdTextField.setText((String)table.getValueAt(getSelectedRowIndex,0));
            bookTypeNameTextField2.setText((String)table.getValueAt(getSelectedRowIndex, 1));
            bookTypeDescriptionTextArea.setText((String)table.getValueAt(getSelectedRowIndex, 2));
        }
    }
}
