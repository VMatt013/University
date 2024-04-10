def main():
    g   = '\033[92m' #Color codes
    e   = '\033[0m'
    err = '\033[91m'
    h   = '\033[95m'
    
	#1. Feladat
    print(h,'1. Feladat',e)
    txt = input(' Adjon meg egy mondatot:' + g + ' ')
    Lett = {}

    for x in txt.upper().replace(' ',''):
        try:
            Lett[x] += 1
        except:
            Lett[x] = 1

    print(e,"Betűk gyakorisága:",g,Lett,e)
    print(" Fordítva: ",g,txt[::-1],e)
    print(" Listába rendezve szavanként:",g,txt.split(' '),e,"\n")

    #2. Feladat
    print(h,'2. Feladat',e)
    num,unit = [int(i) for i in input("Adjon meg egy számot és egy mértékegységet (number cm/inch): "+g).split(' ')]

    if unit == "inch":
        print(format(num * 0.393700787,".2f"), "centimeters",e)
    elif unit == "cm":
        print(format(num * 2.54,".2f"), "inches",e)
    else:
        print(err,"Not correct unit!",e)

if __name__ == "__main__":
    main()




    def main():
        pass
     
    if __name__ == '__main__':
        main()