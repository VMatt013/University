import random
from game_search_algorithms import alfabeta_search, minimax_search


# GAME BASE CLASS
class Game:
    def legal_steps(self, state):
        """Steps that can be made in given state."""
        raise NotImplementedError()

    def take_step(self, step, state):  # NOQA
        """Result of taking step in given state."""
        raise NotImplementedError

    def goodness(self, state, player):
        """Goodness measure of the state for the player."""
        raise NotImplementedError()

    def is_leaf(self, state):
        """Is the node a terminal node."""
        return not self.legal_steps(state)

    def next(self, state):
        """Return next player."""
        return state['next']

    def print(self, state):
        """Print current state."""
        print(state)

    def next_state(self, state):
        """Return next (step, state) list."""
        return [(step, self.take_step(step, state))
                for step in self.legal_steps(state)]

    def __repr__(self):
        """Print the name of the game."""
        return '<%s>' % self.__class__.__name__


# TIC-TAC TOE CLASS
class TicTacToe(Game):
    """3x3 version."""

    def __init__(self, h=3, v=3, k=3):
        """Base of the game"""
        self.h = 3
        self.v = 3
        self.k = 3
        steps = [(x, y) for x in range(1, h + 1) for y in range(1, v + 1)]
        print(steps)
        print(type(steps))
        print(steps[0])
        self.initial = {'next': 'X',
                        'result': 0,
                        'board': {},
                        'steps': steps}

    def legal_steps(self, state):
        """We can step on every empty cell"""
        return state['steps']

    def take_step(self, step, state):
        """Effect of the step"""
        if step not in state['steps']:
            return state
        board = state['board'].copy()
        board[step] = state['next']
        steps = list(state['steps'])
        steps.remove(step)
        return {
            'next': 'X' if state['next'] == 'O' else 'O',
            'result': self.result(board, step, state['next']),  # need to change
            'board': board,
            'steps': steps
        }

    def result(self, board, step, player):
        """If X wins with this step then return 1. If O wins with this then return -1. Else return 0."""
        if (self.check_triples(board, step, player, (0, 1)) or self.check_triples(board, step, player, (1, 0)) or
                self.check_triples(board, step, player, (1, -1)) or self.check_triples(board, step, player, (1, 1))):
            return 1 if player == 'X' else -1
        return 0

    def check_triples(self, board, step, player, direction):
        """Check for triples in a direction."""
        delta_x, delta_y = direction
        x, y = step
        n = 0
        while board.get((x, y)) == player:
            n += 1
            x, y = x + delta_x, y + delta_y
        x, y = step
        while board.get((x, y)) == player:
            n += 1
            x, y = x - delta_x, y - delta_y
        n -= 1
        return n >= self.k

    def goodness(self, state, player):
        """X goodness: 1, if it wins; -1, if it loses, 0 if draw."""
        return state['result'] if player == "X" else -state['result']

    def is_leaf(self, state):
        """If someone won or the table is full it will be the end of the game."""
        return state['result'] != 0 or len(state['steps']) == 0

    def print(self, state):
        """Let's see the current state."""
        board = state['board']
        for x in range(1, self.h + 1):
            for y in range(1, self.v + 1):
                print(board.get((x, y), '.'), end=" ")
            print()
        print('Result of the game: ', state['result'])
        print()


# PLAYERS
def random_player(game, state):
    """Randomly choose between options"""
    return random.choice(game.legal_steps(state))


def alfabeta_player(game, state):
    """Search in game tree"""
    return alfabeta_search(state, game)


def minimax_player(game, state):
    """Search in game tree"""
    return minimax_search(state, game)


def play_game(game, *players):
    state = game.initial
    game.print(state)
    while True:
        for player in players:
            step = player(game, state)
            state = game.take_step(step, state)
            game.print(state)
            if game.is_leaf(state):
                end_result = game.goodness(state, 'X')
                return "X wins" if end_result == 1 else "O wins" if end_result == -1 else "Draw"


tto = TicTacToe()

# Test if playing works
# play_game(tto, random_player, random_player)

# Demonstrate the power of the search algorithms
# you can comment out the game.print(state) lines in the play_game function for this
for i in range(1):
    #print(play_game(tto, random_player, random_player))  # outcome will be random (starting player has the edge)
    print(play_game(tto, alfabeta_player, random_player))  # X will always win
    # print(play_game(tto, random_player, alfabeta_player))  # O will most likely win
    # print(play_game(tto, alfabeta_player, alfabeta_player))  # game will always end in draw
