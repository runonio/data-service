/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.runon.commons.sync;

import io.runon.commons.api.ApiMessage;
import io.runon.commons.api.Messages;
import io.runon.commons.utils.ExceptionUtil;

/**
 * 동기화 api
 * @author macle
 */
public class SyncApi extends ApiMessage {


    @Override
    public void receive(String message) {
        try{
            SynchronizerManager synchronizerManager = SynchronizerManager.getInstance();
            synchronizerManager.sync();
            communication.sendMessage(Messages.SUCCESS);
        }catch(Exception e){
            communication.sendMessage(Messages.FAIL + ExceptionUtil.getStackTrace(e));
        }
    }
}
