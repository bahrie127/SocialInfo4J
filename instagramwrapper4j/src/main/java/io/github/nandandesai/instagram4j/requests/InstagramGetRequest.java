/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.nandandesai.instagram4j.requests;

import io.github.nandandesai.instagram4j.InstagramConstants;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 
 * @author brunovolpato
 *
 */
public abstract class InstagramGetRequest<T> extends InstagramRequest<T> {

    @Override
    public String getMethod() {
        return "GET";
    }
    
    @Override
    public T execute() throws IOException, IllegalAccessException, InstantiationException {
        Request request = new Request.Builder().url(InstagramConstants.API_URL + getUrl()).build();
        Call call=api.getClient().newCall(request);
        Response response = call.execute();

        int resultCode=response.code();
        String content=response.body().string();
        response.close();
        call.cancel();
        api.getClient().connectionPool().evictAll();


        return parseResult(resultCode, content);
    }
    
    @Override
    public String getPayload() {
        return null;
    }

    
}
