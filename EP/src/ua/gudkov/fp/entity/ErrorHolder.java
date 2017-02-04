package ua.gudkov.fp.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Holder for errors.
 * 
 * @author A.Gudkov
 *
 */
public class ErrorHolder implements Serializable {
	private static final long serialVersionUID = -8823202627739869620L;
	private Map<String, String> errors = new HashMap<>();

	public void add(String errorField, String errorMsg) {
		errors.put(errorField, errorMsg);
	}

	public void add(String key) {
		errors.put(key, key);
	}

	public void addAll(ErrorHolder errorHolder) {
		errors.putAll(errorHolder.getErrors());
	}

	public boolean isEmpty() {
		return errors.isEmpty();
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void clear() {
		errors.clear();
	}

}
