# Hinweise

In den letzten beiden Abschnitten wurde das selbst-definierte 
funktionale Interface `BinaryOperator` benutzt.

Java 8 enthält ein Paket `java.util.function`, in welchem ein ganz ähnliches Interface 
bereits definiert ist -- allerdings eines, welches mit einem generischen Typ parametrisiert ist:

````java
public interface BinaryOpreator<T> {
    T apply(T t0, T t1);
}
````

Dieses wollen wir nun benutzen.

## Vorgehen

1. BinaryOpreator importieren
2. generischen Typ definieren
