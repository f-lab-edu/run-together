package com.srltas.runtogether.domain.model.user.exception;

import com.srltas.runtogether.common.exception.RunTogetherException;
import com.srltas.runtogether.common.exception.code.ErrorCode;

public class UserException extends RunTogetherException {

  public UserException(ErrorCode errorCode) {
    super(errorCode);
  }
}
