/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import DataProcessing.supportElement.ParsingRule;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lelightwin
 */
public class ParsingRuleManager {

    private ArrayList<String> lexicalRules = new ArrayList<String>();
    private ArrayList<ParsingRule> rules = new ArrayList<ParsingRule>();

    public ParsingRuleManager(DefaultMutableTreeNode node){
        processingRuleSetFromNode(node);
    }
    
    /**
     * 
     * @param node
     * @return the rule set which is used for creating the parsing tree with "node" root
     */
    private void processingRuleSetFromNode(DefaultMutableTreeNode node) {
        if (!node.isLeaf()) {
            String[] a1 = StringAnalyze.analyze1((String)node.getUserObject());
            String left = a1[0];
            String leftIllus = "["+a1[0]+"]    "+a1[1];

            String right = "";
            String rightIllus = "";
            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode iNode = (DefaultMutableTreeNode) node.getChildAt(i);
                processingRuleSetFromNode(iNode);
                String[] a2 = StringAnalyze.analyze1((String) iNode.getUserObject());
                right = right.concat(a2[0] + " ");
                rightIllus = rightIllus.concat("["+a2[0]+"]    " + a2[1] + "\n");
            }
            right = right.trim();
            if (!isNearLexicalNode(node)) {
                String[] strs = StringAnalyze.analyze2(left);
                String leftPLabel = strs[0];
                String leftSynFunc = strs[1];
                
                String[] rights = right.split(" ");
                String rightPLabel = "";
                String rightSynFunc = "";
                for (int i = 0; i < rights.length; i++) {
                    String[] strs2 = StringAnalyze.analyze2(rights[i]);
                    rightPLabel = rightPLabel.concat(" "+strs2[0]);
                    rightSynFunc = rightSynFunc.concat(" "+strs2[1]);
                }
                
                getRules().add(new ParsingRule(leftPLabel, rightPLabel,leftSynFunc, rightSynFunc, leftIllus, rightIllus));
            } else {
                getLexicalRules().add(left+" -> "+right);
            }
        }
    }

    /**
     * 
     * @param node
     * @return true if node is near-lexical node (like N->"ma")
     */
    private boolean isNearLexicalNode(DefaultMutableTreeNode node) {
        if (node != null) {
            if ((node.getFirstChild().isLeaf()) && (node.getChildCount() == 1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the lexicalRules
     */
    public ArrayList<String> getLexicalRules() {
        return lexicalRules;
    }

    /**
     * @return the rules
     */
    public ArrayList<ParsingRule> getRules() {
        return rules;
    }
    
    public static void main(String[] args) {
    }
}
