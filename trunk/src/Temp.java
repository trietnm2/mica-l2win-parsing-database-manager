
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lelightwin
 */
public class Temp {
    public static void main(String[] args) throws IOException {
        ArrayList<String> label = new ArrayList<String>();
        String filePath = System.getProperty("user.dir")+"/vietnameseParsingRule/vnRule.nr";
        BufferedReader bfr1 = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
        String data = "";
        while ((data = bfr1.readLine())!=null){
            String[] strs = data.split(" ");
            for (int i = 0; i < strs.length-1; i++) {
                String string = strs[i];
                if (!label.contains(string)){
                    label.add(string);
                }
            }
        }
        bfr1.close();
        
        Collections.sort(label);
        String posFilePath = System.getProperty("user.dir")+"/vietnameseParsingRule/POS.dat";
        BufferedWriter bfw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(posFilePath), "utf-8"));
        for (int i = 0; i < label.size(); i++) {
            String string = label.get(i);
            bfw1.write(string);
            bfw1.newLine();
        }
        bfw1.close();
    }
}
