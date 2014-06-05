package game.ut3;

import game.piece.Piece;

import java.awt.*;

public class Marker extends Piece {
	private boolean isX;
	private boolean isWinner;

	private Marker(boolean isX, boolean isWinner) {
		this.isX = isX;
		this.isWinner = isWinner;
	}

	public Marker(boolean isX) {
		this(isX,false);
	}

	@Override
	public boolean isX() {
		return isX;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Marker marker = (Marker) o;

		if (isWinner != marker.isWinner) return false;
		if (isX != marker.isX) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (isX ? 1 : 0);
		result = 31 * result + (isWinner ? 1 : 0);
		return result;
	}

	public String getText() {
		return isX ? "X" : "O";
	}

	public Color getColor() {
		return isWinner?Color.RED:null;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}


}
