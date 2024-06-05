/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.databaseproject;

import static com.mycompany.databaseproject.projectcreator.ppm;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author G.Q
 */
public class EditProject extends javax.swing.JFrame {

    /**
     * Creates new form EditProject
     */
    
    public static int presetcase;
    public static String username;
    public static String ID;
    public static boolean darkmode;
    public static String projname;
    public static String desc;
    public static int ppm;
    
    static Color darkcolor =new java.awt.Color(0,153,255);
    static Color lightcolor = new java.awt.Color(56, 175, 255);
    static Color lightercolor =new java.awt.Color(66,179,255);
    static customerview1 outview;

    public EditProject(String u,boolean d,String id,Color dark,Color light,Color lighter,customerview1 c) {
        username=u;
        darkmode=d;
        ID=id;
        darkcolor=dark;
        lightcolor=light;
        lightercolor= lighter;
        outview = c;
        
        initComponents();
        
        this.jLabel27.setText(customerview1.jTable2.getValueAt(customerview1.jTable2.getSelectedRow(),0).toString());
        this.jTextField9.setText(customerview1.jTable2.getValueAt(customerview1.jTable2.getSelectedRow(),1).toString());
        this.jTextField10.setText(customerview1.jTable2.getValueAt(customerview1.jTable2.getSelectedRow(),5).toString());
        this.jTextField11.setText(customerview1.jTable2.getValueAt(customerview1.jTable2.getSelectedRow(),4).toString());
        this.jTextField12.setText(customerview1.jTable2.getValueAt(customerview1.jTable2.getSelectedRow(),3).toString());
        if(customerview1.jTable2.getValueAt(customerview1.jTable2.getSelectedRow(),6) == null){
            this.jTextArea3.setText("");
        }
        else{
           this.jTextArea3.setText(customerview1.jTable2.getValueAt(customerview1.jTable2.getSelectedRow(),6).toString()); 
        }
        
        
        if(this.jTextField12.getText().equals("Apartment building")){
            ppm = 5;
        }
        else if(this.jTextField12.getText().equals("Duplex")){
            ppm = 5;
        }
        else if(this.jTextField12.getText().equals("Villa")){
            ppm = 15;
        }
        else if(this.jTextField12.getText().equals("Hospital")){
            ppm = 5;
        }
        else if(this.jTextField12.getText().equals("Clinic")){
            ppm = 10;
        }
        else if(this.jTextField12.getText().equals("Rehabiliatation center")){
            ppm = 10;
        }
        else if(this.jTextField12.getText().equals("Kindergarten")){
            ppm = 7;
        }
        else if(this.jTextField12.getText().equals("High school")){
            ppm = 7;
        }
        else if(this.jTextField12.getText().equals("elementary school")){
            ppm = 7;
        }
        else if(this.jTextField12.getText().equals("University department")){
            ppm = 10;
        }
        else {
            ppm = 7;
        }
        
        pricecalc();
        
        
    }
    
    public void changetheme(boolean d,Color dark,Color light,Color lighter){
        
        darkmode=d;
        darkcolor =dark;
        lightcolor =light;
        lightercolor = lighter;
        
        if(!darkmode)
        {
            
            jPanel4.setBackground(new java.awt.Color(242,242,242));
            jPanel5.setBackground(darkcolor);
            
            jLabel11.setForeground(Color.white);
            jLabel19.setForeground(darkcolor);
            jLabel20.setForeground(darkcolor);
            jLabel21.setForeground(darkcolor);
            jLabel22.setForeground(darkcolor);
            jLabel23.setForeground(darkcolor);
            jLabel24.setForeground(darkcolor);
            jLabel25.setForeground(darkcolor);
            jLabel26.setForeground(darkcolor);
            jLabel27.setForeground(darkcolor);
            
            jButton5.setBackground(darkcolor);
            jButton6.setBackground(darkcolor);          
        }  
        if(darkmode)
        {
          
          jPanel4.setBackground(new java.awt.Color(61,61,61));
          jPanel5.setBackground(Color.black);
          
          jLabel11.setForeground(Color.white);
          jLabel19.setForeground(Color.white);
          jLabel20.setForeground(Color.white);
          jLabel21.setForeground(Color.white);
          jLabel22.setForeground(Color.white);
          jLabel23.setForeground(Color.white);
          jLabel24.setForeground(Color.white);
          jLabel25.setForeground(Color.white);
          jLabel26.setForeground(Color.white);
          jLabel27.setForeground(Color.white);
          
          jButton5.setBackground(Color.black);
          jButton6.setBackground(Color.black);  
            
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

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit project information");
        setResizable(false);

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(0, 153, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Project information edit");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 255));
        jLabel19.setText("Project name:");

        jTextField9.setText("Enter name here");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 255));
        jLabel20.setText("Project area:");

        jTextField10.setText("Enter area here");
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

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 153, 255));
        jLabel21.setText("Number of floors:");

        jTextField11.setText("Enter number of floors here");
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField11MouseExited(evt);
            }
        });
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 153, 255));
        jLabel22.setText("Project type:");

        jTextField12.setText("Enter type here");
        jTextField12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField12MouseExited(evt);
            }
        });
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 153, 255));
        jLabel23.setText("Project description:");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText("Enter description here");
        jTextArea3.setToolTipText("");
        jScrollPane3.setViewportView(jTextArea3);

        jButton5.setBackground(new java.awt.Color(0, 153, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Cancel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 153, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Confirm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 153, 255));
        jLabel24.setText("Estimated price:");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setText("0 $");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 153, 255));
        jLabel26.setText("Project ID:");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 153, 255));
        jLabel27.setText("ID");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 42, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23)))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField11)
                                .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                .addComponent(jTextField12)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addGap(33, 33, 33))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void pricecalc(){
        
        if(this.jTextField12.getText().equals("Apartment building")){
            ppm = 5;
        }
        else if(this.jTextField12.getText().equals("Duplex")){
            ppm = 5;
        }
        else if(this.jTextField12.getText().equals("Villa")){
            ppm = 15;
        }
        else if(this.jTextField12.getText().equals("Hospital")){
            ppm = 5;
        }
        else if(this.jTextField12.getText().equals("Clinic")){
            ppm = 10;
        }
        else if(this.jTextField12.getText().equals("Rehabiliatation center")){
            ppm = 10;
        }
        else if(this.jTextField12.getText().equals("Kindergarten")){
            ppm = 7;
        }
        else if(this.jTextField12.getText().equals("High school")){
            ppm = 7;
        }
        else if(this.jTextField12.getText().equals("elementary school")){
            ppm = 7;
        }
        else if(this.jTextField12.getText().equals("University department")){
            ppm = 10;
        }
        else {
            ppm = 7;
        }        
        
      int area=0,floors=0;
         //  if(!jTextField6.getText().matches("\\d+")) 
          area=Integer.parseInt( jTextField10.getText());
       //    if(!jTextField1.getText().matches("\\d+")) 
          floors=Integer.parseInt( jTextField11.getText());
          int price = area * floors *ppm;
          jLabel25.setText(Integer.toString(price)+"$");
   }
    
    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String back[] = {"Yes","No"};
        var submit = JOptionPane.showOptionDialog(null, "are you sure?", "Cancel project edit", 0, 1, null, back, back[0]);
        if(submit == 0){
            this.dispose();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String back[] = {"Yes","No"};
        var submit = JOptionPane.showOptionDialog(null, "Save changes?", "Submit project edit", 0, 1, null, back, back[0]);
        if(submit == 0){
            //Save changes in database:
            try {
            // TODO add your handling code here:
            //Saving Project changes in Database:
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            String name = this.jTextField9.getText();
            String Sarea = this.jTextField10.getText();
            int area = Integer.parseInt(Sarea);
            String Sfloors = this.jTextField11.getText();
            int floors = Integer.parseInt(Sfloors);
            String type = this.jTextField12.getText();
            String desc = this.jTextArea3.getText();
            
            String qry = "update architecture_firm.\"Project\" set \"Project_name\"='" + name + "',\"Project_area\"='" + area + "',\"Number_of_floors\"='" + floors + "',\"Type\"='" + type + "',\"Description\"='" + desc + "' where \"Customer_ID\"='" + ID + "' and \"Project_ID\"='" + customerview1.jTable2.getValueAt(customerview1.jTable2.getSelectedRow(),0).toString() +"'";
            
            Statement stmt = con.createStatement();
            stmt.executeUpdate(qry);
            
            con.commit();
            con.close();
            
            JOptionPane.showMessageDialog(null,"Changes are saved");
            
            outview.dispose();
            dispose();
            
            customerview1 a = new customerview1(username,ID);
            a.setVisible(true);
            a.showprojectedit();
            a.changeTheme(darkmode, darkcolor, lightcolor, lightcolor);
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }            
            
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jTextField10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseEntered
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField10MouseEntered

    private void jTextField10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseExited
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField10MouseExited

    private void jTextField11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseEntered
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField11MouseEntered

    private void jTextField11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseExited
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField11MouseExited

    private void jTextField11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseClicked
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField11MouseClicked

    private void jTextField10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseClicked
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField10MouseClicked

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseClicked
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField12MouseClicked

    private void jTextField12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseEntered
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField12MouseEntered

    private void jTextField12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseExited
        // TODO add your handling code here:
        pricecalc();
    }//GEN-LAST:event_jTextField12MouseExited

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
            java.util.logging.Logger.getLogger(EditProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditProject(customerview1.username,customerview1.darkmode,customerview1.ID,customerview1.darkcolor,customerview1.lightcolor,customerview1.lightercolor,outview).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
