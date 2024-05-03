import numpy as np
import random


# SEARCHES
# Minimax search
def minimax_search(state, game):
    player = game.next(state)

    # define labels on each level of the tree
    def max_value(state):
        if game.is_leaf(state):
            return game.goodness(state, player)
        return max([min_value(s) for (_, s) in game.next_state(state)])

    def min_value(state):
        if game.is_leaf(state):
            return game.goodness(state, player)
        return min([max_value(s) for (_, s) in game.next_state(state)])

    # minimax method
    children_values = [(a, min_value(s)) for (a, s) in game.next_state(state)]
    step, value = max(children_values, key=lambda a_s: a_s[1])
    return step


def alfabeta_search(state, game, d=4, cut_test=None, expand=None):
    """Search game tree until defined depth"""
    player = game.next(state)

    def max_value(state, alfa, beta, depth):
        if cut_test(state, depth):
            return expand(state)
        v = float("-inf")
        for (a, s) in game.next_state(state):
            v = max(v, min_value(s, alfa, beta, depth+1))
            if v >= beta:
                return v
            alfa = max(alfa, v)
        return v

    def min_value(state, alfa, beta, depth):
        if cut_test(state, depth):
            return expand(state)
        v = float("inf")
        for (a, s) in game.next_state(state):
            v = min(v, max_value(s, alfa, beta, depth+1))
            if v <= alfa:
                return v
            beta = min(beta, v)
        return v

    # Alfabeta search
    cut_test = cut_test or (lambda state, depth: depth > d or game.is_leaf(state))
    expand = expand or (lambda state: game.goodness(state, player))
    alfa = float("-inf")
    best_step = None
    for a, s in game.next_state(state):
        v = min_value(s, alfa, float("inf"), 0)
        if v > alfa:
            alfa = v
            best_step = a
    return best_step


class QLearningAgent:
    def __init__(self, n_states, n_actions, learning_rate):
        self.n_states = n_states
        self.n_actions = n_actions
        self.learning_rate = learning_rate
        
        self.q_table = np.zeros((n_states, n_actions))
    
    def act(self, state, epsilon):
        # Generate a random number on the [0, 1) interval
        random_int = random.uniform(0, 1)
        
        # We exploit with (1-epsilon) probability
        if random_int > epsilon:
            action = np.argmax(self.q_table[state])
        # We explore with epsilon probability
        else:
            action = random.randint(0, self.n_actions - 1)
        
        return action
    
    def learn(self, state, action, reward, new_state, gamma):
        old_value = self.q_table[state][action]
        new_estimate = reward + gamma * max(self.q_table[new_state]) 
        
        self.q_table[state][action] = old_value + self.learning_rate * (new_estimate - old_value)
