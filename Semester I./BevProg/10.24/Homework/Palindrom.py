import string

def ClearTxt(text):
    text = text.lower()
    Forbidden = string.punctuation + ' '
    Ekezetek = {"á":'a', "é":'e', "í":'i', "ó":'o', "ö":'o', "ő":'o', "ú":'u', "ü":'u', "ű":'u'}
    hunLett = ["cs","dz","gy","ly","ny","sz","ty","zs"]
    newText = ""
        
    i = 0
    while i < len(text):
        if not(text[i] in Forbidden):
            if(i < len(text)-2 and text[i:i+3] == "dzs"):
                        newText = newText + "9"
                        i = i+3         
                        continue

            elif(i < len(text)-1 and text[i:i+2] in hunLett):   
                for x in range(len(hunLett)):
                    if(text[i:i+2] == hunLett[x]):
                        newText = newText + str(x)
                        i = i+1        
                        continue
            elif not (text[i] in Ekezetek):
                newText = newText + text[i]
            else:
                newText = newText + Ekezetek[text[i]]
        i = i + 1
        return newText

def isPalindrom(text):
    reverse = text[::-1]
    for i in range(len(text)):
        if text[i] != reverse[i]:
            return False
    return True

if __name__ == "__main__":
    if isPalindrom(ClearTxt(str(input("Adj megy egy mondatot")))):
        print(": Palindrom")
    else:
        print(": Nem Palindrom")
