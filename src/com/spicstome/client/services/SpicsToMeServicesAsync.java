/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.spicstome.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spicstome.client.dto.UserDTO;


public interface SpicsToMeServicesAsync {
	
	void getUser(String userName, String password, AsyncCallback<UserDTO> callback);
	void getCurrentUser(AsyncCallback<UserDTO> callback);
	void disconnectCurrentUser(AsyncCallback<Boolean> callback);
}
