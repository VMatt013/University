from problems.hanoi.state import Hanoi
from problems.hanoi.operator import Operator
from search.informed import A_Alg, get_solution

if __name__ == "__main__":
    alg = A_Alg(delay_seconds=0)

    start_state = Hanoi()

    operators = [
        Operator(from_="A", to="B"),
        Operator(from_="A", to="C"),
        Operator(from_="B", to="A"),
        Operator(from_="B", to="C"),
        Operator(from_="C", to="A"),
        Operator(from_="C", to="B")
    ]

    result = alg.search(start_state, operators, cost_function=lambda operator, node: 1, heuristic_function=lambda node: 3-len(node.towers["C"]))