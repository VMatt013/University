# Feladat

Ebben a feladatban tv-sorozat epizódokkal kell dolgoznod. A `tvshow.Episode` rekord osztály egy tv-sorozat epizódot ábrázol, az alábbi komponensei vannak:

* `season`: évad száma (`int`);
* `number`: az epizód száma az évadon belül (`int`);
* `title`: cím (`String`);
* `airdate`: premier napja (`java.time.LocalDate`);
* `airtime`: hány órakor került adásba az epizód a premier napján (`java.time.LocalTime`);
* `runtime`: epizód hossza percben (`int`);
* `rating`: átlagos pontszám (`Double`), lehet `null`;
* `summary`: epizód tartalmának összefoglalása (`String`);

Az `src/main/resources/tvshow` könyvtár alatt találod a `dallas.ser` bináris állományt, mely 356 darab `Episode` objektum egy listáját tartalmazza. A lista objektum sorosítással került kiírásra az állományba. Az objektumok a [Dallas](https://www.tvmaze.com/shows/863/dallas) című sorozat részeit ábrázolják.

A `tvshow.EpisodeManager` interfész `Episode` objektumok kezeléséhez biztosít támogatást. A `getEpisodes()` alapértelmezett metódus adja vissza a 356 `Episode` objektum listáját.

Az interfész négy absztrakt metódust tartalmaz, ezeket lásd a forráskódban.

## Előkészületek

A továbbiakban minden Git műveletet a projekt `pom.xml` állományát tartalmazó könyvtáradban kell végrehajtani.

Hozz létre egy üres Git tárolót a

```shell
git init
```

parancs  végrehajtásával.

Konfiguráld a Git-et megfelelően beállítva a tárolóban a `user.name` és `user.email` opciókat a

```shell
git config --local user.name "teljes_hivatalos_név"
git config --local user.email email_cím
```

parancsokkal. Figyeld meg, hogy a `--local` opció megadásával arra utasítjuk a Git-et, hogy az opciókat a tárolóban, a `.git` rejtett könyvtár alatt raktározza el.

Importáld a projekt könyvtárát a tárolóba a

```shell
git add .
git commit -m "initial import"
```

parancsok végrehajtásával.

## Interfész implementálás

A feladatod egy, az interfészt implementáló `EpisodeManagerImpl` nevű osztály létrehozása a `tvshow` csomagban.

Először is implementáld az interfészt az IntelliJ IDEA [Implement Methods](https://www.jetbrains.com/help/idea/implementing-methods-of-an-interface.html) lehetőségével, mely "csonkokat" hoz létre az interfész metódusokhoz. Az IDE által generált metódustörzsek üresek lesznek vagy pedig egy `return 0;`, `return null;` vagy `return Optional.empty();` utasítást fognak tartalmazni. Tipp: a _Copy Javadoc_ jelölő bekattintása arra utasítja az IDE-t, hogy az interfész metódus Javadoc megjegyzését is másolja le, ez jól jön!

Adj hozzá az `EpisodeManagerImpl` osztályhoz egy `main` metódust. A `main` metódus törzsének első sorában deklarálj egy olyan lokális változót, melynek értéke egy referencia az osztály egy példányára.

**Git művelet**: Add hozzá a metódus "csonkokat" és a `main` metódust tartalmazó `EpisodeManagerImpl` osztályt a tárolóhoz egy _commit_-ban, melynek üzenete legyen `"feat: add EpisodeManagerImpl class with method stubs"`.

Az `EpisodeManagerImpl` osztályban az alábbi módon kell implementálnod minden egyes absztrakt interfész metódust:

* Ha a metódus `void` visszatérési típusú és a neve `print`-tel kezdődik, akkor az alábbi módon kell, hogy kinézzen az implementációja:

  ```java
  @Override
  public void printSomething() {
      getEpisodes().stream()
          // köztes műveletek
          .forEach(System.out::println);
  }
  ```

* Ha a visszatérési típus nem `void`, akkor az implementáció az alábbi módon kell, hogy kinézzen:

  ```java
  @Override
  public SomeType getSomething() {
      return getEpisodes().stream()
          // köztes műveletek
          // terminális művelet
          ;
  }
  ```

Tehát a metódustörzsek pontosan egy _stream_ csővezetéket kell, hogy tartalmazzanak, semmi más nem megengedett! Elutasításra kerülnek az olyan metódusok, melyek törzse bármi egyebet tartalmaz, mint egy _stream_ csővezeték.

Ezután az interfész használatának bemutatásához a `main` metódusban meg kell hívnod a példányon mind a négy absztrakt interfész metódust. Ha egy metódus egy értéket ad vissza, akkor azt a konzolon meg is kell jeleníteni.

Egy interfész metódus implementációja 1%-ot ér, azonban akkor, és csak akkor jár a pont, ha **az alábbiak mindegyike teljesül**:

* A metódustörzs semmi egyebet nem tartalmaz, mint egy _stream_ csővezeték (nem `void` metódus esetén előtte egy `return` utasítással).

* A _stream_ csővezeték pontosan azt csinálja, ami az interfész metódus Javadoc megjegyzésében is elő van írva. **Nem jár részpont a részben helyes implementációkra.**

* A metódus meghívásra kerül az `EpisodeManagerImpl` osztály `main`  metódusában. Ha a metódus értéket ad vissza, az a konzolra is kiírásra kerül.

Szigorúan tilos az `EpisodeManager` interfészben bármiféle módosítás. Például nem változtatható meg egy metódus visszatérési típusa. Ha az interfészben bármit is megváltoztatsz, akkor a megoldásod automatikusan elutasításra kerül.

**Git művelet**: Véglegesítd a változásokat a tárolóban egy _commit_-ban, melynek üzenete legyen `"fix: implement interface methods in EpisodeManagerImpl"`.

## Függőségek hozzáadása a projekthez

**Git művelet**: Hozz létre a tárolóban egy `flogger` nevű ágat és válts át rá. A további változások mind a `flogger` ágon kell, hogy történjenek.

A `pom.xml` állományban add hozzá az alábbi függőségeket a projekthez:

* [`com.google.flogger:flogger:0.8`](https://central.sonatype.com/artifact/com.google.flogger/flogger/0.8), melynek hatásköre (`scope`) legyen `compile`;

* [`com.google.flogger:flogger-system-backend:0.8`](https://central.sonatype.com/artifact/com.google.flogger/flogger-system-backend/0.8), melynek hatásköre (`scope`) legyen `runtime`.

Tehát a [Flogger](https://github.com/google/flogger) könyvtárral fogunk naplózni a projektben.

**Fontos**: A függőségek hozzáadása után kattints a `pom.xml` állományon az egér jobb gombjával a _Project_ fülön, majd válaszd a _Reload project_ pontot a _Maven_ almenüből.

**Git művelet**: Véglegesítd a változásokat a tárolóban egy _commit_-ban, melynek üzenete legyen `"build: add Flogger dependencies to pom.xml"`.

Ezután az alábbi módosításokat kell elvégezned az `EpisodeManager` interfészben. Először is add hozzá a kódhoz az

```java
import com.google.common.flogger.FluentLogger;
```

import deklarációt. Majd pedig szúrd be az alábbi sort a `getEpisodes()` alapértelmezett metódus `return` utasítása elé:

```java
FluentLogger.forEnclosingClass().atInfo().log("Loaded %d objects", episodes.size());
```

Ha tehát meghívásra kerül az alapértelmezett metódus, akkor a Flogger naplózza a betöltött objektumok számát.

1%-ot kapsz, ha a betöltött objektumok száma megjelenik a konzolon a program futtatásakor.

**Git művelet**: Véglegesítd a változásokat a tárolóban egy _commit_-ban, melynek üzenete legyen `"feat: add logging with Flogger to EpisodeManager"`.

## Tippek

Egy [java.time.LocalDate](https://docs.oracle.com/en%2Fjava%2Fjavase%2F21%2Fdocs%2Fapi%2F%2F/java.base/java/time/LocalDate.html) objektum esetén a [getDayOfWeek()](https://docs.oracle.com/en%2Fjava%2Fjavase%2F21%2Fdocs%2Fapi%2F%2F/java.base/java/time/LocalDate.html#getDayOfWeek()) metódusa adja vissza, hogy a dátum a hét mely napjára esik, a visszatérési típusa [java.time.DayOfWeek](https://docs.oracle.com/en%2Fjava%2Fjavase%2F21%2Fdocs%2Fapi%2F%2F/java.base/java/time/DayOfWeek.html).

Szükséged lesz a [java.lang.Double](https://docs.oracle.com/en%2Fjava%2Fjavase%2F21%2Fdocs%2Fapi%2F%2F/java.base/java/lang/Double.html) osztály [doubleValue()](https://docs.oracle.com/en%2Fjava%2Fjavase%2F21%2Fdocs%2Fapi%2F%2F/java.base/java/lang/Double.html#doubleValue()) metódusára is, mely a `Double` objektumnak megfelelő primitív `double` típusú értéket adja vissza.
