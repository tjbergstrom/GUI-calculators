// Ty Bergstrom
// CSCE222
// November 12 2018
// homework assignment 4 - calculator

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;
            
public class Calcul8r extends JFrame implements ActionListener {
    JTextField disp;
    JButton button;
    JPanel mainPanel;
    JPanel buttonPanel;
    double answer;
    String firstNumber, operand, secondNumber;
    String defaultFont = "Gungsuh";
    
    public Calcul8r(){
        firstNumber=operand=secondNumber="";
        gooey();
    }
    
    // GUI interface, called in the constructor
    public void gooey() {
        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
        setVisible(true);
        setResizable(true);
        setSize(600,400);
        setTitle("Ty's Calcul8r");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 150));
        mainPanel.setBackground(new java.awt.Color(0, 0, 0));
        // text field to display input and answer
        disp = new JTextField(400);
        disp.setEditable(false);
        disp.setFont(new java.awt.Font(defaultFont, 0, 44));
        disp.setHorizontalAlignment(SwingConstants.RIGHT);
        disp.setBackground(new java.awt.Color(220, 220, 220));
        // panel for button grid
        buttonPanel = new JPanel(new GridLayout(4,4));
        buttonPanel.setBackground(new java.awt.Color(0, 0, 0));
        // add buttons to panel
        String[] buttons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+","-","*","/","=","c"};
        for(int j=0; j<buttons.length; j++){
            button = new JButton(buttons[j]);
            buttonPanel.add(button);
            button.setFont(new java.awt.Font(defaultFont, 1, 28));
            if(j>=0 && j<=9){
                button.setBackground(new java.awt.Color(140, 140, 140));
            }else{
                button.setBackground(new java.awt.Color(110, 110, 110));
            }
            button.addActionListener(this);
        }
        // place text field above button panel, both in the main panel
        mainPanel.add(disp, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    }
    
    // get input
    public void actionPerformed(ActionEvent event){
        String inputStr = event.getActionCommand();
        // get numbers input
        if(inputStr.charAt(0) >= '0' && inputStr.charAt(0) <= '9'){
            if(!operand.equals("")){
                secondNumber = secondNumber+inputStr;
                disp.setText(operand+secondNumber);
                // to continue number input after evaluation, first number = the previus answer
                if(firstNumber.equals("")){
                    firstNumber = Double.toString(answer);
                    disp.setText(operand+secondNumber);
                }
            }else{
                firstNumber = firstNumber+inputStr;
                disp.setText(firstNumber);
            }
        // clear button
        }else if(inputStr.charAt(0)=='c'){
            firstNumber=operand=secondNumber="";
            disp.setText("");
        // get operand input
        }else if(inputStr.charAt(0)=='+'){
            operand = "+";
        }else if(inputStr.charAt(0)=='-'){
            operand = "-";
        }else if(inputStr.charAt(0)=='*'){
            operand = "*";
        }else if(inputStr.charAt(0)=='/'){
            operand = "/";
        }if(inputStr.charAt(0)=='='){
            // calculate and display
            calculations();
            displayAnswer();
            // reset all 
            firstNumber=operand=secondNumber="";
        }
    }
    
    public double calculations(){
        if(operand=="+"){
            answer = (Double.parseDouble(firstNumber)+Double.parseDouble(secondNumber));
        }else if(operand=="-"){
            answer = (Double.parseDouble(firstNumber)-Double.parseDouble(secondNumber));
        }else if(operand=="*"){
            answer = (Double.parseDouble(firstNumber)*Double.parseDouble(secondNumber));
        }else if(operand=="/"){
            // divide by zero error
            if((Double.parseDouble(secondNumber))==0.0){
                answer = 0;
            }else{
                answer = (Double.parseDouble(firstNumber)/Double.parseDouble(secondNumber));
            }
        }
        return answer;
    }
    
    public void displayAnswer(){
        // round if .0
        if((answer == Math.floor(answer)) && !Double.isInfinite(answer)){
            disp.setText("="+Math.round(answer));
        }else{
            disp.setText("="+answer);
        }
    }
    
    public static void main(String args[]){
        // Nimbus Look and Feel
        // Delete this try catch block if Nimbus is not available to you
        try{
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(ClassNotFoundException ex){
            java.util.logging.Logger.getLogger(Calcul8r.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch(InstantiationException ex){
            java.util.logging.Logger.getLogger(Calcul8r.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch(IllegalAccessException ex){
            java.util.logging.Logger.getLogger(Calcul8r.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch(javax.swing.UnsupportedLookAndFeelException ex){
            java.util.logging.Logger.getLogger(Calcul8r.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Delete the above try catch block if Nimbus is unavailable
        
        Calcul8r calc = new Calcul8r();
    }
}