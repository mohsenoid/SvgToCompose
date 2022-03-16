# SvgToCompose
SVG path to Jetpack Compose tool

This tool can take the SVG path, and export it as a Jetpack Compose material icon path method calls.

## Example
SVG path:
```
M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-6h2v6zm0-8h-2V7h2v2z
```
Compose path:
```Kotlin
moveTo(12.0f, 2.0f)
curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f)
reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f)
reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f)
reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f)
close()
moveToRelative(1.0f, 15.0f)
horizontalLineToRelative(-2.0f)
verticalLineToRelative(-6.0f)
horizontalLineToRelative(2.0f)
verticalLineToRelative(6.0f)
close()
moveToRelative(0.0f, -8.0f)
horizontalLineToRelative(-2.0f)
verticalLineTo(7.0f)
horizontalLineToRelative(2.0f)
verticalLineToRelative(2.0f)
close()
```
