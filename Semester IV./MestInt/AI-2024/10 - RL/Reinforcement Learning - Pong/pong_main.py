import pygame
import numpy as np
import random
import matplotlib.pyplot as plt
from tqdm import tqdm
import pickle

# Color definitions
BLACK = (0,0,0)
WHITE = (255,255,255)
RED = (255,0,0)
GREEN = (0,255,0)
BLUE = (0,0,255)

# Signs for decoding the game
# We have 3 signs:
# - empty (a cell belonging neither to a ball nor to a player)
# - ball
# - player (the rectangle)
SIGN_EMPTY = " "
SIGN_BALL = "o"
SIGN_PLAYER = "x"

# This will change how big the "window will be"
SPACE_SIZE = (20, 20)
# We will use this to up-scale the UI window meaning that the UI window will be of the size SPACE_SIZE * ZOOM_SIZE
ZOOM_SIZE = 10

# We have 3 actions that the agent can take at any given time:
# - idle (not changing position)
# - left
# - right
ACTION_IDLE = "IDLE"
ACTION_LEFT = "LEFT"
ACTION_RIGHT = "RIGHT"

ACTIONS = [
    ACTION_IDLE,
    ACTION_LEFT,
    ACTION_RIGHT
]

# The class representing our Q-learning Agent
class QLearningAgent:
    def __init__(self, n_states, n_actions, learning_rate):
        self.n_states = n_states
        self.n_actions = n_actions
        self.learning_rate = learning_rate
        
        self.q_table = np.zeros((n_states, n_actions))
    
    def act(self, state, epsilon):
        # Generate a random number on the [0, 1) interval
        random_int = random.uniform(0,1)
        
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
        
        self.q_table[state][action] = old_value + self.learning_rate * (new_estimate- old_value)   


pygame.init()

# Initializing the display window
size = (SPACE_SIZE[0] * ZOOM_SIZE, SPACE_SIZE[1] * ZOOM_SIZE)
screen = pygame.display.set_mode(size)
pygame.display.set_caption("pong")

# Starting coordinates of the paddle
rect_x = SPACE_SIZE[0] // 2
rect_y = SPACE_SIZE[1] - 1

# Initial speed of the paddle
rect_change_x = 0
rect_change_y = 0

rect_size_x = 6
rect_size_to_sides_x = rect_size_x // 2
rect_size_y = 1

# Initial position of the ball
ball_x = SPACE_SIZE[0] // 2
ball_y = 1

# Speed of the ball
ball_change_x = 1
ball_change_y = 1
ball_size_to_sides = 1

# Draws the paddle. Also restricts its movement between the edges of the window.
def drawrect(screen,x,y):
    pygame.draw.rect(screen,RED,[(x - rect_size_to_sides_x) * ZOOM_SIZE,y * ZOOM_SIZE, ZOOM_SIZE * rect_size_x, ZOOM_SIZE])

# Encodes the given state to make recognizing a given state easier
def encode_state(ball_x, ball_y, rect_x, rect_y, ball_change_x, ball_change_y):
    return (ball_x, ball_y, rect_x, rect_y, ball_change_x, ball_change_y)

# We reset the global variables
def reset():
    global ball_change_x
    global ball_change_y
    global ball_size_to_sides
    global ball_x
    global ball_y
    global rect_x
    global rect_y
    global rect_change_x
    global rect_change_y

    # Reset the ball's movement
    ball_change_x = 1
    ball_change_y = 1
    ball_size_to_sides = 1

    # Reset the ball's position
    ball_x = SPACE_SIZE[0] // 2
    ball_y = 1

    # Starting coordinates of the paddle
    rect_x = SPACE_SIZE[0] // 2
    rect_y = SPACE_SIZE[1] - 1

    # Initial speed of the paddle
    rect_change_x = 0
    rect_change_y = 0

# We will use this to keep track of the states that we have encountered so far
state_to_id = {}

# The number of possible states in total
num_states = SPACE_SIZE[0] * SPACE_SIZE[1] * SPACE_SIZE[0] * SPACE_SIZE[1] * 2 * 2
print(num_states)

agent = QLearningAgent(n_states=num_states, n_actions=3, learning_rate=1.0)

def play_episodes(n_episodes=10_000, max_epsilon=1.0, min_epsilon=0.05, decay_rate=0.0001, gamma=0.99, learn=True, viz=False, human=False, log=False):
    global ball_change_x
    global ball_change_y
    global ball_size_to_sides
    global ball_x
    global ball_y
    global rect_x
    global rect_y
    global rect_change_x
    global rect_change_y

    rewards = []
    epsilon_history = []

    # We go over the episodes
    for episode in tqdm(range(n_episodes)):
        done = False

        # Decrease epsilon
        epsilon = min_epsilon + (max_epsilon - min_epsilon) * \
                np.exp(-decay_rate * episode)

        # Reset the environment
        total_reward = 0
        reset()

        # Get & encode the first state
        state = encode_state(ball_x, ball_y, rect_x, rect_y, ball_change_x, ball_change_y)
        if state not in state_to_id:
            state_to_id[state] = len(state_to_id)

        while not done:
            reward = 0
            
            # Background
            screen.fill(BLACK)

            if not human:
                # We ask our agent for an action & set the movement of the player accordingly
                action = agent.act(state=state_to_id[state], epsilon=epsilon)
                action_name = ACTIONS[action]

                if action_name == ACTION_LEFT:
                    rect_change_x = -1
                elif action_name == ACTION_RIGHT:
                    rect_change_x = 1
                elif action_name == ACTION_IDLE:
                    rect_change_x = 0
                else:
                    print("Error, unknwon action", action)
                    exit(-1)
            else:
                # We can also handle human input
                action=0
                for event in pygame.event.get():
                    if event.type == pygame.QUIT:
                        done = True
                    elif event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_LEFT:
                            rect_change_x = -1
                        elif event.key == pygame.K_RIGHT:
                            rect_change_x = 1   
                    elif event.type == pygame.KEYUP:
                        if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                            rect_change_x = 0
                        elif event.key == pygame.K_UP or event.key == pygame.K_DOWN:
                            rect_change_y = 0

            # We change the position of the player & the ball according to the action taken
            rect_x += rect_change_x
            rect_y += rect_change_y

            # This handles the movement of the ball.
            if ball_x<0:
                ball_x=0
                ball_change_x = ball_change_x * -1
            elif ball_x>SPACE_SIZE[0]:
                ball_x=SPACE_SIZE[0]
                ball_change_x = ball_change_x * -1
            elif ball_y<0:
                ball_y=0
                ball_change_y = ball_change_y * -1
            # When the ball & the player collide we increase the reward and change the trajectory
            elif ball_x + ball_size_to_sides >= rect_x - rect_size_to_sides_x and ball_x - ball_size_to_sides<=rect_x + rect_size_to_sides_x and ball_y==SPACE_SIZE[1]-1:
                ball_change_y = ball_change_y * -1
                reward = 1
            # We end the episode when the player fails to hit the ball
            elif ball_y> SPACE_SIZE[1] - 1:
                ball_change_y = ball_change_y * -1
                done = True
                reward = -1

            # Now we are in a new state because we took a certain action
            new_state = encode_state(ball_x, ball_y, rect_x, rect_y, ball_change_x, ball_change_y)
            if new_state not in state_to_id:
                state_to_id[new_state] = len(state_to_id)

            ball_x += ball_change_x
            ball_y += ball_change_y

            # If the agent goes beyond the screen (on either side), we terminate the episode
            if rect_x - rect_size_to_sides_x < 0:
                rect_x = 0 + rect_size_to_sides_x
                reward = -1
                done = True
            if rect_x > SPACE_SIZE[0] - rect_size_to_sides_x - 1:
                rect_x = SPACE_SIZE[0] - rect_size_to_sides_x - 1 
                reward = -1
                done = True
            
            # We visualize the environment
            if viz:
                # Ball            
                pygame.draw.rect(screen,WHITE,[(ball_x - ball_size_to_sides) * ZOOM_SIZE, (ball_y - ball_size_to_sides) * ZOOM_SIZE, ZOOM_SIZE * ball_size_to_sides, ZOOM_SIZE * ball_size_to_sides])
                
                drawrect(screen,rect_x,rect_y)
                pygame.display.flip()         
                clock.tick(60)
            
            # We update our Q-table
            if learn:
                agent.learn(state_to_id[state], action, reward, state_to_id[new_state], gamma)

            # The current state for the next timestep will be the current new_state
            state = new_state
            total_reward += reward

        if log:
            print("Total reward:", total_reward)
            
        rewards.append(total_reward)
        epsilon_history.append(epsilon)
    
    return rewards, epsilon_history


# # Game's main loop
clock = pygame.time.Clock()
#
# rewards, epsilon_history = play_episodes(
#     n_episodes=50_000, max_epsilon=1.0, min_epsilon=0.05, decay_rate=0.0001, gamma=0.95, learn=True, viz=False, human=False, log=False
# )
#
# # We can check the history of the agent's performance
# plt.plot(epsilon_history)
# plt.show()
#
# plt.plot(rewards)
# plt.show()
#
# # We save our results
# with open("agent.pkl", "wb") as f:
#     pickle.dump(agent, f)
# with open("state_to_id.pkl", "wb") as f:
#     pickle.dump(state_to_id, f)

# We can now observe how our trained agent performs
with open("agent.pkl", "rb") as f:
    agent = pickle.load(f)
with open("state_to_id.pkl", "rb") as f:
    state_to_id = pickle.load(f)
rewards, epsilon_history = play_episodes(10, max_epsilon=0, min_epsilon=0, decay_rate=0, gamma=0, learn=False, viz=True, log=True)
plt.plot(epsilon_history)
plt.show()
plt.plot(rewards)
plt.show()

pygame.quit()    
