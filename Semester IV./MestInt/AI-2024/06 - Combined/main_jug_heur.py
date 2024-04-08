from problems.jug.state import Jug
from problems.jug.operator import Operator
from search.informed import A_Alg, get_solution
import math

if __name__ == "__main__":
    alg = A_Alg(delay_seconds=0)

    start_state = Jug([0, 0, 5])

    operators = [
        Operator(from_=0, to=1),
        Operator(from_=0, to=2),
        Operator(from_=1, to=0),
        Operator(from_=1, to=2),
        Operator(from_=2, to=0),
        Operator(from_=2, to=1)
    ]

    result = alg.search(start_state, operators, cost_function=lambda operator, node: 1, heuristic_function=lambda node: math.fabs(4-node.contents[2]))

