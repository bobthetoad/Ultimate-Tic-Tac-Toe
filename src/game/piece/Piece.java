package game.piece;

/**
 * Created by javedmohamed on 5/18/14.
 */
public abstract class Piece {
	public boolean isX() {
		throw new IllegalStateException("this method must be implemented by my subclass");
	}

}
