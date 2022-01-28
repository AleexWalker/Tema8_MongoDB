package exercicis

import com.mongodb.MongoClient
import org.bson.Document
import java.util.logging.Level
import java.util.logging.LogManager

fun main(args: Array<String>) {
    LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)

    val conexion = MongoClient("localhost", 27017)
    val baseDatos = conexion.getDatabase("test")

    val ordenar = Document()
    ordenar.put("ID:", 1)

    val estaciones = baseDatos.getCollection("estaciones").find().sort(ordenar)

    for (numero in estaciones) {
        println("${numero.get("ID")}.- ${numero.get("Punto")} (${numero.get("Ocupados")}/${numero.get("Puestos")})")
    }

    conexion.close()
}