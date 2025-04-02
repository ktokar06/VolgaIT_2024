package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import utils.ScreenshotUtils;

@Epic("Tic Tac Toe Game Tests")
@Feature("Draw Game Scenarios")
public class GameDrawTest extends BaseTest {

    @Test
    public void testGameDraw() {
        try {
            simulateDraw();
        } catch (Exception e) {
            ScreenshotUtils.captureAndAttach(driver, "Game Draw Error");
            throw e;
        }
    }

    private void simulateDraw() {
        // X moves
        gamePage.makeMove(1, 1);
        gamePage.makeMove(1, 3);
        gamePage.makeMove(2, 2);
        gamePage.makeMove(3, 1);
        gamePage.makeMove(3, 3);

        // O moves
        gamePage.makeMove(1, 2);
        gamePage.makeMove(2, 1);
        gamePage.makeMove(2, 3);
        gamePage.makeMove(3, 2);
    }
}