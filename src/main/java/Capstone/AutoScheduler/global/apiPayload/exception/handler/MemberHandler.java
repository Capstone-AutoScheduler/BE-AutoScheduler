package Capstone.AutoScheduler.global.apiPayload.exception.handler;

import Capstone.AutoScheduler.global.apiPayload.code.BaseErrorCode;
import Capstone.AutoScheduler.global.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode baseErrorCode){
        super(baseErrorCode);
    }
}
