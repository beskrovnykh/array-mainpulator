

# Оптимизация алгоритма суммирования массивов

Вычисления проводил на ноутбуке Quad-Core Intel Core i7 1,2 GHz, 4 Core

1) Лобовое решение + вывод в консоль ~38 мин
```
Total execution time: 2297586 ms
Result: 2147483646
[Finished in 2298.73s]
```

Из-за переполнения ответ неверный

2) Уменьшение числа операций, корректировка но все еще квадратичная сложность

```
javac NaiveArrayManipulator.java && java NaiveArrayManipulator
```
```
Total execution time: 178015 ms
Result: 2501448788
[Finished in 179.03s]
```

3) Улучшение последовательного алгоритма. Идея в том, чтобы решать задачу в два этапа. Не "разворачивать" операции (10^5), а инкрементально накапливать в "упакованном" виде. 
На втором этапе "разворачивать" изменения и восстанавливать искомый массив. После этого искать максимум по полученному массиву (10^7).

```
javac ArrayManipulator.java && java ArrayManipulator
``` 

```
Total execution time: 58 ms
Result: 2501448788
[Finished in 1.01s]
```
4) Операции второго этапа можно разбить на несколько групп и считать рекурсивно с помощью ForkJoinPool. Идея состояла в этом. Состояние каждой части алгоритма описывается максимальной частичной суммой элементов ряда и суммой всех его элементов. Решение не показало ускорения, что было ожидаемо из-за небольшого кол-ва элементов в массиве (10^7).

На множестве пар $a =(a_{sum}, a_{max})$ операция 
$a \bigoplus b = (a_{sum} + b_{sum}, \max(a_{max}, a_{sum} + b_{max})$
будет удвовлетворять отношению $(a \bigoplus b) \bigoplus c = a \bigoplus (b \bigoplus c)$, значит паралелльная реализация корректна

```
javac ParallelArrayManipulator.java && java ParallelArrayManipulator
```
```
Total execution time: 101 ms
Result: 2501448788
[Finished in 1.17s]
```

Запуск тестов для проверки корректности параллельного решения 

```
javac -cp .:lib/junit-4.12.jar ParallelArrayManipulatorTest.java && java -cp .:lib/junit-4.12.jar:lib/hamcrest-2.2.jar org.junit.runner.JUnitCore ParallelArrayManipulatorTest
```



