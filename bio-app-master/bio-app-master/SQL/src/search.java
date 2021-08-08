import org.apache.poi.ss.usermodel.charts.ScatterChartData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class search {

    private JPanel panel1;
    private JButton insert;
    private JButton search;
    private JTextArea textArea1;
    private JTextField textField3;
    private JTextField textField2;
    private JTextField textField1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField textfield4;
    private JTextField textfield5;

    private void addtable(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bio", "tienloi", "meomotminh");

            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE tree ( Indx integer UNIQUE AUTO_INCREMENT,Description VARCHAR(100),Good VARCHAR(100),Neutral VARCHAR (100))");

            //System.out.println(rs.getString(1));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


    public search() {

        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    String nam = textField1.getText();
                    String description = textField2.getText();
                    String good = textField3.getText();
                    String bad = textfield4.getText();
                    String neutral = textfield5.getText();

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con= DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/bio","tienloi","meomotminh");

                    Statement stmt=con.createStatement();
                    stmt.executeUpdate("insert into tree(Nam,Description,Good,Bad,Neutral) values('"+nam
                                    +"','"+description+"','"+good+"','"+bad+"','"+neutral+"')");

                    //System.out.println(rs.getString(1));
                    con.close();
                }catch(Exception exception){
                    System.out.println(exception);
                    addtable();
                }
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String nam = textField1.getText();

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/bio","tienloi","meomotminh");

                    System.out.println(nam);

                    Statement stmt=con.createStatement();
                    ResultSet rs=stmt.executeQuery("select * from tree where Nam like '%"+nam+"%'");
                    while(rs.next()) {

                        textArea1.append(rs.getInt(1) + " \n " + rs.getString(2) + " \n " + rs.getString(3) + " \n " + rs.getString(4) + " \n " + rs.getString(5) + " \n " + rs.getString(6) + "\n");

                        textField1.setText(rs.getString(2));
                        textField2.setText(rs.getString(3));
                        textField3.setText(rs.getString(4));
                        textfield4.setText(rs.getString(5));
                        textfield5.setText(rs.getString(6));
                    }

                    con.close();
                }catch(Exception exception){ System.out.println(e);}
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new search().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
