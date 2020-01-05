# Accounting App

Simulace jednoduché účetní aplikace

## Spuštění

Pomocí skriptu `run.sh`. 

Skript provede build aplikace a následné spuštění Docker kontejneru. Kontejner obsahuje databázi a prostředí pro běh aplikace. Jako první se provede spuštění databáze a po jejím načtení a spuštění se spustí prostředí pro aplikaci.

Dostupné na adrese

Windows: `http://192.168.99.100:8080/`

OS X a Linux: `http://localhost/`

## Uživatelé


| Login     | Password | Role          |
|-----------|----------|---------------|
| admin001  |   1234   |     admin     |
| admin002  |   1111   |     admin     |
| purser0001|   0001   |     purser    |
| purser0002|   0002   |     purser    |

## Implementace

Byly implementovány všechny povinné funkce ze zadání `https://github.com/osvetlik/pia/blob/master/SEMESTRALKA.md`.

### Validace
Data jsou kontrolována pouze u formulářů pro práci s uživatelem. Je ošetřeno jak zadání povinných hodnot, tak maximální možná velikost vstupu. U vstupů pro známé formáty (tel, číslo, email, číslo účtu, ...) je kontrola prováděna pomocí regexů. Validace je implementována v souboru `UserValidator`.

### Responzibilita
Vychází primárně z bootstrapu. Menu je na menších obrazovkách (< 700px) nahrazeno vyskakovacím. Dvousloupcový layout je použit v detailu faktury.

### Přidávání položek do faktury
Je nutné zadat všechny položky validní. Přidávání položek do faktury je řešeno pomocí JS, který přidá další řádky. Tyto řádky je nutné vyplnit.