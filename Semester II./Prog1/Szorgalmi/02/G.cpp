#include <caca.h>
#include <cstdio>
#include<iostream>

using namespace std;
int active = 0;

struct entry{
    string text;
    bool on = false;
};

void write(string text,bool On = false){


}

void Menu2(){
        if (system("CLS")) system("clear");
        string Entries[] = {{"Start"}, {"Settings"}, {"Exit"} };
        for (int i = 0; i < 3;i++){
            if(i != active){
                cout << "\033[0;37m" << Entries[i] << "\033[0m" << endl;
            }
            else {
                cout << "\033[1;32m" << Entries[i] << "\033[0m" << endl;
            }
        }

}

void Menu(){
        entry Entries[] = {{"Start",true}, {"Settings"}, {"Exit"} };
        string Entries2[] = {{"Start"}, {"Settings"}, {"Exit"} };
        for (entry i : Entries){
            switch(i.on)
        {
        case false:
            cout << "\033[0;37m" << i.text << "\033[0m" << endl;
            break;
        case true:
            cout << "\033[1;32m" << i.text << "\033[0m" << endl;
            break;
        }
        }

}

void Keys(){
    char c;
    while(1) {
        system("stty raw");
        c = getchar();
        system("stty cooked");
        switch(c)
        {
        case '.':
            system("stty cooked");
            exit(0);
        case 'A':
        case 'w':
            active -=1;
            if(active < 0){active = 2;}
            break;
        case 'B':
        case 's':
            active +=1;
            if(active > 2){active = 0;}

            break;
        default:
            break;
        }
        Menu2();

    }
}

int main()
{
    char c;

    Menu2();
    Keys();



    return 0;
}

