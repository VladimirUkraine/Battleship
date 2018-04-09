import java.io.*;
import java.util.*;

public class GameHelper {

    private static final String alphabet = "abcdefg"; //7
    private int gridLenght = 7;                  // 7
    private int gridSize = 49;  // 7 * 7 = 49
    private int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.println(prompt + " ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        String[] alphacoords = new String[comSize];
        String temp = null;
        int[] coords = new int[comSize];
        int attempts = 0;
        boolean succes = false;
        int locations = 0;

        comCount++;
        int incr = 1;
        if ((comCount % 2) == 1) {
            incr = gridLenght;
        }

        while (!succes & attempts++ < 200) {
            locations = (int) (Math.random() * gridSize);
            int x = 0;
            succes = true;
            while (succes && x < comSize) {
                if (grid[locations] == 0) {
                    coords[x++] = locations;
                    locations += incr;
                }
                if (locations >= gridSize) {
                    succes = false;
                }
            }
            if (x > 0 && (locations % gridLenght == 0)) {
                succes = false;
            }
        }
        int x =0;
        int row = 0;
        int column =0;

        while (x < comSize){
            grid[coords[x]] = 1;
            row = (int) (coords[x] / gridLenght);
            column = coords[x] % gridLenght;
            temp = String.valueOf(alphabet.charAt(column));

            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }
        return alphaCells;

    }
}
