/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import com.sun.org.apache.xerces.internal.dom.AttributeMap;
import java.io.File;
import java.io.IOException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author lelightwin
 */
public class ReadXMLCorpus {

    private File inputFile;

    public ReadXMLCorpus(File inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * @param node
     * @return the label value of node
     */
    private static String getValueOfNode(Node node) {
        Node target = node.getFirstChild();
        if (target != null) {
            if (target.getNodeType() == Node.TEXT_NODE) {
                String result = target.getTextContent().trim();
                return result;
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * @function create treeNode from xmlNode (recursively)
     *
     * @param treeNode
     * @param xmlNode
     */
    private void generate(DefaultMutableTreeNode treeNode, Node xmlNode) {
        NodeList children = xmlNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                String str = child.getNodeName();

                AttributeMap atts = (AttributeMap) child.getAttributes();
                if (atts != null) {
                    Node syntaxAtt = atts.item(0);
                    if (syntaxAtt != null) {
                        if (!"".equals(syntaxAtt.getNodeValue())) {
                            str += "(" + syntaxAtt.getNodeValue() + ")";
                        }
                    }
                }

                DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(str);
                treeNode.add(childTreeNode);
                String data = getValueOfNode(child);
                if (!data.equals("")) {
                    DefaultMutableTreeNode text = new DefaultMutableTreeNode(data);
                    childTreeNode.add(text);
                }
                generate(childTreeNode, child);
            }
        }
    }

    /**
     * @return text for each node
     * @param node
     */
    private String addText(DefaultMutableTreeNode node) {
        String str = "";
        if (node.isLeaf()) {
            str = node.toString();
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                str += addText((DefaultMutableTreeNode) node.getChildAt(i)) + " ";
            }
            str = str.trim();
            if (!node.toString().contains(" - ")) {
                node.setUserObject(node.toString() + " - " + str);
            }
        }
        return str;
    }

    /**
     *
     * @return the node include all the Parsing Tree Node of all sentences which
     * has been extracted from inputFile
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public DefaultMutableTreeNode getFullExtractedTreeNode() throws SAXException, IOException, ParserConfigurationException {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        Node node = doc.getFirstChild();
        NodeList listSentences = node.getChildNodes();
        for (int i = 0; i < listSentences.getLength(); i++) {
            Node iSentence = listSentences.item(i);
            if (iSentence.getNodeType() == Node.ELEMENT_NODE) {
                String text = getValueOfNode(iSentence);
                DefaultMutableTreeNode iNode = new DefaultMutableTreeNode(iSentence.getNodeName() + " - " + text);
                root.add(iNode);
                generate(iNode, iSentence);
                addText(iNode);
            }
        }
        return root;
    }

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        ReadXMLCorpus rxc = new ReadXMLCorpus(new File(System.getProperty("user.dir") + "/vlspXMLCorpus_Refined/newTrainingCorpus.xml"));
        rxc.getFullExtractedTreeNode();
    }
}
