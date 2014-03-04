package com.spicstome.server.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletException;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.MailDTO;
import com.spicstome.client.dto.MailListDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.PronounDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.shared.Adjective;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Log;
import com.spicstome.client.shared.Pecs;
import com.spicstome.client.shared.Point2D;
import com.spicstome.client.shared.Word;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Noun;
import com.spicstome.client.shared.Pronoun;
import com.spicstome.client.shared.Referent;
import com.spicstome.client.shared.Student;
import com.spicstome.client.shared.Teacher;
import com.spicstome.client.shared.User;
import com.spicstome.client.shared.Verb;
import com.spicstome.server.Encryption;
import com.spicstome.server.HibernateUtil;
import com.spicstome.server.business.MailHelper;

public class SpicsToMeServicesImpl extends RemoteServiceServlet implements SpicsToMeServices {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	/* SESSION */
	
	@Override
	public UserDTO getUser(String login, String password) {
		
		String hash = Encryption.toSHA256(password);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    @SuppressWarnings("unchecked")
		List<User> users = session.createCriteria(User.class).add(Restrictions.eq("login",login)).add(Restrictions.eq("password",hash)).list();
	    
	    if (users.isEmpty()) {
	    	session.getTransaction().commit();
	    	return null;
	    } else {
	    	UserDTO userDTO = Transtypage.createUserDTO(users.get(0));
	    	userDTO.setPassword(password);
	    	getThreadLocalRequest().getSession().setAttribute("currentUser", userDTO);
	    	session.getTransaction().commit();
	    	return userDTO;
	    }
	}

	@Override
	public UserDTO getCurrentUser() {
	
		Object o  = getThreadLocalRequest().getSession().getAttribute("currentUser");
		if(o==null)
			return null;
		
		long id = Long.valueOf(((UserDTO) o).getId().toString());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    User user = (User) session.load(User.class, id);
	    
	    UserDTO userDTO=null;
	    
	    if(user instanceof Student)
	    	userDTO = Transtypage.createStudentDTO((Student)user);
	    else if(user instanceof Teacher)
	    	userDTO = Transtypage.createTeacherDTO((Teacher)user);
	    else if(user instanceof Referent)
	    	userDTO = Transtypage.createReferentDTO((Referent)user);
	    
	    session.getTransaction().commit();
	    
	    userDTO.setPassword(((UserDTO) o).getPassword());
	    
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
	public UserDTO getUser(String mail) {		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    @SuppressWarnings("unchecked")
		List<User> users = session.createCriteria(User.class).add(Restrictions.eq("email",mail)).list();
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
	
	@Override
	public List<UserDTO> getEverybody() {
		
		List<UserDTO> all = new ArrayList<UserDTO>();
		
		List<ReferentDTO> referents =  getAllReferents();
		
		for(ReferentDTO referent:referents)
			all.add(referent);
		
		List<TeacherDTO> teachers =  getAllTeachers();
		
		for(TeacherDTO teacher:teachers)
			all.add(teacher);
		
		List<StudentDTO> students =  getAllStudents();
		
		for(StudentDTO student:students)
			all.add(student);
		
		return all;
	}
	

	@Override
	public Double getAverageMessageLength(int nWeekInCalendar,int nYearInCalendar,ArrayList<LogDTO> set) {
		
		Calendar calendar = Calendar.getInstance();
		double all=0.0;
		boolean nonConstrained= ((nWeekInCalendar==-1) || (nYearInCalendar==-1));
		double sum=0.0;	

		for(LogDTO log:set)
		{
			calendar.setTime(log.getDate());
			int nWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			int nYear = calendar.get(Calendar.YEAR);
			
			if(((nWeek==nWeekInCalendar) && (nYear==nYearInCalendar))|| nonConstrained)
			{
				sum+=log.getMessageLength();
				all++;
			}
			
		}
		
		if(all==0.0)
			return 0.0;

		return sum/all;
		
	}
	
	
	
	public double getCountMail(int nWeekInCalendar,int nYearInCalendar,ArrayList<LogDTO> set)
	{
		Calendar calendar = Calendar.getInstance();
		
		double sum=0.0;	
		int nWeek=-1;
		int nYear=-1;

		for(LogDTO log:set)
		{
			calendar.setTime(log.getDate());
			nWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			nYear = calendar.get(Calendar.YEAR);
			
			
			
			if((nWeek==nWeekInCalendar) && (nYear==nYearInCalendar))
			{
				sum++;
			}
		}

		return sum;
	}
	
	@Override
	public Double getAverageTimeExecution(int nWeekInCalendar,int nYearInCalendar,ArrayList<LogDTO> set) {
		
		Calendar calendar = Calendar.getInstance();
		double all=0.0;
		boolean nonConstrained= ((nWeekInCalendar==-1) || (nYearInCalendar==-1));
		double sum=0.0;	

		for(LogDTO log:set)
		{
			calendar.setTime(log.getDate());
			int nWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			int nYear = calendar.get(Calendar.YEAR);
			
			if(((nWeek==nWeekInCalendar) && (nYear==nYearInCalendar))|| nonConstrained)
			{
				sum+=log.getExecutionTime();
				all++;
			}
			
		}
		
		if(all==0.0)
			return 0.0;

		return sum/all;
		
	}

	@Override
	public ArrayList<Point2D> getHistoryPerWeek(ArrayList<LogDTO> set,int type) {
		
		ArrayList<Point2D> res = new ArrayList<Point2D>();
		int nbMonthBack=6;
		int nW;
		int nY;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
		int currentYear = cal.get(Calendar.YEAR);
		cal.add(Calendar.MONTH,-nbMonthBack);

		do{
			
			nW = cal.get(Calendar.WEEK_OF_YEAR);
			nY = cal.get(Calendar.YEAR);
			System.out.println(nW+" "+nY);

			if(type==0)
				res.add(new Point2D((double) nW,getCountMail(nW,nY, set)));
			else if(type==1)
				res.add(new Point2D((double) nW,getAverageTimeExecution(nW,nY, set).doubleValue()));
			else if(type==2)
				res.add(new Point2D((double) nW,getAverageMessageLength(nW,nY, set).doubleValue()));

			
			cal.add(Calendar.WEEK_OF_YEAR, 1);
			
			
		}while(!((nW==currentWeek) && (nY==currentYear)));
		
		
		return res;
	}

	@Override
	public ArrayList<Double> getPartitionMessageLength(ArrayList<LogDTO> set) {
		
		ArrayList<Double> res  =new ArrayList<Double>();
		
		if(set.size()==0)
		{
			res.add(0.0);res.add(0.0);res.add(0.0);res.add(0.0);res.add(0.0);
			return res;
		}

		int nbMessage1=0;
		int nbMessage2=0;
		int nbMessage3=0;
		int nbMessage4=0;
		int nbMessage5p=0;
	
		for(LogDTO log:set)
		{
			switch(log.getMessageLength())
			{
				case 1:
					nbMessage1++;
				break;
				case 2:
					nbMessage2++;
				break;
				case 3:
					nbMessage3++;
				break;
				case 4:
					nbMessage4++;
				break;
				default:
					nbMessage5p++;
				break;
			}
		}
		
		res.add((double)nbMessage1/set.size()*100.0);
		res.add((double)nbMessage2/set.size()*100.0);
		res.add((double)nbMessage3/set.size()*100.0);
		res.add((double)nbMessage4/set.size()*100.0);
		res.add((double)nbMessage5p/set.size()*100.0);

		return res;
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

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Album album = new Album((long)-1);
		
		Image imageRoot = new Image((long)-1);
		imageRoot.setFilename("all.png");
		session.save(imageRoot);
		
		Folder root = new Folder((long)-1);
		root.setContent(new TreeSet<Pecs>());
		root.setImage(imageRoot);
		root.setName("Tout");
		session.save(root);
		
		album.setFolder(root);
		session.save(album);
	
		studentDTO.setAlbum(Transtypage.createAlbumDTO(album));

	    Student student = new Student(studentDTO);	
	    session.save(student);
	    
	    session.getTransaction().commit();
	    
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
	public Long saveLog(LogDTO logDTO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();   
		session.beginTransaction();
		
		Student student = new Student(logDTO.getStudent());
		
		Log log = new Log(logDTO,student);
		session.save(log);
		
		session.getTransaction().commit();
		return log.getId();
	}
	
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
	public Long saveWord(WordDTO wordDTO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();   
	    session.beginTransaction();
	    
	    long idRes = 0;
		
	    if(wordDTO instanceof SubjectDTO)
	    {
	    	if(wordDTO instanceof NounDTO)
	 	    {
	    		NounDTO nounDTO = (NounDTO)wordDTO;
		    	Noun noun = new Noun(nounDTO,new Folder(nounDTO.getFolder(),null));	 	
				session.save(noun);		
				idRes = noun.getId();
	 	    }
	    	else if(wordDTO instanceof PronounDTO)
	 	    {
	    		PronounDTO pronounDTO = (PronounDTO)wordDTO;
		    	Pronoun pronoun = new Pronoun(pronounDTO,new Folder(pronounDTO.getFolder(),null));	 	
				session.save(pronoun);		
				idRes = pronoun.getId();
	 	    }
	    	else if(wordDTO instanceof ArticleDTO)
	 	    {
	    		ArticleDTO articleDTO = (ArticleDTO)wordDTO;
		    	Article article = new Article(articleDTO,new Folder(articleDTO.getFolder(),null));	 	
				session.save(article);		
				idRes = article.getId();
	 	    }
	    	
	    }
	    else if(wordDTO instanceof VerbDTO)
	    {
	    	VerbDTO verbDTO = (VerbDTO)wordDTO;
	    	Verb verb = new Verb(verbDTO,new Folder(verbDTO.getFolder(),null));	
	    	
			session.save(verb);
			
			idRes = verb.getId();
	    }
	    else if(wordDTO instanceof AdjectiveDTO)
	    {
	    	AdjectiveDTO adjectiveDTO = (AdjectiveDTO)wordDTO;
	    	Adjective adjective = new Adjective(adjectiveDTO,new Folder(adjectiveDTO.getFolder(),null));	
	    	
			session.save(adjective);
			
			idRes = adjective.getId();
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
	public boolean updateFolderAndChild(FolderDTO folder) {
		
		updateFolder(folder);
		
		for(PecsDTO pecsDTO:folder.getContent())
		{
			if(pecsDTO instanceof FolderDTO)	
			{
				updateFolderAndChild((FolderDTO)pecsDTO);
			}
		}
		
		return true;
	}

	
	@Override
	public boolean updateWord(WordDTO wordDTO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	 
		
		if(wordDTO instanceof SubjectDTO)
		{
			if(wordDTO instanceof NounDTO)
			{
				NounDTO nounDTO = (NounDTO)wordDTO;
				Noun noun = new  Noun(nounDTO,new Folder(nounDTO.getFolder(),null));
			    session.update(noun);
			}
			else if(wordDTO instanceof PronounDTO)
			{
				PronounDTO pronounDTO = (PronounDTO)wordDTO;
				Pronoun pronoun = new  Pronoun(pronounDTO,new Folder(pronounDTO.getFolder(),null));
			    session.update(pronoun);
			}
			else if(wordDTO instanceof ArticleDTO)
			{
				ArticleDTO pronounDTO = (ArticleDTO)wordDTO;
				Article article = new  Article(pronounDTO,new Folder(pronounDTO.getFolder(),null));
			    session.update(article);
			}
			
		}
		else if(wordDTO instanceof VerbDTO)
		{
			VerbDTO verbDTO = (VerbDTO)wordDTO;
			Verb verb = new  Verb(verbDTO,new Folder(verbDTO.getFolder(),null));
		    session.update(verb);
		}
		else if(wordDTO instanceof AdjectiveDTO)
		{
			AdjectiveDTO adjectiveDTO = (AdjectiveDTO)wordDTO;
			Adjective adjective = new  Adjective(adjectiveDTO,new Folder(adjectiveDTO.getFolder(),null));
		    session.update(adjective);
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
	public boolean deleteWord(long id) {
	
		
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();    
	    session.beginTransaction();
	    Word word = (Word) session.load(Word.class, id);	
	    session.delete(word);
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
	    	
	    	
	    	/*
	    	for(Log log:((Student) user).getLogs())
	    	{
	    		 session.delete(log);
	    	}
	    	
	    	((Student) user).getLogs().clear();
	    	*/
	    	
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
	
	public WordDTO copyWord(WordDTO word,FolderDTO parent)
	{
		ImageDTO copyImage = new ImageDTO((long)-1, word.getImage().getFilename());	
		Long idImageWord = saveImage(copyImage);
		
		if(word instanceof SubjectDTO)
		{
			if(word instanceof NounDTO)
			{
				NounDTO nounDTO = (NounDTO) word;
				NounDTO copyNoun= new NounDTO((long)-1,nounDTO.getName(),nounDTO.getOrder(),parent,copyImage,0,nounDTO.getGender(),nounDTO.getNumber(),nounDTO.getUncountable()) ;
				
				copyNoun.getImage().setId(idImageWord);
				Long id = saveWord(copyNoun);
				copyNoun.setId(id);
				
				return copyNoun;
			}
			else if(word instanceof PronounDTO)
			{
				PronounDTO pronounDTO = (PronounDTO) word;
				PronounDTO copyPronoun= new PronounDTO((long)-1,pronounDTO.getName(),pronounDTO.getOrder(),parent,copyImage,0,pronounDTO.getGender(),pronounDTO.getPerson(),pronounDTO.getNumber()) ;
				
				copyPronoun.getImage().setId(idImageWord);
				Long id = saveWord(copyPronoun);
				copyPronoun.setId(id);
				
				return copyPronoun;
			}
			else if(word instanceof ArticleDTO)
			{
				ArticleDTO articleDTO = (ArticleDTO) word;
				ArticleDTO copyPronoun= new ArticleDTO((long)-1,articleDTO.getName(),articleDTO.getOrder(),parent,copyImage,0,articleDTO.getGender(),articleDTO.getNumber()) ;
				
				copyPronoun.getImage().setId(idImageWord);
				Long id = saveWord(copyPronoun);
				copyPronoun.setId(id);
				
				return copyPronoun;
			}
			
		}
		else if(word instanceof VerbDTO)
		{
			VerbDTO verbDTO = (VerbDTO) word;
			VerbDTO copyVerb= new VerbDTO((long)-1,verbDTO.getName(),verbDTO.getOrder(),parent,copyImage,0,
					verbDTO.getNegation(),verbDTO.getGroup(),verbDTO.getIrregular1(),verbDTO.getIrregular2(),verbDTO.getIrregular3(),verbDTO.getIrregular4(),verbDTO.getIrregular5(),verbDTO.getIrregular6()) ;
			
			copyVerb.getImage().setId(idImageWord);
			Long id = saveWord(copyVerb);
			copyVerb.setId(id);
			
			return copyVerb;
		}
		else if(word instanceof AdjectiveDTO)
		{
			AdjectiveDTO ajectiveDTO = (AdjectiveDTO) word;
			AdjectiveDTO copyAdjective= new AdjectiveDTO((long)-1,ajectiveDTO.getName(),ajectiveDTO.getOrder(),parent,copyImage,0,
					ajectiveDTO.getMatching1(),ajectiveDTO.getMatching2(),ajectiveDTO.getMatching3(),ajectiveDTO.getMatching4()) ;
			
			copyAdjective.getImage().setId(idImageWord);
			Long id = saveWord(copyAdjective);
			copyAdjective.setId(id);
			
			return copyAdjective;
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

			if(pecs instanceof WordDTO)
			{
				WordDTO copyWord = copyWord((WordDTO)pecs, copyFolder);	
				copyFolder.getContent().add(copyWord);
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

	
	@Override
	public boolean sendMail(UserDTO sender, String emailReceiver, ArrayList<WordDTO> words, 
			ArrayList<String> correctedWords) {
		
		return MailHelper.sendMail(sender, emailReceiver, words, correctedWords);
	}
	
	@Override
	public MailListDTO getMails(UserDTO user, int startPosition, boolean isDescDirection, int maxNbValidMails) {
		
		MailListDTO mails =  MailHelper.mailsInbox(user, startPosition, isDescDirection, maxNbValidMails);
		
		// loading the sender user
		for(int i=0; i<mails.getMails().size(); i++) {
			mails.getMails().get(i).setSender(getUser(mails.getMails().get(i).getSender().getId()));
		}
		
		return mails;
	}
}
