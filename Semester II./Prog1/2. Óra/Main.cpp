#include <iostream>
#include <array>
using namespace std;

int main(){
    int nums[3][4] = {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12}
    };

    int Max = 0;
    for (int i = 0;i<sizeof(nums)/sizeof(nums[0]);i++){
        int sorMax = 0;
        for(int j : nums[i]){
            if (sorMax < j) {sorMax = j;}
        }
        cout << i+1 << ". sor maximuma: " << sorMax << endl;
        if (Max < sorMax) Max = {sorMax};
    }
    cout <<"A számok maximuma: " << Max << endl;

    for (int i = 99; i >=0; i-=3) cout << i << endl;
    for (int i = 1; i < 300; i*=2) cout << i << endl;
    return 0;
}
