/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Test_Not_In_Use;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author lelightwin
 */
public class NameFilter implements FilenameFilter {

    private String str;

    public NameFilter(String str) {
        this.str = str;
    }

    public boolean accept(File dir, String name) {
        return name.endsWith("." + str);
    }
}