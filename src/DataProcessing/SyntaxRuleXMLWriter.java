/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author lelightwin
 */
public class SyntaxRuleXMLWriter {

    private ArrayList<String> ruleListStr = new ArrayList<String>();
    private ArrayList<String> lexicalRuleListStr = new ArrayList<String>();
    private ArrayList<Integer> ruleQuantity = new ArrayList<Integer>();
    private ArrayList<Integer> lexicalRuleQuantity = new ArrayList<Integer>();
    private ArrayList<String> syntaxFunctions = new ArrayList<String>();
    private ArrayList<ArrayList<String>> ruleIllustrationListStr = new ArrayList<ArrayList<String>>();
    private static XMLOutputFactory factory = XMLOutputFactory.newInstance();
    private static XMLStreamWriter writer;

    public SyntaxRuleXMLWriter(ArrayList<String> ruleListStr,
            ArrayList<String> lexicalRuleListStr,
            ArrayList<Integer> ruleQuantity,
            ArrayList<Integer> lexicalRuleQuantity,
            ArrayList<ArrayList<String>> ruleIllustrationListStr) {
        this.ruleListStr = ruleListStr;
        this.lexicalRuleListStr = lexicalRuleListStr;
        this.ruleQuantity = ruleQuantity;
        this.lexicalRuleQuantity = lexicalRuleQuantity;
        this.ruleIllustrationListStr = ruleIllustrationListStr;
    }

    public void createXMLFile(String normalRuleXMLFile, String lexicalRuleXMLFile) {
        try {
            File rFile = new File(normalRuleXMLFile);
            File lrFile = new File(lexicalRuleXMLFile);

            // <editor-fold desc="for normal syntax rule">
            writer = factory.createXMLStreamWriter(new FileOutputStream(rFile), "utf-8");
            writer.writeStartDocument();
            writer.writeEndDocument();
            writer.close();
            // </editor-fold>

            // <editor-fold desc="for lexical rule">
            writer = factory.createXMLStreamWriter(new FileOutputStream(lrFile), "utf-8");
            writer.writeStartDocument();
            writer.writeEndDocument();
            writer.close();
            // </editor-fold>
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SyntaxRuleXMLWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(SyntaxRuleXMLWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
