#include<iostream>
#include <array>
#define N 4

using namespace std;
string Szin[] = {"Red","Blue","Black","White"};
string Tipus[] = {"Audi","Ford","BMW","Ferrari"};





struct Car{
    string szin;
    string tipus;
    short int loero;
    bool kolcsonozheto;
    int ar;
    //string kartip;
    //string alvazszam;
    //string nev;
    };
      
void nemKolcsonzott(array<Car,5> A){
    long int ossz  = 0;
    for(Car a : A){
        ossz += a.ar;
    }
    cout << "Az autók ára: " << ossz << endl;
}

void MaxAr(array<Car,5> A){
    int nemKolcsonzott  = 0;
    for(Car a : A){
        if (a.kolcsonozheto) nemKolcsonzott++;
    }
    cout << nemKolcsonzott << " autó nincs kikölcsönözve" << endl;
}
    
    
    
int main(){
    srand(time(NULL));
    array <Car,5> A;
        
        for(int i = 0; i < 5; i++){
            A[i].szin = Szin[rand() % N]; 
            A[i].tipus = Tipus[rand() % N]; 
            A[i].loero = rand() % 141 + 10 ;
            A[i].ar = rand() % 1000000;
            A[i].kolcsonozheto = bool(rand() % 2);
        }
        
        for(Car i : A){
            cout << i.tipus << " " << i.szin << " " << i.loero << " Lóerő " << i.ar << " Ft "  << i.kolcsonozheto << endl;
        }
        
        MaxAr((A));
        nemKolcsonzott(A);
    return 0;
}
