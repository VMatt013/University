#include<iostream>
#include <ctime>

using namespace std;


int yes_or_no(){
    string valasz;
    while (true)
    {
        cin >> valasz;
        switch (valasz[0])
            {
            case 'Y':
            case 'y':
                return true;
            case 'N':
            case 'n':
                return false;
            default:
                cout << "Rossz bemenet probald ujra" << endl;
            }
    }
    return false;
}

struct Card{
    bool visible = false;
    int value = 0;
};

int Points = 0;

void Out(Card C[10]){
    if (system("CLS")) system("clear");
    for(int i=0;i<10; i++){
        if (C[i].visible){printf("%2d| %d \n",i+1,C[i].value);}
        else{printf("%2d| %p\n",i+1,&C[i].value);}
    }
}

void Loop(Card C[10]){
    int max = 5;
    int a,b;
    while(1){

        Out(C);

        cout << endl;
        cout << "Choose two cards!" << endl;
        cout << "1st card: ";
        cin >> a;
        if( a < 0 or a > 10 or C[a-1].visible){
            while(1){
                    Out(C);
                    if(!(0 < a && a < 10)){
                        cout << endl << "Number is out of bounds" << endl;
                    }
                    else if((C[a-1].visible)){
                        cout << endl << "The selected card is already visible" << endl;
                    }
                    else{
                        break;
                    }
                    cout << "1st card: ";
                    cin >> a;
            }
        }
        C[a-1].visible = true;
        Out(C);

        cout << endl << "2nd card: ";
        cin >> b;
        if(b < 0 or b > 10 or C[b-1].visible){
             while(1){
                    Out(C);
                    if(!(0 < b && b < 10)){
                        cout << endl << "Number is out of bounds" << endl;
                    }
                    else if((C[b-1].visible)){
                        cout << endl << "The selected card is already visible" << endl;
                    }
                    else{
                        break;
                    }
                    cout << "2nd card: ";
                    cin >> b;
            }
        }
        C[b-1].visible = true;
        Out(C);

        cout << endl << "The chosen cards are " << a << " and "<< b << "." << endl;

        if(C[int(a)-1].value !=  C[int(b)-1].value){
            C[int(a)-1].visible = false;
            C[int(b)-1].visible = false;
        }
        else if(Points < max-1) {Points++; cout << endl << "You found a pair!" << endl;}
        else{
            cout << "Congratulations you won the game!" << endl;
            cin.ignore();
            cout << "Press Enter to quit!" << endl;
            cin.get();
            return;
        }
        cin.ignore();
        cout << "Press Enter to continue!" << endl;
        cin.get();

    }



}

int main()
{

    srand(time(NULL));
    Card Cards[10];
    for (int i = 0; i < 10; i++){
        if(Cards[i].value != 0){continue;}

        while (1){
            int index = rand() % 10;

            if(Cards[index].value != 0 || index == i){continue;}
            int r = rand() % 200 + 1;
            Cards[index].value = r;
            Cards[i].value = r;
            break;
            }
        }

    Loop(Cards);




    //scanf("%d");
    return 0;
}


