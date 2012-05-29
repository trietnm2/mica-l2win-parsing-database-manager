
import java.io.*;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author lelightwin
 */
public class Temp2 {

    public static void main(String[] args) throws IOException {
        String vlspXMLDir = System.getProperty("user.dir") + "/vlspXMLCorpus/";
        String outputDir = System.getProperty("user.dir") + "/vlspXMLCorpus_Refined/";
        File[] xmlFiles = new File(vlspXMLDir).listFiles();
        int fileCount = 0;
        String outputFile = outputDir + "vlsp0" + fileCount + ".xml";
        BufferedWriter bfw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
        for (int i = 0; i < xmlFiles.length; i++) {
            File file = xmlFiles[i];
            ArrayList<String> strArr = new ArrayList<String>();
            BufferedReader bfr1 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String data = "";
            while ((data = bfr1.readLine())!=null){
                strArr.add(data);
            }
            strArr.remove(0);
            strArr.remove(0);
            strArr.remove(strArr.size()-1);
            bfr1.close();
        }
        bfw1.close();
    }
}