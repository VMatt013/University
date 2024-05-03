import matplotlib.pyplot as plt
import random
from game_search_algorithms import alfabeta_search, minimax_search, QLearningAgent
import numpy as np
import json
from tqdm import tqdm
import pickle


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
            # need to change
            'result': self.result(board, step, state['next']),
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


# We instantiate our Q-learning agent
agent = QLearningAgent(n_states=19683, n_actions=9, learning_rate=0.01)

# We define the necessary variables: the environment, the number of steps that the agent can take in a given
# episode and the states that we have encountered so far
tto = TicTacToe()
N_MAX_STEPS_PER_EPISODE = 9
state_to_id = {}


# We encode the given state to a string that we can use for deciding whether if we have encountered
# this state before or not
def state_to_str(state):
    result = [[" ", " ", " "] for r in range(3)]
    for k, v in state["board"].items():
        result[k[0] - 1][k[1] - 1] = v
    return "\n".join(["|".join(r) for r in result])


def encountered_state(state, memory):
    return state in memory


def play_episodes(n_episodes, max_epsilon=1.0, min_epsilon=0.05, decay_rate=0.0001, gamma=0.9, learn=True, enemy=None):
    global state_to_id
    global tto
    global agent
    
    rewards = []
    epsilon_history = []

    # We go over each episode
    for episode in tqdm(range(n_episodes)):
        done = False

        # We decrease the epsilon and reset the variables
        epsilon = min_epsilon + (max_epsilon - min_epsilon) * \
            np.exp(-decay_rate * episode)
            
        total_reward = 0

        # We reset the environment to the starting state & store it
        state = tto.initial
        state_representation = state_to_str(state)
        if state_representation not in state_to_id:
            state_to_id[state_representation] = len(state_to_id)

        while not done:
            ##########################
            # Q-learning agent's turn
            # We ask our agent for an action
            action = agent.act(
                state=state_to_id[state_representation], epsilon=epsilon)

            # Since our action is just an integer, we transform it into a tuple
            action_tuple = ((action // 3) + 1, (action % 3) + 1)

            # We take the action that the agent signaled & store its representation
            new_state = tto.take_step(state=state, step=action_tuple)
            state_representation = state_to_str(state)
            new_state_representation = state_to_str(new_state)

            # If the game has ended, we get the reward depending on the end results
            if tto.is_leaf(new_state):
                reward = tto.goodness(new_state, 'X')
                done = True
            # If the agent picked a cell that is already occupied, then we terminate the episode
            elif new_state_representation == state_representation:
                reward = -1
                done = True
            # Otherwise the reward of being in any other state is 0
            else:
                reward = 0

            # We check if the states we encountered have been stored or not
            if state_representation not in state_to_id:
                state_to_id[state_representation] = len(state_to_id)
            if new_state_representation not in state_to_id:
                state_to_id[new_state_representation] = len(state_to_id)

            # We update our Q-table and update our total reward for the episode
            if learn:
                agent.learn(state_to_id[state_representation], action,
                                reward, state_to_id[new_state_representation], gamma)
            total_reward += reward

            # Our state is the new state
            state = new_state

            # If done, finish the episode
            if done:
                break
            
            ##########################
            # The enemy's turn
            step = enemy(tto, state)
            state = tto.take_step(step, state)

            # If the enemy managed to arrive at a terminal state
            if tto.is_leaf(state):
                # We evaluate how good the state is for our agent and update its Q-table
                reward = tto.goodness(state, "X")
                total_reward += reward

                agent.learn(state_to_id[state_representation], action,
                                reward, state_to_id[new_state_representation], gamma)
                
                state_representation = state_to_str(state)
                
                break

            # We store the new state
            state_representation = state_to_str(state)
            if state_representation not in state_to_id:
                state_to_id[state_representation] = len(state_to_id)

        if not learn and total_reward < 0:
            print(state_representation)
            print("Total reward:", total_reward)

        rewards.append(total_reward)
        epsilon_history.append(epsilon)

    return rewards, epsilon_history


# We define some parameters like the number of episodes, gamma, etc.
N_EPISODES = 500_000
max_epsilon = 1.0
min_epsilon = 0.05
decay_rate = 0.0001
gamma = 0.9

# We train the agent
rewards, epsilon_history = play_episodes(n_episodes=N_EPISODES, max_epsilon=max_epsilon,
                                         min_epsilon=min_epsilon, decay_rate=decay_rate, gamma=gamma, learn=True, enemy=random_player)

# Summary statistics
print("Wins:\t", len([r for r in rewards if r > 0]))
print("Ties:\t", len([r for r in rewards if r == 0]))
print("Losses:\t", len([r for r in rewards if r < 0]))


# We smoother the results as the training history can greatly oscillate due to the random nature of the agent
def smoothen(data):
    return np.cumsum(data) / np.arange(len(rewards) + 1)[1:]


# We show how epsilon decreased throughout the training process
plt.plot(range(N_EPISODES), epsilon_history)
plt.title("The value of epsilon in each episode")
plt.xlabel("Episode")
plt.ylabel("Epsilon")
plt.show()

# We can check the rewards
plt.plot(rewards, "*")
plt.plot(smoothen(rewards))
plt.show()

# Now we can evaluate how the trained agent performs against a random agent
rewards, epsilon_history = play_episodes(
    n_episodes=10_000, max_epsilon=0, min_epsilon=0, decay_rate=0, gamma=0, learn=False, enemy=random_player)

# Summary statistics
print("Wins:\t", len([r for r in rewards if r > 0]))
print("Ties:\t", len([r for r in rewards if r == 0]))
print("Losses:\t", len([r for r in rewards if r < 0]))

# We can check the rewards as well
plt.plot(rewards, "*")
plt.plot(smoothen(rewards))
plt.show()

# Now we can save the agent
with open("agent.pkl", "wb") as f:
    pickle.dump(agent, f)
with open("state_to_id.pkl", "wb") as f:
    pickle.dump(state_to_id, f)

# load agent
# agent_f = open("agent.pkl", "rb")
# agent = pickle.load(agent_f)
# state_to_id_f = open("state_to_id.pkl", "rb")
# state_to_id = pickle.load(state_to_id_f)
#
# # And now we can play against the agent
# done = False
# state = tto.initial
# state_representation = state_to_str(state)
# total_reward = 0
#
# if state_representation not in state_to_id:
#     state_to_id[state_representation] = len(state_to_id)
#
# while not done:
#     # Q-learning agent's turn
#     action = agent.act(
#         state=state_to_id[state_representation], epsilon=0)
#
#     action_tuple = ((action // 3) + 1, (action % 3) + 1)
#     print('action tuple = ', action_tuple)
#     new_state = tto.take_step(state=state, step=action_tuple)
#     print('new_state = ', new_state)
#     state_representation = state_to_str(state)
#     new_state_representation = state_to_str(new_state)
#
#     if tto.is_leaf(new_state):
#         reward = tto.goodness(new_state, 'X')
#         print("Game over")
#         done = True
#     elif new_state_representation == state_representation:
#         reward = -1
#         print("Game over (illegal step)")
#         done = True
#     else:
#         reward = 0
#
#     if state_representation not in state_to_id:
#         state_to_id[state_representation] = len(state_to_id)
#     if new_state_representation not in state_to_id:
#         state_to_id[new_state_representation] = len(state_to_id)
#
#     total_reward += reward
#
#     state = new_state
#     state_representation = state_to_str(state)
#
#     if done:
#         break
#
#     # We show the current state to the player and ask for a position in the form of x-y
#     print(new_state_representation)
#     print(state)
#     inp = input("Your choice:").split("-")
#     pos_x, pos_y = int(inp[0]), int(inp[1])
#     step = (pos_x, pos_y)
#     print(step)
#
#     state = tto.take_step(step, state)
#     state_representation = state_to_str(state)
#     print(state)
#     if state_representation not in state_to_id:
#         state_to_id[state_representation] = len(state_to_id)
#
#     # If the human player managed to get to a terminal state, then we display the results
#     if tto.is_leaf(state):
#         done = True
#         reward = tto.goodness(state, "X")
#
#         print("Game over. You won.")
#
#         total_reward += reward
#         break
#
# print("Total reward:", total_reward)
