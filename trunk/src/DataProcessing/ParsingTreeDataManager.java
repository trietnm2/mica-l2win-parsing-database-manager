/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import DataProcessing.supportElement.ParseTextNode;
import java.io.*;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lelightwin
 */
public class ParsingTreeDataManager {

    private BufferedReader bfr;
    private ArrayList<ParseTextNode> pNodes;

    public ParsingTreeDataManager(File inputFile) throws FileNotFoundException, UnsupportedEncodingException {
        bfr = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "utf-8"));
        pNodes = new ArrayList<ParseTextNode>();
    }

    /**
     * 
     * @param str
     * @return level of the String-in-line get from bfr
     */
    private int getLevel(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\t') {
                count++;
            } else {
                return count;
            }
        }
        return count;
    }

    /**
     * @function : add the node corresponding to str into the current processing tree using the pNodes (stack)
     * @param str 
     */
    public void createBranch(String str1) {
        int level = getLevel(str1);
        str1 = str1.replaceAll("\t", "").trim();
        // create ParseTextNode correspond to str1
        ParseTextNode ptn = new ParseTextNode(level, str1);
        if (!pNodes.isEmpty()) {
            // find the parent node of ptn in pNodes, 
            ParseTextNode lastPtn = pNodes.get(pNodes.size() - 1);
            while (lastPtn.getLevel() >= ptn.getLevel()) {
                //set Phrase for the complete node which is removed from pNodes
                DefaultMutableTreeNode lastNode = lastPtn.getNode();
                if (!lastNode.isLeaf()) {
                    if (lastNode.getFirstChild().isLeaf()) {
                        String string1 = (String) lastNode.getUserObject();
                        String string2 = (String) (((DefaultMutableTreeNode) lastNode.getFirstChild()).getUserObject());
                        lastNode.setUserObject(string1 + " - " + string2);
                    } else {
                        String string = "";
                        String string1 = (String) lastNode.getUserObject();
                        for (int i = 0; i < lastNode.getChildCount(); i++) {
                            String string2 = (String) (((DefaultMutableTreeNode) lastNode.getChildAt(i)).getUserObject());
                            String string3 = string2.substring(string2.indexOf(" - ") + 3);
                            string = string.concat(" " + string3).trim();
                        }
                        lastNode.setUserObject(string1 + " - " + string);
                    }
                }
                //remove
                pNodes.remove(pNodes.size() - 1);
                lastPtn = pNodes.get(pNodes.size() - 1);
            }
            // here is the parent node which is returned
            lastPtn = pNodes.get(pNodes.size() - 1);
            // add ptn to its child
            lastPtn.getNode().add(ptn.getNode());
            pNodes.add(ptn);
        } else {
            pNodes.add(ptn);
        }
    }

    /**
     * 
     * @param bfr
     * @return the Parsing Tree Node of one sentence in bfr
     * @throws IOException 
     */
    private DefaultMutableTreeNode getParsedSentence(BufferedReader bfr) throws IOException {
        pNodes.clear();
        String str1 = "";
        while (!"</s>".equals(str1 = bfr.readLine())) { //get all the line in bfr that compound the parsing tree of complete sentence
            createBranch(str1); //create tree using the line described above
        }
        //end by returning the root of the parsing tree and setting the phrase for the root
        DefaultMutableTreeNode sentenceNode = pNodes.get(0).getNode();
        String string = "";
        String string1 = (String) sentenceNode.getUserObject();
        for (int i = 0; i < sentenceNode.getChildCount(); i++) {
            String string2 = (String) (((DefaultMutableTreeNode) sentenceNode.getChildAt(i)).getUserObject());
            int index = string2.indexOf(" - ");
            if (index != -1) {
                String string3 = string2.substring(string2.indexOf(" - ") + 3);
                string = string.concat(" " + string3).trim();
            } else {
                string = string.concat(" "+string2);
            }
        }
        sentenceNode.setUserObject(string1 + " - " + string);

        return sentenceNode;
    }

    /**
     * 
     * @return the node include all the Parsing Tree Node of all sentences which has been extracted from bfr
     * @throws IOException 
     */
    public DefaultMutableTreeNode getFullExtractedTreeNode() throws IOException {
        String str = "";
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
        while ((str = bfr.readLine()) != null) {
            if (str.startsWith("<s")) {
                DefaultMutableTreeNode sentenceNode = getParsedSentence(bfr);
                root.add(sentenceNode);
            }
        }
        bfr.close();
//        System.out.println("method:getFullExtractedTreeNode class:ParsingTreeDataManager printing...");
//        display(0,root);
        return root;
    }

    /**
     * @function : project the tree onto the console screen
     * @param : node
     */
    private void display(int level, DefaultMutableTreeNode node) {
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.println(node.getUserObject());
        for (int i = 0; i < node.getChildCount(); i++) {
            display(level + 1, (DefaultMutableTreeNode) node.getChildAt(i));
        }
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        new ParsingTreeDataManager(new File(System.getProperty("user.dir") + "/parsedTreeCorpus_newFormat/25272.prd")).getFullExtractedTreeNode();
    }
}
