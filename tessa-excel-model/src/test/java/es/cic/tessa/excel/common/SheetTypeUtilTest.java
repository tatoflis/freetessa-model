package es.cic.tessa.excel.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class SheetTypeUtilTest
{

    @Test
    void resolveLabels_conLabelsValidos_separaPorComa()
    {

	String[] result = SheetTypeUtil.resolveLabels("label1, label2, label3");

	assertNotNull(result);
	assertEquals(3, result.length);
	assertEquals("label1", result[0]);
	assertEquals("label2", result[1]);
	assertEquals("label3", result[2]);
    }

    @Test
    void resolveLabels_conLabelUnico_devuelveArrayDeUno()
    {

	String[] result = SheetTypeUtil.resolveLabels("single");

	assertNotNull(result);
	assertEquals(1, result.length);
	assertEquals("single", result[0]);
    }

    @Test
    void resolveLabels_conNull_devuelveNull()
    {

	assertNull(SheetTypeUtil.resolveLabels(null));
    }

    @Test
    void resolveLabels_conVacio_devuelveNull()
    {

	assertNull(SheetTypeUtil.resolveLabels(""));
    }

    @Test
    void convertToStringLabels_conLabels_generaStringCorrecto()
    {

	Set<String> labels = new LinkedHashSet<>();
	labels.add("grp1");
	labels.add("grp2");

	String result = SheetTypeUtil.convertToStringLabels(labels);

	assertNotNull(result);
	assertTrue(result.contains("grp1"));
	assertTrue(result.contains("grp2"));
	assertFalse(result.endsWith(","));
    }

    @Test
    void convertToStringLabels_conNull_devuelveVacio()
    {

	assertEquals("", SheetTypeUtil.convertToStringLabels(null));
    }

    @Test
    void convertToStringLabels_conVacio_devuelveVacio()
    {

	assertEquals("", SheetTypeUtil.convertToStringLabels(Set.of()));
    }

    @Test
    void checkMappingHeader_conAlias_eliminaSufijo()
    {

	String result = SheetTypeUtil.checkMappingHeader("AttrName (Alias)");

	assertEquals("AttrName", result);
    }

    @Test
    void checkMappingHeader_conValues_eliminaSufijo()
    {

	String result = SheetTypeUtil.checkMappingHeader("AttrName (Values)");

	assertEquals("AttrName", result);
    }

    @Test
    void checkMappingHeader_sinSufijo_devuelveOriginal()
    {

	String result = SheetTypeUtil.checkMappingHeader("AttrName");

	assertEquals("AttrName", result);
    }

    @Test
    void getAttributeName_conParentesis_eliminaContenido()
    {

	String result = SheetTypeUtil.getAttributeName("Temperature (°C)");

	assertEquals("Temperature", result);
    }

    @Test
    void getAttributeName_sinParentesis_devuelveOriginal()
    {

	String result = SheetTypeUtil.getAttributeName("SimpleName");

	assertEquals("SimpleName", result);
    }

    @Test
    void getAttributeName_conMultiplesParentesis_eliminaTodos()
    {

	String result = SheetTypeUtil.getAttributeName("Name (type) (extra)");

	assertEquals("Name", result);
    }

    @Test
    void getCellValue_conReferenciaExterna_parseaSheetYRef()
    {

	CellValue result = SheetTypeUtil.getCellValue("'Sheet1'!A1");

	assertEquals("Sheet1", result.getSheetName());
	assertEquals("A1", result.getRefCell());
    }

    @Test
    void getCellValue_conValorSimple_devuelveCellValue()
    {

	CellValue result = SheetTypeUtil.getCellValue("simple value");

	assertEquals("simple value", result.getCellValue());
    }
}
