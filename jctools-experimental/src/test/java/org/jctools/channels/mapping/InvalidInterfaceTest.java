/*
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
package org.jctools.channels.mapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class InvalidInterfaceTest {
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { NoGettersOrSetters.class }, { InvalidReturnGetter.class }, { ParameterGetter.class },
				{ InvalidReturnSetter.class }, { NoParameterSetter.class }, });
	}

	private Class<?> representingKlass;

	public InvalidInterfaceTest(Class<?> representingKlass) {
		this.representingKlass = representingKlass;
	}

	@Test(expected = InvalidInterfaceException.class)
	public void interfaceIsInvalid() {
		new Mapper(representingKlass, false);
	}

	// ---------------------------------------------------

	public interface NoGettersOrSetters {
		public void neitherGetterNorSetter();
	}

	public interface InvalidReturnGetter {
		public Object getFoo();
	}

	public interface ParameterGetter {
		public int getFoo(long bar);
	}

	public interface InvalidReturnSetter{
		public Object setFoo();
	}

	public interface NoParameterSetter {
		public void setFoo();
	}

}
