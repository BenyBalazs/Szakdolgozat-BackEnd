package com.benyovszki.szakdolgozat.action;

import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.OperationException;

public abstract class BaseAction {

    protected OperationException missingOrIncompleteParam(String msg) {
        return new OperationException(ErrorType.MISSING_OR_INCOMPLETE_PARAMETER, msg);
    }
}
