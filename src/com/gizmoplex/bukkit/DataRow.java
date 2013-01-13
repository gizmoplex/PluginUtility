package com.gizmoplex.bukkit;

public class DataRow implements Comparable<DataRow>
{


  private int _numColumns;
  private Object[] _values;
  private DataTable _dataTable;

  /***
   * Constructor for the DataRow class.
   * 
   * @param dataTable
   *          The DataTable to which the DataRow belongs.
   * @param numColumns
   *          The number of columns in the DataRow.
   */
  public DataRow(DataTable dataTable, int numColumns)
  {

    // Save the parent DataTable
    _dataTable = dataTable;

    // Save the number of columns in the data row
    _numColumns = numColumns;

    // Create array to store values
    _values = new Object[_numColumns];

  }


  /***
   * Constructor for the DataRow class that also sets values.
   * 
   * @param dataTable
   *          The DataTable to which the DataRow belongs.
   * @param numColumns
   *          The number of columns in the DataRow.
   * @param values
   *          An array of values to be assigned to the DataRow.
   */
  public DataRow(DataTable dataTable, int numColumns, Object[] values)
  {
    this(dataTable, numColumns);

    for (int i = 0; i < _values.length && i < values.length; i++)
      _values[i] = values[i];

  }


  /***
   * Returns the value of the specified column.
   * 
   * @param column
   *          The index of the column.
   * @return The value of the specified column.
   */
  public Object getValue(int columnIndex)
  {
    // If column is invalid, throw an exception
    if (columnIndex < 0 || columnIndex >= _numColumns)
      return (null);

    return (_values[columnIndex]);

  }


  /***
   * Returns the value of the specified column based on the column name.
   * 
   * @param columnName
   *          The name of the column.
   * @return
   */
  public Object getValue(String columnName)
  {
    int columnIndex = _dataTable.getColumnIndex(columnName);

    return (getValue(columnIndex));
  }


  /***
   * Sets the value of the specified column.
   * 
   * @param column
   *          The index of the column.
   * @param value
   *          The value to be set.
   */
  public void setValue(int columnIndex, Object value)
  {

    // If column is invalid, throw an exception
    if (columnIndex < 0 || columnIndex >= _numColumns)
      return;

    _values[columnIndex] = value;

  }


  /***
   * Sets the value of the specified column based on the column name.
   * 
   * @param columnName
   *          The name of the column.
   * @param value
   *          The value to be set.
   */
  public void setValue(String columnName, Object value)
  {
    int columnIndex = _dataTable.getColumnIndex(columnName);

    setValue(columnIndex, value);
  }


  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public int compareTo(DataRow dataRow)
  {
    Object value = _values[_dataTable.getSortColumnIndex()];
    Object compareValue = dataRow.getValue(_dataTable.getSortColumnIndex());
    int retVal = 0;

    if (value instanceof Comparable
        && value.getClass().isInstance(compareValue))
    {
      if (value instanceof String)
        retVal = ((String) value).compareToIgnoreCase((String) compareValue);
      else
        retVal = ((Comparable) value).compareTo(compareValue);
    }

    // If descending order, flip comparison result
    if (!_dataTable.getSortAscending())
      retVal = -retVal;

    return (retVal);
  }

}