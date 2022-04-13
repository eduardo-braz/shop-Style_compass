package com.compass.fasttracker.history.service;

import com.compass.fasttracker.history.DTOs.HistoricDTO;
import com.compass.fasttracker.history.models.Historic;
import com.compass.fasttracker.history.repository.HistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class HistoryServiceImpl implements HistoryService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public HistoricDTO getHistoric(Long idUser) {
        Optional<Historic> found = this.historyRepository.findByUserId(idUser);
        if (found.isPresent())
            return modelMapper.map(found.get(), HistoricDTO.class);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


}
