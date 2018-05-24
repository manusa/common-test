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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

/**
 * Tests for {@link MockInstantiationAwareBeanPostProcessor}.
 *
 * @author Marc Nuri <marc@marcnuri.com>
 * @see MockInstantiationAwareBeanPostProcessor
 */
@RunWith(JUnit4.class)
public class MockInstantiationAwareBeanPostProcessorTest {

  private MockInstantiationAwareBeanPostProcessor miabpp;

  @Before
  public void setUp() {
    miabpp = new MockInstantiationAwareBeanPostProcessor();
  }

  @After
  public void tearDown() {
    miabpp = null;
  }

  @Test
  public void postProcessAfterInstantiation_mockedBean_shouldReturnFalse() {
    // Given
    final Object mockedBean = Mockito.mock(Object.class);
    final String mockedBeanName = "mockedBean";

    // When
    final boolean postProcessBean = miabpp.postProcessAfterInstantiation(mockedBean, mockedBeanName);

    // Then
    final boolean expectedValue = false;
    assertThat(postProcessBean, is(expectedValue));
  }

  @Test
  public void postProcessAfterInstantiation_regularBean_shouldReturnTrue() {
    // Given
    final Object regularBean = new Object();
    final String regularBeanName = "regularBean";

    // When
    final boolean postProcessBean = miabpp.postProcessAfterInstantiation(regularBean, regularBeanName);

    // Then
    final boolean expectedValue = true;
    assertThat(postProcessBean, is(expectedValue));
  }
}
