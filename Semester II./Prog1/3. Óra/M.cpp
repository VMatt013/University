#include <iostream>
#include <string>
using namespace std;


int main(){
    string S = " ";

    int tomb[5] = {1,2,3,4,10};
    int *tptr = tomb;

    int tmp = *tptr;
    *(tomb) = *(tptr+4);
    *(tomb+4) =tmp;

    for(int i : tomb){
        cout << i << endl;
    }

    tptr = tomb;
    return 0;
}
