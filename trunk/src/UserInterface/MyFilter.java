/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author lelightwin
 */
public class MyFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isFile()) {
            return f.getName().endsWith(".prd");
        } else {
            return true;
        }
    }

    @Override
    public String getDescription() {
        return "*.prd";
    }
}
