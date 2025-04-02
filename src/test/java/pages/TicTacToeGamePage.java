package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TicTacToeGamePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public TicTacToeGamePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectPlayWithFriend() {
        try {
            WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'game-modules')]//g-dropdown-menu")));
            menu.click();

            WebElement friendOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@role='menuitem' and contains(., 'Друг')]")));
            friendOption.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to select play mode: " + e.getMessage());
        }
    }

    public void makeMove(int row, int col) {
        String xpath = String.format("//tr[%d]/td[%d]", row, col);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    public boolean isCellAvailable(int row, int col) {
        String xpath = String.format("//tr[%d]/td[%d]", row, col);
        WebElement cell = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return cell.getText().isEmpty();
    }

    public boolean checkWinner() {
        String[][] board = new String[3][3];
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                String xpath = String.format("//tr[%d]/td[%d]", i, j);
                board[i-1][j-1] = driver.findElement(By.xpath(xpath)).getText();
            }
        }

        return checkRows(board) || checkColumns(board) || checkDiagonals(board);
    }

    private boolean checkRows(String[][] board) {
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].isEmpty() && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(String[][] board) {
        for (int i = 0; i < 3; i++) {
            if (!board[0][i].isEmpty() && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(String[][] board) {
        return (!board[0][0].isEmpty() && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) ||
                (!board[0][2].isEmpty() && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]));
    }
}