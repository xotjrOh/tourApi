package com.interpark.tour.common.msg;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultVo<T> {
    private String resultCode;
    private String resultMessage;
    private T resultData;

    @Builder
    public ResultVo(String resultCode, String resultMessage, T resultData) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultData = resultData;
    }
}
