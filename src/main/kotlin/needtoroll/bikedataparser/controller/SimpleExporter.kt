package needtoroll.bikedataparser.controller

import needtoroll.bikedataparser.model.Resource
import java.io.File

class SimpleExporter(private val targetFilePath: String, private val targetFileName: String) : Exporter {

    override fun export(resource: Resource) {
        simpleCsvExport(resource)
    }

    private fun simpleCsvExport(resource: Resource) {
        val headers = "Latitude;Longitude"
        val data = resource.features
                .filter { it.properties.name != "Motorrad" }
                .map { "${it.geometry.coordinates[1]};${it.geometry.coordinates[0]}" }.toList()
        val content = mutableListOf(headers)
        data.forEach { content.add(it) }
        writeListToFile(content)

    }

    private fun writeListToFile(content: List<String>, fileEnding: String = "csv"){
        val fileName = "$targetFilePath\\$targetFileName.$fileEnding"
        File(fileName).printWriter().use { writer -> content.forEach {writer.println(it)} }
    }

}