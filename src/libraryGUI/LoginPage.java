package libraryGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LoginPage implements ActionListener{
    private JFrame loginWindow;
    private JPanel titlePane;
    private JPanel upperPane;
    private JPanel lowerPane;
    private JButton loginButton;
    private JLabel userLabel;
    private JLabel pwdLabel;
    private JLabel titleLabel;
    private JTextField edtUserId;
    private JPasswordField edtPwd;

    public LoginPage(){
        loginWindow = new JFrame("log in");
        loginWindow.setSize(350,190);
        loginWindow.setLayout(new BorderLayout());
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setLocationRelativeTo(null);

        titlePane = new JPanel();
        loginWindow.add(titlePane,BorderLayout.NORTH);
        titlePane.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePane.setPreferredSize(new Dimension(0, 45));

        upperPane = new JPanel();
        loginWindow.add(upperPane,BorderLayout.CENTER);
        upperPane.setLayout(new FlowLayout(FlowLayout.CENTER));

        lowerPane = new JPanel();
        loginWindow.add(lowerPane,BorderLayout.SOUTH);
        lowerPane.setLayout(new FlowLayout(FlowLayout.CENTER));

        titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Dialog",Font.BOLD,15));
        titlePane.add(titleLabel);

        loginButton = new JButton("log in");
        loginButton.addActionListener(this);
        lowerPane.add(loginButton);

        userLabel = new JLabel("username");
        pwdLabel = new JLabel("password");
        edtUserId = new JTextField(20);
        edtPwd = new JPasswordField(20);
        upperPane.add(userLabel);
        upperPane.add(edtUserId);
        upperPane.add(pwdLabel);
        upperPane.add(edtPwd);

        loginWindow.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == loginButton){
            if(edtUserId.getText().equals("John") && String.valueOf(edtPwd.getPassword()).equals("1702")){
                //int isOK = JOptionPane.showConfirmDialog(null, "congrats");
                //JOptionPane.showMessageDialog(null, "You are logged in.");
                MainPage mainPage = new MainPage();
                loginWindow.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "Please input the correct username or password.");
                edtUserId.setText("");
                edtPwd.setText("");
            }
        }

    }

    public static void main(String[] args){
        LoginPage loginPage = new LoginPage();

    }

}
