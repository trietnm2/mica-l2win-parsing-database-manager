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
public class ParsingTreeCorpusDataConverter {

    private File input;

    public ParsingTreeCorpusDataConverter(File input) throws FileNotFoundException, IOException, ClassNotFoundException {
        this.input = input;
    }

    /** Ten ham : result
     *  Chuc nang : tra ve tap cay du lieu doc ra duoc
     *  Tham so :
     */
    public ArrayList<ParsingTreeNode> result() throws IOException {
        ArrayList<ParsingTreeNode> trees = new ArrayList<ParsingTreeNode>();

        //System.out.println(rules.size());
        //System.out.println(file.getName());
        BufferedReader bf = new BufferedReader(new FileReader(input));
//            PrintWriter pw = new PrintWriter(new File(System.getProperty("user.dir") + "/parsedTreeCorpus_newFormat/" + file.getName()));
        String str = "";
        int count = 0;

        while ((str = bf.readLine()) != null) {
            String tree = "";
            while (!str.equals("</s>")) {
                tree = tree.concat(str.trim());
                str = bf.readLine();
            }
            tree = tree.replaceFirst("<s>", "");
            if (tree.trim() == null ? "" != null : !tree.trim().equals("")) {
                count++;
//                    pw.println("<s id = \"" + count + "\">");
//                    //System.out.println(file.getName() + "\t-\t" + tree);
                ParsingTreeNode outputTree = getTree(tree);
                trees.add(outputTree);
//                    displayTree(outputTree, pw);
//                    pw.println("</s>");
//                    oos.writeObject(outputTree);
            }
        }
        bf.close();
//            pw.close();
        return trees;
    }

    /** Ten ham : creatNode
     *  Chuc nang : tao ra mot node du lieu tu cap ngoac don chua du lieu trong corpus
     *  Tham so :
     *      node : luu nut duoc tao ra
     *      last : cap ngoac don chua du lieu tuong ung
     */
    private void createNode(ParsingTreeNode node, Parentthesis last) {
        //title la doan text nam giua cap ngoac don last
        String title = last.getText().trim();
        node.setLevel(last.getLevel());
        if (title.indexOf(" ") == -1) { // neu text khong chua dau cach
            if (title.indexOf("-") != -1) { // neu text chua dau "-"
                //System.out.println(title);
                String[] attributes = title.split("-");
                node.setTag(attributes[0]);
                if (attributes.length > 1) {
                    node.setExtra(attributes[1]);
                }
            } else {// neu text khong chua dau "-" va dau cach
                node.setTag(title);
            }
        } else {// neu text chua dau cach
            String[] tags = title.split(" ");
            String str = tags[0].trim();
            if ((!str.equals("-"))
                    && (str.indexOf("-") != -1)) {
                String[] attributes = str.split("-");
                node.setTag(attributes[0]);
                if (attributes.length > 1) {
                    node.setExtra(attributes[1]);
                }
            } else {
                node.setTag(tags[0]);
            }

            String string = "";
            for (int i = 1; i < tags.length; i++) {
                string = string.concat(" " + tags[i]);
            }
            ParsingTreeNode leap = new ParsingTreeNode(string);
            leap.setLevel(last.getLevel() + 1);
            node.setChildren(leap);
        }
    }

    /** Ten ham : createTreeBranch
     *  Chuc nang : them mot not vao stack de ghep vao thanh cay phan tich cu phap theo dinh dang du lieu moi
     *  Tham so : 
     *      nodeStack   : stack cua cac node da duyet qua 
     *      node        : node can them vao 
     */
    private void createTreeBranch(ArrayList<ParsingTreeNode> nodeStack, ParsingTreeNode node) {
        int level = node.getLevel();
        while ((!nodeStack.isEmpty()) && (nodeStack.get(nodeStack.size() - 1).getLevel() == (level + 1))) {
            node.setChildren(nodeStack.remove(nodeStack.size() - 1));
        }
        nodeStack.add(node);
    }

    /** Ten ham : getTree
     *  Chuc nang : tra ve cay phan tich cu phap tu xau du lieu trong corpus
     *  Tham so : xau s bieu thi cho mot cay phan tich cu phap trong corpus
     */
    private ParsingTreeNode getTree(String s) {
        ArrayList<Parentthesis> patStack = new ArrayList<Parentthesis>();
        ArrayList<ParsingTreeNode> nodeStack = new ArrayList<ParsingTreeNode>();
        ParsingTreeNode result = null;
        int level = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (!patStack.isEmpty()) {
                    Parentthesis last = patStack.get(patStack.size() - 1);
                    if (last.getText().equals("")) {
                        last.setText(s.substring(last.getOpenPosition() + 1, i));
                    }
                }
                level++;
                Parentthesis p = new Parentthesis();
                p.setLevel(level);
                p.setOpenPosition(i);
                patStack.add(p);

            } else if (c == ')') {
                level--;
                Parentthesis last = patStack.remove(patStack.size() - 1);
                if (last.getText().equals("")) {
                    last.setText(s.substring(last.getOpenPosition() + 1, i));
                }
                ParsingTreeNode node = new ParsingTreeNode();
                createNode(node, last);
                createTreeBranch(nodeStack, node);
                //System.out.println(node.getTag() + "(" + node.getExtra() + ")");

            }

        }
        return nodeStack.get(0);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
//        ParsingTreeCorpusDataConverter PTCDC = new ParsingTreeCorpusDataConverter();
    }
}
