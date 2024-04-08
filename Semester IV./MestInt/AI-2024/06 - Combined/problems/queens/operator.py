from problems.queens.state import NQueens, CellOptions
import math

class Operator:
    def __init__(self, row, col):
        self.row = row
        self.col = col

    def precondition_fulfilled(self, n_queens):
        return n_queens.table[self.row][self.col] == CellOptions.FREE
    
    def use(self, n_queens):
        n = n_queens.n

        result = NQueens(n=n)

        for r in range(n):
            for c in range(n):
                result.table[r][c] = n_queens.table[r][c]

        result.table[self.row][self.col] = CellOptions.QUEEN

        for c in range(n):
            if result.table[self.row][c] == CellOptions.FREE:
                result.table[self.row][c] = CellOptions.UNDER_ATTACK

        for r in range(n):
            if result.table[r][self.col] == CellOptions.FREE:
                result.table[r][self.col] = CellOptions.UNDER_ATTACK

        # -----------------------------------------------------------
        # fel es balra
        r = self.row - 1
        c = self.col - 1

        while r >= 0 and c >= 0:
            if result.table[r][c] == CellOptions.FREE:
                result.table[r][c] = CellOptions.UNDER_ATTACK
            
            r -= 1
            c -= 1

        # -----------------------------------------------------------
        # fel es jobbra
        r = self.row - 1
        c = self.col + 1

        while r >= 0 and c < n:
            if result.table[r][c] == CellOptions.FREE:
                result.table[r][c] = CellOptions.UNDER_ATTACK
            
            r -= 1
            c += 1

        # -----------------------------------------------------------
        # le es balra
        r = self.row + 1
        c = self.col - 1

        while r < n and c >= 0:
            if result.table[r][c] == CellOptions.FREE:
                result.table[r][c] = CellOptions.UNDER_ATTACK
            
            r += 1
            c -= 1
        
        # -----------------------------------------------------------
        # le es jobbra
        r = self.row + 1
        c = self.col + 1

        while r < n and c < n:
            if result.table[r][c] == CellOptions.FREE:
                result.table[r][c] = CellOptions.UNDER_ATTACK
            
            r += 1
            c += 1

        return result
    
    def __str__(self):
        return f"Operator [Row={self.row}, Column={self.col}]"
    
if __name__ == "__main__":
    print("")

    n_queens = NQueens(n=8)

    print("State:", n_queens)

    o = Operator(row=1, col=2)

    print("Operator:", o)
    print("Pre-condition:", o.precondition_fulfilled(n_queens))

    n_queens = o.use(n_queens)

    print("State after:", n_queens)