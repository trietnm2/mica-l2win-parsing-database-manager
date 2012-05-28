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
class StringAnalyze {

    private String rulePart;
    private String illustration;

    StringAnalyze(String str) {
        if (str.contains(" - ")) {
            int index = str.indexOf(" - ");
            this.rulePart = str.substring(0,index);
            this.illustration = str.substring(index+3);
        } else {
            this.rulePart = str;
            this.illustration = str;
        }
    }

    /**
     * @return the rulePart
     */
    public String getRulePart() {
        return rulePart;
    }

    /**
     * @return the illustration
     */
    public String getIllustration() {
        return illustration;
    }
}

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
            StringAnalyze a1 = new StringAnalyze((String) node.getUserObject());
            String left = a1.getRulePart();
            String leftIllus = "["+a1.getRulePart()+"]    "+a1.getIllustration();

            String right = "";
            String rightIllus = "";
            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode iNode = (DefaultMutableTreeNode) node.getChildAt(i);
                processingRuleSetFromNode(iNode);
                StringAnalyze a2 = new StringAnalyze((String) iNode.getUserObject());
                right = right.concat(a2.getRulePart() + " ");
                rightIllus = rightIllus.concat("["+a2.getRulePart()+"]    " + a2.getIllustration() + "\n");
            }
            
            if (!isNearLexicalNode(node)) {
                getRules().add(new ParsingRule(left, right.trim(), leftIllus, rightIllus));
            } else {
                getLexicalRules().add(left+" -> "+right.trim());
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
}
