package needtoroll.bikedataparser.annotation
@Target(AnnotationTarget.PROPERTY)
annotation class ExportableColumn(val columnName: String)