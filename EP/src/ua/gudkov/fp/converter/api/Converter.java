package ua.gudkov.fp.converter.api;

/**
 * Converter interface.
 * 
 * @author A.Gudkov
 *
 */
public interface Converter<T, E> {

	/**
	 * Converts one object to another, typically form bean to entity.
	 *
	 * @param t
	 *            the given object
	 * @return the converted object
	 */
	E convert(T t);
}
