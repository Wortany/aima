/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.myProblem;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

/**
 *
 * @author DE BAZEN
 */
public class DistanceToExitHeuristicFunction implements HeuristicFunction{
    

    @Override
    public double h(Object state) {
        ObstacleBoard ob = (ObstacleBoard) state;
        return getNumberOfTilesFromExit(ob);
    }
    
    private int getNumberOfTilesFromExit(ObstacleBoard ob) {
        int distance = 0;
        XYLocation agentLoc = ob.getLocationOfAgent();
        XYLocation exitLoc = ob.getLocationOfExit();
        int xdistance = agentLoc.getXCoOrdinate() - exitLoc.getXCoOrdinate();
        int ydistance = agentLoc.getYCoOrdinate() - exitLoc.getYCoOrdinate();
        distance = (int) Math.sqrt(Math.pow(xdistance, 2) + Math.pow(ydistance, 2));
        //distance = Math.abs(xdistance) + Math.abs(ydistance);
        return distance;
    }
    
}
