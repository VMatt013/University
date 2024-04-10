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

char* ToUpper(char Text[MAX]){
    static char Upper[MAX];
    for (int i = 0; i  < strlen(Text); i++)
    {   /*switch (Text[i])
    {
    case 'ó':
            Text[i] = 'Ó';
        continue;
        
     case 'ü':
            Text[i] = 'Ü';
        continue;

     case 'ö':
            Text[i] = 'Ö';
        continue;

     case 'ő':
            Text[i] = 'Ő';
        continue;

    case 'ú':
            Text[i] = 'Ú';
        continue;

    case 'ű':
            Text[i] = 'Ű';
        continue;
    }*/
        Upper[i] = toupper(Text[i]);
    }
    return Upper;
}

Occurrences LetterCount(char Letter,char Text[MAX]){
    Occurrences Occured = { Letter, 0};

    for (int i= 0; i < strlen(Text); i++)
    {
        if(Text[i] == Letter)
        {
            Occured.Count++;
        }
    }
    return Occured;
}



int main(){
    char Txt[MAX];
    Occurrences Data[50];

    for(int i = 0; i < strlen(Txt); i++)
    {

    }

    strcpy(Txt,ToUpper(fgets(Txt,MAX,stdin)));
    printf("%s",strrev(Txt));
    Occurrences A = LetterCount('c',"aaabc");
    printf("%s",Txt);
    return 0;
}