package com.example.stealer.model;

import com.example.stealer.enums.SizeType;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class Size {

    SizeType type;

    List<Integer> values;
}
