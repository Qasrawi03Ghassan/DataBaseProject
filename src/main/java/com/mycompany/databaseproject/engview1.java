/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.databaseproject;
import static com.mycompany.databaseproject.customerview1.ID;
import static com.mycompany.databaseproject.customerview1.TH;
import static com.mycompany.databaseproject.customerview1.darkcolor;
import static com.mycompany.databaseproject.customerview1.lightcolor;
import static com.mycompany.databaseproject.customerview1.lightercolor;
import static com.mycompany.databaseproject.studentview1.ID;
import static com.mycompany.databaseproject.viewengprofile1.darkcolor;
import static com.mycompany.databaseproject.viewengprofile1.lightcolor;
import static com.mycompany.databaseproject.viewengprofile1.lightercolor;
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
/**
 *
 * @author Msys
 */
public class engview1 extends javax.swing.JFrame {
public static boolean darkmode=false;
public static String username;
public static String ID;

static Color darkcolor =new java.awt.Color(0,153,255);
static Color lightcolor = new java.awt.Color(56, 175, 255);
static Color lightercolor =new java.awt.Color(66,179,255);

static editengaccount engedit;
static viewengprofile1 engprofile;
    /**
     * Creates new form engview1
     */
    public engview1(String u,String id) {     
        username=u;  
        ID=id;
        darkcolor =new java.awt.Color(0,153,255);
        lightcolor = new java.awt.Color(56, 175, 255);
        lightercolor =new java.awt.Color(66,179,255);
        engedit =new editengaccount(darkmode,username,ID,darkcolor,lightcolor,lightercolor,this);     
        engprofile= new viewengprofile1(darkmode,username,ID,"","",1,"",1,darkcolor,lightcolor,lightercolor);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Customizing tableHeaders:        
        TH = this.jTable6.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        TH = this.jTable5.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        TH = this.jTable3.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 14));
            
        //Aligning cells:
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<8;i++){
            this.jTable6.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        
        tcr.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<9;i++){
            this.jTable3.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        
        tcr.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0;i<7;i++){
            this.jTable5.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        
        getStudentsTable();
        getProjectTable();
        getManageProjectTable();
        
        for(int i=0;i<jTable6.getRowCount();i++){
            jComboBox2.addItem(jTable6.getValueAt(i,0).toString());
        }
        
        
    }
    
    public void showMngStus(){
        jPanel10.setVisible(true);
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
                    
                        String DataArr[] = {PID,name,LandID,type,SFloors,SArea,Desc};
                    DefaultTableModel a = (DefaultTableModel)this.jTable5.getModel();
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
                DepID = viewrs.getString("Department_ID");
                
                String ViewArr[] = {PID,name,LandID,custID,type,SFloors,SArea,Desc};
                DefaultTableModel a = (DefaultTableModel)this.jTable6.getModel();
                a.addRow(ViewArr);
            }
            viewrs.close();            
            
            con.commit();
            con.close();
            
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
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
            
            String qry = "select * from architecture_firm.\"Student_trainee\" where \"Supervisor_ID\" = '" + ID + "'";
            //String qry = "select * from architecture_firm.\"Student_trainee\" where \"Department_ID\" = '" + "010D" + "'";
            
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
               DefaultTableModel tbm = (DefaultTableModel)this.jTable3.getModel();
               tbm.addRow(DataArr);
               
            }
            rs.close();
            
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
        jPanel11 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
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

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.setLayout(null);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blue theme (default)", "Red theme", "Green theme", "Orange theme", "Purple theme" }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel11.add(jComboBox1);
        jComboBox1.setBounds(0, 0, 179, 30);

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
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185))
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(920, 0, 180, 680);

        jPanel10.setVisible(false);

        jButton5.setBackground(new java.awt.Color(0, 153, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Back");
        jButton5.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
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

        jTable3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.setRowHeight(70);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton12.setBackground(new java.awt.Color(0, 153, 255));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Delete student");
        jButton12.setPreferredSize(new java.awt.Dimension(72, 30));
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

        jButton13.setBackground(new java.awt.Color(0, 153, 255));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Modify student");
        jButton13.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton13MouseExited(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(0, 153, 255));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Add new student");
        jButton14.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton14MouseExited(evt);
            }
        });
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 153, 255));
        jLabel35.setText("Student name:");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 153, 255));
        jLabel36.setText("Reqiured hours:");

        jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField6.setText("enter required hours here");
        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6MouseClicked(evt);
            }
        });
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 153, 255));
        jLabel37.setText("Work shift:");

        jTextField7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField7.setText("enter work shift here");
        jTextField7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField7MouseExited(evt);
            }
        });
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 255));
        jLabel38.setText("name");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel40.setText("Manage your students here:");

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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("+");
        jLabel16.setForeground(darkcolor);
        if(darkmode)
        jLabel16.setForeground(Color.white);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("-");
        jLabel17.setToolTipText("");
        jLabel17.setForeground(darkcolor);
        if(darkmode)
        jLabel17.setForeground(Color.white);
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 153, 255));
        jLabel42.setText("Assign project:");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel38))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addGap(67, 67, 67)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel40)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel43)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)))
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel40)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel38))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jTextField6)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel10);
        jPanel10.setBounds(0, 150, 1100, 530);

        jPanel9.setVisible(false);

        jButton6.setBackground(new java.awt.Color(0, 153, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Back");
        jButton6.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
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

        jTable6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Poject ID", "Project name", "Land ID", "Customer ID", "Type", "Floors number", "Project area", "Description"
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
        jTable6.setRowHeight(70);
        jScrollPane6.setViewportView(jTable6);

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Choose Project");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel44)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(865, 865, 865)
                            .addComponent(jButton1))))
                .addGap(34, 34, 34))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9);
        jPanel9.setBounds(0, 150, 1100, 530);

        jPanel8.setVisible(false);

        jButton7.setBackground(new java.awt.Color(0, 153, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Back");
        jButton7.setPreferredSize(new java.awt.Dimension(72, 30));
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

        jTable5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Poject ID", "Project name", "Land ID", "Type", "Floors number", "Project area", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setRowHeight(70);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable5MouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

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

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.setText("enter project type here");
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField2MouseExited(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 153, 255));
        jLabel31.setText("Project type:");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 153, 255));
        jLabel34.setText("Project floors:");

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField4.setText("enter project floors number here");
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField4MouseExited(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("enter project description here");
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea1);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 153, 255));
        jLabel33.setText("Project description:");

        jButton15.setBackground(new java.awt.Color(0, 153, 255));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Modify project");
        jButton15.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
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

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel41.setText("Manage selected projects in your department here:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel32)
                                .addComponent(jLabel33))
                            .addGap(50, 50, 50)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(64, 64, 64)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel31)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel34)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField4)))))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel41)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
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
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
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
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    public void changetheme(Boolean Dark, Color dark,Color light,Color lighter){
        // TODO add your handling code here:
        //darkmode=!darkmode;
        
        darkmode =Dark;
        darkcolor = dark;
        lightcolor =light;
        lightercolor = lighter;
        
        //darkmode code
        jComboBox1.setVisible(!darkmode);
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
        //jLabel12.setBackground(new java.awt.Color(81,81,81));
        jPanel6.setBackground(new java.awt.Color(51,51,51));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,51,51)));
        //jLabel12.setForeground(Color.white);
        
        TH=jTable3.getTableHeader();
        TH.setBackground(Color.black);
        
        TH=jTable5.getTableHeader();
        TH.setBackground(Color.black);
        
        TH=jTable6.getTableHeader();
        TH.setBackground(Color.black);
        
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
        jLabel43.setForeground(Color.white);
        jLabel44.setForeground(Color.white);
        jLabel42.setForeground(Color.white);
        jLabel16.setForeground(Color.white);
        jLabel17.setForeground(Color.white);
        
        jPanel8.setBackground(new java.awt.Color(61,61,61));
        jPanel9.setBackground(new java.awt.Color(61,61,61));
        jPanel10.setBackground(new java.awt.Color(61,61,61));
        
        jButton1.setBackground(Color.black);
        jButton6.setBackground(Color.black);
        jButton5.setBackground(Color.black);
        jButton7.setBackground(Color.black);
        jButton15.setBackground(Color.black);
        jButton14.setBackground(Color.black);
        jButton13.setBackground(Color.black);
        jButton12.setBackground(Color.black);
        
      //  jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1)dark.png"))); // NOI18N
      //  jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1)dark.png"))); // NOI18N
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
        jPanel6.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        //jLabel12.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        //jLabel12.setForeground(Color.white);
        
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
        jLabel17.setForeground(darkcolor);
        jLabel16.setForeground(darkcolor);
        jLabel42.setForeground(darkcolor);
        jLabel39.setForeground(Color.black);
        jLabel40.setForeground(Color.black);
        jLabel41.setForeground(Color.black);
        
        
        jPanel8.setBackground(new java.awt.Color(242,242,242));
        jPanel9.setBackground(new java.awt.Color(242,242,242));
        jPanel10.setBackground(new java.awt.Color(242,242,242));
        
        TH=jTable3.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH=jTable5.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH=jTable6.getTableHeader();
        TH.setBackground(darkcolor);
        
        jButton1.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton7.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton15.setBackground(darkcolor);
        jButton14.setBackground(darkcolor);
        jButton13.setBackground(darkcolor);
        jButton12.setBackground(darkcolor);
      //  jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N
     //   jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N  
        }
          if(jPanel6.isVisible())
       { jPanel6.setVisible(false);
               jPanel6.setVisible(true);

               }
          
          engedit.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(engedit.isVisible())
       { engedit.setVisible(false);
               engedit.setVisible(true);
               }
         
         engprofile.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(engprofile.isVisible())
       { engprofile.setVisible(false);
               engprofile.setVisible(true);
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
        engedit.setLocationRelativeTo(null);
        engedit.setVisible(false);
        engedit.setVisible(true);
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
        //darkmode code
        jComboBox1.setVisible(!darkmode);
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
        jLabel12.setBackground(new java.awt.Color(81,81,81));
        jPanel6.setBackground(new java.awt.Color(51,51,51));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,51,51)));
        
        jPanel10.setBackground(new java.awt.Color(61,61,61));
        jPanel9.setBackground(new java.awt.Color(61,61,61));
        jPanel8.setBackground(new java.awt.Color(61,61,61));
        
        TH=jTable3.getTableHeader();
        TH.setBackground(Color.BLACK);
        
        TH=jTable5.getTableHeader();
        TH.setBackground(Color.BLACK);
        
        TH=jTable6.getTableHeader();
        TH.setBackground(Color.BLACK);
        
        jLabel12.setForeground(Color.white);
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
        jLabel44.setForeground(Color.white);
        jLabel41.setForeground(Color.white);
        jLabel43.setForeground(Color.white);
        jLabel16.setForeground(Color.white);
        jLabel42.setForeground(Color.white);
        jLabel17.setForeground(Color.white);
        
        
        jButton6.setBackground(Color.black);
        jButton1.setBackground(Color.black);
        jButton5.setBackground(Color.black);
        jButton7.setBackground(Color.black);
        jButton15.setBackground(Color.black);
        jButton14.setBackground(Color.black);
        jButton13.setBackground(Color.black);
        jButton12.setBackground(Color.black);
      //  jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1)dark.png"))); // NOI18N
      //  jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1)dark.png"))); // NOI18N
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
        jPanel6.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        jLabel12.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        jLabel12.setForeground(Color.white);
        
        TH=jTable3.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH=jTable5.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH=jTable6.getTableHeader();
        TH.setBackground(darkcolor);
        
        jPanel10.setBackground(new java.awt.Color(242,242,242));
        jPanel9.setBackground(new java.awt.Color(242,242,242));
        jPanel8.setBackground(new java.awt.Color(242,242,242));
        
        jLabel30.setForeground(darkcolor);
        jLabel31.setForeground(darkcolor);
        jLabel32.setForeground(darkcolor);
        jLabel33.setForeground(darkcolor);
        jLabel34.setForeground(darkcolor);
        jLabel35.setForeground(darkcolor);
        jLabel43.setForeground(darkcolor);
        jLabel36.setForeground(darkcolor);
        jLabel37.setForeground(darkcolor);
        jLabel44.setForeground(darkcolor);
        jLabel38.setForeground(darkcolor);
        jLabel16.setForeground(darkcolor);
        jLabel17.setForeground(darkcolor);
        jLabel42.setForeground(darkcolor);
        jLabel39.setForeground(Color.black);
        jLabel40.setForeground(Color.black);
        jLabel41.setForeground(Color.black);
        
        
        jButton5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton1.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton7.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton15.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton14.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton13.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton12.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
      //  jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N
     //   jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N  
        }
          if(jPanel6.isVisible())
       { jPanel6.setVisible(false);
               jPanel6.setVisible(true);

               }
          
          engedit.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(engedit.isVisible())
       { engedit.setVisible(false);
               engedit.setVisible(true);
               }
         
         engprofile.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(engprofile.isVisible())
       { engprofile.setVisible(false);
               engprofile.setVisible(true);
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
    }
    if (exit == 1) { 
        loginpage2 a = new loginpage2();
        a.setVisible(true);
        dispose();
    }
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        {
            jLabel14.setBackground(lightcolor);//new java.awt.Color(66,179,255));
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
        if(!darkmode){
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        }
        if(darkmode){
        jPanel5.setBackground(Color.black);
        jPanel3.setBackground(Color.black);               
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
        if(!darkmode){
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));}
        }
        if(darkmode)
        {
        jPanel4.setBackground(Color.black);
        jPanel3.setBackground(Color.black);
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
        if(!darkmode){
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        }
           if(darkmode)
        {
        jPanel4.setBackground(Color.black);
        jPanel5.setBackground(Color.black);
        }
    }//GEN-LAST:event_jPanel3MouseClicked

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
      jPanel10.setVisible(false);
        if(!darkmode)
        jPanel3.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel3.setBackground(Color.black);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
         jPanel9.setVisible(false);
        if(!darkmode)
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel5.setBackground(Color.black);
    }//GEN-LAST:event_jButton6MouseClicked

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
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        // TODO add your handling code here:
      if(darkmode)
        jButton7.setBackground(new java.awt.Color(81,81,81));
        if(!darkmode)
        jButton7.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));   
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        // TODO add your handling code here:
        if(darkmode)
        jButton7.setBackground(Color.black);
        if(!darkmode)
        jButton7.setBackground(darkcolor);//new java.awt.Color(0,153,255));
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jPanel8.setVisible(false);
        if(!darkmode)
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel4.setBackground(Color.black);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // put the values in here
        //make sure to add them to jLabel6MouseClicked(java.awt.event.MouseEvent evt)
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
        
        String firstname=Fname;
        String lastname=Lname;
        int age=2024-Byear;
        String work_hours=workHours;
        int salary=Integer.parseInt(Salary);
        
        
        //
         engprofile.dispose();
        engprofile= new viewengprofile1(darkmode,username,ID,firstname,lastname,age,work_hours,salary,darkcolor,lightcolor,lightercolor);
        engprofile.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

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
         engprofile.dispose();
        engprofile= new viewengprofile1(darkmode,username,ID,firstname,lastname,age,work_hours,salary,darkcolor,lightcolor,lightercolor);
        engprofile.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseClicked

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
        jPanel6.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
     //   jLabel12.setBackground(lightercolor);//new java.awt.Color(66,179,255));
      //  jLabel12.setForeground(Color.white);
        jButton5.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton7.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton15.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton14.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton13.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton12.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        jButton1.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        
        TH=jTable3.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH=jTable5.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH=jTable6.getTableHeader();
        TH.setBackground(darkcolor);
        
        jLabel30.setForeground(darkcolor);
        jLabel31.setForeground(darkcolor);
        jLabel32.setForeground(darkcolor);
        jLabel33.setForeground(darkcolor);
        jLabel34.setForeground(darkcolor);
        jLabel35.setForeground(darkcolor);
        jLabel36.setForeground(darkcolor);
        jLabel37.setForeground(darkcolor);
        jLabel44.setForeground(darkcolor);
        jLabel38.setForeground(darkcolor);
        jLabel43.setForeground(darkcolor);
        jLabel16.setForeground(darkcolor);
        jLabel17.setForeground(darkcolor);
        jLabel42.setForeground(darkcolor);
        jLabel39.setForeground(Color.black);
        jLabel40.setForeground(Color.black);
        jLabel41.setForeground(Color.black);
      //  jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N
     //   jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/engprofilepic(1).png"))); // NOI18N  
        
          if(jPanel6.isVisible())
       { jPanel6.setVisible(false);
               jPanel6.setVisible(true);

               }
          
          engedit.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(engedit.isVisible())
       { engedit.setVisible(false);
               engedit.setVisible(true);
               }
         
         engprofile.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(engprofile.isVisible())
       { engprofile.setVisible(false);
               engprofile.setVisible(true);
               }
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
        if(this.jTable3.getRowCount() == 0){
            JOptionPane.showMessageDialog(null,"The table is empty, please add a student first!", "Student selection error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            String StuIDtoDelete = JOptionPane.showInputDialog(null,"Please enter student ID here");
        try {
            // TODO add your handling code here:
            //Delete selected Land in database:
            int notFound = 0;
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry0 = "delete from architecture_firm.\"Student_Project\" where \"Student_ID\"='" + this.jTable3.getValueAt(this.jTable3.getSelectedRow(),0).toString() + "'";
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
                stmt.executeUpdate(qry0);
                stmt.executeUpdate(qry);
                DefaultTableModel tbm = (DefaultTableModel)this.jTable3.getModel();
                tbm.removeRow(getRowIndex(jTable3, 0, StuIDtoDelete));
                JOptionPane.showMessageDialog(null,"Student with ID " + StuIDtoDelete + " is deleted successfully!");
            }
            
            
            con.commit();
            con.close();
            
            jLabel38.setText("name");
            jTextField7.setText("enter work shift here");
            jTextField6.setText("enter required hours here");
            
            
            
            //dispose();
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        }
        
        if(this.jPanel6.isVisible()){
            this.jPanel6.setVisible(false);
            this.jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    public static int getRowIndex(JTable table, int column, String value) {
        int rowCount = table.getModel().getRowCount();

        for (int row = 0; row < rowCount; row++) {
            if (table.getModel().getValueAt(row, column).equals(value)) {
                return row;
            }
        }

        return -1;
    }
    
    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseClicked

    private void jButton13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseEntered

    private void jButton13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseExited

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
            Boolean darkm1=darkmode;
            Color darkc1 =darkcolor;
            Color lightc1= lightcolor;
            Color lighterc1= lightercolor;
            
            if(jTable3.getRowCount() == 0){
                JOptionPane.showMessageDialog(null,"The table is empty, please add a student first!","Student selection error",JOptionPane.ERROR_MESSAGE);
            }
            else{
             if(jTable3.getSelectedRow() == -1){
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
            
            String qry0 = "insert into architecture_firm.\"Student_Project\" (\"Student_ID\",\"Project_ID\") values('" + jTable3.getValueAt(jTable3.getSelectedRow(),0).toString() + "','" + jComboBox2.getSelectedItem().toString() + "')";
            
            String qry = "update architecture_firm.\"Student_trainee\" set \"Work_hours\"='" + this.jTextField7.getText() + "',\"Required_hours\"='" + Integer.parseInt(this.jTextField6.getText()) + "' where \"ID\"='" + this.jTable3.getValueAt(this.jTable3.getSelectedRow(), 0).toString() + "' and \"Supervisor_ID\"='" + ID + "'";
            
            Statement stmt = con.createStatement();
            stmt.executeUpdate(qry);
            
            con.commit();
            con.close();
            
            JOptionPane.showMessageDialog(null,"Changes are saved");
            dispose();
            
            
            engview1 a = new engview1(username,ID);
            a.setVisible(true);
            
            a.jPanel10.setVisible(true);
            
            a.changetheme(darkm1, darkc1, lightc1, lighterc1);
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        }
            }   
            }            
        if(this.jPanel6.isVisible()){
            this.jPanel6.setVisible(false);
            this.jPanel6.setVisible(false);
        }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14MouseClicked

    private void jButton14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseEntered
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton14MouseEntered

    private void jButton14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseExited
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton14MouseExited

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        
        addStuforEng a = new addStuforEng(username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        a.setVisible(true);        
        
        
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:

        if ("enter project name here".equals(jTextField1.getText()))
        {
            jTextField1.setText("");
            jTextField1.setForeground(Color.black);}

        if ("".equals(jTextField2.getText()))
        {
            jTextField2.setText("enter project type here");
            //jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        }

        if ("".equals(jTextField3.getText()))
        {
            jTextField3.setText("enter project area here");
            //jTextField3.setForeground(new java.awt.Color(153, 153, 153));
        }

        if ("".equals(jTextArea1.getText()))
        {
            jTextArea1.setText("enter project description here");
            //jTextArea1.setForeground(new java.awt.Color(153, 153, 153));
        }
        if ("".equals(jTextField4.getText()))
        {
            jTextField4.setText("enter project floors number here");
            //jTextField4.setForeground(new java.awt.Color(153, 153, 153));
        }
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked
        // TODO add your handling code here:
        if ("enter project area here".equals(jTextField3.getText()))
        {
            jTextField3.setText("");
            jTextField3.setForeground(Color.black);}

        if ("".equals(jTextField1.getText()))
        {
            jTextField1.setText("enter project name here");
            //jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        }

        if ("".equals(jTextField2.getText()))
        {
            jTextField2.setText("enter project type here");
            //jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        }   

        if ("".equals(jTextArea1.getText()))
        {
            jTextArea1.setText("enter project description here");
            //jTextArea1.setForeground(new java.awt.Color(153, 153, 153));
        }
        if ("".equals(jTextField4.getText()))
        {
            jTextField4.setText("enter project floors number here");
            //jTextField4.setForeground(new java.awt.Color(153, 153, 153));
        }
    }//GEN-LAST:event_jTextField3MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        // TODO add your handling code here:
        if ("enter project type here".equals(jTextField2.getText()))
        {
            jTextField2.setText("");
            jTextField2.setForeground(Color.black);}

        if ("".equals(jTextField1.getText()))
        {
            jTextField1.setText("enter project name here");
            //jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        }

        if ("".equals(jTextField3.getText()))
        {
            jTextField3.setText("enter project area here");
            //jTextField3.setForeground(new java.awt.Color(153, 153, 153));
        }

        if ("".equals(jTextArea1.getText()))
        {
            jTextArea1.setText("enter project description here");
            //jTextArea1.setForeground(new java.awt.Color(153, 153, 153));
        }
        if ("".equals(jTextField4.getText()))
        {
            jTextField4.setText("enter project floors number here");
            //jTextField4.setForeground(new java.awt.Color(153, 153, 153));
        }
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        // TODO add your handling code here:
        if ("enter project floors number here".equals(jTextField4.getText()))
        {
            jTextField4.setText("");
           // jTextField4.setForeground(Color.black);
        }

        if ("".equals(jTextField1.getText()))
        {
            jTextField1.setText("enter project name here");
            //jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        }

        if ("".equals(jTextField2.getText()))
        {
            jTextField2.setText("enter project type here");
           // jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        }

        if ("".equals(jTextField3.getText()))
        {
            jTextField3.setText("enter project area here");
            //jTextField3.setForeground(new java.awt.Color(153, 153, 153));
        }
        if ("".equals(jTextArea1.getText()))
        {
            jTextArea1.setText("enter project description here");
           // jTextArea1.setForeground(new java.awt.Color(153, 153, 153));
        }
        
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

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
            Boolean darkm1=darkmode;
            Color darkc1 =darkcolor;
            Color lightc1= lightcolor;
            Color lighterc1= lightercolor;
            
            if(this.jTable5.getRowCount() == 0){
                JOptionPane.showMessageDialog(null,"The table is empty, please select a project first!","Project selection error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(this.jTable5.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null,"Please select the project you want to modify first!","Project selection error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String confirm[] = {"Submit changes","Close"};
        var exit = JOptionPane.showOptionDialog(null, "Save changes?", "Save project properities changes",0, 1, null, confirm, confirm[0]);
        if(exit == 0){
            //Save changes of project here:
            try {
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "update architecture_firm.\"Project\" set \"Project_name\"='" + this.jTextField1.getText() + "',\"Type\"='" + this.jTextField2.getText() + "',\"Project_area\"='" + Integer.parseInt(this.jTextField3.getText()) +"',\"Number_of_floors\"='" + Integer.parseInt(this.jTextField4.getText()) +"',\"Description\"='" + this.jTextArea1.getText() +"' where \"Project_ID\"='" + this.jTable5.getValueAt(this.jTable5.getSelectedRow(), 0).toString() + "'";
            
            Statement stmt = con.createStatement();
            stmt.executeUpdate(qry);
            
            con.commit();
            con.close();
            
            JOptionPane.showMessageDialog(null,"Changes are saved");
            
            dispose();
            
            engview1 a = new engview1(username,ID);
            a.setVisible(true);
            
            
            a.jPanel8.setVisible(true);
             
            
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

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTextField2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseEntered
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTextField2MouseEntered

    private void jTextField2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseExited
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTextField2MouseExited

    private void jTextField4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseEntered
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTextField4MouseEntered

    private void jTextField4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseExited
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTextField4MouseExited

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        
        this.jTextField1.setText(this.jTable5.getValueAt(this.jTable5.getSelectedRow(),1).toString());
        this.jTextField2.setText(this.jTable5.getValueAt(this.jTable5.getSelectedRow(),3).toString());
        this.jTextField3.setText(this.jTable5.getValueAt(this.jTable5.getSelectedRow(),5).toString());
        this.jTextField4.setText(this.jTable5.getValueAt(this.jTable5.getSelectedRow(),4).toString());
        this.jTextArea1.setText(this.jTable5.getValueAt(this.jTable5.getSelectedRow(),6).toString());
                
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTable5MouseClicked

    private void jTable5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseEntered
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTable5MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(jTable6.getRowCount() == 0){
            JOptionPane.showMessageDialog(null,"There are no projects in your department currently!","Project selcetion error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            int exist = 0;
        
        if(this.jTable6.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Please select a project first!","Project selcetion error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            for(int i=0;i<jTable5.getRowCount();i++){
                if(this.jTable5.getValueAt(i,0).toString().equals(this.jTable6.getValueAt(this.jTable6.getSelectedRow(),0).toString())){
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
                    try {            
                        DriverManager.registerDriver(new org.postgresql.Driver());
                        String cInfo = "jdbc:postgresql://localhost:5432/postgres";
                        Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
                        con.setAutoCommit(false);

                        Statement stmt = con.createStatement();
                        
                        String PID = this.jTable6.getValueAt(this.jTable6.getSelectedRow(),0).toString();
                        
                        
                        String qry = "insert into architecture_firm.\"Engineer_project\"(\"Engineer_ID\",\"Project_ID\") values('" + ID + "','" + PID + "')";
                        
                        stmt.executeUpdate(qry);



                        con.commit();
                        con.close();            

                    } catch (Exception ex) {
                       JOptionPane.showMessageDialog(null, ex.toString());
                    }
                    
                    
                    
                    
                    DefaultTableModel a = (DefaultTableModel)this.jTable5.getModel();
                    if(this.jTable6.getValueAt(this.jTable6.getSelectedRow(),7) == null){
                        this.jTable6.setValueAt("", this.jTable6.getSelectedRow(),7);
                    }
                    String arr[] = {jTable6.getValueAt(jTable6.getSelectedRow(),0).toString(),jTable6.getValueAt(jTable6.getSelectedRow(),1).toString(),jTable6.getValueAt(jTable6.getSelectedRow(),2).toString(),jTable6.getValueAt(jTable6.getSelectedRow(),4).toString(),jTable6.getValueAt(jTable6.getSelectedRow(),5).toString(),jTable6.getValueAt(jTable6.getSelectedRow(),6).toString(),jTable6.getValueAt(jTable6.getSelectedRow(),7).toString()};
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
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6MouseClicked

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7MouseClicked

    private void jTextField7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7MouseEntered

    private void jTextField7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7MouseExited

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        this.jLabel38.setText(this.jTable3.getValueAt(this.jTable3.getSelectedRow(),1).toString() + " " + this.jTable3.getValueAt(this.jTable3.getSelectedRow(),2).toString());
        this.jTextField7.setText(this.jTable3.getValueAt(this.jTable3.getSelectedRow(),6).toString());
        this.jTextField6.setText(this.jTable3.getValueAt(this.jTable3.getSelectedRow(),8).toString());
        
        
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTextField11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseClicked
        // TODO add your handling code here:
        if("Start typing and it will do the search".equals(jTextField11.getText())){
            jTextField11.setText("");
            jTextField11.setForeground(Color.black);
        }
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTextField11MouseClicked

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        // TODO add your handling code here:
        DefaultTableModel tbm = (DefaultTableModel)jTable3.getModel();
        TableRowSorter<DefaultTableModel> s = new TableRowSorter<>(tbm);
        jTable3.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(jTextField11.getText()));
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jTextField12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseClicked
        // TODO add your handling code here:
        if("Start typing and it will do the search".equals(jTextField12.getText())){
            jTextField12.setText("");
            jTextField12.setForeground(Color.black);
        }
        if(jPanel6.isVisible()){
            jPanel6.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jTextField12MouseClicked

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        // TODO add your handling code here:
        DefaultTableModel tbm = (DefaultTableModel)jTable6.getModel();
        TableRowSorter<DefaultTableModel> s = new TableRowSorter<>(tbm);
        jTable6.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(jTextField12.getText()));
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        
        Integer num = Integer.parseInt(jTextField6.getText());
        num += 6;
        jTextField6.setText(num.toString());
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        Integer num = Integer.parseInt(jTextField6.getText());
        num -= 6;
        if(num < 0){
           num=0;
        }
        jTextField6.setText(num.toString());
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

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
                new engview1(username,ID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
