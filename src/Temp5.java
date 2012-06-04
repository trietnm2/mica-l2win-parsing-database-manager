
import java.io.*;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author lelightwin
 */
public class Temp5 {

    public static void main(String[] args) throws IOException {
        String inputFile = System.getProperty("user.dir") + "/vlspXMLCorpus_Refined/trainingCorpus.xml";
        String outputFile = System.getProperty("user.dir") + "/vlspXMLCorpus_Refined/newTrainingCorpus.xml";

        BufferedReader bfr1 = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "utf-8"));
        BufferedWriter bfw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
        String data = "";
        while ((data = bfr1.readLine()) != null) {
            if (!data.contains("id=\"")) {
                if (!data.contains("</")){
                    bfw1.write(data.substring(0,data.lastIndexOf(">")+1));
                } else {
                    bfw1.write(data);
                }
            } else {
                bfw1.write(data);
            }
            bfw1.newLine();
        }
        bfw1.close();
        bfr1.close();
    }
}
