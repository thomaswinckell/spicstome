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

import java.util.LinkedHashMap;
import java.util.List;

import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.ui.form.FormUtils;
import com.spicstome.client.ui.widget.Crumb;

/**
 * Sample implementation of {@link UsersManagementView}.
 */
public class UsersManagementViewImpl extends UserViewImpl implements UsersManagementView {

	DynamicForm editUserForm;
	SelectItem studentsSelectItem;
	ButtonItem editStudentButtonItem;
	LinkedHashMap<String, String> studentsValueMap, studentsImagesValueMap;
	
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
		
		editUserForm = new DynamicForm();
		
		/* Student edit/remove */
		
		studentsSelectItem = new SelectItem("students", "Etudiants");
		editStudentButtonItem = new ButtonItem("btn_edit_student", "Editer");
		
		studentsValueMap = new LinkedHashMap<String, String>();
		studentsImagesValueMap = new LinkedHashMap<String, String>();
		
		studentsSelectItem.setImageURLPrefix(FormUtils.UPLOAD_IMAGE_PATH);
		
		editUserForm.setFields(studentsSelectItem, editStudentButtonItem);
		
		mainPanel.addMember(editUserForm);
	}
	
	public void setStudents (List<StudentDTO> students) {
		String firstStudentId = null;
		for(StudentDTO student : students) {
			
			if (firstStudentId == null)
				firstStudentId = student.getId().toString();
				
			studentsValueMap.put(student.getId().toString(), student.getFirstName()+" "+student.getName());
			studentsImagesValueMap.put(student.getId().toString(), student.getImage().getFilename());
		}
		
		if (firstStudentId != null) {
			studentsSelectItem.setDefaultValue(firstStudentId);
			editStudentButtonItem.enable();
		}
		else {
			studentsSelectItem.setDefaultValue("Aucun");
			studentsSelectItem.disable();
			editStudentButtonItem.disable();
		}
		
		studentsSelectItem.setValueMap(studentsValueMap);
		studentsSelectItem.setValueIcons(studentsImagesValueMap);
		
		editStudentButtonItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				
				Long idStudent = Long.parseLong(studentsSelectItem.getValueAsString());
				
				goTo(new AddUserPlace(idStudent.toString()));
			}				
		});
	}
}
