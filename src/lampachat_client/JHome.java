package lampachat_client;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class JHome extends javax.swing.JFrame {

    /**
     * Creates new form JHome
     */
    public static String contact = null;
    public static int contactLIstCount;
    public static int msgCount;
    private String userName;
    public String getUserName(){
        return userName;
    }

    public void incrmsgCount() {
        msgCount++;
    }

    public int getmsgCount() {
        return msgCount;
    }

    public JHome() {
        initComponents();
        this.setLocationRelativeTo(null);

        setStatus(false);
        jScrollPane2.setVisible(false);
        jScrollPane3.setVisible(false);
        jButtonSend.setVisible(false);
        if (!this.checkLogin()) {
            new JInfoWindow().setText("Please, Login or Register");
            jMenuItemAddContact.setVisible(false);
            JMenuItemLogout.setVisible(false);
            jMenuItemProfile.setVisible(false);
        } else {
            JMenuItemLogin.setVisible(false);
        }
        ConctactListThread conctactListThread = new ConctactListThread(this);
    }

    public boolean checkLogin() {
        Database database = new Database();
        userName = database.ReadLogin();
        if (userName == null) {
            return false;
        }
        return true;
    }

    public void setContactList(ArrayList<String> list) {
        var ls = new DefaultListModel();
        for (int i = 0; i < list.size(); i++) {
            ls.addElement(list.get(i));
        }
        jList1.setModel(ls);
    }

    public void setStatus(boolean online) {
        if (online) {
            jLabel1.setText("Online");
            jLabel1.setForeground(Color.decode("0x00cc00"));
        } else {
            jLabel1.setText("Offline");
            jLabel1.setForeground(Color.decode("0xcc000a"));
        }
    }

    public void msgIN(String[] sr) {
        if (sr[0].equals(userName)) {
            sr[0] = "Me";
        }
        jTextArea1.setText(jTextArea1.getText() + "-" + sr[0] + "-  (" + sr[3] + "):\n" + sr[2] + "\n" + "\n");
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButtonSend = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        JMenuItemLogin = new javax.swing.JMenuItem();
        JMenuItemLogout = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemProfile = new javax.swing.JMenuItem();
        jMenuItemAddContact = new javax.swing.JMenuItem();
        jMenuItemOptions = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu2Update = new javax.swing.JMenuItem();
        jMenu2AboutApp = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LampaChat");
        setMinimumSize(new java.awt.Dimension(400, 300));

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel1.setText("Status");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setToolTipText("");
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(jTextArea1);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jButtonSend.setText("Send");
        jButtonSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSendMouseClicked(evt);
            }
        });

        jMenu1.setText("Menu");

        JMenuItemLogin.setText("Login");
        JMenuItemLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemLoginActionPerformed(evt);
            }
        });
        jMenu1.add(JMenuItemLogin);

        JMenuItemLogout.setText("Logout");
        JMenuItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(JMenuItemLogout);
        jMenu1.add(jSeparator2);

        jMenuItemProfile.setText("Profile");
        jMenuItemProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProfileActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemProfile);
        jMenuItemProfile.getAccessibleContext().setAccessibleName("jMenuItemProfile");

        jMenuItemAddContact.setText("Add Contact");
        jMenuItemAddContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddContactActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemAddContact);

        jMenuItemOptions.setText("Options");
        jMenuItemOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOptionsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemOptions);
        jMenu1.add(jSeparator1);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenu2Update.setText("Check Update");
        jMenu2.add(jMenu2Update);

        jMenu2AboutApp.setText("About");
        jMenu2AboutApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2AboutAppActionPerformed(evt);
            }
        });
        jMenu2.add(jMenu2AboutApp);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSend))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSend)
                            .addComponent(jLabel1))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JMenuItemLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemLoginActionPerformed
        JLogin loginWindow = new JLogin(this);
        loginWindow.setVisible(true);
    }//GEN-LAST:event_JMenuItemLoginActionPerformed

    private void jMenuItemOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOptionsActionPerformed
        // TODO add your handling code here:
        new JOptions(this);
    }//GEN-LAST:event_jMenuItemOptionsActionPerformed

    private void jMenu2AboutAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2AboutAppActionPerformed
        // TODO add your handling code here:

        //this.setState(JHome.ICONIFIED);
    }//GEN-LAST:event_jMenu2AboutAppActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        if (!evt.getValueIsAdjusting()) {//This line prevents double events
            System.out.println(jList1.getSelectedValue());
            jScrollPane2.setVisible(true);
            jScrollPane3.setVisible(true);
            jButtonSend.setVisible(true);
            jTextArea1.setText("");
            contact = jList1.getSelectedValue();
            msgCount = 1;
            MsgInWindow ms = new MsgInWindow(this, contact);
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void jButtonSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSendMouseClicked
        // TODO add your handling code here:
        if (!jTextArea2.getText().equals("")) {
            System.out.println("Jhome send message= " + jTextArea2.getText());
            Message message = new Message();
            message.sendMessege(contact, jTextArea2.getText());
            jTextArea2.setText("");
        }
    }//GEN-LAST:event_jButtonSendMouseClicked

    private void JMenuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemLogoutActionPerformed
        // TODO add your handling code here:
        /*Database Database = new Database();
        Database.deletefile();
        JMenuItemLogin.setVisible(true);
        JMenuItemLogout.setVisible(false);
        new JInfoWindow(this).setText("You are logout!\nAll user files is deleted!\n\nPlease RESTART app!");
        jMenuBar1.setVisible(false);
        jScrollPane1.setVisible(false);
        jScrollPane2.setVisible(false);
        jScrollPane3.setVisible(false);
        jLabel1.setEnabled(false);
        jButtonSend.setVisible(false);
        jLabel1.setText("Please RESTART app");*/
        new JConfirmLogout(this);
    }//GEN-LAST:event_JMenuItemLogoutActionPerformed
    private void jMenuItemAddContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddContactActionPerformed
        // TODO add your handling code here:
        new JFindFriends();
    }//GEN-LAST:event_jMenuItemAddContactActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        // TODO add your handling code here:
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProfileActionPerformed
        // TODO add your handling code here:
        new JProfile(this);
    }//GEN-LAST:event_jMenuItemProfileActionPerformed

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
            java.util.logging.Logger.getLogger(JHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JMenuItemLogin;
    private javax.swing.JMenuItem JMenuItemLogout;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenu2AboutApp;
    private javax.swing.JMenuItem jMenu2Update;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAddContact;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemOptions;
    private javax.swing.JMenuItem jMenuItemProfile;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
