#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h> 
#define MAX 255

typedef struct Occurrence
{
    char Letter;
    int Count;
} Occurrences;


Occurrences * TextCheck(char Text[MAX]){
    static Occurrences Data[50];

    for (int i = 0; i < strlen(Text)-1; i++)
    {
        int Counter = 0;
        
        if (Text[i] != ' ')
        {
            Data[Counter].Letter = Text[i];

            for (int j = 0; j < strlen(Text)-1; j++)
            {
                if (Text[i] == Text[j])
                {
                    Data[Counter].Count++;

                    Text[j] = ' ';
                }
            }
            Counter++;
        } 
    }
    
    return Data;
}


int main(){
    char txt[MAX];
    Occurrences * D;
    printf("Adjon meg egy mondatot: ");
    //fgets(txt,MAX,stdin);
    scanf("%s",txt);

    printf("Alap: %s \n",txt);
    printf("Forditva: %s \n",strrev(txt));

    printf("Utana: %s \n",txt);

    D = TextCheck(txt);
    printf("Utana: %s \n",txt);
    
    printf("%c\n",txt[1]);

    for (int i = 0; i < 10; i++)
    {
        //printf("%c - %d",D[i].Letter, D[i].Count);
    }
    

    return 0;
}