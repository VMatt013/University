from problems.hanoi.state import Hanoi
from problems.hanoi.operator import Operator
from search.uninformed import BackTrack, BackTrackWithCircleMonitoring, DepthSearchAlgorithm, BreadthSearchAlgorithm, get_solution

if __name__ == "__main__":
    #alg = BreadthSearchAlgorithm(delay_seconds=0)
    alg = DepthSearchAlgorithm(delay_seconds=0)

    start_state = Hanoi()

    operators = [
        Operator(from_="A", to="B"),
        Operator(from_="A", to="C"),
        Operator(from_="B", to="A"),
        Operator(from_="B", to="C"),
        Operator(from_="C", to="A"),
        Operator(from_="C", to="B"),
    ]

    result = alg.search(start_state, operators)


