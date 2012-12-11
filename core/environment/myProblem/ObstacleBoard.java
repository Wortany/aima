/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.myProblem;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.XYLocation;

/**
 *
 * @author DE BAZEN
 */
public class ObstacleBoard {
    
    public static Action WEST = new DynamicAction("West");

    public static Action NORTHWEST = new DynamicAction("NorthWest");

    public static Action NORTH = new DynamicAction("North");
    
    public static Action NORTHEAST = new DynamicAction("NorthEast");
    
    public static Action EAST = new DynamicAction("East");
    
    public static Action SOUTHEAST = new DynamicAction("SouthEast");

    public static Action SOUTH = new DynamicAction("South");
    
    public static Action SOUTHWEST = new DynamicAction("SouthWest");
    
    private char[][] board;
    
    private int[] exitLoc;
    
    private int[] agentLoc;
    
    //PUBLIC METHODS
    
    public ObstacleBoard() {
        board = new char[][] { {'S', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' '},
                               {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '},
                               {'X', 'X', 'X', ' ', ' ', ' ', ' ', 'X', ' '},
                               {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' '},
                               {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                               {'X', ' ', ' ', 'X', ' ', 'X', ' ', ' ', ' '},
                               {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                               {'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X', 'X'},
                               {' ', ' ', 'X', ' ', 'G', ' ', ' ', ' ', ' '}};
        exitLoc = getPositionOf('G');
        agentLoc = new int[] {0,0};
    }

    public ObstacleBoard(char[][] board) {
        this.board = new char[board.length][board[0].length];
	for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                this.board[i][j] = board[i][j];
            }
        }
        exitLoc = getPositionOf('G');
        agentLoc = getPositionOf('S');
    }

    public ObstacleBoard(ObstacleBoard copyBoard) {
        this(copyBoard.getBoard());
    }
    
    public char[][] getBoard() {
        return this.board;
    }
    
    public char getValueAt(XYLocation loc) {
        return getValueAt(loc.getXCoOrdinate(), loc.getYCoOrdinate());
    }
    
    public XYLocation getLocationOfAgent() {
        int[] pos = getAgentPosition();
	return new XYLocation(pos[0], pos[1]);
    }
    
    public XYLocation getLocationOfExit() {
        int[] pos = getExitPosition();
	return new XYLocation(pos[0], pos[1]);
    }
    
    public void moveAgentEast() {
        int[] agentPos = getAgentPosition();
	int x = agentPos[0];
	int y = agentPos[1];
	if (y != (board[0].length-1)) {
            char valueOnEast = getValueAt(x, y + 1);
            if(valueOnEast != 'X') {
                setValue(x, y, ' ');
                setValue(x, y + 1, 'S');
                setAgent(x, y + 1);
            }
	}
    }

    public void moveAgentSouthEast() {
        int[] agentPos = getAgentPosition();
	int x = agentPos[0];
	int y = agentPos[1];
	if (y != (board[0].length-1) && x != 0) {
            char valueOnSouthEast = getValueAt(x - 1 , y + 1);
            if(valueOnSouthEast != 'X') {
                setValue(x, y, ' ');
                setValue(x - 1, y + 1, 'S');
                setAgent(x - 1, y + 1);
            }
	}
    }
    
    public void moveAgentSouth() {
        int[] agentPos = getAgentPosition();
	int x = agentPos[0];
	int y = agentPos[1];
	if (x != 0) {
            char valueOnSouth = getValueAt(x - 1 , y);
            if(valueOnSouth != 'X') {
                setValue(x, y, ' ');
                setValue(x - 1, y, 'S');
                setAgent(x - 1, y);
            }
	}
    }
    
    public void moveAgentSouthWest() {
        int[] agentPos = getAgentPosition();
	int x = agentPos[0];
	int y = agentPos[1];
	if (y != 0 && x != 0) {
            char valueOnSouthWest = getValueAt(x - 1 , y - 1);
            if(valueOnSouthWest != 'X') {
                setValue(x, y, ' ');
                setValue(x - 1, y - 1, 'S');
                setAgent(x - 1, y - 1);
            }
	}
    }
    
    public void moveAgentWest() {
        int[] agentPos = getAgentPosition();
	int x = agentPos[0];
	int y = agentPos[1];
	if (y != 0) {
            char valueOnWest = getValueAt(x , y - 1);
            if(valueOnWest != 'X') {
                setValue(x, y, ' ');
                setValue(x, y - 1, 'S');
                setAgent(x, y - 1);
            }
	}
    }
    
    public void moveAgentNorthWest() {
        int[] agentPos = getAgentPosition();
	int x = agentPos[0];
	int y = agentPos[1];
	if (y != 0 && x != (board.length-1)) {
            char valueOnNorthWest = getValueAt(x + 1 , y - 1);
            if(valueOnNorthWest != 'X') {
                setValue(x, y, ' ');
                setValue(x + 1, y - 1, 'S');
                setAgent(x + 1, y - 1);
            }
	}
    }
    
    public void moveAgentNorth() {
        int[] agentPos = getAgentPosition();
	int x = agentPos[0];
	int y = agentPos[1];
	if (x != (board.length-1)) {
            char valueOnNorth = getValueAt(x + 1 , y);
            if(valueOnNorth != 'X') {
                setValue(x, y, ' ');
                setValue(x + 1, y, 'S');
                setAgent(x + 1, y);
            }
	}
    }
    
    public void moveAgentNorthEast() {
        int[] agentPos = getAgentPosition();
	int x = agentPos[0];
	int y = agentPos[1];
	if (y != (board[0].length-1) && x != (board.length-1)) {
            char valueOnNorthEast = getValueAt(x + 1 , y + 1);
            if(valueOnNorthEast != 'X') {
                setValue(x, y, ' ');
                setValue(x + 1, y + 1, 'S');
                setAgent(x + 1, y + 1);
            }
	}
    }
    
    public boolean canMoveAgent(Action where) {
        boolean retVal = true;
	int[] pos = getAgentPosition();
	if (where.equals(WEST)) {
            retVal = (pos[1] != 0 && getValueAt(pos[0], pos[1] - 1) != 'X');
        } else if (where.equals(NORTHWEST)) {
            retVal = (pos[0] != (board.length-1) && pos[1] != 0 && getValueAt(pos[0] + 1, pos[1] - 1) != 'X');
        } else if (where.equals(NORTH)) {
            retVal = (pos[0] != (board.length-1) && getValueAt(pos[0] + 1, pos[1]) != 'X');
        } else if (where.equals(NORTHEAST)) {
            retVal = (pos[0] != (board.length-1) && pos[1] != (board[0].length-1) && getValueAt(pos[0] + 1, pos[1] + 1) != 'X');
        } else if (where.equals(EAST)) {
            retVal = (pos[1] != (board[0].length-1) && getValueAt(pos[0], pos[1] + 1) != 'X');
        } else if (where.equals(SOUTHEAST)) {
            retVal = (pos[0] != 0 && pos[1] != (board[0].length-1) && getValueAt(pos[0] - 1, pos[1] + 1) != 'X');
        } else if (where.equals(SOUTH)) {
            retVal = (pos[0] != 0 && getValueAt(pos[0] - 1, pos[1]) != 'X');
        } else if (where.equals(SOUTHWEST)) {
            retVal = (pos[0] != 0 && pos[1] != 0 && getValueAt(pos[0] - 1, pos[1] - 1) != 'X');
        }
	return retVal;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
	}
	if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
	}
	ObstacleBoard aBoard = (ObstacleBoard) o;
        for (int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if (this.getValueAt(j, j) != aBoard.getValueAt(i,j)) {
                    return false;
                }
            }
	}
	return true;
    }
    
    @Override
    public int hashCode() {
        int[] pos = getAgentPosition();
        int result = pos[0]*17 + pos[1]*6047;
	return result;
    }

    @Override
    public String toString() {
        String retVal = "";
        for(int i = board.length-1; i >= 0; i--) {
            retVal = retVal.concat("|");
            for(int j = 0; j < board[0].length; j++) {
                retVal = retVal.concat(String.valueOf(board[i][j])).concat("|");
            }
            retVal = retVal.concat("\n");
        }
	return retVal;
    }
    
    //PRIVATE METHODS
    
    private char getValueAt(int x, int y) {
        return board[x][y];
    }
    
    private int[] getPositionOf(char val) {
        int[] retVal = {-1, -1};
	for (int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if (board[i][j] == val) {
                    retVal[0] = i;
                    retVal[1] = j;
                    return retVal;
                }
            }
	}
	return retVal;
    }
    
    private int[] getAgentPosition() {
        return agentLoc;
    }
    
    private int[] getExitPosition() {
        return exitLoc;
    }
    
    private void setValue(int x, int y, char val) {
	board[x][y] = val;
    }
    
    private void setAgent(int x, int y) {
        agentLoc[0] = x;
        agentLoc[1] = y;
    }
}
