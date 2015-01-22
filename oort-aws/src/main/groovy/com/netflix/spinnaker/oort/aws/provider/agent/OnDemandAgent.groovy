/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.oort.aws.provider.agent

import com.netflix.spinnaker.cats.agent.CacheResult
import com.netflix.spinnaker.cats.provider.ProviderCache

interface OnDemandAgent {
  public static final int NO_TTL = -1
  public static final int ONE_MINUTE_TTL = 60
  public static final int ONE_HOUR_TTL = 60 * ONE_MINUTE_TTL

  String getOnDemandAgentType();

  static class OnDemandResult {
    String sourceAgentType
    Collection<String> authoritativeTypes = []
    CacheResult cacheResult
    Map<String, Collection<String>> evictions = [:]
  }

  boolean handles(String type)

  OnDemandResult handle(ProviderCache providerCache, Map<String, ? extends Object> data)
}