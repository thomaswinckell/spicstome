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

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;


public interface SpicsToMeServicesAsync {
	
	/* SESSION */
	void getUser(String userName, String password, AsyncCallback<UserDTO> callback);
	void getCurrentUser(AsyncCallback<UserDTO> callback);
	void disconnectCurrentUser(AsyncCallback<Boolean> callback);
	
	/* GET */
	void getAlbumOwner(long id,AsyncCallback<StudentDTO> callback);
	void getFoldersAlbum(long id,AsyncCallback<List<FolderDTO>> callback);
	void getAlbum(long id,AsyncCallback<AlbumDTO> callback);
	void getStudent(Long idStudent, AsyncCallback<StudentDTO> callback); 
	void getReferentConnected(AsyncCallback<ReferentDTO> callback); 
	void getAllStudents(AsyncCallback<List<StudentDTO>> callback);
	
	/* SAVE */
	void saveImage(ImageDTO imageDTO, AsyncCallback<Long> callback);
	void saveFolder(FolderDTO folderDTO, AsyncCallback<Long> callback);
	void saveAlbum(AlbumDTO albumDTO, AsyncCallback<Long> callback);
	void saveArticle(ArticleDTO articleDTO, AsyncCallback<Long> callback);

	
	void deleteArticle(long id,AsyncCallback<Boolean> callback);
	void deleteFolder(long id,AsyncCallback<Boolean> callback);


	void saveUser(UserDTO userDTO, AsyncCallback<Long> callback);
	void saveStudent(StudentDTO studentDTO, AsyncCallback<Long> callback);
	void saveTeacher(TeacherDTO teacherDTO, AsyncCallback<Long> callback);
	void saveReferent(ReferentDTO referentDTO, AsyncCallback<Long> callback);

}
