def main():
    txt = input('Adjon meg egy mondatot: ')
    Lett = {}

    for x in txt:
        try:
            Lett[x] += 1
        except:
            Lett[x] = 1
    print("Betűk gyakorisága:",Lett)
    print("Fordítva: ", txt[::-1])
    print("Listába rendezve szavanként:",txt.split(' '))

if __name__ == "__main__":
    main()


