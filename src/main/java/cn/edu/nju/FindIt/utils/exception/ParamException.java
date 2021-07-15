package cn.edu.nju.FindIt.utils.exception;

import cn.edu.nju.FindIt.utils.enums.ExceptionEnum;

public class ParamException extends BasicException {
    public ParamException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}