# Hinweise

## Producer

* Warum kann eine Instanz vom Typ `Box<B>` nicht `Box<A>` zugewiesen werden?
    * Weil diese Instanz ja nur `B` oder
      spezieller verarbeiten kann -- und wir sonst eine Hintertür schaffen würden, da doch ein `A` hereinzustecken
* `set(null)` ist einzige was geht, weil die Box könnte ja NOCH SPEZIELLER sein (z.B. nur D verarbeiten) und null der
  einzige Wert ist, der nie zu ungenau ist
* extends = kovarianz = "was kann dieser producer mindestens liefern"

## Consumer

* Gegenteil: `super` = kontravarianz = "was kann dieser Consumer mindestens akzeptieren"
* super bedeutet, der generische Typ ist von diesem Typ oder von Superklasse