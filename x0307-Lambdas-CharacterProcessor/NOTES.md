# Hinweise

Als weiteres Beispiel bauen wir einen `CharacterProcessor`.

Ein `CharacterProcessor` bekommt eine Folge von Zeichen, welche er zeichenweise lesen wird.
Die Verarbeitung jedes Zeichens soll an einen `Handler` delegiert werden.

Der Input wird über einen `Reader` eingelesen.

## Vorbereitung

1. Verstehen, was ein `java.io.Reader` ist
2. `Handler` Interface anschauen und verstehen
3. `CharacterProcessor` anschauen, erste Ausbaustufe

## Vorgehen

1. demo1 - demo5 der Reihe nach anschauen und aus main() aufrufen
2. `Testers` Bibliotheksklasse erweitern (z.B. Ziffern zählen) und nutzen

## Übungen

In einer neuen Methode `demo8` Methode:

1. Implementieren Sie einen Handler, der das Zeichen als Großbuchstabe ausgibt
2. Implementieren Sie einen Tester, der nur Vokale beachtet
3. Lassen Sie den String "Wasser in Dortmund" bearbeiten - welche Ausgabe erscheint?

