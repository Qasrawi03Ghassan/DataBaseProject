/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.databaseproject;

import static com.mycompany.databaseproject.customerview1.TH;
import static com.mycompany.databaseproject.customerview1.darkcolor;
import static com.mycompany.databaseproject.customerview1.getRowIndex;
import static com.mycompany.databaseproject.customerview1.lightcolor;
import static com.mycompany.databaseproject.customerview1.lightercolor;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Msys
 */
public class studentview1 extends javax.swing.JFrame {
public static boolean darkmode=false;
public static String username;
public static String ID;

static Color darkcolor =new java.awt.Color(0,153,255);
static Color lightcolor = new java.awt.Color(56, 175, 255);
static Color lightercolor =new java.awt.Color(66,179,255);

static editstudentaccount stuedit; 
static viewstuprofile stuprofile;
static JTableHeader TH;
    /**
     * Creates new form studentview1
     */
    public studentview1(String u,String id) {
        username=u;
        ID=id;
        darkcolor =new java.awt.Color(0,153,255);
        lightcolor = new java.awt.Color(56, 175, 255);
        lightercolor =new java.awt.Color(66,179,255);
        stuedit= new editstudentaccount(darkmode,username,ID,darkcolor,lightcolor,lightercolor,this);
        stuprofile = new viewstuprofile(darkmode,username,ID,"","",1,"",1,darkcolor,lightcolor,lightercolor);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Customizing table headers:
        TH = this.jTable1.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        TH = this.jTable2.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
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
        
        getProjectsTable();
        getManageProjectsTable();
    }
    
    public void getProjectsTable(){
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
            
            String DepIDforStu;
            
            String qryDep = "select \"Department_ID\" from architecture_firm.\"Student_trainee\" where \"ID\"='" + ID + "'";
            
            String qryView = "select * from architecture_firm.\"Project\"";
            
            String qry = "select ";
            
            Statement stmt = con.createStatement();
            
            ResultSet Deprs = stmt.executeQuery(qryDep);
            while(Deprs.next()){
                DepIDforStu = Deprs.getString("Department_ID");
                qryView = "select * from architecture_firm.\"Project\" where \"Department_ID\"='" + DepIDforStu + "'";
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
                
                String ViewArr[] = {PID,name,LandID,custID,type,SArea,SFloors,Desc,DepID};
                //String MngArr[] = {PID,name,LandID,type,SFloors,SArea,Desc};
                
                DefaultTableModel a = (DefaultTableModel)this.jTable1.getModel();
                a.addRow(ViewArr);
                
                //a = (DefaultTableModel)this.jTable2.getModel();
                //a.addRow(MngArr);
                
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
    
    public void getManageProjectsTable(){
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
            
            String qry0 = "select \"Project_ID\" from architecture_firm.\"Student_Project\" where \"Student_ID\"='" + ID + "'";
            String qryc = "select count(\"Student_ID\") from architecture_firm.\"Student_Project\" where \"Student_ID\"='" + ID + "'";
            String qry = "";
            
            int StuProsCount = 0;
            
            Statement stmt = con.createStatement();
            
            ResultSet rsc = stmt.executeQuery(qryc);
            while(rsc.next()){
                StuProsCount = rsc.getInt("count");                
            }
            rsc.close();
            
            String StuProIDs[] = new String [StuProsCount];
            
            ResultSet rs0 = stmt.executeQuery(qry0);
            for(int i=0;i<StuProIDs.length;i++){
                if(rs0.next()){
                    StuProIDs[i] = rs0.getString("Project_ID");
                }
            }
            rs0.close();
            
            for(int i=0;i<StuProIDs.length;i++){
                qry = "select * from architecture_firm.\"Project\" where \"Project_ID\" ='" + StuProIDs[i] + "'";
                ResultSet rs = stmt.executeQuery(qry);
                while(rs.next()){
                    PID = rs.getString("Project_ID");
                    name = rs.getString("Project_name");
                    LandID = rs.getString("Land_ID");
                    custID = rs.getString("Customer_ID");
                    type = rs.getString("Type");
                    Desc = rs.getString("Description");
                    Floors = rs.getInt("Number_of_floors");
                    SFloors = Integer.toString(Floors);
                    Area = rs.getInt("Project_area");
                    SArea = Integer.toString(Area);
                    DepID = rs.getString("Department_ID");
                    
                    String MngArr[] = {PID,name,LandID,type,SArea,SFloors,Desc};

                    DefaultTableModel a = (DefaultTableModel)this.jTable2.getModel();
                    a.addRow(MngArr);
                }
                rs.close();
            }
            
            
            
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

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
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
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1)seethru.png"))); // NOI18N
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

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(null);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blue theme (default)", "Red theme", "Green theme", "Orange theme", "Purple theme" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox1);
        jComboBox1.setBounds(0, 0, 179, 30);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(920, 0, 180, 680);

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1)seethru.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

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
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
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
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 1100, 150);

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
        jScrollPane1.setViewportView(jTable1);

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel39.setText("View projects in your department here:");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 153, 255));
        jLabel43.setText("Search project here:");

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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 936, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel43)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel43)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        jPanel1.add(jPanel9);
        jPanel9.setBounds(0, 150, 1100, 530);

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
                "Project ID", "Project name", "Type", "Area", "Floors number", "Description", "Department ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        jTable2.setRowHeight(70);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

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
        jLabel40.setText("Manage your assigned projects here:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 44, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel33))
                                    .addGap(50, 50, 50)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(106, 106, 106)
                                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField2))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(54, 54, 54))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8);
        jPanel8.setBounds(0, 150, 1100, 530);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/voltair-j-mayer-h-architects_26(student).jpg"))); // NOI18N

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
        
        jComboBox1.setVisible(!darkmode);
        //darkmode code
        if(darkmode)
        {
        //jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lightmode.png"))); // NOI18N
        //jLabel12.setText("light mode");   
        jPanel6.setBackground(new java.awt.Color(51,51,51));
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
        jPanel2.setBackground(Color.black);   
        //jLabel12.setBackground(new java.awt.Color(81,81,81));
        //jLabel12.setForeground(Color.white);
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,51,51)));
        jButton5.setBackground(Color.black);
        jButton6.setBackground(Color.black);
        jButton15.setBackground(Color.black);
        
        jLabel30.setForeground(Color.black);
        jLabel31.setForeground(Color.black);
        jLabel32.setForeground(Color.black);
        jLabel33.setForeground(Color.black);
        jLabel34.setForeground(Color.black);
        jLabel39.setForeground(Color.white);
        jLabel40.setForeground(Color.white);
        jLabel43.setForeground(Color.white);
        
        TH = jTable1.getTableHeader();
        TH.setBackground(Color.black);
        
        TH = jTable2.getTableHeader();
        TH.setBackground(Color.black);
       // jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1)dark.png"))); // NOI18N
        //jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1)dark.png"))); // NOI18N
        }
        //lightmode code
        if(!darkmode)
        { 
        //jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        //jLabel12.setText("Dark mode");
        jPanel6.setBackground(lightcolor);//new java.awt.Color(56,175,255)); 
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(jPanel8.isVisible())
        {
            jPanel4.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
        if(jPanel9.isVisible())
        {
            jPanel5.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel2.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
        //jLabel12.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        //jLabel12.setForeground(Color.white);
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        jButton5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton15.setBackground(darkcolor);
       // jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1).png"))); // NOI18N
       // jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1).png"))); // NOI18N
       
        jLabel30.setForeground(darkcolor);
        jLabel31.setForeground(darkcolor);
        jLabel32.setForeground(darkcolor);
        jLabel33.setForeground(darkcolor);
        jLabel34.setForeground(darkcolor);
        jLabel39.setForeground(Color.black);
        jLabel40.setForeground(Color.black);
        jLabel40.setForeground(darkcolor);

        }
            if(jPanel6.isVisible())
       { jPanel6.setVisible(false);
               jPanel6.setVisible(true);

               }
            
            stuedit.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(stuedit.isVisible())
       { stuedit.setVisible(false);
               stuedit.setVisible(true);
               }
         
         stuprofile.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(stuprofile.isVisible())
       { stuprofile.setVisible(false);
               stuprofile.setVisible(true);
               }
    }
    
    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        jPanel6.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        stuedit.setLocationRelativeTo(null);
        stuedit.setVisible(false);
        stuedit.setVisible(true);
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
            stuprofile.dispose();
            stuedit.dispose();
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
        jPanel6.setBackground(new java.awt.Color(51,51,51));
        jPanel8.setBackground(new java.awt.Color(61,61,61));
        jPanel9.setBackground(new java.awt.Color(61,61,61));
        jPanel4.setBackground(Color.black);
        
        TH = jTable1.getTableHeader();
        TH.setBackground(Color.black);
        
        TH = jTable2.getTableHeader();
        TH.setBackground(Color.black);
        
        if(jPanel8.isVisible())
        {
        jPanel4.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel5.setBackground(Color.black);
        if(jPanel9.isVisible())
        {
        jPanel5.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel2.setBackground(Color.black);   
        jLabel12.setBackground(new java.awt.Color(81,81,81));
        jLabel12.setForeground(Color.white);
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,51,51)));
        jButton5.setBackground(Color.black);
        jButton6.setBackground(Color.black);
        jButton15.setBackground(Color.black);
        jLabel30.setForeground(Color.white);
        jLabel31.setForeground(Color.white);
        jLabel32.setForeground(Color.white);
        jLabel33.setForeground(Color.white);
        jLabel34.setForeground(Color.white);
        jLabel39.setForeground(Color.white);
        jLabel40.setForeground(Color.white);
        jLabel43.setForeground(Color.white);
       // jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1)dark.png"))); // NOI18N
        //jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1)dark.png"))); // NOI18N
        }
        //lightmode code
        if(!darkmode)
        { 
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        jLabel12.setText("Dark mode");
        jPanel6.setBackground(lightcolor);//new java.awt.Color(56,175,255)); 
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        
        jPanel8.setBackground(new java.awt.Color(242,242,242));
        jPanel9.setBackground(new java.awt.Color(242,242,242));
        
        TH = jTable1.getTableHeader();
        TH.setBackground(darkcolor);
        
        TH = jTable2.getTableHeader();
        TH.setBackground(darkcolor);
        
        if(jPanel8.isVisible())
        {
            jPanel4.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
        if(jPanel9.isVisible())
        {
            jPanel5.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel2.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
        
        jLabel12.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        jLabel12.setForeground(Color.white);
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        jButton5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton15.setBackground(darkcolor);
        
        
        jLabel30.setForeground(darkcolor);
        jLabel31.setForeground(darkcolor);
        jLabel32.setForeground(darkcolor);
        jLabel33.setForeground(darkcolor);
        jLabel34.setForeground(darkcolor);
        jLabel39.setForeground(Color.black);
        jLabel40.setForeground(Color.black);
        jLabel43.setForeground(darkcolor);
       // jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1).png"))); // NOI18N
       // jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1).png"))); // NOI18N

        }
            if(jPanel6.isVisible())
       { jPanel6.setVisible(false);
               jPanel6.setVisible(true);

               }
            
            stuedit.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(stuedit.isVisible())
       { stuedit.setVisible(false);
               stuedit.setVisible(true);
               }
         
         stuprofile.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(stuprofile.isVisible())
       { stuprofile.setVisible(false);
               stuprofile.setVisible(true);
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
     stuprofile.dispose();
     stuedit.dispose();
    }
    if (exit == 1) { 
        loginpage2 a = new loginpage2();
        a.setVisible(true);
        dispose();
        stuprofile.dispose();
        stuedit.dispose();
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

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        jPanel6.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseEntered

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
        jPanel5.setBackground(Color.black);}
    }//GEN-LAST:event_jPanel5MouseExited

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        if(jPanel8.isVisible())
        jPanel8.setVisible(false);
        else
        jPanel8.setVisible(true);
        jPanel9.setVisible(false);   
        if(darkmode)
         jPanel5.setBackground(Color.black);
        else
         jPanel5.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        if(jPanel9.isVisible())
        jPanel9.setVisible(false);
        else
        jPanel9.setVisible(true);
        jPanel8.setVisible(false);
        if(darkmode)
         jPanel4.setBackground(Color.black);
        else
         jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
    }//GEN-LAST:event_jPanel5MouseClicked

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
        if(darkmode)
        jPanel5.setBackground(Color.black);
        if(!darkmode)
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
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
        if(darkmode)
        jPanel4.setBackground(Color.black);
        if(!darkmode)
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        String Fname = "";
        String Lname = "";
        int Byear = 0;
        String workHours = "";
        String remainedHours = "";
       // put the values in here
       try {
            // TODO add your handling code here:
            //Saving customer properties in Database:
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "select * from architecture_firm.\"Student_trainee\" where \"ID\" = '" + ID + "'";
            String dateqry = "select  DATE_PART('year',\"Birthdate\") from architecture_firm.\"Student_trainee\" where \"ID\"='" + ID +"'";
            
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(qry);
            while(rs.next()){
               Fname = rs.getString("First_name");
               Lname = rs.getString("Last_name");
               workHours = rs.getString("Work_hours");
               remainedHours = rs.getString("Required_hours");
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
       
       
       //make sure to add values to jLabel6MouseClicked(java.awt.event.MouseEvent evt) as well
        String firstname=Fname;
        String lastname=Lname;
        int age=2024 - Byear;
        String work_hours=workHours;
        int remain=Integer.parseInt(remainedHours);
        
        
        //
        stuprofile.dispose();
         stuprofile=new viewstuprofile(darkmode,username,ID,firstname,lastname,age,work_hours,remain,darkcolor,lightcolor,lightercolor);
         stuprofile.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
   
        String Fname = "";
        String Lname = "";
        int Byear = 0;
        String workHours = "";
        String remainedHours = "";
       // put the values in here
       try {
            // TODO add your handling code here:
            //Saving customer properties in Database:
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "select * from architecture_firm.\"Student_trainee\" where \"ID\" = '" + ID + "'";
            String dateqry = "select  DATE_PART('year',\"Birthdate\") from architecture_firm.\"Student_trainee\" where \"ID\"='" + ID +"'";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            
            while(rs.next()){
               Fname = rs.getString("First_name");
               Lname = rs.getString("Last_name");
               workHours = rs.getString("Work_hours");
               remainedHours = rs.getString("Required_hours");
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
        int age=2024 - Byear;
        String work_hours=workHours;
        int remain=Integer.parseInt(remainedHours);
        
        
        //
        stuprofile.dispose();
         stuprofile=new viewstuprofile(darkmode,username,ID,firstname,lastname,age,work_hours,remain,darkcolor,lightcolor,lightercolor);
         stuprofile.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        // TODO add your handling code here:
        int i;
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
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
        
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        jLabel12.setText("Dark mode");
        jPanel6.setBackground(lightcolor);//new java.awt.Color(56,175,255)); 
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(jPanel8.isVisible())
        {
            jPanel4.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel5.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
        if(jPanel9.isVisible())
        {
            jPanel5.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel2.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
       // jLabel12.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        //jLabel12.setForeground(Color.white);
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        jButton5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton15.setBackground(darkcolor);//new java.awt.Color(0,153,255));
       // jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1).png"))); // NOI18N
       // jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stuprofilepic(1).png"))); // NOI18N
       
       jLabel30.setForeground(darkcolor);
       jLabel31.setForeground(darkcolor);
       jLabel32.setForeground(darkcolor);
       jLabel33.setForeground(darkcolor);
       jLabel34.setForeground(darkcolor);
       jLabel39.setForeground(Color.black);
       jLabel40.setForeground(Color.black);
       jLabel43.setForeground(darkcolor);
       
       TH = jTable1.getTableHeader();
       TH.setBackground(darkcolor);
        
       TH = jTable2.getTableHeader();
       TH.setBackground(darkcolor);

        
            if(jPanel6.isVisible())
       { jPanel6.setVisible(false);
               jPanel6.setVisible(true);

               }
            
            stuedit.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(stuedit.isVisible())
       { stuedit.setVisible(false);
               stuedit.setVisible(true);
               }
         
         stuprofile.changetheme(darkmode, darkcolor, lightcolor, lightercolor);
         if(stuprofile.isVisible())
       { stuprofile.setVisible(false);
               stuprofile.setVisible(true);
               }
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
        
        if(this.jTable2.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Please select a project first!", "Project selection error",JOptionPane.ERROR_MESSAGE);
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
            
            studentview1 a = new studentview1(username,ID);
            a.setVisible(true);
            
             
            
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
        
    }//GEN-LAST:event_jButton15ActionPerformed

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

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        this.jTextField1.setText(this.jTable2.getValueAt(this.jTable2.getSelectedRow(),1).toString());
        this.jTextField2.setText(this.jTable2.getValueAt(this.jTable2.getSelectedRow(),3).toString());
        this.jTextField3.setText(this.jTable2.getValueAt(this.jTable2.getSelectedRow(),5).toString());
        this.jTextField4.setText(this.jTable2.getValueAt(this.jTable2.getSelectedRow(),4).toString());
        this.jTextArea1.setText(this.jTable2.getValueAt(this.jTable2.getSelectedRow(),6).toString());
    }//GEN-LAST:event_jTable2MouseClicked

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
        DefaultTableModel tbm = (DefaultTableModel)jTable1.getModel();
        TableRowSorter<DefaultTableModel> s = new TableRowSorter<>(tbm);
        jTable1.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(jTextField11.getText()));
    }//GEN-LAST:event_jTextField11KeyReleased

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
            java.util.logging.Logger.getLogger(studentview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(studentview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(studentview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studentview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new studentview1(username,ID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
