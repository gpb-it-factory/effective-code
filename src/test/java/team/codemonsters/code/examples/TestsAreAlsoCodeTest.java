package team.codemonsters.code.examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestsAreAlsoCodeTest {

    @Nested
    class UglyTests {
        private static int sharedState = 0;

        // название теста ни о чём не говорит
        @Test
        void test3() {
            // Использование Sleep для эмуляции работы
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Нечитаемые названия перменных и magic-number
            int x = 42;
            Assertions.assertEquals(42, x);

            // Множественные ассёрты - нет понимания, что проверяется
            Assertions.assertEquals(5, 2 + 3);
            Assertions.assertEquals("Hello", "Hello");
            Assertions.assertTrue(true);

            // Игнор исключений
            try {
                throw new Exception("Test Exception");
            } catch (Exception e) {
                // Ignore
            }

            // Модификация общего состояния - это не юнит-тест, так как нарушается изоляция
            sharedState += 1;
            Assertions.assertEquals(1, sharedState);

            var a = 2;
            var b = 3;
            var c = a + b;

            // Логика и ветвление в тестах
            if (c == 3) {
                Assertions.assertEquals("Hello World", "World Hello");
            } else {
                Assertions.fail("This should never happen");
            }

            // Избыточное использование комментариев
            // Очень важная проверка математической функции, ожидаем 1+1=2
            Assertions.assertTrue(1 + 1 == 2); // Проверка мотематики, важно проверить математику
        }
    }
}




