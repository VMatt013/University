def Tabla():
    Sort = "{0:>4}{1:>1}{2:>5}{3:>5}{4:>5}{5:>5}{6:>5}{7:>5}{8:>5}{9:>5}{10:>5}{11:>5}{12:>5}{13:>5}"
    print(Sort.format("", " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"))

    print("\t:------------------------------------------------------------", sep="")

    List = []
    for i in range(0, 12):
        for j in range(1, 13):
            List.append(j * (i + 1))
        print(elrendezes.format(str(i + 1), ":", List[0], List[1], List[2], List[3], List[4], List[5],
              List[6], List[7], List[8], List[9], List[10], List[11]))
        List = []


if __name__ == "__main__":
    Tabla()
