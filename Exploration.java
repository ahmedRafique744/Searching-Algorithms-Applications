/*
 * Name: Ahmed Rafique Choudhry
 * CS 231 Section A
 * Date: 05/01/2024
 * Class Purpose: Exploration - Provides the code that implement the Required Analyses evaluating the performance of our 3 algorithms.
 */

public class Exploration {

    public static void main(String[] args) { // the main method to be run.
        if (args.length > 0) {
            LinkedList<Cell> result = null;
            int failCount = 0;
            int pathLength = 0;
            int cellsExplored = 0; // assigning the number of cells explored by each algorithm initially to 0
            for (float i = 0.0f; i < 1.0f; i=i+0.1f){
                for (int j = 0; j < 100; j++) { // nested for loop that runs experiment 100 times for each density.
                    Maze tester = new Maze(20, 20, i);
                    if (args[0].equals("Depth")) {
                        MazeDepthFirstSearch testMaze = new MazeDepthFirstSearch(tester);
                        result = testMaze.search(tester.get(0, 0), tester.get(19, 19), false, 0);
                    } else if (args[0].equals("Breadth")) {
                        MazeBreadthFirstSearch testMaze = new MazeBreadthFirstSearch(tester);
                        result = testMaze.search(tester.get(0, 0), tester.get(19, 19), false, 0);
                    } else if (args[0].equals("A-star")) {
                        MazeAStarSearch testMaze = new MazeAStarSearch(tester);
                        result = testMaze.search(tester.get(0, 0), tester.get(19, 19), false, 0);
                    }
                    if (result == null) {
                        failCount++;
                    } if (result != null) {
                        pathLength = pathLength + result.size();
                    }
                    for (Cell cell : tester) {
                        if (cell.getPrev() != null) {
                            cellsExplored++;
                        }
                    }
                }
                System.out.println("Probability of Finding Target with " + i + " density: " + (100.00-(double)failCount)/(double)100 + ", length of path: " + (pathLength / 100) + ", and average number of cells explored: " + (cellsExplored / 100));
                failCount = 0;
            }
        }
    }
}
