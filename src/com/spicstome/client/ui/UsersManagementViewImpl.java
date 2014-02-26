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

import java.util.List;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.UserEditPlace;
import com.spicstome.client.ui.form.ReferentsEditForm;
import com.spicstome.client.ui.form.StudentsEditForm;
import com.spicstome.client.ui.form.TeachersEditForm;
import com.spicstome.client.ui.widget.Crumb;

/**
 * Sample implementation of {@link UsersManagementView}.
 */
public class UsersManagementViewImpl extends UserViewImpl implements UsersManagementView {

	private StudentsEditForm studentsEditForm;
	private ReferentsEditForm referentsEditForm;
	private TeachersEditForm teachersEditForm;
	
	public UsersManagementViewImpl() {

		super();
		
		addCrumb(new Crumb("Gestion des utilisateurs") {
			@Override
			public void onClickCrumb() {
			}
		});
		
		/* Add a user */
		
		IconButton buttonAddUser = new IconButton("");
		buttonAddUser.setIcon("new.png");
		buttonAddUser.setIconSize(40);
		buttonAddUser.setLayoutAlign(Alignment.CENTER);
		
		buttonAddUser.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				goTo(new UserEditPlace(null));
			}			
		});
		
		studentsEditForm = new StudentsEditForm();
		teachersEditForm = new TeachersEditForm();
		referentsEditForm = new ReferentsEditForm();
		
		VLayout vLayout = new VLayout();
		vLayout.addMember(buttonAddUser);
		vLayout.addMember(studentsEditForm);
		vLayout.addMember(teachersEditForm);
		vLayout.addMember(referentsEditForm);
		
		mainPanel.addMember(vLayout);
	}
	
	public void setStudents (List<UserDTO> students) {
		studentsEditForm.setStudents(students, this);
	}
	
	public void setReferents (List<UserDTO> referents) {		
		referentsEditForm.setReferents(referents, this);
	}
	
	public void setTeachers (List<UserDTO> teachers) {
		teachersEditForm.setTeachers(teachers, this);
	}
}
