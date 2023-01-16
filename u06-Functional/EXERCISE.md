# Übung

## ex2

### Einleitung

Objekte vom Typ `Foo` sollen in einer `BiMap` gespeichert werden können.

Das gedachte Verhalten der BiMap in `ex1.Application.main()` definiert.

### Aufgaben

Implementieren Sie die fehlenden Methodeninhalte in der Klasse `BiMap`.

Führen Sie mittels der `Value`-Klasse etwas komplexere Berechnungen durch (Strichrechung gemischt mit Punktrechnung
etc.). Programmieren Sie fluent.

## ex2

### Aufgabe

Studieren Sie die Klasse `Value` und die `Application.main()` Methode.

Die Klasse `Processor` ist gemäß dem Template-Method-Pattern aufgebaut.

Die Template-Methode `run()` ruft die hook-Methoden `begin`,
`process` und `end` auf.

`begin` und `end` besitzen bereits eine Implementierung – `process` dagegen ist `abstract`. Der Grund dafür ist, dass
nur die `process` Methode für jede Implementierung relevant ist, die anderen nur für manche. Dies wird an den zwei
gegebenen Implementierungen deutlich.

### Aufgaben

Zerlegen Sie die obige `Processor` Klasse in zwei Teile – in die Klasse `ProcessorRunner` und `Processor`. Die Klasse
`ProcessorRunner` enthält nur die `run` Methode; diese delegiert an ein Objekt, dessen Klasse das
**Interface** `Processor` implementiert.

Dieses hat die Methoden `begin`, `process` und `end`. Die Methoden `begin` und `end` sollten dann bereits
defaultmäßig implementiert sein. Und bringen Sie natürlich die Anwendung wieder zum Laufen...