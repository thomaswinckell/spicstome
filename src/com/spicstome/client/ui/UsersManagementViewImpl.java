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
package com.spicstome.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.ui.widget.Crumb;

/**
 * Sample implementation of {@link UsersManagementView}.
 */
public class UsersManagementViewImpl extends UserViewLayout implements UsersManagementView {

	public UsersManagementViewImpl() {

		super();
		
		addCrumb(new Crumb("Gestion des utilisateurs") {
			@Override
			public void onClickCrumb() {			
				goTo(new UsersManagementPlace());
			}
		});
		
		Button buttonAddUser = new Button("Ajouter un utilisateur", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				goTo(new AddUserPlace());
			}			
		});
		
		mainPanel.addMember(buttonAddUser);
	}
}
