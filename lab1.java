import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class HW{
     
    static boolean isOperand(char x){
        return (x >= 'a' && x <= 'z') ||
                (x >= 'A' && x <= 'Z');
    }
    static boolean isOperator(char x){
        return (x == '+') || (x == '-') ||(x == '*') ||(x == '/') ||(x == '$');
    }

    public static void main(String args[]) throws IOException{

        InputStream is = null; 
        InputStreamReader isr = null;
        BufferedReader br = null;

        Stack<String> s = new Stack<String>();
        int count = 1;

        FileWriter myWriter = new FileWriter("output.txt");

        try{
            // open input stream test.txt for reading purpose.
            is = new FileInputStream("D:/Downloads/input.txt");
            // create new input stream reader
            isr = new InputStreamReader(is);
            // create new buffered reader
            br = new BufferedReader(isr);
            int value=0;
            
            // reads to the end of the stream 
            while((value = br.read()) != -1){
                if(value == '\n'){
                    //next input line
                    System.out.println('\n');
                    myWriter.write("\n");
                    count = 1;
                    continue;
                }
                // converts int to character
                char exp = (char)value;

                if(isOperand(exp)==false && isOperator(exp)==false){
                    myWriter.write("Encounter invalid input, terminate the program");
                    break;
                }

                // Push operands
                if (isOperand(exp)){
                    s.push(exp+ "");
                }
        
                else{
                    char operator = exp;
                    String op1 = s.peek();
                    s.pop();
                    String op2 = s.peek();
                    s.pop();
                    System.out.println("LD "+op2);
                    
                    myWriter.write("LD "+op2);
                    myWriter.write("\n");

                    switch(operator){
                        case '+':
                            System.out.println("AD "+op1);
                            myWriter.write("AD "+op1);
                            break;
                        case '-':
                            System.out.println("SB "+op1);
                            myWriter.write("SB "+op1);
                            break;
                        case '*':
                            System.out.println("ML "+op1);
                            myWriter.write("ML "+op1);
                            break;
                        case '/':
                            System.out.println("DV "+op1);
                            myWriter.write("DV "+op1);
                            break;
                        case '$':
                            System.out.println("EXP"+op1);
                            myWriter.write("EXP"+op1);
                        break;
                    }

                    String temp = "TEMP"+count;
                    //s.push("(" + op2 + exp.charAt(i) + op1 + ")");
                    s.push(temp);
                    
                    System.out.println("ST TEMP"+count);
                    myWriter.write("\n");
                    myWriter.write("ST TEMP"+count);
                    myWriter.write("\n");
                    count++;
                }
            }
         }catch(Exception e){
            e.printStackTrace();
         }finally{
            myWriter.close();
         }
    }
}