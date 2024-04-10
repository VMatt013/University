def Legs(Chicken,Cow,Pig):
    return Chicken * 2 + Cow * 4 + Pig * 4

if __name__ == '__main__':
    print(Legs(int(input("Chickens: ")),int(input("Cows: ")),int(input("Pigs: "))))