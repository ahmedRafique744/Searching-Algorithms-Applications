import java.util.Comparator;

public class MazeAStarSearch extends AbstractMazeSearch {

    private PriorityQueue<Cell> priorityQueue;

    public MazeAStarSearch(Maze maze) {
        super(maze);
        Comparator<Cell> comparator = new Comparator<Cell>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
                int targetRow = getTarget().getRow();
                int targetCol = getTarget().getCol();

                int startSumOne = traceback(cell1).size();
                int startSumTwo = traceback(cell2).size();

                int targetRowOne = Math.abs(cell1.getRow() - targetRow);
                int targetColOne = Math.abs(cell1.getCol() - targetCol);
                int targetSumOne = targetRowOne + targetColOne;

                int targetRowTwo = Math.abs(cell2.getRow() - targetRow);
                int targetColTwo = Math.abs(cell2.getCol() - targetCol);
                int targetSumTwo = targetRowTwo + targetColTwo;

                int totalDistOne = startSumOne + targetSumOne;
                int totalDistTwo = startSumTwo + targetSumTwo;

                return ( (Comparable<Integer>) totalDistOne).compareTo(totalDistTwo);
                }
            };
        priorityQueue = new Heap<Cell>(comparator, false);
    }

    public int numRemainingCells() {
        return priorityQueue.size();
    }

    public void addCell(Cell next) {
        priorityQueue.offer(next);
    }

    public Cell findNextCell() {
        return priorityQueue.poll();
    }

    public static void main(String[] args) {
        Maze testMaze = new Maze(30, 30, 0.2);
        MazeAStarSearch search = new MazeAStarSearch(testMaze);
        LinkedList<Cell> path = search.search(testMaze.get(0, 0), testMaze.get(19,23), true, 20);
        System.out.println(path);
    }

}