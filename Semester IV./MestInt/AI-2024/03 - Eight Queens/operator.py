from state import NQueens, CellOptions
import math

class Operator:
    def __init__(self, row, col):
        self.row = row
        self.col = col

    def precondition_fulfilled(self, n_queens):
        return n_queens.table[self.row][self.col] == CellOptions.FREE
    
    def use(self, n_queens):
        n = n_queens.n

        result = NQueens(n)

        # Copy the old state
        for r in range(n):
            for c in range(n):
                result.table[r][c] = n_queens.table[r][c]
        
        result.table[self.row][self.col] = CellOptions.QUEEN

        # Each column in the row
        for c in range(n):
            # Avoid overriding the previous values
            if result.table[self.row][c] == CellOptions.FREE:
                result.table[self.row][c] = CellOptions.UNDER_ATTACK

        # Each row in the column
        for r in range(n):
            # Avoid overriding the previous values
            if result.table[r][self.col] == CellOptions.FREE:
                result.table[r][self.col] = CellOptions.UNDER_ATTACK

        # Diagonally as well
        # Up left
        r = self.row - 1
        c = self.col - 1

        while r >= 0 and c >=0:
            # Avoid overriding the previous values
            if result.table[r][c] == CellOptions.FREE:
                result.table[r][c] = CellOptions.UNDER_ATTACK

            r -= 1
            c -= 1

        # Up right
        r = self.row - 1
        c = self.col + 1

        while r >= 0 and c < n:
            # Avoid overriding the previous values
            if result.table[r][c] == CellOptions.FREE:
                result.table[r][c] = CellOptions.UNDER_ATTACK

            r -= 1
            c += 1

        # Down left
        r = self.row + 1
        c = self.col - 1

        while r < n and c >= 0:
            # Avoid overriding the previous values
            if result.table[r][c] == CellOptions.FREE:
                result.table[r][c] = CellOptions.UNDER_ATTACK

            r += 1
            c -= 1

        # Down right
        r = self.row + 1
        c = self.col + 1

        while r < n and c < n:
            # Avoid overriding the previous values
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

    print("New state:", n_queens)

    o = Operator(row=4, col=2)

    print("Operator:", o)
    print("Pre-condition:", o.precondition_fulfilled(n_queens))

    o = Operator(row=4, col=4)

    print("Operator:", o)
    print("Pre-condition:", o.precondition_fulfilled(n_queens))

    n_queens = o.use(n_queens)

    print("New state:", n_queens)


