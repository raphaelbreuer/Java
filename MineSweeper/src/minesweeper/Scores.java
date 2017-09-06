package minesweeper;

import java.util.ArrayList;
import java.util.Collections;


/**i added level to object so i could display hi scores according to each level
 * the add method takes int of the current  minesweeper level only adds it if matches to object level upon reading it
 *
 * @author Breuer
 */
public class Scores implements Comparable<Scores>
{

    String name;
    int score;
    int level;
    ArrayList<Scores> scores = new ArrayList();

    public Scores()
    {
    }

    public Scores(String name, int score,int level)
    {
        this.name = name;
        this.score = score;
        this.level=level;
      
    }

    public void add(Scores score,int l)//adds sorts and takes out highest only top 5 wins
    {
      if(score.level!=l)
          return;
        if (scores.size() < 3)
        {
            scores.add(score);
        } else if (score.score < scores.get(scores.size()-1).score)//last is always highest after sort
        {
            scores.set((scores.size()-1), score);
        }
        
        sort();
    }
 
    public void sort()
    {
       Collections.sort(scores);
    }

    public String getName()
    {
        return name;
    }

    public long getScore()
    {
        return score;
    }

    public String print()
    {
        StringBuilder s = new StringBuilder();
        if (!scores.isEmpty())
        {
            for (Scores score1 : scores)
            {
                s.append(score1);
            }
        }
        return s.toString();
    }

    @Override
    public String toString()
    {
        int minutes = (score % 3600) / 60;
                    int seconds = score % 60;
        return  name + " won in " + String.format("%02d:%02d", minutes, seconds) + " \n";
    }

    @Override
    public int compareTo(Scores o)
    {
        if (score > o.score)
        {
            return 1;
        }
        if (score < o.score)
        {
            return -1;
        }
        return 0;
    }

}
