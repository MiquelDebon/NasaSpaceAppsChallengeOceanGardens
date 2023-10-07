package com.galacticspacecoders.hackathon.model.helper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class GenericDocumentToDtoConverter<S,D> {

    public D convertDocumentToDto(S document, Class<D> dtoClass){
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(document, dtoClass);
        return mapper.map(document, dtoClass);
    }


}
