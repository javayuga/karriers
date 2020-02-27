package club.code55.karriers.domain.v1;

import java.util.Objects;

public class GameSession {
    private String uuid;
    private Integer currentTurn;
    private Integer maxTurns;

    public GameSession() {

    }

    public GameSession(String uuid, Integer currentTurn, Integer maxTurns) {
        this.uuid = uuid;
        this.currentTurn = currentTurn;
        this.maxTurns = maxTurns;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getMaxTurns() {
        return maxTurns;
    }

    public void setMaxTurns(Integer maxTurns) {
        this.maxTurns = maxTurns;
    }

    public Integer getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Integer currentTurn) {
        this.currentTurn = currentTurn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameSession)) return false;
        GameSession that = (GameSession) o;
        return Objects.equals(getUuid(), that.getUuid()) &&
                Objects.equals(getCurrentTurn(), that.getCurrentTurn()) &&
                Objects.equals(getMaxTurns(), that.getMaxTurns());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getCurrentTurn(), getMaxTurns());
    }

    @Override
    public String toString() {
        return "GameSession{" +
                "uuid='" + uuid + '\'' +
                ", currentTurn=" + currentTurn +
                ", maxTurns=" + maxTurns +
                '}';
    }
}
