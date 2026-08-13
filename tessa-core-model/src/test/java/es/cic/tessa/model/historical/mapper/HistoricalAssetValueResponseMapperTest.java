package es.cic.tessa.model.historical.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.HistoricalAssetValue;
import es.cic.tessa.model.historical.dto.HistoricalAssetValueResponse;

class HistoricalAssetValueResponseMapperTest
{

    private HistoricalAssetValueResponseMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new HistoricalAssetValueResponseMapper(null, null);
    }

    @Test
    void historicalAssetValueToResponse_conModifDate_seCopiaAlResponse()
    {

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));

	LocalDateTime modifDate = LocalDateTime.of(2024, 2, 20, 12, 0);
	hav.setModifDate(modifDate);

	HistoricalAssetValueResponse result = mapper.historicalAssetValueToHistoricalAssetValueRespose(hav);

	assertEquals(modifDate.toInstant(ZoneOffset.UTC), result.getModifDate());
    }

    @Test
    void historicalAssetValueToResponse_sinModifDate_quedaNull()
    {

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));

	HistoricalAssetValueResponse result = mapper.historicalAssetValueToHistoricalAssetValueRespose(hav);

	assertNull(result.getModifDate());
    }
}
