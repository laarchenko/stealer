package com.example.stealer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MappingUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}
