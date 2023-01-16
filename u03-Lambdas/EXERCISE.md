# Übung

## ex2

Wir implementieren eine Klasse `DynamicArray`, welches die Speicherung einer beliebigen Anzahl
an Elementen ermöglicht (sehr ähnlich einer `ArrayList`...).

Die Klasse implementiert eine Reallokations-Strategie: immer dann, wenn das interne Array voll ist,
wird ein neues Array erzeugt. Die in der Klasse programmierte Strategie hierfür ist die
Verdopplung der Array-Größe.

Die "fest verdrahtete" Berechnung der neuen Größe soll ersetzt werden durch eine vom Benutzer
definierbare Strategie. Dafür soll das vorgegebene Interface `Reallocator` genutzt werden.

1. Schauen Sie sich die Klassen im Package `ex2` an
2. Implementieren und nutzen Sie eine neue Reallokations-Strategie
3. Ersetzten Sie dann das `Reallocator` Interface durch das Standard-Interface `IntFunction`

