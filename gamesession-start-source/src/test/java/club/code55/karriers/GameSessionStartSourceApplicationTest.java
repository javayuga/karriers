package club.code55.karriers;

import club.code55.karriers.domain.v1.GameSession;
import org.junit.Test;

import java.util.function.Supplier;

import static org.junit.Assert.assertNotNull;

public class GameSessionStartSourceApplicationTest {

  @Test
  public void supplyLoan() {
    GameSessionStartSourceApplication app = new GameSessionStartSourceApplication();
    Supplier<GameSession> gameSessionSupplier = app.supplyGameSession();
    assertNotNull(gameSessionSupplier);
    assertNotNull(gameSessionSupplier.get());
    assertNotNull(gameSessionSupplier.get().getUuid());
    assertNotNull(gameSessionSupplier.get().getCurrentTurn());
  }
}