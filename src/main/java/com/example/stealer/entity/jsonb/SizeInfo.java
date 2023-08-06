package com.example.stealer.entity.jsonb;

import com.example.stealer.enums.SizeType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SizeInfo {

    SizeType type;
    Integer value;
}
