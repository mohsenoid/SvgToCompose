package com.mohsenoid.compose.svgconvertor.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "path", strict = false)
data class ModelPath(

    @field:Attribute(name = "d", required = true)
    var d: String? = null,

    @field:Attribute(name = "fill", required = false)
    var fill: String? = null,
)
