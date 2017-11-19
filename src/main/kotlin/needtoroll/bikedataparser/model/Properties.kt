package needtoroll.bikedataparser.model

import needtoroll.bikedataparser.annotation.ExportableColumn


data class Properties(var name: String = "",
                      @ExportableColumn("anzahlPlaetze") var anzahl_pp: Int = 0,
                      var markierung: String = "",
                      var sicherhheit: String = "",
                      var dach: String = "",
                      var signalisation: String = "",
                      var bemerkung: String? = null,
                      var gebuehr: String = "",
                      var oeffentlicher_grund: String = "") {
}