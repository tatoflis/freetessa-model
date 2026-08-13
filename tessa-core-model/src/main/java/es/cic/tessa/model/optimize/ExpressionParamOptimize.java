package es.cic.tessa.model.optimize;


public class ExpressionParamOptimize extends TessaElementOptimize
{

    private static final long serialVersionUID = 1L;

    private String type;
    private boolean required;
    private int position;
    private String defaultValue;

    @Override
    public int hashCode()
    {

	return java.util.Objects.hash(name);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(obj == null)
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	ExpressionParamOptimize other = (ExpressionParamOptimize) obj;
	return java.util.Objects.equals(name, other.name);
    }

    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public boolean isRequired()
    {

	return required;
    }


    public void setRequired(boolean required)
    {

	this.required = required;
    }


    public int getPosition()
    {

	return position;
    }


    public void setPosition(int position)
    {

	this.position = position;
    }


    public String getDefaultValue()
    {

	return defaultValue;
    }


    public void setDefaultValue(String defaultValue)
    {

	this.defaultValue = defaultValue;
    }

}
