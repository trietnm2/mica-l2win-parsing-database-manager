/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

/**
 *
 * @author lelightwin
 */
public class StringAnalyze {

    /**
     *
     * @param str
     * @return rule part and illustration part
     */
    public static String[] analyze1(String str) {
        String[] result = new String[2];
        if (str.contains(" - ")) {
            int index = str.indexOf(" - ");
            result[0] = str.substring(0, index);
            result[1] = str.substring(index + 3);
        } else {
            result[0] = str;
            result[1] = str;
        }
        return result;
    }

    /**
     *
     * @param str
     * @return parsing label and syntax function
     */
    public static String[] analyze2(String str) {
        String[] strs = new String[2];
        if (str.contains("(")) {
            strs[0] = str.substring(0, str.indexOf("("));
            strs[1] = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        } else {
            strs[0] = str;
            strs[1] = "NONE";
        }
        return strs;
    }
    
    public static void main(String[] args) {
        String[] test = StringAnalyze.analyze2("NP(R)");
        System.out.println(test[0]+"-"+test[1]);
    }
}
