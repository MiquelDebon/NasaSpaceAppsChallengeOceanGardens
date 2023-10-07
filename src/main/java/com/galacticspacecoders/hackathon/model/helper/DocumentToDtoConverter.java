package com.galacticspacecoders.hackathon.model.helper;

import org.modelmapper.ModelMapper;


public class DocumentToDtoConverter {

    public GameDto convertDocumentToDto(S document, Class<D> dtoClass) throws ConverterException {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(document, dtoClass);
        return mapper.map(document, dtoClass);
    }


}
