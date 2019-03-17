package uniandes.isis2304.hotelAndes.negocio;

import uniandes.isis2304.parranderos.negocio.TipoBebida;

/**
 * Clase para modelar el concepto rol de usuario del negocio de los Parranderos
 *
 * @author NOT A MAC
 */

public class RolUsuario implements VORolUsuario 
{
	//-----------------------------------------------------------------
	// 			ATRIBUTOS
	//-----------------------------------------------------------------
	/**
	 * El identificador del tipo del RolUsuario
	 */
	private long id;

	/**
	 * El nombre del RolUsuario
	 */
	private String nombre;

	//-----------------------------------------------------------------
	// 			METODOS
	//-----------------------------------------------------------------
	/**
	 * Constructor por defecto
	 */
	public RolUsuario() 
	{
		this.id = 0;
		this.nombre = "Default";
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del rol de usuario
	 * @param nombre - El nombre del rol de usuario
	 */
	public RolUsuario(long id, String nombre) 
	{
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * @return El id del rol de usuario
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del rol de usuario
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El nombre del rol de usuario
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre del rol de usuario
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}


	/**
	 * @return Una cadena de caracteres con la información del rol de usuario
	 */
	@Override
	public String toString() 
	{
		return "TipoBebida [id=" + id + ", nombre=" + nombre + "]";
	}

	/**
	 * @param tipo - El RolUsuario a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		RolUsuario ru = (RolUsuario) tipo;
		return id == ru.id && nombre.equalsIgnoreCase (ru.nombre);
	}
}
