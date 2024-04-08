from problems.queens.state import NQueens
from problems.queens.operator import Operator
from search.uninformed import BackTrack, BackTrackWithCircleMonitoring, DepthSearchAlgorithm, BreadthSearchAlgorithm, get_solution

if __name__ == "__main__":
    #alg = BreadthSearchAlgorithm(delay_seconds=0)
    alg = DepthSearchAlgorithm(delay_seconds=0)

    start_state = NQueens(n=4)

    operators = []

    for row in range(start_state.n):
        for col in range(start_state.n):
            operators.append(Operator(row, col))

    result = alg.search(start_state, operators)


