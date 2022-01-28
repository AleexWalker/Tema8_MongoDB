package exemples

import com.mongodb.MongoClient
import java.util.logging.Level
import java.util.logging.LogManager

fun main(){
    LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)

    val con = MongoClient("localhost" , 27017)
    val bd = con.getDatabase("test")

    val llibres = bd.getCollection("libro").find()

    for (llibre in llibres)
        println(llibre.get("titulo"))

    con.close();
}


