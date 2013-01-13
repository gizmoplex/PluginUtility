package com.gizmoplex.bukkit;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;


public class LocationReference implements java.io.Serializable
{


  private static final long serialVersionUID = 1L;
  
  // Private data members
  private String _worldName;
  private double _x;
  private double _y;
  private double _z;
  private float _yaw;
  private float _pitch;


  /***
   * Creates a new LocationReference from a Location object.
   * 
   * @param Location
   *          The Location object from which the LocationReference is created.
   */
  public LocationReference(Location Location)
  {
    
    // Set data values
    _worldName = Location.getWorld().getName();
    _x = Location.getX();
    _y = Location.getY();
    _z = Location.getZ();
    _yaw = Location.getYaw();
    _pitch = Location.getPitch();
  }


  /***
   * Creates a new LocationReference from discreet data values.
   * 
   * @param WorldName
   *          The name of the world.
   * @param X
   *          The X position.
   * @param Y
   *          The Y position.
   * @param Z
   *          The Z position.
   * @param Yaw
   *          The yaw value.
   * @param Pitch
   *          The pitch value.
   */
  public LocationReference(String WorldName, double X, double Y, double Z,
      float Yaw, float Pitch)
  {

    // Save the location parameters
    _worldName = WorldName;
    _x = X;
    _y = Y;
    _z = Z;
    _yaw = Yaw;
    _pitch = Pitch;

  }


  public String getWorldName()
  {
    return (_worldName);
  }


  public void setWorldName(String WorldName)
  {
    _worldName = WorldName;
  }


  public double getX()
  {
    return (_x);
  }


  public void setX(double X)
  {
    _x = X;
  }


  public double getY()
  {
    return (_y);
  }


  public void setY(double Y)
  {
    _y = Y;
  }


  public double getZ()
  {
    return (_z);
  }


  public void setZ(double Z)
  {
    _z = Z;
  }


  public float getYaw()
  {
    return (_yaw);
  }


  public void setYaw(float Yaw)
  {
    _yaw = Yaw;
  }


  public float getPitch()
  {
    return (_pitch);
  }


  public void setPitch(float Pitch)
  {
    _pitch = Pitch;
  }


  /***
   * Creates a new Location object based on the information contained in the
   * LocationReference object.
   * 
   * @param Plugin
   *          The JavaPlugin object. This is used to access the server and
   *          obtain the world.
   * @return If successful, a new Location object is returned. Otherwise, null
   *         is returned.
   */
  public Location ceateLocation(JavaPlugin Plugin)
  {
    Location location;
    World world;

    // Retrieve the world
    world = Plugin.getServer().getWorld(_worldName);

    // If world not found, error
    if (world == null)
      return (null);

    // Create the location
    location = new Location(world, _x, _y, _z, _yaw, _pitch);

    // Return the location
    return (location);

  }
  

}
