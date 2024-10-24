public class MazeBreadthFirstSearch extends AbstractMazeSearch {
    
    private Queue<Cell> queue;

    public MazeBreadthFirstSearch(Maze maze) {
        super(maze);
        queue = new LinkedList<Cell>();
    }

    public int numRemainingCells() {
        return queue.size();
    }

    public Cell findNextCell() {
        return queue.poll();
    }

    public void addCell(Cell cell) {
        queue.offer(cell);
    }

    public static void main(String[] args) {
        Maze testMaze = new Maze(30, 30, 0.2);
        MazeBreadthFirstSearch search = new MazeBreadthFirstSearch(testMaze);
        LinkedList<Cell> path = search.search(testMaze.get(0, 0), testMaze.get(23,23), true, 20);
        System.out.println(path);
    }





}
