//////////////////////////////////////////////////////////////////////////////
// Graphical User Interface Implementation for KSS Word Index Engine        //
// Implementation : Home Window                                             //
// Author : V. Subba Reddy                                                  //
// Reg NO : 11MCMT03                                                        //
//////////////////////////////////////////////////////////////////////////////


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/*----------------------------------------------------------------------------
Home Window of KSS Word Index Engine
----------------------------------------------------------------------------*/
public class HomeWindow extends JFrame {
   private  JPanel contentPane;      //decleration of attributes of class
   private  JPanel panel1;
   private  JPanel panel2;
   private  JPanel panel3;
   private  JPanel panel4;
   private  JLabel label1;
   private  JLabel label2;
   private  JLabel label3;
   private  JFileChooser fileChooser;
   
   private  JScrollPane scrollPane;
   private  JTextArea textArea1;
   public   JTextArea textArea2;
   
   private  JButton searchButton;
   private  JButton addButton;
   private  JButton deleteButton;
   private  JButton queryButton;
   private  JButton quitButton;
   private  JButton clearButton;
   private  JButton helpButton;
   private  JButton parseButton;
   private  Container c;
   public   KSSDictionary dictionary;
   /*------------------------------------------------------------------------
   Constructor of HomeWindow
   ------------------------------------------------------------------------*/
   
   public HomeWindow() {    
      super();
      initialiseComponents();               // initialising components of GUI
      
//      this.setDefaultLookAndFeelDecorated(true);
      this.setLocation(new Point(300, 100));
      this.setSize(new Dimension(665, 490));
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setResizable(false);
      this.setVisible(true);
      dictionary = new KSSDictionary();
   }
   
   public void initialiseComponents() {
      contentPane = new JPanel();      //creating objects for components
      panel1 = new JPanel();
      panel2 = new JPanel();
      panel3 = new JPanel();
      panel4 = new JPanel();
      
      label1 = new JLabel();
      label2 = new JLabel();
      label3 = new JLabel();
      scrollPane = new JScrollPane();
      
      textArea1 = new JTextArea();
      textArea2 = new JTextArea();
      
      searchButton = new JButton("<html>" +
      "<body text=#0000ff>" +
      "<h3>Search</h3>" +
      "</body>" +
      "</html>");
      addButton = new JButton("<html>" +
      "<body text=#0000ff>" +
      "Add Word" +
      "</body>" +
      "</html>");
      deleteButton = new JButton("<html>" +
      "<body text=#0000ff>" +
      "Delete Word" +
      "</body>" +
      "</html>");
      queryButton = new JButton("<html>" +
      "<body text=#0000ff>" +
      "Queries" +
      "</body>" +
      "</html>");
      quitButton = new JButton("<html>" +
      "<body text=#ff0000>" +
      "Quit" +
      "</body>" +
      "</html>");
      clearButton = new JButton("<html>" +
      "<body text=#0000ff>" +
      "Clear" +
      "</body>" +
      "</html>");
      helpButton = new JButton("<html>" +
      "<body text=#007700>" +
      "Help" +
      "</body>" +
      "</html>");
      parseButton = new JButton("<html>" +
      "<body text=#0000ff>" +
      "Open File" +
      "</body>" +
      "</html>");
                                             //getting skeleton for window
      contentPane = (JPanel) this.getContentPane(); 
                                             //setting background colors
      contentPane.setLayout(null);
      contentPane.setBackground(new Color(100, 255, 255));
      panel1.setBackground(new Color(220, 220, 220));      
      panel2.setBackground(new Color(213, 213, 255));
      panel3.setBackground(new Color(213, 213, 255));
      panel4.setBackground(new Color(255, 213, 213));
      
      
                                             //Adding components
      addComponent(contentPane, panel1, 5, 5, 645, 95);
      addComponent(contentPane, panel2, 5, 105, 645, 75);
      addComponent(contentPane, panel3, 5, 185, 195, 270);
      addComponent(contentPane, panel4, 205, 185, 445, 38);
      addComponent(contentPane, scrollPane, 205, 225, 445, 230);
      scrollPane.setViewportView(textArea2);
      
      addComponent(panel1, label1, 0, 0, 655, 95);
      addComponent(panel2, label2, 0, 0, 660, 75);
      addComponent(panel4, label3, 165, 0, 110, 38);
      panel1.setLayout(null);
      panel2.setLayout(null);
      panel3.setLayout(null);
      panel4.setLayout(null);
      
      label1.setIcon(new ImageIcon("../images/Title.JPG"));
      
      label2.setLayout(null);
      label3.setLayout(null);
      
      label3.setText("<html>" +
      "<body font = 20 text=#007700>" +
      "<h1><center>Results</center><h1>" +
      "</body>" +
      "</html>");
      
      textArea2.setFont(new Font("Sans Serif", Font.BOLD, 16));
      textArea2.setForeground(new Color(30, 30, 255));
      
      textArea1.setFont(new Font("Sans Serif", Font.ITALIC, 14));
      textArea1.setForeground(new Color(0, 0, 255));
      
      label2.setFont(new Font("Sans Serif", Font.BOLD, 16));
      label2.setForeground(new Color(255, 0, 255));
      label2.setText("   Enter word : ");
      
      addComponent(label2, textArea1, 150, 27, 150, 20);
      addComponent(label2, searchButton, 320, 20, 100, 30);
      addComponent(label2, clearButton, 440, 20, 100, 30);
      addComponent(label2, helpButton, 560, 10, 80, 20);
      
      addComponent(panel3, parseButton, 30, 30, 130, 25);
      addComponent(panel3, addButton, 30, 75, 130, 25);
      addComponent(panel3, deleteButton, 30, 120, 130, 25);
      addComponent(panel3, queryButton, 30, 165, 130, 25);
      addComponent(panel3, quitButton, 30, 210, 130, 25);
      
      textArea1.setEditable(true);
      textArea1.setBackground(new Color(255, 255, 255));
      
      textArea2.setBackground(new Color(250, 255, 250));
      textArea2.setBorder(BorderFactory.createRaisedBevelBorder());
      textArea2.setEditable(false);
                                             //setting tool tips
      searchButton.setToolTipText("To search some word in the Dictionary");
      parseButton.setToolTipText("To open a new file to update " + 
          "Dictionary");
      addButton.setToolTipText("To add new word into the Dictionary");
      deleteButton.setToolTipText("To delete word from Dictionary");
      queryButton.setToolTipText("To pose frequency related Queries");
      quitButton.setToolTipText("To Quit from the application");
      clearButton.setToolTipText("To clear the Display");
      helpButton.setToolTipText("For more Help");
      
      this.setTitle("KSS Word Index Engine");
      
                        //adding action listeners
      
      searchButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            searchButton_actionPerformed(e);
         }
      });
      
      parseButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            parseButton_actionPerformed(e);
         }
      });
      
      addButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            addButton_actionPerformed(e);
         }
      });
      
      deleteButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            deleteButton_actionPerformed(e);
         }
      });
      
      queryButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            queryButton_actionPerformed(e);
         }
      });
      
      quitButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            quitButton_actionPerformed(e);
         }
      });
      
      clearButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            clearButton_actionPerformed(e);
         }
      });
      
      helpButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            helpButton_actionPerformed(e);
         }
      });
      
      textArea1.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            enterKey_actionPerformed(ke);
         }
      });
      
   }
   
                     //adds components to container
   public void addComponent(Container container, Component component, int x,
   int y, int width, int height ) {
      component.setBounds(x, y, width, height);
      container.add(component);
      return;
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
   
   
   private void searchFunction() {
      String str = "";
      Item item = new Item();
      str = textArea1.getText();
      str = str.trim();
      textArea1.setText(null);
      textArea1.setText(str);
      if(isString(str) == false || str.equals("")) {
         JDialog.setDefaultLookAndFeelDecorated(true);
         JOptionPane.showMessageDialog(null, "Enter word only");
      }
      else {
         item = dictionary.search(str);
         if(item == null)
            textArea2.setText("No results");
         else {
            textArea2.setText("Word\t: " + item.word + "\n");
            textArea2.append("Frequency\t: " + item.frequency + "\n");
         }
      }
      
   }
   
                              //implementing acton listeners
   public void enterKey_actionPerformed(KeyEvent ke) {
      int key = ke.getKeyCode();
      if(key == KeyEvent.VK_ENTER){
         searchFunction();
      }
   }
   
   private void searchButton_actionPerformed(ActionEvent e) {
      searchFunction();
   }
   
   private void parseButton_actionPerformed(ActionEvent e) {
      fileChooser = new JFileChooser();
      int rval = fileChooser.showOpenDialog(this);
      
      if(rval == JFileChooser.APPROVE_OPTION) {
         File file = fileChooser.getSelectedFile();
         
         dictionary = new KSSDictionary();
         Parse parse = new Parse(dictionary);
         rval = parse.parse(file.getAbsolutePath());
         if(rval == 0)
            textArea2.setText("\n       \"Dictionary is updated\"\n\n" + 
            "       From : " + file.getName());
         else
            textArea2.setText("\n          \"Dictionary isn't' updated\"\n\n"
               + "File is empty or it contains no valid word");
      }
   }
   
   private void addButton_actionPerformed(ActionEvent e) {
      AddWindow aw = new AddWindow(this, dictionary); // creating add window
      this.setEnabled(false); //to make home window not to work
   }
   
   private void deleteButton_actionPerformed(ActionEvent e) {
      DeleteWindow dw = new DeleteWindow(this, dictionary); //creating delete
                                                            //window
      this.setEnabled(false); //to make home window not to work
   }
   
   private void queryButton_actionPerformed(ActionEvent e) {
      QueryWindow qw = new QueryWindow(this, dictionary); //creating query
                                                         //window
      this.setEnabled(false); //to make home window not to work
   }
   
   private void quitButton_actionPerformed(ActionEvent e) {
      JDialog.setDefaultLookAndFeelDecorated(true);
      int response = JOptionPane.showConfirmDialog(null, "Do you really" + 
      " want to quit?", "Confirm",
      JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (response == JOptionPane.YES_OPTION) {
         System.exit(0);
      }
//      else if (response == JOptionPane.NO_OPTION) {
//      }
//      else if (response == JOptionPane.CLOSED_OPTION) {
//      }
   }
   
   private void clearButton_actionPerformed(ActionEvent e) {
      textArea1.setText(null); //clears text entering area
      textArea2.setText(null); //clears display area
   }
   
   private void helpButton_actionPerformed(ActionEvent e) {
      HelpWindow helpw = new HelpWindow();
   }
   
   
   /* Creating HomeWindow object */
   public static void main(String args[]) {
      
      HomeWindow hw = new HomeWindow();
   }
   
}
