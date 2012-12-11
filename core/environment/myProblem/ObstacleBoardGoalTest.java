/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.myProblem;

import aima.core.search.framework.GoalTest;
import aima.core.util.datastructure.XYLocation;

/**
 *
 * @author DE BAZEN
 */
public class ObstacleBoardGoalTest implements GoalTest{
        
    public boolean isGoalState(Object state){
        ObstacleBoard ob = (ObstacleBoard) state;
        XYLocation agentLoc = ob.getLocationOfAgent();
        XYLocation exitLoc = ob.getLocationOfExit();
        return agentLoc.equals(exitLoc);
    }
}
