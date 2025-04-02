package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import utils.ScreenshotUtils;

@Epic("Tic Tac Toe Game Tests")
@Feature("Player O Win Scenarios")
public class PlayerOWinTest extends BaseTest {

    @Test
    public void testPlayerOWins() {
        try {
            simulateOWin();
        } catch (Exception e) {
            ScreenshotUtils.captureAndAttach(driver, "Player O Wins Error");
            throw e;
        }
    }

    private void simulateOWin() {
        // X moves
        gamePage.makeMove(1, 1);
        gamePage.makeMove(2, 1);
        gamePage.makeMove(3, 1);

        // O moves
        gamePage.makeMove(1, 2);
        gamePage.makeMove(2, 2);
        gamePage.makeMove(3, 2);
    }
}