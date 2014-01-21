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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;

@RemoteServiceRelativePath("SpicsToMeServices")
public interface SpicsToMeServices extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static SpicsToMeServicesAsync instance;
		public static SpicsToMeServicesAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(SpicsToMeServices.class);
			}
			return instance;
		}
	}
	
	/* SESSION */
	public UserDTO getUser(String userName, String password);
	public UserDTO getCurrentUser();
	public boolean disconnectCurrentUser();
	
	/* GET */
	public FolderDTO getFolder(long id);
	public StudentDTO getAlbumOwner(long id);
	public AlbumDTO getAlbum(long id);
	public UserDTO getUser(Long idUser);
	public StudentDTO getStudent(Long idStudent);
	public ReferentDTO getReferentConnected();
	public UserDTO getUserByLogin(String login);
	public List<StudentDTO> getAllStudents();
	public List<TeacherDTO> getAllTeachers();
	public List<ReferentDTO> getAllReferents();
	
	/* SAVE */
	public Long saveImage(ImageDTO imageDTO);
	public Long saveFolder(FolderDTO folderDTO);
	public Long saveAlbum(AlbumDTO albumDTO);
	public Long saveArticle(ArticleDTO articleDTO);
	public Long saveUser(UserDTO userDTO);

	/* UPDATE */
	public boolean updateFolder(FolderDTO folderDTO);
	public boolean updateArticle(ArticleDTO articleDTO);

	public Long updateImage(ImageDTO imageDTO);
	public Long updateUser(UserDTO userDTO, boolean isNewPassword);
	
	/* DELETE */
	public boolean deleteArticle(long id);
	public boolean deleteFolder(long id);
	public boolean deleteUser(long id);
	
	/*COPY*/
	public FolderDTO copyFolder(FolderDTO folderDTO,FolderDTO parent);
	public ArticleDTO copyArticle(ArticleDTO articleDTO,FolderDTO parent);
}
