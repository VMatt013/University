#include<iostream>
using namespace std;



void m(){
    int arr[10] = {100,97,18,213,6,12,4,324,29,24};

    for(int i=0;i<10;i++){

        for(int j=0;j<9-i;j++)
        {
            if(*(arr+j) > *(arr+j+1))
            {
                *(arr+j) += *(arr+j+1);
                *(arr+j+1) = *(arr+j) - *(arr+j+1);
                *(arr+j) -= *(arr+j+1);
            }
        }

    }

    for(int i : arr){
        cout << i << " ";
    }


    cout << endl;

}

int main(){
    m();
    return 0;



}




