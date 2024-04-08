class Hanoi:
    TOWERS = ["A", "B", "C"]

    def __init__(self, disks=None):
        if disks:
            self.towers = {
                "A": disks,
                "B": set(),
                "C": set()
            }
        else:
            self.towers = {
                "A": {1, 2, 3},
                "B": set(),
                "C": set()
            }
    
    def is_goal_state(self):
        return self.towers["C"] == {1, 2, 3}
    
    def __str__(self):
        return f"Tower of Hanoi[Towers={str(self.towers)}]"
    
    def __eq__(self, other):
        return self.towers == other.towers
    
if __name__ == "__main__":
    hanoi = Hanoi()

    print(hanoi)