package com.mohsenoid.compose.svgconvertor

import com.mohsenoid.compose.svgconvertor.model.ModelPath
import com.mohsenoid.compose.svgconvertor.model.ModelSvg
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister
import org.w3c.dom.Document
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

class SvgFileHelper(svgFilePath: String) {

    private val svgFile = File(svgFilePath)

    val exists get() = svgFile.exists()

    val isSvg: Boolean get() = svgFile.extension.equals(SVG_FILE_EXTENSION, true)

    fun getSvgPaths(): List<ModelPath> {
        val reader = svgFile.reader(Charsets.UTF_8)
        val serializer: Serializer = Persister()
        val dataFetch = serializer.read(ModelSvg::class.java, reader)
        return dataFetch.paths
    }

    fun altGetSvgPaths() {
        val d = readXml()
//        d.
        println(d)
    }

    private fun readXml(): Document {
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val xmlInput = InputSource(StringReader(svgFile.readText()))
        val doc = dBuilder.parse(xmlInput)

        return doc
    }

    companion object {
        private const val SVG_FILE_EXTENSION = "svg"
    }
}