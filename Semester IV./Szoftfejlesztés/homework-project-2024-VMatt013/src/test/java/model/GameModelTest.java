package model;

import org.junit.jupiter.api.Test;
import puzzle.TwoPhaseMoveState;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the game model
 */
class GameModelTest {
    GameModel gameModel = new GameModel(Map.Test);

    Position none = new Position(0, 0); // NONE
    Position empty = new Position(0, 3); // EMPTY
    Position full1 = new Position(1, 3); // FULL
    Position full2 = new Position(0, 5); // FULL
    Position full3 = new Position(1, 8); // FULL
    Position full4 = new Position(1, 9); // FULL


    TwoPhaseMoveState.TwoPhaseMove<Position> up = new TwoPhaseMoveState.TwoPhaseMove<>(full1,new Position(0,3));
    TwoPhaseMoveState.TwoPhaseMove<Position> down = new TwoPhaseMoveState.TwoPhaseMove<>(full2,new Position(1,5));
    TwoPhaseMoveState.TwoPhaseMove<Position> left = new TwoPhaseMoveState.TwoPhaseMove<>(full1, new Position(1,2));
    TwoPhaseMoveState.TwoPhaseMove<Position> right = new TwoPhaseMoveState.TwoPhaseMove<>(full1, new Position(1,4));

    /**
     * Tests the field property of the game model
     */
    @Test
    void fieldProperty() {
        assertEquals(GameModel.State.NONE, gameModel.fieldProperty(none.row(), none.col()).get());
        assertEquals(GameModel.State.EMPTY, gameModel.fieldProperty(empty.row(), empty.col()).get());
        assertEquals(GameModel.State.FULL, gameModel.fieldProperty(full1.row(), full1.col()).get());
    }

    /**
     * Tests if the move is legal
     */
    @Test
    void isLegalMove() {
        assertTrue(gameModel.isLegalMove(new TwoPhaseMoveState.TwoPhaseMove<>(full1, empty)));

        assertFalse(gameModel.isLegalMove(new TwoPhaseMoveState.TwoPhaseMove<>(empty, none))); //isLegalToMoveFrom
        assertFalse(gameModel.isLegalMove(new TwoPhaseMoveState.TwoPhaseMove<>(none, empty))); //isLegalToMoveFrom
        assertFalse(gameModel.isLegalMove(new TwoPhaseMoveState.TwoPhaseMove<>(full1, full2))); //isLegalToMoveTo
        assertFalse(gameModel.isLegalMove(new TwoPhaseMoveState.TwoPhaseMove<>(full2, empty))); //isNextMove
    }

    /**
     * Tests if the move's to position is next to the move's from position
     */
    @Test
    void isNext() {
        assertTrue(gameModel.isNext(up));
        assertTrue(gameModel.isNext(down));
        assertTrue(gameModel.isNext(left));
        assertTrue(gameModel.isNext(right));

        assertFalse(gameModel.isNext(new TwoPhaseMoveState.TwoPhaseMove<>(full1, full2)));
    }

    /**
     * Tests if the move is legal to move from the given position
     */
    @Test
    void isLegalToMoveFrom() {
        assertFalse(gameModel.isLegalToMoveFrom(none));
        assertFalse(gameModel.isLegalToMoveFrom(empty));

        assertTrue(gameModel.isLegalToMoveFrom(full1));
    }

    /**
     * Tests if the move is legal to move to the given position
     */
    @Test
    void isLegalToMoveTo() {
        assertFalse(gameModel.isLegalToMoveTo(none));
        assertFalse(gameModel.isLegalToMoveTo(full1));

        assertTrue(gameModel.isLegalToMoveTo(empty));
    }

    /**
     * Tests if the game is solved
     */
    @Test
    void isSolved() {
        assertFalse(gameModel.isSolved());
        assertFalse(new GameModel(Map.Easy).isSolved());
        assertTrue(new GameModel(Map.Solved).isSolved());
    }

    /**
     * Tests if the game model has the given legal moves
     */
    @Test
    void getLegalMoves() {
        assertEquals(gameModel.getLegalMoves(), new GameModel(Map.Test).getLegalMoves());
        assertNotEquals(gameModel.getLegalMoves(), new GameModel(Map.Easy).getLegalMoves());
    }

    /**
     * Tests if the game model and the clone equal but not the same
     */
    @Test
    void testClone() {
        var clone = gameModel.clone();
        assertEquals(clone, gameModel);
        assertNotSame(clone, gameModel);
    }

    /**
     * Tests if the game model can be converted to a string
     */
    @Test
    void testToString() {
        assertEquals("""
                GameModel[numberOfMoves=0, isGameOver=BooleanProperty [value: false]]
                board=
                { x x x 0 x 1 x 0 x x  }
                { 0 0 0 1 0 0 0 0 1 1  }
                """,new GameModel(Map.Test).toString());
    }

    /**
     * Tests if the game model is equal to itself
     */
    @Test
    void testEquals() {
        assertTrue(gameModel.equals(gameModel));
        assertFalse(gameModel.equals(new GameModel(Map.Easy)));
    }

    /**
     * Tests if the game model has the same hash code as itself and its clone
     */
    @Test
    void testHashCode() {
        assertTrue(new GameModel(Map.Test).hashCode() == new GameModel(Map.Test).hashCode());
        assertTrue(new GameModel(Map.Test).hashCode() == gameModel.clone().hashCode());
    }

    /**
     * Tests the number of moves made in the game
     */
    @Test
    void getNumberOfMoves() {
        assertTrue(gameModel.getNumberOfMoves() == 0);

        gameModel.makeMove(up);
        assertTrue(gameModel.getNumberOfMoves() == 1);
        gameModel.makeMove(down);
        assertTrue(gameModel.getNumberOfMoves() == 2);

        assertFalse(gameModel.getNumberOfMoves() == 1);

    }
}