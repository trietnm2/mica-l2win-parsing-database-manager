
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author lelightwin
 */
public class Temp3 {

    public static void main(String[] args) throws IOException {
        String inputDir = System.getProperty("user.dir") + "/vlspXMLCorpus_Refined/";
//        for (int i = 0; i < 7; i++) {
            String inputFile = inputDir + "vlsp.xml";
            String outputFile = inputDir + "trainingCorpus.xml";
            BufferedReader bfr1 = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "utf-8"));
            BufferedWriter bfw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
            String data = "";
            Pattern p = Pattern.compile("<[0-9]+>");
            while ((data = bfr1.readLine()) != null) {
                Matcher m = p.matcher(data);
                if (m.find()) {
                    String str1 = data.substring(0, m.start());
                    String str2 = bfr1.readLine().trim();
                    bfw1.write(str1.concat(str2));
                    bfw1.newLine();
                    System.out.println(str1 + " || " + str2);
                } else {
                    bfw1.write(data);
                    bfw1.newLine();
                }
            }
            bfw1.close();
            bfr1.close();
//        }

//        String str = "lequang<15>thang-543- -g jg3-gm-kv4-ck4mck5-4c-";
//        Pattern p = Pattern.compile("<[0-9]+>");
//        Matcher m = p.matcher(str);
//        while (m.find()) {
//            System.out.println(str.substring(0, m.start()));
//        }
    }
}
