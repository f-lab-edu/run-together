package com.srltas.runtogether.application.exception;

import com.srltas.runtogether.common.exception.RunTogetherException;
import com.srltas.runtogether.common.exception.code.ErrorCode;

public class UnauthorizedException extends RunTogetherException {

  public UnauthorizedException(ErrorCode errorCode) {
    super(errorCode);
  }
}
