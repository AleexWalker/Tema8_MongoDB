package exercicis

import com.mongodb.MongoClient
import org.bson.Document
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL
import java.util.logging.Level
import java.util.logging.LogManager

fun main(){
    LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)

    val url = URL("http://gestiona.bicicas.es/apps/apps.php")
    val rd = url.openConnection().getInputStream().reader()
    val json = (JSONTokener(url.openConnection().getInputStream()).nextValue() as JSONArray).get(0) as JSONObject

    val estaciones = json.getJSONArray("ocupacion")

    val conexion = MongoClient("localhost", 27017)
    val baseDatos = conexion.getDatabase("test")

    for (numero in 0 until estaciones.length()){
        val estacion = estaciones.get(numero) as JSONObject

        val documento = Document()

        documento.put("ID", estacion.get("id"))
        documento.put("Punto", estacion.get("punto"))
        documento.put("Puestos", estacion.get("puestos"))
        documento.put("Ocupados", estacion.get("ocupados"))
        baseDatos.getCollection("estaciones").insertOne(documento)
    }
    conexion.close()
}