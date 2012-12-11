/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.myProblem;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

/**
 *
 * @author De Bazen
 */
public class NumberOfObstaclesHeuristicFunction implements HeuristicFunction{

    @Override
    public double h(Object state) {
        ObstacleBoard ob = (ObstacleBoard) state;
        return getNumberOfObstacles(ob);
    }
    
    private int getNumberOfObstacles(ObstacleBoard ob){
        int nrOfObstacles = 0;
        int topX = 0;
        int botX = 0;
        int topY = 0;
        int botY = 0;
        XYLocation agentLoc = ob.getLocationOfAgent();
        XYLocation exitLoc = ob.getLocationOfExit();
        if(agentLoc.equals(exitLoc)){
            nrOfObstacles = 0;
        }
        else{
            if (agentLoc.getXCoOrdinate() >= exitLoc.getXCoOrdinate()){
                topX = agentLoc.getXCoOrdinate();
                botX = exitLoc.getXCoOrdinate();
            }
            else {
                topX = exitLoc.getXCoOrdinate();
                botX = agentLoc.getXCoOrdinate();  
            }
            if (agentLoc.getYCoOrdinate() >= exitLoc.getYCoOrdinate()){
                topY = agentLoc.getYCoOrdinate();
                botY = exitLoc.getYCoOrdinate();
            }
            else{
                topY = exitLoc.getYCoOrdinate();
                botY = agentLoc.getYCoOrdinate();
            }
            for (int i = botX;i <= topX;i++){
                for(int j = botY;j <= topY;j++){
                    XYLocation pos = new XYLocation(i,j);
                    if (ob.getValueAt(pos) =='X'){
                        nrOfObstacles++;
                    }
                }
            }
        }
        int nrOfTilesToExit = getNumberOfTilesFromExit(ob); 
        return nrOfObstacles + nrOfTilesToExit;
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
