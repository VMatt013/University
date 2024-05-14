#include <cstdio>
#include <iostream>
#include <string>
using namespace std;
    void New(){
        cout << endl << endl;
    }

struct Jarmu{
    string marka;
    string tipus;
    short int evjarat;
};


int main(){
    struct {
        int szul_ev;
        string nev;
    } szemely1,szemely2;

    szemely1.szul_ev = 2000;
    szemely1.nev = "János";

    szemely2 = szemely1;

    cout << szemely1.nev << " " << szemely1.szul_ev << endl;
    cout << szemely2.nev << " " << szemely2.szul_ev << endl;

    szemely1.nev = "Béla";

    cout << szemely1.nev << " " << szemely1.szul_ev << endl;
    cout << szemely2.nev << " " << szemely2.szul_ev << endl;


    Jarmu swift={"Suzuki","Swift",2002};
    Jarmu civic={"Honda","Civic",2009};
    cout << swift.marka << " " << swift.tipus << " " << swift.evjarat << endl;

    if(swift.evjarat > civic.evjarat){
        cout << "A " << swift.tipus << " fiatalabb." << endl;
    }
    else{
        cout << "A " << civic.tipus << " fiatalabb." << endl;
    }

    string st = "Narancs";
    string& gyumolcs = st;

    cout << gyumolcs + " " + st << endl;
    st = "Kiwi";
    cout << gyumolcs + " " + st << endl;
    cout << &gyumolcs <<  " " << &st << endl;

    cout << &civic << " " << &civic.marka << " " << &civic.evjarat << " " << &civic.tipus << endl;

    int a = 30;
    int* p = &a;

    cout << a << " " << &a << " " << p << " " << &p << endl;
    cout << a << " " << &a << " " << p << " " << *p << endl;
    *p = 15;
    cout << a << " " << &a << " " << p << " " << *p << endl;

    int* ptr = NULL;
    printf(": %c \n",100);


    int tomb[5] = {1,2,3,4,5};
    int *tptr;
    tptr = tomb;
    cout << tomb << " " << &tomb[0] << endl;

    New();

    for(int i=0; i < 5; i++){
        cout << tptr << " " << *tptr << endl;
        tptr++;
    }

    New();

    tptr = &tomb[4];
    for(int i=5; i > 0; i--){
        cout << tptr << " " << *tptr << endl;
        tptr--;
    }

    New();

    tptr = tomb;
    while(tptr <= &tomb[4]){
        cout << tptr << " " << *tptr << endl;
        tptr++;
    }

    New();

    for(int i = 0; i < 5; i++){
        *(tomb+i) = 1;
        cout << * (tomb+i) << " " << tomb+i << endl;
    }

    New();

    int* ptrtomb[5];
    for(int i = 0; i < 5; i++){
        ptrtomb[i] = &tomb[i];
        cout << *ptrtomb[i] << endl;
    }

    New();

    //char* s = {"Hello ,world"};

    //char* nevek[3] = {"Béla","Kati","Zsuzsa"};

    int z = 5;
    int* zptr = &z;
    int** zpptr = &zptr;


    cout << z << " " << *zptr << " " << **zpptr << endl;
    /*
     *p++, *(p++) p feloldása, majd pointer incrementálása
     *++p, *(++p) p incrementálása, majd feloldása
     ++*p, ++(*p) p feloldása, majd mutatott érték incrementálása
     (*p)++ p feloldása, majd a mutatott érték incrementálása utólag
    */

    int tomb2[5] = {1,2,3,4,5};
    int mtomb[5];

    int* kezdo_ptr = tomb2;
    int* veg_ptr = &(tomb2[4]);
    int* cel_ptr = mtomb;

    while(kezdo_ptr < veg_ptr){
        *(cel_ptr++) = *(kezdo_ptr++);
    }

    for(int i = 0; i<5; i++){
        cout << mtomb[i] << endl;
    }
    return 0;
}
