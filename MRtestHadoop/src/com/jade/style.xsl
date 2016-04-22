<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" >
<xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
<xsl:template match="/">
<xsl:for-each select="//Host">
<xsl:value-of select="../../@id"/>
<xsl:text>,</xsl:text>
<xsl:value-of select="../../@name"/>
<xsl:text>,</xsl:text>
<xsl:value-of select="../../@location"/>
<xsl:text>,</xsl:text>

<xsl:value-of select="@id"/>
<xsl:text>,</xsl:text>
<xsl:value-of select="Host_Name"/>
<xsl:text>,</xsl:text>
<xsl:value-of select="IP_address"/>
<xsl:text>,</xsl:text>
<xsl:value-of select="OS"/>
<xsl:text>,</xsl:text>
<xsl:value-of select="Load_avg_1min"/>
<xsl:text>,</xsl:text>
<xsl:value-of select="Load_avg_5min"/>
<xsl:text>,</xsl:text>
<xsl:value-of select="Load_avg_15min"/>
<xsl:if test="position () &lt; last()">
  <xsl:text>&#xA;</xsl:text>
</xsl:if>



</xsl:for-each>


</xsl:template>
</xsl:stylesheet>