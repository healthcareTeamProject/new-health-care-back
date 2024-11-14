package com.example.healthcare_back.common.util;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

// BigDecimal타입의 응답에 관한 클래스
// ex) 150.0일때는 150으로 정수형태로 응답, 150.1~9일때는 150.1~9형태로 응답
public class CustomBigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 소수점 이하 불필요한 0을 제거하고, 소수점 자릿수(scale)가 0 이하인지 확인합니다.
        if (value.stripTrailingZeros().scale() <= 0) {
            // scale이 0 이하인 경우 (즉, 소수점이 없는 경우) 정수로 출력합니다.
            gen.writeNumber(value.intValueExact());
        } else {
            // 소수점이 있는 경우 그대로 BigDecimal 값을 출력합니다.
            gen.writeNumber(value);
        }
    }
}
