<?xml version='1.0' encoding='UTF-8' ?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
<xsl:output encoding="UTF-8" method="xml" omit-xml-declaration="yes" />
<xsl:template match="/flowers" >
<html>
<body>
<table border="1px">
<tr>
<td>Name</td>
<td>Soil</td>
<td>Origin</td>
<td width="200px">Visual parameters(stem colour/leafs colour/ave. size)</td>
<td width="200px">Growing tips(temperature, lighting, watering)</td>
<td>Multiplying</td>
</tr>
<xsl:for-each select="flower" >
<xsl:sort select="@name"/>
<tr>
<td><xsl:value-of select="@name"/></td>
<td><xsl:value-of select="@soil"/></td>
<td><xsl:value-of select="@origin"/></td>
<td><xsl:value-of select="concat(visual_parameters/stem_colour, '/', visual_parameters/leafs_colour, '/', visual_parameters/ave_size)"/></td>
<td><xsl:value-of select="concat(growing_tips/temperature, '/', growing_tips/lighting, '/', growing_tips/watering)"/></td>
<td><xsl:value-of select="multiplying"/></td>
</tr>
</xsl:for-each>
</table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
