# Allegro Password Reset
Projekt powstał w celu przeprowadzenia warsztatów z tworzenia mikro-serwisów za pomocą Domain-Driven Design.

## Założenia wstępne
 - Istnieje frontend, który będzie korzystał z implementowanego serwisu (nie implementujemy frontendu).
 - Istnieje kolejka rozsyłająca wiadomości (nie implementujemy kolejki).
 - Dane z MongoDB, ze względu na wydajność, mogą być wyszukiwane tylko po ID dokumentu.
 
## Ogólny opis funkcjonalności
Zadaniem serwisu jest wystawienie możliwości odnowienia hasła przez użytkowników.
Dodatkowo serwis ma udostępniać dla administratorów historię wysłanych do użytkowników e-maili z linkiem do zmiany hasła.

### KROK 1: Odnowienie hasła:
1. **_Użytkownik_** wchodzi na stronę gdzie podaje swój **_login_** lub **_email_**.  
    a) Jeżeli użytkownik posiada **_nawisko panieńskie_** swojej matki, **_imię_** i **_nazwisko_** to przechodzi do strony gdzie podaje nazwisko panieńskie.  
    b) Jeżeli użytkownik nie posiada nazwiska panieńskiego, ale ma imię i nazwisko, to przechodzi do pkt. 2.  
    c) W przeciwnym wypadku użytkownik nie może odnowić swojego hasła. **Proces się kończy**.
2. Do użytkownika zostaje wysłany e-mail z linkiem do ustalenia nowego hasła.
Link przychodzi jako parametr z frontendu.
W temacie e-maila jest zawarte imię i nazwisko użytkownika.
3. Po kliknięciu na link użytkownik podaje nowe hasło.
4. Hasło dla użytkownika zostaje zmienione. **Proces się kończy**.

### KROK 2: Pozyskiwanie danych o użytkownikach
1. Przychodzi wiadomość z kolejki z danymi użytkowników.
2. Dane użytkowników są zapisywane do MongoDB.

### KROK 3: Wyświetlenie historii wysłanych e-maili:
1. Podczas wysyłki e-maila do użytkownika dodawany jest wpis do **_historii_**.
2. Admin wchodzi na strone gdzie wylistowane są następujące dane: **_login użytkownika_**, **_adres e-mail użytkownika_**, **_data wysłania e-maila_**.
