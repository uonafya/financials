package org.openmrs.module.financials;

/**
 * Describes a column in a CohortIndicatorDataSetDefinition
 */
public class ColumnParameters {
	
	private String name;
	
	private String label;
	
	private String dimensions;
	
	private String column;
	
	/**
	 * Default constructor
	 * 
	 * @param name the name
	 * @param label the label
	 * @param dimensions the dimension parameters
	 */
	public ColumnParameters(String name, String label, String dimensions, String column) {
		this.name = name;
		this.label = label;
		this.dimensions = dimensions;
		this.column = column;
	}
	
	/**
	 * Gets the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the label
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Gets the dimension parameters
	 * 
	 * @return the dimension parameters
	 */
	public String getDimensions() {
		return dimensions;
	}
	
	/**
	 * Gets the column
	 * 
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}
}
