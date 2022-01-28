package exemples

import com.mongodb.MongoClient
import org.bson.Document
import java.util.logging.Level
import java.util.logging.LogManager

fun main(){
    LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)

    val con = MongoClient("localhost", 27017)
    val bd = con.getDatabase("test")

    val ordenar = Document()
    ordenar.put("precio", -1)

    val llibres = bd.getCollection("libro").find().sort(ordenar)

    for (llibre in llibres) System.out.println(
        "Títol: " + llibre.get("titulo").toString() + ". Preu: " + llibre.get("precio")
    )

    con.close()
}

