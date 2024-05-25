* Problem in org.demo.MainToRefactor
* Solution in org.demo.bmi


Note: Depending on the context of a problem, we also could consider the option to decompose the class into several ones:
`Human` (entity), `HumanBmi` (value object) and even `HumanBmiCalculations` (util/service). 
However, in scope of this task I've decided to keep only minimal necessary changes


1. The purpose of a comment in `Main` is unclear; configuration details should be placed in a separate file (e.g. README)
2. Move `humanIMB` to a separate file
3. Make `humanIMB` class `public`
4. Class name should start with Uppercase letter (`humanIMB` -> `HumanImb`) (java naming convention)
5. Rewrite `IMB` in class name to `Imb` in order to comply with the CamelCase approach (java naming convention)
6. Replace `Imb` in class name with conventional abbreviation - `Bmi` (`HumanBmi`) and add javadoc
7. Remove `static` for `imb`, since the property is not common for all instances of the class
8. Change `imb` to `value` in order to get rid of gratuitous context and naming duplication
9. Make `W`, `H` `private` to ensure encapsulation 
10. Field names (`W`, `H`) should start with lowercase letter (unless it is a constant)
11. Replace `W`/`H` with `weight`/`height` (meaningful naming)
12. `take`/`put` should be `get`/`set` (java naming convention)
13. Make the class `immutable` (`final` class and properties, no setters or setters instantiate new object)
14. Extract BMI calculation logic to a separate method
15. Use logical `&&` `||` instead of bitwise `&` `|` in conditional statements
16. Add input validation -> `checkPositivityOf(values)`
17. Method names (`Result`) should start with lowercase letter
18. Name `Result` has ambiguous meaning: is it computed index value or status that represents this value? 
-> rename to `identifyStatus()`
19. `string` in `Result` is not meaningful
20. Two spaces between `String` and `string` (should be one)
21. No spaces between `string` and `=`
22. No spaces between operators >,=,< and digits
23. Replace string with `return` statements
24. Introduce `enum Status` constants instead of using string values (Norm, Fat, etc.);
it's enough to declare the enum in scope of the `HumanBmi`, since it has high cohesion with the context of the class
25. `Optimize status calculation` -> 
introduce field `status`; 
as I understand from the context, we should calculate it not from the start, but later on demand
   -> from this perspective, I suggest using the mechanism of lazy initialization (init on demand)
26. Define static factory method `HumanBmi.calculate()` to increase readability and make constructor private to make programmers use only the factory method
27. Define `equals()`, `hashcode()`, `toString()` (java best practice)
2Add unit-tests