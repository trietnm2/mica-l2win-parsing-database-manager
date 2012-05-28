/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Not_In_Use;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author lelightwin
 */
public class CreateParsingRule {

    ArrayList<String> rules = new ArrayList<String>();
    ArrayList<Integer> ruleQuantity = new ArrayList<Integer>();
    ArrayList<ArrayList<String>> headWords = new ArrayList<ArrayList<String>>();
    ArrayList<Integer> headIndex = new ArrayList<Integer>();
    ArrayList<String> lexicalRules = new ArrayList<String>();
    ArrayList<Integer> lexicalRuleQuantity = new ArrayList<Integer>();

    public String getRealName(String name) {
        return name.substring(0, name.indexOf("."));
    }

    public CreateParsingRule() throws IOException, FileNotFoundException, ClassNotFoundException {
        File dir = new File(System.getProperty("user.dir") + "/parsedTreeCorpus");

        File[] files = dir.listFiles(new NameFilter("prd"));
        for (int k = 0; k < files.length; k++) {
            File filek = files[k];
            rules.clear();
            ruleQuantity.clear();
            lexicalRules.clear();
            lexicalRuleQuantity.clear();

            System.out.println(filek.getName());
            ParsingTreeCorpusDataConverter ptcdc = new ParsingTreeCorpusDataConverter(filek);
            ArrayList<ParsingTreeNode> trees = ptcdc.result();
            for (int i = 0; i < trees.size(); i++) {
                ParsingTreeNode ptn = trees.get(i);
                createRuleSet(ptn);
            }
            ///////////////////////////////////
            PrintWriter pw1 = new PrintWriter(System.getProperty("user.dir") + "/dict/ruleSet/" + getRealName(filek.getName())+".rule");
            PrintWriter pw2 = new PrintWriter(System.getProperty("user.dir") + "/dict/lexicalRuleSet/" + getRealName(filek.getName())+".LexRule");
            ArrayList<String> outputRules = new ArrayList<String>();
            ArrayList<String> lexicalOutputRules = new ArrayList<String>();
            for (int i = 0; i < rules.size(); i++) {
                outputRules.add(rules.get(i) + "\t" + ruleQuantity.get(i));
            }
            Collections.sort(outputRules);
            for (int i = 0; i < outputRules.size(); i++) {
                String string = outputRules.get(i);
                pw1.println(string);
            }

            //////////////////////////////////////
            for (int i = 0; i < lexicalRules.size(); i++) {
                lexicalOutputRules.add(lexicalRules.get(i) + "\t" + lexicalRuleQuantity.get(i));
            }
            Collections.sort(lexicalOutputRules);
            for (int i = 0; i < lexicalOutputRules.size(); i++) {
                String string = lexicalOutputRules.get(i);
                pw2.println(string);
            }
            pw1.close();
            pw2.close();
        }
    }

    private void add(String s, ArrayList<String> strs, ArrayList<Integer> ins) {
        if (strs.contains(s)) {
            int index = strs.indexOf(s);
            int count = ins.get(index) + 1;
            ins.set(index, count);
        } else {
            strs.add(s);
            ins.add(1);
        }
    }

    private void createRuleSet(ParsingTreeNode node) {
        if (!node.isLexical()) {
            if (!node.isLexicalNode()) {
                String rule = node.getRule();
                add(rule, rules, ruleQuantity);
                ArrayList<ParsingTreeNode> childrenNode = node.getChildren();
                for (int i = 0; i < childrenNode.size(); i++) {
                    createRuleSet(childrenNode.get(i));
                }
            } else {
                String lrule = node.getRule();
                add(lrule, lexicalRules, lexicalRuleQuantity);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        //        ParsingTreeCorpusDataConverter ptcdc = new ParsingTreeCorpusDataConverter();
        //        PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/parsedTreeCorpus_newFormat/all.data");
        //        ArrayList<ParsingTreeNode> treeList = ptcdc.result();
        //        for (int i = 0; i < treeList.size(); i++) {
        //            ParsingTreeNode tree = treeList.get(i);
        //            int index = i + 1;
        //            pw.println("<s id = \"" + index + "\">");
        //            tree.displayTree(pw);
        //            pw.println("</s>");
        //        }
        //        pw.close();
    }
}
