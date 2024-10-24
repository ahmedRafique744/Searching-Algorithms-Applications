public class MazeDepthFirstSearch extends AbstractMazeSearch{
    
    private Stack<Cell> stack;

    public MazeDepthFirstSearch(Maze maze) {
        super(maze);
        stack = new LinkedList<Cell>();
    }

    public int numRemainingCells() {
        return stack.size();
    }

    public void addCell(Cell next) {
        stack.push(next);
    }

    public Cell findNextCell() {
        return stack.pop();
    }

    public static void main(String[] args) {
        Maze testMaze = new Maze(30, 30, 0.2);
        MazeDepthFirstSearch search = new MazeDepthFirstSearch(testMaze);
        LinkedList<Cell> path = search.search(testMaze.get(0, 0), testMaze.get(8,8), true, 20);
        System.out.println(path);
    }
}
