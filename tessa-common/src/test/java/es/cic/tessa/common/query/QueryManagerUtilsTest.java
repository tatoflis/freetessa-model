package es.cic.tessa.common.query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class QueryManagerUtilsTest
{

    @Test
    void propertyOrderName_name_devuelveNameLower()
    {

	assertEquals("nameLower", QueryManagerUtils.propertyOrderName("name"));
    }

    @Test
    void propertyOrderName_value_devuelveValueLower()
    {

	assertEquals("valueLower", QueryManagerUtils.propertyOrderName("value"));
    }

    @Test
    void propertyOrderName_insertDate_devuelveInsert()
    {

	assertEquals("insert", QueryManagerUtils.propertyOrderName("insertDate"));
    }

    @Test
    void propertyOrderName_modificationDate_devuelveModification()
    {

	assertEquals("modification", QueryManagerUtils.propertyOrderName("modificationDate"));
    }

    @Test
    void propertyOrderName_finalTemplate_devuelveFinal()
    {

	assertEquals("final", QueryManagerUtils.propertyOrderName("finalTemplate"));
    }

    @Test
    void propertyOrderName_abstractTemplate_devuelveAbstract()
    {

	assertEquals("abstract", QueryManagerUtils.propertyOrderName("abstractTemplate"));
    }

    @Test
    void propertyOrderName_propiedadDesconocida_devuelveMismaPropiedad()
    {

	assertEquals("nemonic", QueryManagerUtils.propertyOrderName("nemonic"));
    }

    @Test
    void propertyOrderName_propiedadArbitraria_pasaTal()
    {

	assertEquals("identificator", QueryManagerUtils.propertyOrderName("identificator"));
    }
}
