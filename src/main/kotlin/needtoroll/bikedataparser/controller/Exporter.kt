package needtoroll.bikedataparser.controller

import needtoroll.bikedataparser.model.Resource

interface Exporter {
    fun export(resource: Resource)
}