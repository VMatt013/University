class Jug:
    CAPACITIES = [2, 3, 5]
    INDICES = range(len(CAPACITIES))

    def __init__(self, contents=None):
        if contents:
            self.contents = contents
        else:
            self.contents = [0 for c in Jug.CAPACITIES]

    def __str__(self):
        return f"Jug [Contents={str(self.contents)}]"
    
    def __eq__(self, other):
        return self.contents == other.contents

    def is_goal_state(self):
        return self.contents[2] == 4

if __name__ == "__main__":
    jug = Jug(contents=[2, 3, 0])

    print(jug)
        