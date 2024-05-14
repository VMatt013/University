#include<iostream>
#include<math.h>
using namespace std;

int bannana = 110;


struct ember{
    string nev;
    int kor;
    float magassag_cm;
    float suly_kg;
    char nem;
};

    int CalCalc(ember e){
        switch(e.nem)
        {
            case 'F':
                return 655.1 + (9.6*e.kor) + (1.8*e.magassag_cm) - (4.7*e.kor);
            case 'M':
                return 66.46 + (13.7*e.suly_kg) + (5*e.magassag_cm) - (6.8*e.kor);
        }

        return 0;
    }

    int Bannana(int n,ember e){
        return CalCalc(e) - n*bannana;
    }

    int MoreBannana(int calorie){
        return ceil(calorie / bannana);
    }

int main()
{
    ember Emberek[4] = {{"Béla",12,150,70,'M'},{"Bálint",29,195,40,'M'}, {"Adrienn1",20,165,60,'F'},{"Adrienn2",20,165,60,'M'}};

     cout << MoreBannana(Bannana(5,Emberek[1])) << endl;


    return 0;
}