# Hinweise

Eine `Map` kann Schlüssel auf Objekte abbilden -- und natürlich auch auf Lambda-Objekte.

Angenommen, wir definieren folgende Map:

````java
private final Map<String, BinaryOperator> operators=new LinkedHashMap<>();
````

Dann können wird in diese Map vier anonyme BinaryOperator-Funktionen eintragen:

````java
{
        operators.put("Plus",(x,y)->x+y);
        operators.put("Minus",(x,y)->x-y);
        operators.put("Times",(x,y)->x*y);
        operators.put("Div",(x,y)->x/y);
        }
````

Dadurch können wir das User-Interface und das Verhalten des Taschenrechners dynamisch erzeugen.

Vorgehen:

1. `operators` Map erzeugen
2. Neue Methode `private void addButtons()`
