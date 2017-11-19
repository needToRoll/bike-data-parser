package needtoroll.bikedataparser.controller

import com.google.gson.Gson
import needtoroll.bikedataparser.model.Resource
import java.net.URL

class Importer {
    companion object {

        val urlSource: String = "https://data.stadt-zuerich.ch/dataset/11b61777-800c-4ebe-a340-bbe4affaedfd/" +
                "resource/2eaa9ce6-7a21-429e-b1a0-54b1826a817d/download/zweiradabstellplatz.json"
        val gson: Gson = Gson()

        fun readWebResource(): Resource {
            val url = URL(urlSource)
            val json = url.readText()
            return gson.fromJson(json, Resource::class.java)
        }
    }
}