package cn.edu.nju.FindIt.utils.exception;

import cn.edu.nju.FindIt.utils.enums.ExceptionEnum;

public class NotLoginException extends BasicException {
    public NotLoginException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}

