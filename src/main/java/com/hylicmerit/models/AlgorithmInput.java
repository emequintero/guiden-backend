package com.hylicmerit.models;

import java.util.Arrays;

public class AlgorithmInput {
	private int rows;
	private int columns;
	private int[] start;
	private int[] end;
	private int[][] walls;

	public AlgorithmInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlgorithmInput(int rows, int columns, int[] start, int[] end, int[][] walls) {
		super();
		this.rows = rows;
		this.columns = columns;
		this.start = start;
		this.end = end;
		this.walls = walls;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int[] getStart() {
		return start;
	}

	public void setStart(int[] start) {
		this.start = start;
	}

	public int[] getEnd() {
		return end;
	}

	public void setEnd(int[] end) {
		this.end = end;
	}

	public int[][] getWalls() {
		return walls;
	}

	public void setWalls(int[][] walls) {
		this.walls = walls;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columns;
		result = prime * result + Arrays.hashCode(end);
		result = prime * result + rows;
		result = prime * result + Arrays.hashCode(start);
		result = prime * result + Arrays.deepHashCode(walls);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlgorithmInput other = (AlgorithmInput) obj;
		if (columns != other.columns)
			return false;
		if (!Arrays.equals(end, other.end))
			return false;
		if (rows != other.rows)
			return false;
		if (!Arrays.equals(start, other.start))
			return false;
		if (!Arrays.deepEquals(walls, other.walls))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlgorithmInput [rows=" + rows + ", columns=" + columns + ", start=" + Arrays.toString(start) + ", end="
				+ Arrays.toString(end) + ", walls=" + Arrays.toString(walls) + "]";
	}

}
