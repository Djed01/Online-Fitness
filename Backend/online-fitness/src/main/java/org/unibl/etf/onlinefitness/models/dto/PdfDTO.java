package org.unibl.etf.onlinefitness.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.beans.ConstructorProperties;

@Data
@RequiredArgsConstructor
public class PdfDTO {
    private String fileName;
    private byte[] data;

    public PdfDTO(String fileName,byte[] data){
        this.fileName = fileName;
        this.data = data;
    }
}