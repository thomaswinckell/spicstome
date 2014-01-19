package com.spicstome.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Referent;
import com.spicstome.client.shared.Student;
import com.spicstome.client.shared.Teacher;
import com.spicstome.client.shared.User;
import com.spicstome.server.Encryption;
import com.spicstome.server.HibernateUtil;

public class SpicsToMeServicesImpl extends RemoteServiceServlet implements SpicsToMeServices {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	/* SESSION */
	
	@Override
	public UserDTO getUser(String login, String password) {
		
		password = Encryption.toSHA256(password);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    @SuppressWarnings("unchecked")
		List<User> users = session.createCriteria(User.class).add(Restrictions.eq("login",login)).add(Restrictions.eq("password",password)).list();
	    
	    if (users.isEmpty()) {
	    	session.getTransaction().commit();
	    	return null;
	    } else {
	    	UserDTO userDTO = Transtypage.createUserDTO(users.get(0));	    	
	    	getThreadLocalRequest().getSession().setAttribute("currentUser", userDTO.getId());
	    	session.getTransaction().commit();
	    	return userDTO;
	    }
	}

	@Override
	public UserDTO getCurrentUser() {
	
		Object o  = getThreadLocalRequest().getSession().getAttribute("currentUser");
		if(o==null)
			return null;
		
		long id = Long.valueOf(o.toString());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    User user = (User) session.load(User.class, id);
	    UserDTO userDTO = Transtypage.createUserDTO(user);
	    session.getTransaction().commit();
	    
		return userDTO;
	}
	
	@Override
	public boolean disconnectCurrentUser() {
		getThreadLocalRequest().getSession().setAttribute("currentUser", null);
		return (getCurrentUser()==null);
	}
	
	/* GET */

	
	@Override
	public FolderDTO getFolder(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Folder folder = (Folder) session.load(Folder.class, id);
		FolderDTO parent = (folder.getFolder()==null?null:Transtypage.createFolderDTO(folder.getFolder(), null));
		FolderDTO folderDTO = Transtypage.createFolderDTO(folder,parent);
		session.getTransaction().commit();
		return folderDTO;
	}
	
	@Override
	public AlbumDTO getAlbum(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Album> list = session.createCriteria(Album.class).add(Restrictions.eq("id",id)).list();
		
		if(list.size()>0)
		{
			AlbumDTO albumDTO = Transtypage.createAlbumDTO(list.get(0));
			session.getTransaction().commit();
			return albumDTO;
		}
		else
		{
			session.getTransaction().commit();
			return null;
		}
	}

	@Override
	public StudentDTO getAlbumOwner(long id) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
	    Album album = (Album) session.load(Album.class, id);	
	    @SuppressWarnings("unchecked") 
		List<Student> users = session.createCriteria(Student.class).add(Restrictions.eq("album",album)).list();
	    
	    StudentDTO student = Transtypage.createStudentDTO(users.get(0));
	    session.getTransaction().commit();
	   
	    if(users.size()>0)
	    	return student;
	    else
	    	return null;
	}
	
	@Override
	public ReferentDTO getReferentConnected() {
		return (ReferentDTO) getCurrentUser();
	}
	
	@Override
	public UserDTO getUserByLogin(String login) {		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    @SuppressWarnings("unchecked")
		List<User> users = session.createCriteria(User.class).add(Restrictions.eq("login",login)).list();
	    UserDTO userDTO = null;
	    if (!users.isEmpty())
	    	userDTO = Transtypage.createUserDTO(users.get(0));
    	session.getTransaction().commit();
    	return userDTO;
	}
	
	@Override
	public UserDTO getUser(Long idUser) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = (User) session.load(User.class, idUser);		
		UserDTO userDTO = Transtypage.createUserDTO(user);
		session.getTransaction().commit();
		return userDTO;
	}
	
	@Override
	public StudentDTO getStudent(Long idStudent) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Student student = (Student) session.load(Student.class, idStudent);		
		StudentDTO studentDTO = Transtypage.createStudentDTO(student);
		session.getTransaction().commit();
		return studentDTO;
	}
	
	@Override
	public List<StudentDTO> getAllStudents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Student> students = new ArrayList<Student>(session.createCriteria(Student.class).list());
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>(students != null ? students.size() : 0);
		if (students != null) {
			for (Student student : students) {
				studentDTOs.add(Transtypage.createStudentDTO(student));
			}
		}
		session.getTransaction().commit();
		return studentDTOs;
	}
	
	@Override
	public List<TeacherDTO> getAllTeachers() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Teacher> teachers = new ArrayList<Teacher>(session.createCriteria(Teacher.class).list());
		List<TeacherDTO> teacherDTOs = new ArrayList<TeacherDTO>(teachers != null ? teachers.size() : 0);
		if (teachers != null) {
			for (Teacher teacher : teachers) {
				teacherDTOs.add(Transtypage.createTeacherDTO(teacher));
			}
		}
		session.getTransaction().commit();
		return teacherDTOs;
	}
	
	@Override
	public List<ReferentDTO> getAllReferents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Referent> referents = new ArrayList<Referent>(session.createCriteria(Referent.class).list());		
		List<ReferentDTO> referentDTOs = new ArrayList<ReferentDTO>(referents != null ? referents.size() : 0);
		if (referents != null) {
			for (Referent referent : referents) {
				referentDTOs.add(Transtypage.createReferentDTO(referent));
			}
		}
		session.getTransaction().commit();
		return referentDTOs;
	}
	
	/* SAVE */
	@Override
	public Long saveImage(ImageDTO imageDTO) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    Image image = new Image(imageDTO);
	    session.save(image);
	    session.getTransaction().commit();
	    return image.getId();
	}
	

	@Override
	public Long saveUser(UserDTO userDTO) {
		//Long idImageUser = saveImage(new ImageDTO((long) -1, userDTO.getImage().getFilename()));
		
		Long idImageUser = saveImage(userDTO.getImage());
		userDTO.getImage().setId(idImageUser);
		
		userDTO.setPassword(Encryption.toSHA256(userDTO.getPassword()));
		
		if (userDTO instanceof StudentDTO)
			return saveStudent((StudentDTO) userDTO);
		else if (userDTO instanceof TeacherDTO)
			return saveTeacher((TeacherDTO) userDTO);
		else if (userDTO instanceof ReferentDTO)
			return saveReferent((ReferentDTO) userDTO);
		else
			return null;
	}

	private Long saveStudent(StudentDTO studentDTO) {
			
		ImageDTO imageFolder = new ImageDTO((long) -1, "all.png");
		Long idImage = saveImage(imageFolder);
		imageFolder.setId(idImage);		
		
		FolderDTO folder = new FolderDTO((long) -1, "Tout", 0, null, imageFolder, new HashSet<PecsDTO>());
		Long idFolder = saveFolder(folder);
		folder.setId(idFolder);
	
		AlbumDTO album = new AlbumDTO((long) -1, folder);
		Long idAlbum = saveAlbum(album);
		album.setId(idAlbum);
		studentDTO.setAlbum(album);
		
		Student student = new Student(studentDTO);		
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(student);
	    session.getTransaction().commit();
	    
	    return student.getId();
	}
	
	private Long saveTeacher(TeacherDTO teacherDTO) {
			
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
	    Set<StudentDTO> students = new HashSet<StudentDTO>(teacherDTO.getStudents().size());	
		for(StudentDTO student : teacherDTO.getStudents()) {
			students.add(Transtypage.createStudentDTO((Student) session.load(Student.class, student.getId())));
		}
		
		teacherDTO.setStudents(students);		
		
		Teacher teacher = new Teacher(teacherDTO);	
		
	    session.save(teacher);
	    session.getTransaction().commit();
	    
	    return teacher.getId();
	}
	
	private Long saveReferent(ReferentDTO referentDTO) {
			
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
	    Set<StudentDTO> students = new HashSet<StudentDTO>(referentDTO.getStudents().size());	
		for(StudentDTO student : referentDTO.getStudents()) {
			students.add(Transtypage.createStudentDTO((Student) session.load(Student.class, student.getId())));
		}
		
		referentDTO.setStudents(students);
		
		Referent referent = new Referent(referentDTO);
	    
	    session.save(referent);
	    session.getTransaction().commit();
	    
	    return referent.getId();
	}

	
	/* SAVE */

	
	@Override
	public Long saveFolder(FolderDTO folderDTO) {
			
		//Set<PecsDTO> savedCollection = folderDTO.getContent();
		folderDTO.setContent(new HashSet<PecsDTO>());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Folder parent = (folderDTO.getFolder()==null?null:new Folder(folderDTO.getFolder(),null));	
		Folder folder = new Folder(folderDTO,parent);
	   
	    session.save(folder);
	    session.getTransaction().commit();
	    /*
	    for(PecsDTO pecs:savedCollection)
	    {
	    	long idImage = saveImage(pecs.getImage());
	    	pecs.getImage().setId(idImage);
	    	pecs.getFolder().setId(folder.getId());
	    	
	    	
	    	if(pecs instanceof ArticleDTO)
	    	{
	    		saveArticle((ArticleDTO)pecs);
	    	}
	    	else
	    	{
	    		saveFolder((FolderDTO)pecs);
	    	}
	    	
	    }*/

	    return folder.getId();
	}
	
	@Override
	public Long saveArticle(ArticleDTO articleDTO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();   
	    session.beginTransaction();
		
		Article article = new Article(articleDTO,new Folder(articleDTO.getFolder(),null));	    
	    session.save(article);
	    session.getTransaction().commit();
	    return article.getId();
	}
	
	
	
	@Override
	public Long saveAlbum(AlbumDTO albumDTO) {
		
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    Album album = new Album(albumDTO);
	    session.save(album);
	    session.getTransaction().commit();
	    return album.getId();
	}
	
	/* UPDATE */
	
	@Override
	public boolean updateArticle(ArticleDTO articleDTO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	 
		
		Article article = new  Article(articleDTO,new Folder(articleDTO.getFolder(),null));
	    session.update(article);
	    session.getTransaction().commit();
	    return true;
	}

	@Override
	public boolean updateFolder(FolderDTO folderDTO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		    
		Folder parent = (folderDTO.getFolder()==null?null:new Folder(folderDTO.getFolder(),null));
		Folder folder = new  Folder(folderDTO,parent);
	   
	    session.update(folder);
	    session.getTransaction().commit();
	    return true;
	}
	
	@Override
	public Long updateImage(ImageDTO imageDTO) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    Image image = new Image(imageDTO);	    
	    session.update(image);
	    session.getTransaction().commit();
	    return image.getId();
	}
	

	@Override
	public Long updateUser(UserDTO userDTO, boolean isNewPassword) {
		
		updateImage(userDTO.getImage());
		
		if (isNewPassword)
			userDTO.setPassword(Encryption.toSHA256(userDTO.getPassword()));
		
		if (userDTO instanceof StudentDTO)
			return updateStudent((StudentDTO) userDTO);
		else if (userDTO instanceof TeacherDTO)
			return updateTeacher((TeacherDTO) userDTO);
		else if (userDTO instanceof ReferentDTO)
			return updateReferent((ReferentDTO) userDTO);
		else
			return null;
	}
	
	private Long updateStudent(StudentDTO studentDTO) {
		
		Student student = new Student(studentDTO);		
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.update(student);
	    session.getTransaction().commit();
	    
	    return student.getId();
	}
	
	private Long updateTeacher(TeacherDTO teacherDTO) {
			
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
	    Set<StudentDTO> students = new HashSet<StudentDTO>(teacherDTO.getStudents().size());	
		for(StudentDTO student : teacherDTO.getStudents()) {
			students.add(Transtypage.createStudentDTO((Student) session.load(Student.class, student.getId())));
		}
		
		teacherDTO.setStudents(students);		
		
		Teacher teacher = new Teacher(teacherDTO);	
		
	    session.update(teacher);
	    session.getTransaction().commit();
	    
	    return teacher.getId();
	}
	
	private Long updateReferent(ReferentDTO referentDTO) {
			
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
	    Set<StudentDTO> students = new HashSet<StudentDTO>(referentDTO.getStudents().size());	
		for(StudentDTO student : referentDTO.getStudents()) {
			students.add(Transtypage.createStudentDTO((Student) session.load(Student.class, student.getId())));
		}
		
		referentDTO.setStudents(students);	
		
		Referent referent = new Referent(referentDTO);	
	    
	    session.update(referent);
	    session.getTransaction().commit();
	    
	    return referent.getId();
	}
	
	/* DELETE */
	
	@Override
	public boolean deleteArticle(long id) {
	
		
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();    
	    session.beginTransaction();
	    Article article = (Article) session.load(Article.class, id);	
	    session.delete(article);
	    session.getTransaction().commit();
	    return true;
		
	}
	
	@Override
	public boolean deleteFolder(long id) {

	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();    
	    session.beginTransaction();
	    Folder folder = (Folder) session.load(Folder.class, id);	
	    session.delete(folder);
	    session.getTransaction().commit();
	    return true;
	}

	@Override
	public boolean deleteUser(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();    
	    session.beginTransaction();
	    User user = (User) session.load(User.class, id);
	    
	    if (user instanceof Student) {
	    	
	    	((Student) user).getLogs().clear();
	    	UserDTO userDTO = Transtypage.createUserDTO(user);
	    	
	    	/* Suppressing the link between referents and students */
		    
		    List<ReferentDTO> referents = getAllReferents();
		    
		    for(ReferentDTO referent : referents) {
		    	for(StudentDTO student : referent.getStudents()) {
		    		if (student.getId() == id) {
		    			referent.getStudents().remove(student);
		    			break;
		    		}
		    	}
		    }
		    
		    /* Suppressing the link between referents and teachers */
		    
		    List<TeacherDTO> teachers = getAllTeachers();
		    
		    for(TeacherDTO teacher : teachers) {
		    	for(StudentDTO student : teacher.getStudents()) {
		    		if (student.getId() == id) {
		    			teacher.getStudents().remove(student);
		    			break;
		    		}
		    	}
		    }
		    
		    //session.getTransaction().commit();
		    
		    /* Updating student, teachers and referents */
	    	
	    	updateUser(userDTO, false);
		    
		    for(ReferentDTO referent : referents) {
		    	updateUser(referent, false);
		    }
		    
		    for(TeacherDTO teacher : teachers) {
		    	updateUser(teacher, false);
		    }
	    	
	    } else {
	    	session.getTransaction().commit();
	    }
	    
	    session = HibernateUtil.getSessionFactory().getCurrentSession();    
	    session.beginTransaction();
	    
	    session.delete(user);
	    session.getTransaction().commit();
	    return true;
	}


}
