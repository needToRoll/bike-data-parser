package needtoroll.bikedataparser

import needtoroll.bikedataparser.controller.DynamicExporter
import needtoroll.bikedataparser.controller.Importer

fun main(args: Array<String>) {
//    val resource = Importer.readWebResource()
//    val exporter = SimpleExporter("D:\\","exportTest")
//    exporter.export(resource)
    val resource = Importer.readWebResource()
    val exporter = DynamicExporter("featureType", "anzahlPlaetze")
    exporter.export(resource)
}