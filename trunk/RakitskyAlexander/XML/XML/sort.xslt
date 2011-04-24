<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="xml" omit-xml-declaration="yes"/>
<xsl:template match="/candies/candy">
<xsl:apply-templates>
<xsl:sort select="name"
order="ascending"/>
</xsl:apply-templates>
</xsl:template>
<xsl:template match="ingredients">
Title: <xsl:apply-templates select="name"/>
</xsl:template>
</xsl:stylesheet>