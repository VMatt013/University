package model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.tinylog.Logger;
import puzzle.TwoPhaseMoveState;

/**
 * The selector that selects the fields to move from and to
 */
public class Selector {
    /**
     * The model of the game
     */
    private final GameModel model;

    /** The possible phases of the selector */
    public enum Phase {
        SELECT_FROM,
        SELECT_TO,
        READY_TO_MOVE

    }

    /** The read only property of the phase of the selector */
    private final ReadOnlyObjectWrapper<Phase> phase;

    /** The position of the field to move from */
    private Position from;

    /** The stack pane of the field to move from */
    private StackPane fromItem;

    /** The position of the field to move to */
    private Position to;

    /** The stack pane of the field to move to */
    private StackPane toItem;

    /**
     * Creates a new selector for the given model
     * @param model the model of the game
     */
    public Selector(GameModel model) {
        this.model = model;
        phase = new ReadOnlyObjectWrapper<>(Phase.SELECT_FROM);
    }

    /** returns the read only property of the phase */
    public ReadOnlyObjectProperty<Phase> phaseProperty() {
        return phase.getReadOnlyProperty();
    }

    /** returns if the game is ready to move */
    public boolean isReadyToMove() {
        return phase.get() == Phase.READY_TO_MOVE;
    }

    /** selects the field to move from or to */
    public void select(StackPane field) {
        switch (phase.get()) {
            case SELECT_FROM -> selectFrom(field);
            case SELECT_TO -> selectTo(field);
            case READY_TO_MOVE -> throw new IllegalStateException();
        }
    }

    /** selects the field to move from */
    private void selectFrom(StackPane field) {
        var position = new Position(GridPane.getRowIndex(field),GridPane.getColumnIndex(field));
        if (model.isLegalToMoveFrom(position)) {
            from = position;
            fromItem = field;
            phase.set(Phase.SELECT_TO);
            Logger.info("Selected ({},{})",GridPane.getRowIndex(field), GridPane.getColumnIndex(field) );
        } else {
            Logger.error("Invalid Selection");
        }
    }

    /** selects the field to move to */
    private void selectTo(StackPane field) {
        var position = new Position(GridPane.getRowIndex(field),GridPane.getColumnIndex(field));
        if (model.isLegalMove(new TwoPhaseMoveState.TwoPhaseMove<>(from, position))) {
            to = position;
            toItem = field;
            phase.set(Phase.READY_TO_MOVE);
            Logger.info("Selected ({},{})",GridPane.getRowIndex(field), GridPane.getColumnIndex(field) );
        } else {
            reset();
            Logger.error("Invalid Selection");
        }
    }

    /** returns the stack pane of the field to move from */
    public StackPane getFrom() {
        if (phase.get() == Phase.SELECT_FROM) {
            throw new IllegalStateException();
        }
        return fromItem;
    }

    /** makes the move */
    public void makeMove() {
        if (phase.get() != Phase.READY_TO_MOVE) {
            throw new IllegalStateException();
        }
        Logger.info("Move ({},{}) -> ({},{})",from.row(),from.col(),to.row(),to.col());
        model.makeMove(new TwoPhaseMoveState.TwoPhaseMove<>(from, to));
        reset();
    }

    /** resets the selector */
    public void reset() {
        fromItem.getChildren().getFirst().getStyleClass().remove("selected");
        from = null;
        to = null;
        phase.set(Phase.SELECT_FROM);
    }

}