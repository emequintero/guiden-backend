package com.hylicmerit.models;

import java.util.List;

public class Animation {
	private String state;
	private List<Integer> values;

	public Animation(String state, List<Integer> values) {
		super();
		this.state = state;
		this.values = values;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		Animation other = (Animation) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animation [state=" + state + ", values=" + values + "]";
	}

}
