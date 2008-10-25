//$Id$
package org.jrecruiter.hibernate;

import java.io.Serializable;
import java.util.Locale;

import org.hibernate.AssertionFailure;
import org.hibernate.cfg.DefaultNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.util.StringHelper;
import org.jvnet.inflector.Noun;

/**
 * An improved naming strategy that prefers embedded
 * underscores to mixed case names
 * @see DefaultNamingStrategy the default strategy
 *
 * @author Gavin King
 * @author Gunnar Hillert
 */
public class ImprovedPluralizedNamingStrategy implements NamingStrategy, Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 0L;

	/**
	 * A convenient singleton instance
	 */
	public static final NamingStrategy INSTANCE = new ImprovedPluralizedNamingStrategy();

	/**
	 * Return the unqualified class name, mixed case converted to
	 * underscores
	 */
	public String classToTableName(String className) {
		return addUnderscores( StringHelper.unqualify(className), true );
	}
	/**
	 * Return the full property path with underscore seperators, mixed
	 * case converted to underscores
	 */
	public String propertyToColumnName(String propertyName) {
		return addUnderscores( StringHelper.unqualify(propertyName) );
	}
	/**
	 * Convert mixed case to underscores
	 */
	public String tableName(String tableName) {
		return addUnderscores(tableName);
	}
	/**
	 * Convert mixed case to underscores
	 */
	public String columnName(String columnName) {
		return addUnderscores(columnName);
	}

	protected static String addUnderscores(String name) {
		return addUnderscores(name, false);
	}

	protected static String addUnderscores(String name, boolean pluralize) {
		StringBuffer buf = new StringBuffer( name.replace('.', '_') );
		for (int i=1; i<buf.length()-1; i++) {
			if (
				Character.isLowerCase( buf.charAt(i-1) ) &&
				Character.isUpperCase( buf.charAt(i) ) &&
				Character.isLowerCase( buf.charAt(i+1) )
			) {
				buf.insert(i++, '_');
			}
		}

		if (pluralize) {
			String[] splitTableNameFragments = buf.toString().toLowerCase().split("_");

			StringBuffer buf2 = new StringBuffer();

			for (int i=0; i<splitTableNameFragments.length; i++) {

				if (i<(splitTableNameFragments.length-1)) {
					buf2.append(splitTableNameFragments[i]);
					buf2.append("_");
				} else {
					buf2.append(
							Noun.pluralOf(
									splitTableNameFragments[i], Locale.ENGLISH));
				}
			}

			return buf2.toString();
		}

		return buf.toString();
	}

	public String collectionTableName(
			String ownerEntity, String ownerEntityTable, String associatedEntity, String associatedEntityTable,
			String propertyName
	) {
		return tableName( ownerEntityTable + '_' + propertyToColumnName(propertyName) );
	}

	/**
	 * Return the argument
	 */
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		return columnName( joinedColumn );
	}

	/**
	 * Return the property name or propertyTableName
	 */
	public String foreignKeyColumnName(
			String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName
	) {
		String header = propertyName != null ? StringHelper.unqualify( propertyName ) : propertyTableName;
		if (header == null) throw new AssertionFailure("NamingStrategy not properly filled");
		return columnName( header ); //+ "_" + referencedColumnName not used for backward compatibility
	}

	/**
	 * Return the column name or the unqualified property name
	 */
	public String logicalColumnName(String columnName, String propertyName) {
		return StringHelper.isNotEmpty( columnName ) ? columnName : StringHelper.unqualify( propertyName );
	}

	/**
	 * Returns either the table name if explicit or
	 * if there is an associated table, the concatenation of owner entity table and associated table
	 * otherwise the concatenation of owner entity table and the unqualified property name
	 */
	public String logicalCollectionTableName(String tableName,
											 String ownerEntityTable, String associatedEntityTable, String propertyName
	) {
		if ( tableName != null ) {
			return tableName;
		}
		else {
			//use of a stringbuffer to workaround a JDK bug
			return new StringBuffer(ownerEntityTable).append("_")
					.append(
						associatedEntityTable != null ?
						associatedEntityTable :
						StringHelper.unqualify( propertyName )
					).toString();
		}
	}
	/**
	 * Return the column name if explicit or the concatenation of the property name and the referenced column
	 */
	public String logicalCollectionColumnName(String columnName, String propertyName, String referencedColumn) {
		return StringHelper.isNotEmpty( columnName ) ?
				columnName :
				StringHelper.unqualify( propertyName ) + "_" + referencedColumn;
	}
}
