package com.mohsenoid.compose.svgconvertor.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "svg", strict = false)
class ModelSvg {

    @field:ElementList(name = "path", required = true, inline = true)
    lateinit var paths: List<ModelPath>
}
