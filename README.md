# Allegro Password Reset
Projekt powstał w celu przeprowadzenia warsztatów z tworzenia mikro-serwisów za pomocą Domain-Driven Design.

## Założenia wstępne
 - Istnieje frontend, który będzie korzystał z implementowanego serwisu (nie implementujemy frontendu).
 - Istnieje kolejka rozsyłająca wiadomości (nie implementujemy kolejki).
 - Dane z MongoDB, ze względu na wydajność, mogą być wyszukiwane tylko po ID dokumentu.
 
## Ogólny opis funkcjonalności
Zadaniem serwisu jest wystawienie możliwości odnowienia hasła przez użytkowników.
Dodatkowo serwis ma udostępniać dla administratorów historię wysłanych do użytkowników e-maili z linkiem do zmiany hasła.

## Krok 1
### Odnowienie hasła:
1. **_Użytkownik_** wchodzi na stronę gdzie podaje swój **_login_** lub **_email_**.  
    a) Jeżeli użytkownik posiada **_nawisko panieńskie_** swojej matki, **_imię_** i **_nazwisko_** to przechodzi do strony gdzie podaje nazwisko panieńskie.
    *(Pełna metoda odnawiania hasła)*  
    b) Jeżeli użytkownik nie posiada nazwiska panieńskiego, ale ma imię i nazwisko, to przechodzi do pkt. 2.
    *(Uproszczona metoda odnawiania hasła)*  
    c) W przeciwnym wypadku użytkownik nie może odnowić swojego hasła. **Proces się kończy**.
    *(Metoda odnawiania hasła niedostępna)*
2. Do użytkownika zostaje **_wysłany e-mail_** z linkiem do ustalenia nowego hasła.
Link przychodzi jako parametr z frontendu.
W temacie e-maila jest zawarte imię i nazwisko użytkownika.
3. Po kliknięciu na link użytkownik podaje nowe hasło.
4. Hasło użytkownika zostaje **_zaszyfrowane_** i zmienione. **Proces się kończy**.

### Pozyskiwanie danych o użytkownikach
1. Przychodzi wiadomość z kolejki z danymi użytkowników.
2. Dane użytkowników są zapisywane do MongoDB.

### Zadanie do zaimplementowania
1. Przełączyć się na branch step-1-start
2. Zaimplementować po kolei endpointy zaczynając od góry, tak aby testy przechodziły.
3. Porównać implementację z branchami step-1-endpoint-x.

## Krok 2
### Wyświetlenie historii wysłanych e-maili:
1. Podczas wysyłki e-maila do użytkownika dodawany jest wpis do **_historii_**.
2. Admin na stronie podaje **_id użytkownika_**.
3. Na stronie wylistowane są następujące dane: **_login użytkownika_**, **_adres e-mail użytkownika_**, **_data wysłania e-maila_**.

### Zadanie do zaimplementowania
1. Przełączyć się na branch step-2-start
2. Zaimplementować ostatni endpoint tak aby testy przechodziły.
3. Porównać implementację z branchami step-2-endpoint-1.

----

# Architektura DDD
Pomoc dydaktyczna.

## Logika aplikacji
#### Serwis aplikacyjny
 - Metoda = przypadek użycia
 - Operuje na modelu
 - Bezstanowy
 
#### Kontrakt z serwisem infrastrukturalnym
 - Definiuje funkcjonalności pomocnicze
 - Interfejs
 
## Model
#### Agregat
 - Podstawowa jednostka operacyjna
 - Powiązane encje i value objecty
 - Jeden punkt wejściowy – korzeń
 - Zawsze w prawidłowym stanie

Encja:

 - Unikalne ID
 - Mutowalna
 - Nieanemiczna
 
Value Object:

 - Brak unikalnego pola
 - Niemutowalny
 - Typ złożony

#### Serwis domenowy
 - Metoda = zachowanie logicznie nie pasujące do żadnej encji
 - Bezstanowy

#### Fabryka
 - Tworzy agregaty
 - Ogranicza sposoby tworzenia agregatu
 - Wyciąga złożoną logikę z konstruktorów

#### Repozytorium
 - Zarządza utrwalaniem agregatów
 - Interfejs

#### Zdarzenie domenowe
 - Oddziela model od innych warstw
 - Konsumowane w innych warstwach

#### Polityka
 - Odzwierciedla wykonanie jednej operacji na kilka sposobów
 - Wzorzec projektowy: Strategia

#### Kontrakt z serwisem infrastrukturalnym
 - Definiuje funkcjonalności pomocnicze
 - Interfejs

## Infrastruktura
#### Serwis infrastrukturalny
 - Spełnia kontrakt zdefiniowany w innych warstwach
 - Bezstanowy

#### Implementacja repozytorium
 - Spełnia kontrakt zdefiniowany przez repozytorium
 - Określa sposób utrwalania agregatów
 - Bezstanowa
