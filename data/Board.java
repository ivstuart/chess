/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package data;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Board {

	private Piece[][] grid;
	private LinkedList pieces;
	private Piece whiteKing;
	private Piece blackKing;

	/**
	 * 
	 */
	public Board() {
		super();

		pieces = new LinkedList();
		grid = new Piece[8][8];

		this.reset();

		// TODO Auto-generated constructor stub
	}

	public void addPiece(Piece piece) {
		grid[piece.getX()][piece.getY()] = piece;
		pieces.add(piece);
	}

	public boolean anyKnightAttacking(boolean isWhite, Piece defender) {
		Iterator i = pieces.iterator();
		while (i.hasNext()) {
			Object piece = i.next();
			if (piece instanceof Knight) {
				Knight horsey = (Knight) piece;
				if (isWhite != horsey.isWhite()) {
					if (isKnightAttacking(horsey, defender)) {
						System.out
								.println("Opponent knight is attacking the king!");
						return true;
					}
				}
			}
		}
		return false;
	}

	private void createPawnRow(int row, boolean isWhite) {
		for (int col = 0; col < 8; col++) {
			grid[col][row] = new Pawn(new Location(col, row), isWhite);
		}
	}

	private void createRow(int row, boolean isWhite) {
		grid[0][row] = new Rook(new Location(0, row), isWhite);
		grid[1][row] = new Knight(new Location(1, row), isWhite);
		grid[2][row] = new Bishop(new Location(2, row), isWhite);
		grid[3][row] = new Queen(new Location(3, row), isWhite);
		grid[4][row] = new King(new Location(4, row), isWhite);
		grid[5][row] = new Bishop(new Location(5, row), isWhite);
		grid[6][row] = new Knight(new Location(6, row), isWhite);
		grid[7][row] = new Rook(new Location(7, row), isWhite);

		if (isWhite) {
			whiteKing = grid[4][row];
		} else {
			blackKing = grid[4][row];
		}

	}

	public Piece getKing(boolean isWhite) {
		if (isWhite) {
			return whiteKing;
		} else {
			return blackKing;
		}

	}

	/**
	 * @param i
	 * @param j
	 */
	public Piece getPiece(int i, int j) {
		if (i < 0 || i > 7 || j < 0 || j > 7) {
			return null;
		}
		return grid[i][j];

	}

	/**
	 * @param from
	 */
	public Piece getPiece(Location from) {
		// TODO Auto-generated method stub
		return grid[from.getXPos()][from.getYPos()];
	}

	/**
	 * @return
	 */
	public LinkedList getPieces() {
		return pieces;
	}

	public boolean isDiagonalAttacker(int x, int y, boolean isWhite) {
		if (grid[x][y] == null) {
			return false;
		}
		if (grid[x][y].isWhite() == isWhite) {
			return false;
		}
		if (grid[x][y] instanceof Queen || grid[x][y] instanceof Bishop) {
			System.out.println("Dia Check from x=" + x);
			System.out.println("Dia Check from y=" + y);
			return true;
		}
		return false;
	}

	public boolean isHorizontalAttacker(int x, int y, boolean isWhite) {
		if (grid[x][y] == null) {
			return false;
		}
		if (grid[x][y].isWhite() == isWhite) {
			return false;
		}
		if (grid[x][y] instanceof Queen || grid[x][y] instanceof Rook) {
			System.out.println("Check from x=" + x);
			System.out.println("Check from y=" + y);
			return true;
		}
		return false;
	}

	private boolean isKnightAttacking(Knight aKnight, Piece aPiece) {
		int x1 = aKnight.getX();
		int y1 = aKnight.getY();
		int x2 = aPiece.getX();
		int y2 = aPiece.getY();

		if (Math.abs(x1 - x2) + Math.abs(y1 - y2) == 3) {
			if (Math.abs(x1 - x2) != 0 || Math.abs(x1 - x2) != 3) {
				return true;
			}
		}
		return false;
	}

	public boolean isPiece(int i, int j) {

		return (null != grid[i][j]);
	}

	public void remove(Location loc) {
		// pieces.remove(grid[loc.getXPos()][loc.getYPos()]);
		grid[loc.getXPos()][loc.getYPos()] = null;
	}

	/**
	 * @param pawn
	 */
	public void remove(Piece piece) {
		// TODO Auto-generated method stub
		grid[piece.getX()][piece.getY()] = null;
		pieces.remove(piece);
	}

	/**
	 * 
	 */
	public void reset() {
		// TODO Auto-generated method stub
		pieces.clear();

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				grid[row][col] = null;
			}
		}

		createRow(0, false);
		createPawnRow(1, false);

		createPawnRow(6, true);
		createRow(7, true);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {

				if (grid[row][col] != null) {
					pieces.add(grid[row][col]);
				}
			}
		}
	}

	/**
	 * @param newPiece
	 */
	public void setPiece(Piece piece) {
		// TODO Auto-generated method stub
		grid[piece.getX()][piece.getY()] = piece;

	}

	/**
	 * @param piece
	 * @param to
	 */
	public void setPiece(Piece piece, Location to) {
		// TODO Auto-generated method stub

		Piece toCapture = getPiece(to);

		if (toCapture != null) {
			pieces.remove(toCapture);
		}

		grid[to.getXPos()][to.getYPos()] = piece;
		grid[piece.getX()][piece.getY()] = null;
		piece.setLocation(to);
	}

}
