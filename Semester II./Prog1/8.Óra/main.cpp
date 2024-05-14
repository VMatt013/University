#include <iostream>
#include <string>
#include <vector>
using namespace std;

class Allat{
    public:
        //adattag
        string nev;
        int kor;
        
        void mozog(){
            cout << nev << " megmozdult." << endl;
        }
        
        static int counter;
        
        static void printCounter(){
            cout << "Counter: " << counter << endl;
        }
        
        // Érték szerinti paraméter átadás
        // b helyére aktuális paraméterek másolása
        void baratkozik(Allat b);
        
        
        
        // Konstruktor : objektum létrehozása után
        // Függvény neve megegyezik az osztály nevével
        // Nincs visszatérési érték
        
        // This
        
        // Paraméterezett konstruktor || full vagy teljes konstruktor
        Allat(string name, int age){
                this -> nev = name;
                this -> kor = age;
                counter++;
        }
        
        // default 
        Allat(){}
        
        
        
        // Destruktor
        ~Allat(){
            counter--;
        }
};

class Kutya : public Allat{
public:
        string szin;
        string kedvencJatek;
        
        void Ugat(){
            cout << "vau vau" << endl; 
        }
        
                           
        Kutya(string name, int age,string szin, string jatek){
                this -> nev = name;
                this -> kor = age;
                this -> szin = age;
                this -> kedvencJatek = jatek;
        }
        ~Kutya(){
            cout << this -> nev << " destructed" << endl;
        }
        

};
int Allat::counter = 0;

void Allat::baratkozik(Allat b){
        cout << nev << " és " << b.nev << " barátok lettek." << endl;
};


string Idosebb(Allat a, Allat b){
            if(a.kor > b.kor){
                return a.nev;
                
            }
            else{
                return b.nev;
            }
        }

int main()
{
    Allat a("Riki",12);
    a.mozog();
    Allat b = {"Cirmi",7};
    a.baratkozik(b);
    Allat::printCounter();
    Kutya k("Fifi",15,"Barna","Labda");
    k.mozog();
    
    // Kutya = Allat && Kutya != Allat
    cout << Idosebb(a,b) << " idősebb." << endl;
    cout << Idosebb(a,k) << " idősebb." << endl;
    
    vector<Allat> allatok;
    allatok.push_back(a);
    allatok.push_back(b);
    allatok.push_back(k);
    
    for (Allat a : allatok){
        cout << a.nev << endl;
    }
    
    
    
    return 0;
}
