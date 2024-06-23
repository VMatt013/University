package game;

import javafx.application.Application;
import model.GameModel;
import model.Map;
import model.Position;
import puzzle.TwoPhaseMoveState;
import puzzle.solver.BreadthFirstSearch;
public class Main {

    public static void main(String[] args) {
        BreadthFirstSearch<TwoPhaseMoveState.TwoPhaseMove<Position>> bfs = new BreadthFirstSearch<>();
        bfs.solveAndPrintSolution(new GameModel(Map.Medium));
        Application.launch(GameApplication.class, args);
    }

}
