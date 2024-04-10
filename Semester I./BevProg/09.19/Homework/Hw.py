def Compare(A,B,C):
    L = [A,B,C]
    L.sort()

    if L[2] < (L[0]+L[1]):
        print('Van ilyen háromszög')
    else:
        print('Nincs ilyen háromszög')

if __name__ == '__main__':
    a,b,c = input('Add meg a háromszög 3 oldalát (a b c): ').split(' ')
    Compare(int(a),int(b),int(c))