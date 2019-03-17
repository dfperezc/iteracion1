package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.RolUsuario;
import uniandes.isis2304.hotelAndes.persistencia.PersistenciaHotelAndes;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ROL DE USUARIO de hotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author NOT A MAC
 */
public class SQLRolUsuario {
	
	//----------------------------------------------------------------
	//			CONSTANTES
	//----------------------------------------------------------------
		/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaHotelAndes.SQL;

	//----------------------------------------------------------------
		//			ATRIBUTOS
		//----------------------------------------------------------------
		/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaHotelAndes pha;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pha - El Manejador de persistencia de la aplicación
	 */
	public SQLRolUsuario (PersistenciaHotelAndes pha)
	{
		this.pha = pha;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un ROL USUARIO a la base de datos de hotelAndes
	 * @param pm - El manejador de persistencia
	 * @param idRolUsuario - El identificador del rol de usuario
	 * @param nombreRolUsuario - El nombre del  rol de usuario
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarRolUsuario (PersistenceManager pm, long idRolUsuario, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.darTablaRolUsuario() + "(id, nombre) values (?, ?)");
        q.setParameters(idRolUsuario, nombre);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar roles de usuario de la base de datos de hotelAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreRolUsuario - El nombre del rol de usuario
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarRolUsuarioPorNombre (PersistenceManager pm, String nombreRolUsuario)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pha.darTablaRolUsuario() + " WHERE nombre = ?");
        q.setParameters(nombreRolUsuario);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar ROLES DE USUARIO de la base de datos de hotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idRolUsuario - El identificador del rol de usuario
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarRolUsuarioPorId (PersistenceManager pm, long idRolUsuario)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pha.darTablaRolUsuario() + " WHERE id = ?");
        q.setParameters(idRolUsuario);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Rol de usuario de la 
	 * base de datos de hotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idRolUsuario - El identificador del rol de usuario
	 * @return El objeto RolUsuario que tiene el identificador dado
	 */
	public RolUsuario darRolUsuarioPorId (PersistenceManager pm, long idRolUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.darTablaRolUsuario() + " WHERE id = ?");
		q.setResultClass(RolUsuario.class);
		q.setParameters(idRolUsuario);
		return (RolUsuario) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN ROL DE USUARIO de la 
	 * base de datos de hotelAndesl, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreRolUsuario - El nombre del rol de usuario
	 * @return El objeto ROL USUARIO que tiene el nombre dado
	 */
	public List<RolUsuario> darRolesUsuarioNombre (PersistenceManager pm, String nombreRolUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.darTablaRolUsuario() + " WHERE nombre = ?");
		q.setResultClass(RolUsuario.class);
		q.setParameters(nombreRolUsuario);
		return (List<RolUsuario>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ROLES DE USUARIO de la 
	 * base de datos de hotelAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ROLUSUARIO
	 */
	public List<RolUsuario> darRolesUsuario (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.darTablaRolUsuario  ());
		q.setResultClass(RolUsuario.class);
		return (List<RolUsuario>) q.executeList();
	}

}
