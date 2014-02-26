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

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.shared.Point2D;

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
	public List<AlbumDTO> getGeneralAndExampleAlbum();
	public ReferentDTO getReferentConnected();
	public UserDTO getUserByLogin(String login);
	public List<StudentDTO> getAllStudents();
	public List<TeacherDTO> getAllTeachers();
	public List<ReferentDTO> getAllReferents();
	public List<UserDTO> getEverybody();
	public Double getAverageMessageLength(int nW,int nY,ArrayList<LogDTO> set);
	public Double getAverageTimeExecution(int nW,int nY,ArrayList<LogDTO> set);
	public ArrayList<Point2D> getHistoryPerWeek(ArrayList<LogDTO> set,int type);
	public ArrayList<Double> getPartitionMessageLength(ArrayList<LogDTO> set);
	
	/* SAVE */
	public Long saveImage(ImageDTO imageDTO);
	public Long saveFolder(FolderDTO folderDTO);
	public Long saveAlbum(AlbumDTO albumDTO);
	public Long saveWord(WordDTO wordDTO);
	public Long saveUser(UserDTO userDTO);
	public Long saveLog(LogDTO logDTO);

	/* UPDATE */
	public boolean updateFolder(FolderDTO folderDTO);
	public boolean updateWord(WordDTO wordDTO);
	public Long updateImage(ImageDTO imageDTO);
	public Long updateUser(UserDTO userDTO, boolean isNewPassword);
	public boolean updateAlbum(AlbumDTO album);
	public boolean updateFolderAndChild(FolderDTO folder);
	
	/* DELETE */
	public boolean deleteWord(long id);
	public boolean deleteFolder(long id);
	public boolean deleteUser(long id);
	
	/*COPY*/
	public FolderDTO copyFolder(FolderDTO folderDTO,FolderDTO parent);
	public WordDTO copyWord(WordDTO articleDTO,FolderDTO parent);
	public AlbumDTO copyAlbum(AlbumDTO albumDTO);
	
	/*MAIL*/
	public boolean sendMail(UserDTO sender, String emailReceiver, ArrayList<WordDTO> words, ArrayList<String> correctedWords);
}
