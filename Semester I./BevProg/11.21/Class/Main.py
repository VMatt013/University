def beolvas():
    with open("playlist.csv",'r') as File:
        playlist = [ (lambda l : {'eloado' : l[0], "cim" : l[1], "mufaj" : l[2], "hossz" : int(l[3]) } )( line.strip().split(';') ) for line in File ]
        return playlist

def teljes_hossz(playlist):
    osszeg = sum([i["hossz"] for i in playlist])
    m,s = osszeg // 60 ,osszeg % 60
    with open("02_hossz.txt","w") as File:
        File.write(f"{m}:{s}")

def leghosszabb_rockzene(playlist):
    H = [i['hossz'] for i in playlist]
    with open("leghosszabb_rockzene.txt",'w') as File:
        File.write(playlist[H.index(max(H))]["cim"])

def leggyakoribb_mufaj(playlist):
    mufaj = [i['mufaj'] for i in playlist]
    M = {i : mufaj.count(i) for i in set(mufaj)}
    count = [*M.values()]
    with open("04_kedvenc_mufaj.txt","w") as File:
        File.write( [ *M.keys() ][ count.index(max(count)) ].upper() )

def zeneket_csoportosit(playlist):
    E = {}
    for i in playlist:
        E[i["eloado"]] = E.get(i["eloado"],0) + i["hossz"]
    with open("05_osszegzes.txt","w") as File:
        for eloado in sorted(E):
            File.write(f"{eloado} - {E[eloado]}\n")

def zeneket_listaz(playlist,nev):
    Dalok = [i["cim"].lower() for i in playlist if i["eloado"].lower() == nev.lower()]

    with open(f"06_{nev.lower().replace(' ','_')}_dalok.txt","w") as File:
        if Dalok:
            for i in Dalok:
                File.write(f"{i}\n")
        else:
            File.write("Nincs ilyen eloado a lejatszasi listaban!")

def zeneket_torol(playlist,nevek):
    with open("07_torolt.txt","w") as File:
        for i in playlist:
            if not i['eloado'] in nevek:
                File.write(f"{i['eloado']} {i['cim']} {i['mufaj']} {i['hossz']}\n")


L = beolvas()
teljes_hossz(L)
leghosszabb_rockzene(L)
leggyakoribb_mufaj(L)
zeneket_csoportosit(L)
zeneket_listaz(L,"POWERWOLF")
zeneket_torol(L,["Linkin Park","Imagine Dragons"])
