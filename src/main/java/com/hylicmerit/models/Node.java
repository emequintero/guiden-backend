package com.hylicmerit.models;

public class Node implements Comparable<Node>{
	private Integer column;
	private Integer row;
	private Integer distance = Integer.MAX_VALUE;
	private Boolean isFinish = false;
	private Boolean isStart = false;
	private Boolean isWall = false;
	private Boolean isVisited = false;
	private Node previousNode = null;

	public Node(Integer row, Integer column) {
		this.row = row;
		this.column = column;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Node getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}

	public Boolean isFinish() {
		return isFinish;
	}

	public void setFinish(Boolean isFinish) {
		this.isFinish = isFinish;
	}

	public Boolean isStart() {
		return isStart;
	}

	public void setStart(Boolean isStart) {
		this.isStart = isStart;
	}

	public Boolean isWall() {
		return isWall;
	}

	public void setWall(Boolean isWall) {
		this.isWall = isWall;
	}

	public Boolean isVisited() {
		return isVisited;
	}

	public void setVisited(Boolean isVisited) {
		this.isVisited = isVisited;
	}

	@Override
	public int hashCode() {
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + column;
		result = prime * result + (isFinish ? 1231 : 1237);
		result = prime * result + (isStart ? 1231 : 1237);
		result = prime * result + (isWall ? 1231 : 1237);
		result = prime * result + row;
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
		Node other = (Node) obj;
		if (column != other.column)
			return false;
		if (isFinish != other.isFinish)
			return false;
		if (isStart != other.isStart)
			return false;
		if (isWall != other.isWall)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [column=" + column + ", row=" + row + ", distance=" + distance + ", isFinish=" + isFinish
				+ ", isStart=" + isStart + ", isWall=" + isWall + ", isVisited=" + isVisited + ", previousNode="
				+ previousNode + "]";
	}

	@Override
	public int compareTo(Node next) {
		return this.getDistance().compareTo(next.getDistance());
	}

}
