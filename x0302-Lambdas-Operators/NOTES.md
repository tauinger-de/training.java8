# Hinweise

Die `onPlus` und die `onMinus` Methode unseres Taschenrechners sehen annähernd gleich aus.

Wie kann die Redundanz, die hier offensichtlich vorliegt, vermieden werden?

## Vorgehen

1. Vorstellung des Interface `BinaryOperator`
2. Implementierung neuer Methode `void onCalc(BinaryOperator op)`
3. in `registerListeners()` nun verschachtelte Lambdas
