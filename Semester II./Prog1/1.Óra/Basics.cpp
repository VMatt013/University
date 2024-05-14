#include <cwchar>
#include <iostream>
#include <iterator>
#define LENGTH 14 //előre betöltött "változó"

int glob = 1; //globális változó

void Basics(){
    //comment
    /*Comment*/
    char c = 'a'; // Karakter literál
    int i = 0;
    short int si = 7;
    long int li = 50;
    float f = 10.2;
    double d = 10.5; // több tizedesjegyig pontos
    bool b = true;

    const int width = 2; //constant

    std::string Text = "Hello World!";
    std::cout << Text << "\n";
    std::cout << Text << std::endl;

    std::cout << "char: " << sizeof(char) << std::endl;
    std::cout << "int: " << sizeof(int) << std::endl;
    std::cout << "short int: " << sizeof(short int) << std::endl;
    std::cout << "long int: " << sizeof(long int) << std::endl;
    std::cout << "float: " << sizeof(float) << std::endl;
    std::cout << "double: " << sizeof(double) << std::endl;
    std::cout << "bool: " << sizeof(bool) << std::endl;

    int tomb[3] = {1,2,3};

    typedef int egesz;
    egesz szam = 10;

    {int a = 2;} //Block
    int a = 2;

    std::cout << a++ << std::endl;
    std::cout << ++a << std::endl;

    std::cout << "\\" << std::endl;

    //escape
    //' " ? alert backspace form-feed new-line carriage-return tav vertical-tab
    // \\ \' \" \: \a \b \f \n \r \t \v

    //Operátorok : + - * / % ++ --
    // && || !
    //Értékadó Operátorok: = += *= /= <<= >>= &= ^= |=
    // i%=2 = i = i%2

    //Bit műveletek: &-és, |-vagy, ^-XOR, << eltolás >>

    using namespace std; //Nem kell std:: -ot használni

    cout << "Using namespace" << endl;

    //implicit konverzió
    int x1 = 1;
    double y1;
    y1 = x1;

    //explicit konverzió
    int z = 21;
    double zs;
    zs = double(z);

    if (25 > 1){
        cout << "T";
    }
    else if (2){
        cout << "elif";
    }
    else{
        cout << "F";
    }

    //10 == 1 ? cout << "True" : cout << "False";
    bool isItEqual = 10 == 1 ? true : false;

    int x3=1,y3 = 2;

    string A =  x3 == y3 ? "e" : x3 < y3 ? "k" : "n";
    cout << A << endl;

    int nap = 1;
    switch(nap){
        case 6:
            cout << "szombat" << endl;
            break;
        case 7:
            cout << "vasárnap" << endl;
            break;

        default:
            cout << "mindjárt hétvége" << endl;
            break;

    }

    i = 0;
    while(i<10){
        cout << i << endl;
        i++;
    }

    do{
        cout << "do" << endl;
    }
    while(2<1);

    char ch;
    for (ch = 'a'; ch < 'z' ; ch++){
        cout << ch << endl;
    }

    for (i = 10; ch < 100 ; i = (i/2)+1){
        cout << i << endl;
    }

    for(;;){
        cout << "vegtelen" << endl;
        break;
    }

    int Tomb[5] = {1,2,3,4,5};

    for (int i : Tomb){
        cout << i << endl;
    }

    for (i = 0; i < sizeof(Tomb)/sizeof(int);i++){
        cout << i << endl;
    }

    char betuk[2][4] = {
        {'a','b','c','d'},
        {'e','f','g','h'}
    };

    for (int i = 0; i < 2; i++){
        for (int j = 0; j < 4; j++){
            cout << betuk[i][j] << ' ';
        }
        cout << endl;
    }

    int szam2d[3][4] = {
        {1,2,3,4},
        {5,6,7,8},
        {8,10,11,12}
    };

     for (int i = 0; i < 3; i++){
        float atlag = 0;
        for (int j = 0; j < 4; j++){
            atlag += szam2d[i][j];
        }

        cout << i << ". sor atlaga: "<< atlag/4 << endl;
    }

    return;
}





int main(){
    Basics();


    return 0;
}
