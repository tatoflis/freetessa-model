package es.cic.tessa.model.support;


import java.time.Instant;


public interface TemporalElement extends Element
{

    Instant getTimestamp();


    void setTimestamp(Instant moment);
}