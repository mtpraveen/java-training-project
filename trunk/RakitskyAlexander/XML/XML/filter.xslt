<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" omit-xml-declaration="
yes"/>
<xsl:template match="/candies/candy">
<xsl:apply-templates select="value/proteins[child::text()=10]"/>
</xsl:template>
<xsl:template match="value">
Proteins: <xsl:value-of select="name"/>
production: <xsl:value-of select="production"/>
</xsl:template>
</xsl:stylesheet>