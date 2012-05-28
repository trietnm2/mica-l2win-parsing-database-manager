/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Test_Not_In_Use;

/**
 *
 * @author lelightwin
 */
public class Parentthesis {
    private int openPosition;
    private int closePosition;
    private String text = "";
    private int level;

    public Parentthesis(){

    }

    public Parentthesis(int open, int close){
        this.openPosition = open;
        this.closePosition = close;
    }

    /**
     * @return the openPosition
     */
    public int getOpenPosition() {
        return openPosition;
    }

    /**
     * @param openPosition the openPosition to set
     */
    public void setOpenPosition(int openPosition) {
        this.openPosition = openPosition;
    }

    /**
     * @return the closePosition
     */
    public int getClosePosition() {
        return closePosition;
    }

    /**
     * @param closePosition the closePosition to set
     */
    public void setClosePosition(int closePosition) {
        this.closePosition = closePosition;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
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


}
