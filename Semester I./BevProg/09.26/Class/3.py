from tokenize import Name


def fun(a):
    if a < 4:
        b = a/(a-3)
    print('Value of b: ',b)


try:
    fun(5)
except ZeroDivisionError:
    print('ZeroDivisionError Occured and Handled') 
except NameError:
    print('NameError Occured and Handled') 
