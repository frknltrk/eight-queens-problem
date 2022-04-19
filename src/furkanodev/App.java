/*
THE GOAL STATE
-------Q			
-Q------
---Q----
Q-------
------Q-
----Q---
--Q-----
-----Q--
*/

package furkanodev;
import aima.core.environment.nqueens.NQueensBoard;
import aima.core.environment.nqueens.NQueensFunctions;
import aima.core.environment.nqueens.QueenAction;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.uninformed.*;
import aima.core.search.informed.*;

import aima.core.util.datastructure.XYLocation;

import java.util.List;
import java.util.Optional;

public class App {
	
	public static NQueensBoard createBoard() {
		NQueensBoard board = new NQueensBoard(8);
		board.addQueenAt(new XYLocation(0, 3));
		board.addQueenAt(new XYLocation(1, 1));
		return board;
	}

	public static void main(String[] args) {
		Problem<NQueensBoard, QueenAction> problem = new GeneralProblem<>(createBoard(),
		TailoredNQF::getIFActions, TailoredNQF::getResult, TailoredNQF::testGoal);
		
		SearchForActions<NQueensBoard, QueenAction> bfsearch = new BreadthFirstSearch<>(new GraphSearch<>());
		SearchForActions<NQueensBoard, QueenAction> dfsearch = new DepthFirstSearch<>(new GraphSearch<>());
		SearchForActions<NQueensBoard, QueenAction> dlsearch = new DepthLimitedSearch<>(8);
		SearchForActions<NQueensBoard, QueenAction> idsearch = new IterativeDeepeningSearch<>();
		SearchForActions<NQueensBoard, QueenAction> ucsearch = new UniformCostSearch<>(new GraphSearch<>());

//		SearchForActions<NQueensBoard, QueenAction> gbfsearch = new GreedyBestFirstSearch<>(new GraphSearch<>(), TailoredNQF::getNumberOfAttackingPairs);
//		SearchForActions<NQueensBoard, QueenAction> assearch = new AStarSearch<>(new GraphSearch<>(), TailoredNQF::getNumberOfAttackingPairs);
		
		System.out.println("BFS");
		Optional<List<QueenAction>> actions1 = bfsearch.findActions(problem);
	    actions1.ifPresent(qActions -> qActions.forEach(System.out::println));
	    System.out.println(bfsearch.getMetrics());
	    System.out.println("DFS");
		Optional<List<QueenAction>> actions2 = dfsearch.findActions(problem);
	    actions2.ifPresent(qActions -> qActions.forEach(System.out::println));
	    System.out.println(dfsearch.getMetrics());
	    System.out.println("DLS");
		Optional<List<QueenAction>> actions3 = dlsearch.findActions(problem);
	    actions3.ifPresent(qActions -> qActions.forEach(System.out::println));
	    System.out.println(dlsearch.getMetrics());
	    System.out.println("IDS");
		Optional<List<QueenAction>> actions4 = idsearch.findActions(problem);
	    actions4.ifPresent(qActions -> qActions.forEach(System.out::println));
	    System.out.println(idsearch.getMetrics());
	    System.out.println("UCS");
		Optional<List<QueenAction>> actions5 = ucsearch.findActions(problem);
	    actions5.ifPresent(qActions -> qActions.forEach(System.out::println));
	    System.out.println(ucsearch.getMetrics());
	}

}

/*
Action[name=placeQueenAt, location=(2, 6)]
Action[name=placeQueenAt, location=(3, 2)]
Action[name=placeQueenAt, location=(4, 5)]
Action[name=placeQueenAt, location=(5, 7)]
Action[name=placeQueenAt, location=(6, 4)]
Action[name=placeQueenAt, location=(7, 0)]
*/