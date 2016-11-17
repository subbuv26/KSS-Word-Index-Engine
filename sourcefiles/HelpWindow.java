//////////////////////////////////////////////////////////////////////////////
// Graphical User Interface Implementation for KSS Word Index Engine        //
// Implementation : Add wizard Window                                       //
// Author : V. Subba Reddy                                                  //
// Reg NO : 11MCMT03                                                        //
//////////////////////////////////////////////////////////////////////////////

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

class HelpWindow extends JFrame {
   private JPanel contentPane;         //decleration of attributes of class
   private JPanel panel;
   private JLabel label;
   

   private JTabbedPane tabbedPane;
   private JEditorPane editorPane1;
   private JEditorPane editorPane2;
   private JEditorPane editorPane3;
   
   /*------------------------------------------------------------------------
   Constructor of AddWindow
   ------------------------------------------------------------------------*/

   HelpWindow() {
      super();
      initialiseComponents();    //initialising components of GUI
      
      this.setSize(new Dimension(315, 360));
      this.setLocation(new Point(300, 200));
      this.setTitle("KSS Word Index Engine Help");
      this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      this.setAlwaysOnTop(true);
      this.setResizable(false);
      this.setVisible(true);
   }
   
   public void initialiseComponents() {
      contentPane = new JPanel();   //creating components
      panel = new JPanel();
      label = new JLabel();
      
   
      tabbedPane = new JTabbedPane();
      editorPane1 = new JEditorPane();
      editorPane2 = new JEditorPane();
      editorPane3 = new JEditorPane();
      
                                 //getting URLs for html pages
      java.net.URL aboutUsURL =
         HelpWindow.class.getResource("./htmlpages/aboutus.html");
      java.net.URL helpURL =
         HelpWindow.class.getResource("./htmlpages/index.html");
      java.net.URL contactUsURL =
         HelpWindow.class.getResource("./htmlpages/contactus.html");
      
                                 //getting skeleton for window
      contentPane = (JPanel) this.getContentPane();
      contentPane.setLayout(null);
      contentPane.setBackground(new Color(255, 255, 100));
      
      label.setLayout(null);
      panel.setLayout(null);
      label.setIcon(new ImageIcon("../images/Titlef.jpg"));
      
                                       //adding components
      addComponent(panel, label, 0, 0, 305, 75);
      
      addComponent(contentPane, panel, 5, 5, 295, 75);
      addComponent(contentPane, tabbedPane, 5, 85, 295, 240);
      
      //adding tabs with editor pane
      
      tabbedPane.add("<html>" +
         "<body text=#0000ff>" +
         "About us" +
         "</body>" +
         "</html>", editorPane1);
      
      tabbedPane.add("<html>" +
         "<body text=#0000ff>" +
         "Help" +
         "</body>" +
         "</html>", editorPane2);
      
      tabbedPane.add("<html>" +
         "<body text=#0000ff>" +
         "Contact us" +
         "</body>" +
         "</html>", editorPane3);
      
      try {
         editorPane1.setPage(aboutUsURL);
      }
      catch( IOException ioe) {
      }
      
      try {
      editorPane2.setPage(helpURL);
      }
      catch( IOException ioe) {
      }
      
      try {
         editorPane3.setPage(contactUsURL);
      }
      catch( IOException ioe) {
      }
      
      editorPane2.addHyperlinkListener(new HyperlinkListener() {
         public void hyperlinkUpdate(HyperlinkEvent hle) {
            performHyperlinkUpdate(hle);
         }
      });
      
      
      editorPane1.setEditable(false);
      editorPane2.setEditable(false);
      editorPane3.setEditable(false);
   }
   
   public void addComponent(Container container, Component component, int x,
   int y, int width, int height) {
      component.setBounds(x, y, width, height);
      container.add(component);
      return;
   }
   
                              //adds component to container
   public void performHyperlinkUpdate(HyperlinkEvent hle) {
      if(hle.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
         try {
            editorPane2.setPage(hle.getURL());
         }
         catch( IOException ioe) {
         }
      }
   }
/*
   public static void main(String args[]) {
      HelpWindow hw = new HelpWindow();
   }   */
}
