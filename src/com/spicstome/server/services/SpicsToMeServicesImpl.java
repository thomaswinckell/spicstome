package com.spicstome.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Referent;
import com.spicstome.client.shared.Student;
import com.spicstome.client.shared.Subject;
import com.spicstome.client.shared.Teacher;
import com.spicstome.client.shared.User;
import com.spicstome.client.shared.Verb;
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
	public List<AlbumDTO> getGeneralAndExampleAlbum() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AlbumDTO> list = new ArrayList<AlbumDTO>();
		Album general = (Album) session.load(Album.class, (long)1);
		Album example = (Album) session.load(Album.class, (long)2);
		list.add(Transtypage.createAlbumDTO(general));
		list.add(Transtypage.createAlbumDTO(example));
		session.getTransaction().commit();
		
		return list;
	}
	
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
	    
	    if(users.size()>0)
	    {
	    	StudentDTO student = Transtypage.createStudentDTO(users.get(0));

	    	session.getTransaction().commit();
	    	return student;
	    }
	    else
	    {
	    	session.getTransaction().commit();
	    	return null;
	    }

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
			/*
		ImageDTO imageFolder = new ImageDTO((long) -1, "all.png");
		Long idImage = saveImage(imageFolder);
		imageFolder.setId(idImage);		
		
		FolderDTO folder = new FolderDTO((long) -1, "Tout", 0, null, imageFolder, new ArrayList<PecsDTO>());
		Long idFolder = saveFolder(folder);
		folder.setId(idFolder);*/
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Album example = (Album) session.load(Album.class, (long)2);
		AlbumDTO exampleDTO = Transtypage.createAlbumDTO(example);
		
		session.getTransaction().commit();
		
		
		
		AlbumDTO album = copyAlbum(exampleDTO);
		studentDTO.setAlbum(album);

			
	    Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
	    session2.beginTransaction();
	    
	    Student student = new Student(studentDTO);	
	    session2.save(student);
	    session2.getTransaction().commit();
	    
	    return student.getId();
	}
	
	private Long saveTeacher(TeacherDTO teacherDTO) {
			
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
	    ArrayList<StudentDTO> students = new ArrayList<StudentDTO>(teacherDTO.getStudents().size());	
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
	    
	    ArrayList<StudentDTO> students = new ArrayList<StudentDTO>(referentDTO.getStudents().size());	
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
		folderDTO.setContent(new ArrayList<PecsDTO>());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Folder parent = (folderDTO.getFolder()==null?null:new Folder(folderDTO.getFolder(),null));	
		Folder folder = new Folder(folderDTO,parent);
		 
	    session.save(folder);
	    session.getTransaction().commit();

	    return folder.getId();
	}
	
	@Override
	public Long saveArticle(ArticleDTO articleDTO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();   
	    session.beginTransaction();
	    
	    long idRes = 0;
		
	    if(articleDTO instanceof SubjectDTO)
	    {
	    	SubjectDTO subjectDTO = (SubjectDTO)articleDTO;
	    	Subject subject = new Subject(subjectDTO,new Folder(subjectDTO.getFolder(),null));	
	    	
			session.save(subject);
			
			idRes = subject.getId();
	    }
	    else if(articleDTO instanceof VerbDTO)
	    {
	    	VerbDTO verbDTO = (VerbDTO)articleDTO;
	    	Verb verb = new Verb(verbDTO,new Folder(verbDTO.getFolder(),null));	
	    	
			session.save(verb);
			
			idRes = verb.getId();
	    }
		
		
	  
	    session.getTransaction().commit();
	    return idRes;
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
		
		if(articleDTO instanceof SubjectDTO)
		{
			SubjectDTO subjectDTO = (SubjectDTO)articleDTO;
			Subject subject = new  Subject(subjectDTO,new Folder(subjectDTO.getFolder(),null));
		    session.update(subject);
		}
		else if(articleDTO instanceof VerbDTO)
		{
			VerbDTO verbDTO = (VerbDTO)articleDTO;
			Verb verb = new  Verb(verbDTO,new Folder(verbDTO.getFolder(),null));
		    session.update(verb);
		}
		
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
	    
	    ArrayList<StudentDTO> students = new ArrayList<StudentDTO>(teacherDTO.getStudents().size());	
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
	    
	    ArrayList<StudentDTO> students = new ArrayList<StudentDTO>(referentDTO.getStudents().size());	
		for(StudentDTO student : referentDTO.getStudents()) {
			students.add(Transtypage.createStudentDTO((Student) session.load(Student.class, student.getId())));
		}
		
		referentDTO.setStudents(students);	
		
		Referent referent = new Referent(referentDTO);	
	    
	    session.update(referent);
	    session.getTransaction().commit();
	    
	    return referent.getId();
	}
	
	@Override
	public boolean updateAlbum(AlbumDTO album) {
		// TODO Auto-generated method stub
		return false;
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

	/* COPY */
	
	public ArticleDTO copyArticle(ArticleDTO article,FolderDTO parent)
	{
		ImageDTO copyImage = new ImageDTO((long)-1, article.getImage().getFilename());	
		Long idImageArticle = saveImage(copyImage);
		
		if(article instanceof SubjectDTO)
		{
			SubjectDTO subjectDTO = (SubjectDTO) article;
			SubjectDTO copySubject= new SubjectDTO((long)-1,subjectDTO.getName(),subjectDTO.getOrder(),parent,copyImage,new HashSet<LogDTO>(),0,subjectDTO.getGender(),subjectDTO.getNature(),subjectDTO.getNumber()) ;
			
			copySubject.getImage().setId(idImageArticle);
			Long id = saveArticle(copySubject);
			copySubject.setId(id);
			
			return copySubject;
		}
		else if(article instanceof VerbDTO)
		{
			VerbDTO verbDTO = (VerbDTO) article;
			VerbDTO copyVerb= new VerbDTO((long)-1,verbDTO.getName(),verbDTO.getOrder(),parent,copyImage,new HashSet<LogDTO>(),0,
					verbDTO.getGroup(),verbDTO.getIrregular1(),verbDTO.getIrregular2(),verbDTO.getIrregular3(),verbDTO.getIrregular4(),verbDTO.getIrregular5(),verbDTO.getIrregular6()) ;
			
			copyVerb.getImage().setId(idImageArticle);
			Long id = saveArticle(copyVerb);
			copyVerb.setId(id);
			
			return copyVerb;
		}

	
		return null;
	}
	
	public FolderDTO copyFolder(FolderDTO folderDTO,FolderDTO parent)
	{
		FolderDTO copyFolder = new FolderDTO((long)-1,
				folderDTO.getName(),
				folderDTO.getOrder(),
				parent,
				new ImageDTO((long)-1,folderDTO.getImage().getFilename()),
				new ArrayList<PecsDTO>());
		
		Long idImage = saveImage(copyFolder.getImage());
		copyFolder.getImage().setId(idImage);
		
		Long idFolder = saveFolder(copyFolder);    
		copyFolder.setId(idFolder);
		
		for(PecsDTO pecs :folderDTO.getContent())
		{

			if(pecs instanceof ArticleDTO)
			{
				ArticleDTO copyArticle = copyArticle((ArticleDTO)pecs, copyFolder);	
				copyFolder.getContent().add(copyArticle);
			}
			else
			{
				FolderDTO copyFolder2 = copyFolder((FolderDTO)pecs, copyFolder);			
				copyFolder.getContent().add(copyFolder2);
			}

		}
		
		return copyFolder;
	}

	@Override
	public AlbumDTO copyAlbum(AlbumDTO albumDTO) {

		FolderDTO copyRacine = copyFolder(albumDTO.getFolder(),null);		
		AlbumDTO copyAlbum = new AlbumDTO((long)-1,copyRacine);	
		long id = saveAlbum(copyAlbum);
	
		copyAlbum.setId(id);		
		return copyAlbum;
		
	}


	
	


	
}
