# Hinweise

Das folgende Beispiel demonstriert, wie aus einem nicht-funktionalen Interface funktionale
Interfaces gebaut werden können.

Eine Heizung (oder ein Kühlschrank...) soll mittels eines Thermostats gesteuert werden können.

Im Folgenden werden zwei Lösungen präsentiert – die erste benutzt ein nicht-funktionales Interface,
die zweite ein funktionales Interface (und ist damit für die Verwendung von Lambdas prädestiniert).

## Vorgehen

1. Application.demoX() Methoden anschauen und Klassen vorstellen, dann ausführen

## Übung

Das Thermostat-Heater-Beispiel soll erweitert werden.

Die `onAlarm` Methode des Interfaces `AlarmListener` soll einen `AlarmEvent` als Parameter übergeben
bekommen:

````java
public interface AlarmListener {
    void onAlarm(AlarmEvent e);
}
````

`AlarmEvent` sei wie folgt definiert:

````java
public class AlarmEvent<S> {
    public final S source;
    public final String message;

    public AlarmEvent(S source, String message) {
        // ...
    }
}
````

Dann muss natürlich auch die Anwendung angepasst werden...
