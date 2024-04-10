def isPrime(num):
    if num > 2:
        for i in range(2,num):
            if num % (i) == 0:
                return False 
    elif num == 2:
        return True
    else:
        return False
    return True

if __name__ == '__main__':
    if isPrime(int(input("Adj meg egy számot: "))):
        print("A szám prím")
    else:
        print("A szám nem prím")

    