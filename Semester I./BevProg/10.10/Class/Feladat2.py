P1 = input("Player1 (R P S): ")
P2 = input("Player2 (R P S): ")


if P1 == P2:
    print("Döntetlen")
elif P1 == 'R' and P2 == 'S':
    print("P1 won")
elif P1 == 'R' and P2 == 'P':
    print("P2 won")

elif P1 == 'S' and P2 == 'R':
    print("P2 won")
elif P1 == 'S' and P2 == 'P':
    print("P1 won")

elif P1 == 'P' and P2 == 'R':
    print("P1 won")
elif P1 == 'P' and P2 == 'S':
    print("P2 won")
