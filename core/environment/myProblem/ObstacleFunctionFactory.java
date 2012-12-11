/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.myProblem;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.StepCostFunction;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author DE BAZEN
 */
public class ObstacleFunctionFactory {
    private static ActionsFunction _actionsFunction = null;
    private static ResultFunction _resultFunction = null;
    private static StepCostFunction _stepCostFunction = null;

    public static ActionsFunction getActionsFunction() {
        if (null == _actionsFunction) {
            _actionsFunction = new ObstacleFunctionFactory.ObActionsFunction();
	}
	return _actionsFunction;
    }

    public static ResultFunction getResultFunction() {
        if (null == _resultFunction) {
            _resultFunction = new ObstacleFunctionFactory.ObResultFunction();
	}
	return _resultFunction;
    }
    
    public static StepCostFunction getStepCostFunction() {
        if (null == _stepCostFunction) {
            _stepCostFunction = new ObstacleFunctionFactory.ObStepCostFunction();
	}
	return _stepCostFunction;
    }

    private static class ObActionsFunction implements ActionsFunction {
        public Set<Action> actions(Object state) {
            ObstacleBoard board = (ObstacleBoard) state;
            Set<Action> actions = new LinkedHashSet<Action>();
            if(board.canMoveAgent(ObstacleBoard.NORTH)) {
                actions.add(ObstacleBoard.NORTH);
            }
            if(board.canMoveAgent(ObstacleBoard.NORTHEAST)) {
                actions.add(ObstacleBoard.NORTHEAST);
            }
            if(board.canMoveAgent(ObstacleBoard.EAST)) {
                actions.add(ObstacleBoard.EAST);
            }
            if(board.canMoveAgent(ObstacleBoard.SOUTHEAST)) {
                actions.add(ObstacleBoard.SOUTHEAST);
            }
            if(board.canMoveAgent(ObstacleBoard.SOUTH)) {
                actions.add(ObstacleBoard.SOUTH);
            }
            if(board.canMoveAgent(ObstacleBoard.SOUTHWEST)) {
                actions.add(ObstacleBoard.SOUTHWEST);
            }
            if(board.canMoveAgent(ObstacleBoard.WEST)) {
                actions.add(ObstacleBoard.WEST);
            }
            if(board.canMoveAgent(ObstacleBoard.NORTHWEST)) {
                actions.add(ObstacleBoard.NORTHWEST);
            }
            return actions;
	}
    }
    
    private static class ObResultFunction implements ResultFunction {
        public Object result(Object s, Action a) {
            ObstacleBoard board = (ObstacleBoard) s;
            if (ObstacleBoard.NORTH.equals(a) && board.canMoveAgent(ObstacleBoard.NORTH)) {
                ObstacleBoard newBoard = new ObstacleBoard(board);
		newBoard.moveAgentNorth();
		return newBoard;
            } else if (ObstacleBoard.NORTHEAST.equals(a) && board.canMoveAgent(ObstacleBoard.NORTHEAST)) {
                ObstacleBoard newBoard = new ObstacleBoard(board);
		newBoard.moveAgentNorthEast();
		return newBoard;
            } else if (ObstacleBoard.EAST.equals(a) && board.canMoveAgent(ObstacleBoard.EAST)) {
                ObstacleBoard newBoard = new ObstacleBoard(board);
		newBoard.moveAgentEast();
		return newBoard;
            } else if (ObstacleBoard.SOUTHEAST.equals(a) && board.canMoveAgent(ObstacleBoard.SOUTHEAST)) {
                ObstacleBoard newBoard = new ObstacleBoard(board);
		newBoard.moveAgentSouthEast();
		return newBoard;
            } else if (ObstacleBoard.SOUTH.equals(a) && board.canMoveAgent(ObstacleBoard.SOUTH)) {
                ObstacleBoard newBoard = new ObstacleBoard(board);
		newBoard.moveAgentSouth();
		return newBoard;
            } else if (ObstacleBoard.SOUTHWEST.equals(a) && board.canMoveAgent(ObstacleBoard.SOUTHWEST)) {
                ObstacleBoard newBoard = new ObstacleBoard(board);
		newBoard.moveAgentSouthWest();
		return newBoard;
            } else if (ObstacleBoard.WEST.equals(a) && board.canMoveAgent(ObstacleBoard.WEST)) {
                ObstacleBoard newBoard = new ObstacleBoard(board);
		newBoard.moveAgentWest();
		return newBoard;
            } else if (ObstacleBoard.NORTHWEST.equals(a) && board.canMoveAgent(ObstacleBoard.NORTHWEST)) {
                ObstacleBoard newBoard = new ObstacleBoard(board);
		newBoard.moveAgentNorthWest();
		return newBoard;
            }
            // The Action is not understood or is a NoOp
            // the result will be the current state.
            return s;
	}
    }
    
    private static class ObStepCostFunction implements StepCostFunction {
        
        private static double horizontalVerticalCost = 1.0;
        private static double diagonalCost = Math.sqrt(2.0);
                
        public double c(Object state, Action action, Object nextState) {
            if(ObstacleBoard.NORTH.equals(action)) {
                return horizontalVerticalCost;
            } else if(ObstacleBoard.NORTHEAST.equals(action)) {
                return diagonalCost;
            } else if(ObstacleBoard.EAST.equals(action)) {
                return horizontalVerticalCost;
            } else if(ObstacleBoard.SOUTHEAST.equals(action)) {
                return diagonalCost;
            } else if(ObstacleBoard.SOUTH.equals(action)) {
                return horizontalVerticalCost;
            } else if(ObstacleBoard.SOUTHWEST.equals(action)) {
                return diagonalCost;
            } else if(ObstacleBoard.WEST.equals(action)) {
                return horizontalVerticalCost;
            } else if(ObstacleBoard.NORTHWEST.equals(action)) {
                return diagonalCost;
            }
            return 0;
	}
    }
}
