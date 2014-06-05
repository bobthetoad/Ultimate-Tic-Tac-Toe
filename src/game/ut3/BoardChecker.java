package game.ut3;

import game.board.Region;
import game.piece.Piece;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class BoardChecker {
	// chailuvs code
	public static ArrayList<Location> getWinLocations(BoundedGrid<Piece> grid,
			Region region, boolean isX) {

		if (getHorizontalLocations(grid, region, isX) != null) {

			return getHorizontalLocations(grid, region, isX);

		}

		if (getVerticalLocations(grid, region, isX) != null) {

			return getVerticalLocations(grid, region, isX);

		}

		if ((getDiagonalLocations1(grid, region, isX)) != null) {

			return getDiagonalLocations1(grid, region, isX);

		}

		if ((getDiagonalLocations2(grid, region, isX)) != null) {

			return getDiagonalLocations2(grid, region, isX);

		}

		return null;

	}

	private static ArrayList<Location> getHorizontalLocations(
			BoundedGrid<Piece> boundedGrid, Region region, boolean isX) {

		if (boundedGrid == null
				|| boundedGrid.getOccupiedLocations().size() == 0) {

			return null;

		}

		ArrayList<Location> longestChain = new ArrayList<Location>();

		Location loc1 = new Location(region.getLoc1().getRow(), region
				.getLoc1().getCol());
		Location loc2 = new Location(region.getLoc1().getRow(), region
				.getLoc1().getCol());
		Location loc3 = new Location(region.getLoc1().getRow(), region
				.getLoc1().getCol());
		for (int y = 0; y < 3; y++)

		{

			loc1 = new Location(region.getLoc1().getRow() + y, region.getLoc1()
					.getCol());
			loc2 = new Location(region.getLoc1().getRow() + y, region.getLoc1()
					.getCol() + 1);
			loc3 = new Location(region.getLoc1().getRow() + y, region.getLoc1()
					.getCol() + 2);
			longestChain.add(loc1);
			longestChain.add(loc2);
			longestChain.add(loc3);
			if (boundedGrid.get(loc1) instanceof Piece
					&& boundedGrid.isValid(loc1)
					&& boundedGrid.get(loc1).isX() == isX
					&& boundedGrid.get(loc2) instanceof Piece
					&& boundedGrid.isValid(loc1)
					&& boundedGrid.get(loc1).isX() == isX
					&& boundedGrid.get(loc3) instanceof Piece
					&& boundedGrid.isValid(loc1)
					&& boundedGrid.get(loc1).isX() == isX) {
				break;
			} else {
				longestChain.clear();
			}
		}
		if (longestChain.size() > 0) {
			return longestChain;
		}
		return null;
	}

	private static ArrayList<Location> getVerticalLocations(
			BoundedGrid<Piece> boundedGrid, Region region, boolean isX) {

		if (boundedGrid == null
				|| boundedGrid.getOccupiedLocations().size() == 0) {

			return null;

		}

		ArrayList<Location> longestChain = new ArrayList<Location>();

		Location loc1 = new Location(region.getLoc1().getRow(), region
				.getLoc1().getCol());
		Location loc2 = new Location(region.getLoc1().getRow(), region
				.getLoc1().getCol());
		Location loc3 = new Location(region.getLoc1().getRow(), region
				.getLoc1().getCol());
		for (int y = 0; y < 3; y++)

		{

			loc1 = new Location(region.getLoc1().getRow(), region.getLoc1()
					.getCol() + y);
			loc2 = new Location(region.getLoc1().getRow() + 1, region.getLoc1()
					.getCol() + y);
			loc3 = new Location(region.getLoc1().getRow() + 2, region.getLoc1()
					.getCol() + y);
			longestChain.add(loc1);
			longestChain.add(loc2);
			longestChain.add(loc3);
			if (boundedGrid.get(loc1) instanceof Piece
					&& boundedGrid.isValid(loc1)
					&& boundedGrid.get(loc1).isX() == isX
					&& boundedGrid.get(loc2) instanceof Piece
					&& boundedGrid.isValid(loc1)
					&& boundedGrid.get(loc1).isX() == isX
					&& boundedGrid.get(loc3) instanceof Piece
					&& boundedGrid.isValid(loc1)
					&& boundedGrid.get(loc1).isX() == isX) {
				break;
			} else {
				longestChain.clear();
			}
		}
		if (longestChain.size() > 0) {
			return longestChain;
		}
		return null;
	}

	private static ArrayList<Location> getDiagonalLocations1(
			BoundedGrid<Piece> boundedGrid, Region region, boolean isX) {

		if (boundedGrid == null
				|| boundedGrid.getOccupiedLocations().size() == 0) {

			return null;

		}

		ArrayList<Location> longestChain = new ArrayList<Location>();

		Location loc1 = region.getLoc1();

		Location loc2 = new Location(loc1.getRow() + 1, loc1.getCol() + 1);

		Location loc3 = new Location(loc1.getRow() + 2, loc1.getCol() + 2);

		if (

		boundedGrid.get(loc1) != null && boundedGrid.isValid(loc1) && (boundedGrid.get(loc1).isX() == isX)

		&& boundedGrid.get(loc2) != null && boundedGrid.isValid(loc2) && (boundedGrid.get(loc2).isX() == isX)

		&& boundedGrid.get(loc3) != null && boundedGrid.isValid(loc3) && (boundedGrid.get(loc3).isX() == isX)

		) {

			longestChain.add(loc1);

			longestChain.add(loc2);

			longestChain.add(loc3);

			return longestChain;

		}

		return null;

	}

	private static ArrayList<Location> getDiagonalLocations2(
			BoundedGrid<Piece> boundedGrid, Region region, boolean isX) {

		if (boundedGrid == null
				|| boundedGrid.getOccupiedLocations().size() == 0) {

			return null;

		}

		ArrayList<Location> longestChain = new ArrayList<Location>();

		Location loc1 = region.getLoc1();

		Location loc2 = new Location(loc1.getRow() + 2, loc1.getCol());

		Location loc3 = new Location(loc1.getRow() + 1, loc1.getCol() + 1);

		Location loc4 = new Location(loc1.getRow(), loc1.getCol() + 2);

		if (

		boundedGrid.get(loc2) instanceof Piece && boundedGrid.isValid(loc2)
				&& (boundedGrid.get(loc2).isX() == isX)

				&& boundedGrid.get(loc3) instanceof Piece
				&& boundedGrid.isValid(loc3)
				&& (boundedGrid.get(loc3).isX() == isX)

				&& boundedGrid.get(loc4) instanceof Piece
				&& boundedGrid.isValid(loc4)
				&& (boundedGrid.get(loc4).isX() == isX)

		) {

			longestChain.add(loc2);

			longestChain.add(loc3);

			longestChain.add(loc4);

			return longestChain;

		}

		return null;

	}

	// javs code
	public static int getWinner(ut3Board.WinRegion[][] winRegions, boolean isX) {
		int winner;
		winner = getHorizontalWinner(winRegions, isX);
		if (winner != -1) {
			return winner;
		}
		winner = getVerticalWinner(winRegions, isX);
		if (winner != -1) {
			return winner;
		}
		winner = getDiagonalWinner(winRegions, isX);
		if (winner != -1) {
			return winner;
		}
		return -1;
	}

	private static int getHorizontalWinner(ut3Board.WinRegion[][] winRegions,
			boolean isX) {
		for (int row = 0; row < 3; row++) {
			ArrayList<Location> locations = new ArrayList<Location>();
			for (int col = 0; col < 3; col++) {
				locations.add(new Location(row, col));
			}
			boolean isNull = false;
			for (Location location : locations) {
				if (winRegions[location.getRow()][location.getCol()] == null) {
					isNull = true;
				}
			}
			if (isNull) {
				continue;
			}
			boolean isWin = true;
			for (Location location : locations) {
				if (winRegions[location.getRow()][location.getCol()].isX() != isX) {
					isWin = false;
				}
			}
			if (isWin) {
				return isX ? 1 : 0;
			}
		}
		return -1;
	}

	private static int getVerticalWinner(ut3Board.WinRegion[][] winRegions,
			boolean isX) {
		for (int col = 0; col < 3; col++) {
			ArrayList<Location> locations = new ArrayList<Location>();
			for (int row = 0; row < 3; row++) {
				locations.add(new Location(row, col));
			}
			boolean isNull = false;
			for (Location location : locations) {
				if (winRegions[location.getRow()][location.getCol()] == null) {
					isNull = true;
				}
			}
			if (isNull) {
				continue;
			}
			boolean isWin = true;
			for (Location location : locations) {
				if (winRegions[location.getRow()][location.getCol()].isX() != isX) {
					isWin = false;
				}
			}
			if (isWin) {
				return isX ? 1 : 0;
			}
		}
		return -1;
	}

	private static int getDiagonalWinner(ut3Board.WinRegion[][] winRegions,
			boolean isX) {
		ArrayList<Location> locations = new ArrayList<Location>();
		for (int n = 0; n < 3; n++) {
			locations.add(new Location(n, n));
		}
		boolean isNull = false;
		for (Location location : locations) {
			if (winRegions[location.getRow()][location.getCol()] == null) {
				isNull = true;
			}
		}
		if (!isNull) {
			boolean isWin = true;
			for (Location location : locations) {
				if (winRegions[location.getRow()][location.getCol()].isX() != isX) {
					isWin = false;
				}
			}
			if (isWin) {
				return isX ? 1 : 0;
			}
		}
		locations.clear();
		for (int n = 0; n < 3; n++) {
			locations.add(new Location(n, 2 - n));
		}
		isNull = false;
		for (Location location : locations) {
			if (winRegions[location.getRow()][location.getCol()] == null) {
				isNull = true;
			}
		}
		if (!isNull) {
			boolean isWin = true;
			for (Location location : locations) {
				if (winRegions[location.getRow()][location.getCol()].isX() != isX) {
					isWin = false;
				}
			}
			if (isWin) {
				return isX ? 1 : 0;
			}
		}
		return -1;
	}
}
