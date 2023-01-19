# Hinweise

Ein Lambda hat von sich aus keinen Typ (wir sagen nicht new YY() wie bei einer anonymen Klasse).

Wie kann dann trotzdem der Typ eines Lambdas bestimmt
werden (er muss bestimmt werden!)?

Es gibt vier Möglichkeiten:

1. durch den Typ der zugewiesenen Variable, also z.B. `Runnable r = () -> {}`
2. durch einen Cast
3. durch den Typ eines Methoden-Parameters
4. durch den Typ der `return` Deklaration einer Methode

## Vorgehen

1. die vier demo Methoden vorstellen


