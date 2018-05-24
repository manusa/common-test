/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-05-20.
 */

package com.marcnuri.spring.test.mock.beans;

import static org.mockito.Mockito.mockingDetails;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * Implementation of {@link InstantiationAwareBeanPostProcessorAdapter} that prevents Mocked beans
 * with {@link Autowired} annotated fields to be post processed by the Spring Container otherwise
 * requiring a Bean of the autowired field type.
 *
 * @author Marc Nuri
 * @since 0.0.1
 */
public class MockInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

  @Override
  public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
    return mockingDetails(bean).isMock() ? false : super.postProcessAfterInstantiation(bean, beanName);
  }

}
