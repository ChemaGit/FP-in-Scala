## A Functional Collection: Set
````scala
val set = Set(1 , 2 , 3)

// Set instances are callable (they have apply)
set(2) // true
set(42) // false

/**
Set instances are callable like functions
The apply method always returns a value: true/false.

=> Sets behave like actual functions

Sets are functions!
*/

trait Set[A] extends (A) => Boolean with...
````