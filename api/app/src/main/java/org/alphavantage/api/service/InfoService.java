package org.alphavantage.api.service;

import org.alphavantage.api.dto.InfoDTO;
import org.alphavantage.api.dto.ResponseDTO;

public interface InfoService {

    ResponseDTO<InfoDTO> getInfo();

}
