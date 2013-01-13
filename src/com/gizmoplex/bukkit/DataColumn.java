package com.gizmoplex.bukkit;

public class DataColumn
{


  // Private data members
  private String _name;
  private Class<?> _type;
  private boolean _editable;


  /***
   * Constructor for the DataColumn class.
   * 
   * @param name
   *          The name of the column.
   * @param type
   *          The class type of the column.
   * @param editable
   *          Whether or not the column is editable.
   */
  public DataColumn(String name, Class<?> type, boolean editable)
  {

    // Save the column name and type
    _name = name;
    _type = type;
    _editable = editable;

  }


  /***
   * Returns the name of the column.
   * 
   * @return
   */
  public String getName()
  {
    return (_name);
  }


  /***
   * Sets the name of the column.
   * 
   * @param name
   */
  public void setName(String name)
  {
    _name = name;
  }


  /***
   * Returns the type of the column.
   * 
   * @return
   */
  public Class<?> getType()
  {
    return (_type);
  }


  /***
   * Sets the type of the column.
   * 
   * @param type
   */
  public void setType(Class<?> type)
  {
    _type = type;
  }


  /***
   * Returns true if the column is editable. Otherwise, returns false.
   * 
   * @return
   */
  public boolean getEditable()
  {
    return (_editable);
  }


  /***
   * Sets the column to be editable or not.
   * 
   * @param editable
   */
  public void setEditable(boolean editable)
  {
    _editable = editable;
  }


  /***
   * Clones the column.
   */
  protected DataColumn clone()
  {
    return (new DataColumn(_name, _type, _editable));
  }

}
