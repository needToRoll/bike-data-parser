package needtoroll.bikedataparser.model

import needtoroll.bikedataparser.annotation.ExportableColumn

data class Geometry(@ExportableColumn("geometryType") var type: String = "",
                    var coordinates: List<Float> = arrayListOf())