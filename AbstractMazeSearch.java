/*
 * Name: Ahmed Rafique Choudhry
 * CS 231 Section A
 * Date: 05/01/2024
 * Class Purpose: Abstract Maze Search - The abstract class that we will use to implement our particular searching algorithms.
 */

import java.awt.Graphics;
import java.awt.Color;

public abstract class AbstractMazeSearch {
    
    private Maze maze;
    private Cell start;
    private Cell target;
    private Cell cur;
    private MazeSearchDisplay searchDisplay;

    public AbstractMazeSearch(Maze maze) {
        this.maze = maze;
        this.start = null;
        this.target = null;
        this.cur = null;
    }

    public Maze getMaze() {
        return this.maze;
    }

    public void setTarget(Cell target) {
        this.target = target;
        this.target.setType();
    }

    public Cell getTarget() {
        return this.target;
    }

    public void setCur(Cell cell) {
        this.cur = cell;
    }

    public Cell getCur() {
        return this.cur;
    }

    public void setStart(Cell start) {
        this.start = start;
        this.start.setPrev(start);
    }

    public Cell getStart() {
        return this.start;
    }

    public void reset() {
        this.start = null;
        this.target = null;
        this.cur = null;
    }

    public LinkedList<Cell> traceback(Cell cell) {
        LinkedList<Cell> path = new LinkedList<Cell>();
        while (!cell.equals(start)) {
            cell = cell.getPrev();
            path.add(cell);
        }
        return path;
    }

    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) {

        setStart(start);
        setTarget(target);
        setCur(start);

        addCell(start);

        if (display == true) {
            this.searchDisplay = new MazeSearchDisplay(this, 20);
        } else if (display == false) {
            this.searchDisplay = null;
        }
        while (numRemainingCells() > 0) {
            setCur(findNextCell());
            LinkedList<Cell> neighbors = this.maze.getNeighbors(this.cur);

            for (Cell neighbor : neighbors) {
                if (neighbor.getPrev() == null) {
                    neighbor.setPrev(cur);
                    addCell(neighbor);
                    if (neighbor == getTarget()) {
                        return traceback(getTarget());
                    }
                }
            }
            if (display == true) {
                try{ Thread.sleep(delay); }
                catch(InterruptedException e) {} ;
                searchDisplay.repaint();
            }
        }
        return null;
    }

    public abstract Cell findNextCell();

    public abstract void addCell(Cell next);

    public abstract int numRemainingCells();

    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);
    
        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }
    


}


