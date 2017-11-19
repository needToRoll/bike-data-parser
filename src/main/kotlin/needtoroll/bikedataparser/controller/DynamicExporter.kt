package needtoroll.bikedataparser.controller

import needtoroll.bikedataparser.annotation.ExportableColumn
import needtoroll.bikedataparser.model.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class DynamicExporter(vararg options: String) : Exporter {

    val options = options


    var completePropertiesMap: MutableMap<KClass<*>, Map<String, KProperty<*>>> = HashMap()
    var exportOptions: MutableList<String> = mutableListOf()

    var typesList = arrayOf(Feature::class, Geometry::class, Properties::class)

    override fun export(resource: Resource) {
        generatePropertiesMap()
        val headers = options.clone()
        val features = resource.features.filter { it.properties.name != "Motorrad" }
        for (feature in features) {
            exportPropertiesRelatedToType(Feature::class, feature)
            exportPropertiesRelatedToType(Geometry::class, feature.geometry)
            exportPropertiesRelatedToType(Properties::class, feature.properties)
        }

    }

    private fun generatePropertiesMap() {
        typesList.forEach { generatePropertiesListForType(it) }

    }

    private fun generatePropertiesListForType(type: KClass<*>) {
        var propertiesMap = HashMap<String, KProperty1<*, *>>()

        val propertiesProperties = type.memberProperties
        for (propertiesProperty in propertiesProperties) {
            var name = propertiesProperty.name
            if (propertiesProperty.annotations.map { it.annotationClass }.contains(ExportableColumn::class)) {
                name = (propertiesProperty.annotations.find { it is ExportableColumn } as ExportableColumn).columnName
            }
            propertiesMap.put(name.toLowerCase(), propertiesProperty)
            exportOptions.add(name.capitalize())
        }
        this.completePropertiesMap.put(type, propertiesMap)

    }

    private fun getKeysToExportForType(type: KClass<*>): List<String> {
        val lowerCaseColumns = options.clone().map { it.toLowerCase() }
        return completePropertiesMap[type]!!.keys.map { it.toLowerCase() }
                .filter { lowerCaseColumns.contains(it) }.toList()
    }

    private fun exportPropertiesRelatedToType(type: KClass<*>, receiver: Any) {
        for (key in getKeysToExportForType(type)) {
            var property = completePropertiesMap[type]!![key] as KProperty1<*, *>
            if (property == null) {
                println("Key: $key cannot be found in Type ${type.simpleName}")
            } else {
                getPropertyValueAsString(type, property, receiver)

            }
        }
    }

    private fun getPropertyValueAsString(type: KClass<*>, property: KProperty1<*, *>, receiver: Any): String {
        return when (type) {
            Feature::class    -> {
                property as KProperty1<Feature, *>
                receiver as Feature
                property.get(receiver).toString()

            }
            Properties::class -> {
                property as KProperty1<Properties, *>
                receiver as Properties
                property.get(receiver).toString()

            }
            Geometry::class   -> {
                property as KProperty1<Geometry, *>
                receiver as Geometry
                property.get(receiver).toString()
            }
            else              -> {
                throw UnsupportedOperationException()
            }
        }

    }
}


