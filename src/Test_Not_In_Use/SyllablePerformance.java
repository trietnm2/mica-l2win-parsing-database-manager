/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Not_In_Use;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author lelightwin
 */
public class SyllablePerformance {

    private ArrayList<String> syllable = new ArrayList<String>();
    private String[] nonSyllable = {"<s>", "</s>"};

    public ArrayList<String> getData(File f) throws FileNotFoundException, IOException {
        ArrayList<String> strData = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(new FileReader(f));
        String str = "";
        while ((str = bf.readLine())!=null) {
            //System.out.println(str);
            for (int i = 0; i < nonSyllable.length; i++) {
                str = str.replaceAll(nonSyllable[i], "");
            }
            String[] words = str.split(" ");
            for (int i = 0; i < words.length; i++) {
                String string = words[i];
                if (!strData.contains(string)) {
                    strData.add(string);
                }
            }
        }
        return strData;
    }

    public SyllablePerformance() throws FileNotFoundException, IOException {
        File dir = new File(System.getProperty("user.dir") + "/parsedTreeCorpus");
        File[] files = dir.listFiles(new NameFilter("raw"));
        for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (!file.getName().equals("109898.van.raw")) {
                    syllable.addAll(getData(file));
                }
        }
        File result = new File(System.getProperty("user.dir")+"/syllableAnalyzingResult.res");
        PrintWriter pw = new PrintWriter(result);
        pw.println(syllable.size());
        for (int i = 0; i < syllable.size(); i++) {
            String string = syllable.get(i);
            pw.println(string);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new SyllablePerformance();
    }
}
