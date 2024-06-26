# Юнит-тесты

Под "юнитом" понимается единица поведения, а не единица кода.

Тестируйте конечный результат вместо деталей реализации.

## Пишите хорошие юнит-тесты

Хороший юнит-тест должен обладать следующими четырьмя атрибутами:
- защита от багов;
- устойчивость к рефакторингу;
- быстрая обратная связь;
- простота поддержки.

Защита от багов показывает, насколько хорошо тест справляется с выявлением ошибок (регрессий). 
Чем больше кода проверяет тест (как вашего, так и кода библиотек и фреймворков, задействованных в проекте), тем выше вероятность того, что тест обнаружит ошибку.

Устойчивость к рефакторингу определяет, насколько тест хрупок: может ли он перенести рефакторинг рабочего кода, не выдавая ложных срабатываний.
Ложное срабатывание представляет собой "ложную тревогу": тест падает, но покрываемая им функциональность работает.

Защита от багов и устойчивость к рефакторингу составляют метрику точности теста. 
Тест точен, когда он выдает хороший сигнал (способен находить ошибки) с минимально возможным шумом (ложных срабатываний).

Быстрая обратная связь - мера того, насколько быстро выполняется тест.

Простота поддержки состоит из двух компонентов:
- сложность понимания теста. Чем меньше тест, тем проще он читается;
- сложность выполнения теста. Чем меньше внепроцессорных зависимостей, тем проще поддерживать их в работоспособном состоянии.

Эффективность теста определяется произведением этих четырех атрибутов. Если один из атрибутов равен нулю, то эффективность всего теста тоже равна нулю.

## Изолируйте юнит-тесты

Юнит-тест:
- проверяет одну единицу проведения;
- делает это быстро;
- и в изоляции от других тестов.

Классическая (детройтская) школа юнит-тестирования считает, что изолированы друг от друга должны быть юнит-тесты. 
Кроме того тестируется единица поведения, а не единица кода. Таким образом, только совместные зависимости должны заменяться тестовыми заглушками. 
Совместными называются зависимости, предоставляющие тестам возможность влиять на результаты друг друга.

## Оформление юнит-тестов

Все юнит-тесты должны строиться по схеме ААА или Given-When-Then: подготовка(Arrange, Given), действие(Act, When), проверка(Assert, Then).
Если тест состоит из нескольких секций подготовки, действий или проверки, это указывает на то, что тест проверяет сразу несколько единиц поведения. 
Если этот тест - юнит-тест, разбейте его на несколько тестов: по одному для каждого действия. 
Чтобы разделить три секции теста, либо включите в них соответствующий комментарий (Arrange, Act, Assert) или (Given, When, Then), либо вставьте пустые строки между секциями.

Секция действия, содержащая более одной строки, — признак проблем с API тестируемой системы. 
Клиент должен не забывать выполнять эти действия совместно, что бы не привести к нарушению логической целостности.

Параметризованные тесты помогают сократить объем кода, необходимого для похожих тестов.

Assertion-библиотеки помогают улучшить читаемость кода за счет реструктуризации порядка слов в проверках в тестах.

Используйте аннотацию @DisplayName над тестовым классом и методом для описания выполняемых проверок.

```java
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.example.ArithmeticOperation;

import java.util.Arrays;
import java.util.List;

@DisplayName("Проверка арифметических операций")
public class ArithmeticOperationTest {

    @DisplayName("Проверка суммы двух чисел")
    @ParameterizedTest
    @MethodSource("additionParameters")
    public void testAddition(int a, int b, int expected) {

        // Arrange
        ArithmeticOperation arithmeticOperation = new ArithmeticOperation();

        // Act
        int sum = arithmeticOperation.addition(a, b);

        // Assert
        Assertions.assertThat(expected).isEqualTo(sum);
    }

    private static List<Arguments> additionParameters() {
        return Arrays.asList(
                Arguments.of(1, 2, 3),
                Arguments.of(Integer.MIN_VALUE, Integer.MAX_VALUE, -1)
        );
    }
}
```

## Полезные ресурсы
Про практику AAA можно прочитать в статье Владимира Хорикова [Making Better Unit Tests: part 1, the AAA pattern](https://freecontent.manning.com/making-better-unit-tests-part-1-the-aaa-pattern/)  
Про практику Given-When-Then можно прочитать в статье Мартина Фаулера [Given When Then](https://martinfowler.com/bliki/GivenWhenThen.html)  

