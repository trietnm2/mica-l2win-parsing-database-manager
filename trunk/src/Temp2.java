
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
        String outputFile = outputDir + "vlsp.xml";
        BufferedWriter bfw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));

        // <editor-fold desc="write start document">
        bfw1.write("<?xml version=\"1.0\" ?>");
        bfw1.newLine();
        bfw1.write("<root>");
        bfw1.newLine();
        // </editor-fold>

        for (int i = 0; i < xmlFiles.length; i++) {
            File file = xmlFiles[i];
            ArrayList<String> strArr = new ArrayList<String>();
            BufferedReader bfr1 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String data = "";
            while ((data = bfr1.readLine()) != null) {
                strArr.add(data);
            }
            strArr.remove(0);
            strArr.remove(0);
            strArr.remove(strArr.size() - 1);
            for (int j = 0; j < strArr.size(); j++) {
                String string = strArr.get(j);
                bfw1.write(string);
                bfw1.newLine();
            }
            bfr1.close();

            // <editor-fold desc="change the file to write">
//            if (i % 6 == 5) {
//                // <editor-fold desc="write end document">
//                bfw1.write("</root>");
//                bfw1.close();
//                // </editor-fold>
//
//                // <editor-fold desc="write start of new document">
//                fileCount++;
//                outputFile = outputDir + "vlsp0" + fileCount + ".xml";
//                bfw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
//                bfw1.write("<?xml version=\"1.0\" ?>");
//                bfw1.newLine();
//                bfw1.write("<root>");
//                bfw1.newLine();
//                // </editor-fold>
//            }
            // </editor-fold>
        }
        
        // <editor-fold desc="write end document">
        bfw1.write("</root>");
        bfw1.close();
        // </editor-fold>
    }
}