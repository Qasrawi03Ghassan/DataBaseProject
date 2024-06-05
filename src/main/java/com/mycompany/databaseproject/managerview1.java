/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.databaseproject;
import static com.mycompany.databaseproject.engview1.getRowIndex;
import static com.mycompany.databaseproject.studentview1.TH;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Msys
 */
public class managerview1 extends javax.swing.JFrame {
public static boolean darkmode=false;
public static String username;
public static String ID;

static Color darkcolor =new java.awt.Color(0,153,255);
static Color lightcolor = new java.awt.Color(56, 175, 255);
static Color lightercolor =new java.awt.Color(66,179,255);

static editmanageraccount manageredit;
static viewengprofile1 managerprofile;
public static boolean engtable;
    /**
     * Creates new form engview1
     */
    public managerview1(String u,String id) {     
        username=u;  
        ID=id;     
        darkcolor =new java.awt.Color(0,153,255);
        lightcolor = new java.awt.Color(56, 175, 255);
        lightercolor =new java.awt.Color(66,179,255);
        manageredit =new editmanageraccount(darkmode,username,ID,darkcolor,lightcolor,lightercolor,this);
        managerprofile= new viewengprofile1(darkmode,username,ID,"","",1,"",1,darkcolor,lightcolor,lightercolor);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //this.jButton8.setVisible(false); //editAccount needs fixing
        
        //Customizing table headers:
        TH = this.jTable4.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        TH = this.jTable1.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        TH = this.jTable2.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        TH = this.jTable5.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        TH = this.jTable3.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        //Aligning cells:
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<this.jTable1.getColumnCount();i++){
            this.jTable1.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        
        tcr.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<this.jTable2.getColumnCount();i++){
            this.jTable2.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        
        tcr.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<this.jTable3.getColumnCount();i++){
            this.jTable3.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        
        tcr.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<this.jTable4.getColumnCount();i++){
            this.jTable4.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        
        tcr.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<this.jTable5.getColumnCount();i++){
            this.jTable5.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        
        getProjectTable();
        getManageProjectTable();
        
        getStudentsTable();
        getStudentsInDepTable();
        
        getEngineersInDepTable();
        
        for(int i=0;i<jTable1.getRowCount();i++){
            jComboBox2.addItem(jTable1.getValueAt(i,0).toString());
        }
        
    }
    public void showMngStus(){
        jPanel10.setVisible(true);
    }
    public void getEngineersInDepTable(){
        //Getting Students from DB:
        try {

            String EngID="";
            String Fname="";
            String Lname="";
            String Bdate = "";
            String Sex = "";
            String Pnumber = "";
            String workHours = "";
            String Degree = "";
            int Salary = 0;
            //String DepID = "";
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            
            String mngdep = "select * from architecture_firm.\"Full-time_Engineer\" where \"ID\" = '" + ID + "'";
            String qry = "";
            
            ResultSet rs0 = stmt.executeQuery(mngdep);
            while(rs0.next()){
                qry = "select * from architecture_firm.\"Full-time_Engineer\" where \"Department_ID\" = '" + rs0.getString("Department_ID") + "'";
                ResultSet rs = stmt2.executeQuery(qry);
                while(rs.next()){
                    EngID = rs.getString("ID");
                    Fname = rs.getString("First_name");
                    Lname = rs.getString("Last_name");
                    Bdate = rs.getString("Birthdate");
                    Sex = rs.getString("Sex");
                    Pnumber = rs.getString("Phone_number");
                    workHours = rs.getString("Work_hours");
                    Degree = rs.getString("Degree");
                    Salary = Integer.parseInt(rs.getString("Salary")); 
                    
                    String DataArr2[]={EngID,Fname,Lname,workHours,Degree,Integer.toString(Salary)};
                    
                    DefaultTableModel tbm = (DefaultTableModel)this.jTable5.getModel();
                    
                    if(!(EngID.equals(ID))){ //Using this "if" to prevent showing the manager in the table
                       tbm.addRow(DataArr2); 
                    }
                    
                    
                    
                    
                    
                }
                rs.close();
            }
            rs0.close();
            
            con.commit();
            con.close();
            
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void getStudentsInDepTable(){
        //Getting Students from DB:
        try {

            String StuID="";
            String Fname="";
            String Lname="";
            String Bdate = "";
            String Sex = "";
            String Pnumber = "";
            String workHours = "";
            String CollegeYear = "";
            String RequiredHours = "";
            //String DepID = "";
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            
            String mngdep = "select \"Department_ID\" from architecture_firm.\"Full-time_Engineer\" where \"ID\" = '" + ID + "'";
            String qry = "";
            
            ResultSet rs0 = stmt.executeQuery(mngdep);
            while(rs0.next()){
                qry = "select * from architecture_firm.\"Student_trainee\" where \"Department_ID\" = '" + rs0.getString("Department_ID") + "'";
                ResultSet rs = stmt2.executeQuery(qry);
                while(rs.next()){
                    StuID = rs.getString("ID");
                    Fname = rs.getString("First_name");
                    Lname = rs.getString("Last_name");
                    Bdate = rs.getString("Birthdate");
                    Sex = rs.getString("Sex");
                    Pnumber = rs.getString("Phone_number");
                    workHours = rs.getString("Work_hours");
                    CollegeYear = rs.getString("College_year");
                    RequiredHours = rs.getString("Required_hours"); 
                    
                    String DataArr2[]={StuID,Fname,Lname,workHours,CollegeYear,RequiredHours};
                    
                    DefaultTableModel tbm = (DefaultTableModel)this.jTable3.getModel();
                    tbm.addRow(DataArr2);
                    
                }
                rs.close();
            }
            rs0.close();
            
            con.commit();
            con.close();
            
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton12 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel34 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jPanel6.setVisible(false);
        jPanel6.setBackground(new java.awt.Color(56, 175, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(56, 175, 255)));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1)seethru.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sidearrow.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbsetting(1).png"))); // NOI18N
        jLabel9.setText("edit profile");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel9.setOpaque(true);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dblogout.png"))); // NOI18N
        jLabel10.setText("sign out");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel10.setOpaque(true);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel10MouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        /*
        jLabel8.setText("account name");
        */
        jLabel8.setText(username);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        jLabel12.setText("Dark mode");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel12.setOpaque(true);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jLabel14.setText("Exit  ");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel14.setOpaque(true);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(51, 51, 51));
        jPanel13.setLayout(null);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blue theme (default)", "Red theme", "Green theme", "Orange theme", "Purple theme" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel13.add(jComboBox1);
        jComboBox1.setBounds(0, 0, 179, 30);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Projects", "Customers", "Lands", "Engineers", "Students" }));

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 0, 0));
        jButton12.setText("Generate report");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton12MouseExited(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addGap(44, 44, 44))
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(920, 0, 180, 680);

        jPanel12.setVisible(false);

        jPanel10.setVisible(false);

        jButton9.setBackground(new java.awt.Color(0, 153, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Back");
        jButton9.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton9MouseExited(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTable3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "First name", "Last name", "Work shift", "College year", "Required hours"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setRowHeight(70);
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable3);

        jTable5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Engineer ID", "First name", "Last name", "Work shift", "Degree", "Salary"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setRowHeight(70);
        jTable5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable5);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 153, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Students");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Engineers");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 255));
        jLabel18.setText("Work shift:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 153, 255));
        jLabel22.setText("Salary:");

        jTextField5.setForeground(new java.awt.Color(153, 153, 153));
        jTextField5.setText("enter work shift here");

        jTextField6.setForeground(new java.awt.Color(153, 153, 153));
        jTextField6.setText("enter salary here");

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Update engineer");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete engineer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 153, 255));
        jLabel23.setText("Work shift:");

        jTextField7.setForeground(new java.awt.Color(153, 153, 153));
        jTextField7.setText("enter work shift here");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 153, 255));
        jLabel24.setText("Required hours:");

        jTextField8.setForeground(new java.awt.Color(153, 153, 153));
        jTextField8.setText("enter required hours here");

        jButton4.setBackground(new java.awt.Color(0, 153, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Delete student");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Update student");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel42.setText("Manage engineers and students in your department here");

        jButton8.setBackground(new java.awt.Color(0, 153, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Manage account");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)
                        .addComponent(jLabel42)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addGap(110, 110, 110))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addGap(139, 139, 139)
                        .addComponent(jLabel17)
                        .addGap(210, 210, 210))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel12);
        jPanel12.setBounds(0, 150, 1100, 530);

        jPanel10.setVisible(false);

        jPanel10.setVisible(false);

        jButton10.setBackground(new java.awt.Color(0, 153, 255));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Back");
        jButton10.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton10MouseExited(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTable4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First name", "Last name", "Birth date", "Sex/Gender", "Phone number", "Work shift", "College year", "Required hours"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setRowHeight(70);
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jButton15.setBackground(new java.awt.Color(0, 153, 255));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Delete student");
        jButton15.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton15MouseExited(evt);
            }
        });
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(0, 153, 255));
        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Modify student");
        jButton16.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton16MouseExited(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(0, 153, 255));
        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Add new student");
        jButton17.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton17MouseExited(evt);
            }
        });
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel41.setText("Manage your students here:");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 153, 255));
        jLabel35.setText("Student name:");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 255));
        jLabel38.setText("name");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 153, 255));
        jLabel36.setText("Reqiured hours:");

        jTextField9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField9.setText("enter required hours here");
        jTextField9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField9MouseClicked(evt);
            }
        });
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 153, 255));
        jLabel37.setText("Work shift:");

        jTextField10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField10.setText("enter work shift here");
        jTextField10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField10MouseExited(evt);
            }
        });
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 153, 255));
        jLabel43.setText("Search student here:");

        jTextField11.setForeground(new java.awt.Color(153, 153, 153));
        jTextField11.setText("Start typing and it will do the search");
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField11MouseClicked(evt);
            }
        });
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("+");
        jLabel25.setForeground(darkcolor);
        if(darkmode)
        jLabel25.setForeground(Color.white);
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("-");
        jLabel26.setToolTipText("");
        jLabel26.setForeground(darkcolor);
        if(darkmode)
        jLabel26.setForeground(Color.white);
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 153, 255));
        jLabel45.setText("Assign project:");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(477, 477, 477)
                        .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel38))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)))
                .addGap(0, 14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel43)
                .addGap(319, 319, 319))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel43)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField10)
                        .addComponent(jLabel37))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(jLabel38)))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel45)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10);
        jPanel10.setBounds(0, 150, 1100, 530);

        jPanel9.setVisible(false);

        jPanel9.setVisible(false);

        jButton5.setBackground(new java.awt.Color(0, 153, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Back");
        jButton5.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Project ID", "Project name", "Customer ID", "Land ID", "Type", "Area", "Floors number", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(70);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton7.setBackground(new java.awt.Color(0, 153, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Choose project");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel39.setText("View projects in your department here:");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 153, 255));
        jLabel44.setText("Search project here:");

        jTextField12.setForeground(new java.awt.Color(153, 153, 153));
        jTextField12.setText("Start typing and it will do the search");
        jTextField12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField12MouseClicked(evt);
            }
        });
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(104, 104, 104)
                                .addComponent(jLabel44)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField12))
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton7)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel9);
        jPanel9.setBounds(0, 150, 1100, 530);

        jPanel8.setVisible(false);

        jPanel8.setVisible(false);

        jButton6.setBackground(new java.awt.Color(0, 153, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Back");
        jButton6.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Project ID", "Project name", "Type", "Area", "Floors number", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(70);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jButton18.setBackground(new java.awt.Color(0, 153, 255));
        jButton18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Modify project");
        jButton18.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton18MouseExited(evt);
            }
        });
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 153, 255));
        jLabel30.setText("Project name:");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setText("enter project name here");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 153, 255));
        jLabel31.setText("Project type:");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.setText("enter project type here");
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 153, 255));
        jLabel32.setText("Project area:");

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField3.setText("enter project area here");
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField3MouseClicked(evt);
            }
        });
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 153, 255));
        jLabel33.setText("Project description:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("enter project description here");
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea1);

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 153, 255));
        jLabel34.setText("Project floors:");

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField4.setText("enter project floors number here");
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel40.setText("Manage selected projects here:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGap(0, 44, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel33))
                                    .addGap(50, 50, 50)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(106, 106, 106)
                                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel40))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField2))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(54, 54, 54))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel8);
        jPanel8.setBounds(0, 150, 1100, 530);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbsettings.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1)seethru.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/managestudent.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("manage students");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manageproject.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("manage projects");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel15)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 255));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5MouseExited(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/viewproject.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("View projects");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(0, 153, 255));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel11MouseExited(evt);
            }
        });

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbmanagesettings.png"))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("manage department");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 1100, 150);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/voltair-j-mayer-h-architects_26(eng)1.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7);
        jPanel7.setBounds(0, 150, 1100, 530);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1101, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void getStudentsTable(){
        //Getting Students from DB:
        try {

            String StuID="";
            String Fname="";
            String Lname="";
            String Bdate = "";
            String Sex = "";
            String Pnumber = "";
            String workHours = "";
            String CollegeYear = "";
            String RequiredHours = "";
            //String DepID = "";
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            //String EngDepID = "";
            
            
            //String EngDep = "select \"Department_ID\" from architecture_firm.\"Full-time_Engineer\" where \"ID\"='" + ID + "'";
            
            String qry = "select * from architecture_firm.\"Student_trainee\" where \"Supervisor_ID\" = '" + ID + "'";
            //String qrysm = "";
            
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(qry);
            while(rs.next()){                
               StuID = rs.getString("ID");
               Fname = rs.getString("First_name");
               Lname = rs.getString("Last_name");
               Bdate = rs.getString("Birthdate");
               Sex = rs.getString("Sex");
               Pnumber = rs.getString("Phone_number");
               workHours = rs.getString("Work_hours");
               CollegeYear = rs.getString("College_year");
               RequiredHours = rs.getString("Required_hours");               
               //DepID = rs.getString("Department_ID");

               
               String DataArr[] = {StuID,Fname,Lname,Bdate,Sex,Pnumber,workHours,CollegeYear,RequiredHours};
               String DataArr2[]={StuID,Fname,Lname,workHours,CollegeYear,RequiredHours};
               
               DefaultTableModel tbm = (DefaultTableModel)this.jTable4.getModel();
               tbm.addRow(DataArr);
               
               //tbm = (DefaultTableModel)this.jTable3.getModel();
               //tbm.addRow(DataArr2);
               
            }
            rs.close();
            
            
            con.commit();
            con.close();
            
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
    }    
    
    public void changetheme(Boolean Dark, Color dark,Color light,Color lighter){
        // TODO add your handling code here:
        //darkmode=!darkmode;
        
        darkmode =Dark;
        darkcolor = dark;
        lightcolor =light;
        lightercolor = lighter;
        
        jComboBox1.setVisible(!darkmode);
        //darkmode code
        if(darkmode)
        {
        //jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lightmode.png"))); // NOI18N
        //jLabel12.setText("light mode"); 
        jPanel2.setBackground(Color.black);
        jPanel3.setBackground(Color.black);
          if(jPanel10.isVisible())
        {
            jPanel3.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel4.setBackground(Color.black);
        if(jPanel8.isVisible())
        {
            jPanel4.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel5.setBackground(Color.black);
          if(jPanel9.isVisible())
        {
            jPanel5.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel11.setBackground(Color.black);
        if(jPanel12.isVisible())
        {
            jPanel11.setBackground(new java.awt.Color(51,51,51));
        }
        //jLabel12.setBackground(new java.awt.Color(81,81,81));
        jPanel6.setBackground(new java.awt.Color(51,51,51));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,51,51)));
        //jLabel12.setForeground(Color.white);
        
        jPanel10.setBackground(new java.awt.Color(242,242,242));
        jPanel12.setBackground(new java.awt.Color(242,242,242));
        jPanel14.setBackground(new java.awt.Color(61,61,61));
        jPanel15.setBackground(new java.awt.Color(61,61,61));
        jPanel16.setBackground(new java.awt.Color(61,61,61));
        jPanel17.setBackground(new java.awt.Color(61,61,61));
        
        
        
        jLabel30.setForeground(Color.white);
        jLabel31.setForeground(Color.white);
        jLabel32.setForeground(Color.white);
        jLabel33.setForeground(Color.white);
        jLabel34.setForeground(Color.white);
        jLabel35.setForeground(Color.white);
        jLabel36.setForeground(Color.white);
        jLabel37.setForeground(Color.white);
        jLabel38.setForeground(Color.white);
        jLabel39.setForeground(Color.white);
        jLabel40.setForeground(Color.white);
        jLabel41.setForeground(Color.white);
        jLabel42.setForeground(Color.white);
        jLabel16.setForeground(Color.white);
        jLabel17.setForeground(Color.white);
        jLabel18.setForeground(Color.white);
        jLabel22.setForeground(Color.white);
        jLabel23.setForeground(Color.white);
        jLabel24.setForeground(Color.white);
        jLabel25.setForeground(Color.white);
        jLabel26.setForeground(Color.white);
        jLabel45.setForeground(Color.white);
        
        jButton1.setBackground(Color.black);
        jButton2.setBackground(Color.black);
        jButton3.setBackground(Color.black);
        jButton4.setBackground(Color.black);
        jButton5.setBackground(Color.black);
        jButton10.setBackground(Color.black);
        jButton6.setBackground(Color.black);
        jButton7.setBackground(Color.black);
        jButton8.setBackground(Color.black);
        jButton9.setBackground(Color.black);
        jButton15.setBackground(Color.black);
        jButton16.setBackground(Color.black);
        jButton17.setBackground(Color.black);
        jButton18.setBackground(Color.black);
        
        TH = jTable4.getTableHeader();
        TH.setBackground(Color.black);
        
        TH = jTable1.getTableHeader();
        TH.setBackground(Color.black);
        
        TH = jTable2.getTableHeader();
        TH.setBackground(Color.black);
        
        TH = jTable3.getTableHeader();
        TH.setBackground(Color.black);
        
        TH = jTable5.getTableHeader();
        TH.setBackground(Color.black);
        
    //    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1)dark.png"))); // NOI18N
  //     jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1)dark.png"))); // NOI18N
        
        }
        //lightmode code
         if(!darkmode)
        {
        //jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        //jLabel12.setText("Dark mode");
        jPanel2.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel10.isVisible())
        {
            jPanel3.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel8.isVisible())
        {
            jPanel4.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel9.isVisible())
        {
            jPanel5.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel12.isVisible())
        {
            jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel6.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        //jLabel12.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        //jLabel12.setForeground(Color.white);
        jButton10.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton1.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton2.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton3.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton4.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton7.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton8.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton9.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton15.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton16.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton17.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton18.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        
        jLabel30.setForeground(darkcolor);
        jLabel31.setForeground(darkcolor);
        jLabel32.setForeground(darkcolor);
        jLabel33.setForeground(darkcolor);
        jLabel34.setForeground(darkcolor);
        jLabel35.setForeground(darkcolor);
        jLabel36.setForeground(darkcolor);
        jLabel37.setForeground(darkcolor);
        jLabel38.setForeground(darkcolor);
        jLabel16.setForeground(darkcolor);
        jLabel17.setForeground(darkcolor);
        jLabel18.setForeground(darkcolor);
        jLabel22.setForeground(darkcolor);
        jLabel23.setForeground(darkcolor);
        jLabel24.setForeground(darkcolor);
        jLabel39.setForeground(Color.black);
        jLabel40.setForeground(Color.black);
        jLabel41.setForeground(Color.black);
        jLabel42.setForeground(Color.black);
        jLabel43.setForeground(darkcolor);
        jLabel44.setForeground(darkcolor);
        jLabel45.setForeground(darkcolor);
        jLabel25.setForeground(darkcolor);
        jLabel26.setForeground(darkcolor);
        
        
        jPanel10.setBackground(new java.awt.Color(242,242,242));
        jPanel12.setBackground(new java.awt.Color(242,242,242));
        jPanel14.setBackground(new java.awt.Color(242,242,242));
        jPanel15.setBackground(new java.awt.Color(242,242,242));
        jPanel16.setBackground(new java.awt.Color(242,242,242));
        jPanel17.setBackground(new java.awt.Color(242,242,242));
        
        TH = jTable4.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable1.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable2.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable3.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable5.getTableHeader();
        TH.setBackground(darkcolor);
        
   //     jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N
   //     jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N  
        }
          if(jPanel6.isVisible())
       { jPanel6.setVisible(false);
               jPanel6.setVisible(true);

               }
          
          manageredit.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(manageredit.isVisible())
       { manageredit.setVisible(false);
               manageredit.setVisible(true);
               }
         
         managerprofile.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(managerprofile.isVisible())
       { managerprofile.setVisible(false);
               managerprofile.setVisible(true);
               }
    }
    
    public void getManageProjectTable(){
        try {

            String name = "";
            String LandID="";
            String type = "";
            int Floors = 0;
            String SFloors = "";
            int Area = 0;
            String SArea = "";
            String Desc = "";
            
            String PID = "";
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            Statement stmt = con.createStatement();
            
            int projectscount = 0;
            String qry0 = "select count(\"Project_ID\") from architecture_firm.\"Engineer_project\" where \"Engineer_ID\" = '" + ID + "'";
            
            ResultSet rs0 = stmt.executeQuery(qry0);
            while(rs0.next()){
                projectscount = rs0.getInt("count");
            }
            rs0.close();
            
            String AssignedProjectsIDs[] = new String[projectscount];
            
            
            String qry = "select * from architecture_firm.\"Engineer_project\" where \"Engineer_ID\" = '" + ID + "'";
            
            ResultSet rs = stmt.executeQuery(qry);
            for(int i=0;i<AssignedProjectsIDs.length;i++){
                if(rs.next()){
                    AssignedProjectsIDs[i] = rs.getString("Project_ID");
                }
            }
            rs.close();
            
            //testing getting ProjectIDs for assigned Eng:
            //for(int i=0;i<AssignedProjectsIDs.length;i++){
            //    System.out.println(AssignedProjectsIDs[i]);
            //}
            
            for(int i=0;i<AssignedProjectsIDs.length;i++){
                String qryget = "select * from architecture_firm.\"Project\" where \"Project_ID\" ='" + AssignedProjectsIDs[i] + "'";
                ResultSet rs1 = stmt.executeQuery(qryget);
                while(rs1.next()){
                    PID = rs1.getString("Project_ID");
                    name = rs1.getString("Project_name");
                    LandID = rs1.getString("Land_ID");
                    type = rs1.getString("Type");
                    Desc = rs1.getString("Description");
                    Floors = rs1.getInt("Number_of_floors");
                    SFloors = Integer.toString(Floors);
                    Area = rs1.getInt("Project_area");
                    SArea = Integer.toString(Area);
                    
                        String DataArr[] = {PID,name,type,SArea,SFloors,Desc};
                    DefaultTableModel a = (DefaultTableModel)this.jTable2.getModel();
                    a.addRow(DataArr);
                }
                rs1.close();                
            }
            
            
            con.commit();
            con.close();
            
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void getProjectTable(){
        //Getting projects from DB:
        try {

            String name = "";
            String LandID="";
            String custID="";
            String type = "";
            int Floors = 0;
            String SFloors = "";
            int Area = 0;
            String SArea = "";
            String Desc = "";
            String DepID ="";
            
            String PID = "";
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String DepIDforEng;
            
            String qryDep = "select \"Department_ID\" from architecture_firm.\"Full-time_Engineer\" where \"ID\"='" + ID + "'";
            
            String qryView = "select * from architecture_firm.\"Project\"";
            
            String qry = "select * from architecture_firm.\"Project\"";
            
            Statement stmt = con.createStatement();
            
            ResultSet Deprs = stmt.executeQuery(qryDep);
            while(Deprs.next()){
                DepIDforEng = Deprs.getString("Department_ID");
                qryView = "select * from architecture_firm.\"Project\" where \"Department_ID\"='" + DepIDforEng + "'";
                //qry = "select * from architecture_firm.\"Project\" where \"Department_ID\"='" + DepIDforEng + "'";
            }
            Deprs.close();
            
            
            
            ResultSet viewrs = stmt.executeQuery(qryView);
            while(viewrs.next()){
                PID = viewrs.getString("Project_ID");
                name = viewrs.getString("Project_name");
                LandID = viewrs.getString("Land_ID");
                custID = viewrs.getString("Customer_ID");
                type = viewrs.getString("Type");
                Desc = viewrs.getString("Description");
                Floors = viewrs.getInt("Number_of_floors");
                SFloors = Integer.toString(Floors);
                Area = viewrs.getInt("Project_area");
                SArea = Integer.toString(Area);
                //DepID = viewrs.getString("Department_ID");
                
                String ViewArr[] = {PID,name,LandID,custID,type,SArea,SFloors,Desc};
                DefaultTableModel a = (DefaultTableModel)this.jTable1.getModel();
                a.addRow(ViewArr);
            }
            viewrs.close();
                        
            //String qry = "select * from architecture_firm.\"Project\" inner join architecture_firm.\"Engineer_project\" on \"Project\".\"Project_ID\"=\"Engineer_project\".\"Project_ID\" where \"Engineer_ID\"='" + ID + "'";
            
            
            //String qry = "select * from architecture_firm.\"Project\" inner join architecture_firm.\"Full-time_Engineer\" on \"Project\".\"Department_ID\"=\"Full-time_Engineer\".\"Department_ID\" where \"ID\" = '" + ID + "' and \"Project\".\"Department_ID\"='" + EngDepID + "'";
            
            /*
            ResultSet rs = stmt.executeQuery(qry);
            while(rs.next()){
                PID = rs.getString("Project_ID");
                name = rs.getString("Project_name");
                LandID = rs.getString("Land_ID");
                type = rs.getString("Type");
                Desc = rs.getString("Description");
                Floors = rs.getInt("Number_of_floors");
                SFloors = Integer.toString(Floors);
                Area = rs.getInt("Project_area");
                SArea = Integer.toString(Area);                
                
                //EngDepID = rs.getString(19);
                
                String DataArr[] = {PID,name,LandID,type,SFloors,SArea,Desc};
                
                DefaultTableModel a = (DefaultTableModel)this.jTable5.getModel();
                a.addRow(DataArr);
            }
            rs.close(); */           
            
            
            con.commit();
            con.close();
            
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
           jPanel6.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        jPanel6.setVisible(false);
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        manageredit.setLocationRelativeTo(null);
        manageredit.setVisible(false);
        manageredit.setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        {
            jLabel9.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        }
        if(darkmode)
        {
            jLabel9.setBackground(new java.awt.Color(81,81,81));
        }
        jLabel9.setForeground(Color.white);
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        // TODO add your handling code here:
        jLabel9.setBackground(Color.white);
        jLabel9.setForeground(Color.black);
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:

        String[] options = { "sign out", "cancel"};
        var exit = JOptionPane.showOptionDialog(null, "are you sure you want to sign out?", "Sign out",
            0, 1, null, options, options[0]);
        if (exit == 0) {
            loginpage2 a = new loginpage2();
            a.setVisible(true);
            dispose();
            managerprofile.dispose();
            manageredit.dispose();
        }
        if (exit == 1) {
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
        // TODO add your handling code here:        if(!darkmode)
        {
            jLabel10.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        }
        if(darkmode)
        {
            jLabel10.setBackground(new java.awt.Color(81,81,81));
        }
        jLabel10.setForeground(Color.white);
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseExited
        // TODO add your handling code here:
        jLabel10.setBackground(Color.white);
        jLabel10.setForeground(Color.black);
    }//GEN-LAST:event_jLabel10MouseExited

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        darkmode=!darkmode;
        jComboBox1.setVisible(!darkmode);
        //darkmode code
        if(darkmode)
        {
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lightmode.png"))); // NOI18N
        jLabel12.setText("light mode"); 
        jPanel2.setBackground(Color.black);
        jPanel3.setBackground(Color.black);
          if(jPanel10.isVisible())
        {
            jPanel3.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel4.setBackground(Color.black);
        if(jPanel8.isVisible())
        {
            jPanel4.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel5.setBackground(Color.black);
          if(jPanel9.isVisible())
        {
            jPanel5.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel11.setBackground(Color.black);
        if(jPanel12.isVisible())
        {
            jPanel11.setBackground(new java.awt.Color(51,51,51));
        }
        jLabel12.setBackground(new java.awt.Color(81,81,81));
        jPanel6.setBackground(new java.awt.Color(51,51,51));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,51,51)));
        jLabel12.setForeground(Color.white);
        
        
        jLabel35.setForeground(Color.white);
        jLabel36.setForeground(Color.white);
        jLabel37.setForeground(Color.white);
        jLabel38.setForeground(Color.white);
        jLabel39.setForeground(Color.white);
        jLabel40.setForeground(Color.white);
        jLabel41.setForeground(Color.white);
        jLabel42.setForeground(Color.white);
        jLabel43.setForeground(Color.white);
        jLabel44.setForeground(Color.white);
        jLabel25.setForeground(Color.white);
        jLabel26.setForeground(Color.white);
        
        jPanel10.setBackground(new java.awt.Color(61,61,61));
        jPanel12.setBackground(new java.awt.Color(61,61,61));
        jPanel14.setBackground(new java.awt.Color(61,61,61));
        jPanel15.setBackground(new java.awt.Color(61,61,61));
        jPanel16.setBackground(new java.awt.Color(61,61,61));
        jPanel17.setBackground(new java.awt.Color(61,61,61));
        
        jLabel16.setForeground(Color.white);
        jLabel17.setForeground(Color.white);
        jLabel18.setForeground(Color.white);
        jLabel22.setForeground(Color.white);
        jLabel23.setForeground(Color.white);
        jLabel24.setForeground(Color.white);
        
        jLabel30.setForeground(Color.white);
        jLabel31.setForeground(Color.white);
        jLabel32.setForeground(Color.white);
        jLabel33.setForeground(Color.white);
        jLabel34.setForeground(Color.white);
        jLabel45.setForeground(Color.white);
        
        jButton5.setBackground(Color.black);
        jButton1.setBackground(Color.black);
        jButton2.setBackground(Color.black);
        jButton3.setBackground(Color.black);
        jButton4.setBackground(Color.black);        
        jButton10.setBackground(Color.black);
        jButton6.setBackground(Color.black);
        jButton8.setBackground(Color.black);
        jButton9.setBackground(Color.black);
        jButton15.setBackground(Color.black);
        jButton16.setBackground(Color.black);
        jButton17.setBackground(Color.black);
        jButton18.setBackground(Color.black);
        jButton7.setBackground(Color.black);
        
        TH = jTable4.getTableHeader();
        TH.setBackground(Color.black);
        
        TH = jTable1.getTableHeader();
        TH.setBackground(Color.black);
        
        TH = jTable2.getTableHeader();
        TH.setBackground(Color.black);
        
        TH = jTable5.getTableHeader();
        TH.setBackground(Color.black);
        
        TH = jTable3.getTableHeader();
        TH.setBackground(Color.black);
        
    //    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1)dark.png"))); // NOI18N
  //     jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1)dark.png"))); // NOI18N
        
        }
        //lightmode code
         if(!darkmode)
        {
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        jLabel12.setText("Dark mode");
        jPanel2.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel10.isVisible())
        {
            jPanel3.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel8.isVisible())
        {
            jPanel4.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel9.isVisible())
        {
            jPanel5.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel12.isVisible())
        {
            jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel6.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        jLabel12.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        jLabel12.setForeground(Color.white);
        jButton10.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton8.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton9.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton17.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton16.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton15.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton18.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton1.setBackground(darkcolor);
        jButton2.setBackground(darkcolor);
        jButton3.setBackground(darkcolor);
        jButton4.setBackground(darkcolor);
        jButton7.setBackground(darkcolor);
        
        
        jLabel35.setForeground(darkcolor);
        jLabel36.setForeground(darkcolor);
        jLabel37.setForeground(darkcolor);
        jLabel38.setForeground(darkcolor);
        jLabel39.setForeground(darkcolor);
        jLabel25.setForeground(darkcolor);
        jLabel26.setForeground(darkcolor);
        jLabel40.setForeground(Color.black);
        jLabel41.setForeground(Color.black);
        jLabel42.setForeground(Color.black);
        jLabel44.setForeground(darkcolor);
        jLabel43.setForeground(darkcolor);
        jLabel45.setForeground(darkcolor);
        
        jPanel10.setBackground(new java.awt.Color(242,242,242));
        jPanel12.setBackground(new java.awt.Color(242,242,242));
        jPanel14.setBackground(new java.awt.Color(242,242,242));
        jPanel15.setBackground(new java.awt.Color(242,242,242));
        jPanel16.setBackground(new java.awt.Color(242,242,242));
        jPanel17.setBackground(new java.awt.Color(242,242,242));
        
        jLabel16.setForeground(darkcolor);
        jLabel17.setForeground(darkcolor);
        jLabel18.setForeground(darkcolor);
        jLabel22.setForeground(darkcolor);
        jLabel23.setForeground(darkcolor);
        jLabel24.setForeground(darkcolor);
        
        jLabel30.setForeground(darkcolor);
        jLabel31.setForeground(darkcolor);
        jLabel32.setForeground(darkcolor);
        jLabel33.setForeground(darkcolor);
        jLabel34.setForeground(darkcolor);
        
        TH = jTable4.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable1.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable2.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable5.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable3.getTableHeader();
        TH.setBackground(darkcolor);
        
   //     jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N
   //     jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N  
        }
          if(jPanel6.isVisible())
       { jPanel6.setVisible(false);
               jPanel6.setVisible(true);

               }
          
          manageredit.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(manageredit.isVisible())
       { manageredit.setVisible(false);
               manageredit.setVisible(true);
               }
         
         managerprofile.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(managerprofile.isVisible())
       { managerprofile.setVisible(false);
               managerprofile.setVisible(true);
               }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        {
            jLabel12.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        }
        if(darkmode)
        {
            jLabel12.setBackground(new java.awt.Color(81,81,81));
        }
        jLabel12.setForeground(Color.white);
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        // TODO add your handling code here:
        jLabel12.setBackground(Color.white);
        jLabel12.setForeground(Color.black);
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
String[] options = { "exit","sign out" ,"cancel"};
    var exit = JOptionPane.showOptionDialog(null, "are you sure you want to exit?", "exit", 
                                                      0, 0, null, options, options[0]);
    if (exit == 0) {
     dispose();
     managerprofile.dispose();
     manageredit.dispose();
    }
    if (exit == 1) { 
        loginpage2 a = new loginpage2();
        a.setVisible(true);
        dispose();
        managerprofile.dispose();
        manageredit.dispose();
    }
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        {
            jLabel14.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        }
        if(darkmode)
        {
            jLabel14.setBackground(new java.awt.Color(81,81,81));
        }
        jLabel14.setForeground(Color.white);
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        // TODO add your handling code here:
                jLabel14.setBackground(Color.white);
        jLabel14.setForeground(Color.black);
    }//GEN-LAST:event_jLabel14MouseExited

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        // TODO add your handling code here:
         if(!darkmode)
        jPanel4.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel4.setBackground(new java.awt.Color(51,51,51));
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
        // TODO add your handling code here:
        if(!jPanel8.isVisible()){
        if(!darkmode)
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel4.setBackground(Color.black);}
    }//GEN-LAST:event_jPanel4MouseExited

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        // TODO add your handling code here:
         if(!darkmode)
        jPanel5.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel5.setBackground(new java.awt.Color(51,51,51));
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseExited
        // TODO add your handling code here:
        if(!jPanel9.isVisible()){
        if(!darkmode)
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel5.setBackground(Color.black);  }
    }//GEN-LAST:event_jPanel5MouseExited

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        jPanel3.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel3.setBackground(new java.awt.Color(51,51,51));
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        // TODO add your handling code here:
        if(!jPanel10.isVisible()){
        if(!darkmode)
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel3.setBackground(Color.black);} 
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        if(jPanel8.isVisible())
        jPanel8.setVisible(false);
        else
        jPanel8.setVisible(true);
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel12.setVisible(false);
        if(!darkmode){
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        }
        if(darkmode){
        jPanel5.setBackground(Color.black);
        jPanel3.setBackground(Color.black);     
        jPanel11.setBackground(Color.black);
        }
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        if(jPanel9.isVisible())
        jPanel9.setVisible(false);
        else
        jPanel9.setVisible(true);
       
        jPanel8.setVisible(false);
        jPanel10.setVisible(false);
        jPanel12.setVisible(false);
        if(!darkmode){
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
}
        if(darkmode)
        {
        jPanel4.setBackground(Color.black);
        jPanel3.setBackground(Color.black);
        jPanel11.setBackground(Color.black);

        }
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
         if(jPanel10.isVisible())
        jPanel10.setVisible(false);
        else
        jPanel10.setVisible(true);
        jPanel9.setVisible(false);
        jPanel8.setVisible(false);
        jPanel12.setVisible(false);
        if(!darkmode){
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        }
           if(darkmode)
        {
        jPanel4.setBackground(Color.black);
        jPanel5.setBackground(Color.black);
        jPanel11.setBackground(Color.black);
        }
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
        if(jPanel12.isVisible())
        jPanel12.setVisible(false);
        else
        jPanel12.setVisible(true);
       
        jPanel8.setVisible(false);
        jPanel10.setVisible(false);
        jPanel9.setVisible(false);
        if(!darkmode){
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel5.setBackground(darkcolor);}//new java.awt.Color(0, 153, 255));}
        if(darkmode)
        {
        jPanel4.setBackground(Color.black);
        jPanel3.setBackground(Color.black);
        jPanel5.setBackground(Color.black);
        }
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel11.setBackground(new java.awt.Color(51,51,51));
    }//GEN-LAST:event_jPanel11MouseEntered

    private void jPanel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseExited
        // TODO add your handling code here:
        if(!jPanel12.isVisible()){
        if(!darkmode)
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel11.setBackground(Color.black);} 
    }//GEN-LAST:event_jPanel11MouseExited

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        // TODO add your handling code here:
        int i;
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
     String Fname = "";
        String Lname = "";
        int Byear = 0;
        String workHours = "";
        String Salary = "";
       // put the values in here
       try {
            // TODO add your handling code here:
            //Saving customer properties in Database:
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "select * from architecture_firm.\"Full-time_Engineer\" where \"ID\" = '" + ID + "'";
            String dateqry = "select  DATE_PART('year',\"Birthdate\") from architecture_firm.\"Full-time_Engineer\" where \"ID\"='" + ID +"'";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            
            while(rs.next()){
               Fname = rs.getString("First_name");
               Lname = rs.getString("Last_name");
               workHours = rs.getString("Work_hours");
               Salary = rs.getString("Salary");
            }
            rs.close();
            
            ResultSet D_rs = stmt.executeQuery(dateqry);
            while(D_rs.next()){
                Byear = Integer.parseInt(D_rs.getString("date_part"));
            }
            D_rs.close();
            
            con.commit();
            con.close();
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        // put the values in here
        String firstname=Fname;
        String lastname=Lname;
        int age=2024-Byear;
        String work_hours=workHours;
        int salary=Integer.parseInt(Salary);
        
        
        //
        managerprofile.dispose();
        managerprofile= new viewengprofile1(darkmode,username,ID,firstname,lastname,age,work_hours,salary,darkcolor,lightcolor,lightercolor);
        managerprofile.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // put the values in here
        String Fname = "";
        String Lname = "";
        int Byear = 0;
        String workHours = "";
        String Salary = "";
       // put the values in here
       try {
            // TODO add your handling code here:
            //Saving customer properties in Database:
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "select * from architecture_firm.\"Full-time_Engineer\" where \"ID\" = '" + ID + "'";
            String dateqry = "select  DATE_PART('year',\"Birthdate\") from architecture_firm.\"Full-time_Engineer\" where \"ID\"='" + ID +"'";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            
            while(rs.next()){
               Fname = rs.getString("First_name");
               Lname = rs.getString("Last_name");
               workHours = rs.getString("Work_hours");
               Salary = rs.getString("Salary");
            }
            rs.close();
            
            ResultSet D_rs = stmt.executeQuery(dateqry);
            while(D_rs.next()){
                Byear = Integer.parseInt(D_rs.getString("date_part"));
            }
            D_rs.close();
            
            con.commit();
            con.close();
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        // make sute to add values to jLabel6MouseClicked(java.awt.event.MouseEvent evt)
        
        String firstname=Fname;
        String lastname=Lname;
        int age=2024-Byear;
        String work_hours=workHours;
        int salary=Integer.parseInt(Salary);
        
        
        //
         managerprofile.dispose();
        managerprofile= new viewengprofile1(darkmode,username,ID,firstname,lastname,age,work_hours,salary,darkcolor,lightcolor,lightercolor);
        managerprofile.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       
 String colorcase =jComboBox1.getSelectedItem().toString();
        
          if(colorcase.equals( "Blue theme (default)")) {
                 darkcolor =new java.awt.Color(0,153,255);
                 lightcolor = new java.awt.Color(56, 175, 255);
                 lightercolor =new java.awt.Color(66,179,255);}      
          else if(colorcase.equals( "Red theme")) {
                 darkcolor =new java.awt.Color(225,38,93);
                 lightcolor = new java.awt.Color(229,71,117);
                 lightercolor =new java.awt.Color(231,85,127);}
          else if(colorcase.equals( "Green theme")) {
                 darkcolor =new java.awt.Color(29,169,29);
                 lightcolor = new java.awt.Color(34,200,34);
                 lightercolor =new java.awt.Color(35,209,35);}
          else if(colorcase.equals( "Orange theme")) {
                 darkcolor =new java.awt.Color(255,135,39);
                 lightcolor = new java.awt.Color(255,152,71);
                 lightercolor =new java.awt.Color(255,160,86);} 
          else if(colorcase.equals( "Purple theme")) {
                 darkcolor =new java.awt.Color(147,77,178);
                 lightcolor = new java.awt.Color(160,106,184);
                 lightercolor =new java.awt.Color(169,120,190);} 
          
// TODO add your handling code here:
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        jLabel12.setText("Dark mode");
        jPanel2.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel10.isVisible())
        {
            jPanel3.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel8.isVisible())
        {
            jPanel4.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel9.isVisible())
        {
            jPanel5.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        if(jPanel12.isVisible())
        {
            jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel6.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
    //  jLabel12.setBackground(lightercolor);//new java.awt.Color(66,179,255));
     //   jLabel12.setForeground(Color.white);
        jButton10.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton9.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton18.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton1.setBackground(darkcolor);
        jButton2.setBackground(darkcolor);
        jButton3.setBackground(darkcolor);
        jButton4.setBackground(darkcolor);
        jButton15.setBackground(darkcolor);
        jButton16.setBackground(darkcolor);
        jButton17.setBackground(darkcolor);
        jButton7.setBackground(darkcolor);
        jButton8.setBackground(darkcolor);
        
        jLabel16.setForeground(darkcolor);
        jLabel17.setForeground(darkcolor);
        jLabel18.setForeground(darkcolor);
        jLabel22.setForeground(darkcolor);
        jLabel23.setForeground(darkcolor);
        jLabel24.setForeground(darkcolor);
        jLabel25.setForeground(darkcolor);
        jLabel26.setForeground(darkcolor);
        jLabel45.setForeground(darkcolor);
        
        jLabel30.setForeground(darkcolor);
        jLabel31.setForeground(darkcolor);
        jLabel32.setForeground(darkcolor);
        jLabel33.setForeground(darkcolor);
        jLabel34.setForeground(darkcolor);
        jLabel35.setForeground(darkcolor);
        jLabel36.setForeground(darkcolor);
        jLabel37.setForeground(darkcolor);
        jLabel38.setForeground(darkcolor);
        jLabel43.setForeground(darkcolor);
        jLabel44.setForeground(darkcolor);
        
        
        
        
        jLabel39.setForeground(Color.black);
        jLabel40.setForeground(Color.black);
        jLabel41.setForeground(Color.black);
        jLabel42.setForeground(Color.black);
        
        TH = jTable4.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable1.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable2.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable3.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable5.getTableHeader();
        TH.setBackground(darkcolor);
        
   //     jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N
   //     jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N  
        
          if(jPanel6.isVisible())
       { jPanel6.setVisible(false);
               jPanel6.setVisible(true);

               }
          
          manageredit.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(manageredit.isVisible())
       { manageredit.setVisible(false);
               manageredit.setVisible(true);
               }
         
         managerprofile.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(managerprofile.isVisible())
       { managerprofile.setVisible(false);
               managerprofile.setVisible(true);
               }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        jPanel12.setVisible(false);
        if(!darkmode)
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel11.setBackground(Color.black);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseExited
        // TODO add your handling code here:
        if(darkmode)
        jButton10.setBackground(Color.black);
        if(!darkmode)
        jButton10.setBackground(darkcolor);//new java.awt.Color(0,153,255));
    }//GEN-LAST:event_jButton9MouseExited

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
        // TODO add your handling code here:
        if(darkmode)
        jButton10.setBackground(new java.awt.Color(81,81,81));
        if(!darkmode)
        jButton10.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseClicked

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseExited

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        jPanel10.setVisible(false);
        if(!darkmode)
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel3.setBackground(Color.black);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15MouseClicked

    private void jButton15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseEntered
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton15MouseEntered

    private void jButton15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseExited
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton15MouseExited

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        String StuIDtoDelete = JOptionPane.showInputDialog(null,"Please enter student ID here");
        try {
            // TODO add your handling code here:
            //Delete selected Land in database:
            int notFound = 0;
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "delete from architecture_firm.\"Student_trainee\" where \"ID\" = '" + StuIDtoDelete + "' and \"Supervisor_ID\"='" + ID + "'";
            String StuIDqry = "select \"ID\" from architecture_firm.\"Student_trainee\" where \"Supervisor_ID\" = '" + ID + "'";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(StuIDqry);
            
            while(rs.next()){
                String StudentID = rs.getString("ID");
                if(StuIDtoDelete.equals(StudentID)){
                    notFound = 0;
                    //rowCount++;
                    break;
                }
                else{
                    notFound = 1;
                }
            }
            rs.close();
            
            if(notFound == 1){
                JOptionPane.showMessageDialog(null, "Student ID " + StuIDtoDelete + " is not present!");
            }
            else{
                stmt.executeUpdate(qry);
                DefaultTableModel tbm = (DefaultTableModel)this.jTable4.getModel();
                tbm.removeRow(getRowIndex(jTable4, 0, StuIDtoDelete));
                
                for(int i=0;i<jTable3.getRowCount();i++){
                    if(StuIDtoDelete.equals(jTable3.getValueAt(i,0))){
                        tbm = (DefaultTableModel) this.jTable3.getModel();
                        tbm.removeRow(i);
                    }
                }
                
                JOptionPane.showMessageDialog(null,"Student with ID " + StuIDtoDelete + " is deleted successfully!");
            }
            
            
            con.commit();
            con.close();
            
            jLabel38.setText("name");
            jTextField10.setText("enter work shift here");
            jTextField9.setText("enter required hours here");
            
            
            
            //dispose();
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }        
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16MouseClicked

    private void jButton16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16MouseEntered

    private void jButton16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16MouseExited

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
            Boolean darkm1=darkmode;
            Color darkc1 =darkcolor;
            Color lightc1= lightcolor;
            Color lighterc1= lightercolor;
            
            if(jTable4.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null,"Please select a student first!","Student selection error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String confirm[] = {"Submit changes","Close"};
        var exit = JOptionPane.showOptionDialog(null, "Save changes?", "Save Student changes",0, 1, null, confirm, confirm[0]);
        if(exit == 0){
            //Save changes of project here:
            try {
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry0 = "insert into architecture_firm.\"Student_Project\" (\"Student_ID\",\"Project_ID\") values('" + jTable4.getValueAt(jTable4.getSelectedRow(),0).toString() + "','" + jComboBox2.getSelectedItem().toString() + "')";
            String qry = "update architecture_firm.\"Student_trainee\" set \"Work_hours\"='" + this.jTextField10.getText() + "',\"Required_hours\"='" + Integer.parseInt(this.jTextField9.getText()) + "' where \"ID\"='" + this.jTable4.getValueAt(this.jTable4.getSelectedRow(), 0).toString() + "' and \"Supervisor_ID\"='" + ID + "'";
            
            Statement stmt = con.createStatement();
            stmt.executeUpdate(qry);
            stmt.executeUpdate(qry0);
            
            con.commit();
            con.close();
            
            JOptionPane.showMessageDialog(null,"Changes are saved");
            
            dispose();
            
            managerview1 a = new managerview1(username,ID);
            a.setVisible(true);
            
            a.jPanel10.setVisible(true); 
            
            a.changetheme(darkm1, darkc1, lightc1, lighterc1);
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        }
            }
        
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseClicked
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton17MouseClicked

    private void jButton17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseEntered
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton17MouseEntered

    private void jButton17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseExited
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton17MouseExited

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        addStuforMng a = new addStuforMng(username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        a.setVisible(true);
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        // TODO add your handling code here:
        if(darkmode)
        jButton5.setBackground(new java.awt.Color(81,81,81));
        if(!darkmode)
        jButton5.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
        if(darkmode)
        jButton5.setBackground(Color.black);
        if(!darkmode)
        jButton5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jPanel9.setVisible(false);
        if(!darkmode)
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel5.setBackground(Color.black);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        // TODO add your handling code here:
        if(darkmode)
        jButton6.setBackground(new java.awt.Color(81,81,81));
        if(!darkmode)
        jButton6.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        // TODO add your handling code here:
        if(darkmode)
        jButton6.setBackground(Color.black);
        if(!darkmode)
        jButton6.setBackground(darkcolor);//new java.awt.Color(0,153,255));
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jPanel8.setVisible(false);
        if(!darkmode)
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel4.setBackground(Color.black);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseEntered
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton18MouseEntered

    private void jButton18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseExited
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton18MouseExited

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        if(jTable2.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Please select a project first!","Project selection error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            Boolean darkm1=darkmode;
            Color darkc1 =darkcolor;
            Color lightc1= lightcolor;
            Color lighterc1= lightercolor;
            
        String confirm[] = {"Submit changes","Close"};
        var exit = JOptionPane.showOptionDialog(null, "Save changes?", "Save project properities changes",0, 1, null, confirm, confirm[0]);
        if(exit == 0){
            //Save changes of project here:
            try {
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "update architecture_firm.\"Project\" set \"Project_name\"='" + this.jTextField1.getText() + "',\"Type\"='" + this.jTextField2.getText() + "',\"Project_area\"='" + Integer.parseInt(this.jTextField3.getText()) +"',\"Number_of_floors\"='" + Integer.parseInt(this.jTextField4.getText()) +"',\"Description\"='" + this.jTextArea1.getText() +"' where \"Project_ID\"='" + this.jTable2.getValueAt(this.jTable2.getSelectedRow(), 0).toString() + "'";
            
            Statement stmt = con.createStatement();
            stmt.executeUpdate(qry);
            
            con.commit();
            con.close();
            
            JOptionPane.showMessageDialog(null,"Changes are saved");
            
            dispose();
            
            managerview1 a = new managerview1(username,ID);
            a.setVisible(true);
            
            a.jPanel8.setVisible(true);
            
            a.changetheme(darkm1, darkc1, lightc1, lighterc1);
            
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        }
        }
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }

    }//GEN-LAST:event_jButton18ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:

        if ("enter project name here".equals(jTextField1.getText()))
        {
            jTextField1.setText("");
            jTextField1.setForeground(Color.black);}

        if ("".equals(jTextField2.getText()))
        {
            jTextField2.setText("enter project type here");
            jTextField2.setForeground(new java.awt.Color(153, 153, 153));}

        if ("".equals(jTextField3.getText()))
        {
            jTextField3.setText("enter project area here");
            jTextField3.setForeground(new java.awt.Color(153, 153, 153));}

        if ("".equals(jTextArea1.getText()))
        {
            jTextArea1.setText("enter project description here");
            jTextArea1.setForeground(new java.awt.Color(153, 153, 153));}
        if ("".equals(jTextField4.getText()))
        {
            jTextField4.setText("enter project floors number here");
            jTextField4.setForeground(new java.awt.Color(153, 153, 153));}
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        // TODO add your handling code here:
        if ("enter project type here".equals(jTextField2.getText()))
        {
            jTextField2.setText("");
            jTextField2.setForeground(Color.black);}

        if ("".equals(jTextField1.getText()))
        {
            jTextField1.setText("enter project name here");
            jTextField1.setForeground(new java.awt.Color(153, 153, 153));}

        if ("".equals(jTextField3.getText()))
        {
            jTextField3.setText("enter project area here");
            jTextField3.setForeground(new java.awt.Color(153, 153, 153));}

        if ("".equals(jTextArea1.getText()))
        {
            jTextArea1.setText("enter project description here");
            jTextArea1.setForeground(new java.awt.Color(153, 153, 153));}
        if ("".equals(jTextField4.getText()))
        {
            jTextField4.setText("enter project floors number here");
            jTextField4.setForeground(new java.awt.Color(153, 153, 153));}
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked
        // TODO add your handling code here:
        if ("enter project area here".equals(jTextField3.getText()))
        {
            jTextField3.setText("");
            jTextField3.setForeground(Color.black);}

        if ("".equals(jTextField1.getText()))
        {
            jTextField1.setText("enter project name here");
            jTextField1.setForeground(new java.awt.Color(153, 153, 153));}

        if ("".equals(jTextField2.getText()))
        {
            jTextField2.setText("enter project type here");
            jTextField2.setForeground(new java.awt.Color(153, 153, 153));}

        if ("".equals(jTextArea1.getText()))
        {
            jTextArea1.setText("enter project description here");
            jTextArea1.setForeground(new java.awt.Color(153, 153, 153));}
        if ("".equals(jTextField4.getText()))
        {
            jTextField4.setText("enter project floors number here");
            jTextField4.setForeground(new java.awt.Color(153, 153, 153));}
    }//GEN-LAST:event_jTextField3MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
        // TODO add your handling code here:
        if ("enter project description here".equals(jTextArea1.getText()))
        {
            jTextArea1.setText("");
            jTextArea1.setForeground(Color.black);}

        if ("".equals(jTextField1.getText()))
        {
            jTextField1.setText("enter project name here");
            jTextField1.setForeground(new java.awt.Color(153, 153, 153));}

        if ("".equals(jTextField2.getText()))
        {
            jTextField2.setText("enter project type here");
            jTextField2.setForeground(new java.awt.Color(153, 153, 153));}

        if ("".equals(jTextField3.getText()))
        {
            jTextField3.setText("enter project area here");
            jTextField3.setForeground(new java.awt.Color(153, 153, 153));}
        if ("".equals(jTextField4.getText()))
        {
            jTextField4.setText("enter project floors number here");
            jTextField4.setForeground(new java.awt.Color(153, 153, 153));}
    }//GEN-LAST:event_jTextArea1MouseClicked

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        // TODO add your handling code here:
        if ("enter project floors number here".equals(jTextField4.getText()))
        {
            jTextField4.setText("");
            jTextField4.setForeground(Color.black);}

        if ("".equals(jTextField1.getText()))
        {
            jTextField1.setText("enter project name here");
            jTextField1.setForeground(new java.awt.Color(153, 153, 153));}

        if ("".equals(jTextField2.getText()))
        {
            jTextField2.setText("enter project type here");
            jTextField2.setForeground(new java.awt.Color(153, 153, 153));}

        if ("".equals(jTextField3.getText()))
        {
            jTextField3.setText("enter project area here");
            jTextField3.setForeground(new java.awt.Color(153, 153, 153));}
        if ("".equals(jTextArea1.getText()))
        {
            jTextArea1.setText("enter project description here");
            jTextArea1.setForeground(new java.awt.Color(153, 153, 153));}
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(jTable5.getRowCount() == 0){
            JOptionPane.showMessageDialog(null,"There are no engineers to update!","Engineer update error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(jTable5.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Please choose an engineer first!","Engineer selection error",JOptionPane.ERROR_MESSAGE);
        }
            else{
                String sure []={"Yes","No"};
        var exit = JOptionPane.showOptionDialog(null,"Are you sure you want to update the selected engineer?", "Engineer update", 0, 1, null, sure, sure[0]);
        if(exit == 0){
             try {
            
                DriverManager.registerDriver(new org.postgresql.Driver());
                String cInfo = "jdbc:postgresql://localhost:5432/postgres";
                Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
                con.setAutoCommit(false);
                
                String qry = "update architecture_firm.\"Full-time_Engineer\" set \"Work_hours\"='" + jTextField5.getText() + "', \"Salary\"='" + Integer.parseInt(jTextField6.getText()) + "' where \"ID\"='" + jTable5.getValueAt(jTable5.getSelectedRow(),0) + "'";

                Statement stmt = con.createStatement();
                
                
                stmt.executeUpdate(qry);

                con.commit();
                con.close();

                JOptionPane.showMessageDialog(null,"Changes are saved successfully!");
                
                Boolean darkm1=darkmode;
                Color darkc1 =darkcolor;
                Color lightc1= lightcolor;
                Color lighterc1= lightercolor;
                
                dispose();
                managerview1 a = new managerview1(username,ID);
                a.setVisible(true);
                
                a.jPanel12.setVisible(true);
                
                a.changetheme(darkm1, darkc1, lightc1, lighterc1);
                

                

            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, ex.toString());
            }      
        }          
            }
        }
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:        
        if(jTable3.getRowCount() == 0){
            JOptionPane.showMessageDialog(null,"There are no students to update!","Student update error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(jTable3.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Please choose a student first!","Student selection error",JOptionPane.ERROR_MESSAGE);
        }
            else{
                String sure []={"Yes","No"};
        var exit = JOptionPane.showOptionDialog(null,"Are you sure you want to update the selected student?", "Student update", 0, 1, null, sure, sure[0]);
        if(exit == 0){
             try {
            
                DriverManager.registerDriver(new org.postgresql.Driver());
                String cInfo = "jdbc:postgresql://localhost:5432/postgres";
                Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
                con.setAutoCommit(false);
                
                String qry = "update architecture_firm.\"Student_trainee\" set \"Work_hours\"='" + jTextField7.getText() + "', \"Required_hours\"='" + Integer.parseInt(jTextField8.getText()) + "' where \"ID\"='" + jTable3.getValueAt(jTable3.getSelectedRow(),0) + "'";

                Statement stmt = con.createStatement();
                
                
                stmt.executeUpdate(qry);

                con.commit();
                con.close();

                JOptionPane.showMessageDialog(null,"Changes are saved successfully!");
                
                Boolean darkm1=darkmode;
                Color darkc1 =darkcolor;
                Color lightc1= lightcolor;
                Color lighterc1= lightercolor;
                
                dispose();
                managerview1 a = new managerview1(username,ID);
                a.setVisible(true);
                
                a.jPanel12.setVisible(true);
                
                a.changetheme(darkm1, darkc1, lightc1, lighterc1);
                

                

            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, ex.toString());
            }      
        }          
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if(jTable1.getRowCount() == 0){
            JOptionPane.showMessageDialog(null,"There are no projects in your department currently!","Project selcetion error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            int exist = 0;
        
        if(this.jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Please select a project first!","Project selcetion error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            for(int i=0;i<jTable2.getRowCount();i++){
                if(this.jTable2.getValueAt(i,0).toString().equals(this.jTable1.getValueAt(this.jTable1.getSelectedRow(),0).toString())){
                    exist = 1;
                    break;
                }
            }
            
            if(exist == 1){
                JOptionPane.showMessageDialog(null,"This project is already on management!","Project selcetion error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String confirm [] ={"Yes","No"};
                var exit = JOptionPane.showOptionDialog(null, "Choose project?", "exit", 
                                                      0, 1, null, confirm, confirm[0]);
                
                if(exit == 0){
                    
                    //Assigning Engineer to the project:
                    //Assigning Engineer to the project:
                    try {            
                        DriverManager.registerDriver(new org.postgresql.Driver());
                        String cInfo = "jdbc:postgresql://localhost:5432/postgres";
                        Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
                        con.setAutoCommit(false);

                        Statement stmt = con.createStatement();
                        
                        String PID = this.jTable1.getValueAt(this.jTable1.getSelectedRow(),0).toString();
                        
                        
                        String qry = "insert into architecture_firm.\"Engineer_project\"(\"Engineer_ID\",\"Project_ID\") values('" + ID + "','" + PID + "')";
                        
                        stmt.executeUpdate(qry);



                        con.commit();
                        con.close();            

                    } catch (Exception ex) {
                       JOptionPane.showMessageDialog(null, ex.toString());
                    }
                    
                    
                    
                    
                    DefaultTableModel a = (DefaultTableModel)this.jTable2.getModel();
                    if(this.jTable1.getValueAt(this.jTable1.getSelectedRow(),7) == null){
                        this.jTable1.setValueAt("", this.jTable1.getSelectedRow(),7);
                    }
                    String arr[] = {jTable1.getValueAt(jTable1.getSelectedRow(),0).toString(),jTable1.getValueAt(jTable1.getSelectedRow(),1).toString(),jTable1.getValueAt(jTable1.getSelectedRow(),4).toString(),jTable1.getValueAt(jTable1.getSelectedRow(),5).toString(),jTable1.getValueAt(jTable1.getSelectedRow(),6).toString(),jTable1.getValueAt(jTable1.getSelectedRow(),7).toString()};
                    a.addRow(arr);
                    JOptionPane.showMessageDialog(null,"Project is now on management");
                }
            }
            
            
        }
        }
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton7MouseExited

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        this.jTextField1.setText(this.jTable2.getValueAt(this.jTable2.getSelectedRow(),1).toString());
        this.jTextField2.setText(this.jTable2.getValueAt(this.jTable2.getSelectedRow(),2).toString());
        this.jTextField3.setText(this.jTable2.getValueAt(this.jTable2.getSelectedRow(),3).toString());
        this.jTextField4.setText(this.jTable2.getValueAt(this.jTable2.getSelectedRow(),4).toString());
        this.jTextArea1.setText(this.jTable2.getValueAt(this.jTable2.getSelectedRow(),5).toString());
                
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9MouseClicked

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10MouseClicked

    private void jTextField10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10MouseEntered

    private void jTextField10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10MouseExited

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        this.jLabel38.setText(this.jTable4.getValueAt(this.jTable4.getSelectedRow(),1).toString() + " " + this.jTable4.getValueAt(this.jTable4.getSelectedRow(),2).toString());
        this.jTextField10.setText(this.jTable4.getValueAt(this.jTable4.getSelectedRow(),6).toString());
        this.jTextField9.setText(this.jTable4.getValueAt(this.jTable4.getSelectedRow(),8).toString());
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTable4MouseClicked
        
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if(jTable5.getSelectedRow() == -1 && jTable3.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"You must choose a student or an engineer first!","Account management error",JOptionPane.ERROR_MESSAGE);
        }
        else if(jTable5.getSelectedRow() != -1){
            //editAccount:
            //boolean engtable = false;
            
            editAccount a = new editAccount(darkmode,username,ID,this,darkcolor,lightcolor,lightercolor,true);
            a.setVisible(true);
            a.changetheme(darkmode, darkcolor, lightcolor, lightcolor);
                        
        }
        else if(jTable3.getSelectedRow() != -1){
            editAccount a = new editAccount(darkmode,username,ID,this,darkcolor,lightcolor,lightercolor,false);
            a.setVisible(true);
            a.changetheme(darkmode, darkcolor, lightcolor, lightcolor);
        }
        
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton8ActionPerformed
    public void showpanel12(){
        jPanel12.setVisible(true);
    }
    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        engtable = true;
        jTable3.clearSelection();
        
        jTextField7.setText("enter work shift here");
        jTextField7.setForeground(new java.awt.Color(153,153,153));
        
        jTextField8.setText("enter required hours here");
        jTextField8.setForeground(new java.awt.Color(153,153,153));
        
        jTextField5.setText(jTable5.getValueAt(jTable5.getSelectedRow(),3).toString());
        jTextField5.setForeground(Color.black);
        
        jTextField6.setText(jTable5.getValueAt(jTable5.getSelectedRow(),5).toString());
        jTextField6.setForeground(Color.black);
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTable5MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked
    
    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        engtable = false;
        jTable5.clearSelection();
        
        jTextField5.setText("enter work shift here");
        jTextField5.setForeground(new java.awt.Color(153,153,153));
        
        jTextField6.setText("enter salary here");
        jTextField6.setForeground(new java.awt.Color(153,153,153));
        
        jTextField7.setText(jTable3.getValueAt(jTable3.getSelectedRow(),3).toString());
        jTextField7.setForeground(Color.black);
        
        jTextField8.setText(jTable3.getValueAt(jTable3.getSelectedRow(),5).toString());
        jTextField8.setForeground(Color.black);
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:        
        if(jTable3.getRowCount() == 0){
            JOptionPane.showMessageDialog(null,"There are no students to delete!","Student deletion error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(jTable3.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Please choose a student first!","Student selection error",JOptionPane.ERROR_MESSAGE);
        }
            else{
                String sure []={"Yes","No"};
        var exit = JOptionPane.showOptionDialog(null,"Are you sure you want to delete the selected student?", "Student deletion", 0, 1, null, sure, sure[0]);
        if(exit == 0){
             try {
            
                DriverManager.registerDriver(new org.postgresql.Driver());
                String cInfo = "jdbc:postgresql://localhost:5432/postgres";
                Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
                con.setAutoCommit(false);
                
                String StuIDtoDelete = jTable3.getValueAt(this.jTable3.getSelectedRow(),0).toString();
                
                String qry0 = "delete from architecture_firm.\"Student_Project\" where \"Student_ID\"='" + this.jTable3.getValueAt(this.jTable3.getSelectedRow(),0).toString() + "'";
                String qry = "delete from architecture_firm.\"Student_trainee\" where \"ID\"='" + this.jTable3.getValueAt(this.jTable3.getSelectedRow(),0).toString() + "'";

                Statement stmt = con.createStatement();
                
                stmt.executeUpdate(qry0);
                stmt.executeUpdate(qry);

                con.commit();
                con.close();
                
                DefaultTableModel a = (DefaultTableModel) this.jTable3.getModel();
                a.removeRow(this.jTable3.getSelectedRow());
                
                
                for(int i=0;i<jTable4.getRowCount();i++){
                    if(StuIDtoDelete.equals(jTable4.getValueAt(i,0))){
                        a = (DefaultTableModel) this.jTable4.getModel();
                        a.removeRow(i);
                    }
                }

                JOptionPane.showMessageDialog(null,"Student deleted successfully!");

                

            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, ex.toString());
            }      
        }          
            }
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(jTable5.getRowCount() == 0){
            JOptionPane.showMessageDialog(null,"There are no engineers to delete!","Engineer deletion error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(jTable5.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Please choose an engineer first!","Engineer selection error",JOptionPane.ERROR_MESSAGE);
        }
            else{
                String sure []={"Yes","No"};
        var exit = JOptionPane.showOptionDialog(null,"Are you sure you want to delete the selected engineer?", "engineer deletion", 0, 1, null, sure, sure[0]);
        if(exit == 0){
             try {
            
                DriverManager.registerDriver(new org.postgresql.Driver());
                String cInfo = "jdbc:postgresql://localhost:5432/postgres";
                Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
                con.setAutoCommit(false);
                
                String qry0 = "delete from architecture_firm.\"Engineer_project\" where \"Engineer_ID\"='" + this.jTable5.getValueAt(this.jTable5.getSelectedRow(),0).toString() + "'";;
                String qry = "delete from architecture_firm.\"Full-time_Engineer\" where \"ID\"='" + this.jTable5.getValueAt(this.jTable5.getSelectedRow(),0).toString() + "'";

                Statement stmt = con.createStatement();
                
                stmt.executeUpdate(qry0);
                stmt.executeUpdate(qry);

                con.commit();
                con.close();
                
                DefaultTableModel a = (DefaultTableModel) this.jTable5.getModel();
                a.removeRow(this.jTable5.getSelectedRow());

                JOptionPane.showMessageDialog(null,"Engineer deleted successfully!");

                

            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, ex.toString());
            }      
        }          
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseClicked
        // TODO add your handling code here:
        if("Start typing and it will do the search".equals(jTextField11.getText())){
            jTextField11.setText("");
            jTextField11.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField11MouseClicked

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        // TODO add your handling code here:
        DefaultTableModel tbm = (DefaultTableModel)jTable4.getModel();
        TableRowSorter<DefaultTableModel> s = new TableRowSorter<>(tbm);
        jTable4.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(jTextField11.getText()));
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jTextField12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseClicked
        // TODO add your handling code here:
        if("Start typing and it will do the search".equals(jTextField12.getText())){
            jTextField12.setText("");
            jTextField12.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField12MouseClicked

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        // TODO add your handling code here:
        DefaultTableModel tbm = (DefaultTableModel)jTable1.getModel();
        TableRowSorter<DefaultTableModel> s = new TableRowSorter<>(tbm);
        jTable1.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(jTextField12.getText()));
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:

        Integer num = Integer.parseInt(jTextField9.getText());
        num += 6;
        jTextField9.setText(num.toString());
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        // TODO add your handling code here:
        Integer num = Integer.parseInt(jTextField9.getText());
        num -= 6;
        if(num < 0){
            num=0;
        }
        jTextField9.setText(num.toString());
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseClicked

    private void jButton12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseEntered

    private void jButton12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseExited

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        String choice = jComboBox4.getSelectedItem().toString();
        int rc = jComboBox4.getSelectedIndex();
        String sure[] = {"Yes","No"};
        var exit = JOptionPane.showOptionDialog(null,"Are you sure you want to generate " + choice + " report?", "Report generation", 0, 1, null, sure, sure[0]);
        if(exit == 0){
            try{
                DriverManager.registerDriver(new org.postgresql.Driver());
                String cInfo = "jdbc:postgresql://localhost:5432/postgres";
                Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
                con.setAutoCommit(false);
                Statement stmt = con.createStatement();
                
                String DepID = "";
                String depid = "select \"Department_ID\" from architecture_firm.\"Full-time_Engineer\" where \"ID\"='" + ID + "'";
                ResultSet depidrs = stmt.executeQuery(depid);
                while(depidrs.next()){
                    DepID = depidrs.getString("Department_ID");
                }
                depidrs.close();

                String path = "";
                
                if(rc == 0){//project
                    path = "ProjRep.jrxml";
                    JasperDesign jd0 = JRXmlLoader.load(path);
                    String qry = "select * from architecture_firm.\"Project\" where \"Department_ID\"='" + DepID + "'";
                    
                    JRDesignQuery nqry0 = new JRDesignQuery();
                    
                    nqry0.setText(qry);
                    jd0.setQuery(nqry0);
                    
                    JasperReport jr0 = JasperCompileManager.compileReport(jd0);
                    JasperPrint jp = JasperFillManager.fillReport(jr0,null,con);

                    JasperViewer jv = new JasperViewer(jp,false);

                    jv.setLocationRelativeTo(null);
                    jv.setVisible(true);
                }
                else if(rc == 1){
                    path = "Customers.jrxml";
                    
                    JasperReport jr = JasperCompileManager.compileReport(path);
                    JasperPrint jp = JasperFillManager.fillReport(jr,null,con);

                    JasperViewer jv = new JasperViewer(jp,false);

                    jv.setLocationRelativeTo(null);
                    jv.setVisible(true);
                }
                else if(rc == 2){
                    path = "Lands.jrxml";
                    
                    JasperReport jr = JasperCompileManager.compileReport(path);
                    JasperPrint jp = JasperFillManager.fillReport(jr,null,con);

                    JasperViewer jv = new JasperViewer(jp,false);

                    jv.setLocationRelativeTo(null);
                    jv.setVisible(true);
                }
                else if(rc == 3){
                    path = "Engineers.jrxml";
                    JasperDesign jd3 = JRXmlLoader.load(path);
                    String qry = "select * from architecture_firm.\"Full-time_Engineer\" where \"Department_ID\"='" + DepID + "' and \"ID\" != '" + ID + "'";
                    
                    JRDesignQuery nqry3 = new JRDesignQuery();
                    
                    nqry3.setText(qry);
                    jd3.setQuery(nqry3);
                    
                    JasperReport jr3 = JasperCompileManager.compileReport(jd3);
                    JasperPrint jp = JasperFillManager.fillReport(jr3,null,con);
                
                    JasperViewer jv = new JasperViewer(jp,false);
                
                    jv.setLocationRelativeTo(null);
                    jv.setVisible(true);
                    
                }
                else if(rc == 4){
                    path = "Students.jrxml";

                    JasperDesign jd4 = JRXmlLoader.load(path);
                    
                    String qry = "select * from architecture_firm.\"Student_trainee\" where \"Department_ID\"='" + DepID + "'";
                    
                    JRDesignQuery nqry4 = new JRDesignQuery();
                    
                    nqry4.setText(qry);
                    jd4.setQuery(nqry4);
                    
                    JasperReport jr4 = JasperCompileManager.compileReport(jd4);
                    JasperPrint jp = JasperFillManager.fillReport(jr4,null,con);
                
                    JasperViewer jv = new JasperViewer(jp,false);
                
                    jv.setLocationRelativeTo(null);
                    jv.setVisible(true);
                }                
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e.toString());
            }
        }
        
    }//GEN-LAST:event_jButton12ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(engview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(engview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(engview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(engview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new managerview1(username,ID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    public static javax.swing.JTable jTable5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
