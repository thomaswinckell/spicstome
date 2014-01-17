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

import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.ui.form.ReferentsEditForm;
import com.spicstome.client.ui.form.StudentsEditForm;
import com.spicstome.client.ui.form.TeachersEditForm;

/**
 * Sample implementation of {@link UsersManagementView}.
 */
public class UsersManagementViewImpl extends UserViewImpl implements UsersManagementView {

	private StudentsEditForm studentsEditForm;
	private ReferentsEditForm referentsEditForm;
	private TeachersEditForm teachersEditForm;
	
	public UsersManagementViewImpl() {

		super();
		
		/*addCrumb(new Crumb("Gestion des utilisateurs") {
			@Override
			public void onClickCrumb() {			
				goTo(new UsersManagementPlace());
			}
		});*/
		
		/* Add a user */
		
		IButton buttonAddUser = new IButton("Ajouter un utilisateur");
		buttonAddUser.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				goTo(new AddUserPlace(null));
			}			
		});		
		
		mainPanel.addMember(buttonAddUser);
		
		studentsEditForm = new StudentsEditForm();
		teachersEditForm = new TeachersEditForm();
		referentsEditForm = new ReferentsEditForm();
		
		mainPanel.addMember(studentsEditForm);
		mainPanel.addMember(teachersEditForm);
		mainPanel.addMember(referentsEditForm);
	}
	
	public void setStudents (List<StudentDTO> students) {
		studentsEditForm.setStudents(students, this);
	}
	
	public void setReferents (List<ReferentDTO> referents) {
		referentsEditForm.setReferents(referents, this);
	}
	
	public void setTeachers (List<TeacherDTO> teachers) {
		teachersEditForm.setTeachers(teachers, this);
	}
}
