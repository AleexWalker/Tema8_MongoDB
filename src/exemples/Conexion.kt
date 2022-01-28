package exemples

import com.mongodb.MongoClient

val con = MongoClient("localhost", 27017)
val bd = con.getDatabase("test")
