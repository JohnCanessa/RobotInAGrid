import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


/**
 * Use to reprecent a cell in the maze.
 */
class Cell {

    // **** ****
    public int r;
    public int c;


    /**
     * Constructor
     */
    public Cell(int r, int c) {
        this.r = r;
        this.c = c;
    }


    /**
     * Return string representation of this cell.
     */
    @Override
    public String toString() {
        return "(" + this.r + "," + this.c + ")";
    }
}


/**
 * Robot in a Grid Cracking the Coding interview Problmem 8.2
 */
public class Solution {

    /**
     * Entry function to get the path from the end back to the start cell.
     * Using bottom up approach.
     */
    static ArrayList<Cell> getPath(Boolean[][] maze) {

        // **** check start condition ****
        if ((maze == null) || (maze.length == 0)) {
            return null;
        }

        // **** used to hold the path ****
        ArrayList<Cell> path = new ArrayList<Cell>();

        // **** start recursion (from the bottom right corner) ****
        if (getPath(maze, maze.length - 1, maze[0].length - 1, path) != null)
            return path;

        // **** path NOT found ****
        return null;
    }

    
    /**
     * Recursive procedure to get from s (top left corner) to e (bottom right corner).
     * Using bottom up approach.
     */
    static Boolean getPath(Boolean[][] maze, int row, int col, ArrayList<Cell> path) {

        // **** check for cell out of bounds or not available (moving left and up only) ****
        if ((col < 0) || (row < 0) || !maze[row][col]) {
            return false;
        }

        // **** recursive call(s) ****
        if (row == 0 && col == 0 ||                     // upper left corner
            getPath(maze, row, col - 1, path) ||        // up
            getPath(maze, row - 1, col, path)) {        // left

                // **** add this cell to the path ****
                Cell p = new Cell(row, col);
                path.add(p);

                // **** path found (end condition) ****
                return true;
        }

        // **** path NOT found (keep on recursing) ****
        return false;
    }


    /**
     * Entry function to get the path from the end back to the start cell.
     * Using top down approach.
     */
    static Stack<Cell> getPathTD(Boolean[][] maze) {

        // **** check start condition ****
        if ((maze == null) || (maze.length == 0)) {
            return null;
        }

        // **** used to hold the path ****
        Stack<Cell> path = new Stack<Cell>();

        // **** start recursion (from the top left corner) ****
        if (getPathTD(maze, 0, 0, path) != null)
            return path;

        // **** path NOT found ****
        return null;
    }

    /**
     * Recursive procedure to get from s (top left corner) to e (bottom right corner).
     * Using top down apporoach.
     */
    static Boolean getPathTD(Boolean[][] maze, int row, int col, Stack<Cell> path) {

        // **** check for cell out of bounds or not available (moving right and down only) ****
        if ((col > maze[0].length - 1) || (row > maze.length - 1) || !maze[row][col]) {
            return false;
        }

        // **** add this cell to the path ****
        Cell p = new Cell(row, col);
        path.add(p);

        // **** ****
        if (row == maze.length - 1 && col == maze[0].length - 1)    // lower right corner
            return true;

        // **** recursion ****
        Boolean found = false;

        // **** ****
        found = getPathTD(maze, row, col + 1, path);                // right
        if (found)
            return true;
            
        // **** ****
        found = getPathTD(maze, row + 1, col, path);                // down
        if (found)
            return true;

        // **** remove this cell from the path ****
        path.pop();

        // **** path NOT found (keep on recursing) ****
        return false;
    }


    /**
     * Test scaffolding.
     * The description does not describe the input format.
     * Specifically it talks about "off limit" cells.
     * Grid cells set to 'o' are open; otherwise are set to 'x' to indicate "off limit".
     */
    public static void main(String[] args) {
        
        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read number of rows in the grid ****
        String[] rc = sc.nextLine().trim().split(" ");
        int rows = Integer.parseInt(rc[0]);
        int cols = Integer.parseInt(rc[1]);

        // ???? ????
        System.out.println("main <<< rows: " + rows + " cols: " + cols);

        // **** declare and initialize the maze ****
        Boolean[][] maze = new Boolean[rows][];
        for (int r = 0; r < rows; r++) {
            maze[r] = new Boolean[cols];
        }

        // **** read the contents of the maze ****
        for (int r = 0; r < rows; r++) {
            String[] cs = sc.nextLine().trim().split(" ");
            for (int c = 0; c < cols; c++) {
                maze[r][c] = cs[c].equals("o") ? true : false;
            }
        }

        // **** close scanner ****
        sc.close();

        // ???? display the maze ????
        System.out.println("main <<< maze: ");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(((maze[r][c] == true) ? "o": "x") + " ");
            }
            System.out.println();
        }

        // **** get the path from the top left corner to the bottom right (down up approach) ****
        ArrayList<Cell> path = getPath(maze);

        // **** display the path (if found) ****
        if (path != null)
            System.out.println("main <<< path: " + path.toString());

        // **** get the path from the top left corner to the bottom right (top down approach) ****
        Stack<Cell> pd = getPathTD(maze);

        // **** display the path (if found) ****
        if (path != null)
            System.out.println("main <<< path: " + pd.toString());
    }
}