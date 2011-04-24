<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"/>
<xsl:template match="/candies">
<html>
<head>
<title>Catalog</title>
</head>
<body>
<table border="1" cellspacing="0">
<tr>
<th>name</th>
<th>energy</th>
<th>type</th>
<th>water</th>
<th>sugar</th>
<th>fructose</th>
<th>chocolate type</th>
<th>vanillin</th>
<th>proteins</th>
<th>fats</th>
<th>carbohydrates</th>
<th>production</th>
</tr>
<xsl:for-each select="candy">
<tr>
<td><xsl:value-of select="name"/></td>
<td><xsl:value-of select="energy"/></td>
<td><xsl:value-of select="type"/></td>
<xsl:for-each select="ingredients">
	<td><xsl:value-of select="water"/></td>
	<td><xsl:value-of select="suger"/></td>
	<td><xsl:value-of select="fructose"/></td>
	<td><xsl:value-of select="chocolateType"/></td>
	<td><xsl:value-of select="vanillin"/></td>
</xsl:for-each>
<xsl:for-each select="value">
	<td><xsl:value-of select="proteins"/></td>
	<td><xsl:value-of select="fats"/></td>
	<td><xsl:value-of select="carbohydrates"/></td>
</xsl:for-each>
<td><xsl:value-of select="production"/></td>
</tr>
</xsl:for-each>

</table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>