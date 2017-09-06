
package minesweeper;

import java.util.TreeMap;

/**
 *
 * @author Breuer
 */
public class hiScoreTree
{
    String name;
    long score;
    TreeMap<Long,String>scores=new TreeMap();
    public hiScoreTree(String name, long score)
    {
        this.name = name;
        this.score = score;
    }
    
    
}