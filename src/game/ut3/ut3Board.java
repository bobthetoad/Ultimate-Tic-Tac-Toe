/**
 * GameBoard.java
 */
package game.ut3;

import game.board.GameBoard;
import game.board.Region;
import game.piece.Piece;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The <code>ut3Board</code> class is specifically designed to handle games of Ultimate Tic Tac Toe, specifically
 * handling what type of <code>Marker</code> object can be placed and in what <code>Region</code> it can be placed in.
 *
 * @author javedmohamed
 * @version 0.2
 * @see game.board.GameBoard
 * @see info.gridworld.world.World
 * @since 0.2
 */
public class ut3Board extends GameBoard {

	private static final Dimension DIMENSION = new Dimension(574, 693);
	/**
	 * The <code>NWREG</code> static field is used to call the Northwestern region of this <code>GameBoard</code>.
	 */
	private static final Region NWREG = new Region(new Location(0, 0), new Location(2, 2));
	/**
	 * The <code>NREG</code> static field is used to call the Northern region of this <code>GameBoard</code>.
	 */
	private static final Region NREG = new Region(new Location(0, 4), new Location(2, 6));
	/**
	 * The <code>NEREG</code> static field is used to call the Northeastern region of this <code>GameBoard</code>.
	 */
	private static final Region NEREG = new Region(new Location(0, 8), new Location(2, 10));
	/**
	 * The <code>WREG</code> static field is used to call the Western region of this <code>GameBoard</code>.
	 */
	private static final Region WREG = new Region(new Location(4, 0), new Location(6, 2));
	/**
	 * The <code>CREG</code> static field is used to call the Central region of this <code>GameBoard</code>.
	 */
	private static final Region CREG = new Region(new Location(4, 4), new Location(6, 6));
	/**
	 * The <code>EREG</code> static field is used to call the Eastern region of this <code>GameBoard</code>.
	 */
	private static final Region EREG = new Region(new Location(4, 8), new Location(6, 10));
	/**
	 * The <code>SWREG</code> static field is used to call the Southwestern region of this <code>GameBoard</code>.
	 */
	private static final Region SWREG = new Region(new Location(8, 0), new Location(10, 2));
	/**
	 * The <code>SREG</code> static field is used to call the Southern region of this <code>GameBoard</code>.
	 */
	private static final Region SREG = new Region(new Location(8, 4), new Location(10, 6));
	/**
	 * The <code>SEREG</code> static field is used to call the Southeastern region of this <code>GameBoard</code>.
	 */
	private static final Region SEREG = new Region(new Location(8, 8), new Location(10, 10));

	private static final Region FULL_BOARD = new Region(new Location(0,0),new Location(10,10));

	/**
	 * The <code>isXTurn</code> field determines whether the next <code>Marker</code> object placed in the grid is
	 * of 'X' type or 'O' type.
	 */
	private boolean isXTurn;
	/**
	 * The <code>currentPlayRegion</code> field stores the next playable region, where playable signifies that a
	 * <code>Marker</code> object can be within that region.
	 */
	private Region currentPlayRegion;

	private WinRegion[][] winRegions;

	/**
	 * Creates a new <code>ut3Board</code> as to standard guidelines.
	 */
	public ut3Board() {
		this(11, 11, true, CREG);
		System.out.print(getFrame());
	}

	/**
	 * Creates a new <code>ut3Board</code> object with a grid with the given dimensions, sets
	 * whether the starting player is 'X' or 'O', and what the starting region will be.
	 *
	 * @param r                 is the number of rows for the grid used to store the <code>Piece</code> objects.
	 * @param c                 is the number of columns for the grid used to store the <code>Piece</code> objects.
	 * @param isXTurn           determines whether the 'X' team will go first.
	 * @param currentPlayRegion determines the starting region for this game.
	 */
	private ut3Board(int r, int c, boolean isXTurn, Region currentPlayRegion) {
		this(new BoundedGrid<Piece>(r, c), isXTurn, currentPlayRegion);
	}

	/**
	 * Creates a new <code>ut3Board</code> object with the given <code>BoundedGrid&lt;Piece&gt;</code>, sets
	 * whether the starting player is 'X' or 'O', and what the starting region will be.
	 *
	 * @param gr                is the is the grid used for storing all of the <code>Piece</code> objects.
	 * @param isXTurn           determines whether the 'X' team will go first.
	 * @param currentPlayRegion determines the starting region for this game.
	 */
	private ut3Board(BoundedGrid<Piece> gr, boolean isXTurn, Region currentPlayRegion) {
		super(gr);
		this.isXTurn = isXTurn;
		this.currentPlayRegion = currentPlayRegion;
		winRegions = new WinRegion[3][3];
	}

	/**
	 * Indicates whether it is the 'X' player's turn or not.
	 *
	 * @return true id it is X's turn and false if it is O's turn.
	 */
	public boolean isXTurn() {
		return isXTurn;
	}

	/**
	 * Handles a click on the Board by placing the appropriate <code>Marker</code> object in the grid.
	 * @param loc is the location clicked.
	 * @return true so that no dropdown menu pops up.
	 */
	@Override
	public boolean locationClicked(Location loc) {
		if (getGrid().get(loc) == null) {
			if (currentPlayRegion.contains(loc)) {
				add(loc, new Marker(isXTurn));
				markWinLocations();
				swapTurn();
				switch (currentPlayRegion.getQuadrant(loc)) {
					case -2:
						setMessage(loc + " is a bad location. " + (isXTurn ? "X" : "O") + ", try Again in the following region: " + currentPlayRegion + ".");
						return true;
					case -1:
						setCurrentPlayRegion(CREG);
						break;
					case Location.NORTH:
						setCurrentPlayRegion(NREG);
						break;
					case Location.SOUTH:
						setCurrentPlayRegion(SREG);
						break;
					case Location.EAST:
						setCurrentPlayRegion(EREG);
						break;
					case Location.WEST:
						setCurrentPlayRegion(WREG);
						break;
					case Location.NORTHEAST:
						setCurrentPlayRegion(NEREG);
						break;
					case Location.SOUTHEAST:
						setCurrentPlayRegion(SEREG);
						break;
					case Location.NORTHWEST:
						setCurrentPlayRegion(NWREG);
						break;
					case Location.SOUTHWEST:
						setCurrentPlayRegion(SWREG);
						break;
				}
				if (isFull(currentPlayRegion)) {
					setCurrentPlayRegion(FULL_BOARD);
				}
				setMessage("It is " + (isXTurn ? "X" : "O") + "'s turn in region: " + displayCurrentRegion() + ".");
			} else {
				setMessage(loc + " is a bad location. " + (isXTurn ? "X" : "O") +
						", try again in the following region: " + displayCurrentRegion() + ".");
			}
		}

		return true;

	}

	private void setXTurn(boolean isXTurn) {
		this.isXTurn = isXTurn;
	}

	/**
	 * Inverses the state of the <code>isXTurn</code> field.
	 */
	private void swapTurn() {
		isXTurn = !isXTurn;
	}

	/**
	 * Returns the playable region.
	 * @return the playable region.
	 */
	public Region getCurrentPlayRegion() {
		return currentPlayRegion;
	}

	/**
	 * Sets the current playable region to the given region.
	 * @param currentPlayRegion is the new playable region.
	 * @throws IllegalStateException if the region passed is not of the nine possible regions.
	 */
	public void setCurrentPlayRegion(Region currentPlayRegion) throws IllegalStateException {
		if (currentPlayRegion.equals(NWREG) ||
				currentPlayRegion.equals(NREG) ||
				currentPlayRegion.equals(NEREG) ||
				currentPlayRegion.equals(WREG) ||
				currentPlayRegion.equals(CREG) ||
				currentPlayRegion.equals(EREG) ||
				currentPlayRegion.equals(SWREG) ||
				currentPlayRegion.equals(SREG) ||
				currentPlayRegion.equals(SEREG) ||
				currentPlayRegion.equals((FULL_BOARD))) {
			this.currentPlayRegion = currentPlayRegion;
		} else {
			throw new IllegalStateException("Is not valid region");
		}

	}

	/**
	 * Displays this object, and draws <code>Border</code> objects to form the board.
	 */
	@Override
	public void show() {
		for (int r = 3; r < getGrid().getNumRows(); r += 4) {
			for (int c = 0; c < getGrid().getNumCols(); c++) {
				add(new Location(r, c), new Border());
			}
		}
		for (int c = 3; c < getGrid().getNumRows(); c += 4) {
			for (int r = 0; r < getGrid().getNumCols(); r++) {
				add(new Location(r, c), new Border());
			}
		}
		super.show();
	}

	/**
	 * Resets the state of this object to its original starting state.
	 */
	@Override
	public void reset() {
		getFrame().setSize(DIMENSION);
		setGrid(new BoundedGrid<Piece>(11, 11));
		setCurrentPlayRegion(CREG);
		setXTurn(true);
	}

	public String displayCurrentRegion() {
		if (currentPlayRegion.equals(NWREG)) {
			return "Northwest";
		} else if (currentPlayRegion.equals(NREG)) {
			return "North";
		} else if (currentPlayRegion.equals(NEREG)) {
			return "Northeast";
		} else if (currentPlayRegion.equals(WREG)) {
			return "West";
		} else if (currentPlayRegion.equals(EREG)) {
			return "East";
		} else if (currentPlayRegion.equals(SEREG)) {
			return "Southeast";
		} else if (currentPlayRegion.equals(SWREG)) {
			return "Southwest";
		} else if (currentPlayRegion.equals(SREG)) {
			return "South";
		} else if (currentPlayRegion.equals(CREG)) {
			return "Center";
		} else if (currentPlayRegion.equals(FULL_BOARD)) {
			return "Anywhere";
		} else {
			return "";
		}
	}

	public boolean isFull(Region region) {
		ArrayList<Location> locations = region.getLocations();
		for (Location location:locations) {
			if (getGrid().get(location)==null) {
				return false;
			}
		}
		return true;
	}

	private ArrayList<Location> getWinLocations() {
		return BoardChecker.getWinLocations((BoundedGrid)getGrid(), getCurrentPlayRegion(), isXTurn);
//		return null;
	}

	public void markWinLocations() {
		ArrayList<Location> locations = getWinLocations();
		if (locations == null) {
			return;
		} else {
			Location location = getRegionLocation(currentPlayRegion);
			if (winRegions[location.getRow()][location.getCol()] == null) {
				for (Location location1 : locations) {
					((Marker) getGrid().get(location1)).setWinner(true);
				}
				winRegions[location.getRow()][location.getCol()] = new WinRegion(currentPlayRegion, isXTurn);
				int winner = BoardChecker.getWinner(winRegions, isXTurn);
				if (winner != -1) {
					handleWin(winner == 1);
				}
			}
		}
	}

	/**
	 * Stores a winning region and who it belongs to.
	 */
	public class WinRegion {
		Region region;
		boolean isX;

		public WinRegion(Region region, boolean isX) {
			this.region = region;
			this.isX = isX;
		}

		public Region getRegion() {
			return region;
		}

		private void setRegion(Region region) {
			this.region = region;
		}

		public boolean isX() {
			return isX;
		}

		private void setX(boolean isX) {
			this.isX = isX;
		}

		@Override
		public String toString() {
			return "WinRegion{" +
					"region=" + region +
					", isX=" + isX +
					'}';
		}
	}

	public Location getRegionLocation(Region region) {
		if (currentPlayRegion.equals(NWREG)) {
			return new Location(0, 0);
		} else if (currentPlayRegion.equals(NREG)) {
			return new Location(0, 1);
		} else if (currentPlayRegion.equals(NEREG)) {
			return new Location(0, 2);
		} else if (currentPlayRegion.equals(WREG)) {
			return new Location(1, 0);
		} else if (currentPlayRegion.equals(EREG)) {
			return new Location(1, 2);
		} else if (currentPlayRegion.equals(SEREG)) {
			return new Location(2, 2);
		} else if (currentPlayRegion.equals(SWREG)) {
			return new Location(2, 0);
		} else if (currentPlayRegion.equals(SREG)) {
			return new Location(2, 1);
		} else if (currentPlayRegion.equals(CREG)) {
			return new Location(1, 1);
		} else if (currentPlayRegion.equals(FULL_BOARD)) {
			return new Location(-1, -1);
		} else {
			return new Location(-2, -2);
		}
	}

	private void handleWin(boolean isX) {
		setMessage("Player " + (isX ? "X" : "O") + " has won!");
		JOptionPane.showMessageDialog(new Frame(), "Congratulations! Player " + (isX?"X":"O") + " has won!\n" +
				"Thanks for Player and please give an 'A' or this program may or may not reformat you hard drive. ;)",
				"Ultimate-Tic-Tac-Toe", JOptionPane.OK_OPTION);
		close();
	}

	public void resize() {
		getFrame().setSize(DIMENSION);
	}
}