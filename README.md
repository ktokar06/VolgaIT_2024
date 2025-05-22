
# 📜 Волга ИТ Автоматизация тестирования на Java
# 📜 Тестирование игры "Крестики-нолики" с помощью Selenium


## 📦 Установка

1. Clone repositories:
   
   ```bash
   git clone https://github.com/ktokar06/TicTacToeTest.git
   ```

2. Go to the project directory:
   
   ```bash
   cd TicTacToeTest
   ```

### Необходимые зависимости

- **Java**: Убедитесь, что у вас установлена Java Development Kit (JDK).
- **Maven**: Для управления зависимостями.
- **ChromeDriver**: Соответствующая версия ChromeDriver для вашей версии браузера Google Chrome.

#### Структура кода
1. Импорт библиотек
   Код начинается с импорта необходимых библиотек, включая JUnit для тестирования и Selenium для управления браузером.

``` Java
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Allure;
```
2. Настройка окружения
   В методе setUp() создаются экземпляры WebDriver и WebDriverWait, после чего открывается браузер и производится поиск игры "Крестики-нолики".


``` Java
@BeforeEach
public void setUp() {
driver = new ChromeDriver();
wait = new WebDriverWait(driver, Duration.ofSeconds(10));
driver.get("https://www.google.com");
searchGame();
}
```
3. Тестовые методы
   Тесты реализованы в трех методах:

testPlayGame: Тестирует стандартную игру между двумя игроками.
testGameDraw: Проверяет сценарий ничьей.
testPlayerOWins: Проверяет сценарий, когда выигрывает игрок O.


4. Логика игры
   Методы playGame, simulateDraw, и simulateOWin реализуют логику игры. В них прописаны действия игроков и проверки состояния игрового поля.

``` Java
 private void playGame() throws IOException {
        int movesCount = 0; // Count the number of moves made

        // Main game loop
        while (movesCount < 9) {
            boolean madeMove = false; // Track if a move was made

            // Check each cell to make a move
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (isCellAvailable(i, j)) {
                        makeMove(i, j); // Make a move if cell is available
                        madeMove = true;
                        movesCount++;
                        if (checkWinner()) { // Check for a winner after the move
                            System.out.println("Победитель найден!"); // Winner found message
                            return;
                        }
                        break; // Break to start the next round
                    }
                }
                if (madeMove) break; // Exit the outer loop if a move was made
            }
            if (!madeMove) break; // Exit if no moves were made
        }
        // If all moves are done and no winner
        if (movesCount == 9) {
            System.out.println("Игра закончилась вничью."); // Game ended in a draw
        }
    }
``` 

5. Проверка победителя
   Метод checkWinner анализирует игровое поле и определяет, есть ли победитель.

``` Java

    private boolean checkWinner() {
        String[][] board = new String[3][3]; // Initialize the board

        // Fill the board with current game state
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                String xpath = String.format("//tr[%d]/td[%d]", i, j);
                board[i - 1][j - 1] = driver.findElement(By.xpath(xpath)).getText();
            }
        }

        // Check rows and columns for a winner
        for (int i = 0; i < 3; i++) {
            if ((board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].isEmpty()) ||
                (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].isEmpty())) {
                return true; // Winner found
            }
        }

        // Check diagonals for a winner
        if ((board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].isEmpty()) ||
            (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].isEmpty())) {
            return true; // Winner found
        }

        return false; // No winner
    }

``` 

6. Обработка исключений
   Если при клике на ячейку возникает исключение ElementClickInterceptedException, делается скриншот для дальнейшего анализа.

``` Java
       private void makeMove(int row, int col) throws IOException {
        String xpath = String.format("//tr[%d]/td[%d]", row, col);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click(); // Click the cell
        } catch (ElementClickInterceptedException e) {
            captureScreenshot(); // Capture screenshot if click is intercepted
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(Files.readAllBytes(Paths.get("screenshot.png"))));
            throw e; // Rethrow the exception for further handling
        }
``` 

7. Завершение теста
   Метод tearDown закрывает браузер после выполнения тестов, чтобы освободить ресурсы.

``` Java
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser to free resources
        }
    }
}
``` 

Заключение
Данный проект демонстрирует возможность автоматизации тестирования для веб-приложений. Тестирование игры "Крестики-нолики" показывает, как можно использовать Selenium для взаимодействия с веб-элементами и проверки результатов игры. Вы можете расширить проект, добавив новые сценарии и улучшив логику игры.

