package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import utils.ScreenshotUtils;

@Epic("Tic Tac Toe Game Tests")
@Feature("Basic Game Functionality")
public class BasicGameTest extends BaseTest {

    @Test
    public void testPlayGame() {
        try {
            playGame();
        } catch (Exception e) {
            ScreenshotUtils.captureAndAttach(driver, "Play Game Error");
            throw e;
        }
    }

    private void playGame() {
        int movesCount = 0;
        while (movesCount < 9) {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (gamePage.isCellAvailable(i, j)) {
                        gamePage.makeMove(i, j);
                        movesCount++;
                        if (gamePage.checkWinner()) return;
                        break;
                    }
                }
            }
        }
    }
}