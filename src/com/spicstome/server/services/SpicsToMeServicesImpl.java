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
package com.spicstome.server.services;


import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.hibernate.HibernateManager;
import com.spicstome.client.hibernate.HibernateUtil;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Student;
import com.spicstome.client.shared.User;

/**
 * 
 * @author Maxime
 * 
 * This class describe the service on the server side that will perform the hibernate
 * asking and reporting work.
 * It is based on the HibernateManager
 *
 */

public class SpicsToMeServicesImpl extends RemoteServiceServlet implements SpicsToMeServices {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	public UserDTO getUser(String login, String password) {		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    List<User> users = session.createCriteria(User.class).add(Restrictions.eq("login",login)).add(Restrictions.eq("password",password)).list();
	    session.getTransaction().commit();
	    
	    if (users.isEmpty())
	    	return null;
	    else {
	    	UserDTO userDTO = createUserDTO(users.get(0));	    	
	    	getThreadLocalRequest().getSession().setAttribute("currentUser", userDTO);	    
	    	return userDTO;
	    }
	}

	@Override
	public UserDTO getCurrentUser() {
		return (UserDTO)getThreadLocalRequest().getSession().getAttribute("currentUser");
	}
	
	@Override
	public boolean disconnectCurrentUser() {
		getThreadLocalRequest().getSession().setAttribute("currentUser", null);
		return (getCurrentUser()==null);
	}
	
	@Override
	public Long saveImage(ImageDTO imageDTO) {
		Image image = new Image(imageDTO);
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(image);
	    session.getTransaction().commit();
	    return image.getId();
	}
	
	@Override
	public Long saveFolder(FolderDTO folderDTO) {
		Folder folder = new Folder(folderDTO);
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(folder);
	    session.getTransaction().commit();
	    return folder.getId();
	}
	
	@Override
	public Long saveAlbum(AlbumDTO albumDTO) {
		Album album = new Album(albumDTO);
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(album);
	    session.getTransaction().commit();
	    return album.getId();
	}	

	@Override
	public Long saveStudent(StudentDTO studentDTO) {
		
		// saving a default album
		ImageDTO imageFolder = new ImageDTO((long) -1, "all.png");
		Long idImageFolder = saveImage(imageFolder);
		imageFolder.setId(idImageFolder);
		
		FolderDTO folder = new FolderDTO((long) -1, "Tout", 0, null, imageFolder, new HashSet<PecsDTO>());
		Long idFolder = saveFolder(folder);
		folder.setId(idFolder);
		
		AlbumDTO album = new AlbumDTO((long) -1, folder);
		Long idAlbum = saveAlbum(album);
		album.setId(idAlbum);
		studentDTO.setAlbum(album);
		
		Long idImageUser = saveImage(studentDTO.getImage());
		studentDTO.getImage().setId(idImageUser);
		
		Student student = new Student(studentDTO);		
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(student);
	    session.getTransaction().commit();
	    
	    return student.getId();
	}	

	private ImageDTO createImageDTO(Image image) {
		return new ImageDTO(image.getId(), image.getFilename());
	}

	private UserDTO createUserDTO(User user) {
		return new UserDTO(user.getId(), user.getSubscriptionDate(), user.getFirstName(), 
				user.getName(), user.getEmail(), user.getLogin(), user.getPassword(), createImageDTO(user.getImage()));
	}
	
	/*@Override
	public User Login(String login, String password) {
	
		User user = HibernateManager.getInstance().login(login, password);

		if(user!=null)
		{
			getThreadLocalRequest().getSession().setAttribute("currentUser", user);
		}
		
		return user;
		
		
	}

	@Override
	public User CurrentUser() {
		
		return (User)getThreadLocalRequest().getSession().getAttribute("currentUser");
	}*/
}
