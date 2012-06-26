package Temp;


import java.io.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lelightwin
 */
public class Temp6 {
    public static void main(String[] args) throws IOException {
        String inputFile = System.getProperty("user.dir")+"/vlspXMLCorpus/Backup/newTrainingCorpus.xml";
        String outputFile = System.getProperty("user.dir")+"/vlspXMLCorpus/vlsp.xml";
        
        BufferedReader bfr1 = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "utf-8"));
        BufferedWriter bfw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
        
        String data = "";
        while ((data = bfr1.readLine())!=null){
            bfw1.write(data.trim());
        }
        
        bfw1.close();
        bfr1.close();
    }
}
