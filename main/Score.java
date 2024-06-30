package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The Score class is responsible for managing the game scores.
 * <p>
 * This class reads and writes scores to a file, and calculates the maximum score.
 * </p>
 *
 * @author Nina Brand, Jan-Hendrik Heise, Arman Momtahan
 */

public class Score {

    /**
     * This method reads the score file, adds the new score, calculates the maximum score, and writes the scores back to the file.
     *
     * @param scorep1 This is the new score to be added.
     * @return int This returns the maximum score.
     */

    public int readScoreFile(int scorep1) {

        String scoreString = String.valueOf(scorep1);

        ArrayList<String> score = new ArrayList<>();
        score.add(scoreString);

        File scoreBorde = new File("Score");
        File playerScore = new File("Score/PlayerScore.txt");

        int MaxScore = 0;
        if (scoreBorde.exists()) { //Ordner existiert

            if (playerScore.exists()) {

                try {

                    Scanner sc = new Scanner(playerScore);

                    while (sc.hasNext()) {
                        score.add(sc.next());
                    }

                    ArrayList<Integer> ListMax = new ArrayList<>();
                    for (String s : score) {
                        ListMax.add(Integer.parseInt(s));
                    }

                    MaxScore = Collections.max(ListMax);
                    System.out.println(MaxScore);

                    System.out.println(score);

                    sc.close();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else {
                try {
                    playerScore.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } else {
            //ordner und file werden erstellt wenn nichts existent
            scoreBorde.mkdir();
            try {
                playerScore.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            OutputStream out = new FileOutputStream(playerScore);

            for (String s : score) {
                String s2 = s + " ";
                out.write(s2.getBytes());
            }

            out.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return MaxScore;
    }
}