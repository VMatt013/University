class Jug:
    CAPACITIES = [2, 3, 5]
    INDICES = range(len(CAPACITIES))

    def __init__(self, contents=None):
        if contents:
            self.contents = contents
        else:
            self.contents = [0 for c in Jug.CAPACITIES]

    def __str__(self):
        return f"Jug [contents={str(self.contents)}]"


if __name__ == "__main__":
    jug = Jug(contents=[2, 3, 0])

    print(jug)
