//////////////////////////////////////////////////////////////////////////////
// Graphical User Interface Implementation for KSS Word Index Engine        //
// Implementation : Add wizard Window                                       //
// Author : V. Subba Reddy                                                  //
// Reg NO : 11MCMT03                                                        //
//////////////////////////////////////////////////////////////////////////////

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddWindow extends JFrame {
   private  JPanel contentPane;         //decleration of attributes of class
   private  JPanel panel1;
   private  JPanel panel2;
   
   private  JLabel label1;
   private  JLabel label2;
   private  JLabel label3;
   private  JLabel label4;
   
   private  JTextArea textArea;
   
   private  JButton addButton;
   private  JButton cancelButton;
   
   private  Container c;
   private  HomeWindow hw;               // to access home window
   public   KSSDictionary dictionary;
   
   /*------------------------------------------------------------------------
   Constructor of AddWindow
   ------------------------------------------------------------------------*/
   
   AddWindow(HomeWindow hw, KSSDictionary dictionary) {
      super();
      this.hw = hw;
      this.dictionary = dictionary;
      initialiseComponents();    //initialising components of GUI
      
      this.setAlwaysOnTop(true);
      this.setLocation(new Point(400, 220));
      this.setSize(new Dimension(355, 255));
      this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      this.setResizable(false);
      this.setVisible(true);
   }
   public void initialiseComponents() {
      contentPane = new JPanel();   //creating components
      panel1 = new JPanel();
      panel2 = new JPanel();
      
      label1 = new JLabel();
      label2 = new JLabel();
      label3 = new JLabel();
      label4 = new JLabel();
      
      textArea = new JTextArea();
      
      addButton = new JButton("<html>" +
      "<body text=#0000ff>" +
      "Add" +
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
   
   
                                    //adding components
      addComponent(contentPane, panel1, 5, 5, 335, 60);
      addComponent(contentPane, panel2, 5, 70, 335, 150);
      
      addComponent(panel1, label1, 0, 0, 335, 80);
      addComponent(panel2, label2, 15, 15, 300, 25);
      addComponent(panel2, label3, 15, 40, 300, 25);
      addComponent(panel2, label4, 15, 65, 110, 20);
      
      label2.setText("KSS Word Index Engine welcomes you");
      label3.setText("to add new words");
      label4.setText("Enter Word : ");
      addComponent(panel2, textArea, 120, 65, 160, 20);
      addComponent(panel2, addButton, 80, 105, 100, 30);
      addComponent(panel2, cancelButton, 210, 105, 100, 30);
      
      panel1.setLayout(null);
      panel2.setLayout(null);
      label1.setLayout(null);
      label2.setLayout(null);
      label3.setLayout(null);
      label4.setLayout(null);
      
      label1.setIcon(new ImageIcon("../images/Titlead.jpg"));
      
      textArea.setEditable(true);
      
      this.setTitle("Add a word to Dictionary");
                  //adding action listeners
      addButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            addButton_actionPerformed(e);
         }
      });
      
      cancelButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            cancelButton_actionPerformed(e);
         }
      });
      
      textArea.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            enterKey_actionPerformed(ke);
         }
      });
      
   }
   
                  //adds component to container
   public void addComponent(Container container, Component component, int x,
   int y, int width, int height ) {
      component.setBounds(x, y, width, height);
      container.add(component);
      return;
   }
   
   private void addButton_actionPerformed(ActionEvent e) {
      String str = "";
      str = textArea.getText();
      str = str.trim();
      
      validateAndAddItem(str);
   }
   
   
   private void cancelButton_actionPerformed(ActionEvent e) {
      textArea.setText(null);
      this.setVisible(false);
      hw.setEnabled(true);
   }
   
   private void enterKey_actionPerformed(KeyEvent ke) {
      int key = ke.getKeyCode();
      if(key == KeyEvent.VK_ENTER){
         String str = "";
         
         str = textArea.getText();
         str = str.trim();
         
         validateAndAddItem(str);
      }
   }

   private void validateAndAddItem(String str) {
      Item item = new Item();
      if(isString(str) == false || str.equals("")) {
         this.setAlwaysOnTop(false);
         JDialog.setDefaultLookAndFeelDecorated(true);
         JOptionPane.showMessageDialog(null, "Enter word only");
         this.setAlwaysOnTop(true);
      }
      else {
         item.word = str;
         item.frequency = 1;
         item = dictionary.addItem(item);
         if(item != null) { 
            hw.textArea2.setText("The word \"" + str +
            "\" is added successfully\n");
            hw.textArea2.append("Word\t: " + item.word + "\n");
            hw.textArea2.append("Frequency\t: " + item.frequency + "\n");
            
            if(item.frequency == 1) {
               hw.textArea2.append("The word is newly added\n");
            }
         }
         else
            hw.textArea2.setText("Sorry!!!\nThe word \"" + str +
            "\" isn't added. Because\nit has maximum frequency: " + 
               Integer.MAX_VALUE);
         textArea.setText(null);
         this.setVisible(false);
         hw.setEnabled(true);
         
      }
      
   }
   
   boolean isString(String str) {
      str = str.toUpperCase();
      int i, c;
      for(i = 0; i < str.length(); i++) {
         c = (int) str.charAt(i);
         if(c < 65 || c > 90)
            return false;
      }
      return true;
   }
   
   
   
/*   public static void main(String args[]) {
      new AddWindow(new HomeWindow());
   } */
}
