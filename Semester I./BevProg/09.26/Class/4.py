try: 
    k = 5/0
    print(k)
except ZeroDivisionError:
    print("Can't divide by 0")
finally:
    print('This is always executed')