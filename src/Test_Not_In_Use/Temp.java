/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Test_Not_In_Use;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author lelightwin
 */
public class Temp {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw1 = new PrintWriter("C:/Thang.txt");
        pw1.println("oi troi oi");
        pw1.close();
        PrintWriter pw2 = new PrintWriter("C:/Thang.txt");
        pw2.println("oi dat hoi");
        pw2.close();
    }
}
