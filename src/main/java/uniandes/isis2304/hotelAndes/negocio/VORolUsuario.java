package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de rol de usuario.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author NOT A MAC
 */

public interface VORolUsuario {
	
	//------------------------------------------------------------
	// 			METODOS
	//------------------------------------------------------------
	/**
	 * @return El id del rol de usuario
	 */
	public long getId();

	/**
	 * @return El nombre del rol de usuario
	 */
	public String getNombre();

	/**
	 * @return Una cadena de caracteres con la información del rol de usuario
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos roles de usuario
	 * @param ru - El rol de usuario a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	@Override
	public boolean equals (Object ru); 
}
