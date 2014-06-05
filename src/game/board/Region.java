package game.board;

import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * Created by javedmohamed on 5/11/14.
 */
public class Region {
	private Location loc1;
	private Location loc2;

	// common 'error' message
	private final String errmsg = "loc1 is not less than loc2";

	/**
	 * Precondition: loc1 is less than loc2
	 *
	 * @param loc1 is the NW <code>Location</code> of the <code>Region</code>
	 * @param loc2 is the SE <code>Location</code> of the <code>Region</code>
	 */
	public Region(Location loc1, Location loc2) throws IllegalStateException {
		if (loc1.compareTo(loc2) <= 0) {
			this.loc1 = loc1;
			this.loc2 = loc2;
		} else {
			throw new IllegalStateException(errmsg);
		}
	}

	public Location getLoc1() {
		return loc1;
	}

	public void setLoc1(Location loc1) throws IllegalStateException {
		if (loc2.compareTo(loc1) >= 0) {
			this.loc1 = loc1;
		} else {
			throw new IllegalStateException(errmsg);
		}

	}

	public Location getLoc2() {
		return loc2;
	}

	public void setLoc2(Location loc2) throws IllegalStateException {
		if (loc1.compareTo(loc2) <= 0) {
			this.loc2 = loc2;
		} else {
			throw new IllegalStateException(errmsg);
		}
	}

	public ArrayList<Location> getLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		for (int r = 0; r <= loc2.getRow() - loc1.getRow(); r++) {
			for (int c = 0; c <= loc2.getCol() - loc1.getCol(); c++) {
				locs.add(new Location(r + loc1.getRow(), c + loc1.getCol()));
			}
		}
		return locs;
	}

	public boolean contains(Location loc) {
		return loc.getRow() >= loc1.getRow() &&
				loc.getRow() <= loc2.getRow() &&
				loc.getCol() >= loc1.getCol() &&
				loc.getCol() <= loc2.getCol();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Region region = (Region) o;

		if (!loc1.equals(region.loc1)) return false;
		if (!loc2.equals(region.loc2)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = loc1.hashCode();
		result = 31 * result + loc2.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Region{" +
				"loc1=" + loc1 +
				", loc2=" + loc2 +
				'}';
	}

	/**
	 * Returns the Quadrant from the center
	 *
	 * @param loc is the <code>Location</code> whose quadrant will be found
	 *
	 * @return the quadrant of the given <code>Location</code> in this <code>Region</code>, <code>"CENTER"</code> if the
	 * center itself, or <code>"NOT CONTAINED"</code> if not contained in this <code>Region</code>.
	 */
	public int getQuadrant(Location loc) {
		Location center = new Location((loc1.getRow() + loc2.getRow()) / 2, (loc1.getCol() + loc2.getCol()) / 2);
		if (!contains(loc)) {
			return -2;
		} else if (loc.equals(center)) {
			return -1;
		}
		int dir = center.getDirectionToward(loc);
		if (dir == Location.NORTH) {
			return Location.NORTH;
		} else if (dir == Location.NORTHEAST) {
			return Location.NORTHEAST;
		} else if (dir == Location.EAST) {
			return Location.EAST;
		} else if (dir == Location.SOUTHEAST) {
			return Location.SOUTHEAST;
		} else if (dir == Location.SOUTH) {
			return Location.SOUTH;
		} else if (dir == Location.SOUTHWEST) {
			return Location.SOUTHWEST;
		} else if (dir == Location.WEST) {
			return Location.WEST;
		} else if (dir == Location.NORTHWEST) {
			return Location.NORTHWEST;
		} else {
			System.err.println("Some thing has gone horribly wrong!");
			return -2;
		}

	}

	public int getRows() {
		return loc2.getRow() - loc1.getRow() + 1;
	}

	public int getCols() {
		return loc2.getCol() - loc1.getCol() + 1;
	}
}
