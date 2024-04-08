from problems.hanoi.state import Hanoi
import math


class Operator:
    def __init__(self, from_, to):
        self.from_ = from_
        self.to = to

    def precondition_fulfilled(self, hanoi):
        implication = True

        implication_first_part = len(hanoi.towers[self.from_]) > 0 
        implication_second_part = min(hanoi.towers[self.from_], default=0) \
            < min(hanoi.towers[self.to], default=math.inf)
        
        if implication_first_part and not implication_second_part:
            implication = False

        return len(hanoi.towers[self.from_]) > 0 and implication
    
    

    def use(self, hanoi):
        result = Hanoi()

        index_untouched = next(filter(lambda i: i not in (self.from_, self.to),
                                    hanoi.TOWERS))
        
        element_picked = min(hanoi.towers[self.from_])

        result.towers[self.from_] = hanoi.towers[self.from_] - {element_picked}
        result.towers[self.to] = hanoi.towers[self.to] | {element_picked}
        result.towers[index_untouched] = hanoi.towers[index_untouched]

        return result
    
    def __str__(self):
        return f"Operator [From={self.from_}, To={self.to}]"

if __name__ == "__main__":
    print("")

    hanoi = Hanoi()

    print("Hanoi:", hanoi)

    print("------------------")

    o1 = Operator(from_="A", to="B")

    print("o1:", o1)

    print("Precondition:", o1.precondition_fulfilled(hanoi))

    hanoi = o1.use(hanoi)

    print("After:", hanoi)
