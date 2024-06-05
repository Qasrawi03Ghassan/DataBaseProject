/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.databaseproject;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

/**
 *
 * @author G.Q
 */
public class editAccount extends javax.swing.JFrame {
    
    static managerview1 outview;
    static Color darkcolor =new java.awt.Color(0,153,255);
    static Color lightcolor = new java.awt.Color(56, 175, 255);
    static Color lightercolor =new java.awt.Color(66,179,255);
    public static boolean darkmode;
    public String ID;
    public String username;
    public static boolean engtable;

    /**
     * Creates new form editAccount
     */
    public editAccount(boolean d,String u,String id,managerview1 a,Color dark,Color light,Color lighter,boolean eng) {
        darkmode=d;
        outview=a;
        darkcolor =dark;
        lightcolor =light;
        lightercolor = lighter;
        ID = id;
        username = u;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        engtable=eng;
        
        jLabel38.setText(getSUP()[0]);
        jLabel41.setText(getSUP()[1]);
        jTextField1.setText(getSUP()[3]);
        jLabel39.setText(getSUP()[2]);
        
        
    }
    public void changetheme(boolean d,Color dark,Color light,Color lighter){
        darkmode=d;
        darkcolor =dark;
        lightcolor =light;
        lightercolor = lighter;
        
        if(!darkmode)
        {
          jPanel1.setBackground(new java.awt.Color(242,242,242));
          
          jButton9.setBackground(darkcolor);
          jButton10.setBackground(darkcolor);
          
          jLabel31.setForeground(darkcolor);
          jLabel34.setForeground(darkcolor);
          jLabel35.setForeground(darkcolor);
          jLabel36.setForeground(darkcolor);
          jLabel37.setForeground(darkcolor);
          jLabel38.setForeground(darkcolor);
          jLabel39.setForeground(darkcolor);
          jLabel40.setForeground(darkcolor);
          jLabel41.setForeground(darkcolor);
          
        }  
        if(darkmode)
        {
          jPanel1.setBackground(new java.awt.Color(61,61,61));
          
          jButton9.setBackground(Color.black);
          jButton10.setBackground(Color.black);
          
          jLabel31.setForeground(Color.white);
          jLabel34.setForeground(Color.white);
          jLabel35.setForeground(Color.white);
          jLabel36.setForeground(Color.white);
          jLabel37.setForeground(Color.white);
          jLabel38.setForeground(Color.white);
          jLabel39.setForeground(Color.white);
          jLabel40.setForeground(Color.white);
          jLabel41.setForeground(Color.white);
          
        }
    }
    
    public String[] getSUP(){
        String name = "";
        String Username = "";
        String email = "";
        String pass = "";
            
        try {
            // TODO add your handling code here:
            //Saving customer properties in Database:
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            String cInfo = "jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
            con.setAutoCommit(false);
            
            Statement stmt = con.createStatement();
            
            if(!engtable){
                String qry = "select * from architecture_firm.\"Student_trainee\" where \"ID\"='" + managerview1.jTable3.getValueAt(managerview1.jTable3.getSelectedRow(),0).toString() + "'";
                
                ResultSet rs = stmt.executeQuery(qry);
                while(rs.next()){
                    name = rs.getString("First_name") + " " + rs.getString("Last_name");
                    email = rs.getString("Email_address");
                    Username = rs.getString("User_name");
                    pass = rs.getString("Password");

                }
            rs.close();
            }
            else{
                String qry = "select * from architecture_firm.\"Full-time_Engineer\" where \"ID\"='" + managerview1.jTable5.getValueAt(managerview1.jTable5.getSelectedRow(),0).toString() + "'";
                
                ResultSet rs = stmt.executeQuery(qry);
                while(rs.next()){
                    name = rs.getString("First_name") + " " + rs.getString("Last_name");
                    email = rs.getString("Email_address");
                    Username = rs.getString("User_name");
                    pass = rs.getString("Password");

                }
            rs.close();
            }
            
            
            
            con.commit();
            con.close();            
            
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        String Arr[] = {name,email,Username,pass};
        return Arr;
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
        jLabel31 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit account password");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 153, 255));
        jLabel31.setText("Edit account credentials here:");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 153, 255));
        jLabel37.setText("Name:");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 255));
        jLabel38.setText("Eng / Stu name");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 153, 255));
        jLabel34.setText("User name:");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 153, 255));
        jLabel35.setText("Current password:");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 153, 255));
        jLabel36.setText("New password:");

        jTextField4.setText("Enter new password here");
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

        jButton10.setBackground(new java.awt.Color(0, 153, 255));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Cancel");
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

        jButton9.setBackground(new java.awt.Color(0, 153, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Confirm");
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

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 153, 255));
        jLabel39.setText("Eng / Stu username");

        jTextField1.setEditable(false);
        jTextField1.setText("Current password");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 153, 255));
        jLabel40.setText("Email address:");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 153, 255));
        jLabel41.setText("Eng / Stu E-mail address");

        jTextField2.setEditable(false);
        jTextField2.setText("jTextField2");
        /*if(!engtable){
            jTextField2.setText(managerview1.jTable3.getValueAt(managerview1.jTable3.getSelectedRow(),0).toString());
        }
        else{
            jTextField2.setText(managerview1.jTable5.getValueAt(managerview1.jTable5.getSelectedRow(),0).toString());
        }*/
        jTextField2.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel34)
                    .addComponent(jLabel37)
                    .addComponent(jLabel36)
                    .addComponent(jLabel40))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4)
                    .addComponent(jLabel41)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel39))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        // TODO add your handling code here:
        if ("Enter new password here".equals(jTextField4.getText()))
        {
            jTextField4.setText("");
            jTextField4.setForeground(Color.black);}
        if ("".equals(jTextField1.getText()))
        {
            jTextField1.setText("Enter current password here");
            jTextField1.setForeground(Color.black);}
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseExited

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        String[] options = { "Yes", "No"};
        var exit = JOptionPane.showOptionDialog(null, "Are you sure?", "Cancel",0, 1, null, options, options[0]);
        if(exit == 0){
            this.dispose();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9MouseExited

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String[] options = { "Yes", "No"};
        var exit = JOptionPane.showOptionDialog(null, "Save changes?", "Cancel",0, 1, null, options, options[0]);
        if(exit == 0){
            try {
               // int wrongPass = 0;
                DriverManager.registerDriver(new org.postgresql.Driver());
                String cInfo = "jdbc:postgresql://localhost:5432/postgres";
                Connection con = DriverManager.getConnection(cInfo,"ghassanq","0123");
                con.setAutoCommit(false);
                
                Statement stmt = con.createStatement();
                String pass="";
                
                String qry="";
                
                //String qry = "update architecture_firm.\"Student_trainee\" set \"Password\"='" + jTextField4.getText() + "' where \"ID\" ='" + managerview1.jTable3.getValueAt(managerview1.jTable3.getSelectedRow(),0).toString() + "'";
                if(!engtable){
                    jTextField2.setText(managerview1.jTable3.getValueAt(managerview1.jTable3.getSelectedRow(),0).toString());
                   qry = "update architecture_firm.\"Student_trainee\" set \"Password\" = '" + this.jTextField4.getText() + "' where \"ID\" = '" + jTextField2.getText() + "'"; 
                }
                else{
                    jTextField2.setText(managerview1.jTable5.getValueAt(managerview1.jTable5.getSelectedRow(),0).toString());
                qry = "update architecture_firm.\"Full-time_Engineer\" set \"Password\" = '" + this.jTextField4.getText() + "' where \"ID\" = '" + jTextField2.getText() + "'";
            }
                
                stmt.executeUpdate(qry);
                
                JOptionPane.showMessageDialog(null,"Password changed");
                
                outview.dispose();
                dispose();
                managerview1 a = new managerview1(username,ID);
                a.setVisible(true);
                a.showpanel12();
                        
                a.changetheme(darkmode, darkcolor, lightcolor, lightcolor);

                con.commit();
                con.close();
                

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }

        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:
        if ("Enter current password here".equals(jTextField1.getText()))
        {
            jTextField1.setText("");
            jTextField1.setForeground(Color.black);}
        if ("".equals(jTextField4.getText()))
        {
            jTextField4.setText("Enter new password here");
            jTextField4.setForeground(Color.black);}
        
    }//GEN-LAST:event_jTextField1MouseClicked

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
            java.util.logging.Logger.getLogger(editAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new editAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
