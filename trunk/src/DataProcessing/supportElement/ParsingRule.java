/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.supportElement;

/**
 *
 * @author lelightwin
 */
public class ParsingRule {

    private String left;                // left side of a parsing rule
    private String right;               // right side of a parsing rule
    private String leftSyntax;          // syntax function of left side
    private String rightSyntax;         // syntax function of right side
    private String leftIllustration;    // a example which illustrate for left side 
    private String rightIllustration;   // a example which illustrate for right side

    public ParsingRule(String left, String right, String leftSyntax, String rightSyntax,String leftIllustration, String rightIllustration) {
        this.left = left;
        this.right = right;
        this.leftSyntax = leftSyntax;
        this.rightSyntax = rightSyntax;
        this.leftIllustration = leftIllustration;
        this.rightIllustration = rightIllustration;
    }

    /**
     * @return the left
     */
    public String getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public String getRight() {
        return right;
    }

    /**
     * @return the leftIllustration
     */
    public String getLeftIllustration() {
        return leftIllustration;
    }

    /**
     * @return the rightIllustration
     */
    public String getRightIllustration() {
        return rightIllustration;
    }

    /**
     * @return the leftSyntax
     */
    public String getLeftSyntax() {
        return leftSyntax;
    }

    /**
     * @return the rightSyntax
     */
    public String getRightSyntax() {
        return rightSyntax;
    }
}
