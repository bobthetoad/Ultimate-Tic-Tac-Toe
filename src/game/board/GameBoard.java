/**
 * GameBoard.java
 */

package game.board;

import game.piece.Piece;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

/**
 * The <code>GameBoard</code> abstract class handles the storage of <code>Piece</code> objects.
 *
 * @author javedmohamed
 * @version 0.2
 * @see info.gridworld.world.World
 * @since 0.1
 */
public abstract class GameBoard extends World<Piece> {
	/**
	 * Creates a new <code>GameBoard</code> object with the supplied <code>BoundedGrid&lt;Piece&gt;</code>.
	 *
	 * @param gr is the grid supplied to store the <code>Piece</code> objects.
	 */
	public GameBoard(BoundedGrid<Piece> gr) {
		super(gr);
	}

	/**
	 * Handles the displaying of this <code>GameBoard</code>.
	 */
	@Override
	public void show() {
		super.show();
		setMessage("This is a GameBoard.");
	}

	/**
	 * Handles the clicks on this <code>GameBoard</code> object.
	 * @param loc is the location clicked.
	 * @return <code>true</code> always as to not open the standard dropdown menu.
	 */
	@Override
	public boolean locationClicked(Location loc) {
		return true;
	}

	/**
	 * The <code>reset</code> method resets this <code>GameBoard</code> object's state to its starting state.
	 */
	public abstract void reset();

	public void close() {
		getFrame().dispose();
	}
}
