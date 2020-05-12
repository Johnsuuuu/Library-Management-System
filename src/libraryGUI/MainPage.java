package libraryGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainPage implements ActionListener{
    private JFrame mainWindow;
    private JMenuBar menuBar;
    private JMenu bookTypeManageMenu;
    private JMenu bookManageMenu;
    private JMenuItem bookTypeAddMenuItem;
    private JMenuItem bookTypeManageMenuItem;
    private JMenuItem bookAddMenuItem;
    private JMenuItem bookManageMenuItem;
    private JPanel panel;
    private JLabel bookimage;
    private Icon icon;

    public MainPage(){
        mainWindow = new JFrame("Library Management System");
        mainWindow.setSize(757,632);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setJMenuBar(createMenuBar());
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setContentPane(createMainPanel());

        mainWindow.setVisible(true);

        bookTypeAddMenuItem.addActionListener(this);
        bookTypeManageMenuItem.addActionListener(this);
        bookAddMenuItem.addActionListener(this);
        bookManageMenuItem.addActionListener(this);


    }

    private JPanel createMainPanel() {
        panel = new JPanel();
        bookimage = new JLabel();
        icon = new ImageIcon("/Users/john/IdeaProjects/myLibrary/src/images/book2.jpg");
        bookimage.setIcon(icon);
        bookimage.setSize(icon.getIconWidth(),icon.getIconHeight());
        panel.add(bookimage);

        return panel;


    }

    private JMenuBar createMenuBar(){
        menuBar = new JMenuBar();

        //book type management
        bookTypeManageMenu = new JMenu("Book Type Management");
        bookTypeAddMenuItem = new JMenuItem("Add Book Type");
        bookTypeManageMenuItem = new JMenuItem("Edit Book Type Info");
        bookTypeManageMenu.add(bookTypeAddMenuItem);
        bookTypeManageMenu.add(bookTypeManageMenuItem);
        menuBar.add(bookTypeManageMenu);

        //book management
        bookManageMenu = new JMenu("Book Management");
        bookAddMenuItem = new JMenuItem("Add Book");
        bookManageMenuItem = new JMenuItem("Edit Book Info");
        bookManageMenu.add(bookAddMenuItem);
        bookManageMenu.add(bookManageMenuItem);
        menuBar.add(bookManageMenu);

        return menuBar;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bookTypeAddMenuItem){
            mainWindow.setContentPane(new BookTypeAddPanel());
            mainWindow.setVisible(true);
        }

        if(e.getSource() == bookTypeManageMenuItem){
            mainWindow.setContentPane(new BookTypeManagePanel());
            mainWindow.setVisible(true);
        }
        if(e.getSource() == bookAddMenuItem){
            mainWindow.setContentPane(new BookAddPanel());
            mainWindow.setVisible(true);
        }
        if(e.getSource() == bookManageMenuItem){
            mainWindow.setContentPane(new BookManagePanel());
            mainWindow.setVisible(true);
        }
    }
}
