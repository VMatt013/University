class Team:
    def __init__(self,nev,projekt,szerep):
        self.nev = nev
        self.projekt = projekt
        self.szerep = szerep

        print("-- Developer létrehozva --")
        print("{} a {}-en dolgozik {} szerepkörben.".format(nev,projekt,szerep))
    def __eq__(self,other):               
        return  self.projekt == other.projekt

def main():
    Workers = [Team("Ricsi", "SolArch", "Frontend"), Team("Angéla", "ZerTeng", "Tesztelő"), Team("Peti", "KefERP", "Backend"), Team("Éva", "KefERP", "Frontend")]
    
    for i in range(len(Workers)):
        for j in range(i+1,len(Workers)):
            if (Workers[i] == Workers[j]):
                print(Workers[i].nev + " és " + Workers[j].nev + " együtt dolgoznak")

if __name__ == '__main__':
    main()