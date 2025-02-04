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

import io.runon.commons.config.Config;
import io.runon.commons.service.Service;
import io.runon.commons.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 동기화 서비스
 * @author macle
 */
@Slf4j
public class SyncService extends Service {


    /**
     * 생성자
     */
    public SyncService()
    {
        setSleepTime(Config.getLong("sync.service.sleep.time", 3600000L));
        setDelayStartTime(Config.getLong("sync.service.sleep.time", 3600000L));
        setState(State.START);
    }

    @Override
    public void work() {
        try{
            SynchronizerManager synchronizerManager = SynchronizerManager.getInstance();
            if (Config.getBoolean("sync.service.flag", true)
                    && !synchronizerManager.isIng()) {
                synchronizerManager.sync();
            }
        }catch(Exception e){
            log.error(ExceptionUtil.getStackTrace(e));
        }
    }



}
