/*
 * Copyright 2010-2013 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill;


import java.net.InetAddress;
import java.net.UnknownHostException;

public class CreatorName {

    // Allow to override the default naming based on Hostname
    private final static String QUEUE_CREATOR_NAME = "org.killbill.queue.creator.name";

    private static String creatorName;

    public static String get() {
        if (creatorName == null) {
            synchronized (CreatorName.class) {
                creatorName = System.getProperty(QUEUE_CREATOR_NAME);
                if (creatorName == null) {
                    try {
                        final InetAddress addr = InetAddress.getLocalHost();
                        creatorName = addr.getHostName();
                    } catch (UnknownHostException e) {
                        creatorName = "creatorName-unknown";
                    }
                }
            }
        }
        return creatorName;
    }
}