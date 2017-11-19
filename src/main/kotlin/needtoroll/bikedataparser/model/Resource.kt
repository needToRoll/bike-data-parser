package needtoroll.bikedataparser.model

import needtoroll.bikedataparser.annotation.ExportableColumn

data class Resource(@ExportableColumn("resourceName") var name: String = "",
                    @ExportableColumn("resourceType") var type: String = "",
                    var features: List<Feature> = listOf())