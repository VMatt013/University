class CellOptions:
    FREE = "   "
    UNDER_ATTACK = " x "
    QUEEN = " Q  "
    
class NQueens:
    def __init__(self, n):
        self.n = n

        self.table = [[CellOptions.FREE for col in range(self.n)] for row in range(self.n)]

    def __str__(self):
        return (str(self.n) + \
                "Queens [Table=\n|"
                + "\n|".join([ "|".join(row) + "|" for row in self.table]) +"\n]")\
                    .replace("\n", "\n" + "-"*(self.n * 3 + self.n + 1) + "\n")

    def __eq__(self, other):
        return self.table == other.table
    
    def is_goal_state(self):
        return sum(
                [sum(
                    [self.table[row][col] == CellOptions.QUEEN 
                    for col in range(self.n)]
                    )
                for row in range(self.n)]
            ) == self.n

if __name__ == "__main__":
    n_queens = NQueens(n=8)

    print(n_queens)