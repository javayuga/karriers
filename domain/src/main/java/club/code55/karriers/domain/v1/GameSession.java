package club.code55.karriers.domain.v1;

import java.util.Objects;

public class GameSession {
    private String uuid;
    private Integer currentTurn;

    public GameSession(){

    }

    public GameSession(String uuid, Integer currentTurn) {
        this.uuid = uuid;
        this.currentTurn = currentTurn;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
                Objects.equals(getCurrentTurn(), that.getCurrentTurn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getCurrentTurn());
    }

    @Override
    public String toString() {
        return "GameSession{" +
                "uuid='" + uuid + '\'' +
                ", currentTurn=" + currentTurn +
                '}';
    }
}
