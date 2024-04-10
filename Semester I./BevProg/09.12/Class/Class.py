
x = 1
y = 5.8

a = float(x)
b = int(y)

print(x)
print(a)
print(y)
print(b)

###############################

for x in "banana":
    print(x)

a =  "Hello world"
print(len(a))

###############################

txt = "The best things in life is free"
if "free" in txt:
    print("Contains")

###############################

print(txt.lower())
print(txt.upper())

###############################

a = "Hello, world"
print(a.split(','))

###############################

a = "Hello"
b = "world"
c = a + b

print(a + b)

###############################

age = 19
txt = "My age is {}"
print(txt.format(age))

###############################

quantity = 3
itemno = 748
price = 74.83
myOrder = "I want {} pieces of item {} for {} dollars"

print(myOrder.format(quantity,itemno,price))


###############################

a = 200
b = 30

if b > a:
    print("b is greater than a")
else:
    print("a is greater than b")
###############################

print("Adj meg 2 számot")
# a = int (input())
# b = int (input())

###############################

thisList = ["apple","cherry","banana","orange","kiwi","melon","mango"]
print(thisList[1:5])
print(thisList[:3])
print(thisList[-4:-1])
print(thisList[::-1])
print(thisList[0:5:2])

###############################

thisList.insert(1,"watermelon")
print(thisList)

thisList.append("pear")
print(thisList)

thisList.remove("banana")
print(thisList)

###############################

for x in thisList:
    print(x)

newList = [x for x in thisList if "a" in x]
print(newList)

###############################

newList.sort(reverse = True)
print(newList)

###############################

thisDict = {
    "brand" : "Ford",
    "model" : "Mustang",
    "year" : 1964
}

print(thisDict)
print(thisDict["brand"])
print(len(thisDict))

###############################

for x in thisDict:
    print(thisDict[x])

for x in thisDict:
    print(x)

for x,y in thisDict.items():
    print(x,y)

i = 1   
while i  < 6 :
    print(i)
    i+=1
else:
    print("i is no longer less than 6")