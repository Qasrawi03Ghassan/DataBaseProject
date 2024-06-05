/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.databaseproject;


import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author Msys
 */
public class customerview1 extends javax.swing.JFrame {

    /**
     * Creates new form customerview1
     */
    public static boolean darkmode = false;
    static String username;
    static String ID;
    static Color darkcolor =new java.awt.Color(0,153,255);
    static Color lightcolor = new java.awt.Color(56, 175, 255);
    static Color lightercolor =new java.awt.Color(66,179,255);
    static editcustomeraccount cusedit;
    static projectcreator createproject;
    static editLands editland;
    static EditProject editproj;
    
    //A variable to customize the table header:
    static JTableHeader TH;
    
    public customerview1(String u,String id) {
        username=u;
        ID=id;
        darkcolor =new java.awt.Color(0,153,255);
        lightcolor = new java.awt.Color(56, 175, 255);
        lightercolor =new java.awt.Color(66,179,255);
        cusedit =new editcustomeraccount(darkmode,username,ID,this,darkcolor,lightcolor,lightercolor);
        createproject=new projectcreator(7,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Customizing tableHeaders:
        TH = this.jTable1.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(new java.awt.Color(255,255,255));
        TH.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
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
            
            getLandsTable();
            getProjectsTable();
            
    }
    
    public void showRespanel(){
        jPanel20.setVisible(true);
    }
    public void showMedpanel(){
        jPanel24.setVisible(true);
    }
    public void showEdupanel(){
        jPanel6.setVisible(true);
    }
    public void showProjectcreate(){
        jPanel5.setVisible(true);
    }
    public void showManageProjects(){
        jPanel30.setVisible(true);
    }
    public void showAddLandPanel(){
        jPanel29.setVisible(true);
    }
    public void showManageLandspanel(){
        jPanel31.setVisible(true);
    }
    
    public boolean checkManageProjects(){
        return (jPanel30.isVisible());
    }
    
    public boolean checkAddLandpanel(){
        return (jPanel29.isVisible());
    }
    
    public boolean checkmanageLandspanel(){
        return (jPanel31.isVisible());
    }
    
    public boolean checkAddPrject(){
        return (jPanel5.isVisible());
    }
    public boolean checkRes(){
        return (jPanel20.isVisible());
    }
    public boolean checkMed(){
        return (jPanel24.isVisible());
    }
    public boolean checkEdu(){
        return (jPanel6.isVisible());
    }
    
    //public boolean checkpanel20(){
    //    return (jPanel20.isVisible());
    //}
    
    //public boolean checkpanel20(){
    //    return (jPanel20.isVisible());
    //}
    
    //public boolean checkpanel20(){
    //    return (jPanel20.isVisible());
    //}
    
    public void changeTheme(Boolean Dark, Color dark,Color light,Color lighter){
        // TODO add your handling code here:
        darkmode =Dark;
        darkcolor = dark;
        lightcolor =light;
        lightercolor = lighter;
        
        
        //darkmode=!darkmode;
        //for dark mode activation
        jComboBox1.setVisible(!darkmode);
        if(darkmode){
        //jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lightmode.png"))); // NOI18N
        //jLabel11.setText("light mode");
        //jLabel11.setBackground(new java.awt.Color(81,81,81));
        jPanel4.setBackground(Color.black);
        jPanel8.setBackground(Color.black);
        jPanel1.setBackground(new java.awt.Color(61,61,61));
        jPanel20.setBackground(new java.awt.Color(61,61,61));
        jPanel25.setBackground(new java.awt.Color(61,61,61));
        jPanel29.setBackground(new java.awt.Color(61,61,61));
        jPanel31.setBackground(new java.awt.Color(61,61,61));
        jPanel30.setBackground(new java.awt.Color(61,61,61));
        
        if(jPanel5.isVisible()||jPanel6.isVisible()||jPanel20.isVisible()||jPanel24.isVisible())
        {
         jPanel8.setBackground(new java.awt.Color(51,51,51));
        }
        
        //For customizing table header:
        TH = this.jTable1.getTableHeader();
        TH.setBackground(Color.black);
        TH.setForeground(Color.white);
        
        TH = this.jTable2.getTableHeader();
        TH.setBackground(Color.black);
        TH.setForeground(Color.white);
        
        
        jButton1.setBackground(Color.black);
        jButton2.setBackground(Color.black);
        jButton3.setBackground(Color.black);
        jButton4.setBackground(Color.black);
        jButton5.setBackground(Color.black);
        jButton6.setBackground(Color.black);
        jButton7.setBackground(Color.black);
        jButton8.setBackground(Color.black);
        jButton9.setBackground(Color.black);
        jButton10.setBackground(Color.black);
        jButton11.setBackground(Color.black);
        jButton12.setBackground(Color.black);
        jButton13.setBackground(Color.black);
        jPanel9.setBackground(Color.black);
           if(jPanel30.isVisible())
        {
            jPanel9.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel11.setBackground(Color.black);
        if(jPanel29.isVisible())
        {
            jPanel11.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel12.setBackground(Color.black);
        if(jPanel31.isVisible())
        {
             jPanel12.setBackground(new java.awt.Color(51,51,51));
        }
        jLabel27.setForeground(Color.white);
        jLabel28.setForeground(Color.white);
        jLabel29.setForeground(Color.white);
        jLabel30.setForeground(Color.white);
        jLabel31.setForeground(Color.white);
        jLabel32.setForeground(Color.white);
        jLabel33.setForeground(Color.white);
        jLabel34.setForeground(Color.white);
        jLabel35.setForeground(Color.white);
        jLabel36.setForeground(Color.white);
        jLabel44.setForeground(Color.white);
        jLabel43.setForeground(Color.white);
       // jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1)dark.png"))); // NOI18N
      //  jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1)dark.png"))); // NOI18N
        jPanel3.setBackground(new java.awt.Color(51,51,51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(61,61,61)));  
       jPanel5.setBackground(new java.awt.Color(61,61,61));
       jPanel15.setBackground(new java.awt.Color(61,61,61));
       jPanel6.setBackground(new java.awt.Color(61,61,61));
      
       jPanel14.setBackground(Color.black);
        }
        //for light mode activation
          if(!darkmode){
       
        //jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        //jLabel11.setText("dark mode");
        //jLabel11.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        jPanel20.setBackground(new java.awt.Color(242,242,242));
        jPanel25.setBackground(new java.awt.Color(242,242,242));
        jPanel29.setBackground(new java.awt.Color(242,242,242));
        jPanel31.setBackground(new java.awt.Color(242,242,242));
        jPanel30.setBackground(new java.awt.Color(242,242,242));
        jPanel3.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        
        TH = this.jTable1.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(Color.white);
        
        TH = this.jTable2.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(Color.white);
        
        jButton1.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton2.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton3.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton7.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton10.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton13.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jLabel27.setForeground(Color.black);
        jLabel28.setForeground(Color.black);
        jLabel29.setForeground(Color.black);
        jLabel30.setForeground(Color.black);
        jLabel31.setForeground(Color.black);
        jLabel32.setForeground(Color.black);
        jLabel33.setForeground(Color.black);
        jLabel44.setForeground(darkcolor);
        jLabel43.setForeground(darkcolor);
        jLabel34.setForeground(Color.black);
        jLabel35.setForeground(Color.black);
        jLabel36.setForeground(Color.black);
        
        if(jPanel5.isVisible()||jPanel6.isVisible()||jPanel20.isVisible()||jPanel24.isVisible())
        {
             jPanel8.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(jPanel30.isVisible())
        {
            jPanel9.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
           if(jPanel29.isVisible())
        {
            jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
                if(jPanel31.isVisible())
        {
            jPanel12.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
       // jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1).png"))); // NOI18N
      //  jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1).png"))); // NOI18N
        jPanel3.setBackground(lightcolor);//ew java.awt.Color(56, 175, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(56, 175, 255)));
        jPanel5.setBackground(new java.awt.Color(242,242,242));
        jPanel15.setBackground(new java.awt.Color(242,242,242));
        jPanel6.setBackground(new java.awt.Color(242,242,242));
        jPanel14.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
        }
        cusedit.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(cusedit.isVisible())
         {cusedit.setVisible(false);
          cusedit.setVisible(true);
         }
          
             if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               }
             
             createproject.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(createproject.isVisible())
       { createproject.setVisible(false);
               createproject.setVisible(true);
               }
         
         /*editland.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(editland.isVisible())
       { editland.setVisible(false);
               editland.setVisible(true);
               }*/
    }
    
    public void getProjectsTable(){
        
        //Getting projects from DB:
        try {

            String projectID = "";
            String name = "";
            String LandID="";
            String type = "";
            int Floors = 0;
            String SFloors = "";
            int Area = 0;
            String SArea = "";
            String Desc = "";
            String DepID ="";
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "select * from architecture_firm.\"Project\" where \"Customer_ID\" = '" + ID + "'";
            
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(qry);
            while(rs.next()){        
               projectID = rs.getString("Project_ID");
               name = rs.getString("Project_name");
               LandID = rs.getString("Land_ID");
               type = rs.getString("Type");
               Floors = rs.getInt("Number_of_floors");
               Area = rs.getInt("Project_area");
               Desc = rs.getString("Description");
               DepID = rs.getString("Department_ID");
               
               SFloors = Integer.toString(Floors);
               SArea = Integer.toString(Area);           
               
               String DataArr[] = {projectID,name,LandID,type,SFloors,SArea,Desc,DepID};
               DefaultTableModel tbm = (DefaultTableModel)this.jTable2.getModel();
               tbm.addRow(DataArr);
               
            }
            rs.close();
            
            con.commit();
            con.close();
            
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        
        
    }
    
    
    public void getLandsTable(){
    
        //Getting lands from DB:
        try {

            String LandID="";
            //String OwnerID="";
            String SoilType="";
            String Location="";
            int Area=0;
            String SArea;
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "select * from architecture_firm.\"Land\" where \"Owner_ID\" = '" + ID + "'";
            
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(qry);
            while(rs.next()){                
               LandID = rs.getString("Land_ID");
               //OwnerID = rs.getString("Owner_ID");
               SoilType = rs.getString("Soil_type");
               Location = rs.getString("Location");
               Area = rs.getInt("Area");
               
               SArea = Integer.toString(Area);           
               
               String DataArr[] = {LandID,SoilType,Location,SArea};
               DefaultTableModel tbm = (DefaultTableModel)this.jTable1.getModel();
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel29 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AL_Najah Architecture Firm");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel2MouseExited(evt);
            }
        });
        jPanel2.setLayout(null);

        jPanel3.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(56, 175, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(56, 175, 255)));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1)seethru.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
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

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        jLabel11.setText("Dark mode");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel11.setOpaque(true);
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jLabel12.setText("Exit  ");
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

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sidearrow.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        /*
        jLabel4.setText("account name");
        */
        jLabel4.setText(username);

        jPanel32.setBackground(new java.awt.Color(51, 51, 51));
        jPanel32.setLayout(null);

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
        jPanel32.add(jComboBox1);
        jComboBox1.setBounds(0, 0, 179, 30);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198))
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(920, 0, 200, 680);

        jPanel29.setVisible(false);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel30.setText("Land ID:");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel34.setText("Land area:");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel35.setText("Land location:");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel36.setText("Soil type:");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setText("enter land ID here");
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

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        jTextField2.setText("enter land area here");
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

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(153, 153, 153));
        jTextField3.setText("enter land location here");
        jTextField3.setPreferredSize(new java.awt.Dimension(126, 26));
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

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(153, 153, 153));
        jTextField4.setText("enter soil type here");
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

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel31.setText("Enter your lands information here:");

        jButton4.setBackground(new java.awt.Color(0, 153, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Submit land");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel29Layout.createSequentialGroup()
                                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel34)
                                        .addComponent(jLabel36))
                                    .addGap(90, 90, 90)
                                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField2)
                                        .addComponent(jTextField4)))
                                .addGroup(jPanel29Layout.createSequentialGroup()
                                    .addComponent(jLabel30)
                                    .addGap(113, 113, 113)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                                    .addComponent(jLabel35)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(271, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addGap(40, 40, 40)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel29);
        jPanel29.setBounds(0, 150, 1100, 530);

        jPanel31.setVisible(false);

        jButton7.setBackground(new java.awt.Color(0, 153, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Back");
        jButton7.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
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

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Land ID", "Soil type", "Location", "Area (m\u00b2)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("Select a row to modify a land properties");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setName(""); // NOI18N
        jTable1.setRowHeight(70);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton8.setBackground(new java.awt.Color(0, 153, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Add new land");
        jButton8.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 153, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Modify land");
        jButton9.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
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

        jButton10.setBackground(new java.awt.Color(0, 153, 255));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Delete land");
        jButton10.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
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

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel32.setText("View and manage your lands here:");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 153, 255));
        jLabel43.setText("Search land here:");

        jTextField5.setForeground(new java.awt.Color(153, 153, 153));
        jTextField5.setText("Start typing and it will do the search");
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5MouseClicked(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel31Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel31Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                .addGap(0, 381, Short.MAX_VALUE)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addComponent(jLabel43)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(63, 63, 63))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jPanel2.add(jPanel31);
        jPanel31.setBounds(0, 150, 1100, 530);

        jPanel24.setVisible(false);

        jPanel26.setBackground(new java.awt.Color(255, 153, 153));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hospital 1.jpg"))); // NOI18N
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel40MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel40MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(255, 153, 153));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rehab 1.jpg"))); // NOI18N
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel41MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel41MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel28.setBackground(new java.awt.Color(255, 153, 153));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clinic 1.jpg"))); // NOI18N
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel42MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel42MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButton3.setBackground(new java.awt.Color(0, 153, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Back");
        jButton3.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(0, 153, 255));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("choose a preset:");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1108, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel2.add(jPanel24);
        jPanel24.setBounds(0, 150, 1100, 530);

        jPanel30.setVisible(false);

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
                "Poject ID", "Project name", "Land ID", "Type", "Floors number", "Project area", "Description", "Department ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        jTable2.setRowHeight(70);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTable2MouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton11.setBackground(new java.awt.Color(0, 153, 255));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Add new project");
        jButton11.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton11MouseExited(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 153, 255));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Modify project");
        jButton12.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
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
        jButton13.setText("Delete project");
        jButton13.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
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

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel33.setText("View and manage your projects here:");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 153, 255));
        jLabel44.setText("Search project here:");

        jTextField6.setForeground(new java.awt.Color(153, 153, 153));
        jTextField6.setText("Start typing and it will do the search");
        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6MouseClicked(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addGap(0, 45, Short.MAX_VALUE)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel30Layout.createSequentialGroup()
                                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel30);
        jPanel30.setBounds(0, 150, 1100, 530);

        jPanel6.setVisible(false);

        jPanel7.setBackground(new java.awt.Color(255, 102, 51));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dpmiddleschool.jpg"))); // NOI18N
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel24MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel24MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 266, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel24)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 102, 51));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dpkindergarten(1).jpg"))); // NOI18N
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel23MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel23MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 252, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 102, 51));

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbuni(1).jpg"))); // NOI18N
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel26MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 269, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 102, 51));

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbhighschool(1).jpg"))); // NOI18N
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel25MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel25MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setOpaque(false);

        jLabel27.setBackground(new java.awt.Color(0, 153, 255));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("choose a preset:");

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Back");
        jButton1.setPreferredSize(new java.awt.Dimension(72, 30));
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

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6);
        jPanel6.setBounds(0, 150, 1100, 530);

        jPanel5.setVisible(false);

        jPanel10.setBackground(new java.awt.Color(0, 153, 255));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbimage7(healthcare)(2).jpg"))); // NOI18N
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel22MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel22MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(0, 153, 255));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbimage(resid)(2).jpg"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(0, 153, 255));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" Other");
        /*
        jLabel2.setToolTipText("");
        */
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbimage8(edu)(1).jpg"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 344, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(jPanel5);
        jPanel5.setBounds(0, 150, 1100, 530);

        jPanel20.setVisible(false);

        jPanel21.setBackground(new java.awt.Color(255, 153, 153));

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Apartments 1.jpg"))); // NOI18N
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel37MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel37MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel22.setBackground(new java.awt.Color(255, 153, 153));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/duplex1.jpg"))); // NOI18N
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel38MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel38MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel23.setBackground(new java.awt.Color(255, 153, 153));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Villa 1.jpg"))); // NOI18N
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel39MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel39MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButton2.setBackground(new java.awt.Color(0, 153, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Back");
        jButton2.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel28.setBackground(new java.awt.Color(0, 153, 255));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("choose a preset:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.add(jPanel20);
        jPanel20.setBounds(0, 150, 1100, 530);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/voltair-j-mayer-h-architects_26(3).jpg"))); // NOI18N
        /*
        jLabel3.setToolTipText("");
        */

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1);
        jPanel1.setBounds(0, 150, 1120, 530);

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1)seethru.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbsettings.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 153, 255));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("create new project");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addproject.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(0, 153, 255));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("manage projects");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manageproject.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(23, 23, 23))
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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("add new land");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addland.png"))); // NOI18N
        /*
        jLabel18.setToolTipText("");
        */
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(0, 153, 255));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel12MouseExited(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("manage owned lands");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manageland1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addGap(172, 172, 172))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(0, 0, 1270, 150);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1101, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
     //  jLabel1.setBackground(Color.black); 
        
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered

    }//GEN-LAST:event_jPanel2MouseEntered

    private void jPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseExited

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        jPanel3.setVisible(false);

        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        jPanel3.setVisible(true);

    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        
        String[] options = { "sign out", "cancel"};
    var exit = JOptionPane.showOptionDialog(null, "are you sure you want to sign out?", "Sign out", 
                                                      0, 1, null, options, options[0]);
    if (exit == 0) {
         loginpage2 a = new loginpage2();
        a.setVisible(true);
        dispose();
        createproject.dispose();
        cusedit.dispose();
    }
    if (exit == 1) { 
    }
    
    }//GEN-LAST:event_jLabel10MouseClicked

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

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
String[] options = { "exit","sign out" ,"cancel"};
    var exit = JOptionPane.showOptionDialog(null, "are you sure you want to exit?", "exit", 
                                                      0, 0, null, options, options[0]);
    if (exit == 0) {
     dispose();
     createproject.dispose();
     cusedit.dispose();
    }
    if (exit == 1) { 
        loginpage2 a = new loginpage2();
        a.setVisible(true);
        dispose();
        createproject.dispose();
        cusedit.dispose();
    }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        jPanel8.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel8.setBackground(new java.awt.Color(51,51,51));
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        // TODO add your handling code here:
        if(!jPanel5.isVisible() && !jPanel6.isVisible() && !jPanel20.isVisible() && !jPanel24.isVisible())
        {
        if(!darkmode)
        jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel8.setBackground(Color.black);
        }
    }//GEN-LAST:event_jPanel8MouseExited

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        if(jPanel30.isVisible())
        {
        if(!darkmode)
        jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel9.setBackground(Color.black);
        jPanel30.setVisible(false);
        }
        else 
        { 
        if(!darkmode)
        jPanel9.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel9.setBackground(new java.awt.Color(51,51,51));    
        jPanel30.setVisible(true);
        }
        if(darkmode)
     {
      jPanel8.setBackground(Color.black);
      jPanel11.setBackground(Color.black);
      jPanel12.setBackground(Color.black);
     
     }
      if(!darkmode)
     {
      jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
      jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
      jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
     
     }  
     
     jPanel5.setVisible(false);
     jPanel6.setVisible(false);
     jPanel20.setVisible(false);
     jPanel24.setVisible(false); 
     jPanel29.setVisible(false);
     jPanel31.setVisible(false);
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        jPanel9.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel9.setBackground(new java.awt.Color(51,51,51));
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
        // TODO add your handling code here:
        if(!jPanel30.isVisible()){
        if(!darkmode)
        jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel9.setBackground(Color.black);}
    }//GEN-LAST:event_jPanel9MouseExited

    private void jPanel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel11.setBackground(new java.awt.Color(51,51,51));

    }//GEN-LAST:event_jPanel11MouseEntered

    private void jPanel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseExited
        // TODO add your handling code here:
        if(!jPanel29.isVisible()){
        if(!darkmode)
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel11.setBackground(Color.black);}

    }//GEN-LAST:event_jPanel11MouseExited

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel11.setBackground(new java.awt.Color(51,51,51));
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        // TODO add your handling code here:
        if(!jPanel29.isVisible()){
        if(!darkmode)
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel11.setBackground(Color.black);}
    }//GEN-LAST:event_jLabel18MouseExited

    private void jPanel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseEntered
        // TODO add your handling code here:
     
                
        if(!darkmode)
        jPanel12.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel12.setBackground(new java.awt.Color(51,51,51));

    }//GEN-LAST:event_jPanel12MouseEntered

    private void jPanel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseExited
        // TODO add your handling code here:
        if(!jPanel31.isVisible()){  
         if(!darkmode)
        jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel12.setBackground(Color.black);}

    }//GEN-LAST:event_jPanel12MouseExited

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        
        if(jPanel5.isVisible())
        {
        if(!darkmode)
        jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel8.setBackground(Color.black);
        jPanel5.setVisible(false);
        }
        else 
        { 
        if(!darkmode)
        jPanel8.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        
        if(darkmode)
        jPanel8.setBackground(new java.awt.Color(51,51,51));    
        jPanel5.setVisible(true);
        }
     if(!darkmode)
     {jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
     jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
     jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));   
     }
     if(darkmode)
     {
      jPanel9.setBackground(Color.black);
      jPanel11.setBackground(Color.black);
      jPanel12.setBackground(Color.black);
     }
     
     jPanel6.setVisible(false);
     jPanel20.setVisible(false);
     jPanel24.setVisible(false);
     jPanel29.setVisible(false);
     jPanel30.setVisible(false);
     jPanel31.setVisible(false);
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        darkmode=!darkmode;
        //for dark mode activation
        jComboBox1.setVisible(!darkmode);
        if(darkmode){
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lightmode.png"))); // NOI18N
        jLabel11.setText("light mode");
        jLabel11.setBackground(new java.awt.Color(81,81,81));
        jPanel4.setBackground(Color.black);
        jPanel8.setBackground(Color.black);
        jPanel1.setBackground(new java.awt.Color(61,61,61));
        jPanel20.setBackground(new java.awt.Color(61,61,61));
        jPanel25.setBackground(new java.awt.Color(61,61,61));
        jPanel29.setBackground(new java.awt.Color(61,61,61));
        jPanel31.setBackground(new java.awt.Color(61,61,61));
        jPanel30.setBackground(new java.awt.Color(61,61,61));
        
        if(jPanel5.isVisible()||jPanel6.isVisible()||jPanel20.isVisible()||jPanel24.isVisible())
        {
         jPanel8.setBackground(new java.awt.Color(51,51,51));
        }
        
        //For customizing table header:
        TH = this.jTable1.getTableHeader();
        TH.setBackground(Color.black);
        TH.setForeground(Color.white);
        
        TH = this.jTable2.getTableHeader();
        TH.setBackground(Color.black);
        TH.setForeground(Color.white);
        
        jButton1.setBackground(Color.black);
        jButton2.setBackground(Color.black);
        jButton3.setBackground(Color.black);
        jButton4.setBackground(Color.black);
        jButton5.setBackground(Color.black);
        jButton6.setBackground(Color.black);
        jButton7.setBackground(Color.black);
        jButton8.setBackground(Color.black);
        jButton9.setBackground(Color.black);
        jButton10.setBackground(Color.black);
        jButton11.setBackground(Color.black);
        jButton12.setBackground(Color.black);
        jButton13.setBackground(Color.black);
        jPanel9.setBackground(Color.black);
           if(jPanel30.isVisible())
        {
            jPanel9.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel11.setBackground(Color.black);
        if(jPanel29.isVisible())
        {
            jPanel11.setBackground(new java.awt.Color(51,51,51));
        }
        jPanel12.setBackground(Color.black);
        if(jPanel31.isVisible())
        {
             jPanel12.setBackground(new java.awt.Color(51,51,51));
        }
        jLabel27.setForeground(Color.white);
        jLabel28.setForeground(Color.white);
        jLabel29.setForeground(Color.white);
        jLabel30.setForeground(Color.white);
        jLabel31.setForeground(Color.white);
        jLabel32.setForeground(Color.white);
        jLabel33.setForeground(Color.white);
        jLabel34.setForeground(Color.white);
        jLabel35.setForeground(Color.white);
        jLabel36.setForeground(Color.white);
        jLabel44.setForeground(Color.white);
        jLabel43.setForeground(Color.white);
       // jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1)dark.png"))); // NOI18N
      //  jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1)dark.png"))); // NOI18N
        jPanel3.setBackground(new java.awt.Color(51,51,51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(61,61,61)));  
       jPanel5.setBackground(new java.awt.Color(61,61,61));
       jPanel15.setBackground(new java.awt.Color(61,61,61));
       jPanel6.setBackground(new java.awt.Color(61,61,61));
      
       jPanel14.setBackground(Color.black);
        }
        //for light mode activation
          if(!darkmode){
       
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        jLabel11.setText("dark mode");
        jLabel11.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        jPanel20.setBackground(new java.awt.Color(242,242,242));
        jPanel25.setBackground(new java.awt.Color(242,242,242));
        jPanel29.setBackground(new java.awt.Color(242,242,242));
        jPanel31.setBackground(new java.awt.Color(242,242,242));
        jPanel30.setBackground(new java.awt.Color(242,242,242));
        jPanel3.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        
        TH = this.jTable1.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(Color.white);
        
        TH = this.jTable2.getTableHeader();
        TH.setBackground(darkcolor);
        TH.setForeground(Color.white);
        
        jButton1.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton2.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton3.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton7.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton10.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton13.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jLabel27.setForeground(Color.black);
        jLabel28.setForeground(Color.black);
        jLabel29.setForeground(Color.black);
        jLabel30.setForeground(Color.black);
        jLabel31.setForeground(Color.black);
        jLabel32.setForeground(Color.black);
        jLabel33.setForeground(Color.black);
        jLabel34.setForeground(Color.black);
        jLabel35.setForeground(Color.black);
        jLabel36.setForeground(Color.black);
        jLabel44.setForeground(darkcolor);
        jLabel43.setForeground(darkcolor);
        if(jPanel5.isVisible()||jPanel6.isVisible()||jPanel20.isVisible()||jPanel24.isVisible())
        {
             jPanel8.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(jPanel30.isVisible())
        {
            jPanel9.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
           if(jPanel29.isVisible())
        {
            jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
                if(jPanel31.isVisible())
        {
            jPanel12.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
       // jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1).png"))); // NOI18N
      //  jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1).png"))); // NOI18N
        jPanel3.setBackground(lightcolor);//ew java.awt.Color(56, 175, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));
        jPanel5.setBackground(new java.awt.Color(242,242,242));
        jPanel15.setBackground(new java.awt.Color(242,242,242));
        jPanel6.setBackground(new java.awt.Color(242,242,242));
        jPanel14.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
        }
        cusedit.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(cusedit.isVisible())
         {cusedit.setVisible(false);
          cusedit.setVisible(true);
         }
          
             if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               }
             
             createproject.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(createproject.isVisible())
       { createproject.setVisible(false);
               createproject.setVisible(true);
               }
         
         editland.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(editland.isVisible())
       { editland.setVisible(false);
               editland.setVisible(true);
               }
         
         editproj.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(editproj.isVisible())
       { editproj.setVisible(false);
               editproj.setVisible(true);
               }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        // TODO add your handling code here:
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbimage(resid)(2)hover.jpg"))); // NOI18N
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        // TODO add your handling code here:
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbimage(resid)(2).jpg"))); // NOI18N
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseEntered
        // TODO add your handling code here:
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbimage7(healthcare)(2)hover.jpg"))); // NOI18N

    }//GEN-LAST:event_jLabel22MouseEntered

    private void jLabel22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseExited
        // TODO add your handling code here:
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbimage7(healthcare)(2).jpg"))); // NOI18N

    }//GEN-LAST:event_jLabel22MouseExited

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        // TODO add your handling code here:
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbimage8(edu)(1)hover.jpg"))); // NOI18N
       if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               }

    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        // TODO add your handling code here:
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbimage8(edu)(1).jpg"))); // NOI18N
     if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               }
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        jPanel5.setVisible(false);
        jPanel20.setVisible(true);

    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        jPanel6.setVisible(true);
        jPanel5.setVisible(false);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jPanel5.setVisible(true);
        jPanel6.setVisible(false); 
      //  jPanel3.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          jPanel5.setVisible(true);
        jPanel20.setVisible(false); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        if(!darkmode)
        jPanel14.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel14.setBackground(new java.awt.Color(51,51,51));
        if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               } 
            
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
                if(!darkmode)
        jPanel14.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel14.setBackground(Color.black);
                    if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               } 
    }//GEN-LAST:event_jLabel2MouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
            jPanel5.setVisible(true);
        jPanel24.setVisible(false);      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
             jPanel5.setVisible(false);
        jPanel24.setVisible(true);     
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        jPanel3.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
        if(jPanel29.isVisible())
        {
        if(!darkmode)
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel11.setBackground(Color.black);
        jPanel29.setVisible(false);
        }
        else 
        { 
        if(!darkmode)
        jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel11.setBackground(new java.awt.Color(51,51,51));    
        jPanel29.setVisible(true);
        }
        if(darkmode)
     {
      jPanel8.setBackground(Color.black);
      jPanel9.setBackground(Color.black);
      jPanel12.setBackground(Color.black);
     
     }
      if(!darkmode)
     {
      jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
      jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
      jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
     
     }  
     
     jPanel5.setVisible(false);
     jPanel6.setVisible(false);
     jPanel20.setVisible(false);
     jPanel24.setVisible(false);  
     jPanel30.setVisible(false);
     jPanel31.setVisible(false);
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
        
   if(jPanel31.isVisible())
        {
        if(!darkmode)
        jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel12.setBackground(Color.black);
        jPanel31.setVisible(false);
        }
        else 
        { 
        if(!darkmode)
        jPanel12.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel12.setBackground(new java.awt.Color(51,51,51));    
        jPanel31.setVisible(true);
        }
        if(darkmode)
     {
      jPanel8.setBackground(Color.black);
      jPanel11.setBackground(Color.black);
      jPanel9.setBackground(Color.black);
     
     }
      if(!darkmode)
     {
      jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
      jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
      jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
     
     }  
     
     jPanel5.setVisible(false);
     jPanel6.setVisible(false);
     jPanel20.setVisible(false);
     jPanel24.setVisible(false); 
     jPanel29.setVisible(false);
     jPanel30.setVisible(false);
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        if(jPanel29.isVisible())
        {
        if(!darkmode)
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel11.setBackground(Color.black);
        jPanel29.setVisible(false);
        }
        else 
        { 
        if(!darkmode)
        jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel11.setBackground(new java.awt.Color(51,51,51));    
        jPanel29.setVisible(true);
        }
        if(darkmode)
     {
      jPanel8.setBackground(Color.black);
      jPanel9.setBackground(Color.black);
      jPanel12.setBackground(Color.black);
     
     }
      if(!darkmode)
     {
      jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
      jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
      jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
     
     }  
     
     jPanel5.setVisible(false);
     jPanel6.setVisible(false);
     jPanel20.setVisible(false);
     jPanel24.setVisible(false); 
     jPanel30.setVisible(false);
      jPanel31.setVisible(false);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        
    String[] options = { "confirm land submission", "cancel"};
    var submit = JOptionPane.showOptionDialog(null, "are you sure your land information is correct?", "data confirmation", 
                                                      0, 1, null, options, options[0]);
    if (submit == 0) {
   //put land database linking thing here     
     try {
            // TODO add your handling code here:
            //Saving Land properties in Database:
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "insert into architecture_firm.\"Land\" values('" + this.jTextField1.getText() + "','" + ID + "','" + this.jTextField4.getText() + "','" + this.jTextField3.getText() + "','" + true + "','" + this.jTextField2.getText() + "')";
            
            Statement stmt = con.createStatement();
            stmt.executeUpdate(qry);
            
            con.commit();
            con.close();
            
            JOptionPane.showMessageDialog(null,"Land added successfully!");
            
            //Adding the new land to the table in manage lands view
            String addedLand[] = {this.jTextField1.getText(),this.jTextField4.getText(), this.jTextField3.getText(), this.jTextField2.getText()};
            
            DefaultTableModel tbm = (DefaultTableModel)this.jTable1.getModel();
            tbm.addRow(addedLand);
            
            jTextField1.setText("enter land ID here");
            jTextField1.setForeground(new java.awt.Color(153, 153, 153));
            jTextField2.setText("enter land area here");
            jTextField2.setForeground(new java.awt.Color(153, 153, 153));
            jTextField3.setText("enter land location here");
            jTextField3.setForeground(new java.awt.Color(153, 153, 153));            
            jTextField4.setText("enter soil type here");
            jTextField4.setForeground(new java.awt.Color(153, 153, 153));
            
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }        
    }
    if (submit == 1) { 
    }    
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        // TODO add your handling code here:
     if(darkmode)
      jButton4.setBackground(new java.awt.Color(81,81,81));
     if(!darkmode)
      jButton4.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        // TODO add your handling code here:
      if(darkmode)
        jButton4.setBackground(Color.black);
       if(!darkmode)
        jButton4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
    }//GEN-LAST:event_jButton4MouseExited

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:
        
        if ("enter land ID here".equals(jTextField1.getText()))
            {
            jTextField1.setText("");
            jTextField1.setForeground(Color.black);}
        
                    if ("".equals(jTextField2.getText()))
            {
            jTextField2.setText("enter land area here");
            jTextField2.setForeground(new java.awt.Color(153, 153, 153));}
           
            if ("".equals(jTextField3.getText()))
            {
            jTextField3.setText("enter land location here");
            jTextField3.setForeground(new java.awt.Color(153, 153, 153));}
            
             if ("".equals(jTextField4.getText()))
            {
            jTextField4.setText("enter soil type here");
            jTextField4.setForeground(new java.awt.Color(153, 153, 153));}
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        // TODO add your handling code here:
          if ("enter land area here".equals(jTextField2.getText()))
            {
            jTextField2.setText("");
            jTextField2.setForeground(Color.black);}
          
            if ("".equals(jTextField1.getText()))
            {
            jTextField1.setText("enter land ID here");
            jTextField1.setForeground(new java.awt.Color(153, 153, 153));}
           
            if ("".equals(jTextField3.getText()))
            {
            jTextField3.setText("enter land location here");
            jTextField3.setForeground(new java.awt.Color(153, 153, 153));}
            
             if ("".equals(jTextField4.getText()))
            {
            jTextField4.setText("enter soil type here");
            jTextField4.setForeground(new java.awt.Color(153, 153, 153));}
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked
        // TODO add your handling code here:
          if ("enter land location here".equals(jTextField3.getText()))
            {
            jTextField3.setText("");
            jTextField3.setForeground(Color.black);}
          
           if ("".equals(jTextField1.getText()))
            {
            jTextField1.setText("enter land ID here");
            jTextField1.setForeground(new java.awt.Color(153, 153, 153));}
           
            if ("".equals(jTextField2.getText()))
            {
            jTextField2.setText("enter land area here");
            jTextField2.setForeground(new java.awt.Color(153, 153, 153));}
            
             if ("".equals(jTextField4.getText()))
            {
            jTextField4.setText("enter soil type here");
            jTextField4.setForeground(new java.awt.Color(153, 153, 153));}
           
    }//GEN-LAST:event_jTextField3MouseClicked

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        // TODO add your handling code here:
          if ("enter soil type here".equals(jTextField4.getText()))
            {
            jTextField4.setText("");
            jTextField4.setForeground(Color.black);}
          
           if ("".equals(jTextField1.getText()))
            {
            jTextField1.setText("enter land ID here");
            jTextField1.setForeground(new java.awt.Color(153, 153, 153));}
           
            if ("".equals(jTextField2.getText()))
            {
            jTextField2.setText("enter land area here");
            jTextField2.setForeground(new java.awt.Color(153, 153, 153));}
            
             if ("".equals(jTextField3.getText()))
            {
            jTextField3.setText("enter land location here");
            jTextField3.setForeground(new java.awt.Color(153, 153, 153));}
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jPanel29.setVisible(false);
         if(darkmode)
        jPanel11.setBackground(Color.black);
       if(!darkmode)
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
    }//GEN-LAST:event_jButton5ActionPerformed

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

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        cusedit.setLocationRelativeTo(null);
          cusedit.setVisible(false);
          cusedit.setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        // TODO add your handling code here:
        jLabel9.setBackground(Color.white);
        jLabel9.setForeground(Color.black);
    }//GEN-LAST:event_jLabel9MouseExited

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

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        // TODO add your handling code here:
                if(!darkmode)
        {
    jLabel11.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        }
        if(darkmode)
        {
   jLabel11.setBackground(new java.awt.Color(81,81,81));
    }
   jLabel11.setForeground(Color.white);
    }//GEN-LAST:event_jLabel11MouseEntered

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

    private void jLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseExited
        // TODO add your handling code here:
               jLabel10.setBackground(Color.white);
        jLabel10.setForeground(Color.black);
    }//GEN-LAST:event_jLabel10MouseExited

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        // TODO add your handling code here:
               jLabel11.setBackground(Color.white);
        jLabel11.setForeground(Color.black);
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        // TODO add your handling code here:
               jLabel12.setBackground(Color.white);
        jLabel12.setForeground(Color.black);
    }//GEN-LAST:event_jLabel12MouseExited

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        // TODO add your handling code here:
                      if(darkmode)
     jButton3.setBackground(new java.awt.Color(51,51,51));
     if(!darkmode)
      jButton3.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
                      if(darkmode)
     jButton1.setBackground(new java.awt.Color(51,51,51));
     if(!darkmode)
      jButton1.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
                      if(darkmode)
     jButton2.setBackground(new java.awt.Color(51,51,51));
     if(!darkmode)
      jButton2.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        // TODO add your handling code here:
                      if(darkmode)
        jButton3.setBackground(Color.black);
       if(!darkmode)
        jButton3.setBackground(darkcolor);//new java.awt.Color(0,153,255));
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
                      if(darkmode)
        jButton1.setBackground(Color.black);
       if(!darkmode)
        jButton1.setBackground(darkcolor);//new java.awt.Color(0,153,255));
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        // TODO add your handling code here:
                      if(darkmode)
        jButton2.setBackground(Color.black);
       if(!darkmode)
        jButton2.setBackground(darkcolor);//new java.awt.Color(0,153,255));
    }//GEN-LAST:event_jButton2MouseExited

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        // TODO add your handling code here:
    createproject.dispose();
        createproject =new projectcreator(7,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true);
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
           createproject.dispose();
        createproject =new projectcreator(8,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true);
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
    createproject.dispose();
        createproject =new projectcreator(9,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true);
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        // TODO add your handling code here:
        createproject.dispose();
        createproject =new projectcreator(10,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true);
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        // TODO add your handling code here:
        createproject.dispose();
        createproject =new projectcreator(1,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true); 
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        // TODO add your handling code here:
        createproject.dispose();
        createproject =new projectcreator(2,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true); 
    }//GEN-LAST:event_jLabel38MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        // TODO add your handling code here:
        createproject.dispose();
        createproject =new projectcreator(3,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true); 
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        // TODO add your handling code here:
        createproject.dispose();
        createproject =new projectcreator(4,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true); 
    }//GEN-LAST:event_jLabel40MouseClicked

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
        createproject.dispose();
        createproject =new projectcreator(5,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true); 
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        // TODO add your handling code here:
        createproject.dispose();
        createproject =new projectcreator(6,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true); 
    }//GEN-LAST:event_jLabel42MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
         createproject.dispose();
        createproject =new projectcreator(11,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        createproject.setVisible(true); 
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel37MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseEntered
        // TODO add your handling code here:
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Apartments 1hover.jpg"))); // NOI18N
    }//GEN-LAST:event_jLabel37MouseEntered

    private void jLabel37MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseExited
        // TODO add your handling code here:
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Apartments 1.jpg"))); // NOI18N
    }//GEN-LAST:event_jLabel37MouseExited

    private void jLabel38MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseEntered
        // TODO add your handling code here:
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/duplex1hover.jpg"))); // NOI18N


    }//GEN-LAST:event_jLabel38MouseEntered

    private void jLabel38MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseExited
        // TODO add your handling code here:
       jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/duplex1.jpg"))); // NOI18N


    }//GEN-LAST:event_jLabel38MouseExited

    private void jLabel39MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseEntered
        // TODO add your handling code here:\
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Villa 1hover.jpg"))); // NOI18N
    if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               } 
    }//GEN-LAST:event_jLabel39MouseEntered

    private void jLabel39MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseExited
        // TODO add your handling code here:
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Villa 1.jpg"))); // NOI18N
            if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               } 
    }//GEN-LAST:event_jLabel39MouseExited

    private void jLabel40MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseEntered
        // TODO add your handling code here:
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hospital 1hover.jpg"))); // NOI18N

    }//GEN-LAST:event_jLabel40MouseEntered

    private void jLabel40MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseExited
        // TODO add your handling code here:
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hospital 1.jpg"))); // NOI18N

    }//GEN-LAST:event_jLabel40MouseExited

    private void jLabel41MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseEntered
        // TODO add your handling code here:
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rehab 1hover.jpg"))); // NOI18N

    }//GEN-LAST:event_jLabel41MouseEntered

    private void jLabel41MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseExited
        // TODO add your handling code here:
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rehab 1.jpg"))); // NOI18N

    }//GEN-LAST:event_jLabel41MouseExited

    private void jLabel42MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseEntered
        // TODO add your handling code here:
     
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clinic 1hover.jpg"))); // NOI18N
            if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               } 
    }//GEN-LAST:event_jLabel42MouseEntered

    private void jLabel42MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseExited
        // TODO add your handling code here:
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clinic 1.jpg"))); // NOI18N
    if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               } 
    }//GEN-LAST:event_jLabel42MouseExited

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
        jPanel30.setVisible(false);
        if(!darkmode)
        jPanel9.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        else
        jPanel9.setBackground(Color.black);

    }//GEN-LAST:event_jButton6ActionPerformed

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
        jPanel31.setVisible(false);
        if(!darkmode)
        jPanel12.setBackground(darkcolor);//new java.awt.Color(0, 153, 255));
        else
        jPanel12.setBackground(Color.black);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseEntered
        // TODO add your handling code here:
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dpmiddleschoolhover.jpg"))); // NOI18N
    if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               } 
    }//GEN-LAST:event_jLabel24MouseEntered

    private void jLabel24MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseExited
        // TODO add your handling code here:
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dpmiddleschool.jpg"))); // NOI18N
        if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               } 
    }//GEN-LAST:event_jLabel24MouseExited

    private void jLabel23MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseEntered
        // TODO add your handling code here:
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dpkindergarten(1)hover.jpg"))); // NOI18N

    }//GEN-LAST:event_jLabel23MouseEntered

    private void jLabel23MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseExited
        // TODO add your handling code here:
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dpkindergarten(1).jpg"))); // NOI18N

    }//GEN-LAST:event_jLabel23MouseExited

    private void jLabel25MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseEntered
        // TODO add your handling code here:
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbhighschool(1)hover.jpg"))); // NOI18N

    }//GEN-LAST:event_jLabel25MouseEntered

    private void jLabel25MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseExited
        // TODO add your handling code here:
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbhighschool(1).jpg"))); // NOI18N

    }//GEN-LAST:event_jLabel25MouseExited

    private void jLabel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseEntered
        // TODO add your handling code here:
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbuni(1)hover.jpg"))); // NOI18N
         if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               } 

    }//GEN-LAST:event_jLabel26MouseEntered

    private void jLabel26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseExited
        // TODO add your handling code here:
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbuni(1).jpg"))); // NOI18N
 if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               } 
    }//GEN-LAST:event_jLabel26MouseExited

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        // TODO add your handling code here:
        //this does nothing but helps with stuff ignote it
        int i=1;
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        // TODO add your handling code here:
        projectcreator proj =new projectcreator(0,username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
        proj.setVisible(true); 
    }//GEN-LAST:event_jPanel14MouseClicked

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
     //     
        //jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darkmode.png"))); // NOI18N
        //jLabel11.setText("dark mode");
        //jLabel11.setBackground(lightercolor);//new java.awt.Color(66,179,255));
        jPanel20.setBackground(new java.awt.Color(242,242,242));
        jPanel25.setBackground(new java.awt.Color(242,242,242));
        jPanel29.setBackground(new java.awt.Color(242,242,242));
        jPanel3.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        jPanel4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        //For customizing table header:
        TH = this.jTable1.getTableHeader();
        TH.setBackground(darkcolor);
        TH = this.jTable2.getTableHeader();
        TH.setBackground(darkcolor);
        jButton1.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton2.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton3.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton4.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton5.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton6.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton7.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton10.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jButton13.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        jLabel27.setForeground(Color.black);
        jLabel28.setForeground(Color.black);
        jLabel29.setForeground(Color.black);
        jLabel30.setForeground(Color.black);
        jLabel31.setForeground(Color.black);
        jLabel32.setForeground(Color.black);
        jLabel33.setForeground(Color.black);
        jLabel34.setForeground(Color.black);
        jLabel35.setForeground(Color.black);
        jLabel36.setForeground(Color.black);
        jLabel44.setForeground(darkcolor);
        jLabel43.setForeground(darkcolor);
        
        if(jPanel5.isVisible()||jPanel6.isVisible()||jPanel20.isVisible()||jPanel24.isVisible())
        {
             jPanel8.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(jPanel30.isVisible())
        {
            jPanel9.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
           if(jPanel29.isVisible())
        {
            jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
        jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
                if(jPanel31.isVisible())
        {
            jPanel12.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        }
      //  jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1).png"))); // NOI18N
     //   jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerprofilepic(1).png"))); // NOI18N
        jPanel3.setBackground(lightcolor);//ew java.awt.Color(56, 175, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(lightcolor));//new java.awt.Color(56, 175, 255)));
        jPanel5.setBackground(new java.awt.Color(242,242,242));
        jPanel15.setBackground(new java.awt.Color(242,242,242));
        jPanel6.setBackground(new java.awt.Color(242,242,242));
        jPanel14.setBackground(darkcolor);//new java.awt.Color(0,153,255)); 
        
        cusedit.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(cusedit.isVisible())
         {cusedit.setVisible(false);
          cusedit.setVisible(true);
         }
        
          
             if(jPanel3.isVisible())
       { jPanel3.setVisible(false);
               jPanel3.setVisible(true);

               }  
             
             createproject.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(createproject.isVisible())
       { createproject.setVisible(false);
               createproject.setVisible(true);
               }
         
         editland.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(editland.isVisible())
       { editland.setVisible(false);
               editland.setVisible(true);
               }
         
         editproj.changetheme(darkmode,darkcolor,lightcolor,lightercolor);
         if(editproj.isVisible())
       { editproj.setVisible(false);
               editproj.setVisible(true);
               }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        // TODO add your handling code here:
        if (jPanel3.isVisible()){
         jPanel3.setVisible(false);
         jPanel3.setVisible(true);
     }
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseExited
        // TODO add your handling code here:
        if (jPanel3.isVisible()){
         jPanel3.setVisible(false);
         jPanel3.setVisible(true);
     }
    }//GEN-LAST:event_jButton8MouseExited

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if(jPanel29.isVisible())
        {
        if(!darkmode)
        jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel11.setBackground(Color.black);
        jPanel29.setVisible(false);
        }
        else 
        { 
        if(!darkmode)
        jPanel11.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        if(darkmode)
        jPanel11.setBackground(new java.awt.Color(51,51,51));    
        jPanel29.setVisible(true);
        }
        if(darkmode)
     {
      jPanel8.setBackground(Color.black);
      jPanel9.setBackground(Color.black);
      jPanel12.setBackground(Color.black);
     
     }
      if(!darkmode)
     {
      jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
      jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
      jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));
     
     }  
     
     jPanel5.setVisible(false);
     jPanel6.setVisible(false);
     jPanel20.setVisible(false);
     jPanel24.setVisible(false); 
     jPanel30.setVisible(false);
     jPanel31.setVisible(false);    
     
     if (jPanel3.isVisible()){
         jPanel3.setVisible(false);
         jPanel3.setVisible(true);
     }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton9MouseExited

    public static int selectedLand;
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        
        if(jTable1.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, ",The table is empty, please add a land first!", "Land selection error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            selectedLand=jTable1.getSelectedRow();   
        
        if(selectedLand == -1){
            JOptionPane.showMessageDialog(null, "Please select a land first!", "No land is selected", JOptionPane.ERROR_MESSAGE);
        }
        else{
           editLands a = new editLands(darkmode,username,ID,this,darkcolor,lightcolor,lightercolor);
           a.setVisible(true);
           a.changetheme(darkmode, darkcolor, lightcolor, lightcolor);
              
        }   
        }     
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseExited

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        String LandIDtoDelete = JOptionPane.showInputDialog(null,"Please enter land ID here");
        try {
            // TODO add your handling code here:
            //Delete selected Land in database:
            int notFound = 0;
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "delete from architecture_firm.\"Land\" where \"Land_ID\" = '" + LandIDtoDelete + "'";
            String LandIDqry = "select \"Land_ID\" from architecture_firm.\"Land\" where \"Owner_ID\" = '" + ID + "'";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(LandIDqry);
            
            while(rs.next()){
                String LandID = rs.getString("Land_ID");
                if(LandIDtoDelete.equals(LandID)){
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
                JOptionPane.showMessageDialog(null, "ID " + LandIDtoDelete + " is not present!");
            }
            else{
                stmt.executeUpdate(qry);
                DefaultTableModel tbm = (DefaultTableModel)this.jTable1.getModel();
                tbm.removeRow(getRowIndex(jTable1, 0, LandIDtoDelete));
                JOptionPane.showMessageDialog(null,"Land with ID " + LandIDtoDelete + " is deleted successfully!");
            }
            
            
            con.commit();
            con.close();
            
            
            
            //dispose();
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseEntered
        // TODO add your handling code here:
        if (jPanel3.isVisible()){
         jPanel3.setVisible(false);
         jPanel3.setVisible(true);
     }
    }//GEN-LAST:event_jButton11MouseEntered

    private void jButton11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseExited
        // TODO add your handling code here:
        if (jPanel3.isVisible()){
         jPanel3.setVisible(false);
         jPanel3.setVisible(true);
     }
    }//GEN-LAST:event_jButton11MouseExited

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        if(jPanel5.isVisible())
        {
        if(!darkmode)
        jPanel8.setBackground(darkcolor);//new java.awt.Color(0,153,255));
        if(darkmode)
        jPanel8.setBackground(Color.black);
        jPanel5.setVisible(false);
        }
        else 
        { 
        if(!darkmode)
        jPanel8.setBackground(lightcolor);//new java.awt.Color(56, 175, 255));
        
        if(darkmode)
        jPanel8.setBackground(new java.awt.Color(51,51,51));    
        jPanel5.setVisible(true);
        }
     if(!darkmode)
     {jPanel9.setBackground(darkcolor);//new java.awt.Color(0,153,255));
     jPanel11.setBackground(darkcolor);//new java.awt.Color(0,153,255));
     jPanel12.setBackground(darkcolor);//new java.awt.Color(0,153,255));   
     }
     if(darkmode)
     {
      jPanel9.setBackground(Color.black);
      jPanel11.setBackground(Color.black);
      jPanel12.setBackground(Color.black);
     }
     
     jPanel6.setVisible(false);
     jPanel20.setVisible(false);
     jPanel24.setVisible(false);
     jPanel29.setVisible(false);
     jPanel30.setVisible(false);
     jPanel31.setVisible(false);
     
     if (jPanel3.isVisible()){
         jPanel3.setVisible(false);
         jPanel3.setVisible(true);
     }
        
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseEntered

    private void jButton12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseExited

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        if(jTable2.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "The table is empty, please add a project first!", "Project selection error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(jTable2.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Please choose a project first!", "Not choosing a project", JOptionPane.ERROR_MESSAGE);
        }
        else{
            EditProject a = new EditProject(username,darkmode,ID,darkcolor,lightcolor,lightercolor,this);
            a.setVisible(true);
            a.setLocationRelativeTo(null);
            a.changetheme(darkmode, darkcolor, lightcolor, lightcolor);
        }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseEntered

    private void jButton13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseExited

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        String ProjIDtoDelete = JOptionPane.showInputDialog(null,"Please enter project ID here");
        try {
            // TODO add your handling code here:
            //Delete selected project in database:
            int notFound = 0;
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String qry = "delete from architecture_firm.\"Project\" where \"Project_ID\" = '" + ProjIDtoDelete + "'";
            String LandIDqry = "select \"Project_ID\" from architecture_firm.\"Project\" where \"Customer_ID\" = '" + ID + "'";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(LandIDqry);
            
            while(rs.next()){
                String ProjID = rs.getString("Project_ID");
                if(ProjIDtoDelete.equals(ProjID)){
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
                JOptionPane.showMessageDialog(null, "ID " + ProjIDtoDelete + " is not present!");
            }
            else{
                stmt.executeUpdate(qry);
                DefaultTableModel tbm = (DefaultTableModel)this.jTable2.getModel();
                tbm.removeRow(getRowIndex(jTable2, 0, ProjIDtoDelete));
                JOptionPane.showMessageDialog(null,"Project with ID " + ProjIDtoDelete + " is deleted successfully!");
            }
            
            
            con.commit();
            con.close();
            
            
            
            //dispose();
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if(jPanel3.isVisible()){
            jPanel3.setVisible(false);
            jPanel3.setVisible(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
        if(jPanel3.isVisible()){
            jPanel3.setVisible(false);
            jPanel3.setVisible(true);
        }
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
         if(jPanel3.isVisible()){
            jPanel3.setVisible(false);
            jPanel3.setVisible(true);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseEntered
        // TODO add your handling code here:
         if(jPanel3.isVisible()){
            jPanel3.setVisible(false);
            jPanel3.setVisible(true);
        }
    }//GEN-LAST:event_jTable2MouseEntered

    private void jTable2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseExited
        // TODO add your handling code here:
         if(jPanel3.isVisible()){
            jPanel3.setVisible(false);
            jPanel3.setVisible(true);
        }
    }//GEN-LAST:event_jTable2MouseExited

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:        
        DefaultTableModel tbm = (DefaultTableModel)jTable1.getModel();
        TableRowSorter<DefaultTableModel> s = new TableRowSorter<>(tbm);
        jTable1.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(jTextField5.getText()));
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseClicked
        // TODO add your handling code here:
        if("Start typing and it will do the search".equals(jTextField5.getText())){
            jTextField5.setText("");
            jTextField5.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField5MouseClicked

    private void jTextField6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MouseClicked
        // TODO add your handling code here:
        if("Start typing and it will do the search".equals(jTextField6.getText())){
            jTextField6.setText("");
            jTextField6.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField6MouseClicked

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        DefaultTableModel tbm = (DefaultTableModel)jTable2.getModel();
        TableRowSorter<DefaultTableModel> s = new TableRowSorter<>(tbm);
        jTable2.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(jTextField6.getText()));
    }//GEN-LAST:event_jTextField6KeyReleased

    public void showlandedit()
    {
        jPanel31.setVisible(true);
    }
    
    public void showprojectedit()
    {
        jPanel30.setVisible(true);
    }
    
    public static int getRowIndex(JTable table, int column, String value) {
        int rowCount = table.getModel().getRowCount();

        for (int row = 0; row < rowCount; row++) {
            if (table.getModel().getValueAt(row, column).equals(value)) {
                return row;
            }
        }

        return -1;
    }
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
            java.util.logging.Logger.getLogger(customerview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customerview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customerview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customerview1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new customerview1(username,ID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
