//////////////////////////////////////////////////////////////////////////////
// Graphical User Interface Implementation for KSS Word Index Engine        //
// Implementation : Queries wizard Window                                   //
// Author : V. Subba Reddy                                                  //
// Reg NO : 11MCMT03                                                        //
//////////////////////////////////////////////////////////////////////////////

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class QueryWindow extends JFrame {
   private  JPanel contentPane;         //decleration of attributes of clas
   private  JPanel panel1;
   private  JPanel panel2;
   
   private  JLabel label1;
   private  JLabel label2;
   private  JLabel label3;
   private  JLabel label4;
   
   private  JTextArea textArea;
   
   private  JButton query1Button;
   private  JButton query2Button;
   private  JButton query3Button;
   private  JButton cancelButton;
   private  HomeWindow hw;               // to access home window
   public   KSSDictionary dictionary;
   
   /*------------------------------------------------------------------------
   Constructor of AddWindow
   ------------------------------------------------------------------------*/
   QueryWindow(HomeWindow hw,KSSDictionary dictionary) {
      super();
      this.hw = hw;
      this.dictionary = dictionary;
      initialiseComponents();            //initialising components of GUI
      this.setTitle("Queries Window");
      this.setSize(new Dimension(315, 400));
      this.setLocation(new Point(400, 220));
      this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      this.setAlwaysOnTop(true);
      this.setResizable(false);
      this.setVisible(true);
   }
   
   public void initialiseComponents() {
      contentPane = new JPanel();      //creating components
      panel1 = new JPanel();
      panel2 = new JPanel();
      
      label1 = new JLabel();
      label2 = new JLabel();
      label3 = new JLabel();
      label4 = new JLabel();
      
      textArea = new JTextArea();
      
      query1Button = new JButton("<html>" +
      "<body text=#0000ff>" +
      "Words with frequency" +
      "</body>" +
      "</html>");
      query2Button = new JButton("<html>" +
      "<body text=#0000ff>" +
      "Top frequency words" +
      "</body>" +
      "</html>");
      query3Button = new JButton("<html>" +
      "<body text=#0000ff>" +
      "Least frequency words" +
      "</body>" +
      "</html>");
      cancelButton = new JButton("<html>" +
      "<body text=#0000ff>" +
      "Cancel" +
      "</body>" +
      "</html>");
      
                                    //getting skeleton for window
      contentPane = (JPanel) this.getContentPane();
      contentPane.setLayout(null);
                                    //setting backgrounds
      contentPane.setBackground(new Color(255, 255, 100));
      panel2.setBackground(new Color(150, 255, 255));
      
      panel1.setLayout(null);
      panel2.setLayout(null);
      label1.setLayout(null);
      label2.setLayout(null);
      label3.setLayout(null);
      label4.setLayout(null);
      
                                       //adding components
      addComponent(contentPane, panel1, 5, 5, 295, 75);
      
      addComponent(contentPane, panel2, 5, 85, 295, 280);
      
      addComponent(panel1, label1, 0, 0, 295, 95);
      addComponent(panel2, label2, 5, 5, 290, 20);
      addComponent(panel2, label3, 5, 30, 270, 20);
      addComponent(panel2, label4, 5, 65, 130, 20);
      
      addComponent(panel2, textArea, 145, 65, 120, 20);
      
      addComponent(panel2, query1Button, 45, 105, 200, 25);
      addComponent(panel2, query2Button, 45, 150, 200, 25);
      addComponent(panel2, query3Button, 45, 195, 200, 25);
      addComponent(panel2, cancelButton, 165, 240, 100, 25);
      
      label1.setIcon(new ImageIcon("../images/Titlef.jpg"));
      label2.setText("KSS Word Index Engine welcomes you");
      label3.setText("to pose frequency based queries");
      label4.setText("Enter Number : ");
      
      
      query1Button.setToolTipText("Finds the words which have the\n frequency"
      + " given by you");
      
      query2Button.setToolTipText("Finds the which are ranked top in terms"
      + "of frequency");
      
      query3Button.setToolTipText("Finds the which are ranked least in terms"
      + "of frequency");
      
      
                                    // adding action listeners
      query1Button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            query1Button_actionPerformed(e);
         }
      });
      
      textArea.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            enterKey_actionPerformed(ke);
         }
      });
      
      query2Button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            query2Button_actionPerformed(e);
         }
      });
      
      query3Button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            query3Button_actionPerformed(e);
         }
      });
      
      cancelButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            cancelButton_actionPerformed(e);
         }
      });
      
   }
   
                                 //adds component to container
   public void addComponent(Container container, Component component, int x,
   int y, int width, int height) {
      component.setBounds(x, y, width, height);
      container.add(component);
      return;
   }
   
   public void enterKey_actionPerformed(KeyEvent ke) {
      int key = ke.getKeyCode();
      if(key == KeyEvent.VK_ENTER){
         frequencyFunction();
      }
   }
   
   private void query1Button_actionPerformed(ActionEvent e) {
      frequencyFunction();
   }
   
   private void frequencyFunction() {
      ArrayList<Item> list = new ArrayList<Item>();
      String str = "";
      int i;
      str = textArea.getText();
      str = str.trim();
      if(isNumber(str) == true && !str.equals("")) {
         try {
            i = Integer.parseInt(str);
         }
         catch(NumberFormatException nfe) {
            this.setAlwaysOnTop(false);
            JDialog.setDefaultLookAndFeelDecorated(true);
            JOptionPane.showMessageDialog(null, "Enter valid Number");
            this.setAlwaysOnTop(true);
            this.textArea.setText(null);
            return;
         }
         
         list = dictionary.wordsWithFrequency(i);
         
         if(list.size() != 0) {
            hw.textArea2.setText("Words with frequency " + i + 
            "are\n\n");
            hw.textArea2.append("Frequency\tWord\n");
            hw.textArea2.append("----------------\t--------\n");
            
            for(i = 0; i < list.size(); i++) {
               hw.textArea2.append(list.get(i).frequency + "\t");
               hw.textArea2.append(list.get(i).word + "\n");
            }
         }
         else
            hw.textArea2.setText("No words with frequency " + i + 
               " in Dictionary\n");
         
         
         this.textArea.setText(null);
         hw.setEnabled(true);
         this.setVisible(false);
      }
      else {
         this.setAlwaysOnTop(false);
         JDialog.setDefaultLookAndFeelDecorated(true);
         JOptionPane.showMessageDialog(null, "Enter Number only");
         this.setAlwaysOnTop(true);
      }
   }
   
   private void query2Button_actionPerformed(ActionEvent e) {
      ArrayList<Item> list = new ArrayList<Item>();
      String str = "";
      int i, number, newFreq = 0, count = 0;
      str = textArea.getText();
      str = str.trim();
      if(isNumber(str) == true && !str.equals("")) {
         try {
            number = Integer.parseInt(str);
         }
         catch(NumberFormatException nfe) {
            this.setAlwaysOnTop(false);
            JDialog.setDefaultLookAndFeelDecorated(true);
            JOptionPane.showMessageDialog(null, "Enter valid Number");
            this.setAlwaysOnTop(true);
            this.textArea.setText(null);
            return;
         }
         
         list = dictionary.topFrequencyWords(number);
         if(list.size() != 0) {
            for(i = 0; i < list.size(); i++)
               if(newFreq != list.get(i).frequency) {
                  newFreq = list.get(i).frequency;
                  count++;
               }
               
            if(number != count) {
               hw.textArea2.setText("There are no Top " + number + 
                  " frequencies\n");
               
               hw.textArea2.append("Top existing " + count + 
                     " (only) frequency words are\n\n");
            }
            else
               hw.textArea2.setText("Top " + number + 
                  " frequency words are\n\n");
            hw.textArea2.append("Frequency\tWord\n");
            hw.textArea2.append("----------------\t--------\n");
            for(i = 0; i < list.size(); i++) {
               hw.textArea2.append(list.get(i).frequency + "\t");
               hw.textArea2.append(list.get(i).word + "\n");
            }
         }
         else
            hw.textArea2.setText("The Dictionary is empty\n");
         
         
         this.textArea.setText(null);
         hw.setEnabled(true);
         this.setVisible(false);
      }
      else {
         this.setAlwaysOnTop(false);
         JDialog.setDefaultLookAndFeelDecorated(true);
         JOptionPane.showMessageDialog(null, "Enter Number only");
         this.setAlwaysOnTop(true);
      }
   }
   
   private void query3Button_actionPerformed(ActionEvent e) {
      ArrayList<Item> list = new ArrayList<Item>();
      String str = "";
      int i, number, newFreq = 0, count = 0;
      str = textArea.getText();
      str = str.trim();
      if(isNumber(str) == true && !str.equals("")) {
         try {
            number = Integer.parseInt(str);
         }
         catch(NumberFormatException nfe) {
            this.setAlwaysOnTop(false);
            JDialog.setDefaultLookAndFeelDecorated(true);
            JOptionPane.showMessageDialog(null, "Enter valid Number");
            this.setAlwaysOnTop(true);
            this.textArea.setText(null);
            return;
         }
         list = dictionary.leastFrequencyWords(number);
         
         if(list.size() != 0) {
            for(i = 0; i < list.size(); i++)
               if(newFreq != list.get(i).frequency) {
                  newFreq = list.get(i).frequency;
                  count++;
               }
               
            if(number != count) {
               hw.textArea2.setText("There are no Least " + number + 
               " frequencies\n");
                  
               hw.textArea2.append("Least existing " + count + 
                  " (only) frequency words are\n\n");
            }
            else
               hw.textArea2.setText("Least " + number + 
               " frequency words are\n\n");
            hw.textArea2.append("Frequency\tWord\n");
            hw.textArea2.append("----------------\t--------\n");
            for(i = 0; i < list.size(); i++) {
               hw.textArea2.append(list.get(i).frequency + "\t");
               hw.textArea2.append(list.get(i).word + "\n");
            }
         }
         else
            hw.textArea2.setText("The Dictionary is empty\n");
         
         
         
         this.textArea.setText(null);
         hw.setEnabled(true);
         this.setVisible(false);
      }
      else {
         this.setAlwaysOnTop(false);
         JDialog.setDefaultLookAndFeelDecorated(true);
         JOptionPane.showMessageDialog(null, "Enter Number only");
         this.setAlwaysOnTop(true);
      }
   }
   
   private void cancelButton_actionPerformed(ActionEvent e) {
      this.textArea.setText(null);
      hw.setEnabled(true);
      this.setVisible(false);
   }
   
   boolean isNumber(String str) {
      if(str.matches("\\d+") == false)
         return false;
      else
         return true;
   }
   
/*   public static void main(String args[]) {
      new QueryWindow();
   } */
}

