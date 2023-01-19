# Übung

## ex1

### Einleitung

Wir arbeiten wieder mit der Klasse `DynamicArray`.

### Aufgaben

Erweitern Sie die Klasse derart, dass sie das Interface `Iterable` implementiert. Der Kopf der
Klasse soll also wie
folgt aussehen:

````java
public class DynamicArray<T> implements Iterable<T> {
}
````

Was fällt Ihnen am Interface `Iterator` auf?

## ex2

### Einleitung

Die Klasse `Processor` ist gemäß dem Template-Method-Pattern aufgebaut.

Die Template-Methode `run()` ruft die hook-Methoden `begin`,
`process` und `end` auf.

`begin` und `end` besitzen bereits eine Implementierung – `process` dagegen ist `abstract`. Der
Grund dafür ist, dass
nur die `process` Methode für jede Implementierung relevant ist, die anderen nur für manche. Dies
wird an den zwei
gegebenen Implementierungen deutlich.

### Aufgaben

Zerlegen Sie die obige `Processor` Klasse in zwei Teile:

* in die Klasse `ProcessorRunner`
* und ein Interface `Processor`

Die Klasse `ProcessorRunner` enthält nur die `run` Methode; diese delegiert an ein Objekt, dessen
Klasse das Interface `Processor` implementiert.

Dieses hat die Methoden `begin`, `process` und `end`. Die Methoden `begin` und `end` sollten dann
bereits defaultmäßig implementiert sein.

Und bringen Sie natürlich die Anwendung wieder zum Laufen...

**Bonus:**

* Implementieren Sie einen `Processor`, der den Input von hinten nach vorne sammelt. D.h. ein
  Input von "Java" erzeugt das Wort "avaJ"
* Was fällt Ihnen beim Input "Reliefpfeiler" auf? :)