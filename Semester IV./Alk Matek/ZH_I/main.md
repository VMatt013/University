# ZH I.

## Lebegőpontos számok, Matlab alapok
### 1. Feladat 
#### Összeg:
Írjon egy Matlab függvényt, amely tetszőleges n esetén megadja az alábbi összeg  értékét
$$s=\sum_{k=1}^n  z$$

$$1 \leq n \leq 33$$
##### megoldás:
A képlet mindig tartalmaz egy összeget, amiben van egy $z$ képletet

```matlab
function fun(n)
    k = 1:n;
    s = sum(z);
end
```
#### Szorzat:
Írjon egy Matlab függvényt, amely tetszőleges n esetén megadja az alábbi szorzat  értékét

$$s = \prod_ {k=1}^n z$$


$$1 \leq n \leq 33$$
##### megoldás:
A képlet mindig tartalmaz egy szorzatot, amiben van egy $z$ képletet
```matlab
function fun(n)
    k = 1:n;
    s = prod(z);
end
```

### 2. Feladat 
Ábrázolni szeretnénk az

$f(x) = z$

függvényt a $[a,b]$ intervallumon. Ehhez meghatározzuk az intervallum n darab, egyenlő lépésközű pontját, ezt az $x$ vektorban tároljuk. Ezután ezekben a pontokban kiszámítjuk a függvény értékét, ezeket az $y$ vektorba tesszük.

##### megoldás:
Megadjuk a kapott intervallumot ```linspace``` segítségével, majd felírjuk a kapott függvényt.
```matlab
function fun(n)
    x = linspace(a,b,n);
    y = z;
end
```
#### 3. Feladat

Adottak az x és y sorvektorok, továbbá az n természetes szám. 

$a$ az a $2n$ elemű sorvektor legyen, aminek páros sorszámú elemei az $x$ vektor első $n$ eleme, a páratlan sorszámú elemei pedig az $1,2,…,n$ számok. 

$b$ az a sorvektor legyen, amit úgy kapunk, hogy elhagyjuk az $y$ vektor $2.$, $4.$ és $5.$ elemét.

##### megoldás:
```matlab
function [a,b]=fun(x,y,n)
    a=zeros(1,2*n);
    a(2:2:end)=x(1:n);
    a(1:2:end)=1:n;
    b=y;
    b([2,4,5])=[];
end
```

#### 4. Feladat
$a=2, t=4, k−=−3, k+=4$ esetén mi lesz a  0.125 lebegőpontos szám jobboldali szomszédja?
##### megoldás:
Átírjuk a számot 2-es számrendszerbe majd megnézzük mennyi a $t$, hogy egyszerűbb legyen számolni normalizáljuk, majd a szomszéd típusától függően megnézzük a legelső 1-estől $t$ számjegyet és a legutolsó számjegy helyére hozzáadunk/kivonunk egyet

$0.125 = 0.001$
normalizálás : $0.001$ $\rArr$ $0.1000 \cdot 2^{-2}$
t = 4 $\rArr$  $1000$ 
jobb oldali szomszéd $\rArr$ 0.1001
A kapott szám $0.001001$, vagyis $0.140625$



#### 5. Feladat
$a=2, t=3, k−=−3, k+=3$ esetén mi lesz a 0.4375 normalizált alakja? 

##### megoldás:
10-es számrendszerbeli szám átírása 2-es számrendszerbe:
pl: 0.4375 (10) = 0.0111 (2)
```
0|.4375 *2  = 0.0111
0|.8750
1|.750
1|.50
1|.0
```

A tizedesvesszőt az első $1$-es számjegy elé eltoljuk, $t$ darab számjegynek kell a tizedesvessző után állnia, majd ez után a kapott számot  megszorozzuk $x$ annyiadik hatványára emelve, ahány jeggyel a tizedesvessző eltolásra került.
Ha a tizedesvessző $z$ hellyel balra tolódott, akkor $x^z$, ha jobbra akkor $x^{-z}$
```
0.0111 -> 0.111
            *** - t számjegy
```
Az így kapott szám: $2^{-1} \cdot 0.111$



#### 6. Feladat
Az $F=[a=2,k−=−6,k+=6,t=5]$ rendszerben a(z) $\frac{221}{576}$ szám normalizálva, szabályos kerekítéssel: 
##### megoldás:
10-es számrendszerbeli szám átírása 2-es számrendszerbe, majd normalizáljuk azt.
Megnézzük hogy $t$ esetén mi a $t+1$-ik számjegye, ha 0 akkor nem változik a szám 1-es esetén hozzáadunk a t-edikhez 1-et
```
0.11001|000111000111
```
Mivel a $t+1$-edik számjegy $0$, nem csinálunk vele semmit.
Majd levágjuk  $t$-n felüli elemeket
Az így kapott szám: $2^{-1} \cdot 0.11001$