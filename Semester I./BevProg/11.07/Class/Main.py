 
def Feladat01():
    try:
        f = open("inpu.txt","r")
        tartalom = f.readlines()
        for sor in tartalom:
            sor = sor.rstrip()
            print(sor)
        f.close()
    except FileNotFoundError as e:
        print(e)

    with open("input.txt","w+") as myFile:
        tartalom = myFile.readlines()

def Feladat02(f):
    A = 0
    with open(f,'r') as File:
        sorok = File.readlines()
    for i in sorok:
        A += int(i)
    with open("ki.txt",'w') as File:
        File.write(str(A/len(sorok)))

def Feladat03(f,n):
        with open(f,'r') as File:
            for i in range(1,n):
                print(File.readline(),end="")
def Feladat04():
    max = 0
    word = ''
    with open("leghosszabb.txt","r") as File:
        line = File.read().split()
        for i in line:
            if len(i) > max:
                max = len(i)
                word = i
    print(word)

def Feladat05(f):
    with open(f,"r") as File:
        Lines = File.readlines()
    Lines[4] = ''
    Lines[7] = ''
    with open("output.txt","a") as File:
        for i in Lines:
            File.write(i)

def Feladat06(f):
    Words = {}
    with open(f,"r") as File:
        txt = File.read().lower().split(" ")
    for i in txt:
        try:
            Words[i] += 1
        except:
            Words[i] = 1
    print(list(Words.keys())[list(Words.values()).index(max(list(Words.values())))])
Feladat06("repeat.txt")
