import time

def get_solution(goal_node):
    history = []

    n = goal_node

    while n is not None:
        history.append(n.operator)
        n = n.parent

    return history[::-1][1:]

class Node:
    def __init__(self, state, parent, operator, cost, heuristic):
        self.state = state
        self.parent = parent
        self.operator = operator
        self.cost = cost
        self.heuristic = heuristic

    def __str__(self):
        return f"Node [state={str(self.state)}, "+\
        f"cost={self.cost}, heuristic={self.heuristic}, "+\
        f"operator={self.operator}]"
    
def cost_function(operator, state):
    return 1

class A_Alg:
    def __init__(self, delay_seconds=1):
        self.delay_seconds = delay_seconds

    def extend(self, cost_function, heuristic_function, node,
               open_nodes, closed_nodes, operators):
        for o in operators:
            if o.precondition_fulfilled(node.state):
                state = o.use(node.state)

                state_in_open_nodes = next(filter(lambda n: n.state == state,
                                            open_nodes), None)
                state_in_closed_nodes = next(filter(lambda n: n.state == state,
                                            closed_nodes), None)

                if state_in_open_nodes is None and \
                    state_in_closed_nodes is None:
                    
                    new_node = Node(
                        state=state,
                        parent=None,
                        cost=node.cost + cost_function(o, node.state),
                        heuristic=heuristic_function(state)
                    )

                    open_nodes.append(new_node)
                else:
                    new_cost = node.cost + cost_function(o, node.state)

                    if state_in_open_nodes is not None:
                        if new_cost < state_in_open_nodes.cost:
                            state_in_open_nodes.parent = node
                            state_in_open_nodes.operator = o
                            state_in_open_nodes.cost = new_cost
                    else:
                        if new_cost < state_in_closed_nodes.cost:
                            state_in_closed_nodes.parent = node
                            state_in_closed_nodes.operator = o
                            state_in_closed_nodes.cost = new_cost

                            closed_nodes.remove(state_in_closed_nodes)
                            open_nodes.append(state_in_closed_nodes)

        open_nodes.remove(node)
        closed_nodes.append(node)

    def search(self, start_state, operators, cost_function,
               heuristic_function):

        new_node = Node(
            state=start_state,
            operator=None,
            cost=0,
            heuristic=heuristic_function(start_state)
        )

        open_nodes = [new_node]
        closed_nodes = []

        while True:
            if len(open_nodes) == 0:
                break
                
            min_total_cost = min([n.cost + n.heuristic 
                                  for n in open_nodes])
            node = next(filter(
                lambda n: n.cost + n.heuristic == min_total_cost
                , open_nodes))
            
            if node.state.is_goal_state():
                break

            self.extend(cost_function, heuristic_function, node,
                        open_nodes, closed_nodes, operators)
            
            time.sleep(self.delay_seconds)
            print("Node:", node)
        
        if len(open_nodes) != 0:
            print("Solution found:")
            print(get_solution(node))
        else:
            print("No solution found during search.")

        return node
                    



        
