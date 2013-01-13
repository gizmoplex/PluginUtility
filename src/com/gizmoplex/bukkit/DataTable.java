package com.gizmoplex.bukkit;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;


public class DataTable extends AbstractTableModel
{


  private static final long serialVersionUID = 1L;

  // Private data members
  private DataColumn[] _columns;
  private HashMap<String, Integer> _columnMap;
  private ArrayList<DataRow> _rows;
  private int _sortColumnIndex = 0;
  private boolean _sortAscending = true;
  private boolean _eventsEnabled = true;
  private boolean _allowEditing = true;


  /***
   * Constructor for the DataTable class.
   * 
   * @param columns
   *          Array of DataColumn objects.
   */
  public DataTable(DataColumn[] columns)
  {

    // Create hash map for column name/index
    _columnMap = new HashMap<String, Integer>();

    // Create new array for columns
    _columns = new DataColumn[columns.length];

    // Load columns into new array and hash map
    for (int i = 0; i < columns.length; i++)
    {
      _columns[i] = columns[i].clone();
      _columnMap.put(_columns[i].getName(), i);
    }

    // Create array list for data rows
    _rows = new ArrayList<DataRow>();

  }


  /***
   * Adds a new row to the data table.
   * 
   * @return The new data row is returned.
   */
  public DataRow addRow()
  {
    int rowIndex;
    DataRow newRow = new DataRow(this, _columns.length);

    _rows.add(newRow);

    // Get the index of the new row (the last one)
    rowIndex = _rows.size() - 1;
    
    // Fire event for row being added
    this.fireTableRowsInserted(rowIndex, rowIndex);

    return (newRow);
  }


  /***
   * Adds a new row to the data table and sets the initial values.
   * 
   * @param values
   *          The initial values.
   * @return The new data row is returned.
   */
  public DataRow addRow(Object[] values)
  {
    int rowIndex;
    DataRow newRow = new DataRow(this, _columns.length, values);

    _rows.add(newRow);

    // Get the index of the new row (the last one)
    rowIndex = _rows.size() - 1;
    
    // Fire event for row being added
    this.fireTableRowsInserted(rowIndex, rowIndex);
    
    return (newRow);
  }


  /***
   * Clears the data table.
   */
  public void clear()
  {
    int lastRowIndex;

    // If there's at least one row in the table
    if (_rows.size() > 0)
    {
      // Get the index of the last row
      lastRowIndex = _rows.size() - 1;
      
      // Clear the ArrayList of rows
      _rows.clear();

      // Fire event
      this.fireTableRowsDeleted(0, lastRowIndex);
    }

  }


  /***
   * Sorts the data table by the specified column name.
   * 
   * @param columnName
   *          The name of the column by which to sort the data table.
   * @parm ascending Sort in ascending or descending order.
   */
  public void sort(String columnName, boolean ascending)
  {
    // Set the sort column index
    _sortColumnIndex = _columnMap.get(columnName);

    // Set the sort order
    _sortAscending = ascending;

    // Perform the sort
    Collections.sort(_rows);
  }


  /***
   * Returns the index of the column currently used for sorting.
   * 
   * @return
   */
  public int getSortColumnIndex()
  {
    return (_sortColumnIndex);
  }


  /***
   * Returns the sort order.
   * 
   * @return If ascending order, true is returned. Otherwise, false is returned.
   */
  public boolean getSortAscending()
  {
    return (_sortAscending);
  }


  /***
   * Returns the index of the specified column name.
   * 
   * @param columnName
   *          The name of the column.
   * @return
   */
  public int getColumnIndex(String columnName)
  {
    return (_columnMap.get(columnName));
  }


  /***
   * Returns the number of columns.
   */
  @Override
  public int getColumnCount()
  {
    return (_columns.length);
  }


  /***
   * Returns the number of rows.
   */
  @Override
  public int getRowCount()
  {
    return (_rows.size());
  }


  /***
   * Returns the value for the specified row and column.
   */
  @Override
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    return (_rows.get(rowIndex).getValue(columnIndex));
  }


  /***
   * Returns the value for the specified row and column.
   * 
   * @param rowIndex
   *          The index of the row.
   * @param columnName
   *          The name of the column.
   * @return The value of the specified cell is returned.
   */
  public Object getValueAt(int rowIndex, String columnName)
  {
    return (_rows.get(rowIndex).getValue(columnName));
  }


  /***
   * Returns the class for the specified column.
   */
  @Override
  public Class<?> getColumnClass(int columnIndex)
  {
    return (_columns[columnIndex].getType());
  }


  /***
   * Returns the name for the specified column.
   */
  @Override
  public String getColumnName(int columnIndex)
  {
    return (_columns[columnIndex].getName());
  }


  /***
   * Returns true if the cell is editable. Otherwise returns false.
   */
  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex)
  {

    // If editing for entire table is disabled, return false
    if (!_allowEditing)
      return (false);

    return (_columns[columnIndex].getEditable());
  }


  /***
   * Sets the value of the cell at the specified row and column.
   */
  @Override
  public void setValueAt(Object value, int rowIndex, int columnIndex)
  {
    _rows.get(rowIndex).setValue(columnIndex, value);

    if (_eventsEnabled)
      fireTableCellUpdated(rowIndex, columnIndex);
  }


  /***
   * Sets the value of the cell at the specified row and column.
   * 
   * @param value
   *          The value to be assigned.
   * @param rowIndex
   *          The index of the row.
   * @param columnName
   *          The name of the column.
   */
  public void setValueAt(Object value, int rowIndex, String columnName)
  {
    int columnIndex;

    columnIndex = _columnMap.get(columnName);

    _rows.get(rowIndex).setValue(columnIndex, value);

    if (_eventsEnabled)
      fireTableCellUpdated(rowIndex, columnIndex);
  }


  /***
   * If events are enabled, true is returned. Otherwise false is returned.
   * 
   * @return
   */
  public boolean getEventsEnabled()
  {
    return (_eventsEnabled);
  }


  /***
   * Enables or disables events.
   * 
   * @param eventsEnabled
   *          True to enable events. False to disable events.
   */
  public void setEventsEnabled(boolean eventsEnabled)
  {
    _eventsEnabled = eventsEnabled;
  }


  /***
   * If editing is allowed for the table, true is returned. Otherwise, false is
   * returned.
   * 
   * @return
   */
  public boolean getAllowEditing()
  {
    return (_allowEditing);
  }


  /***
   * Enables or disables editing for the entire table.
   * 
   * @param allowEditing
   *          True to allow editing. False to prevent editing.
   */
  public void setAllowEditing(boolean allowEditing)
  {
    _allowEditing = allowEditing;
  }

}