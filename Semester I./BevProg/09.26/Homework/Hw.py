def Div(a,b):
    try:
        a,b = int(a),int(b)
        try:
            print(a/b)
        except ZeroDivisionError:
            print('Division by 0 not allowed')
    except ValueError:
        print('Wrong inputs, try again')
    finally:
            print('Out of try except blocks',end = 2 * '\n')
    

while True:
    Div(input("Enter 'a' value: "), input("Enter 'b' value: "))