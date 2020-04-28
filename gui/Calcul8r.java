// just a gui calculator

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class Calcul8r extends JFrame implements ActionListener {
    JPanel mainPanel, buttonPanel;
    JTextField disp;
    JButton button;
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
        setSize(500,400);
        setTitle("Calcul8r");
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
        buttonPanel = new JPanel(new GridLayout(8,4));
        buttonPanel.setBackground(new java.awt.Color(0, 0, 0));
        // add buttons to panel
        String[] buttons = {"x^2", "x^y", "sqrt", "rt(y)", "log", "%", "pi", "e", "sin", "cos", "tan", "(-)",
                            "arcsin", "arccos", "arctan", "!",
                            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+","-","*","/","clear","="};
        for(int j=0; j<buttons.length; j++){
            button = new JButton(buttons[j]);
            buttonPanel.add(button);
            button.setFont(new java.awt.Font(defaultFont, 1, 20));
            if(j>=0 && j<=9){
                button.setBackground(new java.awt.Color(140, 140, 140));
            }else if(j>=9 && j<=15){
                button.setBackground(new java.awt.Color(100, 100, 100));
            }else{
                button.setBackground(new java.awt.Color(60, 80, 80));
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
        if((inputStr.charAt(0) >= '0' && inputStr.charAt(0) <= '9') || inputStr.charAt(0) == 'p' || inputStr.charAt(0) == 'e'){
            if(!operand.equals("")){
                secondNumber = secondNumber+inputStr;
                disp.setText(operand+secondNumber);
                // to continue number input after evaluation, first number = the previus answer
                if(firstNumber.equals("")){
                    firstNumber = Double.toString(answer);
                    disp.setText(operand+secondNumber);
                }
                if(secondNumber.equals("pi")){
                    secondNumber = Double.toString(Math.PI);
                }
                if(secondNumber.equals("e")){
                    secondNumber = Double.toString(Math.E);
                }
            }else{
                firstNumber = firstNumber+inputStr;
                disp.setText(firstNumber);
                if(firstNumber.equals("pi")){
                    firstNumber = Double.toString(Math.PI);
                }
                if(firstNumber.equals("e")){
                    firstNumber = Double.toString(Math.E);
                }
            }
        // get operand input
        }else if(inputStr.charAt(0)=='c' && inputStr.charAt(1)=='l'){
            firstNumber=operand=secondNumber="";
            disp.setText("");
        }else if(inputStr.charAt(0)=='+'){
            operand = "+";
        }else if(inputStr.charAt(0)=='-'){
            operand = "-";
        }else if(inputStr.charAt(0)=='*'){
            operand = "*";
        }else if(inputStr.charAt(0)=='/'){
            operand = "/";
        }else if(inputStr.charAt(0)=='x' && inputStr.charAt(2)=='y'){
            operand = "^";
        }else if(inputStr.charAt(0)=='x'){
            operand = "x^2";
            disp.setText("^2");
        }else if(inputStr.charAt(0)=='s' && inputStr.charAt(1)=='i'){
            operand = "sin";
            disp.setText("sin(" + firstNumber + ")");
        }else if(inputStr.charAt(0)=='s'){
            operand = "sqrt";
            disp.setText("sqrt(" + firstNumber + ")");
        }else if(inputStr.charAt(0)=='r'){
            operand = "rt";
        }else if(inputStr.charAt(0)=='%'){
            operand = "%";
        }else if(inputStr.charAt(0)=='l'){
            operand = "log";
        }else if(inputStr.charAt(0)=='c' && inputStr.charAt(1)=='o'){
            operand = "cos";
            disp.setText("cos(" + firstNumber + ")");
        }else if(inputStr.charAt(0)=='t'){
            operand = "tan";
            disp.setText("tan(" + firstNumber + ")");
        }else if(inputStr.charAt(0)=='('){
            double negativeFirstNumber = 0 - Double.parseDouble(firstNumber);
            firstNumber = Double.toString(negativeFirstNumber);
            disp.setText(firstNumber);
        }else if(inputStr.charAt(0)=='a' && inputStr.charAt(3)=='s'){
            operand = "arcsin";
            disp.setText("arcsin(" + firstNumber + ")");
        }else if(inputStr.charAt(0)=='a' && inputStr.charAt(3)=='c'){
            operand = "arccos";
            disp.setText("arccos(" + firstNumber + ")");
        }else if(inputStr.charAt(0)=='a' && inputStr.charAt(3)=='t'){
            operand = "arctan";
            disp.setText("arctan(" + firstNumber + ")");
        }else if(inputStr.charAt(0)=='!'){
            operand = "!";
        }if(inputStr.charAt(0)=='='){
            // calculate and display
            calculations();
            displayAnswer();
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
            if((Double.parseDouble(secondNumber))==0.0){
                answer=0;
            }else{
                answer = (Double.parseDouble(firstNumber)/Double.parseDouble(secondNumber));
            }
        }else if(operand=="x^2"){
            answer = (Double.parseDouble(firstNumber)*Double.parseDouble(firstNumber));
        }else if(operand=="^"){
            answer = Math.pow(Double.parseDouble(firstNumber),Double.parseDouble(secondNumber));
        }else if(operand=="sqrt"){
            if((Double.parseDouble(firstNumber))<0.0){
                answer=0;
            }else{
                answer = Math.sqrt(Double.parseDouble(firstNumber));
            }
        }else if(operand=="rt"){
            if((Double.parseDouble(secondNumber))<0.0){
                answer=0;
            }else{
                answer = Math.pow(Double.parseDouble(firstNumber), 1/Double.parseDouble(secondNumber));
            }
        }else if(operand=="%"){
            answer = (Double.parseDouble(firstNumber)%Double.parseDouble(secondNumber));
        }else if(operand=="log"){
            if((Double.parseDouble(firstNumber))<=0.0){
                answer=0;
            }else{
                answer = Math.log(Double.parseDouble(firstNumber));
            }
        }else if(operand=="sin"){
            answer = Math.sin(Double.parseDouble(firstNumber));
        }else if(operand=="cos"){
            answer = Math.cos(Double.parseDouble(firstNumber));
        }else if(operand=="tan"){
            answer = Math.tan(Double.parseDouble(firstNumber));
        }else if(operand=="arcsin"){
            answer = Math.asin(Double.parseDouble(firstNumber));
        }else if(operand=="arcos"){
            answer = Math.acos(Double.parseDouble(firstNumber));
        }else if(operand=="arctan"){
            answer = Math.atan(Double.parseDouble(firstNumber));
        }else if(operand=="!"){
            answer = 1;
            for(double i=Double.parseDouble(firstNumber); i>1; i--){
                answer = answer*i;
            }
        }
        return answer;
    }

    public void displayAnswer(){
        String exp = firstNumber+operand+secondNumber;
        // round if .0
        if((answer == Math.floor(answer)) && !Double.isInfinite(answer)){
            disp.setText(exp+"="+Math.round(answer));
        }else{
            disp.setText(exp+"="+answer);
        }
        // reset all 
        firstNumber=operand=secondNumber="";
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



