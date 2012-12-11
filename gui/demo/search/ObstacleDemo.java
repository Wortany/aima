/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.gui.demo.search;

import aima.core.agent.Action;
import aima.core.environment.myProblem.DistanceToExitHeuristicFunction;
import aima.core.environment.myProblem.NumberOfObstaclesHeuristicFunction;
import aima.core.environment.myProblem.ObstacleBoard;
import aima.core.environment.myProblem.ObstacleBoardGoalTest;
import aima.core.environment.myProblem.ObstacleFactory;
import aima.core.environment.myProblem.ObstacleFunctionFactory;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.swing.JFileChooser;

/**
 *
 * @author Wortany
 */
public class ObstacleDemo {
    
    public static void main(String[] args) {
        while(true) {
            try {
                System.out.print("Gelieve een dimensie in te geven of 'exit' om af te sluiten: ");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                String msg = in.readLine();
                if(msg.equals("exit")) {
                    System.exit(0);
                }
                int dimension = Integer.valueOf(msg);
                ObstacleBoard board = ObstacleFactory.getRandomObstacleBoard(dimension);
                printBoard(board);
                boolean sameBoard = true;
                while(sameBoard) {
                    System.out.print("Gelieve een zoekalgoritme te kiezen uit:\n"
                            + "BFSTree, BFSGraph, DLSGraph, A*, A*2, Allemaal.\n"
                            + "Of geef 'Show' in om het bord nogmaals te zien of 'Exit' om een andere dimensie te kiezen\n"
                            + "Je kan ook 'Print' meegeven om het bord te exporteren in een bestand.\n"
                            + "En dus ook een bord importeren met 'Import.' -> ");
                    msg = in.readLine();
                    msg = msg.toLowerCase();
                    boolean correct = false;
                    if(msg.equals("bfstree") || msg.equals("allemaal")) {
                        correct = true;
                        ObstacleBFSTreeDemo(board);
                    }
                    if(msg.equals("bfsgraph") || msg.equals("allemaal")) {
                        correct = true;
                        ObstacleBFSGraphDemo(board);
                    }
                    if(msg.equals("dlsgraph") || msg.equals("allemaal")) {
                        correct = true;
                        ObstacleDLSDemo(board, dimension);
                    }
                    if(msg.equals("a*") || msg.equals("allemaal")) {
                        correct = true;
                        ObstacleAStarDemo(board);
                    }
                    if(msg.equals("a*2") || msg.equals("allemaal")) {
                        correct = true;
                        ObstacleAStar2Demo(board);
                    }
                    if(msg.equals("show")) {
                        printBoard(board);
                    }
                    if(msg.equals("print")) {
                        realPrintBoard(board);
                    }
                    if(msg.equals("import")) {
                        JFileChooser fc = new JFileChooser();
                        fc.showOpenDialog(fc);
                        board = importBoard(fc.getSelectedFile());
                        dimension = board.getBoard().length;
                        printBoard(board);
                    }
                    if(msg.equals("exit")) {
                        correct = true;
                        sameBoard = false;
                    }
                    if(!correct) {
                        System.out.println("Gelieve een geldige optie in te geven!");
                    }
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void ObstacleBFSTreeDemo(ObstacleBoard board) {
        System.out.println("\nObstacleDemo Breadth First Tree Search -->");
        try {
            Problem problem = new Problem(board, ObstacleFunctionFactory
                        .getActionsFunction(), ObstacleFunctionFactory
			.getResultFunction(), new ObstacleBoardGoalTest(),
                        ObstacleFunctionFactory.getStepCostFunction());
            Search search = new BreadthFirstSearch(new TreeSearch());
            executor(problem, search);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
    
    private static void ObstacleBFSGraphDemo(ObstacleBoard board) {
        System.out.println("\nObstacleDemo Breadth-First Graph Search -->");
        try {
            Problem problem = new Problem(board, ObstacleFunctionFactory
                        .getActionsFunction(), ObstacleFunctionFactory
			.getResultFunction(), new ObstacleBoardGoalTest(),
                        ObstacleFunctionFactory.getStepCostFunction());
            Search search = new BreadthFirstSearch(new GraphSearch());
            executor(problem, search);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
    
    private static void ObstacleDLSDemo(ObstacleBoard board, int dimension) {
        System.out.println("\nObstacleDemo Depth-First Limited Search (Dimension^2) -->");
        try {
            Problem problem = new Problem(board, ObstacleFunctionFactory
                        .getActionsFunction(), ObstacleFunctionFactory
			.getResultFunction(), new ObstacleBoardGoalTest(),
                        ObstacleFunctionFactory.getStepCostFunction());
            Search search = new DepthLimitedSearch((int)(Math.pow(dimension, 2)/4));
            executor(problem, search);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
    
    private static void ObstacleAStarDemo(ObstacleBoard board) {
        System.out.println("\nObstacleDemo A* Search -->");
        try {
            Problem problem = new Problem(board, ObstacleFunctionFactory
                        .getActionsFunction(), ObstacleFunctionFactory
			.getResultFunction(), new ObstacleBoardGoalTest(),
                        ObstacleFunctionFactory.getStepCostFunction());
            Search search = new AStarSearch(new GraphSearch(), new DistanceToExitHeuristicFunction());
            executor(problem, search);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
    
    private static void ObstacleAStar2Demo(ObstacleBoard board) {
        System.out.println("\nObstacleDemo A* Search -->");
        try {
            Problem problem = new Problem(board, ObstacleFunctionFactory
                        .getActionsFunction(), ObstacleFunctionFactory
			.getResultFunction(), new ObstacleBoardGoalTest(),
                        ObstacleFunctionFactory.getStepCostFunction());
            Search search = new AStarSearch(new GraphSearch(), new NumberOfObstaclesHeuristicFunction());
            executor(problem, search);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
    
    private static void printInstrumentation(Properties properties) {
        Iterator<Object> keys = properties.keySet().iterator();
	while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
	}
    }

    private static void printActions(List<Action> actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = actions.get(i).toString();
            System.out.println(action);
	}
    }
    
    private static void printBoard(ObstacleBoard board) {
        System.out.println(board.toString());
    }
    
    private static void executor(Problem problem, Search search) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Times to run?");
        String msg = in.readLine();
        int times = Integer.valueOf(msg);
        long time = 0;
        long startTime = System.currentTimeMillis();
        SearchAgent agent = new SearchAgent(problem, search);
        long endTime = System.currentTimeMillis();
        time = endTime-startTime;
        if(times > 1) {
            for(int i = 0; i < times; i++) {
                startTime = System.currentTimeMillis();
                agent = new SearchAgent(problem, search);
                endTime = System.currentTimeMillis();
                time += (endTime-startTime);
            }
            time = time/times;
        }
        Properties instrumentation = agent.getInstrumentation();
        instrumentation.setProperty("Uitvoeringstijd (ms)", String.valueOf(time));
        printActions(agent.getActions());
        printInstrumentation(instrumentation);
    }
    
    private static void realPrintBoard(ObstacleBoard board) {
        char[][] state = board.getBoard();
        try {
            PrintWriter out = new PrintWriter("board" + state.length + ".txt");
            for(int i = state.length-1; i >= 0; i--) {
                for(int j = 0; j < state[0].length; j++) {
                    out.print(state[i][j]);
                }
                out.println();
            }
            out.flush();
            out.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static ObstacleBoard importBoard(File board) {
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader (new FileInputStream(board)));
            reader.mark(7000);
            String line = reader.readLine();
            line = line.replace("|", "");
            reader.reset();
            char[][] state = new char[line.length()][line.length()];
            for(int i = line.length() -1; i >= 0; i--) {
                line = reader.readLine();
                line = line.replace("|", "");
                for(int j = 0; j < line.length(); j++) {
                    if(line.charAt(j) != '|') {
                        state[i][j] = line.charAt(j);
                    }
                }
            }
            reader.close();
            return new ObstacleBoard(state);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
        
}
