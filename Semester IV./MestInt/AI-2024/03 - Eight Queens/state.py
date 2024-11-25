class CellOptions:
    FREE = "   "
    UNDER_ATTACK = " x "
    QUEEN = " Q "

class NQueens:
    def __init__(self, n):
        self.n = n
        self.table = [[CellOptions.FREE for col in range(n)] for row in range(n)]

    def __str__(self):
        return (str(self.n) + \
                "Queens [Table=\n|" + "\n|".join([ "|".join(row) + "|" for row in self.table]) +"\n]")\
                    .replace("\n", "\n" + "-"*(self.n * 3 + self.n + 1) + "\n")
    
if __name__ == "__main__":
    n_queens = NQueens(n=8)

    print(n_queens)
        