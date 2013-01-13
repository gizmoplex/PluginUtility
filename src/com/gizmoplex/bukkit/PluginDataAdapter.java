package com.gizmoplex.bukkit;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class PluginDataAdapter<Type extends Object>
{


  private String _fileName;
  private Type _dataObject = null;


  /***
   * Constructor for the PluginDataAdapter class.
   * 
   * @param FileName
   *          Name of file (including path) from which the object is loaded and
   *          saved.
   */
  public PluginDataAdapter(String FileName)
  {

    // Save the file name
    _fileName = FileName;

  }


  /***
   * Saves the object to the file in binary format.
   * 
   * @return If successful, true is returned. Otherwise, false is returned.
   */
  public boolean Save()
  {
    ObjectOutputStream out;
   
    try
    {
      out = new ObjectOutputStream(new FileOutputStream(_fileName));
      out.writeObject(_dataObject);
      out.flush();
      out.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();

      return (false);
    }

    // Return successfully
    return (true);
  }


  /***
   * Loads the object from the file.
   * 
   * @return If successful, true is returned. Otherwise, false is returned.
   */
  @SuppressWarnings("unchecked")
  public boolean LoadObject()
  {
    ObjectInputStream in;

    try
    {
      in = new ObjectInputStream(new FileInputStream(_fileName));
      _dataObject = (Type) in.readObject();
      in.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();

      return (false);
    }

    // Return successfully
    return (true);

  }


  /***
   * Sets the object.
   * 
   * @param Obj
   *          The object.
   */
  public void SetObject(Type Obj)
  {

    // Save the object
    _dataObject = Obj;

  }


  /***
   * Retrieves the object.
   * 
   * @return The object.
   */
  public Type GetObject()
  {

    // Return the object
    return (_dataObject);

  }


  /***
   * Determines whether file exists or not.
   * 
   * @return If file exists, true is returned. Otherwise, false is returned.
   */
  public boolean FileExists()
  {

    return (new File(_fileName).exists());

  }

}