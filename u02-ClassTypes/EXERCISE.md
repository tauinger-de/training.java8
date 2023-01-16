# Übung

## A) Anonyme Klassen

Schauen Sie sich den Source-Code im Package `ex1` an.

Vereinfachen Sie die Anwendung, indem Sie die `CountingRunnable` Klasse als anonyme Klasse
implementieren.

Können Sie in der anonymen Klasse das "äußere" Objekt ansprechen? Wenn nicht: warum nicht?

## B) Innere Klasse

Bauen Sie die Anwendung derart um, dass Sie in der anonymen Klasse ein äußeres Objekt ansprechen
können.

## C) Thread

Benutzen Sie statt eines `Runnable` eine Ableitung von `Thread`. Implementieren Sie diese Ableitung
wieder als anonyme Klasse.

## D) Zugriff auf Variablen

In lokalen und anonymen Klassen können Sie auch lokale Variablen bzw Parameter derjenigen Methode
ansprechen, in welcher die innere Klasse jeweils definiert ist.

Zeigen Sie dies, indem Sie die Anzahl der durchzuführenden Schleifendurchläufe aus einer Variablen
der umschließenden Methode auslesen.

