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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    @SuppressWarnings("unchecked")
		List<User> users = session.createCriteria(User.class).add(Restrictions.eq("login",login)).add(Restrictions.eq("password",password)).list();
	    
	    if (users.isEmpty()) {
	    	session.getTransaction().commit();
	    	return null;
	    } else {
	    	UserDTO userDTO = Transtypage.createUserDTO(users.get(0));	    	
	    	getThreadLocalRequest().getSession().setAttribute("currentUser", userDTO);
	    	session.getTransaction().commit();
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
	
	/* GET */

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
	public List<AlbumDTO> getReferentAlbums() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Album> list = session.createCriteria(Album.class).list();
		
		
		List<AlbumDTO> listAlbumDTO=new ArrayList<>();

		if(!list.isEmpty())
		{ 
			for(int i=0;i<list.size();i++)
			{
				listAlbumDTO.add(Transtypage.createAlbumDTO(list.get(i)));
			}
		}
		
		session.getTransaction().commit();

		return listAlbumDTO;
	

	}
	
	private List<FolderDTO> GetFoldersFolder(FolderDTO folder)
	{
		List<FolderDTO> res = new ArrayList<FolderDTO>();
		
		res.add(folder);

		for(PecsDTO p:folder.getContent())
		{
			if(p instanceof FolderDTO)
			{
				res.addAll(GetFoldersFolder((FolderDTO)p));
				
			}
		}	
		
		return res;
	}
	

	@Override
	public List<FolderDTO> getFoldersAlbum(long id) {
		
		AlbumDTO albumDTO = getAlbum(id);
		if(albumDTO.getFolder()!=null)
		{
			return GetFoldersFolder(albumDTO.getFolder());
		}
		else
		{
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
	
	/* SAVE */
	
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
	public Long saveUser(UserDTO userDTO) {
		if (userDTO instanceof StudentDTO)
			return saveStudent((StudentDTO) userDTO);
		else if (userDTO instanceof TeacherDTO)
			return saveTeacher((TeacherDTO) userDTO);
		else if (userDTO instanceof ReferentDTO)
			return saveReferent((ReferentDTO) userDTO);
		else
			return null;
	}

	
	@Override
	public Long saveTeacher(TeacherDTO teacherDTO) {
		
		Long idImageUser = saveImage(teacherDTO.getImage());
		teacherDTO.getImage().setId(idImageUser);
			
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
	
	@Override
	public Long saveReferent(ReferentDTO referentDTO) {
		
		Long idImageUser = saveImage(referentDTO.getImage());
		referentDTO.getImage().setId(idImageUser);
		
		Referent referent = new Referent(referentDTO);		
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(referent);
	    session.getTransaction().commit();
	    
	    return referent.getId();
	}
	
	/* SAVE */
	
public Long saveFolder(FolderDTO folderDTO) {
		
		ImageDTO imageFolder = folderDTO.getImage();
		Long id = saveImage(imageFolder);
		imageFolder.setId(id);
		
		Folder parent = (folderDTO.getFolder()==null?null:new Folder(folderDTO.getFolder(),null));
		Folder folder = new Folder(folderDTO,parent);
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    session.save(folder);
	    session.getTransaction().commit();
	    return folder.getId();
	}
	
	@Override
	public Long saveArticle(ArticleDTO articleDTO) {
		
		ImageDTO imageArticle = articleDTO.getImage();
		Long id = saveImage(imageArticle);
		imageArticle.setId(id);
		
		Article article = new Article(articleDTO,new Folder(articleDTO.getFolder(),null));
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    
	    session.beginTransaction();
	    session.save(article);
	    session.getTransaction().commit();
	    return article.getId();
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
}
