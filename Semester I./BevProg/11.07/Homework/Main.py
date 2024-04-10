import string
Blacklist = string.punctuation + "aáeéiíoóöőuúüű"
Lines = []

with open("hazi.txt",'r') as Input:
    for line in Input:
        if not (line == '' or line == '\n'):
            newLine = []
            for word in line.strip().split(" "):
                newWord = ''.join([char for char in word if not (char in Blacklist or char in Blacklist.upper())])
                if not newWord == '':
                    newLine.append(newWord)
            Lines.append(" ".join(newLine))

with open("Eredmeny","w") as Output:
    for i in range(2,len(Lines),3):
        try:
            Output.write(f"{Lines[i]}\n" )
        except:
            break

newWord = ''.join([char for char in word if not (char in Blacklist or char in Blacklist.upper())])


for char in word:
    if not (char in Blacklist or char in Blacklist.upper():
        newWord += char



