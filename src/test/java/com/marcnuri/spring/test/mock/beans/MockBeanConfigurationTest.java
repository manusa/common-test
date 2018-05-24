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

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsMapContaining.hasValue;
import static org.junit.Assert.assertThat;

import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests for {@link MockBeanConfiguration}.
 *
 * @author Marc Nuri
 * @see MockBeanConfiguration
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MockBeanConfiguration.class)
public class MockBeanConfigurationTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void testMockInstantiationAwareBeanPostProcessorInjection() {
    // Given

    // When
    final Map<String, InstantiationAwareBeanPostProcessor> beans =
        applicationContext.getBeansOfType(InstantiationAwareBeanPostProcessor.class);

    // Then
    assertThat(beans.entrySet(), not(empty()));
    assertThat(beans, hasValue(instanceOf(MockInstantiationAwareBeanPostProcessor.class)));
  }
}
