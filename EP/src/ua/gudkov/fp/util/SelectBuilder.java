package ua.gudkov.fp.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Sql select statement builder implementation. Contains methods to create most
 * popular keywords, condition operators. Consist of several containers to save
 * logical piece of the statements which should be converted to String.
 * 
 * @author A.Gudkov
 *
 */
public class SelectBuilder {
	private List<String> columns = new ArrayList<String>();
	private List<String> tables = new ArrayList<String>();
	private List<String> joins = new ArrayList<String>();
	private List<String> leftJoins = new ArrayList<String>();
	private List<String> wheres = new ArrayList<String>();
	private List<String> orderBys = new ArrayList<String>();
	private List<String> groupBys = new ArrayList<String>();
	private List<String> havings = new ArrayList<String>();
	private List<String> limit = new ArrayList<String>(1);

	public SelectBuilder() {

	}

	public SelectBuilder(String table) {
		tables.add(table);
	}

	/**
	 * Adds values of 'limit' operator
	 * 
	 * @param limit
	 *            limit
	 * @return select builder
	 */
	public SelectBuilder limit(String limit) {
		this.limit.add(limit);
		return this;
	}

	/**
	 * Adds column in the statement
	 * 
	 * @param name
	 *            column name
	 * @return select builder
	 */
	public SelectBuilder column(String name) {
		columns.add(name);
		return this;
	}

	/**
	 * Adds column in the statement with 'group by' attribute
	 * 
	 * @param name
	 *            column name
	 * @param groupBy
	 *            is group by the column name
	 * @return select builder
	 */
	public SelectBuilder column(String name, boolean groupBy) {
		columns.add(name);
		if (groupBy) {
			groupBys.add(name);
		}
		return this;
	}

	/**
	 * Adds attributes in the 'from'
	 * 
	 * @param table
	 *            table name
	 * @return select builder
	 */
	public SelectBuilder from(String table) {
		tables.add(table);
		return this;
	}

	/**
	 * Adds attributes in the 'group by'
	 * 
	 * @param expr
	 *            name of column
	 * @return select builder
	 */
	public SelectBuilder groupBy(String expr) {
		groupBys.add(expr);
		return this;
	}

	/**
	 * Adds attributes in the 'having'
	 * 
	 * @param expr
	 *            expression
	 * @return select builder
	 */
	public SelectBuilder having(String expr) {
		havings.add(expr);
		return this;
	}

	/**
	 * Adds attributes in the 'where'
	 * 
	 * @param expr
	 *            expression
	 * @return select builder
	 */
	public SelectBuilder where(String expr) {
		wheres.add(expr);
		return this;
	}

	/**
	 * Adds attributes in the 'join'
	 * 
	 * @param expr
	 *            expression
	 * @return select builder
	 */
	public SelectBuilder join(String join) {
		joins.add(join);
		return this;
	}

	/**
	 * Adds attributes in the 'left join'
	 * 
	 * @param expr
	 *            expression
	 * @return select builder
	 */
	public SelectBuilder leftJoin(String join) {
		leftJoins.add(join);
		return this;
	}

	/**
	 * Adds attributes in the 'order by'
	 * 
	 * @param name
	 *            column name
	 * @param def
	 *            sorting method
	 * @return select builder
	 */
	public SelectBuilder orderBy(String name, boolean def) {
		if (!def) {
			orderBys.add(name + " DESC");
			return this;
		} else {
			return orderBy(name);
		}

	}

	/**
	 * Adds attributes in the 'order by'
	 * 
	 * @param name
	 *            column name
	 * @return
	 */
	public SelectBuilder orderBy(String name) {
		orderBys.add(name);
		return this;
	}

	@Override
	public String toString() {

		StringBuilder sql = new StringBuilder("select ");

		if (columns.size() == 0) {
			sql.append("*");
		} else {
			appendList(sql, columns, "", ", ");
		}

		appendList(sql, tables, " from ", ", ");
		appendList(sql, joins, " join ", " join ");
		appendList(sql, leftJoins, " left join ", " left join ");
		appendList(sql, wheres, " where ", " and ");
		appendList(sql, groupBys, " group by ", ", ");
		appendList(sql, havings, " having ", " and ");
		appendList(sql, orderBys, " order by ", ", ");
		appendList(sql, limit, " limit ", ", ");

		return sql.toString();
	}

	/**
	 * Extracts attributes from containers and append them to string builder.
	 * 
	 * @param sql
	 *            string builder
	 * @param list
	 *            list of attributes
	 * @param key
	 *            operator
	 * @param sep
	 *            separator
	 */
	private void appendList(StringBuilder sql, List<String> list, String key, String sep) {
		boolean first = true;
		for (String s : list) {
			if (first) {
				sql.append(key);
			} else {
				sql.append(sep);
			}
			sql.append(s);
			first = false;
		}
	}

	/**
	 * Returns 'On' operator with given attributes
	 * 
	 * @param table
	 *            table name
	 * @param column1
	 *            column
	 * @param column2
	 *            column
	 * @return 'On operator'
	 */
	public static String On(String table, String column1, String column2) {
		return new StringBuilder().append(table).append(" ON ").append(column1).append("=").append(column2).toString();
	}

	/**
	 * Returns equals condition with given attribute and value
	 * 
	 * @param column
	 *            column
	 * @param val
	 *            value
	 * @return 'eq' condition
	 */
	public static String Eq(String column, String val) {
		return new StringBuilder().append(column).append("=").append("'").append(val).append("'").toString();
	}

	/**
	 * Returns 'between' condition
	 * 
	 * @param column
	 *            column name
	 * @param valFrom
	 *            value 'from'
	 * @param valTo
	 *            value 'to'
	 * @return 'between' condition
	 */
	public static String Between(String column, String valFrom, String valTo) {
		return new StringBuilder().append("(").append(column).append(" BETWEEN ").append(valFrom).append(" AND ")
				.append(valTo).append(")").toString();

	}

	/**
	 * Returns 'less' condition
	 * 
	 * @param column
	 *            column name
	 * @param valTo
	 *            value 'to'
	 * @return 'less' condition
	 */
	public static String Less(String column, String valTo) {
		return new StringBuilder().append(column).append("<").append(valTo).toString();

	}

	/**
	 * Returns 'greater' condition
	 * 
	 * @param column
	 *            column name
	 * @param valFrom
	 *            value 'from'
	 * @return 'greater' condition
	 */
	public static String Greater(String column, String valFrom) {
		return new StringBuilder().append(column).append(">").append(valFrom).toString();
	}

	/**
	 * Returns count function
	 * 
	 * @param columnName
	 *            column name
	 * @return count function
	 */
	public static String CountFunc(String columnName) {
		return new StringBuilder().append("COUNT(*)").append(" ").append(columnName).toString();
	}

}
