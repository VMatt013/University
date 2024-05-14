#include <iostream>
#include <vector>
//#include <string>
using namespace std;


void csere(int& a, int& b){
    int tmp = a;
    a = b;
    b = tmp;
}

void csere2(int* a, int* b){
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

void szoroz(int& a, int b){
    a = a*b;
}

int osszead(int a, int b){
    return a+b;
}

int fact(int n){
    if (n > 1){return n * fact(n-1);}
    else {return 1;}
}

int factor(int n){
    int o = 1;
    for(int i = 0;i < n; i++)
    {
        o *= o;
    }
    return o;
}

int main()
{
    //stack
    int a;
    int b[10];

    //heap
    //C: malloc és free
    //C++: new és delete
    //pointer-var = new adattípus;
    
    int* p = new int;
    int* tp = new int[10];
    
    int* p2 = new(nothrow) int;
    if(!p2){
        cout << "Memória allokáció sikertelen" << endl;
    }
    
    p2 = NULL; // pointer törlése

    int* p3 = new int(10);
    *p3 = 29;
    cout << p3 << " " << *p3 << " " << &p3 << endl;
    
    int n2 = 5;
    int* q = new int[n2];
    for(int i = 0;i < 5;i++)
    {
        q[i] = i+1;
        cout << q[i] << endl;
    }
    
    delete p3;
    //delete q;
    delete[] q;
    
    vector <int> v;
    for(int i=0;i<=10;i++)
    {
        v.push_back(i*10);
        cout << v[i] << " " << v.at(i) << endl;
    }
    
    printf("%d %d \n",v.back(),v.front());
    
    int* pos = v.data();
    printf("%p %d \n",pos,*pos);
    v.pop_back();
    v.clear();
    v = {1,3,4,2,6,85,14,742,13,5,2};
    int e = 1;
    int f = 2;
    csere(e,f);
    csere2(&e,&f);
    //érték szerinti paraméterátadás
    return 0;
}
