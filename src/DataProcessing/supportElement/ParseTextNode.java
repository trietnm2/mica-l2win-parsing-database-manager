/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.supportElement;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lelightwin
 */
public class ParseTextNode {

    private int level;
    private DefaultMutableTreeNode node;

    public ParseTextNode(int level, String text) {
        this.level = level;
        node = new DefaultMutableTreeNode(text);
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the text
     */
    public DefaultMutableTreeNode getNode() {
        return node;
    }
    
    public static void main(String[] args) {
        String s = "ten : Le Quang - Thang";
        System.out.println(s.substring(s.indexOf(" : ")+3));
    }
}
