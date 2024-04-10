import sys

rList = ['a',0,2]

for entry in rList:
    try:
        print('The entry is: ',entry)
        r = 1/int(entry)
    except:
        print('Oops!',sys.exc_info()[0])
        print('Next entry\n')
print('The reciprocal of ' , entry , 'is' , r)
