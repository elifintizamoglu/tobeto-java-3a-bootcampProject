package com.tobeto.bootcampProject.core.utilities.mapping;

import org.modelmapper.ModelMapper;
public interface ModelMapperService {

    ModelMapper forRequest();
    ModelMapper forResponse();
}
