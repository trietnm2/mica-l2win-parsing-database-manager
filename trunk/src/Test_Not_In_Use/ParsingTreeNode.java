/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Not_In_Use;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author lelightwin
 */
public class ParsingTreeNode implements Serializable {

    private String tag;
    private String extra;
    private int level;
    private ArrayList<ParsingTreeNode> children = new ArrayList<ParsingTreeNode>();
    private ParsingTreeNode parent;

    public ParsingTreeNode() {
    }

    public ParsingTreeNode(String tag) {
        this.tag = tag;
    }

    public boolean isLexicalNode() {
        return (children.get(0).getChildren().isEmpty());
    }

    public boolean isLexical() {
        return (children.isEmpty());
    }

    /**
     * @return title of the node
     */
    private String title() {
        if (!this.isLexical()) {
            String str = "";
            if ((this.getExtra() != null) && ((!this.getExtra().equals("H")))) {
                str = this.getTag() + "(" + this.getExtra() + ")";
            } else {
                str = this.getTag();
            }
            return str;
        } else {
            return (this.getTag().toUpperCase());
        }
    }

    /**
     * @return luat hop thanh cua node
     */
    public String getRule() {
        if (this.isLexical()) {
            return "";
        } else {
            String str = this.getTag()+" : ";
            ArrayList<ParsingTreeNode> children = this.getChildren();
            for (int i = 0; i < children.size(); i++) {
                ParsingTreeNode node = children.get(i);
                str = str.concat(node.getTag().trim()+" ");
            }
            return str.trim();
        }
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return the childrens
     */
    public ArrayList<ParsingTreeNode> getChildren() {
        return children;
    }

    /**
     * @return the childrens
     */
    public ParsingTreeNode getChild(int i) {
        return children.get(i);
    }

    /**
     * @param childrens the childrens to set
     */
    public void setChildren(ParsingTreeNode children) {
        this.children.add(0, children);
        children.setParent(this);
    }

    /**
     * @return the parent
     */
    public ParsingTreeNode getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(ParsingTreeNode parent) {
        this.parent = parent;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the extra
     */
    public String getExtra() {
        return extra;
    }

    /**
     * @param extra the extra to set
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /** Ten ham : displayTree
     *  Chuc nang : ghi node ra file
     *  Tham so :
     *      pw: luong du lieu ung voi file 
     */
    public void displayTree(PrintWriter pw) {
        for (int i = 0; i < this.getLevel() - 1; i++) {
            pw.print("\t");
        }
        if (this.getExtra() != null) {
            pw.print(this.getTag().trim() + "(" + this.getExtra() + ")");
        } else {
            pw.print(this.getTag().trim());
        }
        if (!this.isLexical()) {
            pw.println(" - [" + getRule() + "]");
        } else {
            pw.println("");
        }
        ArrayList<ParsingTreeNode> childrenNode = this.getChildren();
        for (int i = 0; i < childrenNode.size(); i++) {
            ParsingTreeNode childNode = childrenNode.get(i);
            childNode.displayTree(pw);
        }
    }
}
