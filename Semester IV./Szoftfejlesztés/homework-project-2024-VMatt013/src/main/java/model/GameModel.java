package model;

import javafx.beans.property.*;
import puzzle.TwoPhaseMoveState;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The game model that stores the state of the game
 */
public class GameModel implements TwoPhaseMoveState<Position> {

    /**
     * The possible states of a fields
     */
    public enum State {
        NONE,
        EMPTY,
        FULL
    }
    /**
     * The number of rows in the game board
     */
    public final int ROWS = Map.rows;
    /**
     * The number of columns in the game board
     */
    public final int COLUMNS = Map.columns;
    /**
     * The game board
     */
    public String[][] MAP = Map.Default;
    /**
     * The number of moves made in the game
     */
    private Integer numberOfMoves;
    /**
     * The start time of the game
     */
    public LocalDateTime startTime = LocalDateTime.now();
    /**
     * Represents the game if the game is over
     */
    private ReadOnlyBooleanWrapper isGameOver;
    /**
     * The number of moves made in the game
     */
    private final ReadOnlyStringWrapper numberOfMovesText;
    /**
     * The game board that stores the state of the fields
     */
    private ReadOnlyObjectWrapper<State>[][] board = new ReadOnlyObjectWrapper[ROWS][COLUMNS];
    /**
     * The game board that stores the value of the fields
     */
    public ReadOnlyObjectWrapper<String>[][] valueBoard = new ReadOnlyObjectWrapper[ROWS][COLUMNS];

    /**
     * Creates a new game model
     */
    public GameModel() {
        for (var i = 0; i < ROWS; i++) {
            for (var j = 0; j < COLUMNS; j++) {
                switch (MAP[i][j]){
                    case "x":
                        valueBoard[i][j] = new ReadOnlyObjectWrapper<>("x");
                        board[i][j] = new ReadOnlyObjectWrapper<>(State.NONE);
                        break;
                    case "0":
                        valueBoard[i][j] = new ReadOnlyObjectWrapper<>("0");
                        board[i][j] = new ReadOnlyObjectWrapper<>(State.EMPTY);
                        break;
                    default:
                        valueBoard[i][j] = new ReadOnlyObjectWrapper(MAP[i][j]);
                        board[i][j] = new ReadOnlyObjectWrapper<>(State.FULL);
                        break;
                }
            }
        }
        isGameOver = new ReadOnlyBooleanWrapper();
        numberOfMoves = 0;
        numberOfMovesText = new ReadOnlyStringWrapper(String.format("moves: %d",numberOfMoves));
    }
    /**
     * Creates a new game model with the given map
     * @param map the map to use
     */
    public GameModel(String[][] map) {
        MAP = map;
        for (var i = 0; i < ROWS; i++) {
            for (var j = 0; j < COLUMNS; j++) {
                switch (MAP[i][j]){
                    case "x":
                        valueBoard[i][j] = new ReadOnlyObjectWrapper<>("x");
                        board[i][j] = new ReadOnlyObjectWrapper<>(State.NONE);
                        break;
                    case "0":
                        valueBoard[i][j] = new ReadOnlyObjectWrapper<>("0");
                        board[i][j] = new ReadOnlyObjectWrapper<>(State.EMPTY);
                        break;
                    default:
                        valueBoard[i][j] = new ReadOnlyObjectWrapper(MAP[i][j]);
                        board[i][j] = new ReadOnlyObjectWrapper<>(State.FULL);
                        break;
                }
            }
        }
        isGameOver = new ReadOnlyBooleanWrapper();
        numberOfMoves = 0;
        numberOfMovesText = new ReadOnlyStringWrapper(String.format("moves: %d",numberOfMoves));
    }

    /**
     * Sets the state of the field at the given position
     * @param p the position to set the state of
     * @param field the state to set the field to
     */
    private void setField(Position p, State field) {
        board[p.row()][p.col()].set(field);
    }
    /**
     * Returns the read only property of the field at the given position
     * @param row the row of the field
     * @param col the column of the field
     * @return the read only property of the field at the given position
     */
    public ReadOnlyObjectProperty<State> fieldProperty(int row, int col) {
        return board[row][col].getReadOnlyProperty();
    }

    /**
     * Retruns if the given move is legal
     * @param move the move to check
     * @return  if the given move is legal
     */
    @Override
    public boolean isLegalMove(TwoPhaseMove<Position> move) {
        return isLegalToMoveFrom(move.from()) & isLegalToMoveTo(move.to()) & isNext(move);
    }

    /**
     * Checks if the given move's from and to positions are next to each other
     * @param move the move to check
     * @return if the given move's from and to positions are next to each other
     */
    public boolean isNext(TwoPhaseMove<Position> move){
        var dx = Math.abs(move.to().row() - move.from().row());
        var dy = Math.abs(move.to().col() - move.from().col());
        return dx + dy == 1;
    }

    /**
     * Checks if the given move is legal to move from the given position
     * @param from the position to check
     * @return if the given move is legal to move from the given position
     */
    @Override
    public boolean isLegalToMoveFrom(Position from) {
        return !(fieldProperty(from.row(), from.col()).get() == State.NONE | fieldProperty(from.row(), from.col()).get() == State.EMPTY);
    }

    /**
     * Checks if the given move is legal to move to the given position
     * @param to the position to check
     * @return if the given move is legal to move to the given position
     */
    public boolean isLegalToMoveTo(Position to) {
        return fieldProperty(to.row(), to.col()).get() == State.EMPTY;
    }

    /**
     * Checks if the game is solved
     * @return if the game is solved
     */
    @Override
    public boolean isSolved() {
        for (Integer i = 1; i < 10; i++){
            if (!Objects.equals(valueBoard[1][i-1].get(), i.toString())){
                return false;
            }
        }
        return true;
    }

    /**
     * Makes the given move and updates the number of moves made
     * @param move the move to make
     */
    @Override
    public void makeMove(TwoPhaseMove<Position> move) {
        changeValueBoard(move);
            setField(move.to(),fieldProperty(move.from().row(), move.from().col()).get());
            setField(move.from(),State.EMPTY);
        numberOfMoves++;
        numberOfMovesText.set(String.format("moves: %d",numberOfMoves));
        if (isSolved()){
            endGame();
        }
    }
    /**
     * Ends the game
     */
    public void endGame(){
        isGameOver.set(true);
    }

    /**
     * Changes the value board of the game
     * @param move the move to change the value board of the game
     */
    private void changeValueBoard(TwoPhaseMove<Position> move){
        int fromX = move.from().row();
        int fromY = move.from().col();

        int toX = move.to().row();
        int toY = move.to().col();

        valueBoard[toX][toY].set(valueBoard[fromX][fromY].get());
        valueBoard[fromX][fromY].set("0");

    }

    /**
     *Returns the set of legal moves from the current state of the game
     * @return the set of legal moves from the current state of the game
     */
    @Override
    public Set<TwoPhaseMove<Position>> getLegalMoves() {
        Set<TwoPhaseMove<Position>> legalMoves = new HashSet<>();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Position from = new Position(row, col);
                if (isLegalToMoveFrom(from)) {
                    Position[] possibleMoves;

                        possibleMoves = new Position[]{
                                new Position(row, col - 1), // Left
                                new Position(row, col + 1), // Right
                                new Position(row + 1, col),  // Down
                                new Position(row - 1, col)  // Up
                        };

                    for (Position to : possibleMoves) {
                        if (to.row() >= 0 && to.row() < ROWS && to.col() >= 0 && to.col() < COLUMNS) {
                            if (isLegalMove(new TwoPhaseMove<>(from, to))) {
                                legalMoves.add(new TwoPhaseMove<>(from, to));
                            }
                        }
                    }
                }
            }
        }

        return legalMoves;
    }


    /**
     * Clones the game model at the current state
     * @return the cloned game model at the current state
     */
    @Override
    public TwoPhaseMoveState<Position> clone() {
        try {
            GameModel copy = (GameModel) super.clone();
            copy.numberOfMoves = this.numberOfMoves;
            copy.isGameOver.set(this.isGameOver.get());
            copy.board = new ReadOnlyObjectWrapper[ROWS][COLUMNS];
            for (var i = 0; i < ROWS; i++) {
                for (var j = 0; j < COLUMNS; j++) {
                    copy.board[i][j] = new ReadOnlyObjectWrapper<>(this.board[i][j].get());
                }
            }
            copy.valueBoard = new ReadOnlyObjectWrapper[ROWS][COLUMNS];
            for (var i = 0; i < ROWS; i++) {
                for (var j = 0; j < COLUMNS; j++) {
                    copy.valueBoard[i][j] = new ReadOnlyObjectWrapper<>(this.valueBoard[i][j].get());
                }
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Returns the string representation of the game model at the current state
     * @return the string representation of the game model at the current state
     */
    @Override
    public String toString() {
        String text =
                  "GameModel["
                + "numberOfMoves="
                + numberOfMoves
                + ", isGameOver="
                + isGameOver
                + "]\nboard=\n";

        for (ReadOnlyObjectWrapper<String>[] i : valueBoard){
            text += "{ ";
            for (ReadOnlyObjectWrapper<String> j : i){
                text += String.format("%s ",j.get());
            }
            text += " }\n";
        }
        return text;
    }

    /**
     * Checks if the given object is equal to the current object
     * @param o the object to check
     * @return if the given object is equal to the current object
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GameModel that)) return false;
        if (!Objects.equals(numberOfMoves, that.numberOfMoves)) return false;
        if (isGameOver.get() != that.isGameOver.get()) return false;
        for (var i = 0; i < ROWS; i++) {
            for (var j = 0; j < COLUMNS; j++) {
                if (board[i][j].get() != that.board[i][j].get()) return false;
            }
        }
        for (var i = 0; i < ROWS; i++) {
            for (var j = 0; j < COLUMNS; j++) {
                if (!Objects.equals(valueBoard[i][j].get(), that.valueBoard[i][j].get())) return false;
            }
        }
        return true;
    }
    /**
     * Returns the hash code of the game model at the current state
     * @return the hash code of the game model at the current state
     */
    @Override
    public int hashCode() {
        var result = Objects.hash(numberOfMoves,isGameOver.get());
        for (var i = 0; i < ROWS; i++) {
            for (var j = 0; j < COLUMNS; j++) {
                result = 31 * result + board[i][j].get().hashCode();
            }
            for (var j = 0; j < COLUMNS; j++) {
                result = 31 * result + valueBoard[i][j].get().hashCode();
            }
        }
        return result;
    }
    /**
     * Returns the read only string property of the number of moves made in the game
     * @return the read only string property of the number of moves made in the game
     */
    public ReadOnlyStringProperty numberOfMovesProperty(){
        return numberOfMovesText;
    }
    /**
     * Returns the read only boolean property of if the game is over
     * @return the read only boolean property of if the game is over
     */
    public ReadOnlyBooleanProperty gameOverProperty(){
        return isGameOver.getReadOnlyProperty();
    }
    /**
     * Returns the number of moves made in the game
     * @return the number of moves made in the game
     */
    public Integer getNumberOfMoves(){
        return numberOfMoves;
    }
}
