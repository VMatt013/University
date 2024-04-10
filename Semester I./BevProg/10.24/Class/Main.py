txt = "Test String"
char = 'a'


magan = [ 'a', 'á', 'e', 'é', 'i', 'í', 'o', 'ó', 'ö', 'ő', 'u', 'ú', 'ü', 'ű']

#for c in txt:
#    if not(c in magan):
#        print(c,end='')

#print(txt.split('t'))

print("i\t\ti**2\t\ti**3\t\ti**5\t\ti**10\t\ti**20")
for i in range(1,11):
    print(i,"\t\t", i**2, "\t\t", i**3, "\t\t", i**5,"\t\t", i**10,"\t\t", i**20)

e = "{0:>4}{1:>6}{2:>6}{3:>8}{4:>13}{5:>24}"

#print(e.format("i","i**2","i**3","i**5","i**10","i**20"))
#for i in range(1,11):
    #print(e.format((i,i**2,i**3,i**5,i**10,i**20)))