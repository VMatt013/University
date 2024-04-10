#include <string>
#include <iostream>
using namespace std;


string txtRev(string Original){
    string New = "Empty";

    for (int i = Original.size(), j = 0 ; i--, j++; i > 0){
        New[j] = Original[i];
    }
        return New;
}




int main ()
{
    string txt;
    cout << "Adjon meg egy mondatot: ";
    cin >> txt;

    string New = "Empty";

    for (int i = txt.size(), j = 0 ; i--, j++; i > 0){
        New[j] = txt[i];
    }

    string a = txtRev(txt);
    cout << New;
    return 0;
}



