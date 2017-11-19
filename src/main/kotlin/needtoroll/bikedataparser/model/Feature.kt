package needtoroll.bikedataparser.model

import needtoroll.bikedataparser.annotation.ExportableColumn

data class Feature(@ExportableColumn("featureType") var type: String = "",
                   var geometry: Geometry = Geometry(),
                   var properties: Properties = Properties())
