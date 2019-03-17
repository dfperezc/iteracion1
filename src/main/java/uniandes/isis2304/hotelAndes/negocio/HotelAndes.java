package uniandes.isis2304.hotelAndes.negocio;


import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import uniandes.isis2304.hotelAndes.persistencia.PersistenciaHotelAndes;
import uniandes.isis2304.parranderos.negocio.Bar;
import uniandes.isis2304.parranderos.negocio.Bebedor;
import uniandes.isis2304.parranderos.negocio.Bebida;
import uniandes.isis2304.parranderos.negocio.Gustan;
import uniandes.isis2304.parranderos.negocio.Parranderos;
import uniandes.isis2304.parranderos.negocio.Sirven;
import uniandes.isis2304.parranderos.negocio.TipoBebida;
import uniandes.isis2304.parranderos.negocio.VOBar;
import uniandes.isis2304.parranderos.negocio.VOBebedor;
import uniandes.isis2304.parranderos.negocio.VOBebida;
import uniandes.isis2304.parranderos.negocio.VOGustan;
import uniandes.isis2304.parranderos.negocio.VOSirven;
import uniandes.isis2304.parranderos.negocio.VOTipoBebida;
import uniandes.isis2304.parranderos.negocio.VOVisitan;
import uniandes.isis2304.parranderos.negocio.Visitan;
import uniandes.isis2304.parranderos.persistencia.PersistenciaParranderos;

/**
 * Clase principal del negocio
 * Satisface todos los requerimientos funcionales del negocio
 *
 * @author NOT A MAC
 */
public class HotelAndes 
{

	//-------------------------------------------------------------------
	//			CONSTANTES
	//-------------------------------------------------------------------
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(HotelAndes.class.getName());

	//-------------------------------------------------------------------
	//			ATRIBUTOS
	//-------------------------------------------------------------------
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaHotelAndes pha;

	//-------------------------------------------------------------------
		//			METODOS
		//-------------------------------------------------------------------
		/**
	 * El constructor por defecto
	 */
	public HotelAndes()
	{
		pha = PersistenciaHotelAndes.getInstance();
	}

	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public HotelAndes (JsonObject tableConfig)
	{
		pha = PersistenciaHotelAndes.getInstance(tableConfig);
	}

	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pha.cerrarUnidadPersistencia ();
	}
	//-------------------------------------------------------------------
			//			METODOS PARA MANEJAR ROLES DE USUARIO
			//-------------------------------------------------------------------
			/**
	 * Adiciona de manera persistente un rol de usuario
	 * Adiciona entradas al log de la aplicación
	 * @param rol - El rol de usuario
	 * @return El objeto RolUsuario adicionado. null si ocurre alguna Excepción
	 */
	public RolUsuario adicionarRolUsuario (String rol)
	{
		log.info ("Adicionando Rol de usuario: " + rol);
		RolUsuario rolUsuario = pha.adicionarRolUsuario (rol);		
		log.info ("Adicionando Rol de usario: " + rolUsuario);
		return rolUsuario;
	}

	/**
	 * Elimina un rol de usuario por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del rol de usuario a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarTipoBebidaPorNombre (String nombre)
	{
		log.info ("Eliminando Rol de usuario por nombre: " + nombre);
		long resp = pha.eliminarRolUsuarioPorNombre(nombre);		
		log.info ("Eliminando Rol de usuario por nombre: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Elimina un rol de usuario por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idRolUsuario - El id del rol de usuario a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarRolUsuarioPorId (long idRolUsuario)
	{
		log.info ("Eliminando Rol de usuario por id: " + idRolUsuario);
		long resp = pha.eliminarRolUsuarioId(idRolUsuario);		
		log.info ("Eliminando Rol de usuario por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los roles de usuario en hotelAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos RolUsuario con todos roles de usuario que conoce la aplicación, llenos con su información básica
	 */
	public List<RolUsuario> darRolesUsuario ()
	{
		log.info ("Consultando roles usuario");
		List<RolUsuario> rolesUsuario = pha.darRolesUsuario();	
		log.info ("Consultando roles usuario: " + rolesUsuario.size() + " existentes");
		return rolesUsuario;
	}

	/**
	 * Encuentra todos losroles de usuario en HotelAndes y los devuelve como una lista de VORolUsuario
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VORolUsuario con todos los roles de usuario que conoce la aplicación, llenos con su información básica
	 */
	public List<VORolUsuario> darVORolesUsuario()
	{
		log.info ("Generando los VO de roles de usuario");        
		List<VORolUsuario> voRoles = new LinkedList<VORolUsuario> ();
		for (VORolUsuario ru : pha.darRolesUsuario())
		{
			voRoles.add (ru);
		}
		log.info ("Generando los VO de roles de usuario: " + voRoles.size() + " existentes");
		return voRoles;
	}

	/**
	 * Encuentra el rol de usuario en HotelAndes con el nombre solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de la bebida
	 * @return Un objeto RolUsuario con el rol de usuario de ese nombre que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public RolUsuario darRolUsuarioNombre (String nombre)
	{
		log.info ("Buscando rol usuario por nombre: " + nombre);
		List<RolUsuario> tb = pha.darRolesUsuarioPorNombre(nombre);
		return !tb.isEmpty () ? tb.get (0) : null;
	}
 
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarHotelAndes ()
	{
		log.info ("Limpiando la BD de hotelAndes");
		long [] borrrados = pha.limpiarHotelAndes();	
		log.info ("Limpiando la BD de hotelAndes: Listo!");
		return borrrados;
	}
}
